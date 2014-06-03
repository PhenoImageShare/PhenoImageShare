package uk.ac.ebi.phis.utils.ontology;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Normalizer {

	public Normalizer(){
	}
	
	
	public String normalizeZygosity(String zygosity) throws Exception{
		String z = zygosity.toLowerCase();
		
		if (z.startsWith("hom")){
			return "homozygous";
		}
		else if (z.startsWith("het")){
			return "heterozygous";
		}
		else if (z.startsWith("hemi")){
			return "hemizygous";
		}
		else if (z.equals("wt")){
			return "wild type";
		}
		else if (z.equals("?") || z.equals("fail")){
			return "unspecified";
		}
		else throw new Exception("Could not match \"" + zygosity + "\"to an existing zygosity");
	}
	
	public String normalizeSex(String sex) throws Exception{
		String z = sex.toLowerCase();
		
		if (z.startsWith("m")){
			return "Male";
		}
		else if (z.startsWith("f")){
			return "Female";
		}
		else if (z.equalsIgnoreCase("unsexed")){
			return "Unsexed";
		}
		else if (z.equalsIgnoreCase("") || z == null){
			return "Unknown";
		}
		else throw new Exception("Could not match \"" + sex + "\"to an existing zygosity");
	}
	
	public float getAgeInDays(String inputAge){
		String age = inputAge.toLowerCase();
		if (age.endsWith("w")){
			Float weeks = new Float(age.replace("w", ""));
			return weeks * 7; // we need the age in days
		}
		else if (age.endsWith("w (at death)")){
			Float weeks = new Float(age.replace("w (at death)", ""));
			return  weeks * 7; // we need the age in days
		}
		else if (age.endsWith("e")){
			return new Float(age.replace("e", ""));
		}
		else if (age.endsWith("e (at death)")){
			return new Float(age.replace("e (at death)", ""));
		}
		else if (age.startsWith("unknown")){
			return -1; 	// "" stands for unspecified age
		}
		else if (age.startsWith("e")){
			return new Float(age.replace("e", ""));
		}
		return new Float(age);
	}
	
	public String normalizeAge(String inputAge){
		String age = inputAge.toLowerCase();
		if (age.endsWith("w")){
			Float weeks = new Float(age.replace("w", ""));
			return String.format("%.2f", weeks * 7); // we need the age in days
		}
		else if (age.endsWith("w (at death)")){
			Float weeks = new Float(age.replace("w (at death)", ""));
			return String.format("%.2f", weeks * 7); // we need the age in days
		}
		else if (age.endsWith("e")){
			return String.format("%.2f", new Float(age.replace("e", "")));
		}
		else if (age.endsWith("e (at death)")){
			return String.format("%.2f", new Float(age.replace("e (at death)", "")));
		}
		else if (age.startsWith("unknown")){
			return "-1"; 	// "" stands for unspecified age
		}
		else if (age.startsWith("e")){
			return String.format("%.2f", new Float(age.replace("e", "")));
		}
		return String.format("%.2f", new Float(age));
	}
	
	public boolean isEmbryonicAge(String age){
		if  (age.endsWith("e") || age.endsWith("e (at death)"))
			return true;
		else return false;
	}
	
	public String getSequence(String geneSymbol, String specieSnakeCase) throws JSONException, IOException{
		// http://beta.rest.ensembl.org/xrefs/symbol/mus_musculus/AKT2?content-type=application/json
		String uri = "http://beta.rest.ensembl.org/xrefs/symbol/" + specieSnakeCase + "/" + geneSymbol + "?content-type=application/json";
		JSONArray arr = readJsonArrayFromUrl(uri);
		String ensemblId = null;
		int i = 0 ;
		while (i++ < arr.length()){
			JSONObject obj = (JSONObject) arr.get(0);
			if (obj.getString("type").equalsIgnoreCase("gene")){
				ensemblId = obj.getString("id");
				break;
			}
		}
		// http://beta.rest.ensembl.org/sequence/id/ENSMUSG00000004056?content-type=text/plain
		if (ensemblId != null){
			String sequenceUrl = "http://beta.rest.ensembl.org/sequence/id/" + ensemblId + "?content-type=text/plain";
			return getStringFromUrl (sequenceUrl);
		}
		return null;
	}
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	public static JSONArray readJsonArrayFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONArray json = new JSONArray(jsonText);
			return json;
		} finally { is.close(); }
	}
	
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally { is.close(); }
	}
	
	public String getStringFromUrl(String url) throws MalformedURLException, IOException{
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String text = readAll(rd);
			return text;
		} finally { is.close(); }
	}
	
	public GenomicLocation getGenomeLocation(String ensemblId) throws JSONException, IOException{
		String url = "http://beta.rest.ensembl.org/lookup/id/" + ensemblId + "?content-type=application/json";
		// http://beta.rest.ensembl.org/lookup/id/ENSG00000157764?content-type=application/json
		JSONObject json = readJsonFromUrl(url);
		GenomicLocation loc = new GenomicLocation();
		loc.setChromosomeString(json.getString("seq_region_name"));
		loc.setEnd(json.getDouble("end"));
		loc.setEnsemblId(json.getString("id"));
		loc.setStart(json.getDouble("start"));
		loc.setStrand(json.getInt("strand"));
		return loc;
	}
	

	public String getImageType (String procedure){
		if (procedure.equalsIgnoreCase("Adult LacZ") || procedure.equalsIgnoreCase("Wholemount Expression") || procedure.equalsIgnoreCase("Anti-nuclear Antibody Assay"))
			return "expression";
		else if (procedure.equalsIgnoreCase("FACS Analysis") || procedure.equalsIgnoreCase("Flow Cytometry"))
			return "cytometry";
		else if (procedure.equalsIgnoreCase("Histopathology") || procedure.equalsIgnoreCase("Skin Histopathology") || procedure.equalsIgnoreCase("Histology Slide") 
				|| procedure.equalsIgnoreCase("Brain Histopathology") || procedure.equalsIgnoreCase("DSS Histology Scores"))
			return "histopathology";
		else if (procedure.equalsIgnoreCase("X-ray") || procedure.equalsIgnoreCase("X-ray Imaging") || procedure.equalsIgnoreCase("Xray"))
			return "xray";
		else if (procedure.equalsIgnoreCase("Combined SHIRPA and Dysmorphology") || procedure.equalsIgnoreCase("Dysmorphology") || procedure.equalsIgnoreCase("Embryo Dysmorphology"))
			return "dysmorphology";
		return "other"; // see rest of types here http://ves-ebi-d0.ebi.ac.uk:8090/mi/impc/dev/solr/images/select?q=*:*&group.field=procedure_name&group=true&fl=procedure_name
	}
}

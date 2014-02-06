package uk.ac.ebi.pish.importer;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.client.HttpClient;

public class Komp2Importer {
	
	HttpSolrServer solr;
	
	public Komp2Importer(){
		
	}
	/**
	 * Need to import as many as possible from:
	 * age
	 * anatomy
	 * anatomy freetext
	 * strain
	 * data source
	 * gene name
	 * gene id
	 * image path
	 * image context path
	 * image type
	 * genotype type
	 * organism
	 * phenotype
	 * phenotype freetext
	 * procedure type
	 * procedure id
	 * sex
	 * zygosity
	 * sequence
	 * stage
	 * conditions
	 * observations
	 * spatial annotation
	 * @throws Exception 
	 * @throws JSONException 
	 */
	public void downloadAndParse() throws JSONException, Exception{
		System.out.println("start the function");
		
		String solrURL = "http://ves-ebi-d0.ebi.ac.uk:8090/mi/impc/dev/solr/images"; //default
		String dbPrefix = "komp2_";
		solr = new HttpSolrServer(solrURL);
		
		SolrQuery query = new SolrQuery()
        .setQuery("*:*")
        .setStart(0).setRows(1000000);
		
		SolrDocumentList documents = solr.query(query).getResults();
		System.out.println(solr.getBaseURL()+"/select?"+query);
		int internal_id = 0; 
		
		JSONObject json = new JSONObject();
		
		long time = System.currentTimeMillis();
		int k = 0; 
		
		for (SolrDocument doc : documents ){
			try{
				JSONObject jsonDoc = new JSONObject();
			
				// Generate internal_id
				String uniqueId = dbPrefix + (internal_id++);
				jsonDoc.put("uniqueId", uniqueId);
				
				String days = (String)doc.getFieldValue("ageInWeeks");
				if(isEmbryonicAge(days))
					jsonDoc.put("embrionicAge", normalizeAge(days));
				else 
					jsonDoc.put("age", normalizeAge(days));
				// stage 	->	see dataStructuringDraft.docx. At the moment we won't compute this, olny have it if it is already provided, but we should allow the user to annotate it!
				jsonDoc.put("anatomyOntId", doc.getFieldValue("maTermId"));
				jsonDoc.put("anatomyOntTerm", doc.getFieldValue("maTermName"));
				//TODO image type
				// need to infere
				
				//TODO genotype type
				// also need to infere & check if that information is in the images core at all. We have: tm1a, tm1b, gene trap.
				
				jsonDoc.put("imageUrl", "http://www.mousephenotype.org/data/media/" + doc.getFieldValue("fullResolutionFilePath").toString());
				//TODO  Image context URL must be computed but at the moment we can't distinguish between genes and alleles (Wait for the weirdness int he image core to dissappear)
				// jsondoc.put("imageContextUrl" , "" + doc.getFieldValue(""));
				jsonDoc.put("institute", doc.getFieldValue("institute"));
				jsonDoc.put("geneSymbol", doc.getFieldValue("accession"));
				jsonDoc.put("geneName", doc.getFieldValue("geneName"));
				jsonDoc.put("organism", "Mus musculus");
				jsonDoc.put("phenotypeOntId", doc.getFieldValue("mpTermId"));
				jsonDoc.put("phenotypeOntTerm", doc.getFieldValue("mpTermName"));
				jsonDoc.put("procedure", doc.getFieldValue("procedure_name"));
				jsonDoc.put("sex", normalizeSex((String) doc.getFieldValue("gender")));
				jsonDoc.put("zygosity", normalizeZygosity((String) doc.getFieldValue("genotype")));
				//TODO sequence
				// get from ENSEMBL
				// I will need to uncomment this when we can get the gene name/symbol from the index
				
				// conditions - DON'T apply
				if (doc.containsKey("tagName")){
					ArrayList<String> tagNames = (ArrayList<String>) doc.getFieldValue("tagName");
					ArrayList<String> tagValues = (ArrayList<String>) doc.getFieldValue("tagValue");
					for (int i = 0; i < tagValues.size(); i ++)
					{
						jsonDoc.accumulate("otherObs", tagNames.get(i) + ": " + tagValues.get(i));
					}
				}
				jsonDoc.put("xStart", doc.getFieldValue("xStart"));
				jsonDoc.put("yStart", doc.getFieldValue("yStart"));
				//TODO if all ends are 0, set them to image size
				jsonDoc.put("xEnd", doc.getFieldValue("xEnd"));
				jsonDoc.put("yEnd", doc.getFieldValue("yEnd"));
				jsonDoc.put("xyUnit", "px");
				json.append("entry_"+uniqueId, jsonDoc);
				k++;
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(doc);
					break;
				}
		}
		System.out.println("Done building the JSON with " + k + " entries in " + (System.currentTimeMillis()-time));
		time = System.currentTimeMillis();
		BufferedWriter cout = new BufferedWriter(new FileWriter ( new File("source/main/resources/komp2ImagesDump.json")));
		cout.write(json.toString());
		cout.close();
		System.out.println("Done writing to file in " + (System.currentTimeMillis()-time));
	}
	
	public String normalizeZygosity(String zygosity) throws Exception{
		String z = zygosity.toLowerCase();
		
		if (z.startsWith("hom")){
			return "homozygote";
		}
		else if (z.startsWith("het")){
			return "heterozygote";
		}
		else if (z.startsWith("hemi")){
			return "hemizygote";
		}
		else if (z.equals("wt")){
			return "wild type";
		}
		else if (z.equals("?")){
			return "unspecified";
		}
		else throw new Exception("Could not match \"" + zygosity + "\"to an existing zygosity");
	}
	
	public String normalizeSex(String sex) throws Exception{
		String z = sex.toLowerCase();
		
		if (z.startsWith("m")){
			return "male";
		}
		else if (z.startsWith("f")){
			return "female";
		}
		else if (z.equalsIgnoreCase("unsexed")){
			return "unsexed";
		}
		else if (z.equalsIgnoreCase("") || z == null){
			return "unspecified";
		}
		else throw new Exception("Could not match \"" + sex + "\"to an existing zygosity");
	}
	
	public float normalizeAge(String inputAge){
		String age = inputAge.toLowerCase();
		if (age.endsWith("w")){
			Float weeks = new Float(age.replace("w", ""));
			return weeks * 7; // we need the age in days
		}
		else if (age.endsWith("w (at death)")){
			Float weeks = new Float(age.replace("w (at death)", ""));
			return weeks * 7; // we need the age in days
		}
		else if (age.endsWith("e")){
			Float weeks = new Float(age.replace("e", ""));
			return weeks * 7; // we need the age in days
		}
		else if (age.endsWith("e (at death)")){
			Float weeks = new Float(age.replace("e (at death)", ""));
			return weeks * 7; // we need the age in days
		}
		else if (age.startsWith("unknown")){
			return -1; 	// -1 stands for unspecified age
		}
		return new Float(age);
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
	
	public JSONObject getGenomeLocation(String ensemblId) throws JSONException, IOException{
		String url = "http://beta.rest.ensembl.org/lookup/id/" + ensemblId + "?content-type=application/json";
		// http://beta.rest.ensembl.org/lookup/id/ENSG00000157764?content-type=application/json
		
		return readJsonFromUrl(url);
	}
	
	public Map<String, Integer> getImageMeasuresFromUrl(String url){
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			 BufferedImage image = ImageIO.read(new URL(url));
			 map.put("height", image.getHeight());
			 map.put("width", image.getWidth());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
}


//internal id
			// age	->	ageInWeeks
			// anatomy id 	->	maTermId
			// anatomy name	->	
			// anatomy freetext 	-> doesn't apply to KOMP2
			// strain	->	get colony id instead?
			// data source -> institute
			// gene symbol	->	symbol
			// gene name	->	geneName
			// gene id	->	accession
			// image type
			// genotype type
			// organism	-> _fixed_
			// phenotype id	-> mpTermId
			// phenotype name	->	mpTermName
			// phenotype freetext 	-> doesn't apply to KOMP2
			// procedure type	->	procedure_name
			// procedure id	-> ? (not sure if we'd actually need this)
			// sex	-> gender
			// zygosity	->	genotype
			// sequence	->
			// stage	->
			// conditions	->
			// observations	->	see tags
			// startX	->	xStart	!list
			// startY	->	yStart	!list
			// endX	->	xEnd	!list
			// endY	->	yEnd	!list
			
			// tagName and tagValues are ordered lists of observations. Need to be matched.
			
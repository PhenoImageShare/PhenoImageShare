package uk.ac.ebi.pish.mains;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.json.JSONException;

import uk.ac.ebi.pish.importer.Komp2Importer;

public class main {

	public static void main(String[] args) throws SolrServerException {
		Komp2Importer komp = new Komp2Importer();
		Map <String, Integer> res = komp.getImageMeasuresFromUrl("http://www.mousephenotype.org/data/media/images/914/I20080318131854_download_full.jpg");
		System.out.println("height: " + res.get("height"));
		System.out.println("width: " + res.get("width"));
		
		try {
			System.out.println(komp.getSequence("akt2", "mus_musculus"));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			komp.downloadAndParse();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

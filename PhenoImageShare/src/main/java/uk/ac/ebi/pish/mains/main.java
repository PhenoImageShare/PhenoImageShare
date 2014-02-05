package uk.ac.ebi.pish.mains;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.json.JSONException;

import uk.ac.ebi.pish.importer.Komp2Importer;

public class main {

	public static void main(String[] args) throws SolrServerException {
		Komp2Importer komp = new Komp2Importer();
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

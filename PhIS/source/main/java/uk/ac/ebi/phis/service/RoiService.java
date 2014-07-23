package uk.ac.ebi.phis.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

import uk.ac.ebi.phis.solrj.dto.RoiPojo;


public class RoiService {

	private HttpSolrServer solr;
	

	public static final class RoiField {
		
	}
	

	public RoiService(String solrUrl) {
		solr = new HttpSolrServer(solrUrl);
	}
	

	public void addBeans(List<RoiPojo> docs){
		try {
			solr.addBeans(docs);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Removes all documents from the core
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public void clear() throws SolrServerException, IOException{
		solr.deleteByQuery("*:*");
	}
	
}

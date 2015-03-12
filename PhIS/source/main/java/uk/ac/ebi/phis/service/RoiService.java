package uk.ac.ebi.phis.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

import uk.ac.ebi.phis.solrj.dto.ChannelDTO;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;
import uk.ac.ebi.phis.utils.web.JSONRestUtil;


public class RoiService extends BasicService{

	
	public RoiService(String solrUrl) {
		super(solrUrl);
	}
	
	public String getRoiAsJsonString(String roiId){
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFilterQueries(RoiDTO.ID + ":\""+ roiId + "\"");
		solrQuery.set("wt", "json");
		
		System.out.println("------ ROI" + getQueryUrl(solrQuery));

		try {
			return JSONRestUtil.getResults(getQueryUrl(solrQuery)).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "Couldn't get anything back from solr.";
	}	


	public RoiDTO getRoiById(String id){
		
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(RoiDTO.ID + ":\""+ id + "\"");
		try {
			List<RoiDTO> results = solr.query(solrQuery).getBeans(RoiDTO.class);
			if (results.size() > 0){
				return results.get(0);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getRois(String imageId){
		
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFilterQueries(RoiDTO.ASSOCIATED_IMAGE_ID + ":\""+ imageId + "\"");
		solrQuery.set("wt", "json");
		

		try {
			return JSONRestUtil.getResults(getQueryUrl(solrQuery)).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "Couldn't get anything back from solr.";
	}	
	
	public void updateRoi(RoiDTO roi){
		List<RoiDTO> list = new ArrayList<>();
		list.add(roi);
		addBeans(list);
	}
	
	public void addRoi(RoiDTO roi){
		List<RoiDTO> list = new ArrayList<>();
		list.add(roi);
		addBeans(list);
	}
	
	public void addBeans(List<RoiDTO> docs){
		try {
			solr.addBeans(docs);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void deleteRoi(String roiId){
		try {
			solr.deleteByQuery(RoiDTO.ID + ":" + roiId);
			solr.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	

	public String getQueryUrl(SolrQuery q){
		return solr.getBaseURL() + "/select?" + q.toString();
	}
	
}

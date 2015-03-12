package uk.ac.ebi.phis.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;

import scala.Array;
import uk.ac.ebi.phis.solrj.dto.ChannelDTO;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;
import uk.ac.ebi.phis.utils.web.JSONRestUtil;

public class ChannelService extends BasicService {

	
	public ChannelService(String solrUrl) {

		super(solrUrl);
	}

	
	public void deleteAssociatedRoi(RoiDTO roi){

		String roiId = roi.getId();
		List<String> channelIds = roi.getAssociatedChannel();
		if (channelIds != null){
			for(String channelId : channelIds){
				ChannelDTO channel = getChannelBean(channelId);
				while (channel.getAssociatedRoi().contains(roiId)){
					channel.getAssociatedRoi().remove(roiId);
				}
				List<ChannelDTO> docs = new ArrayList<>();
				docs.add(channel);
				addBeans(docs);
			}
		}
	}
	
	public void addAssociatedRoi(RoiDTO roi){
		String roiId = roi.getId();
		List<String> channelIds = roi.getAssociatedChannel();
		if (channelIds != null){
			for(String channelId : channelIds){
				ChannelDTO channel = getChannelBean(channelId);
				channel.addAssociatedRoi(roiId);
				List<ChannelDTO> docs = new ArrayList<>();
				docs.add(channel);
				addBeans(docs);
			}
		}
	}
	
	public ChannelDTO getChannelBean(String channelId){
		ChannelDTO channel = null;
		
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(ChannelDTO.ID + ":\""+ channelId + "\"");
		solrQuery.set("wt", "json");
		try {
			QueryResponse result = solr.query(solrQuery);
			if (result.getBeans(ChannelDTO.class).size() > 0){
				// should have only one anyway as ids are unique
				channel = result.getBeans(ChannelDTO.class).get(0);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return channel;
	}
	
	public String getChannelAsJsonString(String channelId){
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(ChannelDTO.ID + ":\""+ channelId + "\"");
		solrQuery.set("wt", "json");
		
		System.out.println("------ ChannelPojo" + getQueryUrl(solrQuery));

		try {
			return JSONRestUtil.getResults(getQueryUrl(solrQuery)).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "Couldn't get anything back from solr.";
	}
	
	public String getChannels(String imageId){
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(ChannelDTO.ASSOCIATED_IMAGE + ":\""+ imageId + "\"");
		solrQuery.set("wt", "json");
		
		try {
			return JSONRestUtil.getResults(getQueryUrl(solrQuery)).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "getChannels : Couldn't get anything back from solr.";
	}
	

	public void addBeans(List<ChannelDTO> docs) {

		try {
			solr.addBeans(docs);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getQueryUrl(SolrQuery q){
		return solr.getBaseURL() + "/select?" + q.toString();
	}
	

	/**
	 * Removes all documents from the core
	 * 
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public void clear()
	throws SolrServerException, IOException {

		solr.deleteByQuery("*:*");
	}

}

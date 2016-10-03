/*******************************************************************************
 * Copyright 2015 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 *******************************************************************************/
package uk.ac.ebi.phis.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import uk.ac.ebi.phis.solrj.dto.ChannelDTO;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;
import uk.ac.ebi.phis.utils.web.JSONRestUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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
				addBean(channel);
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
				addBean(channel);
			}
		}
	}
	
	public ChannelDTO getChannelBean(String channelId){
		ChannelDTO channel = null;
		
		SolrQuery solrQuery = new SolrQuery();
		channelId = handleSpecialCharacters(channelId);
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
	
	public String getChannelAsJsonString(String channelId, Integer resNo){
		SolrQuery solrQuery = new SolrQuery();
		channelId = handleSpecialCharacters(channelId);
		solrQuery.setQuery(ChannelDTO.ID + ":\""+ channelId + "\"");
		if (resNo != null){
			solrQuery.setRows(resNo);
		} else {
			solrQuery.setRows(10000);
		}
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
	
	public String getChannels(String imageId, Integer resNo){
		
		SolrQuery solrQuery = new SolrQuery().setQuery("*:*");
		
		if (imageId != null){
			imageId = handleSpecialCharacters(imageId);
			solrQuery.addFilterQuery(ChannelDTO.ASSOCIATED_IMAGE + ":\""+ imageId + "\"");
		} 		
		if (resNo != null){
			solrQuery.setRows(resNo);
		} else {
			solrQuery.setRows(10);
		}
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

	public void addBean(ChannelDTO doc) {

		try {
			solr.addBean(doc, 30000);
		} catch (SolrServerException | IOException e) {
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
		solr.commit();
	}
	
	
	/**
	 * @since 2015/08/18
	 * @return
	 * @throws SolrServerException
	 */
	public List<String> getGeneIds () 
	throws SolrServerException{
		
		List<String> geneIds = new ArrayList<>();
		
		SolrQuery q = new SolrQuery()
		.setQuery("*:*")
		.setRows(0)
		.setFacet(true)
		.addFacetField(ChannelDTO.GENE_ID)
		.setFacetMinCount(1)
		.setFacetLimit(-1);		
		
		QueryResponse res = solr.query(q);
		if (res.getFacetField(ChannelDTO.GENE_ID).getValues() != null){
			for (Count count : res.getFacetField(ChannelDTO.GENE_ID).getValues()){
				geneIds.add(count.getName());
			}
		}
		
		return geneIds;
	}
	
	

}

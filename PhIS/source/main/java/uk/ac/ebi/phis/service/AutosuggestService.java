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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.Group;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import uk.ac.ebi.phis.solrj.dto.AutosuggestDTO;
import uk.ac.ebi.phis.solrj.dto.AutosuggestTypes;
import uk.ac.ebi.phis.solrj.dto.ImageDTO;

@Service
public class AutosuggestService extends BasicService {

	private static final Logger log = Logger.getLogger(ImageService.class);

	public AutosuggestService(String solrUrl) {
		super(solrUrl);
	}


	public String getAutosuggest(String term, AutosuggestTypes type, String stage, String imagingMethod, String taxon, String sampleType, String imageGeneratedBy, Integer rows) {

		SolrQuery solrQuery = buildAutosuggestQuery(term, type, stage, imagingMethod, taxon, sampleType, imageGeneratedBy, rows);
		ArrayList<String> suggestions = new ArrayList<>();

		solrQuery.set("group", true);
		solrQuery.set("group.field", "term_autosuggest_na");
		
		System.out.println("Solr URL : " + solr.getBaseURL() + "/select?" + solrQuery);
		log.info("Solr URL in getImages : " + solr.getBaseURL() + "/select?" + solrQuery);		
		
		try {

			List<Group> groups = solr.query(solrQuery).getGroupResponse().getValues().get(0).getValues();
			for (Group group : groups) {
				suggestions.add(group.getGroupValue());
			}

		} catch (SolrServerException e) {
			e.printStackTrace();
		}	
		
		JSONObject returnObj = new JSONObject();
		returnObj.put("response", new JSONObject().put("suggestions", new JSONArray(suggestions)));
		return returnObj.toString().replaceAll("<\\\\", "<");
	}

	
	public String getComplexAutosuggest(String term, AutosuggestTypes type, String stage, String imagingMethod, String taxon, String sampleType, String imageGeneratedBy, Integer rows) {

		SolrQuery solrQuery = buildAutosuggestQuery(term, type, stage, imagingMethod, taxon, sampleType, imageGeneratedBy, rows);
		solrQuery.setFields(AutosuggestDTO.AUTOSUGGEST_TERM_ID, AutosuggestDTO.AUTOSUGGEST_TERM_LABEL, AutosuggestDTO.AUTOSUGGEST_TYPE, AutosuggestDTO.AUTOSUGGEST_TERM_SYNONYMS);		
		
		System.out.println("Solr URL : " + solr.getBaseURL() + "/select?" + solrQuery);
		log.info("Solr URL in getImages : " + solr.getBaseURL() + "/select?" + solrQuery);		
		
		try {

			JSONObject returnObj = new JSONObject();
			returnObj.put("response", new JSONObject().put("suggestions", 
					solr.query(solrQuery).getResults()));
			return returnObj.toString().replaceAll("<\\\\", "<");
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		}	
		 return "";
	}
	
	
	private SolrQuery buildAutosuggestQuery( String term, AutosuggestTypes type, String stage, String imagingMethod, String taxon, 
			String sampleType, String imageGeneratedBy, Integer rows) {

		SolrQuery solrQuery = new SolrQuery();
			
		term = handleSpecialCharacters(term);
		imagingMethod = handleSpecialCharacters(imagingMethod);
		taxon = handleSpecialCharacters(taxon);
		sampleType = handleSpecialCharacters(sampleType);
		imageGeneratedBy = handleSpecialCharacters(imageGeneratedBy);		
			
		solrQuery.setQuery(term);
		solrQuery.setFields(ImageDTO.TERM_AUTOSUGGEST);
		solrQuery.set("defType", "edismax");
		solrQuery.set("qf", "term_autosuggest term_autosuggest_ws term_autosuggest_e term_autosuggest_na");
		solrQuery.set("bq", "term_autosuggest_ws:\"" + term +
				"\"^2 term_autosuggest_e:\"" + term + "\"^3 term_autosuggest_na:\"" + term + "\"^4 term_autosuggest:\"" + term + "\"^1");
		solrQuery.setRows(rows); // number of groups to return (not result documents)

		if (type != null) {
			solrQuery.addFilterQuery(AutosuggestDTO.AUTOSUGGEST_TYPE + ":" + type);
		} 			
		if (stage != null){
			solrQuery.addFilterQuery(AutosuggestDTO.STAGE + ":\"" + stage + "\"");
		}
		if (taxon != null){
			solrQuery.addFilterQuery(AutosuggestDTO.TAXON + ":\"" + taxon + "\"");
		}
		if (sampleType != null){
			solrQuery.addFilterQuery(AutosuggestDTO.SAMPLE_TYPE + ":\"" + sampleType + "\"");
		}
		if (imagingMethod != null){
			solrQuery.addFilterQuery(AutosuggestDTO.IMAGING_METHOD + ":\"" + imagingMethod + "\"");
		}
		if (imageGeneratedBy != null){
			solrQuery.addFilterQuery(AutosuggestDTO.IMAGE_GENERATED_BY + ":\"" + imageGeneratedBy + "\"");
		}		
			
		return solrQuery;
	}
	

	public void addBean(AutosuggestDTO doc){
		try {
			solr.addBean(doc);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addBeans(Collection<AutosuggestDTO> docs){
		try {
			solr.addBeans(docs);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
	

	public void clear() throws SolrServerException, IOException{
		System.out.println("DELETING INDEX " + solr.getBaseURL());
		solr.deleteByQuery("*:*");
		solr.commit();
	}
}

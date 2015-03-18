package uk.ac.ebi.phis.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.QueryResponse;
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

		SolrQuery solrQuery = new SolrQuery();
		ArrayList<String> suggestions = new ArrayList<>();

		solrQuery.setQuery(term);
		solrQuery.setFields(ImageDTO.TERM_AUTOSUGGEST);
		solrQuery.set("defType", "edismax");
		solrQuery.set("group", true);
		solrQuery.set("group.field", "term_autosuggest_na");
		solrQuery.set("qf", "term_autosuggest term_autosuggest_ws term_autosuggest_e term_autosuggest_na");
		solrQuery.set("bq", "term_autosuggest_ws:\"" + term +
			"\"^2 term_autosuggest_e:\"" + term + "\"^3 term_autosuggest_na:\"" + term + "\"^4 term_autosuggest:\"" + term + "\"^1");
		solrQuery.setRows(rows); // number of groups to return (not result
									// documents)

		System.out.println("Solr URL : " + solr.getBaseURL() + "/select?" + solrQuery);
		log.info("Solr URL in getImages : " + solr.getBaseURL() + "/select?" + solrQuery);
		
		if (type != null) {
			solrQuery.addFilterQuery(AutosuggestDTO.AUTOSUGGEST_TYPE + ":" + type);
		} 
		
		if (stage != null){
			solrQuery.addFilterQuery(AutosuggestDTO.STAGE + ":" + stage);
		}
		if (taxon != null){
			solrQuery.addFilterQuery(AutosuggestDTO.TAXON + ":" + taxon);
		}
		if (sampleType != null){
			solrQuery.addFilterQuery(AutosuggestDTO.SAMPLE_TYPE + ":" + sampleType);
		}
		if (imagingMethod != null){
			solrQuery.addFilterQuery(AutosuggestDTO.IMAGING_METHOD + ":" + imagingMethod);
		}
		if (imageGeneratedBy != null){
			solrQuery.addFilterQuery(AutosuggestDTO.IMAGE_GENERATED_BY + ":" + imageGeneratedBy);
		}		
		
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
		solr.deleteByQuery("*:*");
	}
}

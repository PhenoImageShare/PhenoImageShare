package uk.ac.ebi.phis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.Group;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import uk.ac.ebi.phis.solrj.dto.ImageDTO;

@Service
public class AutosuggestService {

	private HttpSolrServer solr;

	private static final Logger log = Logger.getLogger(ImageService.class);

	public AutosuggestService(String solrUrl) {
		solr = new HttpSolrServer(solrUrl);
	}
	
	
	public String getAutosuggest(String term, Integer rows){

		SolrQuery solrQuery = new SolrQuery();
		ArrayList<String> suggestions = new ArrayList<>();
		
		if (term != null){
			if (term.length() < 1){
				return "";
			}
			solrQuery.setQuery(term);
			solrQuery.setFields(ImageDTO.TERM_AUTOSUGGEST);
			solrQuery.set("defType", "edismax");
			solrQuery.set("group", true);
			solrQuery.set("group.field", "term_autosuggest_na");
			solrQuery.set("qf", "term_autosuggest term_autosuggest_ws term_autosuggest_e term_autosuggest_na");
			solrQuery.set("bq", "term_autosuggest_ws:" + term + 
				"^2 term_autosuggest_e:" + term + "^3 term_autosuggest_na:ey^4 term_autosuggest_ws:ey^1");
			solrQuery.setRows(rows); // number of groups to return (not result documents)
		
			// qf=term_autosuggest%20term_autosuggest_ws%20term_autosuggest_e%20term_autosuggest_na
			// &defType=edismax&q=ey&
			// bq=term_autosuggest_ws:ey^2%20term_autosuggest_e:ey^3%20term_autosuggest_na:ey^4%20term_autosuggest_ws:ey^1&
			// debugQuery=true&group=true&group.field=term_autosuggest_na&fl=term_autosuggest,term_autosuggest_ws&rows=10000
	
			System.out.println("Solr URL : " + solr.getBaseURL() + "/select?" + solrQuery);
			log.info("Solr URL in getImages : " + solr.getBaseURL() + "/select?" + solrQuery);
			
			try {
				
				List<Group> groups = solr.query(solrQuery).getGroupResponse().getValues().get(0).getValues();
				for (Group group : groups){
					suggestions.add(group.getGroupValue());
				}
				
			} catch (SolrServerException e) {
				e.printStackTrace();
			}
		}

		JSONObject returnObj = new JSONObject();
		returnObj.put("response", new JSONObject().put("suggestions", new JSONArray(suggestions)));
		return returnObj.toString().replaceAll("<\\\\", "<");
	}
}

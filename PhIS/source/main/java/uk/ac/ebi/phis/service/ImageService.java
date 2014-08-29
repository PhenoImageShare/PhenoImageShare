package uk.ac.ebi.phis.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import uk.ac.ebi.phis.solrj.dto.ImageDTO;
import uk.ac.ebi.phis.utils.web.JSONRestUtil;

@Service
public class ImageService {

	private HttpSolrServer solr;

	private static final Logger log = Logger.getLogger(ImageService.class);

	public ImageService(String solrUrl) {
		solr = new HttpSolrServer(solrUrl);

	}
	
	public String getAutosuggest(String term, String mutantGene, String expressedGeneOrAllele, String phenotype, Integer rows){

		SolrQuery solrQuery = new SolrQuery();
		ArrayList<String> suggestions = new ArrayList<>();
		Integer suggestionNumber = 10;
		Integer solrRows = 100;
		
		if (term != null){
			if (term.length() < 1)
				return "";
			// Build the query
			solrQuery.setQuery(ImageDTO.TERM_AUTOSUGGEST + ":" + term);
			solrQuery.setFields(ImageDTO.TERM_AUTOSUGGEST);
			solrQuery.addHighlightField(ImageDTO.TERM_AUTOSUGGEST);
			solrQuery.setHighlight(true);
			solrQuery.setHighlightRequireFieldMatch(true);
			solrQuery.set("f.term_autosuggest_analysed.hl.preserveMulti", true);
			solrQuery.set("hl.simple.pre", "<b>");
			solrQuery.set("hl.simple.post", "</b>");
		}
		else if (mutantGene != null){
			if (mutantGene.length() < 1)
				return "";
			// Build the query
			solrQuery.setQuery(ImageDTO.GENE_SYMBOL + ":" + mutantGene);
			solrQuery.setFields(ImageDTO.GENE_SYMBOL);
			solrQuery.addHighlightField(ImageDTO.GENE_SYMBOL);
			solrQuery.setHighlight(true);
			solrQuery.setHighlightRequireFieldMatch(true);
			solrQuery.set("f." + ImageDTO.GENE_SYMBOL + ".hl.preserveMulti", true);
			solrQuery.set("hl.simple.pre", "<b>");
			solrQuery.set("hl.simple.post", "</b>");
		}
		else if (expressedGeneOrAllele != null){
			if (expressedGeneOrAllele.length() < 1)
				return "";
			// Build the query
			solrQuery.setQuery(ImageDTO.EXPRESSED_GF_SYMBOL_BAG + ":" + expressedGeneOrAllele);
			solrQuery.setFields(ImageDTO.EXPRESSED_GF_SYMBOL_BAG);
			solrQuery.addHighlightField(ImageDTO.EXPRESSED_GF_SYMBOL_BAG);
			solrQuery.setHighlight(true);
			solrQuery.setHighlightRequireFieldMatch(true);
			solrQuery.set("f." + ImageDTO.EXPRESSED_GF_SYMBOL_BAG + ".hl.preserveMulti", true);
			solrQuery.set("hl.simple.pre", "<b>");
			solrQuery.set("hl.simple.post", "</b>");
		}
		else if (phenotype != null){
			System.out.println("PHENOTYPE!!");
			if (phenotype.length() < 1)
				return "";
			// Build the query
			solrQuery.setQuery(ImageDTO.PHENOTYPE_LABEL_BAG + ":" + phenotype);
			solrQuery.setFields(ImageDTO.PHENOTYPE_LABEL_BAG);
			solrQuery.addHighlightField(ImageDTO.PHENOTYPE_LABEL_BAG);
			solrQuery.setHighlight(true);
			solrQuery.setHighlightRequireFieldMatch(true);
			solrQuery.set("f." + ImageDTO.PHENOTYPE_LABEL_BAG + ".hl.preserveMulti", true);
			solrQuery.set("hl.simple.pre", "<b>");
			solrQuery.set("hl.simple.post", "</b>");
		}
		
		if (rows != null){
			solrRows = rows*10;
			suggestionNumber = rows;
		}
		solrQuery.setRows(solrRows);
		
		System.out.println("Solr URL : " + solr.getBaseURL() + "/select?" + solrQuery);
		log.info("Solr URL in getImages : " + solr.getBaseURL() + "/select?" + solrQuery);
		
		// Parse results to filter out un-highlighted entries and duplicates
		try {
			int i = 1;
			while ((suggestions.size() < suggestionNumber && solr.query(solrQuery).getResults().getNumFound() > i*solrRows) || i == 1){
				Map<String, Map<String, List<String>>> highlights = solr.query(solrQuery).getHighlighting();
				OUTERMOST: for (Map<String, List<String>> suggests : highlights.values()){
					for (List<String> suggestLists : suggests.values()){
						for(String highlighted : suggestLists){
							if (highlighted.contains("<b>") && !suggestions.contains(highlighted)){
								System.out.println(highlighted + "  " + highlighted.replaceAll("<\\\\", "<"));
								suggestions.add(highlighted);
								if (suggestions.size() == suggestionNumber){
									break OUTERMOST;
								}
							}
						}
					}
				}
				solrQuery.setStart(i++*solrRows);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
		// Return in JSON format
		JSONObject returnObj = new JSONObject();
		returnObj.put("response", new JSONObject().put("suggestions", new JSONArray(suggestions)));
		return returnObj.toString().replaceAll("<\\\\", "<");
	}
		
	public String getImage(String term, String phenotype, String geneParameterToBeDeleted, String mutantGene, String anatomy, String expressedGene, String sex, String taxon, 
	String stage, String visualisationMethod, String samplePreparation, String imagingMethod, Integer rows, Integer start) throws SolrServerException{

		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		
		if ( geneParameterToBeDeleted != null){
			mutantGene = geneParameterToBeDeleted;
		}
		
		if (term != null){
			solrQuery.setFilterQueries(ImageDTO.PHENOTYPE_ID_BAG + ":\""+ term + "\" OR " + 
				ImageDTO.PHENOTYPE_FREETEXT_BAG + ":\""+ term + "\" OR " + 
				ImageDTO.ANATOMY_COMPUTED_ID_BAG + ":\""+ term + "\" OR " + 
				ImageDTO.ANATOMY_COMPUTED_TERM_BAG + ":\""+ term + "\" OR " + 
				ImageDTO.ANATOMY_FREETEXT + ":\""+ term + "\" OR " + 
				ImageDTO.ANATOMY_ID + ":\""+ term + "\" OR " + 
				ImageDTO.ANATOMY_TERM + ":\""+ term + "\" OR " + 
				ImageDTO.GENE_ID + ":\""+ term + "\" OR " + 
				ImageDTO.GENE_SYMBOL + ":\""+ term + "\" OR " + 
				ImageDTO.EXPRESSED_GF_ID_BAG + ":\""+ term + "\" OR " + 
				ImageDTO.EXPRESSED_GF_SYMBOL_BAG + ":\""+ term + "\" OR " + 
				ImageDTO.EXPRESSION_IN_FREETEXT_BAG + ":\""+ term + "\" OR " + 
				ImageDTO.EXPRESSION_IN_ID_BAG + ":\""+ term + "\" OR " + 
				ImageDTO.EXPRESSION_IN_LABEL_BAG + ":\""+ term + "\" OR " + 
				ImageDTO.IMAGE_GENERATED_BY + ":\""+ term + "\" OR " + 
				ImageDTO.IMAGING_METHOD_ID + ":\""+ term + "\" OR " + 
				ImageDTO.IMAGING_METHOD_LABEL_ANALYSED + ":\""+ term + "\" OR " + 
				ImageDTO.VISUALISATION_METHOD_ID + ":\""+ term + "\" OR " + 
				ImageDTO.VISUALISATION_METHOD_LABEL + ":\""+ term + "\" OR " + 
				ImageDTO.SAMPLE_GENERATED_BY + ":\""+ term + "\" OR " + 
				ImageDTO.SAMPLE_PREPARATION_ID + ":\""+ term + "\" OR " + 
				ImageDTO.SAMPLE_PREPARATION_LABEL + ":\""+ term + "\" OR " + 
				ImageDTO.PHENOTYPE_LABEL_BAG + ":\""+ term + "\"");
		}
		
		if (phenotype != null){
			solrQuery.setFilterQueries(ImageDTO.PHENOTYPE_ID_BAG + ":\""+ phenotype + "\" OR " + 
				ImageDTO.PHENOTYPE_FREETEXT_BAG + ":\""+ phenotype + "\" OR " + 
				ImageDTO.PHENOTYPE_LABEL_BAG + ":\""+ phenotype + "\"");
		}
		
		if (mutantGene != null){
			solrQuery.setFilterQueries(ImageDTO.GENE_ID + ":\""+ mutantGene + "\" OR " +
				ImageDTO.GENE_SYMBOL + ":\""+ mutantGene + "\"");		
		}
		
		if (anatomy != null){
			solrQuery.setFilterQueries(ImageDTO.ANATOMY_ID + ":\""+ anatomy + "\" OR " + 
				ImageDTO.ANATOMY_TERM + ":\""+ anatomy + "\" OR " + 
				ImageDTO.ANATOMY_FREETEXT + ":\""+ anatomy + "\"" );
		}
		if (expressedGene != null){
			solrQuery.setFilterQueries(ImageDTO.EXPRESSED_GF_ID_BAG + ":\"" + expressedGene + "\"");
		}
		if (taxon != null){
			solrQuery.setFilterQueries(ImageDTO.TAXON + ":\"" + taxon + "\" OR " + 
				ImageDTO.NCBI_TAXON_ID + ":\"" + taxon + "\"");
		}
		if (stage != null){
			solrQuery.setFilterQueries(ImageDTO.STAGE + ":\"" + stage + "\" OR " + 
				ImageDTO.STAGE_ID + ":\"" + stage + "\"");
		}
		if (visualisationMethod != null){
			solrQuery.setFilterQueries(ImageDTO.VISUALISATION_METHOD_ID + ":\"" + visualisationMethod + "\" OR " + 
				ImageDTO.VISUALISATION_METHOD_LABEL + ":\"" + visualisationMethod + "\"");
		}
		if (samplePreparation != null){
			solrQuery.setFilterQueries(ImageDTO.SAMPLE_PREPARATION_ID + ":\"" + samplePreparation + "\" OR " + 
				ImageDTO.SAMPLE_PREPARATION_LABEL + ":\"" + samplePreparation + "\"");
		}
		if (samplePreparation != null){
			solrQuery.setFilterQueries(ImageDTO.IMAGING_METHOD_LABEL_ANALYSED + ":\"" + imagingMethod + "\" OR " + 
			ImageDTO.IMAGING_METHOD_ID + ":\"" + imagingMethod + "\"");
		}
		if (sex != null){
			solrQuery.setFilterQueries(ImageDTO.SEX + ":\"" + sex + "\"");
		}
		
		if (rows != null){
			solrQuery.setRows(rows);
		}
		else solrQuery.setRows(100);
		
		if (start != null){
			solrQuery.set("start", start);
		}
		solrQuery.set("wt", "json");
		solrQuery.setFacet(true);
		solrQuery.addFacetField(ImageDTO.STAGE);
		solrQuery.addFacetField(ImageDTO.IMAGING_METHOD_LABEL);
		solrQuery.addFacetField(ImageDTO.TAXON);
		solrQuery.addFacetField(ImageDTO.SAMPLE_TYPE);
		solrQuery.setFacetMinCount(1);
		
		// add pivot facets to get the number of image types per 
		solrQuery.set("facet.pivot", ImageDTO.SAMPLE_TYPE + "," + ImageDTO.IMAGE_TYPE);	
		
		System.out.println("Solr URL : " + solr.getBaseURL() + "/select?" + solrQuery);
		log.info("Solr URL in getImages : " + solr.getBaseURL() + "/select?" + solrQuery);
		
		
		
		try {
			return JSONRestUtil.getResults(getQueryUrl(solrQuery)).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "Couldn't get anything back from solr.";
	}
	
	public String getSolrUrl () {
		return solr.getBaseURL();
	}
	
	public void addBeans(List<ImageDTO> imageDocs){
		try {
			solr.addBeans(imageDocs);
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
	

	public String getQueryUrl(SolrQuery q){
		return solr.getBaseURL() + "/select?" + q.toString();
	}
	
}

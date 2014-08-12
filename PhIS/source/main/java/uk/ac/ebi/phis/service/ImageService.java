package uk.ac.ebi.phis.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import uk.ac.ebi.phis.solrj.dto.ImageDTO;
import uk.ac.ebi.phis.utils.web.JSONRestUtil;

@Service
public class ImageService {

	private HttpSolrServer solr;

	

	public ImageService(String solrUrl) {
		solr = new HttpSolrServer(solrUrl);

	}
	
	public String getImage(String phenotype, String mutantGene, String anatomy, String expressedGene, String sex, String taxon, 
	String stage, String visualisationMethod, String samplePreparation, String imagingMethod, Integer rows, Integer start) throws SolrServerException{

		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
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
			solrQuery.setFilterQueries(ImageDTO.IMAGING_METHOD_LABEL + ":\"" + imagingMethod + "\" OR " + 
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
			
		System.out.println("Solr URL : " + solr.getBaseURL() + "/select?" + solrQuery);
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

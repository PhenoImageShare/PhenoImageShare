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

import uk.ac.ebi.phis.solrj.dto.ImagePojo;
import uk.ac.ebi.phis.utils.web.JSONRestUtil;

@Service
public class ImageService {

	private HttpSolrServer solr;

	

	public ImageService(String solrUrl) {
		solr = new HttpSolrServer(solrUrl);

	}
	
	public String getImage(String phenotype, String mutantGene, String anatomy, String expressedGene, Integer rows, Integer start) throws SolrServerException{

		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		if (phenotype != null){
			solrQuery.setFilterQueries(ImagePojo.ImageField.PHENOTYPE_ID_BAG + ":\""+ phenotype + "\" OR " + 
				ImagePojo.ImageField.PHENOTYPE_FREETEXT_BAG + ":\""+ phenotype + "\" OR " + 
				ImagePojo.ImageField.PHENOTYPE_LABEL_BAG + ":\""+ phenotype + "\"");
		}
		
		if (mutantGene != null){
			solrQuery.setFilterQueries(ImagePojo.ImageField.GENE_ID + ":\""+ mutantGene + "\" OR " +
				ImagePojo.ImageField.GENE_SYMBOL + ":\""+ mutantGene + "\"");		
		}
		
		if (anatomy != null){
			solrQuery.setFilterQueries(ImagePojo.ImageField.ANATOMY_ID + ":\""+ anatomy + "\" OR " + 
				ImagePojo.ImageField.ANATOMY_TERM + ":\""+ anatomy + "\" OR " + 
				ImagePojo.ImageField.ANATOMY_FREETEXT + ":\""+ anatomy + "\"" );
		}
		if (expressedGene != null){
			solrQuery.setFilterQueries(ImagePojo.ImageField.EXPRESSED_GF_ID_BAG + ":\"" + expressedGene + "\"");
		}
		
		if (rows != null){
			solrQuery.setRows(rows);
		}
		else solrQuery.setRows(100);
		
		if (start != null){
			solrQuery.set("start", start);
		}
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
	
	public String getSolrUrl () {
		return solr.getBaseURL();
	}
	
	public void addBeans(List<ImagePojo> imageDocs){
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

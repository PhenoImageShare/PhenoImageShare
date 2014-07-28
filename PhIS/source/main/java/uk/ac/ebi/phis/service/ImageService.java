package uk.ac.ebi.phis.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import uk.ac.ebi.phis.solrj.dto.ImagePojo;

@Service
public class ImageService {

	private HttpSolrServer solr;

	

	public ImageService(String solrUrl) {
		solr = new HttpSolrServer(solrUrl);

	}
	
	public SolrDocumentList getImageByPhenotypeGeneAnatomy(String phenotype, String mutantGene, String anatomy, Integer rows) throws SolrServerException{

		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		if (phenotype != null){
			solrQuery.setFilterQueries(ImagePojo.ImageField.PHENOTYPE_ID_BAG + ":\""+ phenotype + "\"");
		}
		if (mutantGene != null){
			solrQuery.setFilterQueries(ImagePojo.ImageField.GENE_ID + ":\""+ mutantGene + "\"");		
		}
		if (anatomy != null){
			solrQuery.setFilterQueries(ImagePojo.ImageField.ANATOMY_ID + ":\""+ anatomy + "\"");
		}
		if (rows != null)
			solrQuery.setRows(rows);
		else solrQuery.setRows(10);
//		solrQuery.setFields(GeneField.MGI_ACCESSION_ID);
		System.out.println("-----------------------" + solr.getBaseURL() + "/select?" + solrQuery);
		QueryResponse rsp = null;
		rsp = solr.query(solrQuery);
		SolrDocumentList res = rsp.getResults();
		return res;
		
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
	
}

package uk.ac.ebi.phis.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	private HttpSolrServer solr;
	

	public static final class ImageField {
		public final static String PHENOTYPE_ANN_BAG = "phenotype_ann_bag";
	}
	

	public ImageService(String solrUrl) {
		solr = new HttpSolrServer(solrUrl);

	}
	
	public String getImageByPhenotype(String phenotype) throws SolrServerException{

		SolrQuery solrQuery = new SolrQuery();
		String queryString = ImageField.PHENOTYPE_ANN_BAG + ":\""+ phenotype + "\"";
		solrQuery.setQuery(queryString);
		solrQuery.setRows(1000000);
//		solrQuery.setFields(GeneField.MGI_ACCESSION_ID);
		System.out.println("-----------------------" + solr.getBaseURL() + "/select?" + solrQuery);
		QueryResponse rsp = null;
		rsp = solr.query(solrQuery);
		SolrDocumentList res = rsp.getResults();
		return solr.getBaseURL() + "/select?" + solrQuery;
	}
}

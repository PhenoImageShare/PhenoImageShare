package uk.ac.ebi.phis.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;


public class BasicService {


	protected HttpSolrServer solr;
	

	public BasicService(String solrUrl) {
		solr = new HttpSolrServer(solrUrl);
	}
	
	public QueryResponse getDocuments(int rows, int start) 
	throws SolrServerException{

		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setRows(rows);
		solrQuery.setStart(start);
		return solr.query(solrQuery);		
	}
	
	public QueryResponse getDocumentsById(String field, String fieldValue) 
	throws SolrServerException{

		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(field + ":\"" + fieldValue +"\"");
		return solr.query(solrQuery);		
	}
	
}

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
	
	public String handleSpecialCharacters(String q){

		if (q != null){

			String query = new String(q);
			query = query.replace("\\", "\\\\");
			query = query.replace("%5C", "\\\\");
			
			query = query.replace("[", "\\[");
			query = query.replace("]", "\\]");
			query = query.replace("{", "\\{");
			query = query.replace("}", "\\}");
			query = query.replace("|", "\\|");
			query = query.replace("<", "\\<");
			query = query.replace(">", "\\>");
			query = query.replace("."  , "\\.");
			query = query.replace("("  , "\\(");
			query = query.replace(")"  , "\\)");
			query = query.replace("/", "\\/");
			query = query.replace("`", "\\`");
			query = query.replace("~"  , "\\~");
			query = query.replace("%"  , "\\%");
			query = query.replace(" "  , "\\ ");
	
			query = query.replace("%7B", "\\{");
			query = query.replace("%7D", "\\}");
			query = query.replace("%7C", "\\|");
			query = query.replace("%3C", "\\<");
			query = query.replace("%3E", "\\>");
			query = query.replace("%2F", "\\/");
			query = query.replace("%60", "\\`");

			return query;
		}
	
		return null;

	}
	
}

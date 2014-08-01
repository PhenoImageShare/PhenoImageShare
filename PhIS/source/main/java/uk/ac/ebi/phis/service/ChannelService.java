package uk.ac.ebi.phis.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import uk.ac.ebi.phis.solrj.dto.ChannelPojo;
import uk.ac.ebi.phis.solrj.dto.ImagePojo;

public class ChannelService {

	private HttpSolrServer solr;


	public ChannelService(String solrUrl) {

		solr = new HttpSolrServer(solrUrl);
	}

	
	public ChannelPojo getChannel(String channelId){
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setFilterQueries(ChannelPojo.ID + ":\""+ channelId + "\"");
		System.out.println("------ ChannelPojo" + solr.getBaseURL() + "/select?" + solrQuery);
		QueryResponse rsp = null;
		try {
			rsp = solr.query(solrQuery);
			List<ChannelPojo> res = rsp.getBeans(ChannelPojo.class);
			if (res.size() > 0)
				return res.get(0);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public void addBeans(List<ChannelPojo> docs) {

		try {
			solr.addBeans(docs);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Removes all documents from the core
	 * 
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public void clear()
	throws SolrServerException, IOException {

		solr.deleteByQuery("*:*");
	}

}

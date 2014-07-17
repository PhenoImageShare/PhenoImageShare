package uk.ac.ebi.phis.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

import uk.ac.ebi.phis.solrj.pojo.ChannelPojo;

public class ChannelService {

	public static final class ChannelField {

		public final static String ID = "id";
		public final static String ASSOCIATED_ROI = "associated_roi";
		public final static String ASSOCIATED_IMAGE = "associated_image";
		public final static String GENE_ID = "gene_id";
		public final static String GENE_SYMBOL = "gene_symbol";
		public final static String GENETIC_FEATURE_ID = "genetic_feature_id";
		public final static String GENETIC_FEATURE_SYMBOL = "genetic_feature_symbol";
		public final static String GENETIC_FEATURE_ENSEML_ID = "genetic_feature_ensembl_id";
		public final static String CHROMOSOME = "chromosome";
		public final static String START_POS = "start_pos";
		public final static String END_POS = "end_pos";
		public final static String STRAND = "strand";
		public final static String ZYGOSITY = "zygosity";
		public final static String MARKER = "marker";

	}

	private HttpSolrServer solr;

	public ChannelService(String solrUrl) {

		solr = new HttpSolrServer(solrUrl);
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

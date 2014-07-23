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
	

	public static final class ImageField {
		public final static String ANATOMY_ID = "anatomy_id";
		public final static String GENE_ID = "gene_id";
		public final static String ZYGOSITY = "zygosity";
		public final static String DEPTH = "depth";
		public final static String STRAND = "strand";
		public final static String END_POS = "end_pos";
		public final static String START_POS = "start_pos";
		public final static String INSERTCHROMOSOME = "chromosome";
		public final static String GF_ENEMBL_ID = "genetic_feature_ensembl_id";
		public final static String GF_SYMBOL = "genetic_feature_symbol";
		public final static String GF_ID = "genetic_feature_id";
		public final static String GENE_SYMBOL = "gene_symbol";
		public final static String CONDITIONS = "conditions";
		public final static String OBSERVATIONS = "observations";
		public final static String ANATOMY_FREETEXT = "anatomy_freetext";
		public final static String ANATOMY_TERM = "anatomy_term";
		public final static String EMBRYONIC_AGE = "embryonic_age";
		public final static String STAGE_ID = "stage_id";
		public final static String STAGE = "stage";
		public final static String SEX = "sex";
		public final static String NCBI_TAXON_ID = "ncbi_taxon_id";
		public final static String TAXON = "taxon";
		public final static String SAMPLE_GENERATED_BY = "sample_generated_by";
		public final static String AGE_SINCE_BIRTH = "age_since_birth";
		public final static String WIDTH = "width";
		public final static String THUMBNAIL_PATH = "thumbnail_path";
		public final static String ORIGINAL_IMAGE_ID = "original_image_id";
		public final static String MACHINE = "machine";
		public final static String VISUALISATION_METHOD_LABEL = "visualisation_method_label";
		public final static String VISUALISATION_METHOD_ID = "visualisation_method_id";
		public final static String SAMPLE_PREPARATION_LABEL = "sample_preparation_label";
		public final static String SAMPLE_PREPARATION_ID = "sample_preparation_id";
		public final static String IMAGING_METHOD_ID = "imaging_method_id";
		public final static String IMAGING_METHOD_LABEL = "imaging_method_label";
		public final static String IMAGE_CONTEXT_URL = "image_context_url";
		public final static String IMAGE_URL = "image_url";
		public final static String IMAGE_GENERATED_BY = "image_generated_by";
		public final static String HOST_NAME = "host_name";
		public final static String HOST_URL = "host_url";
		public final static String HEIGHT = "height";
		public final static String ASSOCIATED_CHANNEL = "associated_channel";
		public final static String ASSOCIATED_ROI = "associated_roi";
		public final static String ID = "id";
		
		public final static String ANATOMY_COMPUTED_ID_BAG = "anatomy_computed_id_bag";
		public final static String ANATOMY_COMPUTED_TERM_BAG = "anatomy_computed_term_bag";
		public final static String DEPICTED_ANATOMY_ID_BAG = "depicted_anatomy_id_bag";
		public final static String DEPICTED_ANATOMY_TERM_BAG = "depicted_anatomy_term_bag";
		public final static String DEPICTED_ANATOMY_FREETEXT_BAG = "depicted_anatomy_freetext_bag";
		public final static String ABNORMAL_ANATOMY_ID_BAG = "abnornal_anatomy_id_bag";
		public final static String ABNORMAL_ANATOMY_TERM_BAG = "abnornal_anatomy_term_bag";
		public final static String ABNORMAL_ANATOMY_FREETEXT_BAG = "abnornal_anatomy_freetext_bag";
		public final static String EXPRESSION_IN_ID_BAG = "expression_in_id_bag";
		public final static String EXPRESSION_IN_LABEL_BAG = "expression_in_label_bag";
		public final static String EXPRESSION_IN_FREETEXT_BAG = "expression_in_freetext_bag";
		public final static String OBSERVATION_BAG = "observation_bag";
		public final static String MUTANT_GENE_ID_BAG = "mutant_gene_id_bag";
		public final static String MUTANT_GENE_SYMBOL_BAG = "mutant_gene_symbol_bag";
		public final static String EXPRESSED_GF_ID_BAG = "expressed_gf_id_bag";
		public final static String EXPRESSED_GF_SYMBOL_BAG = "expressed_gf_symbol_bag";
		public final static String PHENOTYPE_ID_BAG = "phenotype_id_bag";
		public final static String PHENOTYPE_LABEL_BAG = "phenotype_label_bag";
		public final static String PHENOTYPE_FREETEXT_BAG = "phenotype_freetext_bag";
		
	}
	

	public ImageService(String solrUrl) {
		solr = new HttpSolrServer(solrUrl);

	}
	
	public String getImageByPhenotypeGeneAnatomy(String phenotype, String gene, String anatomy) throws SolrServerException{

		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		if (phenotype != null){
			solrQuery.setFilterQueries(ImageField.PHENOTYPE_ID_BAG + ":\""+ phenotype + "\"");
		}
		if (gene != null){
			solrQuery.setFilterQueries(ImageField.GENE_ID + ":\""+ gene + "\"");		
		}
		if (anatomy != null){
			solrQuery.setFilterQueries(ImageField.ANATOMY_ID + ":\""+ anatomy + "\"");
		}
		solrQuery.setRows(1000000);
//		solrQuery.setFields(GeneField.MGI_ACCESSION_ID);
		System.out.println("-----------------------" + solr.getBaseURL() + "/select?" + solrQuery);
		QueryResponse rsp = null;
		rsp = solr.query(solrQuery);
		SolrDocumentList res = rsp.getResults();
		return solr.getBaseURL() + "/select?" + solrQuery;
		
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

package uk.ac.ebi.phis.solrj.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.solr.client.solrj.beans.Field;

import uk.ac.ebi.phis.service.ImageService;

public class ImageDTO {

	public final static String ANATOMY_ID = "anatomy_id";
	public final static String GENE_ID = "gene_id";
	public final static String CHROMOSOME = "chromosome";
	public final static String ZYGOSITY = "zygosity";
	public final static String DEPTH = "depth";
	public final static String STRAND = "strand";
	public final static String END_POS = "end_pos";
	public final static String START_POS = "start_pos";
	public final static String INSERTCHROMOSOME = "chromosome";
	public final static String GF_ENEMBL_ID = "genetic_feature_ensembl_id";
	public final static String GF_SYMBOL = "genetic_feature_symbol";
	public final static String GF_ID = "genetic_feature_id";
	public final static String GF_SYNONYMS = "genetic_feature_synonyms";
	public final static String GENE_SYMBOL = "gene_symbol";
	public final static String GENE_SYNONYMS = "gene_synonyms";
	public final static String CONDITIONS = "conditions";
	public final static String OBSERVATIONS = "observations";
	public final static String ANATOMY_FREETEXT = "anatomy_freetext";
	public final static String ANATOMY_TERM = "anatomy_term";
	public final static String ANATOMY_SYNONYMS = "anatomy_synonyms";
	public final static String ANATOMY_ANCESTORS_SYNONYMS = "anatomy_ancestors_synonyms";
	public final static String ANATOMY_ANCESTORS_ID = "anatomy_ancestors_id";
	public final static String ANATOMY_ANCESTORS_TERM = "anatomy_ancestors_term";
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
	public final static String IMAGE_TYPE = "image_type";
	public final static String SAMPLE_TYPE = "sample_type";

	public final static String ORIGINAL_IMAGE_ID = "original_image_id";
	public final static String MACHINE = "machine";
	public final static String VISUALISATION_METHOD_LABEL = "visualisation_method_label";
	public final static String VISUALISATION_METHOD_ID = "visualisation_method_id";
	public final static String VISUALISATION_METHOD_SYNONYMS = "visualisation_method_synonyms";
	public final static String SAMPLE_PREPARATION_LABEL = "sample_preparation_label";
	public final static String SAMPLE_PREPARATION_ID = "sample_preparation_id";
	public final static String SAMPLE_PREPARATION_SYNONYMS = "sample_preparation_synonyms";
	public final static String IMAGING_METHOD_ID = "imaging_method_id";
	public final static String IMAGING_METHOD_LABEL = "imaging_method_label";
	public final static String IMAGING_METHOD_LABEL_ANALYSED = "imaging_method_label_analysed";
	public final static String IMAGING_METHOD_SYNONYMS = "imaging_method_synonym";
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
	public final static String ANATOMY_COMPUTED_SYNONYMS_BAG = "anatomy_computed_synonyms_bag";
	public final static String ANATOMY_COMPUTED_ANCESTORS_SYNONYMS_BAG = "anatomy_computed_ancestors_synonyms_bag";
	public final static String ANATOMY_COMPUTED_ANCESTORS_ID_BAG = "anatomy_computed_ancestors_id_bag";
	public final static String ANATOMY_COMPUTED_ANCESTORS_TERM_BAG = "anatomy_computed_ancestors_term_bag";

	public final static String DEPICTED_ANATOMY_ID_BAG = "depicted_anatomy_id_bag";
	public final static String DEPICTED_ANATOMY_TERM_BAG = "depicted_anatomy_term_bag";
	public final static String DEPICTED_ANATOMY_FREETEXT_BAG = "depicted_anatomy_freetext_bag";
	public final static String DEPICTED_ANATOMY_SYNONYMS_BAG = "depicted_anatomy_synonyms_bag";
	public final static String DEPICTED_ANATOMY_ANCESTORS_SYNONYMS_BAG = "depicted_anatomy_ancestors_synonyms_bag";
	public final static String DEPICTED_ANATOMY_ANCESTORS_ID_BAG = "depicted_anatomy_ancestors_id_bag";
	public final static String DEPICTED_ANATOMY_ANCESTORS_TERM_BAG = "depicted_anatomy_ancestors_term_bag";

	public final static String ABNORMAL_ANATOMY_ID_BAG = "abnormal_anatomy_id_bag";
	public final static String ABNORMAL_ANATOMY_TERM_BAG = "abnormal_anatomy_term_bag";
	public final static String ABNORMAL_ANATOMY_FREETEXT_BAG = "abnormal_anatomy_freetext_bag";
	public final static String ABNORMAL_ANATOMY_SYNONYMS_BAG = "abnormal_anatomy_synonyms_bag";
	public final static String ABNORMAL_ANATOMY_ANCESTORS_SYNONYMS_BAG = "abnormal_anatomy_ancestors_synonyms_bag";
	public final static String ABNORMAL_ANATOMY_ANCESTORS_ID_BAG = "abnormal_anatomy_ancestors_id_bag";
	public final static String ABNORMAL_ANATOMY_ANCESTORS_TERM_BAG = "abnormal_anatomy_ancestors_term_bag";

	public final static String EXPRESSION_IN_ID_BAG = "expression_in_id_bag";
	public final static String EXPRESSION_IN_LABEL_BAG = "expression_in_label_bag";
	public final static String EXPRESSION_IN_FREETEXT_BAG = "expression_in_freetext_bag";
	public final static String EXPRESSION_IN_SYNONYMS_BAG = "expression_in_synonyms_bag";
	public final static String EXPRESSION_IN_ANCESTORS_SYNONYMS_BAG = "expression_in_ancestors_synonyms_bag";
	public final static String EXPRESSION_IN_ANCESTORS_ID_BAG = "expression_in_ancestors_id_bag";
	public final static String EXPRESSION_IN_ANCESTORS_TERM_BAG = "expression_in_ancestors_term_bag";

	public final static String OBSERVATION_BAG = "observation_bag";
	public final static String MUTANT_GENE_ID_BAG = "mutant_gene_id_bag";
	public final static String MUTANT_GENE_SYMBOL_BAG = "mutant_gene_symbol_bag";
	public final static String MUTANT_GENE_SYNONYMS_BAG = "mutant_gene_synonyms_bag";
	public final static String EXPRESSED_GF_ID_BAG = "expressed_gf_id_bag";
	public final static String EXPRESSED_GF_SYMBOL_BAG = "expressed_gf_symbol_bag";
	public final static String EXPRESSED_GF_SYNONYMS_BAG = "expressed_gf_synonyms_bag";

	public final static String PHENOTYPE_ID_BAG = "phenotype_id_bag";
	public final static String PHENOTYPE_LABEL_BAG = "phenotype_label_bag";
	public final static String PHENOTYPE_FREETEXT_BAG = "phenotype_freetext_bag";
	public final static String PHENOTYPE_SYNONYMS_BAG = "phenotype_synonyms_bag";
	public final static String PHENOTYPE_ANCESTORS_SYNONYMS_BAG = "phenotype_ancestors_synonyms_bag";
	public final static String PHENOTYPE_ANCESTORS_TERM_BAG = "phenotype_ancestors_term_bag";
	public final static String PHENOTYPE_ANCESTORS_ID_BAG = "phenotype_ancestors_id_bag";

	public final static String TERM_AUTOSUGGEST = "term_autosuggest";
	public final static String GENERIC_SEARCH = "generic_search";

	@Field(ID)
	private String id;

	@Field(ASSOCIATED_ROI)
	private List<String> associatedRoi;

	@Field(ASSOCIATED_CHANNEL)
	private List<String> associatedChannel;

	@Field(HEIGHT)
	private Integer height;

	@Field(HOST_URL)
	private String hostUrl;

	@Field(HOST_NAME)
	private String hostName;

	@Field(IMAGE_GENERATED_BY)
	private String imageGeneratedBy;

	@Field(IMAGE_TYPE)
	private ArrayList<String> imageType;

	@Field(SAMPLE_TYPE)
	private String sampleType;

	@Field(IMAGE_URL)
	private String imageUrl;

	@Field(IMAGE_CONTEXT_URL)
	private String imageContextUrl;

	@Field(IMAGING_METHOD_ID)
	private String imagingMethodId;

	@Field(IMAGING_METHOD_LABEL)
	private String imagingMethodLabel;

	@Field(IMAGING_METHOD_SYNONYMS)
	private ArrayList<String> imagingMethodSynonyms;

	@Field(SAMPLE_PREPARATION_ID)
	private String samplePreparationId;

	@Field(SAMPLE_PREPARATION_LABEL)
	private String samplePreparationLabel;

	@Field(SAMPLE_PREPARATION_SYNONYMS)
	private ArrayList<String> samplePreparationSynonyms;

	@Field(VISUALISATION_METHOD_ID)
	private ArrayList<String> visualisationMethodId;

	@Field(VISUALISATION_METHOD_LABEL)
	private ArrayList<String> visualisationMethodLabel;

	@Field(VISUALISATION_METHOD_SYNONYMS)
	private ArrayList<String> visualisationMethodSynonyms;

	@Field(MACHINE)
	private String machine;

	@Field(THUMBNAIL_PATH)
	private String thumbnailPath;

	@Field(WIDTH)
	private Integer width;

	@Field(GENERIC_SEARCH)
	private List<String> genericSearch;
	
	@Field(AGE_SINCE_BIRTH)
	private Float ageSinceBirth;

	@Field(SAMPLE_GENERATED_BY)
	private String sampleGeneratedBy;

	@Field(TAXON)
	private String taxon;

	@Field(NCBI_TAXON_ID)
	private String ncbiTaxonId;

	@Field(SEX)
	private String sex;

	@Field(STAGE)
	private String stage;

	@Field(STAGE_ID)
	private String stageId;

	@Field(EMBRYONIC_AGE)
	private Float embryonicAge;

	// annotations -->

	@Field(ANATOMY_ID)
	private String anatomyId;

	@Field(ANATOMY_TERM)
	private String anatomyTerm;

	@Field(ANATOMY_FREETEXT)
	private String anatomyFreetext;

	@Field(ANATOMY_SYNONYMS)
	private ArrayList<String> anatomySynonyms;

	@Field(OBSERVATIONS)
	private List<String> observations;

	@Field(CONDITIONS)
	private List<String> conditions;

	@Field(GENE_ID)
	private List<String> geneIds;

	@Field(GENE_SYMBOL)
	private List<String> geneSymbols;

	@Field(GENE_SYNONYMS)
	private List<String> geneSynonyms;

	@Field(GF_ID)
	private List<String> geneticFeatureIds;

	@Field(GF_SYMBOL)
	private List<String> geneticFeatureSymbols;

	@Field(GF_SYNONYMS)
	private List<String> geneticFeatureSynonyms;

	@Field(GF_ENEMBL_ID)
	private List<String> genetifFeatureEnsemlIds;

	@Field(CHROMOSOME)
	private List<String> chromosome;

	@Field(START_POS)
	private List<Long> startPosition;

	@Field(END_POS)
	private List<Long> endPosition;

	@Field(STRAND)
	private List<String> strand;

	@Field(ZYGOSITY)
	private List<String> zygosity;

	@Field(DEPTH)
	private int depth;

	@Field(ANATOMY_COMPUTED_ID_BAG)
	private ArrayList<String> anatomyComputedIdBag;

	@Field(ANATOMY_COMPUTED_TERM_BAG)
	private ArrayList<String> anatomyComputedLabelBag;

	@Field(ANATOMY_COMPUTED_SYNONYMS_BAG)
	private ArrayList<String> anatomyComputedSynonymsBag;

	@Field(ANATOMY_COMPUTED_ANCESTORS_ID_BAG)
	private ArrayList<String> anatomyComputedAncestorsIdBag;

	@Field(ANATOMY_COMPUTED_ANCESTORS_SYNONYMS_BAG)
	private ArrayList<String> anatomyComputedAncestorsSynonymsBag;

	@Field(ANATOMY_COMPUTED_ANCESTORS_TERM_BAG)
	private ArrayList<String> anatomyComputedAncestorsTermBag;

	@Field(DEPICTED_ANATOMY_ID_BAG)
	private ArrayList<String> depictedAnatomyIdBag;

	@Field(DEPICTED_ANATOMY_TERM_BAG)
	private ArrayList<String> depictedAnatomyTermBag;

	@Field(DEPICTED_ANATOMY_FREETEXT_BAG)
	private ArrayList<String> depictedAnatomyFreetextBag;

	@Field(DEPICTED_ANATOMY_SYNONYMS_BAG)
	private ArrayList<String> depictedAnatomySynonymsBag;

	@Field(DEPICTED_ANATOMY_ANCESTORS_ID_BAG)
	private ArrayList<String> depictedAnatomyAncestorsIdBag;

	@Field(DEPICTED_ANATOMY_ANCESTORS_SYNONYMS_BAG)
	private ArrayList<String> depictedAnatomyAncestorsSynonymsBag;

	@Field(DEPICTED_ANATOMY_ANCESTORS_TERM_BAG)
	private ArrayList<String> depictedAnatomyAncestorsTermBag;

	@Field(ABNORMAL_ANATOMY_ID_BAG)
	private ArrayList<String> abnormalAnatomyIdBag;

	@Field(ABNORMAL_ANATOMY_TERM_BAG)
	private ArrayList<String> abnormalAnatomyTermBag;

	@Field(ABNORMAL_ANATOMY_FREETEXT_BAG)
	private ArrayList<String> abnormalAnatomyFreetextBag;

	@Field(ABNORMAL_ANATOMY_SYNONYMS_BAG)
	private ArrayList<String> abnormalAnatomySynonymsBag;

	@Field(ABNORMAL_ANATOMY_ANCESTORS_ID_BAG)
	private ArrayList<String> abnormalAnatomyAncestorsIdBag;

	@Field(ABNORMAL_ANATOMY_ANCESTORS_SYNONYMS_BAG)
	private ArrayList<String> abnormalAnatomyAncestorsSynonymsBag;

	@Field(ABNORMAL_ANATOMY_ANCESTORS_TERM_BAG)
	private ArrayList<String> abnormalAnatomyAncestorsTermBag;

	@Field(EXPRESSED_GF_ID_BAG)
	private ArrayList<String> expressedGfIdBag;

	@Field(EXPRESSED_GF_SYMBOL_BAG)
	private ArrayList<String> expressedGfSymbolBag;

	@Field(EXPRESSED_GF_SYNONYMS_BAG)
	private ArrayList<String> expressedGfSynonymsBag;

	@Field(EXPRESSION_IN_ID_BAG)
	private ArrayList<String> expressionInIdBag;

	@Field(EXPRESSION_IN_LABEL_BAG)
	private ArrayList<String> expressionInLabelBag;

	@Field(EXPRESSION_IN_FREETEXT_BAG)
	private ArrayList<String> expressionInFreetextBag;

	@Field(EXPRESSION_IN_SYNONYMS_BAG)
	private ArrayList<String> expressionInSynonymsBag;

	@Field(EXPRESSION_IN_ANCESTORS_ID_BAG)
	private ArrayList<String> expressionInAncestorsIdBag;

	@Field(EXPRESSION_IN_ANCESTORS_SYNONYMS_BAG)
	private ArrayList<String> expressionInAncestorsSynonymsBag;

	@Field(EXPRESSION_IN_ANCESTORS_TERM_BAG)
	private ArrayList<String> expressionInAncestorsTermBag;

	@Field(MUTANT_GENE_ID_BAG)
	private ArrayList<String> mutantGeneIdBag;

	@Field(MUTANT_GENE_SYMBOL_BAG)
	private ArrayList<String> mutantGeneSymbolBag;

	@Field(MUTANT_GENE_SYNONYMS_BAG)
	private ArrayList<String> mutantGeneSynonymsBag;

	@Field(OBSERVATION_BAG)
	private ArrayList<String> observationBag;

	@Field(PHENOTYPE_ID_BAG)
	private ArrayList<String> phenotypeIdBag;

	@Field(PHENOTYPE_LABEL_BAG)
	private ArrayList<String> phenotypeLabelBag;

	@Field(PHENOTYPE_FREETEXT_BAG)
	private ArrayList<String> phenotypeFreetextBag;

	@Field(PHENOTYPE_SYNONYMS_BAG)
	private ArrayList<String> phenotypeSynonymsBag;

	@Field(PHENOTYPE_ANCESTORS_ID_BAG)
	private HashSet<String> phenotypeAncestorsIdBag;

	@Field(PHENOTYPE_ANCESTORS_SYNONYMS_BAG)
	private HashSet<String> phenotypeAncestorsSynonymsBag;

	@Field(PHENOTYPE_ANCESTORS_TERM_BAG)
	private HashSet<String> phenotypeAncestorsTermBag;

	
		
	
	/**
	 * @return the genericSearch
	 */
	public List<String> getGenericSearch() {
	
		return genericSearch;
	}
	
	/**
	 * @param genericSearch the genericSearch to set
	 */
	public void setGenericSearch(List<String> genericSearch) {
	
		this.genericSearch = genericSearch;
	}


	/**
	 * @return the imagingMethodSynonyms
	 */
	public ArrayList<String> getImagingMethodSynonyms() {
	
		return imagingMethodSynonyms;
	}


	
	/**
	 * @param samplePreparationSynonyms the samplePreparationSynonyms to set
	 */
	public void setSamplePreparationSynonyms(ArrayList<String> samplePreparationSynonyms) {
	
		this.samplePreparationSynonyms = samplePreparationSynonyms;
	}


	
	/**
	 * @param visualisationMethodId the visualisationMethodId to set
	 */
	public void setVisualisationMethodId(ArrayList<String> visualisationMethodId) {
	
		this.visualisationMethodId = visualisationMethodId;
	}


	
	/**
	 * @param visualisationMethodLabel the visualisationMethodLabel to set
	 */
	public void setVisualisationMethodLabel(ArrayList<String> visualisationMethodLabel) {
	
		this.visualisationMethodLabel = visualisationMethodLabel;
	}


	
	/**
	 * @param visualisationMethodSynonyms the visualisationMethodSynonyms to set
	 */
	public void setVisualisationMethodSynonyms(ArrayList<String> visualisationMethodSynonyms) {
	
		this.visualisationMethodSynonyms = visualisationMethodSynonyms;
	}


	
	/**
	 * @param anatomySynonyms the anatomySynonyms to set
	 */
	public void setAnatomySynonyms(ArrayList<String> anatomySynonyms) {
	
		this.anatomySynonyms = anatomySynonyms;
	}


	
	/**
	 * @param geneSynonyms the geneSynonyms to set
	 */
	public void setGeneSynonyms(List<String> geneSynonyms) {
	
		this.geneSynonyms = geneSynonyms;
	}


	
	/**
	 * @param geneticFeatureSynonyms the geneticFeatureSynonyms to set
	 */
	public void setGeneticFeatureSynonyms(List<String> geneticFeatureSynonyms) {
	
		this.geneticFeatureSynonyms = geneticFeatureSynonyms;
	}


	
	/**
	 * @param anatomyComputedSynonymsBag the anatomyComputedSynonymsBag to set
	 */
	public void setAnatomyComputedSynonymsBag(ArrayList<String> anatomyComputedSynonymsBag) {
	
		this.anatomyComputedSynonymsBag = anatomyComputedSynonymsBag;
	}


	
	/**
	 * @param anatomyComputedAncestorsIdBag the anatomyComputedAncestorsIdBag to set
	 */
	public void setAnatomyComputedAncestorsIdBag(ArrayList<String> anatomyComputedAncestorsIdBag) {
	
		this.anatomyComputedAncestorsIdBag = anatomyComputedAncestorsIdBag;
	}


	
	/**
	 * @param anatomyComputedAncestorsSynonymsBag the anatomyComputedAncestorsSynonymsBag to set
	 */
	public void setAnatomyComputedAncestorsSynonymsBag(ArrayList<String> anatomyComputedAncestorsSynonymsBag) {
	
		this.anatomyComputedAncestorsSynonymsBag = anatomyComputedAncestorsSynonymsBag;
	}


	
	/**
	 * @param anatomyComputedAncestorsTermBag the anatomyComputedAncestorsTermBag to set
	 */
	public void setAnatomyComputedAncestorsTermBag(ArrayList<String> anatomyComputedAncestorsTermBag) {
	
		this.anatomyComputedAncestorsTermBag = anatomyComputedAncestorsTermBag;
	}


	
	/**
	 * @param depictedAnatomySynonymsBag the depictedAnatomySynonymsBag to set
	 */
	public void setDepictedAnatomySynonymsBag(ArrayList<String> depictedAnatomySynonymsBag) {
	
		this.depictedAnatomySynonymsBag = depictedAnatomySynonymsBag;
	}


	
	/**
	 * @param depictedAnatomyAncestorsIdBag the depictedAnatomyAncestorsIdBag to set
	 */
	public void setDepictedAnatomyAncestorsIdBag(ArrayList<String> depictedAnatomyAncestorsIdBag) {
	
		this.depictedAnatomyAncestorsIdBag = depictedAnatomyAncestorsIdBag;
	}


	
	/**
	 * @param depictedAnatomyAncestorsSynonymsBag the depictedAnatomyAncestorsSynonymsBag to set
	 */
	public void setDepictedAnatomyAncestorsSynonymsBag(ArrayList<String> depictedAnatomyAncestorsSynonymsBag) {
	
		this.depictedAnatomyAncestorsSynonymsBag = depictedAnatomyAncestorsSynonymsBag;
	}


	
	/**
	 * @param depictedAnatomyAncestorsTermBag the depictedAnatomyAncestorsTermBag to set
	 */
	public void setDepictedAnatomyAncestorsTermBag(ArrayList<String> depictedAnatomyAncestorsTermBag) {
	
		this.depictedAnatomyAncestorsTermBag = depictedAnatomyAncestorsTermBag;
	}


	
	/**
	 * @param abnormalAnatomySynonymsBag the abnormalAnatomySynonymsBag to set
	 */
	public void setAbnormalAnatomySynonymsBag(ArrayList<String> abnormalAnatomySynonymsBag) {
	
		this.abnormalAnatomySynonymsBag = abnormalAnatomySynonymsBag;
	}


	
	/**
	 * @param abnormalAnatomyAncestorsIdBag the abnormalAnatomyAncestorsIdBag to set
	 */
	public void setAbnormalAnatomyAncestorsIdBag(ArrayList<String> abnormalAnatomyAncestorsIdBag) {
	
		this.abnormalAnatomyAncestorsIdBag = abnormalAnatomyAncestorsIdBag;
	}


	
	/**
	 * @param abnormalAnatomyAncestorsSynonymsBag the abnormalAnatomyAncestorsSynonymsBag to set
	 */
	public void setAbnormalAnatomyAncestorsSynonymsBag(ArrayList<String> abnormalAnatomyAncestorsSynonymsBag) {
	
		this.abnormalAnatomyAncestorsSynonymsBag = abnormalAnatomyAncestorsSynonymsBag;
	}


	
	/**
	 * @param abnormalAnatomyAncestorsTermBag the abnormalAnatomyAncestorsTermBag to set
	 */
	public void setAbnormalAnatomyAncestorsTermBag(ArrayList<String> abnormalAnatomyAncestorsTermBag) {
	
		this.abnormalAnatomyAncestorsTermBag = abnormalAnatomyAncestorsTermBag;
	}


	
	/**
	 * @param expressedGfSynonymsBag the expressedGfSynonymsBag to set
	 */
	public void setExpressedGfSynonymsBag(ArrayList<String> expressedGfSynonymsBag) {
	
		this.expressedGfSynonymsBag = expressedGfSynonymsBag;
	}


	
	/**
	 * @param expressionInAncestorsIdBag the expressionInAncestorsIdBag to set
	 */
	public void setExpressionInAncestorsIdBag(ArrayList<String> expressionInAncestorsIdBag) {
	
		this.expressionInAncestorsIdBag = expressionInAncestorsIdBag;
	}


	
	/**
	 * @param expressionInAncestorsSynonymsBag the expressionInAncestorsSynonymsBag to set
	 */
	public void setExpressionInAncestorsSynonymsBag(ArrayList<String> expressionInAncestorsSynonymsBag) {
	
		this.expressionInAncestorsSynonymsBag = expressionInAncestorsSynonymsBag;
	}


	
	/**
	 * @param expressionInAncestorsTermBag the expressionInAncestorsTermBag to set
	 */
	public void setExpressionInAncestorsTermBag(ArrayList<String> expressionInAncestorsTermBag) {
	
		this.expressionInAncestorsTermBag = expressionInAncestorsTermBag;
	}


	
	/**
	 * @param mutantGeneSynonymsBag the mutantGeneSynonymsBag to set
	 */
	public void setMutantGeneSynonymsBag(ArrayList<String> mutantGeneSynonymsBag) {
	
		this.mutantGeneSynonymsBag = mutantGeneSynonymsBag;
	}


	
	/**
	 * @param phenotypeSynonymsBag the phenotypeSynonymsBag to set
	 */
	public void setPhenotypeSynonymsBag(ArrayList<String> phenotypeSynonymsBag) {
	
		this.phenotypeSynonymsBag = phenotypeSynonymsBag;
	}


	
	/**
	 * @param phenotypeAncestorsIdBag the phenotypeAncestorsIdBag to set
	 */
	public void setPhenotypeAncestorsIdBag(HashSet<String> phenotypeAncestorsIdBag) {
	
		this.phenotypeAncestorsIdBag = phenotypeAncestorsIdBag;
	}


	
	/**
	 * @param phenotypeAncestorsSynonymsBag the phenotypeAncestorsSynonymsBag to set
	 */
	public void setPhenotypeAncestorsSynonymsBag(HashSet<String> phenotypeAncestorsSynonymsBag) {
	
		this.phenotypeAncestorsSynonymsBag = phenotypeAncestorsSynonymsBag;
	}


	
	/**
	 * @param phenotypeAncestorsTermBag the phenotypeAncestorsTermBag to set
	 */
	public void setPhenotypeAncestorsTermBag(HashSet<String> phenotypeAncestorsTermBag) {
	
		this.phenotypeAncestorsTermBag = phenotypeAncestorsTermBag;
	}


	/**
	 * @return the anatomyComputedAncestorsIdBag
	 */
	public ArrayList<String> getAnatomyComputedAncestorsIdBag() {

		return anatomyComputedAncestorsIdBag;
	}


	public void addAnatomyComputedAncestorsIdBag(String anatomyComputedAncestorId) {

		if (this.anatomyComputedAncestorsIdBag == null) {
			this.anatomyComputedAncestorsIdBag = new ArrayList<>();
		}
		if (!this.anatomyComputedAncestorsIdBag.contains(anatomyComputedAncestorId)) {
			this.anatomyComputedAncestorsIdBag.add(anatomyComputedAncestorId);
		}
	}


	/**
	 * @return the anatomyComputedAncestorsSynonymsBag
	 */
	public ArrayList<String> getAnatomyComputedAncestorsSynonymsBag() {

		return anatomyComputedAncestorsSynonymsBag;
	}


	/**
	 * @param anatomyComputedAncestorsSynonymsBag
	 *            the anatomyComputedAncestorsSynonymsBag to set
	 */
	public void addAnatomyComputedAncestorsSynonymsBag(ArrayList<String> anatomyComputedAncestorsSynonymsBag) {

		if (this.anatomyComputedAncestorsSynonymsBag == null){
			this.anatomyComputedAncestorsSynonymsBag = new ArrayList<>();
		}
		if (anatomyComputedAncestorsSynonymsBag != null && !this.anatomyComputedAncestorsSynonymsBag.contains(anatomyComputedAncestorsSynonymsBag)){
			this.anatomyComputedAncestorsSynonymsBag.addAll(anatomyComputedAncestorsSynonymsBag);
		}
	}


	/**
	 * @return the anatomyComputedAncestorsTermBag
	 */
	public ArrayList<String> getAnatomyComputedAncestorsTermBag() {

		return anatomyComputedAncestorsTermBag;
	}


	/**
	 * @param anatomyComputedAncestorsTermBag
	 *            the anatomyComputedAncestorsTermBag to set
	 */
	public void addAnatomyComputedAncestorsTermBag(String anatomyComputedAncestorsTermBag) {

		if (this.anatomyComputedAncestorsTermBag == null){
			this.anatomyComputedAncestorsTermBag = new ArrayList<>();
		}
		if (!this.anatomyComputedAncestorsTermBag.contains(anatomyComputedAncestorsTermBag)){
			this.anatomyComputedAncestorsTermBag.add(anatomyComputedAncestorsTermBag);
		}
	}


	/**
	 * @return the depictedAnatomyAncestorsIdBag
	 */
	public ArrayList<String> getDepictedAnatomyAncestorsIdBag() {

		return depictedAnatomyAncestorsIdBag;
	}


	/**
	 * @param depictedAnatomyAncestorId
	 *            the depictedAnatomyAncestorsIdBag to set
	 */
	public void addDepictedAnatomyAncestorsIdBag(String depictedAnatomyAncestorId) {

		if (this.depictedAnatomyAncestorsIdBag == null){
			this.depictedAnatomyAncestorsIdBag = new ArrayList<>();
		}
		if (depictedAnatomyAncestorId != null && !this.depictedAnatomyAncestorsIdBag.contains(depictedAnatomyAncestorId)){
			this.depictedAnatomyAncestorsIdBag.add(depictedAnatomyAncestorId);
		}
	}


	/**
	 * @return the depictedAnatomyAncestorsSynonymsBag
	 */
	public ArrayList<String> getDepictedAnatomyAncestorsSynonymsBag() {

		return depictedAnatomyAncestorsSynonymsBag;
	}


	/**
	 * @param depictedAnatomyAncestorsSynonymsBag
	 *            the depictedAnatomyAncestorsSynonymsBag to set
	 */
	public void addDepictedAnatomyAncestorsSynonymsBag(ArrayList<String> depictedAnatomyAncestorsSynonymsBag) {

		if (this.depictedAnatomyAncestorsSynonymsBag == null){
			this.depictedAnatomyAncestorsSynonymsBag = new ArrayList<>();
		}
		if (depictedAnatomyAncestorsSynonymsBag != null && !this.depictedAnatomyAncestorsSynonymsBag.contains(depictedAnatomyAncestorsSynonymsBag)){
			this.depictedAnatomyAncestorsSynonymsBag.addAll(depictedAnatomyAncestorsSynonymsBag);
		}
	}


	/**
	 * @return the depictedAnatomyAncestorsTermBag
	 */
	public ArrayList<String> getDepictedAnatomyAncestorsTermBag() {

		return depictedAnatomyAncestorsTermBag;
	}


	/**
	 * @param depictedAnatomyAncestorTerm
	 *            the depictedAnatomyAncestorsTermBag to set
	 */
	public void addDepictedAnatomyAncestorsTermBag(String depictedAnatomyAncestorTerm) {

		if (this.depictedAnatomyAncestorsTermBag == null){
			this.depictedAnatomyAncestorsTermBag = new ArrayList<>();
		}
		if (depictedAnatomyAncestorTerm != null && !this.depictedAnatomyAncestorsTermBag.contains(depictedAnatomyAncestorTerm)){
			this.depictedAnatomyAncestorsTermBag.add(depictedAnatomyAncestorTerm);
		}
	}


	/**
	 * @return the abnormalAnatomyAncestorsIdBag
	 */
	public ArrayList<String> getAbnormalAnatomyAncestorsIdBag() {

		return abnormalAnatomyAncestorsIdBag;
	}


	/**
	 * @param abnormalAnatomyAncestorId
	 *            the abnormalAnatomyAncestorsIdBag to set
	 */
	public void addAbnormalAnatomyAncestorsIdBag(String abnormalAnatomyAncestorId) {

		if (this.abnormalAnatomyAncestorsIdBag == null){
			this.abnormalAnatomyAncestorsIdBag = new ArrayList<>();
		}
		if (abnormalAnatomyAncestorId != null && !this.abnormalAnatomyAncestorsIdBag.contains(abnormalAnatomyAncestorId)){
			this.abnormalAnatomyAncestorsIdBag.add(abnormalAnatomyAncestorId);
		}
	}


	/**
	 * @return the abnormalAnatomyAncestorsSynonymsBag
	 */
	public ArrayList<String> getAbnormalAnatomyAncestorsSynonymsBag() {

		return abnormalAnatomyAncestorsSynonymsBag;
	}


	/**
	 * @param abnormalAnatomyAncestorsSynonymsBag
	 *            the abnormalAnatomyAncestorsSynonymsBag to set
	 */
	public void addAbnormalAnatomyAncestorsSynonymsBag(ArrayList<String> abnormalAnatomyAncestorsSynonymsBag) {

		if (this.abnormalAnatomyAncestorsSynonymsBag == null){
			this.abnormalAnatomyAncestorsSynonymsBag = new ArrayList<>();
		}
		if (abnormalAnatomyAncestorsSynonymsBag != null && !this.abnormalAnatomyAncestorsSynonymsBag.contains(abnormalAnatomyAncestorsSynonymsBag)){
			this.abnormalAnatomyAncestorsSynonymsBag.addAll(abnormalAnatomyAncestorsSynonymsBag);
		}
	}


	/**
	 * @return the abnormalAnatomyAncestorsTermBag
	 */
	public ArrayList<String> getAbnormalAnatomyAncestorsTermBag() {

		return abnormalAnatomyAncestorsTermBag;
	}


	/**
	 * @param abnormalAnatomyAncestorTerm
	 *            the abnormalAnatomyAncestorsTermBag to set
	 */
	public void addAbnormalAnatomyAncestorsTermBag(String abnormalAnatomyAncestorTerm) {

		if (this.abnormalAnatomyAncestorsTermBag == null){
			this.abnormalAnatomyAncestorsTermBag = new ArrayList<>();
		}
		if (!this.abnormalAnatomyAncestorsTermBag.contains(abnormalAnatomyAncestorTerm)){
			this.abnormalAnatomyAncestorsTermBag.add(abnormalAnatomyAncestorTerm);
		}
	}


	/**
	 * @return the expressionInAncestorsIdBag
	 */
	public ArrayList<String> getExpressionInAncestorsIdBag() {

		return expressionInAncestorsIdBag;
	}


	/**
	 * @param expressionInAncestorId
	 *            the expressionInAncestorsIdBag to set
	 */
	public void addExpressionInAncestorsIdBag(String expressionInAncestorId) {

		if (this.expressionInAncestorsIdBag == null){
			this.expressionInAncestorsIdBag = new ArrayList<>();
		}
		if (!this.expressionInAncestorsIdBag.contains(expressionInAncestorId)){
			this.expressionInAncestorsIdBag.add(expressionInAncestorId);
		}
	}


	/**
	 * @return the expressionInAncestorsSynonymsBag
	 */
	public ArrayList<String> getExpressionInAncestorsSynonymsBag() {

		return expressionInAncestorsSynonymsBag;
	}


	/**
	 * @param expressionInAncestorsSynonymsBag
	 *            the expressionInAncestorsSynonymsBag to set
	 */
	public void addExpressionInAncestorsSynonymsBag(ArrayList<String> expressionInAncestorsSynonymsBag) {

		if (this.expressionInAncestorsSynonymsBag == null){
			this.expressionInAncestorsSynonymsBag = new ArrayList<>();
		}
		if (expressionInAncestorsSynonymsBag != null && !this.expressionInAncestorsSynonymsBag.contains(expressionInAncestorsSynonymsBag)){
			this.expressionInAncestorsSynonymsBag.addAll(expressionInAncestorsSynonymsBag);
		}
	}


	/**
	 * @return the expressionInAncestorsTermBag
	 */
	public ArrayList<String> getExpressionInAncestorsTermBag() {

		return expressionInAncestorsTermBag;
	}


	/**
	 * @param expressionInAncestorsTermBag
	 *            the expressionInAncestorsTermBag to set
	 */
	public void addExpressionInAncestorsTermBag(String expressionInAncestorsTermBag) {

		if (this.expressionInAncestorsTermBag == null){
			this.expressionInAncestorsTermBag = new ArrayList<>();
		}
		if (!this.expressionInAncestorsTermBag.contains(expressionInAncestorsTermBag)){
			this.expressionInAncestorsTermBag.add(expressionInAncestorsTermBag);
		}
	}


	/**
	 * @return the phenotypeAncestorsIdBag
	 */
	public Set<String> getPhenotypeAncestorsIdBag() {

		return phenotypeAncestorsIdBag;
	}


	/**
	 * @param phenotypeAncestorId
	 *            the phenotypeAncestorsIdBag to set
	 */
	public void addPhenotypeAncestorsIdBag(String phenotypeAncestorId) {

		if (this.phenotypeAncestorsIdBag == null){
			this.phenotypeAncestorsIdBag = new HashSet<>();
		}
		if (!this.phenotypeAncestorsIdBag.contains(phenotypeAncestorId)){
			this.phenotypeAncestorsIdBag.add(phenotypeAncestorId);
		}
	}


	/**
	 * @return the phenotypeAncestorsSynonymsBag
	 */
	public Set<String> getPhenotypeAncestorsSynonymsBag() {

		return phenotypeAncestorsSynonymsBag;
	}


	/**
	 * @param phenotypeAncestorSynonyms
	 *            the phenotypeAncestorsSynonymsBag to set
	 */
	public void addPhenotypeAncestorsSynonymsBag(ArrayList<String> phenotypeAncestorSynonyms) {

		if (this.phenotypeAncestorsSynonymsBag == null){
			this.phenotypeAncestorsSynonymsBag = new HashSet<>();
		}
		if (phenotypeAncestorSynonyms != null && !this.phenotypeAncestorsSynonymsBag.contains(phenotypeAncestorSynonyms)){
			this.phenotypeAncestorsSynonymsBag.addAll(phenotypeAncestorSynonyms);
		}
	}


	/**
	 * @return the phenotypeAncestorsTermBag
	 */
	public Set<String> getPhenotypeAncestorsTermBag() {

		return phenotypeAncestorsTermBag;
	}


	/**
	 * @param phenotypeAncestorTerm
	 *            the phenotypeAncestorsTermBag to set
	 */
	public void addPhenotypeAncestorsTermBag(String phenotypeAncestorTerm) {

		if (this.phenotypeAncestorsTermBag == null){
			this.phenotypeAncestorsTermBag = new HashSet<>();
		}
		if (phenotypeAncestorTerm != null && !this.phenotypeAncestorsTermBag.contains(phenotypeAncestorTerm)){
			this.phenotypeAncestorsTermBag.add(phenotypeAncestorTerm);
		}
	}


	/**
	 * @param imagingMethodSynonyms
	 *            the imagingMethodSynonyms to set
	 */
	public void setImagingMethodSynonyms(ArrayList<String> imagingMethodSynonyms) {

		this.imagingMethodSynonyms = imagingMethodSynonyms;
	}


	/**
	 * @param imagingMethodSynonyms
	 *            the imagingMethodSynonyms to set
	 */
	public void addImagingMethodSynonyms(ArrayList<String> imagingMethodSynonyms) {

		if (this.imagingMethodSynonyms == null) {
			this.imagingMethodSynonyms = new ArrayList<>();
		}
		if (imagingMethodSynonyms != null)
			this.imagingMethodSynonyms.addAll(imagingMethodSynonyms);
	}


	/**
	 * @return the samplePreparationSynonyms
	 */
	public ArrayList<String> getSamplePreparationSynonyms() {

		return samplePreparationSynonyms;
	}


	/**
	 * @param samplePreparationSynonyms
	 *            the samplePreparationSynonyms to set
	 */
	public void addSamplePreparationSynonyms(ArrayList<String> samplePreparationSynonyms) {

		if (this.samplePreparationSynonyms == null) {
			this.samplePreparationSynonyms = new ArrayList<>();
		}
		if (samplePreparationSynonyms != null)
			this.samplePreparationSynonyms.addAll(samplePreparationSynonyms);
	}


	/**
	 * @return the visualisationMethodSynonyms
	 */
	public ArrayList<String> getVisualisationMethodSynonyms() {

		return visualisationMethodSynonyms;
	}


	/**
	 * @param visualisationMethodSynonyms
	 *            the visualisationMethodSynonyms to set
	 */
	public void addVisualisationMethodSynonyms(ArrayList<String> visualisationMethodSynonyms) {

		if (this.visualisationMethodSynonyms == null) {
			this.visualisationMethodSynonyms = new ArrayList<>();
		}
		if (visualisationMethodSynonyms != null)
			this.visualisationMethodSynonyms.addAll(visualisationMethodSynonyms);
	}


	/**
	 * @return the anatomySynonyms
	 */
	public ArrayList<String> getAnatomySynonyms() {

		return anatomySynonyms;
	}


	/**
	 * @param anatomySynonyms
	 *            the anatomySynonyms to set
	 */
	public void addAnatomySynonyms(ArrayList<String> anatomySynonyms) {

		if (this.anatomySynonyms == null) {
			this.anatomySynonyms = new ArrayList<>();
		}
		if (anatomySynonyms != null)
			this.anatomySynonyms.addAll(anatomySynonyms);
	}


	/**
	 * @return the geneSynonyms
	 */
	public List<String> getGeneSynonyms() {

		return geneSynonyms;
	}


	/**
	 * @param geneSynonyms
	 *            the geneSynonyms to set
	 */
	public void addGeneSynonyms(List<String> geneSynonyms) {

		if (this.geneSynonyms == null) {
			this.geneSynonyms = new ArrayList<>();
		}
		if (geneSynonyms != null)
			this.geneSynonyms.addAll(geneSynonyms);
	}


	/**
	 * @return the geneticFeatureSynonyms
	 */
	public List<String> getGeneticFeatureSynonyms() {

		return geneticFeatureSynonyms;
	}


	/**
	 * @param geneticFeatureSynonyms
	 *            the geneticFeatureSynonyms to set
	 */
	public void addGeneticFeatureSynonyms(List<String> geneticFeatureSynonyms) {

		if (this.geneticFeatureSynonyms == null) {
			this.geneticFeatureSynonyms = new ArrayList<>();
		}
		if (geneticFeatureSynonyms != null)
			this.geneticFeatureSynonyms.addAll(geneticFeatureSynonyms);
	}


	/**
	 * @return the anatomyComputedSynonymsBag
	 */
	public ArrayList<String> getAnatomyComputedSynonymsBag() {

		return anatomyComputedSynonymsBag;
	}


	/**
	 * @param anatomyComputedSynonymsBag
	 *            the anatomyComputedSynonymsBag to set
	 */
	public void addAnatomyComputedSynonymsBag(ArrayList<String> anatomyComputedSynonymsBag) {

		if (this.anatomyComputedSynonymsBag == null) {
			this.anatomyComputedSynonymsBag = new ArrayList<>();
		}
		if (anatomyComputedSynonymsBag != null)
			this.anatomyComputedSynonymsBag.addAll(anatomyComputedSynonymsBag);
	}


	/**
	 * @return the depictedAnatomySynonymsBag
	 */
	public ArrayList<String> getDepictedAnatomySynonymsBag() {

		return depictedAnatomySynonymsBag;
	}


	/**
	 * @param depictedAnatomySynonymsBag
	 *            the depictedAnatomySynonymsBag to set
	 */
	public void addDepictedAnatomySynonymsBag(ArrayList<String> depictedAnatomySynonymsBag) {

		if (this.depictedAnatomySynonymsBag == null) {
			this.depictedAnatomySynonymsBag = new ArrayList<>();
		}
		if (depictedAnatomySynonymsBag != null)
			this.depictedAnatomySynonymsBag.addAll(depictedAnatomySynonymsBag);
	}


	/**
	 * @return the abnormalAnatomySynonymsBag
	 */
	public ArrayList<String> getAbnormalAnatomySynonymsBag() {

		return abnormalAnatomySynonymsBag;
	}


	/**
	 * @param abnormalAnatomySynonymsBag
	 *            the abnormalAnatomySynonymsBag to set
	 */
	public void addAbnormalAnatomySynonymsBag(ArrayList<String> abnormalAnatomySynonymsBag) {

		if (this.abnormalAnatomySynonymsBag == null) {
			this.abnormalAnatomySynonymsBag = new ArrayList<>();
		}
		if (abnormalAnatomySynonymsBag != null)
			this.abnormalAnatomySynonymsBag.addAll(abnormalAnatomySynonymsBag);
	}


	/**
	 * @return the expressedGfSynonymsBag
	 */
	public ArrayList<String> getExpressedGfSynonymsBag() {

		return expressedGfSynonymsBag;
	}


	/**
	 * @param expressedGfSynonymsBag
	 *            the expressedGfSynonymsBag to set
	 */
	public void addExpressedGfSynonymsBag(ArrayList<String> expressedGfSynonymsBag) {

		if (this.expressedGfSynonymsBag == null) {
			this.expressedGfSynonymsBag = new ArrayList<>();
		}
		if (expressedGfSynonymsBag != null)
			this.expressedGfSynonymsBag.addAll(expressedGfSynonymsBag);
	}


	/**
	 * @return the expressionInSynonymsBag
	 */
	public ArrayList<String> getExpressionInSynonymsBag() {

		return expressionInSynonymsBag;
	}


	/**
	 * @param expressionInSynonymsBag
	 *            the expressionInSynonymsBag to set
	 */
	public void setExpressionInSynonymsBag(ArrayList<String> expressionInSynonymsBag) {

		if (this.expressionInSynonymsBag == null) {
			this.expressionInSynonymsBag = new ArrayList<>();
		}
		if (expressionInSynonymsBag != null)
			this.expressionInSynonymsBag.addAll(expressionInSynonymsBag);
	}


	/**
	 * @return the mutantGeneSynonymsBag
	 */
	public ArrayList<String> getMutantGeneSynonymsBag() {

		return mutantGeneSynonymsBag;
	}


	/**
	 * @param mutantGeneSynonymsBag
	 *            the mutantGeneSynonymsBag to set
	 */
	public void addMutantGeneSynonymsBag(ArrayList<String> mutantGeneSynonymsBag) {

		if (this.mutantGeneSynonymsBag == null) {
			this.mutantGeneSynonymsBag = new ArrayList<>();
		}
		if (mutantGeneSynonymsBag != null)
			this.mutantGeneSynonymsBag.addAll(mutantGeneSynonymsBag);
	}


	/**
	 * @return the phenotypeSynonymsBag
	 */
	public ArrayList<String> getPhenotypeSynonymsBag() {

		return phenotypeSynonymsBag;
	}


	/**
	 * @param phenotypeSynonymsBag
	 *            the phenotypeSynonymsBag to set
	 */
	public void addPhenotypeSynonymsBag(ArrayList<String> phenotypeSynonymsBag) {

		if (this.phenotypeSynonymsBag == null) {
			this.phenotypeSynonymsBag = new ArrayList<>();
		}
		if (phenotypeSynonymsBag != null)
			this.phenotypeSynonymsBag.addAll(phenotypeSynonymsBag);
	}


	/**
	 * @return the depth
	 */
	public long getDepth() {

		return depth;
	}


	/**
	 * @return the stageId
	 */
	public String getStageId() {

		return stageId;
	}


	/**
	 * @param stageId
	 *            the stageId to set
	 */
	public void setStageId(String stageId) {

		this.stageId = stageId;
	}


	/**
	 * @param depth
	 *            the depth to set
	 */
	public void setDepth(int depth) {

		this.depth = depth;
	}


	/**
	 * @return the associatedRoi
	 */
	public List<String> getAssociatedRoi() {

		return associatedRoi;
	}


	/**
	 * @param associatedRoi
	 *            the associatedRoi to set
	 */
	public void setAssociatedRoi(List<String> associatedRoi) {

		this.associatedRoi = associatedRoi;
	}
	
	public void addAssociatedRoi(String associatedRoiId) {

		if (associatedRoi == null){
			associatedRoi = new ArrayList<>();
		}
		this.associatedRoi.add(associatedRoiId);
	}


	/**
	 * @return the associatedChannel
	 */
	public List<String> getAssociatedChannel() {

		return associatedChannel;
	}


	/**
	 * @param associatedChannel
	 *            the associatedChannel to set
	 */
	public void setAssociatedChannel(List<String> associatedChannel) {

		this.associatedChannel = associatedChannel;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {

		return height;
	}


	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Integer height) {

		this.height = height;
	}


	/**
	 * @return the hostUrl
	 */
	public String getHostUrl() {

		return hostUrl;
	}


	/**
	 * @param hostUrl
	 *            the hostUrl to set
	 */
	public void setHostUrl(String hostUrl) {

		this.hostUrl = hostUrl;
	}


	/**
	 * @return the hostName
	 */
	public String getHostName() {

		return hostName;
	}


	/**
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName) {

		this.hostName = hostName;
	}


	/**
	 * @return the imageGeneratedBy
	 */
	public String getImageGeneratedBy() {

		return imageGeneratedBy;
	}


	/**
	 * @param imageGeneratedBy
	 *            the imageGeneratedBy to set
	 */
	public void setImageGeneratedBy(String imageGeneratedBy) {

		this.imageGeneratedBy = imageGeneratedBy;
	}


	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {

		return imageUrl;
	}


	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {

		this.imageUrl = imageUrl;
	}


	/**
	 * @return the imageContextUrl
	 */
	public String getImageContextUrl() {

		return imageContextUrl;
	}


	/**
	 * @param imageContextUrl
	 *            the imageContextUrl to set
	 */
	public void setImageContextUrl(String imageContextUrl) {

		this.imageContextUrl = imageContextUrl;
	}


	/**
	 * @return the imagingMethodId
	 */
	public String getImagingMethodId() {

		return imagingMethodId;
	}


	/**
	 * @param imagingMethodId
	 *            the imagingMethodId to set
	 */
	public void setImagingMethodId(String imagingMethodId) {

		this.imagingMethodId = imagingMethodId;
	}


	/**
	 * @return the samplePreparationId
	 */
	public String getSamplePreparationId() {

		return samplePreparationId;
	}


	/**
	 * @param samplePreparationId
	 *            the samplePreparationId to set
	 */
	public void setSamplePreparationId(String samplePreparationId) {

		this.samplePreparationId = samplePreparationId;
	}


	/**
	 * @return the visualisationMethodId
	 */
	public ArrayList<String> getVisualisationMethodId() {

		return visualisationMethodId;
	}


	/**
	 * @param visualisationMethodId
	 *            the visualisationMethodId to set
	 */
	public void addVisualisationMethodId(String visualisationMethodId) {
		if (this.visualisationMethodId == null){
			this.visualisationMethodId = new ArrayList<>();
		}
		this.visualisationMethodId.add(visualisationMethodId);
	}


	/**
	 * @return the imagingMethodLabel
	 */
	public String getImagingMethodLabel() {

		return imagingMethodLabel;
	}


	/**
	 * @param imagingMethodLabel
	 *            the imagingMethodLabel to set
	 */
	public void setImagingMethodLabel(String imagingMethodLabel) {

		this.imagingMethodLabel = imagingMethodLabel;
	}


	/**
	 * @return the samplePreparationLabel
	 */
	public String getSamplePreparationLabel() {

		return samplePreparationLabel;
	}


	/**
	 * @param samplePreparationLabel
	 *            the samplePreparationLabel to set
	 */
	public void setSamplePreparationLabel(String samplePreparationLabel) {

		this.samplePreparationLabel = samplePreparationLabel;
	}


	/**
	 * @return the visualisationMethodLabel
	 */
	public ArrayList<String> getVisualisationMethodLabel() {

		return visualisationMethodLabel;
	}


	/**
	 * @param visualisationMethodLabel
	 *            the visualisationMethodLabel to set
	 */
	public void addVisualisationMethodLabel(String visualisationMethodLabel) {
		if (this.visualisationMethodLabel == null){
			this.visualisationMethodLabel = new ArrayList<>();
		}
		this.visualisationMethodLabel.add(visualisationMethodLabel);
	}


	/**
	 * @return the machine
	 */
	public String getMachine() {

		return machine;
	}


	/**
	 * @param machine
	 *            the machine to set
	 */
	public void setMachine(String machine) {

		this.machine = machine;
	}


	/**
	 * @return the thumbnailPath
	 */
	public String getThumbnailPath() {

		return thumbnailPath;
	}


	/**
	 * @param thumbnailPath
	 *            the thumbnailPath to set
	 */
	public void setThumbnailPath(String thumbnailPath) {

		this.thumbnailPath = thumbnailPath;
	}


	/**
	 * @return the width
	 */
	public Integer getWidth() {

		return width;
	}


	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Integer width) {

		this.width = width;
	}


	/**
	 * @return the ageSinceBirth
	 */
	public Float getAgeSinceBirth() {

		return ageSinceBirth;
	}


	/**
	 * @param ageSinceBirth
	 *            the ageSinceBirth to set
	 */
	public void setAgeSinceBirth(Float ageSinceBirth) {

		this.ageSinceBirth = ageSinceBirth;
	}


	/**
	 * @return the sampleGeneratedBy
	 */
	public String getSampleGeneratedBy() {

		return sampleGeneratedBy;
	}


	/**
	 * @param sampleGeneratedBy
	 *            the sampleGeneratedBy to set
	 */
	public void setSampleGeneratedBy(String sampleGeneratedBy) {

		this.sampleGeneratedBy = sampleGeneratedBy;
	}


	/**
	 * @return the taxon
	 */
	public String getTaxon() {

		return taxon;
	}


	/**
	 * @param taxon
	 *            the taxon to set
	 */
	public void setTaxon(String taxon) {

		this.taxon = taxon;
	}


	/**
	 * @return the ncbiTaxonId
	 */
	public String getNcbiTaxonId() {

		return ncbiTaxonId;
	}


	/**
	 * @param ncbiTaxonId
	 *            the ncbiTaxonId to set
	 */
	public void setNcbiTaxonId(String ncbiTaxonId) {

		this.ncbiTaxonId = ncbiTaxonId;
	}


	/**
	 * @return the sex
	 */
	public String getSex() {

		return sex;
	}


	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {

		this.sex = sex;
	}


	/**
	 * @return the stage
	 */
	public String getStage() {

		return stage;
	}


	/**
	 * @param stage
	 *            the stage to set
	 */
	public void setStage(String stage) {

		this.stage = stage;
	}


	/**
	 * @return the embryonicAge
	 */
	public Float getEmbryonicAge() {

		return embryonicAge;
	}


	/**
	 * @param embryonicAge
	 *            the embryonicAge to set
	 */
	public void setEmbryonicAge(Float embryonicAge) {

		this.embryonicAge = embryonicAge;
	}


	/**
	 * @return the anatomyId
	 */
	public String getAnatomyId() {

		return anatomyId;
	}


	/**
	 * @param anatomyId
	 *            the anatomyId to set
	 */
	public void setAnatomyId(String anatomyId) {

		this.anatomyId = anatomyId;
	}


	/**
	 * @return the anatomyTerm
	 */
	public String getAnatomyTerm() {

		return anatomyTerm;
	}


	/**
	 * @param anatomyTerm
	 *            the anatomyTerm to set
	 */
	public void setAnatomyTerm(String anatomyTerm) {

		this.anatomyTerm = anatomyTerm;
	}


	/**
	 * @return the anatomyFreetext
	 */
	public String getAnatomyFreetext() {

		return anatomyFreetext;
	}


	/**
	 * @param anatomyFreetext
	 *            the anatomyFreetext to set
	 */
	public void setAnatomyFreetext(String anatomyFreetext) {

		this.anatomyFreetext = anatomyFreetext;
	}


	/**
	 * @return the observations
	 */
	public List<String> getObservations() {

		return observations;
	}


	/**
	 * @param observations
	 *            the observations to set
	 */
	public void setObservations(List<String> observations) {

		this.observations = observations;
	}


	/**
	 * @return the conditions
	 */
	public List<String> getConditions() {

		return conditions;
	}


	/**
	 * @param conditions
	 *            the conditions to set
	 */
	public void setConditions(List<String> conditions) {

		this.conditions = conditions;
	}


	/**
	 * @return the geneIds
	 */
	public List<String> getGeneIds() {

		return geneIds;
	}


	/**
	 * @param geneIds
	 *            the geneIds to set
	 */
	public void setGeneIds(List<String> geneIds) {

		this.geneIds = geneIds;
	}


	/**
	 * @return the geneSymbols
	 */
	public List<String> getGeneSymbols() {

		return geneSymbols;
	}


	/**
	 * @param geneSymbols
	 *            the geneSymbols to set
	 */
	public void setGeneSymbols(List<String> geneSymbols) {

		this.geneSymbols = geneSymbols;
	}


	/**
	 * @return the geneticFeatureIds
	 */
	public List<String> getGeneticFeatureIds() {

		return geneticFeatureIds;
	}


	/**
	 * @param geneticFeatureIds
	 *            the geneticFeatureIds to set
	 */
	public void setGeneticFeatureIds(List<String> geneticFeatureIds) {

		this.geneticFeatureIds = geneticFeatureIds;
	}


	/**
	 * @return the geneticFeatureSymbols
	 */
	public List<String> getGeneticFeatureSymbols() {

		return geneticFeatureSymbols;
	}


	/**
	 * @param geneticFeatureSymbols
	 *            the geneticFeatureSymbols to set
	 */
	public void setGeneticFeatureSymbols(List<String> geneticFeatureSymbols) {

		this.geneticFeatureSymbols = geneticFeatureSymbols;
	}


	/**
	 * @return the genetifFeatureEnsemlIds
	 */
	public List<String> getGenetifFeatureEnsemlIds() {

		return genetifFeatureEnsemlIds;
	}


	/**
	 * @param genetifFeatureEnsemlIds
	 *            the genetifFeatureEnsemlIds to set
	 */
	public void setGenetifFeatureEnsemlIds(List<String> genetifFeatureEnsemlIds) {

		this.genetifFeatureEnsemlIds = genetifFeatureEnsemlIds;
	}


	/**
	 * @return the chromosome
	 */
	public List<String> getChromosome() {

		return chromosome;
	}


	/**
	 * @param chromosome
	 *            the chromosome to set
	 */
	public void setChromosome(List<String> chromosome) {

		this.chromosome = chromosome;
	}


	/**
	 * @return the startPosition
	 */
	public List<Long> getStartPosition() {

		return startPosition;
	}


	/**
	 * @param startPosition
	 *            the startPosition to set
	 */
	public void setStartPosition(List<Long> startPosition) {

		this.startPosition = startPosition;
	}


	/**
	 * @return the endPosition
	 */
	public List<Long> getEndPosition() {

		return endPosition;
	}


	/**
	 * @param endPosition
	 *            the endPosition to set
	 */
	public void setEndPosition(List<Long> endPosition) {

		this.endPosition = endPosition;
	}


	/**
	 * @return the strand
	 */
	public List<String> getStrand() {

		return strand;
	}


	/**
	 * @param strand
	 *            the strand to set
	 */
	public void setStrand(List<String> strand) {

		this.strand = strand;
	}


	/**
	 * @return the zygosity
	 */
	public List<String> getZygosity() {

		return zygosity;
	}


	/**
	 * @param zygosity
	 *            the zygosity to set
	 */
	public void setZygosity(List<String> zygosity) {

		this.zygosity = zygosity;
	}


	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "ImageDTO [id=" + id + ", associatedRoi=" + associatedRoi + ", associatedChannel=" + associatedChannel + ", height=" + height + ", hostUrl=" + hostUrl + ", hostName=" + hostName + ", imageGeneratedBy=" + imageGeneratedBy + ", imageType=" + imageType + ", sampleType=" + sampleType + ", imageUrl=" + imageUrl + ", imageContextUrl=" + imageContextUrl + ", imagingMethodId=" + imagingMethodId + ", imagingMethodLabel=" + imagingMethodLabel + ", imagingMethodSynonyms=" + imagingMethodSynonyms + ", samplePreparationId=" + samplePreparationId + ", samplePreparationLabel=" + samplePreparationLabel + ", samplePreparationSynonyms=" + samplePreparationSynonyms + ", visualisationMethodId=" + visualisationMethodId + ", visualisationMethodLabel=" + visualisationMethodLabel + ", visualisationMethodSynonyms=" + visualisationMethodSynonyms + ", machine=" + machine + ", thumbnailPath=" + thumbnailPath + ", width=" + width + ", genericSearch=" + genericSearch + ", ageSinceBirth=" + ageSinceBirth + ", sampleGeneratedBy=" + sampleGeneratedBy + ", taxon=" + taxon + ", ncbiTaxonId=" + ncbiTaxonId + ", sex=" + sex + ", stage=" + stage + ", stageId=" + stageId + ", embryonicAge=" + embryonicAge + ", anatomyId=" + anatomyId + ", anatomyTerm=" + anatomyTerm + ", anatomyFreetext=" + anatomyFreetext + ", anatomySynonyms=" + anatomySynonyms + ", observations=" + observations + ", conditions=" + conditions + ", geneIds=" + geneIds + ", geneSymbols=" + geneSymbols + ", geneSynonyms=" + geneSynonyms + ", geneticFeatureIds=" + geneticFeatureIds + ", geneticFeatureSymbols=" + geneticFeatureSymbols + ", geneticFeatureSynonyms=" + geneticFeatureSynonyms + ", genetifFeatureEnsemlIds=" + genetifFeatureEnsemlIds + ", chromosome=" + chromosome + ", startPosition=" + startPosition + ", endPosition=" + endPosition + ", strand=" + strand + ", zygosity=" + zygosity + ", depth=" + depth + ", anatomyComputedIdBag=" + anatomyComputedIdBag + ", anatomyComputedLabelBag=" + anatomyComputedLabelBag + ", anatomyComputedSynonymsBag=" + anatomyComputedSynonymsBag + ", anatomyComputedAncestorsIdBag=" + anatomyComputedAncestorsIdBag + ", anatomyComputedAncestorsSynonymsBag=" + anatomyComputedAncestorsSynonymsBag + ", anatomyComputedAncestorsTermBag=" + anatomyComputedAncestorsTermBag + ", depictedAnatomyIdBag=" + depictedAnatomyIdBag + ", depictedAnatomyTermBag=" + depictedAnatomyTermBag + ", depictedAnatomyFreetextBag=" + depictedAnatomyFreetextBag + ", depictedAnatomySynonymsBag=" + depictedAnatomySynonymsBag + ", depictedAnatomyAncestorsIdBag=" + depictedAnatomyAncestorsIdBag + ", depictedAnatomyAncestorsSynonymsBag=" + depictedAnatomyAncestorsSynonymsBag + ", depictedAnatomyAncestorsTermBag=" + depictedAnatomyAncestorsTermBag + ", abnormalAnatomyIdBag=" + abnormalAnatomyIdBag + ", abnormalAnatomyTermBag=" + abnormalAnatomyTermBag + ", abnormalAnatomyFreetextBag=" + abnormalAnatomyFreetextBag + ", abnormalAnatomySynonymsBag=" + abnormalAnatomySynonymsBag + ", abnormalAnatomyAncestorsIdBag=" + abnormalAnatomyAncestorsIdBag + ", abnormalAnatomyAncestorsSynonymsBag=" + abnormalAnatomyAncestorsSynonymsBag + ", abnormalAnatomyAncestorsTermBag=" + abnormalAnatomyAncestorsTermBag + ", expressedGfIdBag=" + expressedGfIdBag + ", expressedGfSymbolBag=" + expressedGfSymbolBag + ", expressedGfSynonymsBag=" + expressedGfSynonymsBag + ", expressionInIdBag=" + expressionInIdBag + ", expressionInLabelBag=" + expressionInLabelBag + ", expressionInFreetextBag=" + expressionInFreetextBag + ", expressionInSynonymsBag=" + expressionInSynonymsBag + ", expressionInAncestorsIdBag=" + expressionInAncestorsIdBag + ", expressionInAncestorsSynonymsBag=" + expressionInAncestorsSynonymsBag + ", expressionInAncestorsTermBag=" + expressionInAncestorsTermBag + ", mutantGeneIdBag=" + mutantGeneIdBag + ", mutantGeneSymbolBag=" + mutantGeneSymbolBag + ", mutantGeneSynonymsBag=" + mutantGeneSynonymsBag + ", observationBag=" + observationBag + ", phenotypeIdBag=" + phenotypeIdBag + ", phenotypeLabelBag=" + phenotypeLabelBag + ", phenotypeFreetextBag=" + phenotypeFreetextBag + ", phenotypeSynonymsBag=" + phenotypeSynonymsBag + ", phenotypeAncestorsIdBag=" + phenotypeAncestorsIdBag + ", phenotypeAncestorsSynonymsBag=" + phenotypeAncestorsSynonymsBag + ", phenotypeAncestorsTermBag=" + phenotypeAncestorsTermBag + "]";
	}

	/**
	 * @return the id
	 */
	public String getId() {

		return id;
	}


	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {

		this.id = id;
	}


	/**
	 * @return the anatomyComputedIdBag
	 */
	public ArrayList<String> getAnatomyComputedIdBag() {

		return anatomyComputedIdBag;
	}


	/**
	 * @param anatomyComputedIdBag
	 *            the anatomyComputedIdBag to set
	 */
	public void setAnatomyComputedIdBag(ArrayList<String> anatomyComputedIdBag) {

		this.anatomyComputedIdBag = anatomyComputedIdBag;
	}


	/**
	 * @return the anatomyComputedLabelBag
	 */
	public ArrayList<String> getAnatomyComputedLabelBag() {

		return anatomyComputedLabelBag;
	}


	/**
	 * @param anatomyComputedLabelBag
	 *            the anatomyComputedLabelBag to set
	 */
	public void setAnatomyComputedLabelBag(ArrayList<String> anatomyComputedLabelBag) {

		this.anatomyComputedLabelBag = anatomyComputedLabelBag;
	}


	/**
	 * @return the depictedAnatomyIdBag
	 */
	public ArrayList<String> getDepictedAnatomyIdBag() {

		return depictedAnatomyIdBag;
	}


	/**
	 * @param depictedAnatomyIdBag
	 *            the depictedAnatomyIdBag to set
	 */
	public void setDepictedAnatomyIdBag(ArrayList<String> depictedAnatomyIdBag) {

		this.depictedAnatomyIdBag = depictedAnatomyIdBag;
	}


	/**
	 * @return the depictedAnatomyTermBag
	 */
	public ArrayList<String> getDepictedAnatomyTermBag() {

		return depictedAnatomyTermBag;
	}


	/**
	 * @param depictedAnatomyTermBag
	 *            the depictedAnatomyTermBag to set
	 */
	public void setDepictedAnatomyTermBag(ArrayList<String> depictedAnatomyTermBag) {

		this.depictedAnatomyTermBag = depictedAnatomyTermBag;
	}


	/**
	 * @return the expressedGfIdBag
	 */
	public ArrayList<String> getExpressedGfIdBag() {

		return expressedGfIdBag;
	}


	/**
	 * @param expressedGfIdBag
	 *            the expressedGfIdBag to set
	 */
	public void setExpressedGfIdBag(ArrayList<String> expressedGfIdBag) {

		this.expressedGfIdBag = expressedGfIdBag;
	}


	/**
	 * @return the expressedGfSymbolBag
	 */
	public ArrayList<String> getExpressedGfSymbolBag() {

		return expressedGfSymbolBag;
	}


	/**
	 * @param expressedGfSymbolBag
	 *            the expressedGfSymbolBag to set
	 */
	public void setExpressedGfSymbolBag(ArrayList<String> expressedGfSymbolBag) {

		this.expressedGfSymbolBag = expressedGfSymbolBag;
	}


	/**
	 * @return the expressionInIdBag
	 */
	public ArrayList<String> getExpressionInIdBag() {

		return expressionInIdBag;
	}


	/**
	 * @param expressionInIdBag
	 *            the expressionInIdBag to set
	 */
	public void setExpressionInIdBag(ArrayList<String> expressionInIdBag) {

		this.expressionInIdBag = expressionInIdBag;
	}


	/**
	 * @return the expressionInLabelBag
	 */
	public ArrayList<String> getExpressionInLabelBag() {

		return expressionInLabelBag;
	}


	/**
	 * @param expressionInLabelBag
	 *            the expressionInLabelBag to set
	 */
	public void setExpressionInLabelBag(ArrayList<String> expressionInLabelBag) {

		expressionInLabelBag = expressionInLabelBag;
	}


	/**
	 * @return the mutantGeneIdBag
	 */
	public ArrayList<String> getMutantGeneIdBag() {

		return mutantGeneIdBag;
	}


	/**
	 * @param mutantGeneIdBag
	 *            the mutantGeneIdBag to set
	 */
	public void setMutantGeneIdBag(ArrayList<String> mutantGeneIdBag) {

		this.mutantGeneIdBag = mutantGeneIdBag;
	}


	/**
	 * @return the mutantGeneSymbolBag
	 */
	public ArrayList<String> getMutantGeneSymbolBag() {

		return mutantGeneSymbolBag;
	}


	/**
	 * @param mutantGeneSymbolBag
	 *            the mutantGeneSymbolBag to set
	 */
	public void setMutantGeneSymbolBag(ArrayList<String> mutantGeneSymbolBag) {

		this.mutantGeneSymbolBag = mutantGeneSymbolBag;
	}


	/**
	 * @return the observationBag
	 */
	public ArrayList<String> getObservationBag() {

		return observationBag;
	}


	/**
	 * @param observationBag
	 *            the observationBag to set
	 */
	public void setObservationBag(ArrayList<String> observationBag) {

		this.observationBag = observationBag;
	}


	/**
	 * @return the phenotypeIdBag
	 */
	public ArrayList<String> getPhenotypeIdBag() {

		return phenotypeIdBag;
	}


	/**
	 * @param phenotypeIdBag
	 *            the phenotypeIdBag to set
	 */
	public void setPhenotypeIdBag(ArrayList<String> phenotypeIdBag) {

		this.phenotypeIdBag = phenotypeIdBag;
	}

	public void addPhenotypeIdBag(List<String> phenotypeIdBag) {
		if (this.phenotypeIdBag == null){
			this.phenotypeIdBag = new ArrayList<>();
		}
		this.phenotypeIdBag.addAll(phenotypeIdBag);
	}

	public void addPhenotypeFreetextBag(List<String> phenotypeFreetextBag) {
		if (this.phenotypeFreetextBag == null){
			this.phenotypeFreetextBag = new ArrayList<>();
		}
		this.phenotypeFreetextBag.addAll(phenotypeFreetextBag);	
	}
	
	public void addDepictedAnatomyIdBag(List<String> depictedAnatomyIdBag){
		if (this.depictedAnatomyIdBag == null){
			this.depictedAnatomyIdBag = new ArrayList<>();
		}
		this.depictedAnatomyIdBag.addAll(depictedAnatomyIdBag);	
	}

	public void addDepictedAnatomyFreetextBag(List<String> depictedAnatomyFreetextBag){
		if (this.depictedAnatomyFreetextBag == null){
			this.depictedAnatomyFreetextBag = new ArrayList<>();
		}
		this.depictedAnatomyFreetextBag.addAll(depictedAnatomyFreetextBag);	
	}

	public void addObservationBag(List<String> observationBag) {
		if (this.observationBag == null){
			this.observationBag = new ArrayList<>();
		}
		this.observationBag.addAll(observationBag);
	}

	public void addExpressionInIdBag(List<String> expressionInIdBag) {
		if (this.expressionInIdBag == null){
			this.expressionInIdBag = new ArrayList<>();
		}
		this.expressionInIdBag.addAll(expressionInIdBag);
	}

	public void addExpressionInFreetextBag(List<String> ExpressionInFreetextBag) {
		if (this.expressionInFreetextBag == null){
			this.expressionInFreetextBag = new ArrayList<>();
		}
		this.expressionInFreetextBag.addAll(ExpressionInFreetextBag);
	}

	/**
	 * @return the phenotypelabelBag
	 */
	public ArrayList<String> getPhenotypeLabelBag() {

		return phenotypeLabelBag;
	}


	/**
	 * @param phenotypelabelBag
	 *            the phenotypelabelBag to set
	 */
	public void setPhenotypeLabelBag(ArrayList<String> phenotypelabelBag) {

		this.phenotypeLabelBag = phenotypelabelBag;
	}


	/**
	 * @return the depictedAnatomyFreetextBag
	 */
	public ArrayList<String> getDepictedAnatomyFreetextBag() {

		return depictedAnatomyFreetextBag;
	}


	/**
	 * @param depictedAnatomyFreetextBag
	 *            the depictedAnatomyFreetextBag to set
	 */
	public void setDepictedAnatomyFreetextBag(ArrayList<String> depictedAnatomyFreetextBag) {

		this.depictedAnatomyFreetextBag = depictedAnatomyFreetextBag;
	}


	/**
	 * @return the expressionInFreetextBag
	 */
	public ArrayList<String> getExpressionInFreetextBag() {

		return expressionInFreetextBag;
	}


	/**
	 * @param expressionInFreetextBag
	 *            the expressionInFreetextBag to set
	 */
	public void setExpressionInFreetextBag(ArrayList<String> expressionInFreetextBag) {

		expressionInFreetextBag = expressionInFreetextBag;
	}


	/**
	 * @return the phenotypeFreetextBag
	 */
	public ArrayList<String> getPhenotypeFreetextBag() {

		return phenotypeFreetextBag;
	}


	/**
	 * @param phenotypeFreetextBag
	 *            the phenotypeFreetextBag to set
	 */
	public void setPhenotypeFreetextBag(ArrayList<String> phenotypeFreetextBag) {

		this.phenotypeFreetextBag = phenotypeFreetextBag;
	}


	/**
	 * @return the abnormalAnatomyIdBag
	 */
	public ArrayList<String> getAbnormalAnatomyIdBag() {

		return abnormalAnatomyIdBag;
	}


	/**
	 * @param abnormalAnatomyIdBag
	 *            the abnormalAnatomyIdBag to set
	 */
	public void setAbnormalAnatomyIdBag(ArrayList<String> abnormalAnatomyIdBag) {

		this.abnormalAnatomyIdBag = abnormalAnatomyIdBag;
	}
	
	public void addAbnormalAnatomyIdBag(String abnormalAnatomyId) {
		if (this.abnormalAnatomyIdBag == null){
			this.abnormalAnatomyIdBag = new ArrayList<>();
		}
		
		this.abnormalAnatomyIdBag.add(abnormalAnatomyId);
	}

	/**
	 * @return the abnormalAnatomyTermBag
	 */
	public ArrayList<String> getAbnormalAnatomyTermBag() {

		return abnormalAnatomyTermBag;
	}


	/**
	 * @param abnormalAnatomyTermBag
	 *            the abnormalAnatomyTermBag to set
	 */
	public void setAbnormalAnatomyTermBag(ArrayList<String> abnormalAnatomyTermBag) {

		this.abnormalAnatomyTermBag = abnormalAnatomyTermBag;
	}


	/**
	 * @return the abnormalAnatomyFreetextBag
	 */
	public ArrayList<String> getAbnormalAnatomyFreetextBag() {

		return abnormalAnatomyFreetextBag;
	}

	public void addAbnormalAnatomyFreetextBag(String abnormalAnatomyFreetext) {
		if (this.abnormalAnatomyFreetextBag == null){
			this.abnormalAnatomyFreetextBag = new ArrayList<>();
		}
		
		this.abnormalAnatomyFreetextBag.add(abnormalAnatomyFreetext);
	}

	/**
	 * @param abnormalAnatomyFreetextBag
	 *            the abnormalAnatomyFreetextBag to set
	 */
	public void setAbnormalAnatomyFreetextBag(ArrayList<String> abnormalAnatomyFreetextBag) {

		this.abnormalAnatomyFreetextBag = abnormalAnatomyFreetextBag;
	}


	/**
	 * @return the imageType
	 */
	public ArrayList<String> getImageType() {

		return imageType;
	}


	/**
	 * @param imageType
	 *            the imageType to set
	 */
	public void setImageType(ArrayList<String> imageType) {

		this.imageType = imageType;
	}


	/**
	 * @return the sampleType
	 */
	public String getSampleType() {

		return sampleType;
	}


	/**
	 * @param sampleType
	 *            the sampleType to set
	 */
	public void setSampleType(String sampleType) {

		this.sampleType = sampleType;
	}

	
	
}

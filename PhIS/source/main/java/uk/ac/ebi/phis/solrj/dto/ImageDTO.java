package uk.ac.ebi.phis.solrj.dto;

import java.util.ArrayList;
import java.util.List;

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
	public final static String IMAGE_TYPE = "image_type";
	public final static String SAMPLE_TYPE = "sample_type";
	
	public final static String ORIGINAL_IMAGE_ID = "original_image_id";
	public final static String MACHINE = "machine";
	public final static String VISUALISATION_METHOD_LABEL = "visualisation_method_label";
	public final static String VISUALISATION_METHOD_ID = "visualisation_method_id";
	public final static String SAMPLE_PREPARATION_LABEL = "sample_preparation_label";
	public final static String SAMPLE_PREPARATION_ID = "sample_preparation_id";
	public final static String IMAGING_METHOD_ID = "imaging_method_id";
	public final static String IMAGING_METHOD_LABEL = "imaging_method_label";
	public final static String IMAGING_METHOD_LABEL_ANALYSED = "imaging_method_label_analysed";
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

	public final static String TERM_AUTOSUGGEST = "term_autosuggest";
	
	@Field(ID)
	private String id;

	@Field(ASSOCIATED_ROI)
	private List<String> associatedRoi;

	@Field(ASSOCIATED_CHANNEL)
	private List<String> associatedChannel;

	@Field(DEPTH)
	private Integer gepth;

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

	@Field(SAMPLE_PREPARATION_ID)
	private String samplePreparationId;

	@Field(VISUALISATION_METHOD_ID)
	private String visualisationMethodId;

	@Field(IMAGING_METHOD_LABEL)
	private String imagingMethodLabel;

	@Field(SAMPLE_PREPARATION_LABEL)
	private String samplePreparationLabel;

	@Field(VISUALISATION_METHOD_LABEL)
	private String visualisationMethodLabel;

	@Field(MACHINE)
	private String machine;

	@Field(THUMBNAIL_PATH)
	private String thumbnailPath;

	@Field(WIDTH)
	private Integer width;

	// Sample

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

	@Field(OBSERVATIONS)
	private List<String> observations;

	@Field(CONDITIONS)
	private List<String> conditions;

	@Field(GENE_ID)
	private List<String> geneIds;

	@Field(GENE_SYMBOL)
	private List<String> geneSymbols;

	@Field(GF_ID)
	private List<String> geneticFeatureIds;

	@Field(GF_SYMBOL)
	private List<String> geneticFeatureSymbols;

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
	private long depth;

	@Field(ANATOMY_COMPUTED_ID_BAG)
	private ArrayList<String> anatomyComputedIdBag;

	@Field(ANATOMY_COMPUTED_TERM_BAG)
	private ArrayList<String> anatomyComputedLabelBag;

	@Field(DEPICTED_ANATOMY_ID_BAG)
	private ArrayList<String> depictedAnatomyIdBag;

	@Field(DEPICTED_ANATOMY_TERM_BAG)
	private ArrayList<String> depictedAnatomyTermBag;

	@Field(DEPICTED_ANATOMY_FREETEXT_BAG)
	private ArrayList<String> depictedAnatomyFreetextBag;

	@Field(ABNORMAL_ANATOMY_ID_BAG)
	private ArrayList<String> abnormalAnatomyIdBag;

	@Field(ABNORMAL_ANATOMY_TERM_BAG)
	private ArrayList<String> abnormalAnatomyTermBag;

	@Field(ABNORMAL_ANATOMY_FREETEXT_BAG)
	private ArrayList<String> abnormalAnatomyFreetextBag;

	@Field(EXPRESSED_GF_ID_BAG)
	private ArrayList<String> expressedGfIdBag;

	@Field(EXPRESSED_GF_SYMBOL_BAG)
	private ArrayList<String> expressedGfSymbolBag;

	@Field(EXPRESSION_IN_ID_BAG)
	private ArrayList<String> expressionInIdBag;

	@Field(EXPRESSION_IN_LABEL_BAG)
	private ArrayList<String> ExpressionInLabelBag;

	@Field(EXPRESSION_IN_FREETEXT_BAG)
	private ArrayList<String> ExpressionInFreetextBag;

	@Field(MUTANT_GENE_ID_BAG)
	private ArrayList<String> mutantGeneIdBag;

	@Field(MUTANT_GENE_SYMBOL_BAG)
	private ArrayList<String> mutantGeneSymbolBag;

	@Field(OBSERVATION_BAG)
	private ArrayList<String> observationBag;

	@Field(PHENOTYPE_ID_BAG)
	private ArrayList<String> phenotypeIdBag;

	@Field(PHENOTYPE_LABEL_BAG)
	private ArrayList<String> phenotypeLabelBag;

	@Field(PHENOTYPE_FREETEXT_BAG)
	private ArrayList<String> phenotypeFreetextBag;


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
	public void setDepth(long depth) {

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
	 * @return the gepth
	 */
	public Integer getGepth() {

		return gepth;
	}


	/**
	 * @param gepth
	 *            the gepth to set
	 */
	public void setGepth(Integer gepth) {

		this.gepth = gepth;
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
	public String getVisualisationMethodId() {

		return visualisationMethodId;
	}


	/**
	 * @param visualisationMethodId
	 *            the visualisationMethodId to set
	 */
	public void setVisualisationMethodId(String visualisationMethodId) {

		this.visualisationMethodId = visualisationMethodId;
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
	public String getVisualisationMethodLabel() {

		return visualisationMethodLabel;
	}


	/**
	 * @param visualisationMethodLabel
	 *            the visualisationMethodLabel to set
	 */
	public void setVisualisationMethodLabel(String visualisationMethodLabel) {

		this.visualisationMethodLabel = visualisationMethodLabel;
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


	@Override
	public String toString() {

		return "ImagePojo []";
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

		return ExpressionInLabelBag;
	}


	/**
	 * @param expressionInLabelBag
	 *            the expressionInLabelBag to set
	 */
	public void setExpressionInLabelBag(ArrayList<String> expressionInLabelBag) {

		ExpressionInLabelBag = expressionInLabelBag;
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

		return ExpressionInFreetextBag;
	}


	/**
	 * @param expressionInFreetextBag
	 *            the expressionInFreetextBag to set
	 */
	public void setExpressionInFreetextBag(ArrayList<String> expressionInFreetextBag) {

		ExpressionInFreetextBag = expressionInFreetextBag;
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
	 * @param imageType the imageType to set
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
	 * @param sampleType the sampleType to set
	 */
	public void setSampleType(String sampleType) {
	
		this.sampleType = sampleType;
	}

}

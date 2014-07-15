package uk.ac.ebi.phis.solrj.pojo;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class ImagePojo {

	@Field("id")
	private String id;

	@Field("associated_roi")
	private String associatedRoi;

	@Field("associated_channel")
	private String associatedChannel;

	@Field("data_source")
	private String dataSource;

	@Field("depth")
	private Integer gepth;

	@Field("format")
	private String format;

	@Field("height")
	private Integer height;

	@Field("host_url")
	private String hostUrl;

	@Field("host_name")
	private String hostName;

	@Field("image_generated_by")
	private String imageGeneratedBy;

	@Field("image_url")
	private String imageUrl;

	@Field("image_context_url")
	private String imageContextUrl;

	@Field("image_type")
	private String imageType;

	@Field("imaging_method")
	private String imagingMethod;

	@Field("sample_preparation")
	private String samplePreparation;

	@Field("visualisation_method")
	private String visualisationMethod;

	@Field("machine")
	private String machine;

	@Field("original_image_id")
	private String originalImageId;

	@Field("thumbnail_path")
	private String thumbnailPath;

	@Field("width")
	private String width;

	// Sample

	@Field("age_since_birth")
	private String ageSinceBirth;

	@Field("sample_generated_by")
	private String sampleGeneratedBy;

	@Field("taxon")
	private String taxon;

	@Field("ncbi_taxon_id")
	private String ncbiTaxonId;

	@Field("sex")
	private String sex;

	@Field("stage")
	private String stage;

	@Field("embryonic_age")
	private String embryonicAge;

	// annotations -->

	@Field("anatomy_id")
	private String anatomyId;

	@Field("anatomy_term")
	private String anatomyTerm;

	@Field("anatomy_freetext")
	private String anatomyFreetext;

	// field name="anatomy_computed_id" /-->
	// field name="anatomy_computed_term" /-->
	// field name="anatomy_ann_bag" /-->
	// field name="other_ann_bag" /-->
	// field name="phenotype_ann_bag" /-->

	@Field("observations")
	private List<String> observations;

	@Field("conditions")
	private List<String> conditions;

	// genetic features -->

	@Field("gene_id")
	private List<String> geneIds;

	@Field("gene_symbol")
	private List<String> geneSymbols;

	@Field("genetic_feature_id")
	private List<String> geneticFeatureIds;

	@Field("genetic_feature_symbol")
	private List<String> geneticFeatureSymbols;

	@Field("genetic_feature_ensembl_id")
	private List<String> genetifFeatureEnsemlIds;

	// field name="expressed_gf_bag" /-->
	// field name="expressed_anatomy_bag" /-->

	@Field("chromosome")
	private List<String> chromosome;

	@Field("insertion_site")
	private List<Long> insertionSite;

	@Field("start_pos")
	private List<Long> startPosition;

	@Field("end_pos")
	private List<Long> endPosition;

	@Field("strand")
	private List<String> strand;

	@Field("zygosity")
	private List<String> zygosity;


	/**
	 * @return the associatedRoi
	 */
	public String getAssociatedRoi() {

		return associatedRoi;
	}


	/**
	 * @param associatedRoi
	 *            the associatedRoi to set
	 */
	public void setAssociatedRoi(String associatedRoi) {

		this.associatedRoi = associatedRoi;
	}


	/**
	 * @return the associatedChannel
	 */
	public String getAssociatedChannel() {

		return associatedChannel;
	}


	/**
	 * @param associatedChannel
	 *            the associatedChannel to set
	 */
	public void setAssociatedChannel(String associatedChannel) {

		this.associatedChannel = associatedChannel;
	}


	/**
	 * @return the dataSource
	 */
	public String getDataSource() {

		return dataSource;
	}


	/**
	 * @param dataSource
	 *            the dataSource to set
	 */
	public void setDataSource(String dataSource) {

		this.dataSource = dataSource;
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
	 * @return the format
	 */
	public String getFormat() {

		return format;
	}


	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {

		this.format = format;
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
	 * @return the imageType
	 */
	public String getImageType() {

		return imageType;
	}


	/**
	 * @param imageType
	 *            the imageType to set
	 */
	public void setImageType(String imageType) {

		this.imageType = imageType;
	}


	/**
	 * @return the imagingMethod
	 */
	public String getImagingMethod() {

		return imagingMethod;
	}


	/**
	 * @param imagingMethod
	 *            the imagingMethod to set
	 */
	public void setImagingMethod(String imagingMethod) {

		this.imagingMethod = imagingMethod;
	}


	/**
	 * @return the samplePreparation
	 */
	public String getSamplePreparation() {

		return samplePreparation;
	}


	/**
	 * @param samplePreparation
	 *            the samplePreparation to set
	 */
	public void setSamplePreparation(String samplePreparation) {

		this.samplePreparation = samplePreparation;
	}


	/**
	 * @return the visualisationMethod
	 */
	public String getVisualisationMethod() {

		return visualisationMethod;
	}


	/**
	 * @param visualisationMethod
	 *            the visualisationMethod to set
	 */
	public void setVisualisationMethod(String visualisationMethod) {

		this.visualisationMethod = visualisationMethod;
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
	 * @return the originalImageId
	 */
	public String getOriginalImageId() {

		return originalImageId;
	}


	/**
	 * @param originalImageId
	 *            the originalImageId to set
	 */
	public void setOriginalImageId(String originalImageId) {

		this.originalImageId = originalImageId;
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
	public String getWidth() {

		return width;
	}


	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(String width) {

		this.width = width;
	}


	/**
	 * @return the ageSinceBirth
	 */
	public String getAgeSinceBirth() {

		return ageSinceBirth;
	}


	/**
	 * @param ageSinceBirth
	 *            the ageSinceBirth to set
	 */
	public void setAgeSinceBirth(String ageSinceBirth) {

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
	public String getEmbryonicAge() {

		return embryonicAge;
	}


	/**
	 * @param embryonicAge
	 *            the embryonicAge to set
	 */
	public void setEmbryonicAge(String embryonicAge) {

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
	 * @return the insertionSite
	 */
	public List<Long> getInsertionSite() {

		return insertionSite;
	}


	/**
	 * @param insertionSite
	 *            the insertionSite to set
	 */
	public void setInsertionSite(List<Long> insertionSite) {

		this.insertionSite = insertionSite;
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
	 * @param id the id to set
	 */
	public void setId(String id) {
	
		this.id = id;
	}

	
	
}

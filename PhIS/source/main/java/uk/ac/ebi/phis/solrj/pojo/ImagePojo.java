package uk.ac.ebi.phis.solrj.pojo;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class ImagePojo {

	@Field("id")
	private String id;

	@Field("associated_roi")
	private List<String> associatedRoi;

	@Field("associated_channel")
	private List<String> associatedChannel;

	@Field("depth")
	private Integer gepth;

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

	@Field("imaging_method_id")
	private String imagingMethodId;

	@Field("sample_preparation_id")
	private String samplePreparationId;

	@Field("visualisation_method_id")
	private String visualisationMethodId;

	@Field("imaging_method_label")
	private String imagingMethodLabel;

	@Field("sample_preparation_label")
	private String samplePreparationLabel;

	@Field("visualisation_method_label")
	private String visualisationMethodLabel;

	@Field("machine")
	private String machine;

	@Field("original_image_id")
	private String originalImageId;

	@Field("thumbnail_path")
	private String thumbnailPath;

	@Field("width")
	private Integer width;

	// Sample

	@Field("age_since_birth")
	private Float ageSinceBirth;

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

	@Field("stage_id")
	private String stageId;

	@Field("embryonic_age")
	private Float embryonicAge;

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

	@Field("depth")
	private long depth;


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
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {

		this.id = id;
	}

}

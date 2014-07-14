package uk.ac.ebi.phis.solrj.pojo;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class ImagePojo {

	@Field("associated_roi" )
	private static String associatedRoi;
	
	@Field("associated_channel")  
	private static String associatedChannel;
	
	@Field("data_source")
	private static String dataSource;
	
	@Field("depth" ) 
	private static Integer gepth ;
	
	@Field("format")
	private static String format;
	
	@Field("height" )  
	private static Integer height ;
	
	@Field("host_url" )
	private static String hostUrl;
	
	@Field("host_name" ) 
	private static String hostName;
	
	@Field("image_generated_by")
	private static String imageGeneratedBy;
	
	@Field("image_url" )
	private static String imageUrl;
	
	@Field("image_context_url")
	private static String imageContextUrl;
	
	@Field("image_type" )
	private static String imageType;
	
	@Field("imaging_method" )
	private static String imagingMethod;
	
	@Field("sample_preparation" )
	private static String samplePreparation;
	
	@Field("visualisation_method" )
	private static String visualisationMethod;
	
	@Field("machine")
	private static String machine;
	
	@Field("original_image_id")
	private static String originalImageId;
	
	@Field("thumbnail_path")
	private static String thumbnailPath;
	
	@Field("width")  
	private static String width;
	
	// Sample 
	
	@Field("age_since_birth")  
	private static String ageSinceBirth;
	
	@Field("sample_generated_by")
	private static String sampleGeneratedBy;
	
	@Field("taxon")
	private static String taxon;
	
	@Field("ncbi_taxon_id")  
	private static String ncbiTaxonId;
	
	@Field("sex")
	private static String sex;
	
	@Field("stage")   
	private static String stage;
	
	@Field("embryonic_age")  
	private static String embryonicAge;
	
	// annotations -->
	
	@Field("anatomy_id")
	private static String anatomyId;
	
	@Field("anatomy_term")   
	private static String anatomyTerm;
	
	@Field("anatomy_freetext")   
	private static String anatomyFreetext;
	
	// field name="anatomy_computed_id"   /-->
	// field name="anatomy_computed_term"   /-->
	// field name="anatomy_ann_bag"   /-->
	// field name="other_ann_bag"   /-->
	// field name="phenotype_ann_bag"   /-->
	
	@Field("observations")
	private static List<String> observations;
	
	@Field("conditions")
	private static List<String> conditions;
	
	// genetic features -->
	
	@Field("gene_id")
	private static List<String> geneIds;
	
	@Field("gene_symbol")  
	private static List<String> geneSymbols;
	
	@Field("genetic_feature_id")   
	private static List<String> geneticFeatureIds ;
	
	@Field("genetic_feature_symbol")   
	private static List<String> geneticFeatureSymbols ;
	
	@Field("genetic_feature_ensembl_id")  
	private static List<String> genetifFeatureEnsemlIds ;
		
	// field name="expressed_gf_bag"   /-->
	// field name="expressed_anatomy_bag"   /-->
	
	@Field("chromosome")
	private static List<String> chromosome ;
	
	@Field("insertion_site") 
	private static List<Long> insertionSite ;
	
	@Field("start_pos")
	private static List<Long>  startPosition;
	
	@Field("end_pos")
	private static List<Long> endPosition ;
	
	@Field("strand")
	private static List<String> strand ;
	
	@Field("zygosity")
	private static List<String> zygosity ;

	
	
	/**
	 * @return the associatedRoi
	 */
	public static String getAssociatedRoi() {
		return associatedRoi;
	}




	/**
	 * @param associatedRoi the associatedRoi to set
	 */
	public static void setAssociatedRoi(String associatedRoi) {
		ImagePojo.associatedRoi = associatedRoi;
	}




	/**
	 * @return the associatedChannel
	 */
	public static String getAssociatedChannel() {
		return associatedChannel;
	}




	/**
	 * @param associatedChannel the associatedChannel to set
	 */
	public static void setAssociatedChannel(String associatedChannel) {
		ImagePojo.associatedChannel = associatedChannel;
	}




	/**
	 * @return the dataSource
	 */
	public static String getDataSource() {
		return dataSource;
	}




	/**
	 * @param dataSource the dataSource to set
	 */
	public static void setDataSource(String dataSource) {
		ImagePojo.dataSource = dataSource;
	}




	/**
	 * @return the gepth
	 */
	public static Integer getGepth() {
		return gepth;
	}




	/**
	 * @param gepth the gepth to set
	 */
	public static void setGepth(Integer gepth) {
		ImagePojo.gepth = gepth;
	}




	/**
	 * @return the format
	 */
	public static String getFormat() {
		return format;
	}




	/**
	 * @param format the format to set
	 */
	public static void setFormat(String format) {
		ImagePojo.format = format;
	}




	/**
	 * @return the height
	 */
	public static Integer getHeight() {
		return height;
	}




	/**
	 * @param height the height to set
	 */
	public static void setHeight(Integer height) {
		ImagePojo.height = height;
	}




	/**
	 * @return the hostUrl
	 */
	public static String getHostUrl() {
		return hostUrl;
	}




	/**
	 * @param hostUrl the hostUrl to set
	 */
	public static void setHostUrl(String hostUrl) {
		ImagePojo.hostUrl = hostUrl;
	}




	/**
	 * @return the hostName
	 */
	public static String getHostName() {
		return hostName;
	}




	/**
	 * @param hostName the hostName to set
	 */
	public static void setHostName(String hostName) {
		ImagePojo.hostName = hostName;
	}




	/**
	 * @return the imageGeneratedBy
	 */
	public static String getImageGeneratedBy() {
		return imageGeneratedBy;
	}




	/**
	 * @param imageGeneratedBy the imageGeneratedBy to set
	 */
	public static void setImageGeneratedBy(String imageGeneratedBy) {
		ImagePojo.imageGeneratedBy = imageGeneratedBy;
	}




	/**
	 * @return the imageUrl
	 */
	public static String getImageUrl() {
		return imageUrl;
	}




	/**
	 * @param imageUrl the imageUrl to set
	 */
	public static void setImageUrl(String imageUrl) {
		ImagePojo.imageUrl = imageUrl;
	}




	/**
	 * @return the imageContextUrl
	 */
	public static String getImageContextUrl() {
		return imageContextUrl;
	}




	/**
	 * @param imageContextUrl the imageContextUrl to set
	 */
	public static void setImageContextUrl(String imageContextUrl) {
		ImagePojo.imageContextUrl = imageContextUrl;
	}




	/**
	 * @return the imageType
	 */
	public static String getImageType() {
		return imageType;
	}




	/**
	 * @param imageType the imageType to set
	 */
	public static void setImageType(String imageType) {
		ImagePojo.imageType = imageType;
	}




	/**
	 * @return the imagingMethod
	 */
	public static String getImagingMethod() {
		return imagingMethod;
	}




	/**
	 * @param imagingMethod the imagingMethod to set
	 */
	public static void setImagingMethod(String imagingMethod) {
		ImagePojo.imagingMethod = imagingMethod;
	}




	/**
	 * @return the samplePreparation
	 */
	public static String getSamplePreparation() {
		return samplePreparation;
	}




	/**
	 * @param samplePreparation the samplePreparation to set
	 */
	public static void setSamplePreparation(String samplePreparation) {
		ImagePojo.samplePreparation = samplePreparation;
	}




	/**
	 * @return the visualisationMethod
	 */
	public static String getVisualisationMethod() {
		return visualisationMethod;
	}




	/**
	 * @param visualisationMethod the visualisationMethod to set
	 */
	public static void setVisualisationMethod(String visualisationMethod) {
		ImagePojo.visualisationMethod = visualisationMethod;
	}




	/**
	 * @return the machine
	 */
	public static String getMachine() {
		return machine;
	}




	/**
	 * @param machine the machine to set
	 */
	public static void setMachine(String machine) {
		ImagePojo.machine = machine;
	}




	/**
	 * @return the originalImageId
	 */
	public static String getOriginalImageId() {
		return originalImageId;
	}




	/**
	 * @param originalImageId the originalImageId to set
	 */
	public static void setOriginalImageId(String originalImageId) {
		ImagePojo.originalImageId = originalImageId;
	}




	/**
	 * @return the thumbnailPath
	 */
	public static String getThumbnailPath() {
		return thumbnailPath;
	}




	/**
	 * @param thumbnailPath the thumbnailPath to set
	 */
	public static void setThumbnailPath(String thumbnailPath) {
		ImagePojo.thumbnailPath = thumbnailPath;
	}




	/**
	 * @return the width
	 */
	public static String getWidth() {
		return width;
	}




	/**
	 * @param width the width to set
	 */
	public static void setWidth(String width) {
		ImagePojo.width = width;
	}




	/**
	 * @return the ageSinceBirth
	 */
	public static String getAgeSinceBirth() {
		return ageSinceBirth;
	}




	/**
	 * @param ageSinceBirth the ageSinceBirth to set
	 */
	public static void setAgeSinceBirth(String ageSinceBirth) {
		ImagePojo.ageSinceBirth = ageSinceBirth;
	}




	/**
	 * @return the sampleGeneratedBy
	 */
	public static String getSampleGeneratedBy() {
		return sampleGeneratedBy;
	}




	/**
	 * @param sampleGeneratedBy the sampleGeneratedBy to set
	 */
	public static void setSampleGeneratedBy(String sampleGeneratedBy) {
		ImagePojo.sampleGeneratedBy = sampleGeneratedBy;
	}




	/**
	 * @return the taxon
	 */
	public static String getTaxon() {
		return taxon;
	}




	/**
	 * @param taxon the taxon to set
	 */
	public static void setTaxon(String taxon) {
		ImagePojo.taxon = taxon;
	}




	/**
	 * @return the ncbiTaxonId
	 */
	public static String getNcbiTaxonId() {
		return ncbiTaxonId;
	}




	/**
	 * @param ncbiTaxonId the ncbiTaxonId to set
	 */
	public static void setNcbiTaxonId(String ncbiTaxonId) {
		ImagePojo.ncbiTaxonId = ncbiTaxonId;
	}




	/**
	 * @return the sex
	 */
	public static String getSex() {
		return sex;
	}




	/**
	 * @param sex the sex to set
	 */
	public static void setSex(String sex) {
		ImagePojo.sex = sex;
	}




	/**
	 * @return the stage
	 */
	public static String getStage() {
		return stage;
	}




	/**
	 * @param stage the stage to set
	 */
	public static void setStage(String stage) {
		ImagePojo.stage = stage;
	}




	/**
	 * @return the embryonicAge
	 */
	public static String getEmbryonicAge() {
		return embryonicAge;
	}




	/**
	 * @param embryonicAge the embryonicAge to set
	 */
	public static void setEmbryonicAge(String embryonicAge) {
		ImagePojo.embryonicAge = embryonicAge;
	}




	/**
	 * @return the anatomyId
	 */
	public static String getAnatomyId() {
		return anatomyId;
	}




	/**
	 * @param anatomyId the anatomyId to set
	 */
	public static void setAnatomyId(String anatomyId) {
		ImagePojo.anatomyId = anatomyId;
	}




	/**
	 * @return the anatomyTerm
	 */
	public static String getAnatomyTerm() {
		return anatomyTerm;
	}




	/**
	 * @param anatomyTerm the anatomyTerm to set
	 */
	public static void setAnatomyTerm(String anatomyTerm) {
		ImagePojo.anatomyTerm = anatomyTerm;
	}




	/**
	 * @return the anatomyFreetext
	 */
	public static String getAnatomyFreetext() {
		return anatomyFreetext;
	}




	/**
	 * @param anatomyFreetext the anatomyFreetext to set
	 */
	public static void setAnatomyFreetext(String anatomyFreetext) {
		ImagePojo.anatomyFreetext = anatomyFreetext;
	}




	/**
	 * @return the observations
	 */
	public static List<String> getObservations() {
		return observations;
	}




	/**
	 * @param observations the observations to set
	 */
	public static void setObservations(List<String> observations) {
		ImagePojo.observations = observations;
	}




	/**
	 * @return the conditions
	 */
	public static List<String> getConditions() {
		return conditions;
	}




	/**
	 * @param conditions the conditions to set
	 */
	public static void setConditions(List<String> conditions) {
		ImagePojo.conditions = conditions;
	}




	/**
	 * @return the geneIds
	 */
	public static List<String> getGeneIds() {
		return geneIds;
	}




	/**
	 * @param geneIds the geneIds to set
	 */
	public static void setGeneIds(List<String> geneIds) {
		ImagePojo.geneIds = geneIds;
	}




	/**
	 * @return the geneSymbols
	 */
	public static List<String> getGeneSymbols() {
		return geneSymbols;
	}




	/**
	 * @param geneSymbols the geneSymbols to set
	 */
	public static void setGeneSymbols(List<String> geneSymbols) {
		ImagePojo.geneSymbols = geneSymbols;
	}




	/**
	 * @return the geneticFeatureIds
	 */
	public static List<String> getGeneticFeatureIds() {
		return geneticFeatureIds;
	}




	/**
	 * @param geneticFeatureIds the geneticFeatureIds to set
	 */
	public static void setGeneticFeatureIds(List<String> geneticFeatureIds) {
		ImagePojo.geneticFeatureIds = geneticFeatureIds;
	}




	/**
	 * @return the geneticFeatureSymbols
	 */
	public static List<String> getGeneticFeatureSymbols() {
		return geneticFeatureSymbols;
	}




	/**
	 * @param geneticFeatureSymbols the geneticFeatureSymbols to set
	 */
	public static void setGeneticFeatureSymbols(List<String> geneticFeatureSymbols) {
		ImagePojo.geneticFeatureSymbols = geneticFeatureSymbols;
	}




	/**
	 * @return the genetifFeatureEnsemlIds
	 */
	public static List<String> getGenetifFeatureEnsemlIds() {
		return genetifFeatureEnsemlIds;
	}




	/**
	 * @param genetifFeatureEnsemlIds the genetifFeatureEnsemlIds to set
	 */
	public static void setGenetifFeatureEnsemlIds(
			List<String> genetifFeatureEnsemlIds) {
		ImagePojo.genetifFeatureEnsemlIds = genetifFeatureEnsemlIds;
	}




	/**
	 * @return the chromosome
	 */
	public static List<String> getChromosome() {
		return chromosome;
	}




	/**
	 * @param chromosome the chromosome to set
	 */
	public static void setChromosome(List<String> chromosome) {
		ImagePojo.chromosome = chromosome;
	}




	/**
	 * @return the insertionSite
	 */
	public static List<Long> getInsertionSite() {
		return insertionSite;
	}




	/**
	 * @param insertionSite the insertionSite to set
	 */
	public static void setInsertionSite(List<Long> insertionSite) {
		ImagePojo.insertionSite = insertionSite;
	}




	/**
	 * @return the startPosition
	 */
	public static List<Long> getStartPosition() {
		return startPosition;
	}




	/**
	 * @param startPosition the startPosition to set
	 */
	public static void setStartPosition(List<Long> startPosition) {
		ImagePojo.startPosition = startPosition;
	}




	/**
	 * @return the endPosition
	 */
	public static List<Long> getEndPosition() {
		return endPosition;
	}




	/**
	 * @param endPosition the endPosition to set
	 */
	public static void setEndPosition(List<Long> endPosition) {
		ImagePojo.endPosition = endPosition;
	}




	/**
	 * @return the strand
	 */
	public static List<String> getStrand() {
		return strand;
	}




	/**
	 * @param strand the strand to set
	 */
	public static void setStrand(List<String> strand) {
		ImagePojo.strand = strand;
	}




	/**
	 * @return the zygosity
	 */
	public static List<String> getZygosity() {
		return zygosity;
	}




	/**
	 * @param zygosity the zygosity to set
	 */
	public static void setZygosity(List<String> zygosity) {
		ImagePojo.zygosity = zygosity;
	}




	@Override
	public String toString() {
		return "ImagePojo []";
	}
	
	

	
	
	
}

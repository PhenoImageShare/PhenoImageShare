package uk.ac.ebi.phis.solrj.dto;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

import uk.ac.ebi.phis.service.ChannelService;


public class ChannelPojo {

	

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

	
	
	@Field(ChannelPojo.ID)
	String id;
	@Field(ChannelPojo.ASSOCIATED_IMAGE)
	String associatedImage;
	@Field(ChannelPojo.ASSOCIATED_ROI)
	List<String> associatedRoi;
	@Field(ChannelPojo.CHROMOSOME)
	String chromosome;
	@Field(ChannelPojo.END_POS)
	long endPos;
	@Field(ChannelPojo.GENE_ID)
	String geneId;
	@Field(ChannelPojo.GENE_SYMBOL)
	String geneSymbol;
	@Field(ChannelPojo.GENETIC_FEATURE_ENSEML_ID)
	String geneticFeatureEnsemlId;
	@Field(ChannelPojo.GENETIC_FEATURE_ID)
	String geneticFeatureId;
	@Field(ChannelPojo.GENETIC_FEATURE_SYMBOL)
	String geneticFeatureSymbol;
	@Field(ChannelPojo.MARKER)
	String marker;
	@Field(ChannelPojo.START_POS)
	long startPos;
	@Field(ChannelPojo.STRAND)
	String strand;
	@Field(ChannelPojo.ZYGOSITY)
	String zygosity;
	
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
	
	/**
	 * @return the associatedImage
	 */
	public String getAssociatedImage() {
	
		return associatedImage;
	}
	
	/**
	 * @param associatedImage the associatedImage to set
	 */
	public void setAssociatedImage(String associatedImage) {
	
		this.associatedImage = associatedImage;
	}
	
	/**
	 * @return the associatedRoi
	 */
	public List<String> getAssociatedRoi() {
	
		return associatedRoi;
	}
	
	/**
	 * @param associatedRoi the associatedRoi to set
	 */
	public void setAssociatedRoi(List<String> associatedRoi) {
	
		this.associatedRoi = associatedRoi;
	}
	
	/**
	 * @return the chromosome
	 */
	public String getChromosome() {
	
		return chromosome;
	}
	
	/**
	 * @param chromosome the chromosome to set
	 */
	public void setChromosome(String chromosome) {
	
		this.chromosome = chromosome;
	}
	
	/**
	 * @return the endPos
	 */
	public long getEndPos() {
	
		return endPos;
	}
	
	/**
	 * @param endPos the endPos to set
	 */
	public void setEndPos(long endPos) {
	
		this.endPos = endPos;
	}
	
	/**
	 * @return the geneId
	 */
	public String getGeneId() {
	
		return geneId;
	}
	
	/**
	 * @param geneId the geneId to set
	 */
	public void setGeneId(String geneId) {
	
		this.geneId = geneId;
	}
	
	/**
	 * @return the geneSymbol
	 */
	public String getGeneSymbol() {
	
		return geneSymbol;
	}
	
	/**
	 * @param geneSymbol the geneSymbol to set
	 */
	public void setGeneSymbol(String geneSymbol) {
	
		this.geneSymbol = geneSymbol;
	}
	
	/**
	 * @return the geneticFeatureEnsemlId
	 */
	public String getGeneticFeatureEnsemlId() {
	
		return geneticFeatureEnsemlId;
	}
	
	/**
	 * @param geneticFeatureEnsemlId the geneticFeatureEnsemlId to set
	 */
	public void setGeneticFeatureEnsemlId(String geneticFeatureEnsemlId) {
	
		this.geneticFeatureEnsemlId = geneticFeatureEnsemlId;
	}
	
	/**
	 * @return the geneticFeatureId
	 */
	public String getGeneticFeatureId() {
	
		return geneticFeatureId;
	}
	
	/**
	 * @param geneticFeatureId the geneticFeatureId to set
	 */
	public void setGeneticFeatureId(String geneticFeatureId) {
	
		this.geneticFeatureId = geneticFeatureId;
	}
	
	/**
	 * @return the geneticFeatureSymbol
	 */
	public String getGeneticFeatureSymbol() {
	
		return geneticFeatureSymbol;
	}
	
	/**
	 * @param geneticFeatureSymbol the geneticFeatureSymbol to set
	 */
	public void setGeneticFeatureSymbol(String geneticFeatureSymbol) {
	
		this.geneticFeatureSymbol = geneticFeatureSymbol;
	}
	
	/**
	 * @return the marker
	 */
	public String getMarker() {
	
		return marker;
	}
	
	/**
	 * @param marker the marker to set
	 */
	public void setMarker(String marker) {
	
		this.marker = marker;
	}
	
	/**
	 * @return the startPos
	 */
	public long getStartPos() {
	
		return startPos;
	}
	
	/**
	 * @param startPos the startPos to set
	 */
	public void setStartPos(long startPos) {
	
		this.startPos = startPos;
	}
	
	/**
	 * @return the strand
	 */
	public String getStrand() {
	
		return strand;
	}
	
	/**
	 * @param strand the strand to set
	 */
	public void setStrand(String strand) {
	
		this.strand = strand;
	}
	
	/**
	 * @return the zygosity
	 */
	public String getZygosity() {
	
		return zygosity;
	}
	
	/**
	 * @param zygosity the zygosity to set
	 */
	public void setZygosity(String zygosity) {
	
		this.zygosity = zygosity;
	}
	
	
	
}

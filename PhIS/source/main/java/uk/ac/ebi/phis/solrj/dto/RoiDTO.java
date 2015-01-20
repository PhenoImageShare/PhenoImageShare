package uk.ac.ebi.phis.solrj.dto;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.web.bind.annotation.RequestParam;

public class RoiDTO {
	
	
	public static final String  ID="id";
	public static final String  ASSOCIATED_IMAGE_ID = "associated_image";
	public static final String  ASSOCIATED_CHANNEL = "associated_channel";
	public static final String  EXPRESSED_ANATOMY_ID = "expressed_anatomy_id";
	public static final String  EXPRESSED_ANATOMY_FREETEXT = "expressed_anatomy_freetext";
	public static final String  EXPRESSED_ANATOMY_TERM = "expressed_anatomy_term";
	public static final String  DEPICTED_ANATOMY_ID = "depicted_anatomy_id";
	public static final String  DEPICTED_ANATOMY_TERM = "depicted_anatomy_term";
	public static final String  DEPICTED_ANATOMY_FREETEXT = "depicted_anatomy_freetext";
	public static final String  COMPUTED_DEPICTED_ANATOMY_ID = "computed_depicted_anatomy_id";
	public static final String  COMPUTED_DEPICTED_ANATOMY_TERM = "computed_depicted_anatomy_term";
	public static final String  DEPICTED_ANATOMY_ANNOTATION_SOURCE = "depicted_anatomy_annotation_source";
	public static final String  ABNORMALITY_ANATOMY_ID = "abnormality_anatomy_id";
	public static final String  ABNORMALITY_ANATOMY_TERM = "abnormality_anatomy_term";
	public static final String  COMPUTED_ABNORMALITY_ANATOMY_ID = "computed_abnormality_anatomy_id";
	public static final String  COMPUTED_ABNORMALITY_ANATOMY_TERM = "computed_abnormality_anatomy_term";
	public static final String  ABNORMALITY_ANATOMY_FREETEXT = "abnormality_anatomy_freetext";
	public static final String  ABNOMALITY_ANATOMY_ANNOTATION_SOURCE = "abnormality_anatomy_annotation_source";
	public static final String  PHENOTYPE_ID = "phenotype_id";
	public static final String  PHENOTYPE_TERM = "phenotype_term";
	public static final String  PHENOTYPE_FREETEXT = "phenotype_freetext";
	public static final String  OBSERVATIONS = "observations";
	public static final String  X_COORDINATES = "x_coordinates";
	public static final String  Y_COORDINATES = "y_coordinates";
	public static final String  Z_COORDINATES = "z_coordinates";
	
	@Field(ID)
	String id;

	@Field(ASSOCIATED_CHANNEL)
	List<String> associatedChannel;

	@Field(ASSOCIATED_IMAGE_ID)
	String associatedImage;

	@Field(EXPRESSED_ANATOMY_ID)
	List<String> expressedAnatomyId;

	@Field(EXPRESSED_ANATOMY_FREETEXT)
	List<String> expressedAnatomyFreetext;

	@Field(EXPRESSED_ANATOMY_TERM)
	List<String> expressedAnatomyTerm;
	
	@Field(DEPICTED_ANATOMY_ID)
	List<String> depictedAnatomyId;

	@Field(DEPICTED_ANATOMY_TERM)
	List<String> depictedAnatomyTerm;
	
	@Field(COMPUTED_DEPICTED_ANATOMY_ID)
	List<String> computedDepictedAnatomyId;

	@Field(COMPUTED_DEPICTED_ANATOMY_TERM)
	List<String> computedDepictedAnatomyTerm;

	@Field(DEPICTED_ANATOMY_FREETEXT)
	List<String> depictedAnatomyFreetext;

	@Field(DEPICTED_ANATOMY_ANNOTATION_SOURCE)
	List<String> depictedAnatomyAnnotationSource;

	@Field(ABNORMALITY_ANATOMY_ID)
	List<String> abnormalityAnatomyId;

	@Field(ABNORMALITY_ANATOMY_TERM)
	List<String> abnormalityAnatomyTerm;
	
	@Field(COMPUTED_ABNORMALITY_ANATOMY_ID)
	List<String> computedAbnormalityAnatomyId;

	@Field(COMPUTED_ABNORMALITY_ANATOMY_TERM)
	List<String> computedAbnormalityAnatomyTerm;

	@Field(ABNORMALITY_ANATOMY_FREETEXT)
	List<String> abnormalityAnatomyFreetext;

	@Field(ABNOMALITY_ANATOMY_ANNOTATION_SOURCE)
	List<String> abnormalityAnatomyAnnotationSource;

	@Field(PHENOTYPE_ID)
	List<String> phenotypeId;

	@Field(PHENOTYPE_TERM)
	List<String> phenotypeTerm;

	@Field(PHENOTYPE_FREETEXT)
	List<String> phenotypeFreetext;

	@Field(OBSERVATIONS)
	List<String> observations;

	@Field(X_COORDINATES)
	List<Float> xCoordinates;

	@Field(Y_COORDINATES)
	List<Float> yCoordinates;

	@Field(Z_COORDINATES)
	List<Float> zCoordinates;

	
	
	public RoiDTO(){
		
	}
	
	public RoiDTO(String id, List<String> associatedChannel, String associatedImage, List<String> depictedAnatomyId, List<String> depictedAnatomyTerm, List<String> depictedAnatomyFreetext, List<String> depictedAnatomyAnnotationSource, List<String> abnormalityAnatomyId, List<String> abnormalityAnatomyTerm, List<String> abnormalityAnatomyFreetext, List<String> abnormalityAnatomyAnnotationSource, List<String> phenotypeId, List<String> phenotypeTerm, List<String> phenotypeFreetext, List<String> observations, List<Float> xCoordinates, List<Float> yCoordinates, List<Float> zCoordinates) {

		super();
		this.id = id;
		this.associatedChannel = associatedChannel;
		this.associatedImage = associatedImage;
		this.depictedAnatomyId = depictedAnatomyId;
		this.depictedAnatomyTerm = depictedAnatomyTerm;
		this.depictedAnatomyFreetext = depictedAnatomyFreetext;
		this.depictedAnatomyAnnotationSource = depictedAnatomyAnnotationSource;
		this.abnormalityAnatomyId = abnormalityAnatomyId;
		this.abnormalityAnatomyTerm = abnormalityAnatomyTerm;
		this.abnormalityAnatomyFreetext = abnormalityAnatomyFreetext;
		this.abnormalityAnatomyAnnotationSource = abnormalityAnatomyAnnotationSource;
		this.phenotypeId = phenotypeId;
		this.phenotypeTerm = phenotypeTerm;
		this.phenotypeFreetext = phenotypeFreetext;
		this.observations = observations;
		this.xCoordinates = xCoordinates;
		this.yCoordinates = yCoordinates;
		this.zCoordinates = zCoordinates;
	}

	
	

	
	/**
	 * @return the expressedAnatomyId
	 */
	public List<String> getExpressedAnatomyId() {
	
		return expressedAnatomyId;
	}

	
	/**
	 * @param expressedAnatomyId the expressedAnatomyId to set
	 */
	public void setExpressedAnatomyId(List<String> expressedAnatomyId) {
	
		this.expressedAnatomyId = expressedAnatomyId;
	}

	
	/**
	 * @return the expressedAnatomyFreetext
	 */
	public List<String> getExpressedAnatomyFreetext() {
	
		return expressedAnatomyFreetext;
	}

	
	/**
	 * @param expressedAnatomyFreetext the expressedAnatomyFreetext to set
	 */
	public void setExpressedAnatomyFreetext(List<String> expressedAnatomyFreetext) {
	
		this.expressedAnatomyFreetext = expressedAnatomyFreetext;
	}

	
	/**
	 * @return the expressedAnatomyTerm
	 */
	public List<String> getExpressedAnatomyTerm() {
	
		return expressedAnatomyTerm;
	}

	
	/**
	 * @param expressedAnatomyTerm the expressedAnatomyTerm to set
	 */
	public void setExpressedAnatomyTerm(List<String> expressedAnatomyTerm) {
	
		this.expressedAnatomyTerm = expressedAnatomyTerm;
	}

	
	/**
	 * @param xCoordinates the xCoordinates to set
	 */
	public void setxCoordinates(List<Float> xCoordinates) {
	
		this.xCoordinates = xCoordinates;
	}

	
	/**
	 * @param yCoordinates the yCoordinates to set
	 */
	public void setyCoordinates(List<Float> yCoordinates) {
	
		this.yCoordinates = yCoordinates;
	}

	
	/**
	 * @param zCoordinates the zCoordinates to set
	 */
	public void setzCoordinates(List<Float> zCoordinates) {
	
		this.zCoordinates = zCoordinates;
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

	
	/**
	 * @return the associatedChannel
	 */
	public List<String> getAssociatedChannel() {
	
		return associatedChannel;
	}

	
	/**
	 * @param associatedChannel the associatedChannel to set
	 */
	public void setAssociatedChannel(List<String> associatedChannel) {
	
		this.associatedChannel = associatedChannel;
	}

	
	/**
	 * @return the associatedImage
	 */
	public String getAssociatedImage() {
	
		return associatedImage;
	}

	
	
	/**
	 * @return the computedDepictedAnatomyId
	 */
	public List<String> getComputedDepictedAnatomyId() {
	
		return computedDepictedAnatomyId;
	}


	
	/**
	 * @param computedDepictedAnatomyId the computedDepictedAnatomyId to set
	 */
	public void setComputedDepictedAnatomyId(List<String> computedDepictedAnatomyId) {
	
		this.computedDepictedAnatomyId = computedDepictedAnatomyId;
	}


	
	/**
	 * @return the computedDepictedAnatomyTerm
	 */
	public List<String> getComputedDepictedAnatomyTerm() {
	
		return computedDepictedAnatomyTerm;
	}


	
	/**
	 * @param computedDepictedAnatomyTerm the computedDepictedAnatomyTerm to set
	 */
	public void setComputedDepictedAnatomyTerm(List<String> computedDepictedAnatomyTerm) {
	
		this.computedDepictedAnatomyTerm = computedDepictedAnatomyTerm;
	}


	
	/**
	 * @return the computedAbnormalityAnatomyId
	 */
	public List<String> getComputedAbnormalityAnatomyId() {
	
		return computedAbnormalityAnatomyId;
	}


	
	/**
	 * @param computedAbnormalityAnatomyId the computedAbnormalityAnatomyId to set
	 */
	public void setComputedAbnormalityAnatomyId(List<String> computedAbnormalityAnatomyId) {
	
		this.computedAbnormalityAnatomyId = computedAbnormalityAnatomyId;
	}


	
	/**
	 * @return the computedAbnormalityAnatomyTerm
	 */
	public List<String> getComputedAbnormalityAnatomyTerm() {
	
		return computedAbnormalityAnatomyTerm;
	}


	
	/**
	 * @param computedAbnormalityAnatomyTerm the computedAbnormalityAnatomyTerm to set
	 */
	public void setComputedAbnormalityAnatomyTerm(List<String> computedAbnormalityAnatomyTerm) {
	
		this.computedAbnormalityAnatomyTerm = computedAbnormalityAnatomyTerm;
	}


	/**
	 * @param associatedImage the associatedImage to set
	 */
	public void setAssociatedImage(String associatedImage) {
	
		this.associatedImage = associatedImage;
	}

	
	/**
	 * @return the depictedAnatomyId
	 */
	public List<String> getDepictedAnatomyId() {
	
		return depictedAnatomyId;
	}

	
	/**
	 * @param depictedAnatomyId the depictedAnatomyId to set
	 */
	public void setDepictedAnatomyId(List<String> depictedAnatomyId) {
	
		this.depictedAnatomyId = depictedAnatomyId;
	}

	
	/**
	 * @return the depictedAnatomyTerm
	 */
	public List<String> getDepictedAnatomyTerm() {
	
		return depictedAnatomyTerm;
	}

	
	/**
	 * @param depictedAnatomyTerm the depictedAnatomyTerm to set
	 */
	public void setDepictedAnatomyTerm(List<String> depictedAnatomyTerm) {
	
		this.depictedAnatomyTerm = depictedAnatomyTerm;
	}

	
	/**
	 * @return the depictedAnatomyFreetext
	 */
	public List<String> getDepictedAnatomyFreetext() {
	
		return depictedAnatomyFreetext;
	}

	
	/**
	 * @param depictedAnatomyFreetext the depictedAnatomyFreetext to set
	 */
	public void setDepictedAnatomyFreetext(List<String> depictedAnatomyFreetext) {
	
		this.depictedAnatomyFreetext = depictedAnatomyFreetext;
	}

	
	/**
	 * @return the depictedAnatomyAnnotationSource
	 */
	public List<String> getDepictedAnatomyAnnotationSource() {
	
		return depictedAnatomyAnnotationSource;
	}

	
	/**
	 * @param depictedAnatomyAnnotationSource the depictedAnatomyAnnotationSource to set
	 */
	public void setDepictedAnatomyAnnotationSource(List<String> depictedAnatomyAnnotationSource) {
	
		this.depictedAnatomyAnnotationSource = depictedAnatomyAnnotationSource;
	}

	
	/**
	 * @return the abnormalityAnatomyId
	 */
	public List<String> getAbnormalityAnatomyId() {
	
		return abnormalityAnatomyId;
	}

	
	/**
	 * @param abnormalityAnatomyId the abnormalityAnatomyId to set
	 */
	public void setAbnormalityAnatomyId(List<String> abnormalityAnatomyId) {
	
		this.abnormalityAnatomyId = abnormalityAnatomyId;
	}

	
	/**
	 * @return the abnormalityAnatomyTerm
	 */
	public List<String> getAbnormalityAnatomyTerm() {
	
		return abnormalityAnatomyTerm;
	}

	
	/**
	 * @param abnormalityAnatomyTerm the abnormalityAnatomyTerm to set
	 */
	public void setAbnormalityAnatomyTerm(List<String> abnormalityAnatomyTerm) {
	
		this.abnormalityAnatomyTerm = abnormalityAnatomyTerm;
	}

	
	/**
	 * @return the abnormalityAnatomyFreetext
	 */
	public List<String> getAbnormalityAnatomyFreetext() {
	
		return abnormalityAnatomyFreetext;
	}

	
	/**
	 * @param abnormalityAnatomyFreetext the abnormalityAnatomyFreetext to set
	 */
	public void setAbnormalityAnatomyFreetext(List<String> abnormalityAnatomyFreetext) {
	
		this.abnormalityAnatomyFreetext = abnormalityAnatomyFreetext;
	}

	
	/**
	 * @return the abnormalityAnatomyAnnotationSource
	 */
	public List<String> getAbnormalityAnatomyAnnotationSource() {
	
		return abnormalityAnatomyAnnotationSource;
	}

	
	/**
	 * @param abnormalityAnatomyAnnotationSource the abnormalityAnatomyAnnotationSource to set
	 */
	public void setAbnormalityAnatomyAnnotationSource(List<String> abnormalityAnatomyAnnotationSource) {
	
		this.abnormalityAnatomyAnnotationSource = abnormalityAnatomyAnnotationSource;
	}

	
	/**
	 * @return the phenotypeId
	 */
	public List<String> getPhenotypeId() {
	
		return phenotypeId;
	}

	
	/**
	 * @param phenotypeId the phenotypeId to set
	 */
	public void setPhenotypeId(List<String> phenotypeId) {
	
		this.phenotypeId = phenotypeId;
	}

	
	/**
	 * @return the phenotypeTerm
	 */
	public List<String> getPhenotypeTerm() {
	
		return phenotypeTerm;
	}

	
	/**
	 * @param phenotypeTerm the phenotypeTerm to set
	 */
	public void setPhenotypeTerm(List<String> phenotypeTerm) {
	
		this.phenotypeTerm = phenotypeTerm;
	}

	
	/**
	 * @return the phenotypeFreetext
	 */
	public List<String> getPhenotypeFreetext() {
	
		return phenotypeFreetext;
	}

	
	/**
	 * @param phenotypeFreetext the phenotypeFreetext to set
	 */
	public void setPhenotypeFreetext(List<String> phenotypeFreetext) {
	
		this.phenotypeFreetext = phenotypeFreetext;
	}

	
	/**
	 * @return the observations
	 */
	public List<String> getObservations() {
	
		return observations;
	}

	
	/**
	 * @param observations the observations to set
	 */
	public void setObservations(List<String> observations) {
	
		this.observations = observations;
	}

	
	/**
	 * @return the xCoordinates
	 */
	public List<Float> getxCoordinates() {
	
		return xCoordinates;
	}

	
	/**
	 * @param xCoordinates the xCoordinates to set
	 */
	public void setXCoordinates(List<Float> xCoordinates) {
	
		this.xCoordinates = xCoordinates;
	}

	
	/**
	 * @return the yCoordinates
	 */
	public List<Float> getyCoordinates() {
	
		return yCoordinates;
	}

	
	/**
	 * @param yCoordinates the yCoordinates to set
	 */
	public void setYCoordinates(List<Float> yCoordinates) {
	
		this.yCoordinates = yCoordinates;
	}

	
	/**
	 * @return the zCoordinates
	 */
	public List<Float> getzCoordinates() {
	
		return zCoordinates;
	}

	
	/**
	 * @param zCoordinates the zCoordinates to set
	 */
	public void setZCoordinates(List<Float> zCoordinates) {
	
		this.zCoordinates = zCoordinates;
	}
	
}

package uk.ac.ebi.phis.solrj.pojo;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class RoiPojo {

	@Field("id")
	String id;

	@Field("associated_channel")
	List<String> associatedChannel;

	@Field("associated_image")
	String associatedImage;

	@Field("depicted_anatomy_id")
	List<String> depictedAnatomyId;

	@Field("depicted_anatomy_term")
	List<String> depictedAnatomyTerm;
	
	@Field("computed_depicted_anatomy_id")
	List<String> computedDepictedAnatomyId;

	@Field("computed_depicted_anatomy_term")
	List<String> computedDepictedAnatomyTerm;

	@Field("depicted_anatomy_freetext")
	List<String> depictedAnatomyFreetext;

	@Field("depicted_anatomy_annotation_source")
	List<String> depictedAnatomyAnnotationSource;

	@Field("abnormality_anatomy_id")
	List<String> abnormalityAnatomyId;

	@Field("abnormality_anatomy_term")
	List<String> abnormalityAnatomyTerm;
	
	@Field("computed_abnormality_anatomy_id")
	List<String> computedAbnormalityAnatomyId;

	@Field("computed_abnormality_anatomy_term")
	List<String> computedAbnormalityAnatomyTerm;

	@Field("abnormality_anatomy_freetext")
	List<String> abnormalityAnatomyFreetext;

	@Field("abnormality_anatomy_annotation_source")
	List<String> abnormalityAnatomyAnnotationSource;

	@Field("phenotype_id")
	List<String> phenotypeId;

	@Field("phenotype_term")
	List<String> phenotypeTerm;

	@Field("phenotype_freetext")
	List<String> phenotypeFreetext;

	@Field("observations")
	List<String> observations;

	@Field("x_coordinates")
	List<Float> xCoordinates;

	@Field("y_coordinates")
	List<Float> yCoordinates;

	@Field("z_coordinates")
	List<Float> zCoordinates;

	
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

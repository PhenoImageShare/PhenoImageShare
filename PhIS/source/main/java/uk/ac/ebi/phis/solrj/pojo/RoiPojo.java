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

	@Field("depicted_anatomy_freetext")
	List<String> depictedAnatomyFreetext;

	@Field("depicted_anatomy_annotation_source")
	List<String> depictedAnatomyAnnotationSource;

	@Field("abnormality_anatomy_id")
	List<String> abnormalityAnatomyId;

	@Field("abnormality_anatomy_term")
	List<String> abnormalityAnatomyTerm;

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
}

package uk.ac.ebi.phis.utils;

import j.Annotation;
import j.Coordinates;
import j.Dimensions;
import j.Image;
import j.OntologyTerm;
import j.OntologyTermArray;
import j.Roi;

import java.util.List;

import uk.ac.ebi.phis.utils.ontology.OntologyUtils;

public class ValidationUtils {

	private OntologyUtils ou;
	
	public ValidationUtils(){
		ou = new OntologyUtils();
	}
	
	// positiveDimensions
	public boolean hasPositieDimensions (Dimensions d){
		if (d.getImageHeight() > 0 && d.getImageWidth() > 0){
			if (d.getImageDepth() == null || (d.getImageDepth() != null && d.getImageDepth() > 0)){
				return true;
			}
		}
		return false;
	}
	// percentageCoords()
	
	public boolean arePercentagesOk(Coordinates coords){
		for (Float p : coords.getXCoordinates().getEl()){
			if (p < 0 || p > 100){
				return false;
			}
		}for (Float p : coords.getYCoordinates().getEl()){
			if (p < 0 || p > 100){
				return false;
			}
		}
		if (coords.getZCoordinates() != null){
			for (Float p : coords.getXCoordinates().getEl()){
				if (p < 0 || p > 100){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean hasValidOntologyTerms(Image img){
		
		boolean res = true;
		Annotation ann = img.getDepictedAnatomicalStructure();
		// depicted anatomy
		res = res && checkOntologyTerm(ann, "anatomy");
		// stage
		res = res && checkOntologyTerm(img.getOrganism().getStage(), "stage");
		List<OntologyTerm> ota = img.getImageDescription().getSamplePreparation().getEl();
		if  (ota != null){
			for (OntologyTerm ot : ota){
				res = res && checkOntologyTerm(ot, "samplePreparation");
			}
		}
		ota = img.getImageDescription().getVisualisationMethod().getEl();
		if  (ota != null){
			for (OntologyTerm ot : ota){
				res = res && checkOntologyTerm(ot, "visualisationMethod");
			}
		}
		ota = img.getImageDescription().getImagingMethod().getEl();
		if  (ota != null){
			for (OntologyTerm ot : ota){
				res = res && checkOntologyTerm(ot, "imagingMethod");
			}
		}
		return res;
	}
	
	
	public boolean isValidOntologyTerms(Roi roi){
		
		// Do anatomy terms first
		List<Annotation> annList = roi.getAbnormalityInAnatomicalStructure().getEl();
		annList.addAll(roi.getDepictedAnatomicalStructure().getEl());
		boolean res = true;
		// Check if any ids present
		if (annList != null){
			for (Annotation ann : annList){
				res = res && checkOntologyTerm(ann, "anatomy");
			}
		}
		
		// Phenotype terms
		annList = roi.getPhenotypeAnnotations().getEl();
		// Check if any ids present
		if (annList != null){
			for (Annotation ann : annList){
				res = res && checkOntologyTerm(ann, "phenotype");
			}
		}
				
		return res;
	}	
	
	/**
	 * 
	 * @param ann
	 * @param type can be one of phenotype, anatomy, stage, 
	 * @return
	 */
	private boolean checkOntologyTerm(Annotation ann, String type){
		if (ann != null){
			return checkOntologyTerm(ann.getOntologyTerm(), type);
		}
		
		return true;
	}
	
	private boolean checkOntologyTerm(OntologyTerm ot, String type){
			if (ot != null){
				if (ot.getTermLabel() != null && ot.getTermId() == null){ // We don't allow label only
					System.out.println("We don't allow label only img id" );
					return false;
				}else if (ot.getTermId() != null){
					// check they are from the right ontology
					boolean result = true;
					if (type.equalsIgnoreCase("anatomy")){
						result = ou.isAnatomy(ot.getTermId());
					} else if (type.equalsIgnoreCase("phenotype")){
						result = ou.isPhenotype(ot.getTermId());
					} else if (type.equalsIgnoreCase("stage")){
						result = ou.isStage(ot.getTermId());
					}
					// check label and id match
					result = result && ou.labelMatchesId(ot.getTermLabel(), ot.getTermId());
					if (!result){
						System.out.println("Label is from the right ontology : " + ou.isAnatomy(ot.getTermId()) + " label matches id : " + ou.labelMatchesId(ot.getTermLabel(), ot.getTermId()));
					}
					return result;
				}
			}
		return true;
	}
	
}

/*******************************************************************************
 * Copyright 2015 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 *******************************************************************************/
package uk.ac.ebi.phis.utils;

import uk.ac.ebi.phis.exception.PhenoImageShareException;
import uk.ac.ebi.phis.jaxb.*;
import uk.ac.ebi.phis.utils.olsclient.OlsClient;
import uk.ac.ebi.phis.utils.ontology.OntologyUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidationUtils {

	public OlsClient olsClient;
	public OntologyUtils ou;
	public static final Set<String> anatomyOntologies = Stream.of("ma", "uberon", "fbbt", "emap", "emapa").collect(Collectors.toCollection(HashSet::new));
	public static final Set<String> phenotypeOntologies = Stream.of("mp", "cmpo").collect(Collectors.toCollection(HashSet::new));
	public static final Set<String> stageOntologies = Stream.of("mmusdv", "fbdv").collect(Collectors.toSet());

	public ValidationUtils() {
		olsClient = new OlsClient();
	}

	public ValidationUtils(OntologyUtils ou) {
		this.ou = ou;
	}

	// positiveDimensions
	public boolean hasPositieDimensions(Dimensions d) {

		if (d.getImageHeight() > 0 && d.getImageWidth() > 0) {
			if (d.getImageDepth() == null || (d.getImageDepth() != null && d.getImageDepth() > 0)) { return true; }
		}
		return false;
	}


	// percentageCoords()

	public boolean arePercentagesOk(Coordinates coords) {

		for (Double p : coords.getXCoordinates().getEl()) {
			if (p < 0 || p > 100) { return false; }
		}
		for (Double p : coords.getYCoordinates().getEl()) {
			if (p < 0 || p > 100) { return false; }
		}
		if (coords.getZCoordinates() != null) {
			for (Double p : coords.getXCoordinates().getEl()) {
				if (p < 0 || p > 100) { return false; }
			}
		}
		return true;
	}


	public boolean hasValidOntologyTerms(Image img, Boolean strict) throws PhenoImageShareException{

		boolean res = true;
		Annotation ann = img.getDepictedAnatomicalStructure();
		// depicted anatomy
		res = res && checkOntologyTerm(ann, "anatomy", strict);
		if (!res) {
			System.out.println(">>> anatomy >>> " + ann.getOntologyTerm() + " img id " + img.getId());
			return false;
		}
		// stage
		if (img.getOrganism().getStage() != null) {
			res = res && checkOntologyTerm(img.getOrganism().getStage(), "stage", strict);
			if (!res) {
				System.out.println(">>> stage " + img.getOrganism().getStage().getTermId() + "(" + img.getOrganism().getStage().getTermLabel() + ") is not a valid entry for stage.");
				return false;
			}
		}		
		List<Annotation> annotationArray;
		if (img.getImageDescription().getSamplePreparation() != null) {
			annotationArray = img.getImageDescription().getSamplePreparation().getEl();
			if (annotationArray != null) {
				for (Annotation a : annotationArray) {
					OntologyTerm ot = a.getOntologyTerm();
					if (ot != null){
						res = res && checkOntologyTerm(a, "samplePreparation", strict);
						if (!res) {
							System.out.println(">>> sample prep " + ot.getTermId() + "(" + ot.getTermLabel() + ") is not a valid entry for samplePreparation.");
							return false;
						}
					}
				}
			}
		}	
		if (img.getImageDescription().getImagingMethod() != null) {
			annotationArray = img.getImageDescription().getImagingMethod().getEl();
			if (annotationArray != null) {
				for (Annotation a : annotationArray) {
					OntologyTerm ot = a.getOntologyTerm();
					res = res && checkOntologyTerm(a, "imagingMethod", strict);
					if (!res) {
						System.out.println(">>> imaging method " + ot.getTermId() + "(" + ot.getTermLabel() + ") is not a valid entry for imagingMethod.");
						return false;
					}
				}
			}
		}
		return res;
	}


	public boolean hasValidOntologyTerms(Channel channel, Boolean strict) throws PhenoImageShareException{

		boolean res = true;
		List<Annotation> ontologyTermArray;
		if (channel.getVisualisationMethod() != null) {
			ontologyTermArray = channel.getVisualisationMethod().getEl();
			if (ontologyTermArray != null) {
				for (Annotation a : ontologyTermArray) {
					OntologyTerm ot = a.getOntologyTerm();
					res = res && checkOntologyTerm(a, "visualisationMethod", strict);
					if (!res) {
						return false;
					}
				}
			}
		}
		return res;
	}

	public boolean hasValidOntologyTerms(Roi roi, Boolean strict) throws PhenoImageShareException{

		List<Annotation> annList;
		boolean res = true;
		
		if (roi.getAbnormalityInAnatomicalStructure() != null) {
			// Do anatomy terms first
			annList = roi.getAbnormalityInAnatomicalStructure().getEl();
			// Check if any ids present
			if (annList != null) {
				for (Annotation ann : annList) {
					res = res && checkOntologyTerm(ann, "anatomy", strict);
				}
			}

			for (ExpressionAnnotation ann: roi.getDepictedAnatomicalStructure().getEl()){
				res = res && checkOntologyTerm(ann.getOntologyTerm(), "anatomy", strict);
			}
		}

		if (roi.getPhenotypeAnnotations() != null) {
			// Phenotype terms
			annList = roi.getPhenotypeAnnotations().getEl();
			// Check if any ids present
			if (annList != null) {
				for (Annotation ann : annList) {
					res = res && checkOntologyTerm(ann, "phenotype", strict);
				}
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
	private boolean checkOntologyTerm(Annotation ann, String type, Boolean strict) throws PhenoImageShareException{


		if (ann != null) {
			return checkOntologyTerm(ann.getOntologyTerm(), type, strict);
		}

		return true;
	}

	private boolean checkOntologyTerm(OntologyTerm term, String type, Boolean strict) throws PhenoImageShareException{

		if (term != null) {
			if (ou != null) {
				return checkOntologyTermOWL(term, type, strict);
			} else {
				return checkOntologyTermOls(term, type, strict);
			}
		}

		return true;
	}

	private boolean checkOntologyTermOWL(OntologyTerm ot, String type, Boolean strict) throws PhenoImageShareException {

		if (ot != null) {
			if (ot.getTermLabel() != null && ot.getTermId() == null) {
				// We don't allow label only
				System.out.println("We don't allow label only img id");
				return false;
			} else if (ot.getTermId() != null) {
				// check they are from the right ontology
				boolean isValid = true;
				if (type.equalsIgnoreCase("anatomy")) {
					isValid = ou.isAnatomy(ot.getTermId(), strict);
				} else if (type.equalsIgnoreCase("phenotype")) {
					isValid = ou.isPhenotype(ot.getTermId(), strict);
				} else if (type.equalsIgnoreCase("stage")) {
					isValid = ou.isStage(ot.getTermId(), strict);
				} else if (type.equalsIgnoreCase("samplePreparation")) {
					isValid = ou.isSamplePreparation(ot.getTermId(), strict);
				} else if (type.equalsIgnoreCase("visualisationMethod")) {
					isValid = ou.isImaveVisualization(ot.getTermId(), strict);
				} else if (type.equalsIgnoreCase("imagingMethod")) {
					isValid = ou.isImagingMethod(ot.getTermId(), strict);
				}
				// Removed check to see if label & id match. If label doesn't match we'll use the one from the ontology
/*				isValid = isValid && ou.labelMatchesId(ot.getTermLabel(), ot.getTermId());
				if (!isValid) {
//					System.out.println(">> Label matches id? " + ou.labelMatchesId(ot.getTermLabel(), ot.getTermId()) + " for " + ot.getTermId() + " " + ot.getTermLabel() + "\n>> Or the term is not present in the ontologies known for this field. ");
				}
*/				return isValid;
			}
		}
		return false;
	}


	private boolean checkOntologyTermOls(OntologyTerm ot, String type, Boolean strict) throws PhenoImageShareException{

		String ontologyShortName = ot.getTermId().split("_")[0].split(":")[0].toLowerCase(); // split at either : or _ as only one of them will be present
		String termId = ot.getTermId().replace("_", ":"); // need obo id

		if (ot != null) {
			if (ot.getTermLabel() != null && ot.getTermId() == null) {
				// We don't allow label only
				System.out.println("We don't allow label only img id");
				return false;
			} else if (ot.getTermId() != null) {
				// check they are from the right ontology
				boolean isValid = true;
				if (type.equalsIgnoreCase("anatomy")) {
					isValid = anatomyOntologies.contains(ontologyShortName) && olsClient.getTerm(ontologyShortName, termId) != null;
					if (strict && !isValid){
						throw new PhenoImageShareException("Term " + termId + " is not an anatomy term or is not a valid ontology term.");
					}
				} else if (type.equalsIgnoreCase("phenotype")) {
					isValid = phenotypeOntologies.contains(ontologyShortName) && olsClient.getTerm(ontologyShortName, termId) != null;
					if (strict && !isValid){
						throw  new PhenoImageShareException("Term " + termId + " is not a phenotype term or is not a valid ontology term.");
					}
				} else if (type.equalsIgnoreCase("stage")) {
					isValid = stageOntologies.contains(ontologyShortName) && olsClient.getTerm(ontologyShortName, termId) != null;
					if (strict && !isValid){
						throw new PhenoImageShareException("Term " + termId + " is not a stage term or is not a valid ontology term.");
					}
				} else {
					return true;
				}
//				else if (type.equalsIgnoreCase("samplePreparation")) {
//					isValid = ou.isSamplePreparation(ot.getTermId(), strict);
//					if (strict&& !isValid){
//						throw new PhenoImageShareException("Term " + termId + " is not a samplePreparation term or is not a valid ontology term.");
//					}
//				} else if (type.equalsIgnoreCase("visualisationMethod")) {
//					isValid = ou.isImaveVisualization(ot.getTermId(), strict);
//					if (strict&& !isValid){
//						throw new PhenoImageShareException("Term " + termId + " is not a visualisationMethod term or is not a valid ontology term.");
//					}
//				} else if (type.equalsIgnoreCase("imagingMethod")) {
//					isValid = ou.isImagingMethod(ot.getTermId(), strict);
//					if (strict&& !isValid){
//						throw new PhenoImageShareException("Term " + termId + " is not an imagingMethod term or is not a valid ontology term.");
//					}
//				}
//

				// Removed check to see if label & id match. If label doesn't match we'll use the one from the ontology
/*				isValid = isValid && ou.labelMatchesId(ot.getTermLabel(), ot.getTermId());
				if (!isValid) {
//					System.out.println(">> Label matches id? " + ou.labelMatchesId(ot.getTermLabel(), ot.getTermId()) + " for " + ot.getTermId() + " " + ot.getTermLabel() + "\n>> Or the term is not present in the ontologies known for this field. ");
				}
*/				return isValid;
			}
		}
		return false;
	}

}

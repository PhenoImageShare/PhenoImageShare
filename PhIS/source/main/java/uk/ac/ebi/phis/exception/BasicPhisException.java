package uk.ac.ebi.phis.exception;


public class BasicPhisException extends Exception{

	 public static final String EXISTING_ID_EXCEPTION_MESSAGE = "Id already exists. The id provided is ";
	 public static final String EMPTY_ID_EXCEPTION_MESSAGE = "No valid id provided. Please provide one and run again.";
	 public static final String ID_NOT_FOUND_EXCEPTION_MESSAGE = "No valid id provided. Please provide one and run again.";
	 public static final String NO_COORDINATES_EXCEPTION = "Please provide coordinates for each Annotation. At least x and y coordinates are required.";
	 public static final String RELATIONSHIP_EXISTS_EXCEPTION = "Relationship exits and was not added again.";	
	 public static final String NO_ANNOTATION_EXCEPTION = "At least 1 annotation is required to create a ROI. Please provide some observation, phenotype, expression or anatomy information. ";
	 public static final String NOT_MATCHING_ID_AND_TERM_ARRAYS = "The label and id arrays do not have matching lengths. Ontology terms should be provided with both label and id in parallel arrays.";

	public BasicPhisException(String message){
		super(message);
	}
	
}

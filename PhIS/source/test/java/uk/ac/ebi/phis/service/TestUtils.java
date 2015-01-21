package uk.ac.ebi.phis.service;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ebi.phis.solrj.dto.RoiDTO;


public class TestUtils {

	
	public static RoiDTO getTestRoi(){
		
		RoiDTO roi;
		String id = "madeup_ROI_id";
		List<String> associatedChannel = new ArrayList<>();
		associatedChannel.add("komp2_channel_112967_0");
		String associatedImage = "komp2_112967";
		List<String> depictedAnatomyId = new ArrayList<>();
		depictedAnatomyId.add("MADEUP_0001");
		List<String> depictedAnatomyTerm = new ArrayList<>();
		depictedAnatomyTerm.add("some anatomy part");
		List<String> depictedAnatomyFreetext = new ArrayList<>();
		depictedAnatomyFreetext.add("some anatomy freetext"); 
		List<String> depictedAnatomyAnnotationSource = new ArrayList<>();
		depictedAnatomyAnnotationSource.add("USER_ADDED");
		List<String> abnormalityAnatomyId = new ArrayList<>();
		abnormalityAnatomyId.add("MADEUP_0921");
		List<String> abnormalityAnatomyTerm = new ArrayList<>();
		abnormalityAnatomyTerm.add("anat made up term No 2");
		List<String> abnormalityAnatomyFreetext = new ArrayList<>();
		abnormalityAnatomyFreetext.add("some abnormal anatomy freetext No 2");
		List<String> abnormalityAnatomyAnnotationSource = new ArrayList<>();
		abnormalityAnatomyAnnotationSource.add("USER_ADDED"); 
		List<String> phenotypeId = new ArrayList<>();
		phenotypeId.add("MADEUP_928013");
		List<String> phenotypeTerm = new ArrayList<>();
		phenotypeTerm.add("phen term");
		List<String> phenotypeFreetext = new ArrayList<>();
		phenotypeFreetext.add("phen freetext");
		List<String> observations = new ArrayList<>();
		 observations.add("Observing carefully this..." );
		List<Float> xCoordinates = new ArrayList<>();
		xCoordinates.add((float) 2);
		List<Float> yCoordinates = new ArrayList<>();
		yCoordinates.add((float) 4);
		List<Float> zCoordinates = new ArrayList<>();
		zCoordinates.add((float) 12);
		
		roi = new RoiDTO(id, associatedChannel, associatedImage, depictedAnatomyId, depictedAnatomyTerm, depictedAnatomyFreetext, 
			depictedAnatomyAnnotationSource, abnormalityAnatomyId, abnormalityAnatomyTerm, abnormalityAnatomyFreetext, abnormalityAnatomyAnnotationSource, 
			phenotypeId, phenotypeTerm, phenotypeFreetext, observations, xCoordinates, yCoordinates, zCoordinates);
		
		return roi;
	}
}

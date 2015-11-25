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
package uk.ac.ebi.phis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import uk.ac.ebi.phis.solrj.dto.RoiDTO;


public class TestUtils {

	
	public static RoiDTO getTestRoi(){
		
		RoiDTO roi;
		String id = "madeup_id";
		List<String> associatedChannel = new ArrayList<>();
		associatedChannel.add("komp2_channel_112967_0");
		String associatedImage = "komp2_112967";
		List<String> depictedAnatomyId = new ArrayList<>();
		depictedAnatomyId.add("MADEUP_0001");
		List<String> depictedAnatomyTerm = new ArrayList<>();
		depictedAnatomyTerm.add("some anatomy part");
		List<String> depictedAnatomyFreetext = new ArrayList<>();
		depictedAnatomyFreetext.add("some anatomy freetext"); 
//		List<String> depictedAnatomyAnnotationSource = new ArrayList<>();
//		depictedAnatomyAnnotationSource.add("USER_ADDED");
		List<String> abnormalityAnatomyId = new ArrayList<>();
		abnormalityAnatomyId.add("MADEUP_0921");
		List<String> abnormalityAnatomyTerm = new ArrayList<>();
		abnormalityAnatomyTerm.add("anat made up term No 2");
		List<String> abnormalityAnatomyFreetext = new ArrayList<>();
		abnormalityAnatomyFreetext.add("some abnormal anatomy freetext No 2");
//		List<String> abnormalityAnatomyAnnotationSource = new ArrayList<>();
//		abnormalityAnatomyAnnotationSource.add("USER_ADDED"); 
		List<String> phenotypeId = new ArrayList<>();
		phenotypeId.add("MADEUP_928013");
		List<String> phenotypeTerm = new ArrayList<>();
		phenotypeTerm.add("phen term");
		List<String> phenotypeFreetext = new ArrayList<>();
		phenotypeFreetext.add("phen freetext");
		List<String> expressedInId = new ArrayList<>();
		expressedInId.add("MADEUP_43013");
		List<String> expressedInTerm = new ArrayList<>();
		expressedInTerm.add("anat term with expression");
		List<String> expressedInFreetext = new ArrayList<>();
		expressedInFreetext.add("anatomy freetext - expression");
		
		List<String> observations = new ArrayList<>();
		observations.add("Observing carefully this..." );
		List<Float> xCoordinates = new ArrayList<>();
		xCoordinates.add((float) 2);
		List<Float> yCoordinates = new ArrayList<>();
		yCoordinates.add((float) 4);
		List<Float> zCoordinates = new ArrayList<>();
		zCoordinates.add((float) 12);
		
		String userOwner = "ilinca";
		String usergroup = "myGroup";
		Date today = new Date();
		Date creationDate = today;
		Date lastEditDate = today;
		
		roi = new RoiDTO(id, associatedChannel, associatedImage, depictedAnatomyId, depictedAnatomyTerm, depictedAnatomyFreetext, 
			abnormalityAnatomyId, abnormalityAnatomyTerm, abnormalityAnatomyFreetext, 
			phenotypeId, phenotypeTerm, phenotypeFreetext, observations, xCoordinates, yCoordinates, zCoordinates,
			expressedInTerm, expressedInFreetext, expressedInId, userOwner, usergroup, creationDate, lastEditDate);
		
		return roi;
	}
}

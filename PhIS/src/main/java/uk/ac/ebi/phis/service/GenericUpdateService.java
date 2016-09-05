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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.ebi.phis.exception.PhisSubmissionException;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;

@Service
public class GenericUpdateService {
	
	/**
	 * Will manage updates to all cores for each new annotation submitted by a user. 
	 */
	
	@Autowired 
	ImageService is;
		

	@Autowired
	ChannelService cs;


	@Autowired
	RoiService rs;
	
	
	public void updateCores(RoiDTO roi, Boolean publish) throws PhisSubmissionException{

		rs.updateRoi(roi);

		RoiDTO roiToReplace = rs.getRoiById(roi.getId());
		if (publish) {
			if (roiToReplace != null) {
				is.updateImageFromRoi(roiToReplace, roi);
			} else {
				is.addToImageFromRoi(roi);
				cs.addAssociatedRoi(roi);
			}
		} else {
			if (roiToReplace != null){
				is.deleteRoiRefferences(roiToReplace);
				cs.deleteAssociatedRoi(roi);
			}
		}
	}
	
	
	public void addToCores(RoiDTO roi, Boolean publish)
	throws PhisSubmissionException{
		rs.addRoi(roi);
		if (publish) {
			is.addToImageFromRoi(roi);
			cs.addAssociatedRoi(roi);
		}
	}
	
	
	public void deleteFromCores(String roiId) 
	throws PhisSubmissionException{
		RoiDTO roi = rs.getRoiById(roiId);
		cs.deleteAssociatedRoi(roi);
		is.deleteRoiRefferences(roi);
		rs.deleteRoi(roi.getId());
	}
	
}

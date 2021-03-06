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
package uk.ac.ebi.phis.web.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.ac.ebi.neo4jUtils.AnnotationProperties;
import uk.ac.ebi.neo4jUtils.Neo4jAccessUtils;
import uk.ac.ebi.phis.dto.solrj.RoiDTO;
import uk.ac.ebi.phis.exception.PhisSubmissionException;
import uk.ac.ebi.phis.service.GenericUpdateService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.service.RoiService;
import uk.ac.ebi.phis.utils.web.RestStatusMessage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

@Controller
	@RequestMapping("/rest/submission")
	public class SubmissionController {

	@Autowired
	ImageService is;
	@Autowired
	RoiService rs;
		
		@Autowired
		Neo4jAccessUtils neo;
		
		@Autowired 
		GenericUpdateService gus;
		
		/**
		 * 
		 * @param model
		 * @return
		 * @throws SolrServerException
		 * @throws IOException
		 * @throws URISyntaxException
		 */
		@RequestMapping(value="/", method=RequestMethod.GET)	
	    public String showDocumentation( Model model ) {
		
			System.out.println("Try /getImages .");
			return "submission_home";
	    }


		@RequestMapping(value="/createAnnotation", method=RequestMethod.GET)	
	    public @ResponseBody String createAnnotation(
	    
	    		@RequestParam(value = "userId", required = true) String userId,
	            @RequestParam(value = "annotationId", required = true) String annotationId,
	            
	            @RequestParam(value = "associatedImageId", required = true) String associatedImageId,
	            
	    		@RequestParam(value = "userGroupId", required = false) String userGroup,
	            
	            @RequestParam(value = "xCoordinates", required = true) List<Double> xCoordinates,
	            @RequestParam(value = "yCoordinates", required = true) List<Double> yCoordinates,
				@RequestParam(value = "zCoordinates", required = false) List<Double> zCoordinates,
                @RequestParam(value = "publish", required = true) Boolean publish,

	    		@RequestParam(value = "associatedChannelId", required = false) List<String> associatedChannelId,
	    		
	            @RequestParam(value = "depictedAnatomyId", required = false) List<String> depictedAnatomyId,
	            @RequestParam(value = "depictedAnatomyTerm", required = false) List<String> depictedAnatomyTerm,
	            @RequestParam(value = "depictedAnatomyFreetext", required = false) List<String> depictedAnatomyFreetext,
	            
	            @RequestParam(value = "expressionInAnatomyId", required = false) List<String> expressionInAnatomyId,
	            @RequestParam(value = "expressionInAnatomyTerm", required = false) List<String> expressionInAnatomyTerm,
	            @RequestParam(value = "expressionInAnatomyFreetext", required = false) List<String> expressionInAnatomyFreetext,
	            
	            @RequestParam(value = "abnInAnatomyId", required = false) List<String> abnInAnatomyId,
	            @RequestParam(value = "abnInAnatomyFreetext", required = false) List<String> abnInAnatomyFreetext,
	            @RequestParam(value = "abnInAnatomyTerm", required = false) List<String> abnInAnatomyTerm,
	            
	            @RequestParam(value = "phenotypeId", required = false) List<String> phenotypeId,
	            @RequestParam(value = "phenotypeFreetext", required = false) List<String> phenotypeFreetext,
	            @RequestParam(value = "phenotypeTerm", required = false) List<String> phenotypeTerm,

	            @RequestParam(value = "observation", required = false) List<String> observations,
	    		Model model  ) {
			
			JSONObject succeded = RestStatusMessage.getSuccessJson();
			
			try {
				if (is.imageIdExists(associatedImageId)){

					Date today = new Date();
									
					List<Double> zCoord = (zCoordinates != null ? zCoordinates : null);

                    RoiDTO roi = new RoiDTO(annotationId, associatedChannelId, associatedImageId, depictedAnatomyId, depictedAnatomyTerm,
                                depictedAnatomyFreetext, abnInAnatomyId, abnInAnatomyTerm, abnInAnatomyFreetext,
                                phenotypeId, phenotypeTerm, phenotypeFreetext, observations, xCoordinates,
                                yCoordinates, zCoord, expressionInAnatomyTerm, expressionInAnatomyFreetext, expressionInAnatomyId,
                                userId, userGroup, today, today, publish);
                    gus.addToCores(roi, publish);

					neo.createAnnotationWithDates(userId, userGroup, annotationId, associatedImageId, xCoordinates, yCoordinates, zCoordinates, 
							associatedChannelId, depictedAnatomyId, depictedAnatomyFreetext, depictedAnatomyTerm, abnInAnatomyId, 
							abnInAnatomyFreetext, abnInAnatomyTerm,	phenotypeId, phenotypeFreetext, phenotypeTerm, observations, 
							expressionInAnatomyId, expressionInAnatomyTerm, expressionInAnatomyFreetext, today, today);
				}				
			} catch (PhisSubmissionException e) {
				e.printStackTrace();
				succeded = RestStatusMessage.getFailJson();
				succeded.put("message", e.getMessage());
			} 			
			return succeded.toString();
	    }
		
		@RequestMapping(value="/updateAnnotation", method=RequestMethod.GET)	
	    public @ResponseBody String updateAnnotation(
	    		@RequestParam(value = "userId", required = true) String userId,
	            @RequestParam(value = "annotationId", required = true) String annotationId,
	            @RequestParam(value = "associatedImageId", required = true) String associatedImageId,
	            @RequestParam(value = "xCoordinates", required = true) List<Double> xCoordinates,
	            @RequestParam(value = "yCoordinates", required = true) List<Double> yCoordinates,
	    		@RequestParam(value = "userGroupId", required = false) String userGroupId,
	            @RequestParam(value = "zCoordinates", required = false) List<Double> zCoordinates,
	    		@RequestParam(value = "associatedChannelId", required = false) List<String> associatedChannelId,
	            @RequestParam(value = "depictedAnatomyId", required = false) List<String> depictedAnatomyId,
	            @RequestParam(value = "depictedAnatomyTerm", required = false) List<String> depictedAnatomyTerm,
	            @RequestParam(value = "depictedAnatomyFreetext", required = false) List<String> depictedAnatomyFreetext,
	            @RequestParam(value = "abnInAnatomyId", required = false) List<String> abnInAnatomyId,
	            @RequestParam(value = "abnInAnatomyTerm", required = false) List<String> abnInAnatomyTerm,
	            @RequestParam(value = "abnInAnatomyFreetext", required = false) List<String> abnInAnatomyFreetext,
	            @RequestParam(value = "expressionInAnatomyId", required = false) List<String> expressionInAnatomyId,
	            @RequestParam(value = "expressionInAnatomyFreetext", required = false) List<String> expressionInAnatomyFreetext,
	            @RequestParam(value = "expressionInAnatomyTerm", required = false) List<String> expressionInAnatomyTerm,
	            @RequestParam(value = "phenotypeId", required = false) List<String> phenotypeId,
	            @RequestParam(value = "phenotypeTerm", required = false) List<String> phenotypeTerm,
	            @RequestParam(value = "phenotypeFreetext", required = false) List<String> phenotypeFreetext,
	            @RequestParam(value = "observation", required = false) List<String> observations,
                @RequestParam(value = "publish", required = true) Boolean publish,
	    		Model model ) {
			
			JSONObject succeded = RestStatusMessage.getSuccessJson();
			
			try {
				
				if (!is.imageIdExists(associatedImageId)) {
					throw new PhisSubmissionException(PhisSubmissionException.IMAGE_ID_NOT_FOUND_EXCEPTION_MESSAGE);
				} else  if (rs.getRoiById(annotationId) == null ) {
					throw new PhisSubmissionException(PhisSubmissionException.ROI_ID_NOT_FOUND_EXCEPTION_MESSAGE);
				} else {

					System.out.println();

					Date today = new Date();
					Date creationDate = (neo.getNodeById(annotationId) != null) ? new Date(neo.getNodeById(annotationId).getProperty(AnnotationProperties.CREATION_DATE.name()).toString()) :  null;
					
					neo.updateAnnotation(userId, userGroupId, annotationId, associatedImageId, xCoordinates, yCoordinates, zCoordinates, associatedChannelId,
						depictedAnatomyId, depictedAnatomyFreetext, depictedAnatomyTerm, abnInAnatomyId, abnInAnatomyFreetext, abnInAnatomyTerm, phenotypeId, 
						phenotypeFreetext, phenotypeTerm, observations, expressionInAnatomyId, expressionInAnatomyTerm, expressionInAnatomyFreetext,
						creationDate, today);

					RoiDTO roi = new RoiDTO(annotationId, associatedChannelId, associatedImageId, depictedAnatomyId, depictedAnatomyTerm,
                                depictedAnatomyFreetext, abnInAnatomyId, abnInAnatomyTerm, abnInAnatomyFreetext,
                                phenotypeId, phenotypeTerm, phenotypeFreetext, observations, xCoordinates,
                                yCoordinates, zCoordinates != null ? zCoordinates : null,
                                expressionInAnatomyTerm, expressionInAnatomyFreetext, expressionInAnatomyId, userId, userGroupId, creationDate, today, publish);

					gus.updateCores(roi, publish);
				}
				
			} catch (PhisSubmissionException e) {
				e.printStackTrace();
				succeded = RestStatusMessage.getFailJson();
				succeded.put("message", e.getMessage());
			}
			
			return succeded.toString();
	    }
		


		@RequestMapping(value="/deleteAnnotation", method=RequestMethod.GET)	
	    public @ResponseBody String createAnnotation(
	    		@RequestParam(value = "userId", required = true) String userId,
	            @RequestParam(value = "annotationId", required = true) String annotationId,
	    		Model model) {
			
			JSONObject obj = RestStatusMessage.getSuccessJson();
			
			try {
				if (neo.hasSameUser(userId, annotationId)){
					neo.deleteNodeWithRelations(annotationId);
					gus.deleteFromCores(annotationId);
				}else{
					throw new PhisSubmissionException("User provided does not match the user of the annotation. Annotation was not deleted.");
				}
			} catch (PhisSubmissionException e){
				e.printStackTrace();
				obj = RestStatusMessage.getFailJson();
				obj.put("message", e.getMessage());				
			}
			
			return obj.toString();
	    }
		
	}




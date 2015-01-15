package uk.ac.ebi.phis.web.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.ac.ebi.neo4jUtils.Neo4jAccessUtils;

	@Controller
	@RequestMapping("/rest/submission")
	public class DatabaseController {

		@Autowired
		Neo4jAccessUtils neo;
		
		/**
		 * 
		 * @param model
		 * @return
		 * @throws SolrServerException
		 * @throws IOException
		 * @throws URISyntaxException
		 */
		@RequestMapping(value="/", method=RequestMethod.GET)	
	    public String showDocumentation( Model model ) throws SolrServerException, IOException, URISyntaxException {
		
			System.out.println("Try /getImages .");
			return "submission_home";
	    }
		
		
		@RequestMapping(value="/createAnnotation", method=RequestMethod.GET)	
	    public @ResponseBody String createAnnotation(
	    		@RequestParam(value = "userId", required = true) String userId,
	            @RequestParam(value = "anntoationId", required = true) String anntoationId,
	            @RequestParam(value = "associatedImageId", required = true) String associatedImageId,
	            @RequestParam(value = "xCoordinates", required = true) float[] xCoordinates,
	            @RequestParam(value = "yCoordinates", required = true) float[] yCoordinates,
	            @RequestParam(value = "zCoordinates", required = false) float[] zCoordinates,
	    		@RequestParam(value = "associatedChannel", required = false) String associatedChannel,
	            @RequestParam(value = "depictedAnatomyId", required = false) String depictedAnatomyId,
	            @RequestParam(value = "depictedAnatomyLabel", required = false) String depictedAnatomyLabel,
	            @RequestParam(value = "abnInAnatomyId", required = false) String abnInAnatomyId,
	            @RequestParam(value = "abnInAnatomyLabel", required = false) String abnInAnatomyLabel,
	            @RequestParam(value = "phenotypeId", required = false) String phenotypeId,
	            @RequestParam(value = "phenotypeLabel", required = false) String phenotypeLabel,
	            @RequestParam(value = "observation", required = false) String observation,
	    		Model model
	            ) {
				
			try {
				neo.createAnnotation(userId, anntoationId, associatedImageId, xCoordinates, yCoordinates, zCoordinates, associatedChannel, depictedAnatomyId, depictedAnatomyLabel, abnInAnatomyId, abnInAnatomyLabel, phenotypeId, phenotypeLabel, observation, model);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "";
	    }
		
		@RequestMapping(value="/updateAnnotation", method=RequestMethod.GET)	
	    public @ResponseBody String updateAnnotation(
	    		@RequestParam(value = "userId", required = true) String userId,
	            @RequestParam(value = "anntoationId", required = true) String anntoationId,
	            @RequestParam(value = "associatedImageId", required = true) String associatedImageId,
	            @RequestParam(value = "xCoordinates", required = true) float[] xCoordinates,
	            @RequestParam(value = "yCoordinates", required = true) float[] yCoordinates,
	            @RequestParam(value = "zCoordinates", required = false) float[] zCoordinates,
	    		@RequestParam(value = "associatedChannel", required = false) String associatedChannel,
	            @RequestParam(value = "depictedAnatomyId", required = false) String depictedAnatomyId,
	            @RequestParam(value = "depictedAnatomyLabel", required = false) String depictedAnatomyLabel,
	            @RequestParam(value = "abnInAnatomyId", required = false) String abnInAnatomyId,
	            @RequestParam(value = "abnInAnatomyLabel", required = false) String abnInAnatomyLabel,
	            @RequestParam(value = "phenotypeId", required = false) String phenotypeId,
	            @RequestParam(value = "phenotypeLabel", required = false) String phenotypeLabel,
	            @RequestParam(value = "observation", required = false) String observation,
	    		Model model
	            ) {
				
			try {
				neo.createAnnotation(userId, anntoationId, associatedImageId, xCoordinates, yCoordinates, zCoordinates, associatedChannel, depictedAnatomyId, depictedAnatomyLabel, abnInAnatomyId, abnInAnatomyLabel, phenotypeId, phenotypeLabel, observation, model);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "";
	    }
		
		@RequestMapping(value="/deleteAnnotation", method=RequestMethod.GET)	
	    public @ResponseBody String createAnnotation(
	    		@RequestParam(value = "userId", required = true) String userId,
	            @RequestParam(value = "anntoationId", required = true) String anntoationId,
	    		Model model
	            ) {
			try {
				
				if (neo.hasSameUser(userId, anntoationId)){
					neo.deleteNodeWithRelations(anntoationId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
	    }
		
		
	}




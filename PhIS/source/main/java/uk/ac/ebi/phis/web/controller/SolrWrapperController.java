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
import uk.ac.ebi.phis.service.AutosuggestService;
import uk.ac.ebi.phis.service.ChannelService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.service.RoiService;

@Controller
@RequestMapping("/rest")
public class SolrWrapperController {


	@Autowired
	ImageService is;

	@Autowired
	RoiService rs;

	@Autowired
	ChannelService cs;

	@Autowired
	AutosuggestService as;
	
	@Autowired
	Neo4jAccessUtils neo;
	
	/**
	 * 
	 * @param phenotype
	 * @param anatomy
	 * @param gene
	 * @param resultNo
	 * @param model
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@RequestMapping(value="/getImages", method=RequestMethod.GET)	
    public @ResponseBody String getExperimentalData(
    		@RequestParam(value = "term", required = false) String term,
    		@RequestParam(value = "phenotype", required = false) String phenotype,
            @RequestParam(value = "anatomy", required = false) String anatomy,
            @RequestParam(value = "gene", required = false) String gene,
            @RequestParam(value = "mutantGene", required = false) String mutantGene,
            @RequestParam(value = "expressedFeature", required = false) String expressedGene,
            @RequestParam(value = "sex", required = false) String sex,
            @RequestParam(value = "taxon", required = false) String taxon,
            @RequestParam(value = "imageType", required = false) String image_type,
            @RequestParam(value = "sampleType", required = false) String sample_type,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "visualisationMethod", required = false) String visualisationMethod,
            @RequestParam(value = "samplePreparation", required = false) String samplePreparation,
            @RequestParam(value = "imagingMethod", required = false) String imagingMethod,
            @RequestParam(value = "resultNo", required = false) Integer resultNo,
            @RequestParam(value = "start", required = false) Integer start,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
				
		return is.getImage(term, phenotype, gene, mutantGene, anatomy, expressedGene, sex, taxon, image_type, sample_type, stage, visualisationMethod, 
						samplePreparation, imagingMethod, resultNo, start);
    }
	
	
	@RequestMapping(value="/getAutosuggest", method=RequestMethod.GET)
	public  @ResponseBody String getSuggestions(
			@RequestParam(value = "term", required = false) String term,
			@RequestParam(value = "mutantGene", required = false) String mutantGene,
			@RequestParam(value = "expressedGeneOrAllele", required = false) String expressedGeneOrAllele,
			@RequestParam(value = "phenotype", required = false) String phenotype,
			@RequestParam(value = "resultNo", required = false) Integer resultNo,
			Model model){
		if (mutantGene != null || expressedGeneOrAllele != null || phenotype != null ){
			return is.getAutosuggest(term, mutantGene, expressedGeneOrAllele, phenotype, resultNo);
		}
		else {
			return as.getAutosuggest(term, resultNo);
		}
	}
	
	@RequestMapping(value="/getRois", method=RequestMethod.GET)	
    public @ResponseBody String getRois(
    		@RequestParam(value = "roiId", required = false) String roiId,
    		@RequestParam(value = "imageId", required = false) String imageId,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
				
		if (roiId != null){
			return rs.getRoiAsJsonString(roiId);
		}
		else if (imageId != null){
			return rs.getRois(imageId);
		}
		return "";
    }

	
	@RequestMapping(value="/getChannels", method=RequestMethod.GET)	
    public @ResponseBody String getChannels(
            @RequestParam(value = "channelId", required = false) String channelId,
    		@RequestParam(value = "imageId", required = false) String imageId,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
		
		if (channelId != null){
			return cs.getChannelAsJsonString(channelId);
		}else if (imageId != null){
			return cs.getChannels(imageId);
		}
		return "";
    }
	
	
	@RequestMapping(value="/getRoi", method=RequestMethod.GET)	
    public @ResponseBody String getRoi(
    		@RequestParam(value = "roiId", required = false) String roiId,
    		@RequestParam(value = "imageId", required = false) String imageId,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
				
		if (roiId != null){
			return rs.getRoiAsJsonString(roiId);
		}
		else if (imageId != null){
			return rs.getRois(imageId);
		}
		return "";
    }

	@RequestMapping(value="/getChannel", method=RequestMethod.GET)	
    public @ResponseBody String getChannel(
            @RequestParam(value = "channelId", required = false) String channelId,
    		@RequestParam(value = "imageId", required = false) String imageId,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
		
		if (channelId != null){
			return cs.getChannelAsJsonString(channelId);
		}else if (imageId != null){
			return cs.getChannels(imageId);
		}
		return "";
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)	
    public String showSomething( Model model ) throws SolrServerException, IOException, URISyntaxException {
	
		System.out.println("Try /getImages .");
		return "rest_home";
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



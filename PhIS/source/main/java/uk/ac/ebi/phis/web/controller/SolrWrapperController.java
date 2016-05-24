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

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrServerException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.ac.ebi.phis.exception.PhisQueryException;
import uk.ac.ebi.phis.service.AutosuggestService;
import uk.ac.ebi.phis.service.ChannelService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.service.Neo4jService;
import uk.ac.ebi.phis.service.RoiService;
import uk.ac.ebi.phis.solrj.dto.AutosuggestTypes;
import uk.ac.ebi.phis.utils.web.RestStatusMessage;

//CrossOrigin(origins = "http://fiddle.jshell.net", maxAge = 3600)
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
	Neo4jService ns;
		
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
    public @ResponseBody ResponseEntity<String>  getImages(
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
            @RequestParam(value = "chromosome", required = false) String chromosome,
            @RequestParam(value = "position", required = false) String strand,
            @RequestParam(value = "hostName", required = false) String hostName,
            @RequestParam(value = "location", required = false) Long position,
            @RequestParam(value = "startPosition", required = false) Long startPosition,
            @RequestParam(value = "endPosition", required = false) Long endPosition,
            @RequestParam(value = "resultNo", required = false) Integer resultNo,
            @RequestParam(value = "start", required = false) Integer start,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
			
		String responseString;
		try{
			responseString = is.getImages(term, phenotype, mutantGene, anatomy, expressedGene, sex, taxon, image_type, sample_type, stage, visualisationMethod, 
						samplePreparation, imagingMethod, resultNo, start, gene, chromosome, strand, position, startPosition, endPosition, hostName);
			
		} catch (PhisQueryException e){
			JSONObject succeded = RestStatusMessage.getFailJson();
			succeded.put("message", e.getMessage());
			responseString = succeded.toString();
		}
		
		return new ResponseEntity<String>(responseString, createResponseHeaders(), HttpStatus.OK);
    }
	

	@RequestMapping(value="/getImage", method=RequestMethod.GET)	
    public @ResponseBody ResponseEntity<String> getImage(@RequestParam(value = "imageId", required = true) String imageId, Model model) 
    throws SolrServerException, IOException, URISyntaxException {
		
		String responseString = "";
		if (imageId != null){
			responseString = is.getImageAsJsonString(imageId);
		}
		return new ResponseEntity<String>(responseString, createResponseHeaders(), HttpStatus.OK);
    }
	
	
	@RequestMapping(value="/getAutosuggest", method=RequestMethod.GET)
	public  @ResponseBody ResponseEntity<String> getSuggestions(
			@RequestParam(value = "term", required = true) String term,
			@RequestParam(value = "autosuggestType", required = false) String type,
			@RequestParam(value = "resultNo", required = false) Integer resultNo,
			@RequestParam(value = "taxon", required = false) String taxon,
			@RequestParam(value = "sampleType", required = false) String sampleType,
			@RequestParam(value = "stage", required = false) String stage,
			@RequestParam(value = "imagingMethod", required = false) String imagingMethod,
			@RequestParam(value = "imageGeneratedBy", required = false) String imageGeneratedBy,	
			@RequestParam(value = "hostName", required = false) String hostName,
			HttpServletRequest request,
			HttpServletResponse response,	
			Model model){
		
		JSONObject jsonResponse = as.getAutosuggest(term, (type != null ? AutosuggestTypes.valueOf(type) : null), stage, imagingMethod, taxon, sampleType, imageGeneratedBy, hostName, resultNo);
		ResponseEntity<String> resp = new ResponseEntity<String>(jsonResponse.toString(), createResponseHeaders(), HttpStatus.OK);

		return resp ;
		
	}
	
	
	@RequestMapping(value="/getComplexAutosuggest", method=RequestMethod.GET)
	public  @ResponseBody ResponseEntity<String> getComplexSuggestions(
			@RequestParam(value = "term", required = true) String term,
			@RequestParam(value = "autosuggestType", required = false) String type,
			@RequestParam(value = "resultNo", required = false) Integer resultNo,
			@RequestParam(value = "taxon", required = false) String taxon,
			@RequestParam(value = "sampleType", required = false) String sampleType,
			@RequestParam(value = "stage", required = false) String stage,
			@RequestParam(value = "imagingMethod", required = false) String imagingMethod,
			@RequestParam(value = "imageGeneratedBy", required = false) String imageGeneratedBy,
			@RequestParam(value = "hostName", required = false) String hostName,	
			Model model){

		ResponseEntity<String> resp;
		resp = new ResponseEntity<String>(as.getComplexAutosuggest(term, (type != null ? AutosuggestTypes.valueOf(type) : null), stage, imagingMethod, taxon, sampleType, imageGeneratedBy, hostName, resultNo), createResponseHeaders(), HttpStatus.OK); 
				
		return resp;
	}
	

	private HttpHeaders createResponseHeaders(){
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		return responseHeaders;
	}
	
	
	@RequestMapping(value="/getRois", method=RequestMethod.GET)	
    public @ResponseBody ResponseEntity<String> getRois(
    		@RequestParam(value = "roiId", required = false) String roiId,
    		@RequestParam(value = "imageId", required = false) String imageId,
    		@RequestParam(value = "resultNo", required = false) Integer resultNo,
    		@RequestParam(value = "userId", required = false) String userOwner,
    		@RequestParam(value = "userGroupId", required = false) String userGroup,
    		@RequestParam(value = "createdAfter", required = false) String createdAfter,
    		@RequestParam(value = "createdBefore", required = false) String createdBefore,
    		@RequestParam(value = "lastEditAfter", required = false) String lastEditAfter,
    		@RequestParam(value = "lastEditBefore", required = false) String lastEditBefore,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
				
		return new ResponseEntity<String>(rs.getRois(imageId, roiId, userOwner, userGroup, createdAfter, createdBefore, lastEditAfter, lastEditBefore, resultNo),
				createResponseHeaders(), HttpStatus.OK);
		
    }

	
	@RequestMapping(value="/getChannels", method=RequestMethod.GET)	
    public @ResponseBody ResponseEntity<String> getChannels(
            @RequestParam(value = "channelId", required = false) String channelId,
    		@RequestParam(value = "imageId", required = false) String imageId,
    		@RequestParam(value = "resultNo", required = false) Integer resultNo,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
		
		if (channelId != null){
			return new ResponseEntity<String>(cs.getChannelAsJsonString(channelId, resultNo),createResponseHeaders(), HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(cs.getChannels(imageId, resultNo),createResponseHeaders(), HttpStatus.OK);
		}
    }
		
	
	@RequestMapping(value="/getRoi", method=RequestMethod.GET)	
    public @ResponseBody ResponseEntity<String> getRoi(
    		@RequestParam(value = "roiId", required = true) String roiId,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
				
		String responseString = "";
		if (roiId != null){
			responseString = rs.getRoiAsJsonString(roiId, 10);
		}
		return new ResponseEntity<String>(responseString, createResponseHeaders(), HttpStatus.OK);
    }
	

	@RequestMapping(value="/getChannel", method=RequestMethod.GET)	
    public @ResponseBody ResponseEntity<String> getChannel(
            @RequestParam(value = "channelId", required = true) String channelId,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
		
		String responseString = "";
		if (channelId != null){
			responseString = cs.getChannelAsJsonString(channelId, 10);
		}
		
		return new ResponseEntity<String>(responseString, createResponseHeaders(), HttpStatus.OK);
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
		
	
	/**
	 * @since 2015/08/18
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getDataReleases", method=RequestMethod.GET)	
    public @ResponseBody ResponseEntity<String> getDataRelease(Model model){
		
		return  new ResponseEntity<String>( ns.getAllReleases().toString(), createResponseHeaders(), HttpStatus.OK);
		
	}
	
	
}



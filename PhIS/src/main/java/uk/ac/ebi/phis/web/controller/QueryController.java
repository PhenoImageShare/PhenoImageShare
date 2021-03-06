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
import org.springframework.beans.factory.annotation.Value;
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
import uk.ac.ebi.phis.exception.PhisSubmissionException;
import uk.ac.ebi.phis.service.*;
import  uk.ac.ebi.phis.dto.solrj.AutosuggestTypes;
import  uk.ac.ebi.phis.dto.solrj.ImageDTO;
import uk.ac.ebi.phis.utils.web.RestStatusMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/rest")
public class QueryController {

	@NotNull
	@Value("${baseUrl}")
	private String baseUrl;

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
	 // Keep parameters in synch with /download
	@RequestMapping(value="/getImages", method=RequestMethod.GET)	
    public @ResponseBody ResponseEntity<String>  getImages(
    		@RequestParam(value = "term", required = false) String term,
    		@RequestParam(value = "phenotype", required = false) String phenotype,
            @RequestParam(value = "anatomy", required = false) List<String> anatomy,
			@RequestParam(value = "-anatomy", required = false) List<String> excludeAnatomy,
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
						samplePreparation, imagingMethod, resultNo, start, gene, chromosome, strand, position, startPosition, endPosition, hostName, excludeAnatomy);
			
		} catch (PhisQueryException e){
			JSONObject succeded = RestStatusMessage.getFailJson();
			succeded.put("message", e.getMessage());
			responseString = succeded.toString();
		}
		
		return new ResponseEntity<String>(responseString, getJsonResponseHeaders(), HttpStatus.OK);
    }

	@RequestMapping(value="/getImageDetails", method=RequestMethod.GET)
	public  @ResponseBody String getImageInfo(@RequestParam(value = "imageId", required = true) String imageId, Model model)
			throws SolrServerException, PhisQueryException{

		JSONObject obj = new JSONObject();
		JSONObject imageRes = new JSONObject(is.getImageAsJsonString(imageId));
		if (imageRes == null || imageRes.getJSONObject("response").getJSONArray("docs") == null || imageRes.getJSONObject("response").getJSONArray("docs").length() == 0){
			return "Image id does not exist";
		}
		obj.accumulate("image", imageRes.getJSONObject("response").getJSONArray("docs").get(0));

		try{
			JSONObject roiRes = new JSONObject(rs.getRois(imageId, null, null, null, null, null, null, null, 100000));
			if (roiRes.getJSONObject("response").getJSONArray("docs") != null){
				for (int i = 0; i< roiRes.getJSONObject("response").getJSONArray("docs").length(); i++ ){
					JSONObject roi = (JSONObject) roiRes.getJSONObject("response").getJSONArray("docs").get(i);
					obj.append("rois",(new JSONObject()).accumulate(roi.get("id").toString(), roi));
				}
			}
		} catch (Exception e){e.printStackTrace();}

		try{
			JSONObject channelRes  = new JSONObject(cs.getChannelAsJsonString(imageId, 10000));
			if (channelRes.getJSONObject("response").getJSONArray("docs") != null){
				for (int i = 0; i< channelRes.getJSONObject("response").getJSONArray("docs").length(); i++ ){
					JSONObject channel = (JSONObject) channelRes.getJSONObject("response").getJSONArray("docs").get(i);
					obj.append("channels", (new JSONObject()).accumulate(channel.get("id").toString(), channel));
				}
			}
		} catch (Exception e){e.printStackTrace();}

		return obj.toString();
	}

	@RequestMapping(value="/getImage", method=RequestMethod.GET)	
    public @ResponseBody ResponseEntity<String> getImage(@RequestParam(value = "imageId", required = true) String imageId, Model model) 
    throws SolrServerException, IOException, URISyntaxException {
		
		String responseString = "";
		if (imageId != null){
			responseString = is.getImageAsJsonString(imageId);
		}
		return new ResponseEntity<String>(responseString, getJsonResponseHeaders(), HttpStatus.OK);
    }

	@RequestMapping(value="/similar", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getSimilarImages(@RequestParam(value = "imageId", required = true) String imageId,
																 @RequestParam(value = "resultNo", required = false) Integer resultNo,
																 @RequestParam(value = "start", required = false) Integer start,
																 Model model)
			throws SolrServerException, IOException, URISyntaxException {

		try {

			System.out.println("SIMILAR " + is.getSimilarImages(imageId, resultNo, start));
			return new ResponseEntity<String>(is.getSimilarImages(imageId, resultNo, start), getJsonResponseHeaders(), HttpStatus.OK);

		} catch (PhisSubmissionException | PhisQueryException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Request could not be processed.", getJsonResponseHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}




	@RequestMapping(value="/download", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> downloadImage(@RequestParam(value = "imageId", required = false) String imageId,
															  // Parameters from getImages
															  @RequestParam(value = "term", required = false) String term,
															  @RequestParam(value = "phenotype", required = false) String phenotype,
															  @RequestParam(value = "anatomy", required = false) List<String> anatomy,
															  @RequestParam(value = "-anatomy", required = false) List<String> excludeAnatomy,
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
															  HttpServletRequest request, Model model) {
		System.out.println("GOT IN DWNLOAD");
		try {
			if (imageId != null) {
				String download = is.getDownoadInfo(imageId, baseUrl + request.getRequestURI()) + "\n\n" + rs.getDownloadInfo(imageId);
				return new ResponseEntity<String>(download, getTextResponseHeaders(), HttpStatus.OK);
			} else {
				StringBuffer download = new StringBuffer();
				download.append(ImageDTO.getTabbedHeader() + "\n");
				List<ImageDTO> images = is.getImagesDTO(term, phenotype, mutantGene, anatomy, expressedGene, sex, taxon, image_type, sample_type, stage, visualisationMethod,
						samplePreparation, imagingMethod, resultNo, start, gene, chromosome, strand, position, startPosition, endPosition, hostName, excludeAnatomy);
				for (ImageDTO img : images){
					download.append(img.getTabbedToString(baseUrl + request.getRequestURI()) + "\n");
				}
				System.out.println("|-----" + download.toString());
				return new ResponseEntity<String>(download.toString(), getTextResponseHeaders(), HttpStatus.OK);
			}

		} catch (PhisSubmissionException | SolrServerException | PhisQueryException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Request could not be processed.", getJsonResponseHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

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
		ResponseEntity<String> resp = new ResponseEntity<String>(jsonResponse.toString(), getJsonResponseHeaders(), HttpStatus.OK);

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
		resp = new ResponseEntity<String>(as.getComplexAutosuggest(term, (type != null ? AutosuggestTypes.valueOf(type) : null), stage, imagingMethod, taxon, sampleType, imageGeneratedBy, hostName, resultNo), getJsonResponseHeaders(), HttpStatus.OK);
				
		return resp;
	}

	private HttpHeaders getTextResponseHeaders(){
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.TEXT_PLAIN);
		return responseHeaders;
	}

	private HttpHeaders getJsonResponseHeaders(){
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
				getJsonResponseHeaders(), HttpStatus.OK);
		
    }

	
	@RequestMapping(value="/getChannels", method=RequestMethod.GET)	
    public @ResponseBody ResponseEntity<String> getChannels(
            @RequestParam(value = "channelId", required = false) String channelId,
    		@RequestParam(value = "imageId", required = false) String imageId,
    		@RequestParam(value = "resultNo", required = false) Integer resultNo,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
		
		if (channelId != null){
			return new ResponseEntity<String>(cs.getChannelAsJsonString(channelId, resultNo), getJsonResponseHeaders(), HttpStatus.OK);
		}else {
			return new ResponseEntity<String>(cs.getChannels(imageId, resultNo), getJsonResponseHeaders(), HttpStatus.OK);
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
		return new ResponseEntity<String>(responseString, getJsonResponseHeaders(), HttpStatus.OK);
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
		
		return new ResponseEntity<String>(responseString, getJsonResponseHeaders(), HttpStatus.OK);
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
		
		return  new ResponseEntity<String>( ns.getAllReleases().toString(), getJsonResponseHeaders(), HttpStatus.OK);
		
	}

	@RequestMapping(value="/dataRelease", method=RequestMethod.GET)
	public String dataRelease(Model model){

		return "dataRelease";

	}
	
}



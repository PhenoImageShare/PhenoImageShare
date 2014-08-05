package uk.ac.ebi.phis.web.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
            @RequestParam(value = "phenotype", required = false) String phenotype,
            @RequestParam(value = "anatomy", required = false) String anatomy,
            @RequestParam(value = "gene", required = false) String gene,
            @RequestParam(value = "expressedFeature", required = false) String expressedGene,
            @RequestParam(value = "sex", required = false) String sex,
            @RequestParam(value = "taxon", required = false) String taxon,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "visualisationMethod", required = false) String visualisationMethod,
            @RequestParam(value = "samplePreparation", required = false) String samplePreparation,
            @RequestParam(value = "imagingMethod", required = false) String imagingMethod,
            @RequestParam(value = "resultNo", required = false) Integer resultNo,
            @RequestParam(value = "start", required = false) Integer start,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
				
		return is.getImage(phenotype, gene, anatomy, expressedGene, sex, taxon, stage, visualisationMethod, 
						samplePreparation, imagingMethod, resultNo, start);
    }
	/**
	 * 
	 * @param phenotypeId
	 * @param start
	 * @param resultNo
	 * @param model
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@RequestMapping(value="/getRois", method=RequestMethod.GET)	
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

	/**
	 * 
	 * @param phenotypeId
	 * @param start
	 * @param resultNo
	 * @param model
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@RequestMapping(value="/getChannels", method=RequestMethod.GET)	
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
}

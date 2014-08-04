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
	 * @param phenotypeId
	 * @param anatomyId
	 * @param geneId
	 * @param resultNo
	 * @param model
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@RequestMapping(value="/getImages", method=RequestMethod.GET)	
    public @ResponseBody String getExperimentalData(
            @RequestParam(value = "phenotypeId", required = false) String phenotypeId,
            @RequestParam(value = "anatomyId", required = false) String anatomyId,
            @RequestParam(value = "geneId", required = false) String geneId,
            @RequestParam(value = "resultNo", required = false) Integer resultNo,
            @RequestParam(value = "start", required = false) Integer start,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
				
		return is.getImage(phenotypeId, geneId, anatomyId, resultNo, start);
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
            @RequestParam(value = "roiId", required = true) String roiId,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
				
		return rs.getRoiAsJsonString(roiId);
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
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
			
		return cs.getChannelAsJsonString(channelId);
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

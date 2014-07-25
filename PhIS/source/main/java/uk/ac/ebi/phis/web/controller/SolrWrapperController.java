package uk.ac.ebi.phis.web.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uk.ac.ebi.phis.service.ImageService;

@Controller
@RequestMapping("/rest")
public class SolrWrapperController {


	@Autowired
	ImageService is;
	
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
    public @ResponseBody SolrDocumentList getExperimentalData(
            @RequestParam(value = "phenotypeId", required = false) String phenotypeId,
            @RequestParam(value = "anatomyId", required = false) String anatomyId,
            @RequestParam(value = "geneId", required = false) String geneId,
            @RequestParam(value = "resultNo", required = false) Integer resultNo,
    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
		
	
	//	model.addAttribute("res", "Loook, here's what you were looking for!\n "+ is.getImageByPhenotypeGeneAnatomy(phenotypeId, geneId, anatomyId));
		
	//	return "solrQuery";
		
		return is.getImageByPhenotypeGeneAnatomy(phenotypeId, geneId, anatomyId, resultNo);
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

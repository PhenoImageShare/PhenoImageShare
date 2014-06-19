package uk.ac.ebi.phis.web.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uk.ac.ebi.phis.service.ImageService;

@Controller
public class SolrWrapperController {


	@Autowired
	ImageService is;
	
	@RequestMapping(value="/getImages", method=RequestMethod.GET)	
    public String getExperimentalData(
            @RequestParam(value = "phenotype", required = false) String phenotype,
            @RequestParam(value = "anatomyId", required = false) String anatomyId,
            @RequestParam(value = "geneId", required = false) String geneId,

    		Model model
            ) throws SolrServerException, IOException, URISyntaxException {
		
	
		model.addAttribute("res", "Loook, here's what you were looking for!\n "+ is.getImageByPhenotypeGeneAnatomy(phenotype, geneId, anatomyId));
		return "solrQuery";
    }
}

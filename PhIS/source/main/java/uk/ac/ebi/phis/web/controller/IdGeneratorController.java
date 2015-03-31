package uk.ac.ebi.phis.web.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import uk.ac.ebi.phis.idgen.IdGenerator;


@Controller
@RequestMapping("/rest/idGen")
public class IdGeneratorController {
	
	@Autowired
	IdGenerator idGen;
	
	@RequestMapping(value="/getAnnotationId", method=RequestMethod.GET)	
    public @ResponseBody String getExperimentalData( Model model ) throws SolrServerException, IOException, URISyntaxException {
				
		JsonObject res = new JsonObject();
		res.addProperty("annotationId", idGen.getAnnotationId());
		
		return res.toString();
    }
	
	@RequestMapping(value="/", method=RequestMethod.GET)	
    public String showSomething( Model model ) throws SolrServerException, IOException, URISyntaxException {
	
		return "id_gen";
    }
}

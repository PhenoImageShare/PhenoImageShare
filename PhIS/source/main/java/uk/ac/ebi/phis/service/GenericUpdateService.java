package uk.ac.ebi.phis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.ac.ebi.phis.solrj.dto.RoiDTO;


public class GenericUpdateService {
	
	/**
	 * Will manage updates to all cores for each new annotation submitted by a user. 
	 */
	
	@Autowired 
	ImageService is;
	
	@Autowired
	AutosuggestService as;
	
	@Autowired
	ChannelService cs;
	
	@Autowired
	RoiService rs;
	
	public void updateCores(RoiDTO roi){
		
		// update roi core
		List<RoiDTO> docs = new ArrayList<>();
		docs.add(roi);
		rs.addBeans(docs);
		
		//TODO update channel core
		
		
		//TODO update image core
		
		//TODO update autosuggest service
		
	}
	
	
	
}

package uk.ac.ebi.phis.service;

import java.util.ArrayList;
import java.util.List;

import org.crsh.shell.impl.command.system.repl;
import org.springframework.beans.factory.annotation.Autowired;

import uk.ac.ebi.phis.solrj.dto.RoiDTO;


public class GenericUpdateService {
	
	/**
	 * Will manage updates to all cores for each new annotation submitted by a user. 
	 */
	
	@Autowired 
	ImageService is;
		
	@Autowired
	ChannelService cs;
	
	@Autowired
	RoiService rs;
	
	
	public void updateCores(RoiDTO roi){
		RoiDTO roiToReplace  = rs.getRoiById(roi.getId());
		is.updateImageFromRoi(roiToReplace, roi);
		rs.updateRoi(roi);
	}
	
	
	public void addToCores(RoiDTO roi){
		is.addToImageFromRoi(roi);
		rs.addRoi(roi);
		cs.addAssociatedRoi(roi);		
	}
	
	
	public void deleteFromCores(RoiDTO roi){
		cs.deleteAssociatedRoi(roi);
		is.deleteRoiRefferences(roi);
		rs.deleteRoi(roi.getId());
	}
	
}

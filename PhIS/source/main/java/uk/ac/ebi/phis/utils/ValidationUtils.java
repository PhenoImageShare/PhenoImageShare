package uk.ac.ebi.phis.utils;

import org.springframework.stereotype.Service;

import j.Coordinates;
import j.Dimensions;
import j.Image;


public class ValidationUtils {

	// positiveDimensions
	public boolean hasPositieDimensions (Dimensions d){
		if (d.getImageHeight() > 0 && d.getImageWidth() > 0){
			if (d.getImageDepth() == null || (d.getImageDepth() != null && d.getImageDepth() > 0)){
				return true;
			}
		}
		return false;
	}
	// percentageCoords()
	
	public boolean arePercentagesOk(Coordinates coords){
		for (Float p : coords.getXCoordinates().getEl()){
			if (p < 0 || p > 100){
				return false;
			}
		}for (Float p : coords.getYCoordinates().getEl()){
			if (p < 0 || p > 100){
				return false;
			}
		}
		if (coords.getZCoordinates() != null){
			for (Float p : coords.getXCoordinates().getEl()){
				if (p < 0 || p > 100){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean validateOntologyTerms(Image img){
		return false;
	}
}

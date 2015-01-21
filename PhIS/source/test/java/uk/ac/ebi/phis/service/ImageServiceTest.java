package uk.ac.ebi.phis.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import uk.ac.ebi.phis.solrj.dto.ImageDTO;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;


public class ImageServiceTest {

	ImageService is;
	RoiDTO roi ;
	String imageDocId;

	public ImageServiceTest(){
		is = new ImageService("http://localhost:8086/solr-example/images");
		roi = TestUtils.getTestRoi();
		imageDocId = roi.getAssociatedImage();
	}
	
	@Test
	public void testGetImageById(){
		assertTrue(is.getImageById(imageDocId) != null);
	}
	
	
	@Test 
	public void testUpdateFromRoi(){
		System.out.println(roi.getAssociatedImage());
		System.out.println(is.getSolrUrl());
		String imageId = roi.getAssociatedImage();
		
		is.updateImageFromRoi(roi);
		
		ImageDTO image = is.getImageById(imageId);
		
		assertTrue(hasRoiInList(image));
		assertTrue(phenotypeFieldsGotCopied(image));
		assertTrue(abnormalityInAnatomyGotCopied(image));
		assertTrue(depictedAnatomyFieldsGotCopied(image));
		assertTrue(observationsGotCopied(image));
		assertTrue(expressedInFieldsGotCopied(image));
		assertTrue(genericSearchGotFilled(image));
	}
	
	
	private boolean genericSearchGotFilled(ImageDTO img){
		boolean copiedRight = true;
		if (roi.getExpressedAnatomyId() != null){
			System.out.println(img.toString());
			copiedRight = img.getGenericSearch().containsAll(roi.getExpressedAnatomyId());
		}
		if (roi.getPhenotypeId() != null){
			copiedRight = copiedRight && img.getGenericSearch().containsAll(roi.getPhenotypeId());
		}
		if (roi.getExpressedAnatomyFreetext() != null){
			System.out.println(img.toString());
			copiedRight = copiedRight && img.getGenericSearch().containsAll(roi.getExpressedAnatomyFreetext());
		}
		if (roi.getPhenotypeFreetext() != null){
			copiedRight = copiedRight && img.getGenericSearch().containsAll(roi.getPhenotypeFreetext());
		}
		if (roi.getDepictedAnatomyId() != null){
			copiedRight = copiedRight && img.getGenericSearch().containsAll(roi.getDepictedAnatomyId());
		}
		if (roi.getDepictedAnatomyFreetext() != null){
			copiedRight = copiedRight && img.getGenericSearch().containsAll(roi.getDepictedAnatomyFreetext());
		}
		if (roi.getObservations() != null){
			copiedRight = copiedRight && img.getGenericSearch().containsAll(roi.getObservations());			
		}
		if (roi.getAbnormalityAnatomyId() != null){ 
			copiedRight = copiedRight && (img.getGenericSearch().containsAll(roi.getAbnormalityAnatomyId()));	
		}
		if(roi.getAbnormalityAnatomyFreetext() != null){
			copiedRight = copiedRight && img.getGenericSearch().containsAll(roi.getAbnormalityAnatomyFreetext());
		}
		
		return copiedRight;
	}
	
	private boolean hasRoiInList(ImageDTO img){
		return img.getAssociatedRoi().contains(roi.getId());
	}
	
	private boolean expressedInFieldsGotCopied(ImageDTO img){
		boolean copiedRight = true;
		if (roi.getExpressedAnatomyId() != null){
			System.out.println(img.toString());
			copiedRight = img.getExpressionInIdBag().containsAll(roi.getPhenotypeId());
		}
		if (roi.getPhenotypeFreetext() != null){
			copiedRight = copiedRight && img.getPhenotypeFreetextBag().containsAll(roi.getPhenotypeFreetext());
		}
		return copiedRight;
	}
	
	
	private boolean phenotypeFieldsGotCopied(ImageDTO img){
		boolean copiedRight = true;
		if (roi.getPhenotypeId() != null){
			System.out.println(img.toString());
			copiedRight = img.getPhenotypeIdBag().containsAll(roi.getPhenotypeId());
		}
		if (roi.getPhenotypeFreetext() != null){
			copiedRight = copiedRight && img.getPhenotypeFreetextBag().containsAll(roi.getPhenotypeFreetext());
		}
		return copiedRight;
	}
	
	private boolean depictedAnatomyFieldsGotCopied(ImageDTO img){
		boolean copiedRight = true;
		if (roi.getDepictedAnatomyId() != null){
			copiedRight = img.getDepictedAnatomyIdBag().containsAll(roi.getDepictedAnatomyId());
		}
		if (roi.getDepictedAnatomyFreetext() != null){
			copiedRight = copiedRight && img.getDepictedAnatomyFreetextBag().containsAll(roi.getDepictedAnatomyFreetext());
		}				
		return copiedRight;
	}
	
	private boolean observationsGotCopied(ImageDTO img){
		boolean copiedRight = true;
		if (roi.getObservations() != null){
			copiedRight = copiedRight && img.getObservationBag().containsAll(roi.getObservations());			
		}
		return copiedRight;
	}
	
	private boolean abnormalityInAnatomyGotCopied(ImageDTO img){
		boolean copiedRight = true;
		if (roi.getAbnormalityAnatomyId() != null){ 
			copiedRight = copiedRight && (img.getAbnormalAnatomyIdBag().containsAll(roi.getAbnormalityAnatomyId()));	
		}
		if(roi.getAbnormalityAnatomyFreetext() != null){
			copiedRight = copiedRight && img.getAbnormalAnatomyFreetextBag().containsAll(roi.getAbnormalityAnatomyFreetext());
		}
		return copiedRight;
	}
}

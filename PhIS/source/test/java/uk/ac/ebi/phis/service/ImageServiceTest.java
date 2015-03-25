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
	RoiService rs;
	RoiDTO roi ;
	String imageDocId;
	
	
	public ImageServiceTest(){
		is = new ImageService("http://localhost:8086/solr-example/images");
		rs = new RoiService("http://localhost:8086/solr-example/rois");
		roi = TestUtils.getTestRoi();
		imageDocId = roi.getAssociatedImage();
	}
	
	@Test
	public void testGetImageById(){
		assertTrue(is.getImageDTOById(imageDocId) != null);
	}
	
	@Test 
	public void testUpdateFromRoi(){
		is.addToImageFromRoi(roi);
		RoiDTO roiToReplace = rs.getRoiById(roi.getId());
		ArrayList<String> abnormalityAnatomyFreetext = new ArrayList<String>();
		abnormalityAnatomyFreetext.add("OTHER ABNORMALITY TO TEST UPDATES");
		roi.setAbnormalityAnatomyFreetext(abnormalityAnatomyFreetext);
		is.updateImageFromRoi(roiToReplace, roi);
		testAddFromRoi();
		is.deleteRoiRefferences(roi);
	}
	
	@Test
	public void testDeleteRoi(){
		
		is.addToImageFromRoi(roi);
		ImageDTO originalImage = is.getImageDTOById(roi.getAssociatedImage());
		
		is.deleteRoiRefferences(roi);
		ImageDTO newImage = is.getImageDTOById(roi.getAssociatedImage());
		
		System.out.println("\n\nNew image: " + newImage.toString());
		assertTrue(phenotypeFieldsGotDeleted(originalImage, newImage));
		assertTrue(abnormalityInAnatomyGotDeleted(originalImage, newImage));
		assertTrue(depictedAnatomyFieldsGotDeleted(originalImage, newImage));
		assertTrue(observationsGotDeleted(originalImage, newImage));
		assertTrue(expressedInFieldsGotDeleted(originalImage, newImage));
		assertFalse(hasRoiInList(newImage));
	}
	
	@Test 
	public void testAddFromRoi(){
		System.out.println(roi.getAssociatedImage());
		System.out.println(is.getSolrUrl());
		String imageId = roi.getAssociatedImage();
				
		is.addToImageFromRoi(roi);
		
		ImageDTO image = is.getImageDTOById(imageId);
		
		assertTrue(hasRoiInList(image));
		assertTrue(phenotypeFieldsGotCopied(image));
		assertTrue(abnormalityInAnatomyGotCopied(image));
		assertTrue(depictedAnatomyFieldsGotCopied(image));
		assertTrue(observationsGotCopied(image));
		assertTrue(expressedInFieldsGotCopied(image));
		assertTrue(genericSearchGotFilled(image));
		System.out.println("AFTER");
		System.out.println(image);
		
		is.deleteRoiRefferences(roi);
	}
	
	private boolean observationsGotDeleted(ImageDTO original, ImageDTO img){
		
		boolean gotDeleted = true;
		for (String o : roi.getObservations()){
			if (img.getObservationBag() != null){
				gotDeleted = gotDeleted && (img.getObservationBag().lastIndexOf(o) < original.getObservationBag().lastIndexOf(o));
			}
		}
		return gotDeleted;
	}
	
	private boolean expressedInFieldsGotDeleted(ImageDTO original, ImageDTO img){
		
		boolean gotDeleted = true;
		if (roi.getExpressedAnatomyId() != null){
			for (String o : roi.getExpressedAnatomyId()){
				if (img.getExpressionInIdBag() != null){
					gotDeleted = gotDeleted && (img.getExpressionInIdBag().lastIndexOf(o) < original.getExpressionInIdBag().lastIndexOf(o));
				}
			}
		}
		if (roi.getExpressedAnatomyFreetext() != null){
			for (String o : roi.getExpressedAnatomyFreetext()){
				if (img.getExpressionInFreetextBag() != null){
					gotDeleted = gotDeleted && (img.getExpressionInFreetextBag().lastIndexOf(o) < original.getExpressionInFreetextBag().lastIndexOf(o));
				}
			}
		}
		return gotDeleted;
	}
	
	private boolean depictedAnatomyFieldsGotDeleted(ImageDTO original, ImageDTO img){
		
		boolean gotDeleted = true;
		for (String o : roi.getDepictedAnatomyId()){
			if (img.getDepictedAnatomyIdBag() != null){
				gotDeleted = gotDeleted && (img.getDepictedAnatomyIdBag().lastIndexOf(o) < original.getDepictedAnatomyIdBag().lastIndexOf(o));
			}
		}
		for (String o : roi.getDepictedAnatomyFreetext()){
			if (img.getDepictedAnatomyFreetextBag() != null){
				gotDeleted = gotDeleted && (img.getDepictedAnatomyFreetextBag().lastIndexOf(o) < original.getDepictedAnatomyFreetextBag().lastIndexOf(o));
			}
		}
		return gotDeleted;
	}
	
	private boolean phenotypeFieldsGotDeleted(ImageDTO original, ImageDTO img){
		
		boolean gotDeleted = true;
		for (String o : roi.getPhenotypeId()){
			if (img.getPhenotypeIdBag() != null){
				gotDeleted = gotDeleted && (img.getPhenotypeIdBag().lastIndexOf(o) < original.getPhenotypeIdBag().lastIndexOf(o));
			}
		}
		for (String o : roi.getPhenotypeFreetext()){
			if (img.getPhenotypeFreetextBag() != null){
				gotDeleted = gotDeleted && (img.getPhenotypeFreetextBag().lastIndexOf(o) < original.getPhenotypeFreetextBag().lastIndexOf(o));
			}
		}
		return gotDeleted;
	}
	

	private boolean abnormalityInAnatomyGotDeleted(ImageDTO original, ImageDTO img){
		
		boolean gotDeleted = true;
		for (String o : roi.getAbnormalityAnatomyId()){
			if (img.getAbnormalAnatomyIdBag() != null){
				gotDeleted = gotDeleted && (img.getAbnormalAnatomyIdBag().lastIndexOf(o) < original.getAbnormalAnatomyIdBag().lastIndexOf(o));
			}
		}
		for (String o : roi.getAbnormalityAnatomyFreetext()){
			if (img.getAbnormalAnatomyFreetextBag() != null){
				gotDeleted = gotDeleted && (img.getAbnormalAnatomyFreetextBag().lastIndexOf(o) < original.getAbnormalAnatomyFreetextBag().lastIndexOf(o));
			}
		}
		return gotDeleted;
	}
	
	private boolean genericSearchGotFilled(ImageDTO img){
		boolean copiedRight = true;
		if (roi.getExpressedAnatomyId() != null){
			copiedRight = img.getGenericSearch().containsAll(roi.getExpressedAnatomyId());
		}
		if (roi.getPhenotypeId() != null){
			copiedRight = copiedRight && img.getGenericSearch().containsAll(roi.getPhenotypeId());
		}
		if (roi.getExpressedAnatomyFreetext() != null){
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
			System.out.println("\n\n look : " + img.getExpressionInIdBag().containsAll(roi.getExpressedAnatomyId()) );
			copiedRight = img.getExpressionInIdBag().containsAll(roi.getExpressedAnatomyId());
		}
		if (roi.getExpressedAnatomyFreetext() != null){
			System.out.println("\n\n LOOK : " + img.getExpressionInFreetextBag().containsAll(roi.getExpressedAnatomyFreetext()) );
			copiedRight = copiedRight && img.getExpressionInFreetextBag().containsAll(roi.getExpressedAnatomyFreetext());
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

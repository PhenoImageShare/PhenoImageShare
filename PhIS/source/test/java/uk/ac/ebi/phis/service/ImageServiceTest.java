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
	String imageDocId = "komp2_112967";

	public ImageServiceTest(){
		is = new ImageService("http://localhost:8086/solr-example/images");
		String id = "madeup_id";
		List<String> associatedChannel = new ArrayList<>();
		associatedChannel.add("komp2_channel_112967_0");
		String associatedImage = imageDocId;
		List<String> depictedAnatomyId = new ArrayList<>();
		depictedAnatomyId.add("MADEUP_0001");
		List<String> depictedAnatomyTerm = new ArrayList<>();
		depictedAnatomyTerm.add("some anatomy part");
		List<String> depictedAnatomyFreetext = new ArrayList<>();
		depictedAnatomyFreetext.add("some anatomy freetext"); 
		List<String> depictedAnatomyAnnotationSource = new ArrayList<>();
		depictedAnatomyAnnotationSource.add("USER_ADDED");
		List<String> abnormalityAnatomyId = new ArrayList<>();
		abnormalityAnatomyId.add("MADEUP_0921");
		List<String> abnormalityAnatomyTerm = new ArrayList<>();
		abnormalityAnatomyTerm.add("anat made up term No 2");
		List<String> abnormalityAnatomyFreetext = new ArrayList<>();
		abnormalityAnatomyFreetext.add("some abnormal anatomy freetext No 2");
		List<String> abnormalityAnatomyAnnotationSource = new ArrayList<>();
		abnormalityAnatomyAnnotationSource.add("USER_ADDED"); 
		List<String> phenotypeId = new ArrayList<>();
		phenotypeId.add("MADEUP_928013");
		List<String> phenotypeTerm = new ArrayList<>();
		phenotypeTerm.add("phen term");
		List<String> phenotypeFreetext = new ArrayList<>();
		phenotypeFreetext.add("phen freetext");
		List<String> observations = new ArrayList<>();
		 observations.add("Observing carefully this..." );
		List<Float> xCoordinates = new ArrayList<>();
		xCoordinates.add((float) 2);
		List<Float> yCoordinates = new ArrayList<>();
		yCoordinates.add((float) 4);
		List<Float> zCoordinates = new ArrayList<>();
		zCoordinates.add((float) 12);
		
		roi = new RoiDTO(id, associatedChannel, associatedImage, depictedAnatomyId, depictedAnatomyTerm, depictedAnatomyFreetext, 
			depictedAnatomyAnnotationSource, abnormalityAnatomyId, abnormalityAnatomyTerm, abnormalityAnatomyFreetext, abnormalityAnatomyAnnotationSource, 
			phenotypeId, phenotypeTerm, phenotypeFreetext, observations, xCoordinates, yCoordinates, zCoordinates);
		
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
//		assertTrue(depictedAnatomyFieldsGotCopied(image));
		assertTrue(observationsGotCopied(image));
		
		//TODO generic search
	}
	
	private boolean hasRoiInList(ImageDTO img){
		return img.getAssociatedRoi().contains(roi.getId());
	}
	
	private boolean phenotypeFieldsGotCopied(ImageDTO img){
		boolean copiedRight = true;
		if (roi.getPhenotypeId() != null){
			System.out.println(img.toString());
			copiedRight = copiedRight && img.getPhenotypeIdBag().containsAll(roi.getPhenotypeId());
		}
		if (roi.getPhenotypeFreetext() != null){
			copiedRight = copiedRight && img.getPhenotypeFreetextBag().containsAll(roi.getPhenotypeFreetext());
		}
		return copiedRight;
	}
	
	private boolean depictedAnatomyFieldsGotCopied(ImageDTO img){
		boolean copiedRight = true;
		if (roi.getDepictedAnatomyId() != null){
			copiedRight = copiedRight && img.getPhenotypeIdBag().containsAll(roi.getDepictedAnatomyId());
		}
		if (roi.getDepictedAnatomyFreetext() != null){
			copiedRight = copiedRight && img.getPhenotypeFreetextBag().containsAll(roi.getDepictedAnatomyFreetext());
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

package uk.ac.ebi.phis.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import uk.ac.ebi.phis.solrj.dto.ImageDTO;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;


public class ImageServiceTest {

	ImageService is;
	RoiDTO roi ;

	public ImageServiceTest(){
		is = new ImageService("http://localhost:8086/solr-example/images");
		String id = "madeup_id";
		List<String> associatedChannel = new ArrayList<>();
		associatedChannel.add("komp2_channel_112967_0");
		String associatedImage = "komp2_112967";
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
	public void testUpdateFromRoi(){
		String imageId = roi.getAssociatedImage();
		ImageDTO image = is.getImageById(imageId);
		
		is.updateImageFromRoi(roi);
		
//		assertTrue(hasRoiInList(image, roi));
//		assertTrue(phenotypeFieldsGotCopied(image, roi));
		assertTrue(abnormalityInAnatomyGotCopied(image, roi));
//		assertTrue(depictedAnatomyFieldsGotCopied(image, roi));
//		assertTrue(observationsGotCopied(image, roi));
		
		//TODO generic search
	}
	
	private boolean hasRoiInList(ImageDTO img, RoiDTO roi){
		return img.getAssociatedRoi().contains(roi.getId());
	}
	
	private boolean phenotypeFieldsGotCopied(ImageDTO img, RoiDTO roi){
		boolean copiedRight = false;
		
		copiedRight &= (roi.getPhenotypeId() == null || img.getPhenotypeIdBag().contains(roi.getPhenotypeId()));
		copiedRight &= (roi.getPhenotypeId() == null || img.getPhenotypeFreetextBag().contains(roi.getPhenotypeFreetext()));		
		return copiedRight;
	}
	
	private boolean depictedAnatomyFieldsGotCopied(ImageDTO img, RoiDTO roi){
		boolean copiedRight = false;
		copiedRight &= img.getPhenotypeIdBag().contains(roi.getDepictedAnatomyId());
		copiedRight &= img.getPhenotypeFreetextBag().contains(roi.getDepictedAnatomyFreetext());				
		return copiedRight;
	}
	
	private boolean observationsGotCopied(ImageDTO img, RoiDTO roi){
		boolean copiedRight = false;
		copiedRight &= img.getObservations().contains(roi.getObservations());			
		return copiedRight;
	}
	
	private boolean abnormalityInAnatomyGotCopied(ImageDTO img, RoiDTO roi){
		boolean copiedRight = true;
		if (roi.getAbnormalityAnatomyId() != null || roi.getAbnormalityAnatomyFreetext() != null){
			copiedRight = false;
			copiedRight &= (img.getAbnormalAnatomyIdBag().contains(roi.getAbnormalityAnatomyId()));	
			copiedRight &= img.getAbnormalAnatomyFreetextBag().contains(roi.getAbnormalityAnatomyFreetext());
		}
		return copiedRight;
	}
}

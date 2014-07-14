package uk.ac.ebi.phis.importer;

import j.Channel;
import j.Doc;
import j.Image;
import j.Roi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import uk.ac.ebi.phis.utils.ValidationUtils;

public class BatchXmlUploader {


	HashMap<String, Image> imageIdMap = new HashMap<>();
	HashMap<String, Channel> channelIdMap = new HashMap<>();
	HashMap<String, Roi> roiIdMap = new HashMap<>();
	
	ClassLoader classloader;
	
	ValidationUtils vu = new ValidationUtils();
	
	public BatchXmlUploader(){
		classloader = Thread.currentThread().getContextClassLoader();	
	}
		
	public boolean validate(String xmlLocation){
		InputStream xsd;
		InputStream xml;
		boolean isValid = false;
	    try {
	        xsd = classloader.getResourceAsStream("phisSchema.xsd");
	        xml = classloader.getResourceAsStream(xmlLocation);
	        isValid = validateAgainstXSD(xml, xsd);
	        xsd.close();
	        xml.close();
	        isValid = (isValid && checkInformation(xmlLocation));
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return isValid;
	}
	
	boolean validateAgainstXSD(InputStream xml, InputStream xsd)
	{
	    try
	    {
	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(new StreamSource(xsd));
	        Validator validator = schema.newValidator();
	        validator.validate(new StreamSource(xml));
	        return true;
	    } catch (SAXException e) {
	    	  System.out.println("NOT valid");
	    	  System.out.println("Reason: " + e.getLocalizedMessage());	   
	    } catch (IOException e) {
			e.printStackTrace();
		} 
        return false;
	}
	
	boolean checkInformation(String xmlLocation){
		
		// Unmarshal XML
		Doc doc = convertXmlToObjects(xmlLocation);
		
		imageIdMap = new HashMap<>();
		channelIdMap = new HashMap<>();
		roiIdMap = new HashMap<>();

		// Create maps <id, Object> for quick reference
		for (Image img : doc.getImage()){
			imageIdMap.put(img.getId(), img);
		}
		for (Roi roi : doc.getRoi()){
			roiIdMap.put(roi.getId(), roi);
		}
		for (Channel ch: doc.getChannel()){
			channelIdMap.put(ch.getId(), ch);
		}
		
		
		// Check associated image/channel/roi ids are valid a) they exist , b) the link is reflezive
		boolean res = checkIdsReferenceEWxistingObjects();
		if (!res){
			return false;
		}

		for (Image img : imageIdMap.values()){

			// Check ontoloy fields contain ontology IDs and they are from the right ontology
			// Check label & id match
			if (!vu.hasValidOntologyTerms(img)){
				System.out.println("there was something wrong with the ontology terms for img id = " + img.getId());
				
			}
			
			// positive dimensions
			if (! vu.hasPositieDimensions(img.getImageDescription().getImageDimensions())){
				System.out.println("Dimensions are not positive! Validation failed.");
				return false;
			}
			
		}
		
		for (Roi roi : roiIdMap.values()){
			
			// percentages
			if(! vu.arePercentagesOk(roi.getCoordinates())){
				return false;
			}			

			// Check ontoloy fields contain ontology IDs and they are from the right ontology
			// Check label & id match
			if (!vu.isValidOntologyTerms(roi)){
				System.out.println("there was something wrong with the ontology terms for roi id = " + roi.getId());
				return false;
			}
		}
		return true;
	}

	
	private boolean checkIdsReferenceEWxistingObjects(){
		// Associated roi & channel for image really exist
		for (Image img : imageIdMap.values()) {
			if (img.getAssociatedRoi() != null) {
				for (String roiId : img.getAssociatedRoi().getEl()) {
					if (!roiIdMap.containsKey(roiId)) {
						System.out
								.println("roi id referenced without existing in image id = "
										+ img.getId());
						return false;
					}
				}
			}
			if (img.getAssociatedChannel() != null) {
				for (String channelId : img.getAssociatedChannel().getEl()) {
					if (!channelIdMap.containsKey(channelId)) {
						System.out
								.println("channel id referenced without existing in image id = "
										+ img.getId());
						return false;
					}
				}
			}
		}

		// Associated roi & image for channels really exist
		for (Channel channel : channelIdMap.values()) {
			if (channel.getAssociatedRoi() != null) {
				for (String roiId : channel.getAssociatedRoi().getEl()) {
					if (!roiIdMap.containsKey(roiId)) {
						System.out
								.println("roi id referenced without existing in channel id = "
										+ channel.getId());
						return false;
					}
				}
			}
			if (channel.getAssociatedImage() != null) {
				if (!imageIdMap.containsKey(channel.getAssociatedImage())) {
					System.out
							.println("image id referenced without existing in channel id = "
									+ channel.getId());
					return false;
				}
			}
		}

		// Associated image & channel for roi really exist
		for (Roi roi : roiIdMap.values()) {
			if (roi.getAssociatedChannel() != null) {
				for (String channelId : roi.getAssociatedChannel().getEl()) {
					if (!channelIdMap.containsKey(channelId)) {
						System.out
								.println("channel id referenced without existing in roi id = "
										+ roi.getId());
						return false;
					}
				}
			}
			if (roi.getAssociatedImage() != null) {
				if (!imageIdMap.containsKey(roi.getAssociatedImage())) {
					System.out
							.println("image id referenced without existing in roi id = "
									+ roi.getId());
					return false;
				}
			}
		}
		return true;
	}
	
	
	private Doc convertXmlToObjects(String xmlFullPathLocation){
		 try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Doc.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Doc doc = (Doc) jaxbUnmarshaller.unmarshal(classloader.getResourceAsStream(xmlFullPathLocation));
			return doc;
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		 return null;
	}
}

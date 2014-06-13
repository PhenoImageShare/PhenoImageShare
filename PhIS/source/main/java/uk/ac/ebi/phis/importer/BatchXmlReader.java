package uk.ac.ebi.phis.importer;

import j.Doc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class BatchXmlReader {


	ClassLoader classloader;
	
	public BatchXmlReader(){
		classloader = Thread.currentThread().getContextClassLoader();
	}
	
	public Doc convertXmlToObjects(String xmlFullPathLocation){
		 try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Doc.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Doc doc = (Doc) jaxbUnmarshaller.unmarshal(classloader.getResourceAsStream(xmlFullPathLocation));
			System.out.println(doc.getImage().get(0));
			return doc;
		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
		 return null;
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
}

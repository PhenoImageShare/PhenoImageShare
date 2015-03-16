package uk.ac.ebi.phis.xmlDump;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import uk.ac.ebi.phis.jaxb.Doc;
import uk.ac.ebi.phis.jaxb.emage.ImageContentSummary;
import uk.ac.ebi.phis.jaxb.emage.ImageContentSummary.ImageRecord;


public class EmageXmlGenerator {

	private final static String AGGREGATE_URL = "http://testwww.emouseatlas.org/emagewebservices/phis/image/listall";
	ArrayList<String> urlList;
	
	
	public Doc aggregateXml(){
		
		ArrayList<String> urls = getUrls();
		
		for (int i = 0; i < urls.size(); i++){
			Doc temp = readSingleDoc(urls.get(i));
			
			
		}
		
		return null;
	}
	
	
	private Doc readSingleDoc(String link){
		
		Doc doc = null;
		
		try {
			
			URL url = new URL(link);
			InputStream stream = url.openStream();
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Doc.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			System.out.println(">>> unmarshaler not null  " + (jaxbUnmarshaller != null));
			doc = (Doc) jaxbUnmarshaller.unmarshal(stream);
			return doc;
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return doc;
	}
	
	private ArrayList<String> getUrls(){
		
		ArrayList<String> res = new ArrayList<>();
		
		try {
		
			URL url = new URL(AGGREGATE_URL);
			InputStream stream = url.openStream();
			
			JAXBContext jaxbContext = JAXBContext.newInstance(ImageContentSummary.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			System.out.println(">>> >>> unmarshaler not null  " + (jaxbUnmarshaller != null));
			ImageContentSummary ics = (ImageContentSummary) jaxbUnmarshaller.unmarshal(stream);
			
			for (ImageRecord record : ics.getImageRecord()){
				res.add(record.getURL());
			}
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return urlList;
	}
	
	
}

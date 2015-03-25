package uk.ac.ebi.phis.xmlDump;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import uk.ac.ebi.phis.jaxb.Doc;
import uk.ac.ebi.phis.jaxb.emage.ImageContentSummary;
import uk.ac.ebi.phis.jaxb.emage.ImageContentSummary.ImageRecord;


public class EmageXmlGenerator {

	private final static String AGGREGATE_URL = "http://testwww.emouseatlas.org/emagewebservices/phis/image/listall";
	ArrayList<String> urlList;
	
	
	public Doc aggregateXml(){
		
		ArrayList<String> urls = getUrls();
		Doc doc = new Doc();
		
		for (int i = 0; i < urls.size(); i++){
			Doc temp = readSingleDoc(urls.get(i));
			doc.getImage().addAll(temp.getImage());
			if (temp.getRoi() != null){
				doc.getRoi().addAll(temp.getRoi());
			}
			if (temp.getChannel() != null){
				doc.getChannel().addAll(temp.getChannel());
			}
			
			if (i == 1){
				break;
			}
		}
		
		File file = new File("source/main/resources/emageExport.xml");
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Doc.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(doc, file);
			jaxbMarshaller.marshal(doc, System.out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
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
			doc = (Doc) jaxbUnmarshaller.unmarshal(stream);
			return doc;
			
		} catch (JAXBException | IOException e) {
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
			System.out.println(">>> >>> image content summary  " + ics.getImageRecord().size());
			for (ImageRecord record : ics.getImageRecord()){
				res.add(record.getURL());
			}
			
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	
}

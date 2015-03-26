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
import uk.ac.ebi.phis.jaxb.Image;
import uk.ac.ebi.phis.jaxb.OntologyTerm;
import uk.ac.ebi.phis.jaxb.Sex;
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
			for (Image image: doc.getImage()){
				String emageId = image.getOrganism().getStage().getTermId();
				image.getOrganism().setStage(getTheilerStageOntologyTerm(emageId));
				//doc.
			}
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
	
	private OntologyTerm getTheilerStageOntologyTerm(String emageStageId){
		
		OntologyTerm ot = new OntologyTerm();
		
		switch(emageStageId){
			case "TS1": ot.setTermId("MmusDv_0000003");
			ot.setTermLabel("Theiler stage 01");
			case "TS2": ot.setTermId("MmusDv_0000005");
			ot.setTermLabel("Theiler stage 02");
			case "TS3": ot.setTermId("MmusDv_0000006");
			ot.setTermLabel("Theiler stage 03");
			case "TS4": ot.setTermId("MmusDv_0000008");
			ot.setTermLabel("Theiler stage 04");
			case "TS5": ot.setTermId("MmusDv_0000009");
			ot.setTermLabel("Theiler stage 05");
			case "TS6": ot.setTermId("MmusDv_0000010");
			ot.setTermLabel("Theiler stage 06");
			case "TS7": ot.setTermId("MmusDv_0000011");
			ot.setTermLabel("Theiler stage 07");
			case "TS8": ot.setTermId("MmusDv_0000012");
			ot.setTermLabel("Theiler stage 08");
			case "TS9": ot.setTermId("MmusDv_0000014");
			ot.setTermLabel("Theiler stage 09");
			case "TS10": ot.setTermId("MmusDv_0000015");
			ot.setTermLabel("Theiler stage 10");
			case "TS11": ot.setTermId("MmusDv_0000017");
			ot.setTermLabel("Theiler stage 11");
			case "TS12": ot.setTermId("MmusDv_0000019");
			ot.setTermLabel("Theiler stage 12");
			case "TS13": ot.setTermId("MmusDv_0000020");
			ot.setTermLabel("Theiler stage 13");
			case "TS14": ot.setTermId("MmusDv_0000021");
			ot.setTermLabel("Theiler stage 14");
			case "TS15": ot.setTermId("MmusDv_0000022");
			ot.setTermLabel("Theiler stage 15");
			case "TS16": ot.setTermId("MmusDv_0000023");
			ot.setTermLabel("Theiler stage 16");
			case "TS17": ot.setTermId("MmusDv_0000024");
			ot.setTermLabel("Theiler stage 17");
			case "TS18": ot.setTermId("MmusDv_0000025");
			ot.setTermLabel("Theiler stage 18");
			case "TS19": ot.setTermId("MmusDv_0000026");
			ot.setTermLabel("Theiler stage 19");
			case "TS20": ot.setTermId("MmusDv_0000027");
			ot.setTermLabel("Theiler stage 20");
			case "TS21": ot.setTermId("MmusDv_0000028");
			ot.setTermLabel("Theiler stage 21");
			case "TS22": ot.setTermId("MmusDv_0000029");
			ot.setTermLabel("Theiler stage 22");
			case "TS23": ot.setTermId("MmusDv_0000032");
			ot.setTermLabel("Theiler stage 23");
			case "TS24": ot.setTermId("MmusDv_0000033");
			ot.setTermLabel("Theiler stage 24");
			case "TS25": ot.setTermId("MmusDv_0000034");
			ot.setTermLabel("Theiler stage 25");
			case "TS26": ot.setTermId("MmusDv_0000035");
			ot.setTermLabel("Theiler stage 26");
			case "TS27": ot.setTermId("MmusDv_0000036");
			ot.setTermLabel("Theiler stage 27");
			case "TS28": ot.setTermId("MmusDv_0000037");
			ot.setTermLabel("Theiler stage 28");
			case "TS11b": ot.setTermId("MmusDv_0000093");
			ot.setTermLabel("Theiler stage 11b");
			case "TS11c": ot.setTermId("MmusDv_0000094");
			ot.setTermLabel("Theiler stage 11c");
			case "TS11d": ot.setTermId("MmusDv_0000095");
			ot.setTermLabel("Theiler stage 11d");
			case "TS09a": ot.setTermId("MmusDv_0000103");
			ot.setTermLabel("Theiler stage 09a");
			case "TS09b": ot.setTermId("MmusDv_0000104");
			ot.setTermLabel("Theiler stage 09b");
			case "TS10a": ot.setTermId("MmusDv_0000105");
			ot.setTermLabel("Theiler stage 10a");
			case "TS10b": ot.setTermId("MmusDv_0000106");
			ot.setTermLabel("Theiler stage 10b");
		}
		return ot;
	}
		
}

package uk.ac.ebi.phis.xmlDump;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.xml.sax.SAXException;

import uk.ac.ebi.phis.importer.BatchXmlUploader;
import uk.ac.ebi.phis.jaxb.Doc;
import uk.ac.ebi.phis.jaxb.Image;
import uk.ac.ebi.phis.jaxb.OntologyTerm;
import uk.ac.ebi.phis.jaxb.Sex;
import uk.ac.ebi.phis.jaxb.emage.ImageContentSummary;
import uk.ac.ebi.phis.jaxb.emage.ImageContentSummary.ImageRecord;
import uk.ac.ebi.phis.service.ChannelService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.service.RoiService;


public class EmageXmlGenerator {

	private final static String AGGREGATE_URL = "http://testwww.emouseatlas.org/emagewebservices/phis/image/listall";
	ArrayList<String> urlList;
	
	public Doc aggregateXml(){
		
		ArrayList<String> urls = getUrls();
		Doc doc = new Doc();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream xsd;
		try {
			for (int i = 0; i < urls.size(); i++){
				xsd = new FileInputStream("/Users/tudose/git/PhenoImageShare_Jul_22/PhIS/source/main/resources/phisSchema.xsd");
				Doc temp = readSingleDoc(urls.get(i), xsd);
				doc.getImage().addAll(temp.getImage());
				if (temp.getRoi() != null){
					doc.getRoi().addAll(temp.getRoi());
				}
				if (temp.getChannel() != null){
					doc.getChannel().addAll(temp.getChannel());
				}
				if (i % 100 == 0){
					System.out.println("Added "  + i + " documents");
				}
			//	if (i == 1000){
			//		break;
			//	}
			}
			
			File file = new File("source/main/resources/emageExport.xml");
			JAXBContext jaxbContext;
			
				jaxbContext = JAXBContext.newInstance(Doc.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				 
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
				jaxbMarshaller.marshal(doc, file);
	//			jaxbMarshaller.marshal(doc, System.out);
				
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	boolean validateAgainstXSD(InputStream xml, InputStream xsd) {

		try {
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
	
	private Doc readSingleDoc(String link, InputStream xsd){

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
			}
			
			stream = url.openStream();	
			
			if (!validateAgainstXSD (stream, xsd)){
				System.out.println(doc.getImage().get(0).getId() + "  " + link);
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
			ImageContentSummary ics = (ImageContentSummary) jaxbUnmarshaller.unmarshal(stream);
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
			case "TS01":
				ot.setTermId("MmusDv_0000003");
				ot.setTermLabel("Theiler stage 01");
				break;
			case "TS02":
				ot.setTermId("MmusDv_0000005");
				ot.setTermLabel("Theiler stage 02");
				break;
			case "TS03":
				ot.setTermId("MmusDv_0000006");
				ot.setTermLabel("Theiler stage 03");
				break;
			case "TS04":
				ot.setTermId("MmusDv_0000008");
				ot.setTermLabel("Theiler stage 04");
				break;
			case "TS05":
				ot.setTermId("MmusDv_0000009");
				ot.setTermLabel("Theiler stage 05");
				break;
			case "TS06":
				ot.setTermId("MmusDv_0000010");
				ot.setTermLabel("Theiler stage 06");
				break;
			case "TS07":
				ot.setTermId("MmusDv_0000011");
				ot.setTermLabel("Theiler stage 07");
				break;
			case "TS08":
				ot.setTermId("MmusDv_0000012");
				ot.setTermLabel("Theiler stage 08");
				break;
			case "TS09":
				ot.setTermId("MmusDv_0000014");
				ot.setTermLabel("Theiler stage 09");
				break;
			case "TS10":
				ot.setTermId("MmusDv_0000015");
				ot.setTermLabel("Theiler stage 10");
				break;
			case "TS11":
				ot.setTermId("MmusDv_0000017");
				ot.setTermLabel("Theiler stage 11");
				break;
			case "TS12":
				ot.setTermId("MmusDv_0000019");
				ot.setTermLabel("Theiler stage 12");
				break;
			case "TS13":
				ot.setTermId("MmusDv_0000020");
				ot.setTermLabel("Theiler stage 13");
				break;
			case "TS14":
				ot.setTermId("MmusDv_0000021");
				ot.setTermLabel("Theiler stage 14");
				break;
			case "TS15":
				ot.setTermId("MmusDv_0000022");
				ot.setTermLabel("Theiler stage 15");
				break;
			case "TS16":
				ot.setTermId("MmusDv_0000023");
				ot.setTermLabel("Theiler stage 16");
				break;
			case "TS17":
				ot.setTermId("MmusDv_0000024");
				ot.setTermLabel("Theiler stage 17");
				break;
			case "TS18":
				ot.setTermId("MmusDv_0000025");
				ot.setTermLabel("Theiler stage 18");
				break;
			case "TS19":
				ot.setTermId("MmusDv_0000026");
				ot.setTermLabel("Theiler stage 19");
				break;
			case "TS20":
				ot.setTermId("MmusDv_0000027");
				ot.setTermLabel("Theiler stage 20");
				break;
			case "TS21":
				ot.setTermId("MmusDv_0000028");
				ot.setTermLabel("Theiler stage 21");
				break;
			case "TS22":
				ot.setTermId("MmusDv_0000029");
				ot.setTermLabel("Theiler stage 22");
				break;
			case "TS23":
				ot.setTermId("MmusDv_0000032");
				ot.setTermLabel("Theiler stage 23");
				break;
			case "TS24":
				ot.setTermId("MmusDv_0000033");
				ot.setTermLabel("Theiler stage 24");
				break;
			case "TS25":
				ot.setTermId("MmusDv_0000034");
				ot.setTermLabel("Theiler stage 25");
				break;
			case "TS26":
				ot.setTermId("MmusDv_0000035");
				ot.setTermLabel("Theiler stage 26");
				break;
			case "TS27":
				ot.setTermId("MmusDv_0000036");
				ot.setTermLabel("Theiler stage 27");
				break;
			case "TS28":
				ot.setTermId("MmusDv_0000037");
				ot.setTermLabel("Theiler stage 28");
				break;
			case "TS11b":
				ot.setTermId("MmusDv_0000093");
				ot.setTermLabel("Theiler stage 11b");
				break;
			case "TS11c":
				ot.setTermId("MmusDv_0000094");
				ot.setTermLabel("Theiler stage 11c");
				break;
			case "TS11d":
				ot.setTermId("MmusDv_0000095");
				ot.setTermLabel("Theiler stage 11d");
				break;
			case "TS09a":
				ot.setTermId("MmusDv_0000103");
				ot.setTermLabel("Theiler stage 09a");
				break;
			case "TS09b":
				ot.setTermId("MmusDv_0000104");
				ot.setTermLabel("Theiler stage 09b");
				break;
			case "TS10a":
				ot.setTermId("MmusDv_0000105");
				ot.setTermLabel("Theiler stage 10a");
				break;
			case "TS10c":
				ot.setTermId("MmusDv_0000107");
				ot.setTermLabel("Theiler stage 10b");
				break;
			case "TS12a":
				ot.setTermId("MmusDv_0000108");
				ot.setTermLabel("Theiler stage 12a");
				break;
			case "TS12b":
				ot.setTermId("MmusDv_0000109");
				ot.setTermLabel("Theiler stage 12b");
				break;
			case "TS11a":
				ot.setTermId("MmusDv_0000111");
				ot.setTermLabel("Theiler stage 11a");
				break;
			default:
				ot = null;
				System.out.println(emageStageId + " was not found");
		}
		return ot;
	}
		
}

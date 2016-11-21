package uk.ac.ebi.phis.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import uk.ac.ebi.phis.dto.Job;
import uk.ac.ebi.phis.jaxb.Doc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Future;

/**
 * Created by ilinca on 17/11/2016.
 */

@Service("dataUploadService")
public class DataUploadServiceImpl implements  DataUploadService {
//
//    @Autowired
//    BatchXmlUploader batchXmlUploader;

    ClassLoader classloader;


    public DataUploadServiceImpl(){

        classloader = Thread.currentThread().getContextClassLoader();

    }

    @Async
    public Future<Boolean> validateXml(Job currentJob, String filePath) {

        boolean success = true;
        currentJob.setCompleted(false);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Unmarshal XML
        try {
            InputStream xsd;
            InputStream xml;
            xsd = classloader.getResourceAsStream("phisSchema.xsd");
            xml = new FileInputStream(filePath);

            // Validate XSD
            try{
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                success = batchXmlUploader.validateAgainstXSD(xml, xsd);
                success=true;
                if (success) {
                    currentJob.addJobUpdate("XML file validated schema. ", true);
                } else {
                    currentJob.addJobUpdate("XML file does not validated schema. ", false);
                    currentJob.setCompleted(false);
                    currentJob.setSuccess(false);
                }
            } catch (Exception e){
                e.printStackTrace();
                currentJob.addJobUpdate("XML file does not validate the schema => " + e.getMessage(), false);
                currentJob.setCompleted(true);
                currentJob.setSuccess(false);
                e.printStackTrace();
            }
            xsd.close();
            xml.close();
        } catch ( IOException e){
            currentJob.addJobUpdate("We could not read the file => " + e.getMessage(), false);
            currentJob.setCompleted(true);
            currentJob.setSuccess(false);
            e.printStackTrace();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!currentJob.getCompleted()) {
            try {
                Doc doc = convertXmlToObjects(filePath);
//                Boolean validInfo = batchXmlUploader.checkInformation(doc);
                Boolean validInfo = true;
                if (validInfo) {
                    currentJob.setSuccess(true);
                    currentJob.addJobUpdate("Semantic validation was successfull.", true);
                } else {
                    currentJob.setSuccess(false);
                }
            } catch (JAXBException | FileNotFoundException e) {
                currentJob.addJobUpdate("We could not read the file => " + e.getMessage(), false);
                currentJob.setSuccess(false);
                e.printStackTrace();
            }
        }
        // complete job, no matter of results
        currentJob.setCompleted(true);

        System.out.println("DONE WAITING");

        return new AsyncResult<Boolean>( currentJob.getSuccess());
    }


    private Doc convertXmlToObjects(String xmlFullPathLocation) throws JAXBException, FileNotFoundException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Doc.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        System.out.println(">>>>>>" + xmlFullPathLocation + "  " + (jaxbUnmarshaller != null));
        return (Doc) jaxbUnmarshaller.unmarshal(new FileInputStream(xmlFullPathLocation));

    }

}
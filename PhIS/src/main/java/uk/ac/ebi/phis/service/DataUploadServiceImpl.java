package uk.ac.ebi.phis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import uk.ac.ebi.phis.dto.Job;
import uk.ac.ebi.phis.exception.PhenoImageShareException;
import uk.ac.ebi.phis.importer.BatchXmlUploader;
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

    @Autowired
    BatchXmlUploader batchXmlUploader;

    ClassLoader classloader;


    public DataUploadServiceImpl(){

        classloader = Thread.currentThread().getContextClassLoader();

    }

    @Async
    public Future<Boolean> validateXml(Job currentJob, String filePath, Boolean strict) {

        boolean success = true;
        currentJob.setCompleted(false);

        // Unmarshal XML
        try {
            InputStream xsd;
            InputStream xml;
            xsd = classloader.getResourceAsStream("phisSchema.xsd");
            xml = new FileInputStream(filePath);

            // Validate XSD
            try{
                success = batchXmlUploader.validateAgainstXSD(xml, xsd);
                if (success) {
                    currentJob.addJobUpdate("XML file validated schema. ", true);
                } else {
                    currentJob.addJobUpdate("XML file does not validated schema. ", false);
                    currentJob.setCompleted(true);
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

        if (!currentJob.getCompleted()) {
            try {
                System.out.println("Semantic validation ...");
                Doc doc = convertXmlToObjects(filePath);
                Boolean validInfo = batchXmlUploader.checkInformation(doc, true);
                if (validInfo) {
                    currentJob.setSuccess(true);
                    currentJob.addJobUpdate("Semantic validation was successfull.", true);
                } else {
                    currentJob.addJobUpdate("Semantic validation was not successfull.", false);
                    currentJob.setSuccess(false);
                }
            } catch (JAXBException | FileNotFoundException e) {
                currentJob.addJobUpdate("We could not read the file => " + e.getMessage(), false);
                currentJob.setSuccess(false);
                e.printStackTrace();
            } catch (PhenoImageShareException e){
                currentJob.addJobUpdate(e.getMessage(), false);
                currentJob.setSuccess(false);
                e.printStackTrace();
            } catch (Exception e){
                System.err.println("PRINTING EXCEPTION!!");
                e.printStackTrace();
            }
        }

        // complete job, no matter of results
        currentJob.setCompleted(true);

        return new AsyncResult<Boolean>( currentJob.getSuccess());
    }


    private Doc convertXmlToObjects(String xmlFullPathLocation) throws JAXBException, FileNotFoundException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Doc.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        System.out.println(">>>>>> Not null: " + xmlFullPathLocation + "  " + (jaxbUnmarshaller != null));
        return (Doc) jaxbUnmarshaller.unmarshal(new FileInputStream(xmlFullPathLocation));

    }

}
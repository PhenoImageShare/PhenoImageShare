package uk.ac.ebi.phis.mains;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.ac.ebi.phis.importer.BatchXmlUploader;
import uk.ac.ebi.phis.utils.ontology.OntologyMapper;
import uk.ac.ebi.phis.utils.ontology.OntologyMapperPredefinedTypes;
import uk.ac.ebi.phis.utils.ontology.OntologyUtils;
import uk.ac.ebi.phis.xmlDump.SangerImagesImporter;
import uk.ac.ebi.phis.xmlDump.SangerXmlGenerator;
import uk.ac.ebi.phis.xmlDump.TracerImporter;
import uk.ac.ebi.phis.xmlDump.TracerXmlGenerator;

@Component
public class main {

	public static void main(String[] args)
	throws SolrServerException {

		// OntologyMapper mapper = new
		// OntologyMapper(OntologyMapperPredefinedTypes.MA_MP);
		// mapper.getMappings("http://purl.obolibrary.org/obo/MP_0003684",
		// "MA");
		// System.out.println("\t\t " + mapper.getAnatomyLabel("MA_0000003"));

		try {
			long time = System.currentTimeMillis();
			// SangerXmlGenerator sg = new SangerXmlGenerator();
			// sg.exportImages();
			System.out.println("Generating xml for Sanger took " + (System.currentTimeMillis() - time));

			time = System.currentTimeMillis();
			// TracerXmlGenerator tg = new TracerXmlGenerator();
			System.out.println("Generating XML for Tracer took " + (System.currentTimeMillis() - time));

			BatchXmlUploader reader = new BatchXmlUploader();

			time = System.currentTimeMillis();
			// System.out.println(reader.validate("tracerExport.xml"));
			System.out.println("Validating Tracer XML took " + (System.currentTimeMillis() - time));

			time = System.currentTimeMillis();
			System.out.println("Is valid? " + reader.validateAndUpload("sangerExport.xml"));
			System.out.println("Validating Sanger XML took " + (System.currentTimeMillis() - time));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

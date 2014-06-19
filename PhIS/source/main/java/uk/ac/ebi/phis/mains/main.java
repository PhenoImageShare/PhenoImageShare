package uk.ac.ebi.phis.mains;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.solr.client.solrj.SolrServerException;

import uk.ac.ebi.phis.importer.BatchXmlReader;
import uk.ac.ebi.phis.utils.ontology.OntologyMapper;
import uk.ac.ebi.phis.utils.ontology.OntologyMapperPredefinedTypes;
import uk.ac.ebi.phis.utils.ontology.OntologyUtils;
import uk.ac.ebi.phis.xmlDump.SangerImagesImporter;
import uk.ac.ebi.phis.xmlDump.SangerXmlGenerator;
import uk.ac.ebi.phis.xmlDump.TracerImporter;
import uk.ac.ebi.phis.xmlDump.TracerXmlGenerator;

public class main {

	public static void main(String[] args) throws SolrServerException {
		
//		OntologyMapper mapper = new OntologyMapper(OntologyMapperPredefinedTypes.MA_MP);
//		mapper.getMappings("http://purl.obolibrary.org/obo/MP_0003684", "MA");
//		System.out.println("\t\t " + mapper.getAnatomyLabel("MA_0000003"));
		try {

//			OntologyUtils ou = new OntologyUtils();
//			System.out.println(ou.isAnatomyId("EMAP:7442"));
//			BatchXmlReader reader = new BatchXmlReader();
//			System.out.println(reader.validate("tracerExport.xml"));
//			System.out.println(reader.validate("sangerExport.xml"));
//			System.out.println(reader.convertXmlToObjects("sangerExport.xml"));
			SangerXmlGenerator sg = new SangerXmlGenerator();
			sg.read();
			
//			TracerXmlGenerator tg = new TracerXmlGenerator();
//			tg.read();
			
			/*		
			SangerImagesImporter sanger = new SangerImagesImporter();
			sanger.read();
				
			TracerImporter tracer = new TracerImporter();
			tracer.read();
	*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		/*		
		Komp2Importer komp = new Komp2Importer();
		komp.downloadAndParseToXml();
		*/
		
	}

}
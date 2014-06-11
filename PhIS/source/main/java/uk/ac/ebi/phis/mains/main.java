package uk.ac.ebi.phis.mains;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.solr.client.solrj.SolrServerException;

import uk.ac.ebi.phis.importer.SangerImagesImporter;
import uk.ac.ebi.phis.importer.SangerXmlGenerator;
import uk.ac.ebi.phis.importer.TracerImporter;
import uk.ac.ebi.phis.importer.TracerXmlGenerator;
import uk.ac.ebi.phis.utils.ontology.OntologyMapper;
import uk.ac.ebi.phis.utils.ontology.OntologyMapperPredefinedTypes;

public class main {

	public static void main(String[] args) throws SolrServerException {
		
//		OntologyMapper mapper = new OntologyMapper(OntologyMapperPredefinedTypes.MA_MP);
//		mapper.getMappings("http://purl.obolibrary.org/obo/MP_0003684", "MA");
//		System.out.println("\t\t " + mapper.getAnatomyLabel("MA_0000003"));
		try {

//			SangerXmlGenerator sg = new SangerXmlGenerator();
//			sg.read();
			
			TracerXmlGenerator tg = new TracerXmlGenerator();
			tg.read();
			
			/*		
			SangerImagesImporter sanger = new SangerImagesImporter();
			sanger.read();
				
			TracerImporter tracer = new TracerImporter();
			tracer.read();
	*/
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/*		
		Komp2Importer komp = new Komp2Importer();
		komp.downloadAndParseToXml();
		*/
		
	}

}
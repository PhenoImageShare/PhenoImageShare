package uk.ac.ebi.phis.mains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import uk.ac.ebi.phis.xmlDump.SangerXmlGenerator;
import uk.ac.ebi.phis.xmlDump.TracerXmlGenerator;

@Component
public class ExportDatabasesAsXml {

	public static void main(String[] args){

		// OntologyMapper mapper = new
		// OntologyMapper(OntologyMapperPredefinedTypes.MA_MP);
		// mapper.getMappings("http://purl.obolibrary.org/obo/MP_0003684",
		// "MA");
		// System.out.println("\t\t " + mapper.getAnatomyLabel("MA_0000003"));
		
		try {
			long time = System.currentTimeMillis();
			SangerXmlGenerator sg = new SangerXmlGenerator();
			sg.exportImages();
			System.out.println("Generating xml for Sanger took " + (System.currentTimeMillis() - time));

			time = System.currentTimeMillis();
//			TracerXmlGenerator tg = new TracerXmlGenerator();
//			tg.exportImages();
			System.out.println("Generating XML for Tracer took " + (System.currentTimeMillis() - time));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

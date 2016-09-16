/*******************************************************************************
 * Copyright 2015 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 *******************************************************************************/
package uk.ac.ebi.phis.mains;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.springframework.stereotype.Component;
import uk.ac.ebi.phis.xmlDump.SangerXmlGenerator;

import java.io.File;

@Component
public class ExportDatabasesAsXml {

	public static void main(String[] args){

		// OntologyMapper mapper = new OntologyMapper(OntologyMapperPredefinedTypes.MA_MP);
		// mapper.getMappings("http://purl.obolibrary.org/obo/MP_0003684", "MA");
		// System.out.println("\t\t " + mapper.getAnatomyLabel("MA_0000003"));
		OptionParser parser = new OptionParser();
		parser.accepts( "context" ).withRequiredArg();
		parser.accepts( "dataDir" ).withRequiredArg();
		parser.accepts( "releaseVersion" ).withRequiredArg();

		OptionSet options = parser.parse( args );

		if (!options.has("context")) {
			help();
		}

		// Check context file exists
		String contextFile = (String) options.valueOf("context");
		File f = new File(contextFile);
		if (!f.isFile() || !f.canRead()) {
			System.err.println("Context file " + contextFile + " not readable.");
			help();
		} else {
			System.out.println("--context OK");
		}

		try {

			long time = System.currentTimeMillis();
			SangerXmlGenerator sg = new SangerXmlGenerator();
			sg.exportImages(contextFile);
			System.out.println("Generating xml for Sanger took " + (System.currentTimeMillis() - time));
//
//			time = System.currentTimeMillis();
//			IdrXmlGenerator idr = new IdrXmlGenerator();
//			idr.export();
//			System.out.println("Generating xml for IDR took " + (System.currentTimeMillis() - time));
//
//			time = System.currentTimeMillis();
//			TracerXmlGenerator tg = new TracerXmlGenerator();
//			tg.exportImages(contextFile);
//			System.out.println("Generating XML for Tracer took " + (System.currentTimeMillis() - time));

//			time = System.currentTimeMillis();
//			EmageXmlGenerator emage = new EmageXmlGenerator();
//			emage.aggregateXml();
//			System.out.println("Generating XML for Emage took " + (System.currentTimeMillis() - time));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void help() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("PopulateCores usage:\n\n");
		buffer.append("PopulateCores --context <Spring context>\n");
		buffer.append("\t--context|-c\tSpring application context configuration file\n");
		System.out.println(buffer);
		System.exit(1);
	}

}
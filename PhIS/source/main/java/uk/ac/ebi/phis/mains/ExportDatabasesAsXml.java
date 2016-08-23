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

import org.springframework.stereotype.Component;
import uk.ac.ebi.phis.xmlDump.EmageXmlGenerator;

@Component
public class ExportDatabasesAsXml {

	public static void main(String[] args){

		// OntologyMapper mapper = new OntologyMapper(OntologyMapperPredefinedTypes.MA_MP);
		// mapper.getMappings("http://purl.obolibrary.org/obo/MP_0003684", "MA");
		// System.out.println("\t\t " + mapper.getAnatomyLabel("MA_0000003"));
		
		try {
			
			long time = System.currentTimeMillis();
//			SangerXmlGenerator sg = new SangerXmlGenerator();
//			sg.exportImages();
//			System.out.println("Generating xml for Sanger took " + (System.currentTimeMillis() - time));

//			time = System.currentTimeMillis();
//			TracerXmlGenerator tg = new TracerXmlGenerator();
//			tg.exportImages();
//			System.out.println("Generating XML for Tracer took " + (System.currentTimeMillis() - time));

			time = System.currentTimeMillis();
			EmageXmlGenerator emage = new EmageXmlGenerator();
			emage.aggregateXml();
			System.out.println("Generating XML for Emage took " + (System.currentTimeMillis() - time));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

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
import uk.ac.ebi.phis.importer.AutosuggestIndexer;
import uk.ac.ebi.phis.service.AutosuggestService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.utils.ontology.OntologyUtils;


public class PopulateAutosuggest {
	
	
	public static void main(String[] args) {
		
		OptionParser parser = new OptionParser();
		parser.accepts( "solrBaseUrl" ).withRequiredArg();
	
		OptionSet options = parser.parse( args );
	
		if (!options.has("solrBaseUrl")) {
			help();
		}
		
		// Check context file exists
		String solrBaseUrl = (String) options.valueOf("solrBaseUrl");
					
		try {

			ImageService is = new ImageService(solrBaseUrl + "/images");
			AutosuggestService as = new AutosuggestService(solrBaseUrl + "/autosuggest");
			OntologyUtils ou = new OntologyUtils();
			AutosuggestIndexer indexer = new AutosuggestIndexer(is, as, ou);
			as.clear();
			indexer.index();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void help() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("PopulateAutosuggest usage:\n\n");
			buffer.append("PopulateAutosuggest --solrBaseUrl <http://ves-ebi-d0.ebi.ac.uk:8090/mi/phis>\n");
			buffer.append("\t--solrBaseUrl\tURL for the solr to build in\n");
			System.out.println(buffer);
			System.exit(1);
		}
}

package uk.ac.ebi.phis.mains;

import java.io.File;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import uk.ac.ebi.phis.importer.AutosuggestIndexer;
import uk.ac.ebi.phis.service.AutosuggestService;
import uk.ac.ebi.phis.service.ImageService;


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
			as.clear();
			AutosuggestIndexer indexer = new AutosuggestIndexer(is, as);
			indexer.index();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void help() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("PopulateAutosuggest usage:\n\n");
			buffer.append("PopulateAutosuggest --solrBaseUrl <http://ves-ebi-d0.ebi.ac.uk:8090/mi/phis>\n");
			buffer.append("\t--solrBaseUrl\tURL for the solr to buils\n");
			System.out.println(buffer);
			System.exit(1);
		}
}

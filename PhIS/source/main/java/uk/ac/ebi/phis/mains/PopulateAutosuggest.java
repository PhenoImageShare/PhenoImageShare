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
		parser.accepts( "context" ).withRequiredArg();
	
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
		}
		
		ApplicationContext applicationContext = new  FileSystemXmlApplicationContext("file:" + contextFile);
			
		try {

			ImageService is = (ImageService) applicationContext.getBean("imageService");
			AutosuggestService as = (AutosuggestService) applicationContext.getBean("autosuggestService");
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
			buffer.append("PopulateAutosuggest --context <Spring context>\n");
			buffer.append("\t--context|-c\tSpring application context configuration file\n");
			System.out.println(buffer);
			System.exit(1);
		}
}

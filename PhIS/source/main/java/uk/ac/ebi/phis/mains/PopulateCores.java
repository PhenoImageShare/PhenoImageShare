package uk.ac.ebi.phis.mains;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import uk.ac.ebi.phis.importer.BatchXmlUploader;
import uk.ac.ebi.phis.service.ChannelService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.service.RoiService;


public class PopulateCores {

	public static void main(String[] args) {
		
		OptionParser parser = new OptionParser();
		parser.accepts( "context" ).withRequiredArg();

		OptionSet options = parser.parse( args );

		if (!options.has("context")) {
			help();
		}
    	// pass the context file

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
			RoiService rs = (RoiService) applicationContext.getBean("roiService");
			ChannelService cs = (ChannelService) applicationContext.getBean("channelService"); 
			BatchXmlUploader reader = new BatchXmlUploader(is, rs, cs);

			long time = System.currentTimeMillis();
			System.out.println(reader.validateAndUpload("tracerExport.xml"));
			System.out.println("Validating Tracer XML took " + (System.currentTimeMillis() - time));

			time = System.currentTimeMillis();
//			System.out.println("Is valid? " + reader.validateAndUpload("sangerExport.xml"));
			System.out.println("Validating Sanger XML took " + (System.currentTimeMillis() - time));
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

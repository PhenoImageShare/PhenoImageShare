package uk.ac.ebi.phis.mains;

import java.io.File;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

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
		parser.accepts( "dataDir" ).withRequiredArg();

		OptionSet options = parser.parse( args );

		if (!options.has("context")) {
			help();
		}
		if (!options.has("dataDir")) {
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
		// Check data dir exists
		String dataDir = (String) options.valueOf("dataDir");
		File d = new File(dataDir);
		if (!d.isDirectory() || !d.canRead()) {
			System.err.println("dataDir file " + dataDir + " not readable.");
			help();
		}

		ApplicationContext applicationContext = new  FileSystemXmlApplicationContext("file:" + contextFile);
			
		try {

			ImageService is = (ImageService) applicationContext.getBean("imageService");
			RoiService rs = (RoiService) applicationContext.getBean("roiService");
			ChannelService cs = (ChannelService) applicationContext.getBean("channelService"); 
			
			// delete everything in the cores. This will likely change as we might want to do updates only.
			is.clear();
			rs.clear();
			cs.clear();
			
			BatchXmlUploader reader = new BatchXmlUploader(is, rs, cs);

			boolean itWorked = false;
			long time = System.currentTimeMillis();	
			itWorked = reader.validateAndUpload(dataDir + "/VFB_Cachero2010.xml");
			System.out.println(itWorked);
			System.out.println("Importing VFB_Cachero2010 XML took " + (System.currentTimeMillis() - time));
			
			itWorked = false;
			time = System.currentTimeMillis();	
			itWorked = reader.validateAndUpload(dataDir + "/VFB_Ito2013.xml");
			System.out.println(itWorked);
			System.out.println("Importing VFB_Ito2013 XML took " + (System.currentTimeMillis() - time));
			
			itWorked = false;
			time = System.currentTimeMillis();	
			itWorked = reader.validateAndUpload(dataDir + "/VFB_Jenett2012_full.xml");
			System.out.println(itWorked);
			System.out.println("Importing VFB_Jenett2012_full XML took " + (System.currentTimeMillis() - time));
			
			itWorked = false;
			time = System.currentTimeMillis();	
			itWorked = reader.validateAndUpload(dataDir + "/VFB_Yu2013.xml");
			System.out.println(itWorked);
			System.out.println("Importing VFB_Yu2013 XML took " + (System.currentTimeMillis() - time));
			
			itWorked = false;
			time = System.currentTimeMillis();	
			itWorked = reader.validateAndUpload(dataDir + "/VFB_flycircuit_plus.xml");
			System.out.println(itWorked);
			System.out.println("Importing VFB_flycircuit_plus XML took " + (System.currentTimeMillis() - time));
			
			itWorked = false;
			time = System.currentTimeMillis();	
			itWorked = reader.validateAndUpload(dataDir + "/emageExport.xml");
			System.out.println(itWorked);
			System.out.println("Importing EMAGE XML took " + (System.currentTimeMillis() - time));
			
			itWorked = false;
			time = System.currentTimeMillis();	
			itWorked = reader.validateAndUpload(dataDir + "/tracerExport.xml");
			System.out.println(itWorked);
			System.out.println("Importing Tracer XML took " + (System.currentTimeMillis() - time));

			time = System.currentTimeMillis();
			itWorked = reader.validateAndUpload(dataDir + "/sangerExport.xml");
			System.out.println("Is valid? " + itWorked);
			System.out.println("Importing Sanger XML took " + (System.currentTimeMillis() - time));
			
			System.out.println("Solr url is : " + is.getSolrUrl());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

	public static void help() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("PopulateCores usage:\n\n");
		buffer.append("PopulateCores --context <Spring context>\n");
		buffer.append("\t--context|-c\tSpring application context configuration file\n");
		buffer.append("PopulateCores --dataDir <Spring context>\n");
		buffer.append("\t--dataDir|-d\tFull path to the directory containing all the xml files to be used for the Solr index.\n");
		System.out.println(buffer);
		System.exit(1);
	}

}

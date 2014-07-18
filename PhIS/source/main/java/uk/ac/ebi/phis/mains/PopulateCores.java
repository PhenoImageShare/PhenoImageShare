package uk.ac.ebi.phis.mains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.ac.ebi.phis.importer.BatchXmlUploader;
import uk.ac.ebi.phis.service.ChannelService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.service.RoiService;
import uk.ac.ebi.phis.xmlDump.TracerXmlGenerator;


public class PopulateCores {

	public static void main(String[] args) {

		 ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app-config.xml");
			
			try {

				ImageService is = (ImageService) applicationContext.getBean("imageService");
				RoiService rs = (RoiService) applicationContext.getBean("roiService");
				ChannelService cs = (ChannelService) applicationContext.getBean("channelService"); 
				BatchXmlUploader reader = new BatchXmlUploader(is, rs, cs);

				long time = System.currentTimeMillis();
				System.out.println(reader.validateAndUpload("tracerExport.xml"));
				System.out.println("Validating Tracer XML took " + (System.currentTimeMillis() - time));

				time = System.currentTimeMillis();
//				System.out.println("Is valid? " + reader.validateAndUpload("sangerExport.xml"));
				System.out.println("Validating Sanger XML took " + (System.currentTimeMillis() - time));

			} catch (Exception e) {
				e.printStackTrace();
			}


	}

}

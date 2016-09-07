package uk.ac.ebi.phis.service;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import uk.ac.ebi.phis.exception.PhisSubmissionException;
import uk.ac.ebi.phis.solrj.dto.ChannelDTO;
import uk.ac.ebi.phis.solrj.dto.ImageDTO;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;

import java.io.File;

import static junit.framework.TestCase.fail;


/**
 * Created by ilinca on 06/09/2016.
 */


public class GenericUpdateServiceTest {

    private static ApplicationContext applicationContext;
    private GenericUpdateService updateService;
    private ImageService is ;
    private RoiService rs;
    private ChannelService cs;
    private static Logger logger = Logger.getLogger(GenericUpdateServiceTest.class);

    @Before
    public void initialize(){

        String contextFile = "/Users/ilinca/IdeaProjects/PhenoImageShare/PhIS/WebContent/WEB-INF/app-config.xml";
        File f = new File(contextFile);
        if (!f.isFile() || !f.canRead()) {
            System.err.println("Context file " + contextFile + " not readable.");
        } else {
            logger.info("--context OK");
        }
        applicationContext = new FileSystemXmlApplicationContext("file:" + contextFile);

        updateService = (GenericUpdateService) applicationContext.getBean("genericUpdateService");
        cs = (ChannelService) applicationContext.getBean("channelService");
        is = (ImageService) applicationContext.getBean("imageService");
        rs = (RoiService) applicationContext.getBean("roiService");

    }

    @Test
    public void testOperationsWithPhublishFlags(){


        // unpublish

        RoiDTO roi = TestUtils.getTestRoi();
        roi.setPublished(false);


        // add annotation, unpublished
        try {
            updateService.addToCores(roi, false);

            // roi core contains it, published = false
            RoiDTO added = rs.getRoiById(roi.getId());
            if (added == null){
                fail();
            } else if (added.getPublished() == true){

                updateService.deleteFromCores(roi.getId());
                fail();
            }

            // images doesn't contain it
            ImageDTO img = is.getImageDTOById(roi.getAssociatedImage());
            if (img.getAssociatedRoi().contains(roi.getId())){
                updateService.deleteFromCores(roi.getId());
                fail();
            }

            // image annotation bags don't have the annotations
            if (img.getDepictedAnatomyIdBag() != null && img.getDepictedAnatomyIdBag().contains(roi.getDepictedAnatomyId().get(0))){

                updateService.deleteFromCores(roi.getId());
                fail();
            }
            if (img.getAbnormalAnatomyIdBag() != null && img.getAbnormalAnatomyIdBag().contains(roi.getAbnormalityAnatomyId().get(0))){

                updateService.deleteFromCores(roi.getId());
                fail();
            }
            if (img.getPhenotypeIdBag() != null && img.getPhenotypeIdBag().contains(roi.getPhenotypeId().get(0))){

                updateService.deleteFromCores(roi.getId());
                fail();
            }

            // channels doesn't link to it
            for (String channelId: roi.getAssociatedChannel()){
                ChannelDTO channel = cs.getChannelBean(channelId);
                if (channel.getAssociatedRoi().contains(roi.getId())){
                    fail();
                }
            }

        } catch (PhisSubmissionException e) {
            e.printStackTrace();
            fail();
        }

        // publish

        roi.setPublished(true);
        try {
            updateService.addToCores(roi, true);

            // roi core contains it, published = false
            RoiDTO added = rs.getRoiById(roi.getId());
            if (added == null){

                updateService.deleteFromCores(roi.getId());
                fail();
            } else if (added.getPublished() == false){

                updateService.deleteFromCores(roi.getId());
                fail();
            }

            // images contain it
            ImageDTO img = is.getImageDTOById(roi.getAssociatedImage());
            if (!img.getAssociatedRoi().contains(roi.getId())){

                updateService.deleteFromCores(roi.getId());
                fail();
            }

            // image annotation bags have the annotations
            if (img.getDepictedAnatomyIdBag() == null || !img.getDepictedAnatomyIdBag().contains(roi.getDepictedAnatomyId().get(0))){

                updateService.deleteFromCores(roi.getId());
                fail();
            }
            if (img.getAbnormalAnatomyIdBag() == null || !img.getAbnormalAnatomyIdBag().contains(roi.getAbnormalityAnatomyId().get(0))){

                updateService.deleteFromCores(roi.getId());
                fail();
            }
            if (img.getPhenotypeIdBag() == null || !img.getPhenotypeIdBag().contains(roi.getPhenotypeId().get(0))){

                updateService.deleteFromCores(roi.getId());
                fail();
            }

            // channels link to it
            for (String channelId: roi.getAssociatedChannel()){
                ChannelDTO channel = cs.getChannelBean(channelId);
                if (!channel.getAssociatedRoi().contains(roi.getId())){

                    updateService.deleteFromCores(roi.getId());
                    fail();
                }
            }

        } catch (PhisSubmissionException e) {
            e.printStackTrace();
            fail();
        }

        try {
            updateService.deleteFromCores(roi.getId());
        } catch (PhisSubmissionException e) {
            e.printStackTrace();
            fail();
        }
    }

}

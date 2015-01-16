package uk.ac.ebi.phis.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.test.AssertThrows;

import uk.ac.ebi.phis.solrj.dto.ChannelDTO;

public class ChannelServiceTest {
	
	ChannelService cs;

	private static final String CHANNEL_ID = "komp2_channel_112967_0";
	private static final String ROI_ID = "komp2_roi_112967_0_MADE_UP";
	
	
	public ChannelServiceTest(){
		cs = new ChannelService("http://localhost:8086/solr-example/channels");
	}
	
	@Test
	public void testAddAssociatedRoi(){
		
		ChannelDTO channel = cs.getChannelBean(CHANNEL_ID);
		if(channel != null){
			List<String> rois = channel.getAssociatedRoi(); 
			int initialSize = rois.size();
			System.out.println("\nROIS to add to : " + rois);
			if (rois.contains(ROI_ID)){
				// the number of rois should be the same and the id still contained there
				cs.addAssociatedRoi(ROI_ID, CHANNEL_ID);
				channel = cs.getChannelBean(CHANNEL_ID);
				rois = channel.getAssociatedRoi();
				assertTrue(rois.size() == initialSize && rois.contains(ROI_ID));
			}else {
				cs.addAssociatedRoi(ROI_ID, CHANNEL_ID);
				channel = cs.getChannelBean(CHANNEL_ID);
				rois = channel.getAssociatedRoi();
				assertTrue(rois.size() == (1 + initialSize) && rois.contains(ROI_ID));
			}
			System.out.println("After adding: " + rois);
		}
	}
	
	@Test
	public void testDeleteAssociatedRoi(){
		ChannelDTO channel = cs.getChannelBean(CHANNEL_ID);
		if(channel != null){
			List<String> rois = channel.getAssociatedRoi(); 
			int initialSize = rois.size();
			System.out.println("\nROIS to delete from : " + rois);
			if (rois.contains(ROI_ID)){
				// the number of rois should be the same and the id still contained there
				cs.deleteAssociatedRoi(ROI_ID, CHANNEL_ID);
				channel = cs.getChannelBean(CHANNEL_ID);
				rois = channel.getAssociatedRoi();
				assertTrue(rois.size() == (initialSize - 1) && !rois.contains(ROI_ID));
			}else {
				cs.deleteAssociatedRoi(ROI_ID, CHANNEL_ID);
				channel = cs.getChannelBean(CHANNEL_ID);
				rois = channel.getAssociatedRoi();
				assertTrue (rois.size() == initialSize && !rois.contains(ROI_ID));
			}
			System.out.println("After delete: " + channel.getAssociatedRoi());
		}
	}

	
}

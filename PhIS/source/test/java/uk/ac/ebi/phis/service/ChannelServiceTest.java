package uk.ac.ebi.phis.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.test.AssertThrows;

import uk.ac.ebi.phis.solrj.dto.ChannelDTO;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;

public class ChannelServiceTest {
	
	ChannelService cs;

	private RoiDTO roi;
	
	public ChannelServiceTest(){
		cs = new ChannelService("http://localhost:8086/solr-example/channels");
		roi = TestUtils.getTestRoi();
	}
	
	@Test
	public void testAddAssociatedRoi(){
		
		List<String> channels = roi.getAssociatedChannel();
		String roiId = roi.getId();
		
		if(channels != null){
			for (String channelId : channels){
				ChannelDTO channel = cs.getChannelBean(channelId);
				List<String> rois = channel.getAssociatedRoi(); 
				int initialSize = rois.size();
				System.out.println("\nROIS to add to : " + rois);
				if (rois.contains(roiId)){
					// the number of rois should be the same and the id still contained there
					cs.addAssociatedRoi(roi);
					channel = cs.getChannelBean(channelId);
					rois = channel.getAssociatedRoi();
					assertTrue(rois.size() == initialSize && rois.contains(roiId));
				}else {
					cs.addAssociatedRoi(roi);
					channel = cs.getChannelBean(channelId);
					rois = channel.getAssociatedRoi();
					assertTrue(rois.size() == (1 + initialSize) && rois.contains(roiId));
				}
				System.out.println("After adding: " + rois);
			}
		}
	}
	
	@Test
	public void testDeleteAssociatedRoi(){
		
		List<String> channels = roi.getAssociatedChannel();
		String roiId = roi.getId();
		
		if(channels != null){
			for (String channelId : channels){
				ChannelDTO channel = cs.getChannelBean(channelId);
				if(channel != null){
					List<String> rois = channel.getAssociatedRoi(); 
					int initialSize = rois.size();
					System.out.println("\nROIS to delete from : " + rois);
					if (rois.contains(roiId)){
						// the number of rois should be the same and the id still contained there
						cs.deleteAssociatedRoi(roi);
						channel = cs.getChannelBean(channelId);
						rois = channel.getAssociatedRoi();
						assertTrue(rois.size() == (initialSize - 1) && !rois.contains(roiId));
					}else {
						cs.deleteAssociatedRoi(roi);
						channel = cs.getChannelBean(channelId);
						rois = channel.getAssociatedRoi();
						assertTrue (rois.size() == initialSize && !rois.contains(roiId));
					}
					System.out.println("After delete: " + channel.getAssociatedRoi());
				}
			}
		}
	}

	
}

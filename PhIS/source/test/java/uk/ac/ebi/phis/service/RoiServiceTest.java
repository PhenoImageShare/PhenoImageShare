package uk.ac.ebi.phis.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import uk.ac.ebi.phis.solrj.dto.RoiDTO;


public class RoiServiceTest {

	RoiService rs;
	RoiDTO roi;
	
	public RoiServiceTest(){
		rs = new RoiService("http://localhost:8086/solr-example/rois");
		roi = TestUtils.getTestRoi();
	}
	
	@Test
	public void testAddRoi(){
		rs.addRoi(roi);
		RoiDTO newRoi = rs.getRoiById(roi.getId());
		assertTrue(roi.equals(newRoi));
	}
	
	@Test 
	public void testUpdateRoi(){
		List<String> newList = new ArrayList<>();
		newList.add("Some new abnormality to make the ROI distinct.");
		roi.setAbnormalityAnatomyTerm(newList);
		rs.updateRoi(roi);
		RoiDTO newRoi = rs.getRoiById(roi.getId());
		assertTrue(roi.equals(newRoi));
	}
	
	@Test
	public void testDeleteRoi(){
		rs.deleteRoi(roi.getId());
		assertTrue(rs.getRoiById(roi.getId()) == null);
	}
}

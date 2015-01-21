package uk.ac.ebi.phis.service;

import static org.junit.Assert.*;

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
		System.out.println(newRoi.toString());
		System.out.println("\n\n\n old roi: \n" + roi.toString());
		assertTrue(roi.equals(newRoi));
	}
	
	@Ignore @Test 
	public void testUpdateRoi(){
		
	}
	
	@Ignore @Test
	public void testDeleteRoi(){
		
	}
}

/*******************************************************************************
 * Copyright 2015 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 *******************************************************************************/
package uk.ac.ebi.phis.service;

import org.junit.Test;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


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

package uk.ac.ebi.phis.mains;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ebi.neo4jUtils.Neo4jAccessUtils;
import uk.ac.ebi.phis.service.TestUtils;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;


public class Neo4jTest {

	public static void main(String[] args) {

/*		Neo4jAccessUtils neo = new Neo4jAccessUtils("http://localhost:7474/db/data/");
		List<Float> x = new ArrayList<>();
		List<Float> y =  new ArrayList<>();
		List<String> observations = new ArrayList<>();
		
		RoiDTO roi = TestUtils.getTestRoi();
		
		try {	
			System.out.println("Creating annotation: " + neo.createAnnotation("user_1", roi.getId(), roi.getAssociatedImage(), roi.getxCoordinates(), roi.getyCoordinates(), roi.getzCoordinates(), 
			 roi.getAssociatedChannel(), roi.getDepictedAnatomyId(), roi.getDepictedAnatomyFreetext(), roi.getDepictedAnatomyId(),
			 roi.getAbnormalityAnatomyId(), roi.getAbnormalityAnatomyFreetext(), roi.getAbnormalityAnatomyTerm(), 
			 roi.getPhenotypeId(), roi.getPhenotypeFreetext(), roi.getPhenotypeTerm(), roi.getObservations(), 
			 roi.getExpressedAnatomyId(), roi.getExpressedAnatomyTerm(), roi.getExpressedAnatomyFreetext()));	

			System.out.println("Deleting annotation. ");
			neo.deleteNodeWithRelations(roi.getId());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Date : " + neo.getCreationDate(roi.getId()));
		System.out.println("all nodes" + neo.readAllNodes());
		System.out.println("RELATIONS \n"  + neo.getDirectRelationsTo(roi.getId()));
		neo.closeDb();
*/
	}
}
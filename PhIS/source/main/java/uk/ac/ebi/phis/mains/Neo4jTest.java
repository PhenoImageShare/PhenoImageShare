package uk.ac.ebi.phis.mains;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ebi.neo4jUtils.Neo4jAccessUtils;
import uk.ac.ebi.phis.service.TestUtils;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;


public class Neo4jTest {

	public static void main(String[] args) {

		Neo4jAccessUtils neo = new Neo4jAccessUtils("http://localhost:7474/db/data/");
		List<Float> x = new ArrayList<>();
		List<Float> y =  new ArrayList<>();
		List<String> observations = new ArrayList<>();
		
		RoiDTO roi = TestUtils.getTestRoi();
		
		try {
/*			observations.add( "All looks normal");
			neo.addAnnotationNode("222",observations, new Date(), new Date(), x, y, null);
			observations.add("See phenotype annotation.");
			neo.addAnnotationNode("121", observations, new Date(), new Date(), x, y,null);
			neo.addChannel("c_30");
			neo.addImage("i_sq1321");
			neo.addOntologyTerm("BLAH:00001", "Made up ontology term");
			neo.addOntologyTerm("BLAH:00006", "big head");
			neo.addOntologyTerm("BLAH:00003", "heart");
			neo.addOntologyTerm("BLAH:00004", "Abnormal morphology");
			neo.addUser("user_2w3q1");
			Node image = neo.getNodeById("i_sq1321");
			Node annotation1 = neo.getNodeById("222");
			neo.addBidirectionalRelation(annotation1, Neo4jRelationship.HAS_ASSOCIATED_IMAGE, image);
			neo.deleteNodeWithRelations("ann_0546156");
			
*/		
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

	}
}
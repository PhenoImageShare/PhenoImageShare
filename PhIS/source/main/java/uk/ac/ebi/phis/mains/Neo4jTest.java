package uk.ac.ebi.phis.mains;

import java.util.Date;

import org.neo4j.graphdb.Node;

import uk.ac.ebi.neo4jUtils.Neo4jAccessUtils;
import uk.ac.ebi.neo4jUtils.Neo4jRelationship;


public class Neo4jTest {

	public static void main(String[] args) {

		Neo4jAccessUtils neo = new Neo4jAccessUtils();
	//	neo.addSomething();
	//	neo.readSomething();
		float[] x = {0, 100};
		float[] y = {0, 100};
		float[] z = {};
		
		try {
//			neo.addAnnotation("0", "All looks normal", new Date(), new Date(), x, y, z);
//			neo.addAnnotation("1", "See phenotype annotation.", new Date(), new Date(), x, y, z);
//			neo.addChannel("c_0");
//			neo.addImage("i_1");
//			neo.addOntologyTerm("BLAH:00001", "Mage up ontology term");
//			neo.addOntologyTerm("BLAH:00006", "big head");
//			neo.addOntologyTerm("BLAH:00003", "heart");
//			neo.addOntologyTerm("BLAH:00004", "Abnormal morphology");
//			neo.addUser("user_1");
			Node user1 = neo.getNodeById("user_1");
			Node annotation1 = neo.getNodeById("1");
			neo.addBidirectionalRelation(annotation1, Neo4jRelationship.CREATED_BY, user1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		neo.readAllNodes();
		System.out.println("RELATIONS");
		neo.getDirectRelationsTo("0");
		neo.closeDb();

	}

}
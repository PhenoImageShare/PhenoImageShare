package uk.ac.ebi.phis.utils.ontology;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class OntologyUtilsTest {

	OntologyUtils ou = new OntologyUtils();
	
	@Test
	public void testGetStageTopLevel(){
		
		List<OntologyObject> facets = ou.getStageTopLevel("MmusDv_0000092");
		assertTrue(facets.size() == 1);
		
	}
	
	@Test
	public void testDirectParents(){
		
		OntologyObject obj = ou.getOntologyTermById("MmusDv_0000092");
		assertTrue(obj.getDirectparentTerms().size() == 2);
		
	}
	
	
	@Test 
	public void testIntermediateTerms(){
		
		OntologyObject obj = ou.getOntologyTermById("MmusDv_0000092");
		assertTrue(obj.getIntermediateTerms().size() == 2);		// 2, including part-of  
		
	}
		
}

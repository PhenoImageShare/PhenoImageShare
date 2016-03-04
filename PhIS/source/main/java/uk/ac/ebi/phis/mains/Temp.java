package uk.ac.ebi.phis.mains;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import uk.ac.ebi.phis.utils.ontology.OntologyUtils;

public class Temp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OntologyUtils ou = new OntologyUtils();
		try {
			ou.getRoots();
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

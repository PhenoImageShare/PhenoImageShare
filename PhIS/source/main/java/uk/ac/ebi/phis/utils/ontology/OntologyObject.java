package uk.ac.ebi.phis.utils.ontology;

import java.util.ArrayList;


public class OntologyObject {
	
	String id;
	String label;
	ArrayList<String> synonyms;
	ArrayList<OntologyObject> intermediateTerms;
	ArrayList<OntologyObject> topLevelTerms;
	ArrayList<OntologyObject> directParentTerms;
	
	
	public OntologyObject(){
		intermediateTerms = new ArrayList<>();
		topLevelTerms = new ArrayList<>();
		directParentTerms = new ArrayList<>();
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
	
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
	
		this.id = id;
	}
	
	/**
	 * @return the label
	 */
	public String getLabel() {
	
		return label;
	}
	
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
	
		this.label = label;
	}
	
	/**
	 * @return the synonyms
	 */
	public ArrayList<String> getSynonyms() {
	
		return synonyms;
	}
	
	/**
	 * @param synonyms the synonyms to set
	 */
	public void setSynonyms(ArrayList<String> synonyms) {
	
		this.synonyms = synonyms;
	}
	
	public void addSynonym(String synonym) {
		
		if (this.synonyms == null){
			this.synonyms = new ArrayList<>();
		}
		this.synonyms.add(synonym);
	}
	
	
	/**
	 * @return the intermediateTerms
	 */
	public ArrayList<OntologyObject> getIntermediateTerms() {
	
		return intermediateTerms;
	}
	
	/**
	 * @param intermediateTerms the intermediateTerms to set
	 */
	public void setIntermediateTerms(ArrayList<OntologyObject> intermediateTerms) {
	
		this.intermediateTerms = intermediateTerms;
	}
	
	/**
	 * @return the topLevelTerms
	 */
	public ArrayList<OntologyObject> getTopLevelTerms() {
	
		return topLevelTerms;
	}
	
	/**
	 * @param topLevelTerms the topLevelTerms to set
	 */
	public void setTopLevelTerms(ArrayList<OntologyObject> topLevelTerms) {
	
		this.topLevelTerms = topLevelTerms;
	}
	
	/**
	 * @return the directparentTerms
	 */
	public ArrayList<OntologyObject> getDirectparentTerms() {
	
		return directParentTerms;
	}
	
	/**
	 * @param directparentTerms the directparentTerms to set
	 */
	public void setDirectparentTerms(ArrayList<OntologyObject> directparentTerms) {
	
		this.directParentTerms = directparentTerms;
	}
	
}

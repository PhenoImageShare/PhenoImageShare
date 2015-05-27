package uk.ac.ebi.phis.utils.ontology;

import java.util.ArrayList;


public class OntologyObject {
	
	String id;
	String label;
	ArrayList<String> synonyms;
	ArrayList<String> ancestorsBag;
	ArrayList<OntologyObject> intermediateTerms;
	ArrayList<OntologyObject> topLevelTerms;
	ArrayList<OntologyObject> directParentTerms;
	
	public OntologyObject(){
		intermediateTerms = new ArrayList<>();
		topLevelTerms = new ArrayList<>();
		directParentTerms = new ArrayList<>();
		ancestorsBag = new ArrayList<>();
		synonyms = new ArrayList<>();
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
	
	
	public ArrayList<String> getAncestorsBag(){
		
		return ancestorsBag;
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
		for (OntologyObject oo : intermediateTerms){
			ancestorsBag.add(oo.id);
			ancestorsBag.add(oo.label);
			ancestorsBag.addAll(oo.synonyms);
		}
	}
	
	public void addIntermediateTerms(OntologyObject intermediateTerm) {
		
		intermediateTerms.add(intermediateTerm);
		ancestorsBag.add(intermediateTerm.id);
		ancestorsBag.add(intermediateTerm.label);
		ancestorsBag.addAll(intermediateTerm.synonyms);
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

	@Override
	public String toString() {

		return "OntologyObject [id=" + id + ", label=" + label + ", synonyms=" + synonyms + ", ancestorsBag=" + ancestorsBag + ", intermediateTerms=" 
			+ intermediateTerms + ", topLevelTerms=" + topLevelTerms + ", directParentTerms=" + directParentTerms + "]";
	}
	
}

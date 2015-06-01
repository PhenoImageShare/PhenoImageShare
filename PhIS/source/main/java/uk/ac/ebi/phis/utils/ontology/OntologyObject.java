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

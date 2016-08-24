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
/**
 * @author tudose
 */
package uk.ac.ebi.phis.release;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * @author tudose
 *
 */
public class ReleaseDocument {

	String releaseVersion;
	ReleaseEnvironment releaseEnvironment;
	int numberOfGenes;
	int numberOfRois;
	int numberOfImages;
	List<OntologyInstance> ontologiesUsed;
	List<SpeciesData> speciesWithData;
	List<DatasourceInstance> datasourcesUsed;
	HashSet<String> geneIds;
	
	public ReleaseDocument(){
		
	}
	
	public ReleaseDocument(String releaseVersion, ReleaseEnvironment releaseEnvironment, int numberOfGenes, int numberOfRois, int numberOfImages){
		
		setReleaseVersion(releaseVersion);
		setReleaseEnvironment(releaseEnvironment);
		setNumberOfGenes(numberOfGenes);
		setNumberOfRois(numberOfRois);
		setNumberOfImages(numberOfImages);
		
	}	
	
	public String getReleaseVersion() {
	
		return releaseVersion;
	}
	
	public void setReleaseVersion(String releaseVersion) {
	
		this.releaseVersion = releaseVersion;
	}
	
	public ReleaseEnvironment getReleaseEnvironment() {
	
		return releaseEnvironment;
	}
	
	public void setReleaseEnvironment(ReleaseEnvironment releaseEnvironment) {
	
		this.releaseEnvironment = releaseEnvironment;
	}
	
	public int getNumberOfGenes() {
	
		return numberOfGenes;
	}
	
	public void setNumberOfGenes(int numberOfGenes) {
	
		this.numberOfGenes = numberOfGenes;
	}
	
	public int getNumberOfRois() {
	
		return numberOfRois;
	}
	
	public void setNumberOfRois(int numberOfRois) {
	
		this.numberOfRois = numberOfRois;
	}
	
	public int getNumberOfImages() {
	
		return numberOfImages;
	}
	
	public void setNumberOfImages(int numberOfImages) {
	
		this.numberOfImages = numberOfImages;
	}
	
	public List<OntologyInstance> getOntologiesUsed() {
	
		return ontologiesUsed;
	}
	
	public void setOntologiesUsed(List<OntologyInstance> ontologiesUsed) {
	
		this.ontologiesUsed = ontologiesUsed;
	}
	
	public void addOntologiesUsed(OntologyInstance ontologiesUsed) {

		if (this.ontologiesUsed == null){
			this.ontologiesUsed  = new ArrayList<>();
		}
		
		this.ontologiesUsed.add(ontologiesUsed);
	}
	
	public List<SpeciesData> getSpeciesWithData() {
	
		return speciesWithData;
	}
	
	public void setSpeciesWithData(List<SpeciesData> speciesWithData) {
	
		this.speciesWithData = speciesWithData;
	}
	
	public void addSpeciesWithData(SpeciesData speciesWithData){
		
		if (this.speciesWithData == null){
			this.speciesWithData  = new ArrayList<>();
		}
		
		this.speciesWithData.add(speciesWithData);
	}
	
	public List<DatasourceInstance> getDatasourcesUsed() {
	
		return datasourcesUsed;
	}
	
	public void setDatasourcesUsed(List<DatasourceInstance> datasourcesUsed) {
	
		this.datasourcesUsed = datasourcesUsed;
	}
	
	public void addDatasourcesUsed(DatasourceInstance datasource){
		
		if (this.datasourcesUsed == null){
			this.datasourcesUsed  = new ArrayList<>();
		}
		
		this.datasourcesUsed.add(datasource);
	}

	
	public HashSet<String> getGeneIds() {
	
		return geneIds;
	}

	
	public void setGeneIds(HashSet<String> geneIds) {
	
		this.geneIds = geneIds;
	}

	public void addGeneIds(List<String> geneIds){
		
		if (this.geneIds == null){
			this.geneIds  = new HashSet<>();
		}
		
		this.geneIds.addAll(geneIds);
	}
}

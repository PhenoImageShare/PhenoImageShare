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
package uk.ac.ebi.phis.dto.solrj;

import org.apache.solr.client.solrj.beans.Field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AutosuggestDTO {
	

	public final static String ID = "id" ;
	public final static String AUTOSUGGEST_TERM_ID = "autosuggest_term_id" ;
	public final static String AUTOSUGGEST_TERM_LABEL = "autosuggest_term_label" ;
	public final static String AUTOSUGGEST_TERM_SYNONYMS = "autosuggest_term_synonyms" ;
	public final static String AUTOSUGGEST_TYPE = "autosuggest_type" ;
	
	public final static String IMAGE_DOC_ID = "image_doc_id" ;
	public final static String HOST_NAME = "host_name";
	public final static String IMAGE_GENERATED_BY = "image_generated_by";
	public final static String IMAGING_METHOD = "imaging_method";
	public final static String SAMPLE_PREPARATION = "sample_preparation";
	public final static String VISUALIZATION_METHOD = "visualisation_method" ;
	public final static String IMAGE_TYPE = "image_type";
	public final static String SAMPLE_TYPE = "sample_type";
	public final static String SAMPLE_GENERATED_BY = "sample_generated_by" ;
	public final static String TAXON = "taxon" ;
	public final static String ANATOMY = "anatomy";
	public final static String GENE = "gene" ;
	public final static String GENETIC_FEATURE = "genetic_feature" ;
	public final static String ZYGOSITY = "zygosity" ;
	public final static String ANATOMY_COMPUTED = "anatomy_computed" ;
	public final static String ABNORMAL_ANATOMY = "abnormal_anatomy" ;
	public final static String DEPICTED_ANATOMY = "depicted_anatomy" ;
	public final static String EXPRESSION_IN = "expression_in" ;
	public final static String MUTANT_GENE= "mutant_gene";
	public final static String EXPRESSED_GF = "expressed_gf" ;
	public final static String PHENOTYPE = "phenotype" ;
	public final static String SEX = "sex" ;
	public final static String STAGE = "stage" ;
	 
	public final static String  TERM_AUTOSUGGEST = "term_autosuggest" ;
	public final static String  TERM_AUTOSUGGEST_E = "term_autosuggest_e" ;
	public final static String  TERM_AUTOSUGGEST_NA = "term_autosuggest_na" ;
	public final static String TERM_AUTOSUGGEST_WS = "term_autosuggest_ws";

	@Field(ID)
	private String id;
	@Field(AUTOSUGGEST_TERM_ID)
	private String autosuggestTermId;
	@Field(AUTOSUGGEST_TERM_LABEL)
	private String autosuggestTermLabel;
	@Field(AUTOSUGGEST_TERM_SYNONYMS)
	private List<String> autosuggestTermSynonyms;
	@Field(AUTOSUGGEST_TYPE)
	private String autosuggestType;

	@Field(IMAGE_TYPE)
	private List<String> imageType;

	@Field(SAMPLE_TYPE)
	private List<String> sampleType;

	@Field(IMAGING_METHOD)
	private List<String> imagingMethod;

	@Field(SAMPLE_PREPARATION)
	private List<String> samplePreparation;

	@Field(IMAGE_GENERATED_BY)
	private List<String> imageGeneratedBy;

	@Field(SAMPLE_GENERATED_BY)
	private List<String> sampleGeneratedBy;
	
	@Field(HOST_NAME)
	private List<String> hostName;

	@Field(TAXON)
	private List<String> taxon;

	@Field(STAGE)
	private List<String> stage;
	
	@Field(VISUALIZATION_METHOD)
	private List<String> visualizationMethod;
	
	

	@Field(SEX)
	private List<String> sex;

	@Field(DEPICTED_ANATOMY)
	private List<String> depictedAnatomy;
	
	@Field(ANATOMY)
	private List<String> anatomy;
	
	@Field(GENETIC_FEATURE)
	private List<String> geneticFeature;

	@Field(GENE)
	private List<String> gene;

	@Field(ZYGOSITY)
	private List<String> zygosity;

	@Field(ANATOMY_COMPUTED)
	private List<String> anatomyComputed;
	
	@Field(ABNORMAL_ANATOMY)
	private List<String> abnormalAnatomy;

	@Field(EXPRESSED_GF)
	private List<String> expressedGf;

	@Field(EXPRESSION_IN)
	private List<String> expressionIn;

	@Field(MUTANT_GENE)
	private List<String> mutantGene;
	
	@Field(PHENOTYPE)
	private List<String> phenotype;
			
		public List<String> getHostName() {

			return hostName;
		}

		public void setHostName(List<String> hostName) {

			this.hostName = hostName;
		}

		public List<String> getImageGeneratedBy() {

			return imageGeneratedBy;
		}

		public void setImageGeneratedBy(List<String> imageGeneratedBy) {

			this.imageGeneratedBy = imageGeneratedBy;
		}

		public List<String> getSampleGeneratedBy() {

			return sampleGeneratedBy;
		}

		public void setSampleGeneratedBy(List<String> sampleGeneratedBy) {

			this.sampleGeneratedBy = sampleGeneratedBy;
		}
	
		public String getId() {

			return id;
		}

		public void setId(String id) {

			this.id = id;
		}

			
		public List<String> getImagingMethod() {
		
			return imagingMethod;
		}

		
		public void setImagingMethod(List<String> imagingMethod) {
		
			this.imagingMethod = imagingMethod;
		}

		
		public List<String> getSamplePreparation() {
		
			return samplePreparation;
		}

		
		public void setSamplePreparation(List<String> samplePreparation) {
		
			this.samplePreparation = samplePreparation;
		}

		
		public List<String> getVisualizationMethod() {
		
			return visualizationMethod;
		}

		
		public void setVisualizationMethod(List<String> visualizationMethod) {
		
			this.visualizationMethod = visualizationMethod;
		}
	
		
		public List<String> getSex() {
		
			return sex;
		}

		
		public void setSex(List<String> sex) {
		
			this.sex = sex;
		}

		
		public List<String> getDepictedAnatomy() {
		
			return depictedAnatomy;
		}

		
		public void setDepictedAnatomy(List<String> depictedAnatomy) {
		
			this.depictedAnatomy = depictedAnatomy;
		}

		
		public List<String> getAnatomy() {
		
			return anatomy;
		}

		
		public void setAnatomy(List<String> anatomy) {
		
			this.anatomy = anatomy;
		}

		
		public List<String> getGene() {
		
			return gene;
		}

		
		public void setGene(List<String> gene) {
		
			this.gene = gene;
		}

		
		public List<String> getZygosity() {
		
			return zygosity;
		}

		
		public void setZygosity(List<String> zygosity) {
		
			this.zygosity = zygosity;
		}

		public List<String> getAnatomyComputed() {
		
			return anatomyComputed;
		}

		
		public void setAnatomyComputed(List<String> anatomyComputed) {
		
			this.anatomyComputed = anatomyComputed;
		}

		
		public List<String> getAbnormalAnatomy() {
		
			return abnormalAnatomy;
		}

		
		public void setAbnormalAnatomy(List<String> abnormalAnatomy) {
		
			this.abnormalAnatomy = abnormalAnatomy;
		}

		
		public List<String> getExpressedGf() {
		
			return expressedGf;
		}

		
		public void setExpressedGf(List<String> expressedGf) {
		
			this.expressedGf = expressedGf;
		}

		
		public List<String> getExpressionIn() {
		
			return expressionIn;
		}

		
		public void setExpressionIn(List<String> expressionIn) {
		
			this.expressionIn = expressionIn;
		}

		
		public List<String> getMutantGene() {
		
			return mutantGene;
		}

		
		public void setMutantGene(List<String> mutantGene) {
		
			this.mutantGene = mutantGene;
		}

		
		public List<String> getPhenotype() {
		
			return phenotype;
		}

		
		public void setPhenotype(List<String> phenotype) {
		
			this.phenotype = phenotype;
		}

		public List<String> getImageType() {
		
			return imageType;
		}

		public void setImageType(List<String> imageType) {
		
			this.imageType = imageType;
		}
		
		public List<String> getSampleType() {
		
			return sampleType;
		}
		
		public void setSampleType(List<String> sampleType) {
		
			this.sampleType = sampleType;
		}

		
		
		
		public List<String> getTaxon() {
		
			return taxon;
		}

		public void setTaxon(List<String> taxon) {

			this.taxon = taxon;
		}
		
		public void addTaxon(String taxon) {

			if (this.taxon == null) {
				this.taxon = new ArrayList<>();
			}
			if (!this.taxon.contains(taxon)){
				this.taxon.add(taxon);
			}
		}

		public List<String> getStage() {
		
			return stage;
		}

		public void setStage(List<String> stage) {
		
			this.stage = stage;
		}
		
		public void addStage(String stage) {

			if (this.stage == null) {
				this.stage = new ArrayList<>();
			}
			if (!this.stage.contains(stage)){
				this.stage.add(stage);
			}
		}

		public void addStage(List<String> stages) {

			if (this.stage == null) {
				this.stage = new ArrayList<>();
			}
			if (stages != null && !this.stage.containsAll(stages)){
				this.stage.addAll(stages);
			}
		}

		
		public void addSamplePreparation(String samplePreparation) {

			if (this.samplePreparation == null) {
				this.samplePreparation = new ArrayList<>();
			}
			if (!this.samplePreparation.contains(samplePreparation)){
				this.samplePreparation.add(samplePreparation);
			}
		}

		public void addHostName(String hostName) {

			if (this.hostName == null) {
				this.hostName = new ArrayList<>();
			}
			if (!this.hostName.contains(hostName)){
				this.hostName.add(hostName);
			}
		}

		public void addImageGeneratedBy(String imageGeneratedBy) {

			if (this.imageGeneratedBy == null) {
				this.imageGeneratedBy = new ArrayList<>();
			}
			if (!this.imageGeneratedBy.contains(imageGeneratedBy)){
				this.imageGeneratedBy.add(imageGeneratedBy);
			}
		}
		
		public void addImageGeneratedBy(List<String> imageGeneratedBy) {

			if (imageGeneratedBy != null){
				if (this.imageGeneratedBy == null) {
					this.imageGeneratedBy = new ArrayList<>();
				}
				for (String i : imageGeneratedBy){
					if (!this.imageGeneratedBy.contains(i)){
						this.imageGeneratedBy.add(i);
					}
				}
			}
		}

		public void addSampleGeneratedBy(String sampleGeneratedBy) {

			if (this.sampleGeneratedBy == null) {
				this.sampleGeneratedBy = new ArrayList<>();
			}
			if (!this.sampleGeneratedBy.contains(sampleGeneratedBy)){
				this.sampleGeneratedBy.add(sampleGeneratedBy);
			}
		}
		
		public void addSampleType(String sampleType) {

			if (this.sampleType == null) {
				this.sampleType = new ArrayList<>();
			}
			if (!this.sampleType.contains(sampleType)){
				this.sampleType.add(sampleType);
			}
		}
		
		public void addImagingMethod(String imagingMethod) {

			if (this.imagingMethod == null) {
				this.imagingMethod = new ArrayList<>();
			}
			if (!this.imagingMethod.contains(imagingMethod)){
				this.imagingMethod.add(imagingMethod);
			}
		}

		public void addImagingMethod(List<String> imagingMethod) {
		
			if (imagingMethod != null){
				Set<String> set =  new HashSet(imagingMethod);
				if (this.imagingMethod != null) {
					set.addAll(this.imagingMethod);
				}
				this.imagingMethod = new ArrayList<String>(set);
			}
		}

		public void addSex(String sex) {

			if (this.sex == null) {
				this.sex = new ArrayList<>();
			}
			if (!this.sex.contains(sex)){
				this.sex.add(sex);
			}
		}


		public void addPhenotype(List<String> phenotype) {

			if (phenotype != null) {
				Set<String> set = new HashSet(phenotype);
				if (this.phenotype != null) {
					set.addAll(this.phenotype);
				}
				this.phenotype = new ArrayList<String>(set);
			}
		}
		
		public void addSamplePreparation(List<String> samplePreparation) {
			
			if (samplePreparation != null) {
				Set<String> set = new HashSet(samplePreparation);
				if (this.samplePreparation != null) {
					set.addAll(this.samplePreparation);
				}
				this.samplePreparation = new ArrayList<String>(set);
			}
		}
		
		public void addGene(List<String> gene) {

			if (gene != null) {
				Set<String> set = new HashSet(gene);
				if (this.gene != null) {
					set.addAll(this.gene);
				}
				this.gene = new ArrayList<String>(set);
			}
		}
		
		public void addGeneticFeature(List<String> geneticFeature) {

			if (geneticFeature != null) {
				Set<String> set = new HashSet(geneticFeature);
				if (this.geneticFeature != null) {
					set.addAll(this.geneticFeature);
				}
				this.geneticFeature = new ArrayList<String>(set);
			}
		}


	public void addExpressionIn(List<String> expressionIn) {

		if (expressionIn != null) {
			Set<String> set = new HashSet(expressionIn);
			if (this.expressionIn != null) {
				set.addAll(this.expressionIn);
			}
			this.expressionIn = new ArrayList<String>(set);
		}
	}
	
	public void addAbnormalAnatomy(List<String> abnormalAnatomy) {

		if (abnormalAnatomy != null) {
			Set<String> set = new HashSet(abnormalAnatomy);
			if (this.abnormalAnatomy != null) {
				set.addAll(this.abnormalAnatomy);
			}
			this.abnormalAnatomy = new ArrayList<String>(set);
		}
	}
	
	public void addMutantGenes(List<String> mutantGene) {

		if (mutantGene != null) {
			Set<String> set = new HashSet(mutantGene);
			if (this.mutantGene != null) {
				set.addAll(this.mutantGene);
			}
			this.mutantGene = new ArrayList<String>(set);
		}
	}
	
	public void addDepictedAnatomy(List<String> depictedAnatomy) {

		if (depictedAnatomy != null) {
			Set<String> set = new HashSet(depictedAnatomy);
			if (this.depictedAnatomy != null) {
				set.addAll(this.depictedAnatomy);
			}
			this.depictedAnatomy = new ArrayList<String>(set);
		}
	}
	public void addVisualizationMethod(List<String> visualizationMethod) {

			Set<String> set = new HashSet(imageType);
			if (this.visualizationMethod != null) {
				set.addAll(this.visualizationMethod);
			}
			this.visualizationMethod = new ArrayList<String>(set);
		}

		
		public void addImageType(List<String> imageType) {

			Set<String> set = new HashSet(imageType);
			if (this.imageType != null) {
				set.addAll(this.imageType);
			}
			this.imageType = new ArrayList<String>(set);
		}

		public String getAutosuggestTermId() {
		
			return autosuggestTermId;
		}

		public void setAutosuggestTermId(String autosuggestTermId) {
		
			this.autosuggestTermId = autosuggestTermId;
		}

		public String getAutosuggestTermLabel() {
		
			return autosuggestTermLabel;
		}

		public void setAutosuggestTermLabel(String autosuggestTermLabel) {
		
			this.autosuggestTermLabel = autosuggestTermLabel;
		}

		public List<String> getAutosuggestTermSynonyms() {
		
			return autosuggestTermSynonyms;
		}

		public void setAutosuggestTermSynonyms(List<String> list) {
		
			this.autosuggestTermSynonyms = list;
		}

		public String getAutosuggestType() {
		
			return autosuggestType;
		}

		public void setAutosuggestType(String autosuggestType) {
		
			this.autosuggestType = autosuggestType;
		}
		
		
}

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
package uk.ac.ebi.phis.solrj.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.solr.client.solrj.beans.Field;

import uk.ac.ebi.phis.service.ImageService;

public class ImageDTO {

	public final static String ANATOMY_ID = "anatomy_id";
	public final static String GENE_ID = "gene_id";
	public final static String CHROMOSOME = "chromosome";
	public final static String ZYGOSITY = "zygosity";
	public final static String DEPTH = "depth";
	public final static String STRAND = "strand";
	public final static String END_POS = "end_pos";
	public final static String START_POS = "start_pos";
	public final static String INSERTCHROMOSOME = "chromosome";
	public final static String GF_ENEMBL_ID = "genetic_feature_ensembl_id";
	public final static String GF_SYMBOL = "genetic_feature_symbol";
	public final static String GF_ID = "genetic_feature_id";
	public final static String GF_SYNONYMS = "genetic_feature_synonyms";
	public final static String GENE_SYMBOL = "gene_symbol";
	public final static String GENE_SYNONYMS = "gene_synonyms";
	public final static String MUTATION_TYPE = "mutation_type";
	public final static String AGE = "age";
	public final static String CONDITIONS = "conditions";
	public final static String OBSERVATIONS = "observations";
	public final static String ANATOMY_FREETEXT = "anatomy_freetext";
	public final static String ANATOMY_TERM = "anatomy_term";
	public final static String ANATOMY_SYNONYMS = "anatomy_synonyms";
	public final static String ANATOMY_ANCESTORS = "anatomy_ancestors";
	public final static String STAGE_ID = "stage_id";
	public final static String STAGE = "stage";
	public final static String STAGE_ANCESTORS = "stage_ancestors";
	public final static String SEX = "sex";
	public final static String NCBI_TAXON_ID = "ncbi_taxon_id";
	public final static String TAXON = "taxon";
	public final static String SAMPLE_GENERATED_BY = "sample_generated_by";
	public final static String WIDTH = "width";
	public final static String IMAGE_TYPE = "image_type";
	public final static String SAMPLE_TYPE = "sample_type";

	public final static String ORIGINAL_IMAGE_ID = "original_image_id";
	public final static String MACHINE = "machine";
	public final static String VISUALISATION_METHOD_LABEL = "visualisation_method_label";
	public final static String VISUALISATION_METHOD_FREETEXT = "visualisation_method_freetext";
	public final static String VISUALISATION_METHOD_ID = "visualisation_method_id";
	public final static String VISUALISATION_METHOD_SYNONYMS = "visualisation_method_synonyms";
	public final static String VISUALISATION_METHOD_ANCESTORS = "visualisation_method_ancestors";
	public final static String SAMPLE_PREPARATION_LABEL = "sample_preparation_label";
	public final static String SAMPLE_PREPARATION_FREETEXT = "sample_preparation_freetext";
	public final static String SAMPLE_PREPARATION_ID = "sample_preparation_id";
	public final static String SAMPLE_PREPARATION_SYNONYMS = "sample_preparation_synonyms";
	public final static String SAMPLE_PREPARATION_ANCESTORS = "sample_preparation_ancestors";
	public final static String IMAGING_METHOD_ID = "imaging_method_id";
	public final static String IMAGING_METHOD_LABEL = "imaging_method_label";
	public final static String IMAGING_METHOD_FREETEXT = "imaging_method_freetext";
	public final static String IMAGING_METHOD_LABEL_ANALYSED = "imaging_method_label_analysed";
	public final static String IMAGING_METHOD_SYNONYMS = "imaging_method_synonym";
	public final static String IMAGING_METHOD_ANCESTORS = "imaging_method_ancestors";
	public final static String IMAGE_CONTEXT_URL = "image_context_url";
	public final static String IMAGE_URL = "image_url";
	public final static String LICENCE = "licence";
	public final static String THUMBNAIL_URL = "thumbnail_url";
	public final static String IMAGE_GENERATED_BY = "image_generated_by";
	public final static String HOST_NAME = "host_name";
	public final static String HOST_URL = "host_url";
	public final static String HEIGHT = "height";
	public final static String ASSOCIATED_CHANNEL = "associated_channel";
	public final static String ASSOCIATED_ROI = "associated_roi";
	public final static String PUBLICATION = "publication";
	public final static String MAGNIFICATION_LEVEL = "magnification_level";
	public final static String GENOME_ASSEMBLY = "genome_assembly";
	public final static String ID = "id";

	public final static String ANATOMY_COMPUTED_ID_BAG = "anatomy_computed_id_bag";
	public final static String ANATOMY_COMPUTED_TERM_BAG = "anatomy_computed_term_bag";
	public final static String ANATOMY_COMPUTED_SYNONYMS_BAG = "anatomy_computed_synonyms_bag";
	public final static String ANATOMY_COMPUTED_ANCESTORS = "anatomy_computed_ancestors";

	public final static String DEPICTED_ANATOMY_ID_BAG = "depicted_anatomy_id_bag";
	public final static String DEPICTED_ANATOMY_TERM_BAG = "depicted_anatomy_term_bag";
	public final static String DEPICTED_ANATOMY_FREETEXT_BAG = "depicted_anatomy_freetext_bag";
	public final static String DEPICTED_ANATOMY_SYNONYMS_BAG = "depicted_anatomy_synonyms_bag";
	public final static String DEPICTED_ANATOMY_ANCESTORS = "depicted_anatomy_ancestors";

	public final static String ABNORMAL_ANATOMY_ID_BAG = "abnormal_anatomy_id_bag";
	public final static String ABNORMAL_ANATOMY_TERM_BAG = "abnormal_anatomy_term_bag";
	public final static String ABNORMAL_ANATOMY_FREETEXT_BAG = "abnormal_anatomy_freetext_bag";
	public final static String ABNORMAL_ANATOMY_SYNONYMS_BAG = "abnormal_anatomy_synonyms_bag";
	public final static String ABNORMAL_ANATOMY_ANCESTORS = "abnormal_anatomy_ancestors";

	public final static String EXPRESSION_IN_ID_BAG = "expression_in_id_bag";
	public final static String EXPRESSION_IN_LABEL_BAG = "expression_in_label_bag";
	public final static String EXPRESSION_IN_FREETEXT_BAG = "expression_in_freetext_bag";
	public final static String EXPRESSION_IN_SYNONYMS_BAG = "expression_in_synonyms_bag";
	public final static String EXPRESSION_IN_ANCESTORS = "expression_in_ancestors";

	public final static String OBSERVATION_BAG = "observation_bag";
	public final static String MUTANT_GENE_ID_BAG = "mutant_gene_id_bag";
	public final static String MUTANT_GENE_SYMBOL_BAG = "mutant_gene_symbol_bag";
	public final static String MUTANT_GENE_SYNONYMS_BAG = "mutant_gene_synonyms_bag";
	public final static String EXPRESSED_GF_ID_BAG = "expressed_gf_id_bag";
	public final static String EXPRESSED_GF_SYMBOL_BAG = "expressed_gf_symbol_bag";
	public final static String EXPRESSED_GF_SYNONYMS_BAG = "expressed_gf_synonyms_bag";
	public final static String EXPRESSED_GF_MUTATION_TYPE = "expressed_gf_mutation_type";

	public final static String PHENOTYPE_ID_BAG = "phenotype_id_bag";
	public final static String PHENOTYPE_LABEL_BAG = "phenotype_label_bag";
	public final static String PHENOTYPE_FREETEXT_BAG = "phenotype_freetext_bag";
	public final static String PHENOTYPE_SYNONYMS_BAG = "phenotype_synonyms_bag";
	public final static String PHENOTYPE_ANCESTORS = "phenotype_ancestors";

	public final static String TERM_AUTOSUGGEST = "term_autosuggest";
	public final static String GENERIC_SEARCH = "generic_search";
	public final static String GENERIC_SEARCH_ANCESTORS = "generic_search_ancestors";
	public final static String GENERIC_ANATOMY = "generic_anatomy";
	public final static String GENERIC_ANATOMY_ANCESTORS = "generic_anatomy_ancestors";

	@Field(ID)
	private String id;

	@Field(ASSOCIATED_ROI)
	private List<String> associatedRoi;

	@Field(ASSOCIATED_CHANNEL)
	private List<String> associatedChannel;

	@Field(HEIGHT)
	private Integer height;

	@Field(HOST_URL)
	private String hostUrl;

	@Field(HOST_NAME)
	private String hostName;

	@Field(IMAGE_GENERATED_BY)
	private ArrayList<String> imageGeneratedBy;

	@Field(IMAGE_TYPE)
	private ArrayList<String> imageType;

	@Field(SAMPLE_TYPE)
	private String sampleType;

	@Field(IMAGE_URL)
	private String imageUrl;
	
	@Field(LICENCE)
	private String licence;
	
	@Field(THUMBNAIL_URL)
	private String thumbnailUrl;

	@Field(IMAGE_CONTEXT_URL)
	private String imageContextUrl;

	@Field(IMAGING_METHOD_ID)
	private ArrayList<String> imagingMethodId;

	@Field(IMAGING_METHOD_LABEL)
	private ArrayList<String> imagingMethodLabel;

	@Field(IMAGING_METHOD_SYNONYMS)
	private ArrayList<String> imagingMethodSynonyms;

	@Field(IMAGING_METHOD_FREETEXT)
	private ArrayList<String> imagingMethodFreetext;
	
	@Field(IMAGING_METHOD_ANCESTORS)
	private ArrayList<String> imagingMethodAncestors;

	@Field(SAMPLE_PREPARATION_ID)
	private ArrayList<String> samplePreparationId;

	@Field(SAMPLE_PREPARATION_LABEL)
	private ArrayList<String> samplePreparationLabel;

	@Field(SAMPLE_PREPARATION_SYNONYMS)
	private ArrayList<String> samplePreparationSynonyms;
	
	@Field(SAMPLE_PREPARATION_FREETEXT)
	private ArrayList<String> samplePreparationFreetext;
	
	@Field(SAMPLE_PREPARATION_ANCESTORS)
	private ArrayList<String> samplePreparationAncestors;

	@Field(VISUALISATION_METHOD_ID)
	private ArrayList<String> visualisationMethodId;

	@Field(VISUALISATION_METHOD_LABEL)
	private ArrayList<String> visualisationMethodLabel;
	
	@Field(VISUALISATION_METHOD_FREETEXT)
	private ArrayList<String> visualisationMethodFreetext;

	@Field(VISUALISATION_METHOD_SYNONYMS)
	private ArrayList<String> visualisationMethodSynonyms;
	
	@Field(VISUALISATION_METHOD_ANCESTORS)
	private ArrayList<String> visualisationMethodAncestors;

	@Field(MACHINE)
	private String machine;

	@Field(WIDTH)
	private Integer width;

	@Field(GENERIC_SEARCH)
	private List<String> genericSearch;

	@Field(GENERIC_ANATOMY)
	private List<String> genericAnatomy;
	
	@Field(GENERIC_ANATOMY_ANCESTORS)
	private List<String> genericAnatomyAncestors;
	
	@Field(AGE)
	private String age;

	@Field(SAMPLE_GENERATED_BY)
	private String sampleGeneratedBy;

	@Field(TAXON)
	private String taxon;

	@Field(NCBI_TAXON_ID)
	private String ncbiTaxonId;

	@Field(SEX)
	private String sex;

	@Field(STAGE)
	private String stage;

	@Field(STAGE_ID)
	private String stageId;

	@Field(STAGE_ANCESTORS)
	private ArrayList<String> stageAncestors;
	
	@Field(PUBLICATION)
	private List<String> publication;
	
	@Field(MAGNIFICATION_LEVEL)
	private String magnificationLevel;
	
	@Field(GENOME_ASSEMBLY)
	private List<String> genomeAssembly;

	// annotations -->

	@Field(ANATOMY_ID)
	private String anatomyId;

	@Field(ANATOMY_TERM)
	private String anatomyTerm;

	@Field(ANATOMY_FREETEXT)
	private String anatomyFreetext;

	@Field(ANATOMY_SYNONYMS)
	private ArrayList<String> anatomySynonyms;
	
	@Field(ANATOMY_ANCESTORS)
	private ArrayList<String> anatomyAncestors;

	@Field(OBSERVATIONS)
	private List<String> observations;

	@Field(CONDITIONS)
	private List<String> conditions;

	@Field(GENE_ID)
	private List<String> geneIds;

	@Field(GENE_SYMBOL)
	private List<String> geneSymbols;

	@Field(GENE_SYNONYMS)
	private List<String> geneSynonyms;

	@Field(GF_ID)
	private List<String> geneticFeatureIds;
	
	@Field(MUTATION_TYPE)
	private List<String> mutationType;

	@Field(GF_SYMBOL)
	private List<String> geneticFeatureSymbols;

	@Field(GF_SYNONYMS)
	private List<String> geneticFeatureSynonyms;

	@Field(GF_ENEMBL_ID)
	private List<String> genetifFeatureEnsemlIds;

	@Field(CHROMOSOME)
	private List<String> chromosome;

	@Field(START_POS)
	private List<Long> startPosition;

	@Field(END_POS)
	private List<Long> endPosition;

	@Field(STRAND)
	private List<String> strand;

	@Field(ZYGOSITY)
	private List<String> zygosity;

	@Field(DEPTH)
	private int depth;

	@Field(ANATOMY_COMPUTED_ID_BAG)
	private ArrayList<String> anatomyComputedIdBag;

	@Field(ANATOMY_COMPUTED_TERM_BAG)
	private ArrayList<String> anatomyComputedLabelBag;

	@Field(ANATOMY_COMPUTED_SYNONYMS_BAG)
	private ArrayList<String> anatomyComputedSynonymsBag;

	@Field(ANATOMY_COMPUTED_ANCESTORS)
	private ArrayList<String> anatomyComputedAncestors;

	@Field(DEPICTED_ANATOMY_ID_BAG)
	private ArrayList<String> depictedAnatomyIdBag;

	@Field(DEPICTED_ANATOMY_TERM_BAG)
	private ArrayList<String> depictedAnatomyTermBag;

	@Field(DEPICTED_ANATOMY_FREETEXT_BAG)
	private ArrayList<String> depictedAnatomyFreetextBag;

	@Field(DEPICTED_ANATOMY_SYNONYMS_BAG)
	private ArrayList<String> depictedAnatomySynonymsBag;

	@Field(DEPICTED_ANATOMY_ANCESTORS)
	private ArrayList<String> depictedAnatomyAncestors;

	@Field(ABNORMAL_ANATOMY_ID_BAG)
	private ArrayList<String> abnormalAnatomyIdBag;

	@Field(ABNORMAL_ANATOMY_TERM_BAG)
	private ArrayList<String> abnormalAnatomyTermBag;

	@Field(ABNORMAL_ANATOMY_FREETEXT_BAG)
	private ArrayList<String> abnormalAnatomyFreetextBag;

	@Field(ABNORMAL_ANATOMY_SYNONYMS_BAG)
	private ArrayList<String> abnormalAnatomySynonymsBag;

	@Field(ABNORMAL_ANATOMY_ANCESTORS)
	private ArrayList<String> abnormalAnatomyAncestors;

	@Field(EXPRESSED_GF_ID_BAG)
	private ArrayList<String> expressedGfIdBag;

	@Field(EXPRESSED_GF_SYMBOL_BAG)
	private ArrayList<String> expressedGfSymbolBag;

	@Field(EXPRESSED_GF_SYNONYMS_BAG)
	private ArrayList<String> expressedGfSynonymsBag;
	
	@Field(EXPRESSED_GF_MUTATION_TYPE)
	private ArrayList<String> expressedGfMutationType;

	@Field(EXPRESSION_IN_ID_BAG)
	private ArrayList<String> expressionInIdBag;

	@Field(EXPRESSION_IN_LABEL_BAG)
	private ArrayList<String> expressionInLabelBag;

	@Field(EXPRESSION_IN_FREETEXT_BAG)
	private ArrayList<String> expressionInFreetextBag;

	@Field(EXPRESSION_IN_SYNONYMS_BAG)
	private ArrayList<String> expressionInSynonymsBag;

	@Field(EXPRESSION_IN_ANCESTORS)
	private ArrayList<String> expressionInAncestors;

	@Field(MUTANT_GENE_ID_BAG)
	private ArrayList<String> mutantGeneIdBag;

	@Field(MUTANT_GENE_SYMBOL_BAG)
	private ArrayList<String> mutantGeneSymbolBag;

	@Field(MUTANT_GENE_SYNONYMS_BAG)
	private ArrayList<String> mutantGeneSynonymsBag;

	@Field(OBSERVATION_BAG)
	private ArrayList<String> observationBag;

	@Field(PHENOTYPE_ID_BAG)
	private ArrayList<String> phenotypeIdBag;

	@Field(PHENOTYPE_LABEL_BAG)
	private ArrayList<String> phenotypeLabelBag;

	@Field(PHENOTYPE_FREETEXT_BAG)
	private ArrayList<String> phenotypeFreetextBag;

	@Field(PHENOTYPE_SYNONYMS_BAG)
	private ArrayList<String> phenotypeSynonymsBag;

	@Field(PHENOTYPE_ANCESTORS)
	private ArrayList<String> phenotypeAncestors;
	
	public List<String> getGenericSearch() {
	
		return genericSearch;
	}
	
	public void setGenericSearch(List<String> genericSearch) {
	
		this.genericSearch = genericSearch;
	}

	public ArrayList<String> getImagingMethodSynonyms() {
	
		return imagingMethodSynonyms;
	}

	public void setSamplePreparationSynonyms(ArrayList<String> samplePreparationSynonyms) {
	
		this.samplePreparationSynonyms = samplePreparationSynonyms;
	}
	
	public void setVisualisationMethodId(ArrayList<String> visualisationMethodId) {
	
		this.visualisationMethodId = visualisationMethodId;
	}

	public void setVisualisationMethodLabel(ArrayList<String> visualisationMethodLabel) {
	
		this.visualisationMethodLabel = visualisationMethodLabel;
	}

	public void setVisualisationMethodSynonyms(ArrayList<String> visualisationMethodSynonyms) {
	
		this.visualisationMethodSynonyms = visualisationMethodSynonyms;
	}

	public void setAnatomySynonyms(ArrayList<String> anatomySynonyms) {
	
		this.anatomySynonyms = anatomySynonyms;
	}

	public void setGeneSynonyms(List<String> geneSynonyms) {
	
		this.geneSynonyms = geneSynonyms;
	}

	public void setGeneticFeatureSynonyms(List<String> geneticFeatureSynonyms) {
	
		this.geneticFeatureSynonyms = geneticFeatureSynonyms;
	}
	
	
	public List<String> getMutationType() {
	
		return mutationType;
	}
	
	public void addMutationType(String mutationType) {
		
		if (this.mutationType == null){
			this.mutationType = new ArrayList<>();
		}
		this.mutationType.add(mutationType);
	}
	
	public void setMutationType(List<String> mutationType) {
	
		this.mutationType = mutationType;
	}

	
	public ArrayList<String> getExpressedGfMutationType() {
	
		return expressedGfMutationType;
	}

	
	public void setExpressedGfMutationType(ArrayList<String> expressedGfMutationType) {
	
		this.expressedGfMutationType = expressedGfMutationType;
	}

	public String getLicence() {
	
		return licence;
	}

	
	public void setLicence(String licence) {
	
		this.licence = licence;
	}

	public void setAnatomyComputedSynonymsBag(ArrayList<String> anatomyComputedSynonymsBag) {
	
		this.anatomyComputedSynonymsBag = anatomyComputedSynonymsBag;
	}

	
	public ArrayList<String> getImagingMethodFreetext() {
	
		return imagingMethodFreetext;
	}

	
	
	public String getThumbnailUrl() {
	
		return thumbnailUrl;
	}

	
	public void setThumbnailUrl(String thumbnailUrl) {
	
		this.thumbnailUrl = thumbnailUrl;
	}

	
	public ArrayList<String> getStageAncestors() {
	
		return stageAncestors;
	}

	
	public void setStageAncestors(ArrayList<String> stageAncestors) {
	
		this.stageAncestors = stageAncestors;
	}

	public void setImagingMethodFreetext(ArrayList<String> imagingMethodFreetext) {
	
		this.imagingMethodFreetext = imagingMethodFreetext;
	}

	
	public ArrayList<String> getSamplePreparationFreetext() {
	
		return samplePreparationFreetext;
	}

	
	public void setSamplePreparationFreetext(ArrayList<String> samplePreparationFreetext) {
	
		this.samplePreparationFreetext = samplePreparationFreetext;
	}

	
	public ArrayList<String> getVisualisationMethodFreetext() {
	
		return visualisationMethodFreetext;
	}

	
	public void setVisualisationMethodFreetext(ArrayList<String> visualisationMethodFreetext) {
	
		this.visualisationMethodFreetext = visualisationMethodFreetext;
	}


	public List<String> getGenericAnatomy() {
	
		return genericAnatomy;
	}

	
	public void setGenericAnatomy(List<String> genericAnatomy) {
	
		this.genericAnatomy = genericAnatomy;
	}

	public List<String> getGenericAnatomyAncestors() {
	
		return genericAnatomyAncestors;
	}

	
	public void setGenericAnatomyAncestors(List<String> genericAnatomyAncestors) {
	
		this.genericAnatomyAncestors = genericAnatomyAncestors;
	}

	
	public ArrayList<String> getAnatomyAncestors() {
	
		return anatomyAncestors;
	}

	
	public void setAnatomyAncestors(ArrayList<String> anatomyAncestors) {
	
		this.anatomyAncestors = anatomyAncestors;
	}

	
	public void setAnatomyComputedAncestors(ArrayList<String> anatomyComputedAncestors) {
	
		this.anatomyComputedAncestors = anatomyComputedAncestors;
	}

	
	public void setDepictedAnatomyAncestors(ArrayList<String> depictedAnatomyAncestors) {
	
		this.depictedAnatomyAncestors = depictedAnatomyAncestors;
	}

	
	public void setAbnormalAnatomyAncestors(ArrayList<String> abnormalAnatomyAncestors) {
	
		this.abnormalAnatomyAncestors = abnormalAnatomyAncestors;
	}

		
	
	public ArrayList<String> getImagingMethodAncestors() {
	
		return imagingMethodAncestors;
	}

	
	public void setImagingMethodAncestors(ArrayList<String> imagingMethodAncestors) {
	
		this.imagingMethodAncestors = imagingMethodAncestors;
	}

	
	public ArrayList<String> getSamplePreparationAncestors() {
	
		return samplePreparationAncestors;
	}

	
	public void setSamplePreparationAncestors(ArrayList<String> samplePreparationAncestors) {
	
		this.samplePreparationAncestors = samplePreparationAncestors;
	}

	
	public ArrayList<String> getVisualisationMethodAncestors() {
	
		return visualisationMethodAncestors;
	}

	
	public void setVisualisationMethodAncestors(ArrayList<String> visualisationMethodAncestors) {
	
		this.visualisationMethodAncestors = visualisationMethodAncestors;
	}

	public void setExpressionInAncestors(ArrayList<String> expressionInAncestors) {
	
		this.expressionInAncestors = expressionInAncestors;
	}

	
	public void setPhenotypeAncestors(ArrayList<String> phenotypeAncestors) {
	
		this.phenotypeAncestors = phenotypeAncestors;
	}

	/**
	 * @param anatomyComputedAncestorsIdBag the anatomyComputedAncestorsIdBag to set
	 */
	public void setAnatomyComputedAncestorsIdBag(ArrayList<String> anatomyComputedAncestorsIdBag) {
	
		this.anatomyComputedAncestors = anatomyComputedAncestorsIdBag;
	}

	
	/**
	 * @param depictedAnatomySynonymsBag the depictedAnatomySynonymsBag to set
	 */
	public void setDepictedAnatomySynonymsBag(ArrayList<String> depictedAnatomySynonymsBag) {
	
		this.depictedAnatomySynonymsBag = depictedAnatomySynonymsBag;
	}


	
	/**
	 * @param depictedAnatomyAncestorsIdBag the depictedAnatomyAncestorsIdBag to set
	 */
	public void setDepictedAnatomyAncestorsIdBag(ArrayList<String> depictedAnatomyAncestorsIdBag) {
	
		this.depictedAnatomyAncestors = depictedAnatomyAncestorsIdBag;
	}

	
	/**
	 * @param abnormalAnatomySynonymsBag the abnormalAnatomySynonymsBag to set
	 */
	public void setAbnormalAnatomySynonymsBag(ArrayList<String> abnormalAnatomySynonymsBag) {
	
		this.abnormalAnatomySynonymsBag = abnormalAnatomySynonymsBag;
	}


	
	/**
	 * @param abnormalAnatomyAncestorsIdBag the abnormalAnatomyAncestorsIdBag to set
	 */
	public void setAbnormalAnatomyAncestorsIdBag(ArrayList<String> abnormalAnatomyAncestorsIdBag) {
	
		this.abnormalAnatomyAncestors = abnormalAnatomyAncestorsIdBag;
	}

	
	/**
	 * @param expressedGfSynonymsBag the expressedGfSynonymsBag to set
	 */
	public void setExpressedGfSynonymsBag(ArrayList<String> expressedGfSynonymsBag) {
	
		this.expressedGfSynonymsBag = expressedGfSynonymsBag;
	}


	
	/**
	 * @param expressionInAncestorsIdBag the expressionInAncestorsIdBag to set
	 */
	public void setExpressionInAncestorsIdBag(ArrayList<String> expressionInAncestorsIdBag) {
	
		this.expressionInAncestors = expressionInAncestorsIdBag;
	}

	
	/**
	 * @param mutantGeneSynonymsBag the mutantGeneSynonymsBag to set
	 */
	public void setMutantGeneSynonymsBag(ArrayList<String> mutantGeneSynonymsBag) {
	
		this.mutantGeneSynonymsBag = mutantGeneSynonymsBag;
	}


	
	/**
	 * @param phenotypeSynonymsBag the phenotypeSynonymsBag to set
	 */
	public void setPhenotypeSynonymsBag(ArrayList<String> phenotypeSynonymsBag) {
	
		this.phenotypeSynonymsBag = phenotypeSynonymsBag;
	}


	
	/**
	 * @param phenotypeAncestorsIdBag the phenotypeAncestorsIdBag to set
	 */
	public void setPhenotypeAncestorsIdBag(ArrayList<String> phenotypeAncestorsIdBag) {
	
		this.phenotypeAncestors = phenotypeAncestorsIdBag;
	}


	
	/**
	 * @return the anatomyComputedAncestorsIdBag
	 */
	public ArrayList<String> getAnatomyComputedAncestors() {

		return anatomyComputedAncestors;
	}


	public void addAnatomyComputedAncestors(String anatomyComputedAncestorId) {

		if (this.anatomyComputedAncestors == null) {
			this.anatomyComputedAncestors = new ArrayList<>();
		}
		if (!this.anatomyComputedAncestors.contains(anatomyComputedAncestorId)) {
			this.anatomyComputedAncestors.add(anatomyComputedAncestorId);
		}
	}


	/**
	 * @return the depictedAnatomyAncestorsIdBag
	 */
	public ArrayList<String> getDepictedAnatomyAncestors() {

		return depictedAnatomyAncestors;
	}


	/**
	 * @param depictedAnatomyAncestorId
	 *            the depictedAnatomyAncestorsIdBag to set
	 */
	public void addDepictedAnatomyAncestors(String depictedAnatomyAncestorId) {

		if (this.depictedAnatomyAncestors == null){
			this.depictedAnatomyAncestors = new ArrayList<>();
		}
		if (depictedAnatomyAncestorId != null && !this.depictedAnatomyAncestors.contains(depictedAnatomyAncestorId)){
			this.depictedAnatomyAncestors.add(depictedAnatomyAncestorId);
		}
	}



	/**
	 * @param depictedAnatomyAncestorId
	 *            the depictedAnatomyAncestorsIdBag to set
	 */
	public void addDepictedAnatomyAncestors(ArrayList<String> depictedAnatomyAncestors) {
		
		if (depictedAnatomyAncestors != null){
			HashSet<String> ancestors = new HashSet<String>(depictedAnatomyAncestors);
			if (this.depictedAnatomyAncestors == null){
				this.depictedAnatomyAncestors = new ArrayList<>(ancestors);
			} else {
				this.depictedAnatomyAncestors.addAll(ancestors);
			}
		}
	}
	
	public void addStageAncestors(ArrayList<String> stageAncestors) {
		
		if (stageAncestors != null){
			HashSet<String> ancestors = new HashSet<String>(stageAncestors);
			if (this.stageAncestors == null){
				this.stageAncestors = new ArrayList<>(ancestors);
			} else {
				this.stageAncestors.addAll(ancestors);
			}
		}
	}
	
	/**
	 * @return the abnormalAnatomyAncestorsIdBag
	 */
	public ArrayList<String> getAbnormalAnatomyAncestors() {

		return abnormalAnatomyAncestors;
	}


	/**
	 * @param abnormalAnatomyAncestorId
	 *            the abnormalAnatomyAncestorsIdBag to set
	 */
	public void addAbnormalAnatomyAncestors(String abnormalAnatomyAncestor) {

		if (this.abnormalAnatomyAncestors == null){
			this.abnormalAnatomyAncestors = new ArrayList<>();
		}
		if (abnormalAnatomyAncestor != null && !this.abnormalAnatomyAncestors.contains(abnormalAnatomyAncestor)){
			this.abnormalAnatomyAncestors.add(abnormalAnatomyAncestor);
		}
	}
	
	public void addVisualisationMethodAncestors(String vmAncestor) {

		if (this.visualisationMethodAncestors == null){
			this.visualisationMethodAncestors = new ArrayList<>();
		}
		if (vmAncestor != null && !this.visualisationMethodAncestors.contains(vmAncestor)){
			this.visualisationMethodAncestors.add(vmAncestor);
		}
	}
	
	public void addVisualisationMethodAncestors(ArrayList<String> visualisationMethodAncestors) {
		if (visualisationMethodAncestors != null){
			HashSet<String> ancestors = new HashSet<String>(visualisationMethodAncestors);
			if (this.visualisationMethodAncestors == null){
				this.visualisationMethodAncestors = new ArrayList<>(ancestors);
			} else {
				this.visualisationMethodAncestors.addAll(ancestors);
			}
		}
	}
	
	public void addAbnormalAnatomyAncestors(ArrayList<String> abnormalAnatomyAncestors) {
		if (abnormalAnatomyAncestors != null){
			HashSet<String> ancestors = new HashSet<String>(abnormalAnatomyAncestors);
			if (this.abnormalAnatomyAncestors == null){
				this.abnormalAnatomyAncestors = new ArrayList<>(ancestors);
			} else {
				this.abnormalAnatomyAncestors.addAll(ancestors);
			}
		}
	}
	/**
	 * @return the expressionInAncestorsIdBag
	 */
	public ArrayList<String> getExpressionInAncestors() {

		return expressionInAncestors;
	}


	/**
	 * @param expressionInAncestorId
	 *            the expressionInAncestorsIdBag to set
	 */
	public void addExpressionInAncestors(String expressionInAncestor) {

		if (this.expressionInAncestors == null){
			this.expressionInAncestors = new ArrayList<>();
		}
		if (!this.expressionInAncestors.contains(expressionInAncestor)){
			this.expressionInAncestors.add(expressionInAncestor);
		}
	}
	
	public void addExpressionInSynonymsBag(ArrayList<String> expressionInSynonyms) {

		if (this.expressionInSynonymsBag == null){
			this.expressionInSynonymsBag = new ArrayList<>(expressionInSynonyms);
		} else {
			this.expressionInSynonymsBag.addAll(expressionInSynonyms);
		}
	}
	
	public void addExpressionInAncestors(ArrayList<String> expressionInAncestor) {

		if (expressionInAncestor != null){
			HashSet<String> set = new HashSet<String>(expressionInAncestor);
			
			if (this.expressionInAncestors == null){
				this.expressionInAncestors = new ArrayList<>(set);
			}
			if (!this.expressionInAncestors.contains(expressionInAncestor)){
				this.expressionInAncestors.addAll(new ArrayList<String> (set));
			}
		}
	}
	
	/**
	 * @return the phenotypeAncestorsIdBag
	 */
	public ArrayList<String> getPhenotypeAncestors() {

		return phenotypeAncestors;
	}


	/**
	 * @param phenotypeAncestorId
	 *            the phenotypeAncestorsIdBag to set
	 */
	public void addPhenotypeAncestors(String phenotypeAncestor) {

		if (this.phenotypeAncestors == null){
			this.phenotypeAncestors = new ArrayList<>();
		}
		if (!this.phenotypeAncestors.contains(phenotypeAncestor)){
			this.phenotypeAncestors.add(phenotypeAncestor);
		}
	}

	
	public void addPhenotypeAncestors(ArrayList<String> phenotypeAncestors) {
		
		if (phenotypeAncestors != null){
			HashSet<String> set = new HashSet<String>(phenotypeAncestors);
			if (this.phenotypeAncestors == null){
				this.phenotypeAncestors = new ArrayList<>(set);
			} else {
				this.phenotypeAncestors.addAll(new ArrayList<String>(set));
			}
		}
	}

	
	public void addVisualizationMethodAncestors(ArrayList<String> vmAncestors) {
			
		if (vmAncestors != null){
			HashSet<String> set = new HashSet<String>(vmAncestors);
			if (this.visualisationMethodAncestors == null){
				this.visualisationMethodAncestors = new ArrayList<>(set);
			} else {
				this.visualisationMethodAncestors.addAll(new ArrayList<String>(set));
			}
		}
	}
	
	public void addImagingMethodAncestors(ArrayList<String> imAncestors) {
		
		if (imAncestors != null){
			HashSet<String> set = new HashSet<String>(imAncestors);
			if (this.imagingMethodAncestors == null){
				this.imagingMethodAncestors = new ArrayList<>(set);
			} else {
				this.imagingMethodAncestors.addAll(new ArrayList<String>(set));
			}
		}
	}
	
	public void addSamplePreparationAncestors(ArrayList<String> spAncestors) {
		
		if (spAncestors != null){
			HashSet<String> set = new HashSet<String>(spAncestors);
			if (this.samplePreparationAncestors == null){
				this.samplePreparationAncestors = new ArrayList<>(set);
			} else {
				this.samplePreparationAncestors.addAll(new ArrayList<String>(set));
			}
		}
	}
	
	
	/**
	 * @param imagingMethodSynonyms
	 *            the imagingMethodSynonyms to set
	 */
	public void setImagingMethodSynonyms(ArrayList<String> imagingMethodSynonyms) {

		this.imagingMethodSynonyms = imagingMethodSynonyms;
	}


	/**
	 * @param imagingMethodSynonyms
	 *            the imagingMethodSynonyms to set
	 */
	public void addImagingMethodSynonyms(ArrayList<String> imagingMethodSynonyms) {

		if (this.imagingMethodSynonyms == null) {
			this.imagingMethodSynonyms = new ArrayList<>();
		}
		if (imagingMethodSynonyms != null)
			this.imagingMethodSynonyms.addAll(imagingMethodSynonyms);
	}

	public void addImagingMethodFreetext(String imagingMethodFreetext) {

		if (this.imagingMethodFreetext == null) {
			this.imagingMethodFreetext = new ArrayList<>();
		}
		if (imagingMethodFreetext != null)
			this.imagingMethodFreetext.add(imagingMethodFreetext);
	}
	
	public void addSamplePreparationFreetext(String samplePreparationFreetext) {

		if (this.samplePreparationFreetext == null) {
			this.samplePreparationFreetext = new ArrayList<>();
		}
		if (samplePreparationFreetext != null)
			this.samplePreparationFreetext.add(samplePreparationFreetext);
	}
	
	public void addVisualisationMethodFreetext(String visualisationMethodFreetext) {

		if (this.visualisationMethodFreetext == null) {
			this.visualisationMethodFreetext = new ArrayList<>();
		}
		if (visualisationMethodFreetext != null)
			this.visualisationMethodFreetext.add(visualisationMethodFreetext);
	}

	/**
	 * @return the samplePreparationSynonyms
	 */
	public ArrayList<String> getSamplePreparationSynonyms() {

		return samplePreparationSynonyms;
	}


	/**
	 * @param samplePreparationSynonyms
	 *            the samplePreparationSynonyms to set
	 */
	public void addSamplePreparationSynonyms(ArrayList<String> samplePreparationSynonyms) {

		if (this.samplePreparationSynonyms == null) {
			this.samplePreparationSynonyms = new ArrayList<>();
		}
		if (samplePreparationSynonyms != null)
			this.samplePreparationSynonyms.addAll(samplePreparationSynonyms);
	}


	/**
	 * @return the visualisationMethodSynonyms
	 */
	public ArrayList<String> getVisualisationMethodSynonyms() {

		return visualisationMethodSynonyms;
	}


	/**
	 * @param visualisationMethodSynonyms
	 *            the visualisationMethodSynonyms to set
	 */
	public void addVisualisationMethodSynonyms(ArrayList<String> visualisationMethodSynonyms) {

		if (this.visualisationMethodSynonyms == null) {
			this.visualisationMethodSynonyms = new ArrayList<>();
		}
		if (visualisationMethodSynonyms != null)
			this.visualisationMethodSynonyms.addAll(visualisationMethodSynonyms);
	}


	/**
	 * @return the anatomySynonyms
	 */
	public ArrayList<String> getAnatomySynonyms() {

		return anatomySynonyms;
	}


	/**
	 * @param anatomySynonyms
	 *            the anatomySynonyms to set
	 */
	public void addAnatomySynonyms(ArrayList<String> anatomySynonyms) {

		if (this.anatomySynonyms == null) {
			this.anatomySynonyms = new ArrayList<>();
		}
		if (anatomySynonyms != null)
			this.anatomySynonyms.addAll(anatomySynonyms);
	}


	/**
	 * @return the geneSynonyms
	 */
	public List<String> getGeneSynonyms() {

		return geneSynonyms;
	}


	/**
	 * @param geneSynonyms
	 *            the geneSynonyms to set
	 */
	public void addGeneSynonyms(List<String> geneSynonyms) {

		if (this.geneSynonyms == null) {
			this.geneSynonyms = new ArrayList<>();
		}
		if (geneSynonyms != null)
			this.geneSynonyms.addAll(geneSynonyms);
	}


	/**
	 * @return the geneticFeatureSynonyms
	 */
	public List<String> getGeneticFeatureSynonyms() {

		return geneticFeatureSynonyms;
	}


	/**
	 * @param geneticFeatureSynonyms
	 *            the geneticFeatureSynonyms to set
	 */
	public void addGeneticFeatureSynonyms(List<String> geneticFeatureSynonyms) {

		if (this.geneticFeatureSynonyms == null) {
			this.geneticFeatureSynonyms = new ArrayList<>();
		}
		if (geneticFeatureSynonyms != null)
			this.geneticFeatureSynonyms.addAll(geneticFeatureSynonyms);
	}


	/**
	 * @return the anatomyComputedSynonymsBag
	 */
	public ArrayList<String> getAnatomyComputedSynonymsBag() {

		return anatomyComputedSynonymsBag;
	}


	/**
	 * @param anatomyComputedSynonymsBag
	 *            the anatomyComputedSynonymsBag to set
	 */
	public void addAnatomyComputedSynonymsBag(ArrayList<String> anatomyComputedSynonymsBag) {

		if (this.anatomyComputedSynonymsBag == null) {
			this.anatomyComputedSynonymsBag = new ArrayList<>();
		}
		if (anatomyComputedSynonymsBag != null)
			this.anatomyComputedSynonymsBag.addAll(anatomyComputedSynonymsBag);
	}


	/**
	 * @return the depictedAnatomySynonymsBag
	 */
	public ArrayList<String> getDepictedAnatomySynonymsBag() {

		return depictedAnatomySynonymsBag;
	}


	/**
	 * @param depictedAnatomySynonymsBag
	 *            the depictedAnatomySynonymsBag to set
	 */
	public void addDepictedAnatomySynonymsBag(ArrayList<String> depictedAnatomySynonymsBag) {

		if (this.depictedAnatomySynonymsBag == null) {
			this.depictedAnatomySynonymsBag = new ArrayList<>();
		}
		if (depictedAnatomySynonymsBag != null)
			this.depictedAnatomySynonymsBag.addAll(depictedAnatomySynonymsBag);
	}


	/**
	 * @return the abnormalAnatomySynonymsBag
	 */
	public ArrayList<String> getAbnormalAnatomySynonymsBag() {

		return abnormalAnatomySynonymsBag;
	}


	/**
	 * @param abnormalAnatomySynonymsBag
	 *            the abnormalAnatomySynonymsBag to set
	 */
	public void addAbnormalAnatomySynonymsBag(ArrayList<String> abnormalAnatomySynonymsBag) {

		if (this.abnormalAnatomySynonymsBag == null) {
			this.abnormalAnatomySynonymsBag = new ArrayList<>();
		}
		if (abnormalAnatomySynonymsBag != null)
			this.abnormalAnatomySynonymsBag.addAll(abnormalAnatomySynonymsBag);
	}


	/**
	 * @return the expressedGfSynonymsBag
	 */
	public ArrayList<String> getExpressedGfSynonymsBag() {

		return expressedGfSynonymsBag;
	}


	/**
	 * @param expressedGfSynonymsBag
	 *            the expressedGfSynonymsBag to set
	 */
	public void addExpressedGfSynonymsBag(ArrayList<String> expressedGfSynonymsBag) {

		if (this.expressedGfSynonymsBag == null) {
			this.expressedGfSynonymsBag = new ArrayList<>();
		}
		if (expressedGfSynonymsBag != null)
			this.expressedGfSynonymsBag.addAll(expressedGfSynonymsBag);
	}


	/**
	 * @return the expressionInSynonymsBag
	 */
	public ArrayList<String> getExpressionInSynonymsBag() {

		return expressionInSynonymsBag;
	}


	/**
	 * @param expressionInSynonymsBag
	 *            the expressionInSynonymsBag to set
	 */
	public void setExpressionInSynonymsBag(ArrayList<String> expressionInSynonymsBag) {

		if (this.expressionInSynonymsBag == null) {
			this.expressionInSynonymsBag = new ArrayList<>();
		}
		if (expressionInSynonymsBag != null)
			this.expressionInSynonymsBag.addAll(expressionInSynonymsBag);
	}


	/**
	 * @return the mutantGeneSynonymsBag
	 */
	public ArrayList<String> getMutantGeneSynonymsBag() {

		return mutantGeneSynonymsBag;
	}


	/**
	 * @param mutantGeneSynonymsBag
	 *            the mutantGeneSynonymsBag to set
	 */
	public void addMutantGeneSynonymsBag(ArrayList<String> mutantGeneSynonymsBag) {

		if (this.mutantGeneSynonymsBag == null) {
			this.mutantGeneSynonymsBag = new ArrayList<>();
		}
		if (mutantGeneSynonymsBag != null)
			this.mutantGeneSynonymsBag.addAll(mutantGeneSynonymsBag);
	}


	/**
	 * @return the phenotypeSynonymsBag
	 */
	public ArrayList<String> getPhenotypeSynonymsBag() {

		return phenotypeSynonymsBag;
	}


	/**
	 * @param phenotypeSynonymsBag
	 *            the phenotypeSynonymsBag to set
	 */
	public void addPhenotypeSynonymsBag(ArrayList<String> phenotypeSynonymsBag) {

		if (this.phenotypeSynonymsBag == null) {
			this.phenotypeSynonymsBag = new ArrayList<>();
		}
		if (phenotypeSynonymsBag != null)
			this.phenotypeSynonymsBag.addAll(phenotypeSynonymsBag);
	}


	/**
	 * @return the depth
	 */
	public long getDepth() {

		return depth;
	}


	/**
	 * @return the stageId
	 */
	public String getStageId() {

		return stageId;
	}


	/**
	 * @param stageId
	 *            the stageId to set
	 */
	public void setStageId(String stageId) {

		this.stageId = stageId;
	}


	/**
	 * @param depth
	 *            the depth to set
	 */
	public void setDepth(int depth) {

		this.depth = depth;
	}


	/**
	 * @return the associatedRoi
	 */
	public List<String> getAssociatedRoi() {

		return associatedRoi;
	}


	/**
	 * @param associatedRoi
	 *            the associatedRoi to set
	 */
	public void setAssociatedRoi(List<String> associatedRoi) {

		this.associatedRoi = associatedRoi;
	}
	
	public void addAssociatedRoi(String associatedRoiId) {

		if (associatedRoi == null){
			associatedRoi = new ArrayList<>();
		}
		this.associatedRoi.add(associatedRoiId);
	}


	/**
	 * @return the associatedChannel
	 */
	public List<String> getAssociatedChannel() {

		return associatedChannel;
	}


	/**
	 * @param associatedChannel
	 *            the associatedChannel to set
	 */
	public void setAssociatedChannel(List<String> associatedChannel) {

		this.associatedChannel = associatedChannel;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {

		return height;
	}


	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Integer height) {

		this.height = height;
	}


	/**
	 * @return the hostUrl
	 */
	public String getHostUrl() {

		return hostUrl;
	}


	/**
	 * @param hostUrl
	 *            the hostUrl to set
	 */
	public void setHostUrl(String hostUrl) {

		this.hostUrl = hostUrl;
	}


	/**
	 * @return the hostName
	 */
	public String getHostName() {

		return hostName;
	}


	/**
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName) {

		this.hostName = hostName;
	}


	/**
	 * @return the imageGeneratedBy
	 */
	public ArrayList<String> getImageGeneratedBy() {

		return imageGeneratedBy;
	}


	/**
	 * @param imageGeneratedBy
	 *            the imageGeneratedBy to set
	 */
	public void setImageGeneratedBy( ArrayList<String> imageGeneratedBy) {

		this.imageGeneratedBy = imageGeneratedBy;
	}
	
	public void addImageGeneratedBy(String imageGeneratedBy) {

		if(imageGeneratedBy != null){
			if (this.imageGeneratedBy == null){
				this.imageGeneratedBy = new ArrayList<>();		
			}
			this.imageGeneratedBy.add(imageGeneratedBy);
		}
	}
	
	


	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {

		return imageUrl;
	}


	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {

		this.imageUrl = imageUrl;
	}


	/**
	 * @return the imageContextUrl
	 */
	public String getImageContextUrl() {

		return imageContextUrl;
	}


	/**
	 * @param imageContextUrl
	 *            the imageContextUrl to set
	 */
	public void setImageContextUrl(String imageContextUrl) {

		this.imageContextUrl = imageContextUrl;
	}


	/**
	 * @return the imagingMethodId
	 */
	public ArrayList<String> getImagingMethodId() {

		return imagingMethodId;
	}


	/**
	 * @param imagingMethodId
	 *            the imagingMethodId to set
	 */
	public void setImagingMethodId(ArrayList<String> imagingMethodId) {

		this.imagingMethodId = imagingMethodId;
	}

	public void addImagingMethodId(String imagingMethodId) {

		if (this.imagingMethodId == null){
			this.imagingMethodId = new ArrayList<>();
		}
		this.imagingMethodId.add(imagingMethodId);
	}

	/**
	 * @return the samplePreparationId
	 */
	public ArrayList<String> getSamplePreparationId() {

		return samplePreparationId;
	}


	/**
	 * @param samplePreparationId
	 *            the samplePreparationId to set
	 */
	public void setSamplePreparationId(ArrayList<String> samplePreparationId) {

		this.samplePreparationId = samplePreparationId;
	}
	
	public void addSamplePreparationId(String samplePreparationId) {

		if (this.samplePreparationId == null){
			this.samplePreparationId = new ArrayList<>();
		}
		this.samplePreparationId.add(samplePreparationId);
	}


	/**
	 * @return the visualisationMethodId
	 */
	public ArrayList<String> getVisualisationMethodId() {

		return visualisationMethodId;
	}


	/**
	 * @param visualisationMethodId
	 *            the visualisationMethodId to set
	 */
	public void addVisualisationMethodId(String visualisationMethodId) {
		if (this.visualisationMethodId == null){
			this.visualisationMethodId = new ArrayList<>();
		}
		this.visualisationMethodId.add(visualisationMethodId);
	}


	/**
	 * @return the imagingMethodLabel
	 */
	public ArrayList<String> getImagingMethodLabel() {

		return imagingMethodLabel;
	}

	public void addImagingMethodLabel(String imagingMethodLabel) {

		if (this.imagingMethodLabel == null){
			this.imagingMethodLabel = new ArrayList<>();
		}
		this.imagingMethodLabel.add(imagingMethodLabel);
	}
	/**
	 * @param imagingMethodLabel
	 *            the imagingMethodLabel to set
	 */
	public void setImagingMethodLabel(ArrayList<String> imagingMethodLabel) {

		this.imagingMethodLabel = imagingMethodLabel;
	}


	/**
	 * @return the samplePreparationLabel
	 */
	public ArrayList<String> getSamplePreparationLabel() {

		return samplePreparationLabel;
	}

	public void addSamplePreparationLabel(String samplePreparationLabel) {

		if (this.samplePreparationLabel == null){
			this.samplePreparationLabel = new ArrayList<>();
		}
		this.samplePreparationLabel.add(samplePreparationLabel);
	}
	/**
	 * @param samplePreparationLabel
	 *            the samplePreparationLabel to set
	 */
	public void setSamplePreparationLabel(ArrayList<String> samplePreparationLabel) {

		this.samplePreparationLabel = samplePreparationLabel;
	}


	/**
	 * @return the visualisationMethodLabel
	 */
	public ArrayList<String> getVisualisationMethodLabel() {

		return visualisationMethodLabel;
	}


	/**
	 * @param visualisationMethodLabel
	 *            the visualisationMethodLabel to set
	 */
	public void addVisualisationMethodLabel(String visualisationMethodLabel) {
		if (this.visualisationMethodLabel == null){
			this.visualisationMethodLabel = new ArrayList<>();
		}
		this.visualisationMethodLabel.add(visualisationMethodLabel);
	}


	/**
	 * @return the machine
	 */
	public String getMachine() {

		return machine;
	}


	/**
	 * @param machine
	 *            the machine to set
	 */
	public void setMachine(String machine) {

		this.machine = machine;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {

		return width;
	}


	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Integer width) {

		this.width = width;
	}

	public String getAge() {

		return age;
	}

	public void setAge(String age) {

		this.age = age;
	}

	public String getSampleGeneratedBy() {

		return sampleGeneratedBy;
	}

	public void setSampleGeneratedBy(String sampleGeneratedBy) {

		this.sampleGeneratedBy = sampleGeneratedBy;
	}

	public String getTaxon() {

		return taxon;
	}

	public void setTaxon(String taxon) {

		this.taxon = taxon;
	}

	public String getNcbiTaxonId() {

		return ncbiTaxonId;
	}

	public void setNcbiTaxonId(String ncbiTaxonId) {

		this.ncbiTaxonId = ncbiTaxonId;
	}


	/**
	 * @return the sex
	 */
	public String getSex() {

		return sex;
	}


	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {

		this.sex = sex;
	}


	/**
	 * @return the stage
	 */
	public String getStage() {

		return stage;
	}


	/**
	 * @param stage
	 *            the stage to set
	 */
	public void setStage(String stage) {

		this.stage = stage;
	}

	/**
	 * @return the anatomyId
	 */
	public String getAnatomyId() {

		return anatomyId;
	}


	/**
	 * @param anatomyId
	 *            the anatomyId to set
	 */
	public void setAnatomyId(String anatomyId) {

		this.anatomyId = anatomyId;
	}


	/**
	 * @return the anatomyTerm
	 */
	public String getAnatomyTerm() {

		return anatomyTerm;
	}


	/**
	 * @param anatomyTerm
	 *            the anatomyTerm to set
	 */
	public void setAnatomyTerm(String anatomyTerm) {

		this.anatomyTerm = anatomyTerm;
	}

	
	

	/**
	 * @return the anatomyFreetext
	 */
	public String getAnatomyFreetext() {

		return anatomyFreetext;
	}


	/**
	 * @param anatomyFreetext
	 *            the anatomyFreetext to set
	 */
	public void setAnatomyFreetext(String anatomyFreetext) {

		this.anatomyFreetext = anatomyFreetext;
	}


	/**
	 * @return the observations
	 */
	public List<String> getObservations() {

		return observations;
	}


	/**
	 * @param observations
	 *            the observations to set
	 */
	public void setObservations(List<String> observations) {

		this.observations = observations;
	}


	/**
	 * @return the conditions
	 */
	public List<String> getConditions() {

		return conditions;
	}


	/**
	 * @param conditions
	 *            the conditions to set
	 */
	public void setConditions(List<String> conditions) {

		this.conditions = conditions;
	}


	/**
	 * @return the geneIds
	 */
	public List<String> getGeneIds() {

		return geneIds;
	}


	/**
	 * @param geneIds
	 *            the geneIds to set
	 */
	public void setGeneIds(List<String> geneIds) {

		this.geneIds = geneIds;
	}


	/**
	 * @return the geneSymbols
	 */
	public List<String> getGeneSymbols() {

		return geneSymbols;
	}


	/**
	 * @param geneSymbols
	 *            the geneSymbols to set
	 */
	public void setGeneSymbols(List<String> geneSymbols) {

		this.geneSymbols = geneSymbols;
	}


	/**
	 * @return the geneticFeatureIds
	 */
	public List<String> getGeneticFeatureIds() {

		return geneticFeatureIds;
	}


	/**
	 * @param geneticFeatureIds
	 *            the geneticFeatureIds to set
	 */
	public void setGeneticFeatureIds(List<String> geneticFeatureIds) {

		this.geneticFeatureIds = geneticFeatureIds;
	}


	/**
	 * @return the geneticFeatureSymbols
	 */
	public List<String> getGeneticFeatureSymbols() {

		return geneticFeatureSymbols;
	}


	/**
	 * @param geneticFeatureSymbols
	 *            the geneticFeatureSymbols to set
	 */
	public void setGeneticFeatureSymbols(List<String> geneticFeatureSymbols) {

		this.geneticFeatureSymbols = geneticFeatureSymbols;
	}


	/**
	 * @return the genetifFeatureEnsemlIds
	 */
	public List<String> getGenetifFeatureEnsemlIds() {

		return genetifFeatureEnsemlIds;
	}


	/**
	 * @param genetifFeatureEnsemlIds
	 *            the genetifFeatureEnsemlIds to set
	 */
	public void setGenetifFeatureEnsemlIds(List<String> genetifFeatureEnsemlIds) {

		this.genetifFeatureEnsemlIds = genetifFeatureEnsemlIds;
	}


	/**
	 * @return the chromosome
	 */
	public List<String> getChromosome() {

		return chromosome;
	}


	/**
	 * @param chromosome
	 *            the chromosome to set
	 */
	public void setChromosome(List<String> chromosome) {

		this.chromosome = chromosome;
	}
	public void addChromosome(String chromosome) {

		if (this.chromosome == null){
			this.chromosome = new ArrayList<>();
		}
		this.chromosome.add(chromosome);
	}


	/**
	 * @return the startPosition
	 */
	public List<Long> getStartPosition() {

		return startPosition;
	}


	/**
	 * @param startPosition
	 *            the startPosition to set
	 */
	public void setStartPosition(List<Long> startPosition) {

		this.startPosition = startPosition;
	}
	
	public void addStartPosition(Long startPosition) {
		
		if (this.startPosition == null){
			this.startPosition = new ArrayList<>();
		}
		this.startPosition.add(startPosition);
	}


	/**
	 * @return the endPosition
	 */
	public List<Long> getEndPosition() {

		return endPosition;
	}


	/**
	 * @param endPosition
	 *            the endPosition to set
	 */
	public void setEndPosition(List<Long> endPosition) {

		this.endPosition = endPosition;
	}
	
	public void addEndPosition(Long endPosition) {
		
		if (this.endPosition == null){
			this.endPosition = new ArrayList<>();
		}
		this.endPosition.add(endPosition);
	}


	/**
	 * @return the strand
	 */
	public List<String> getStrand() {

		return strand;
	}


	/**
	 * @param strand
	 *            the strand to set
	 */
	public void setStrand(List<String> strand) {

		this.strand = strand;
	}
	
	public void addStrand(String strand) {
		
		if (this.strand == null){
			this.strand = new ArrayList<>();
		}
		this.strand.add(strand);
	}

	/**
	 * @return the zygosity
	 */
	public List<String> getZygosity() {

		return zygosity;
	}


	/**
	 * @param zygosity
	 *            the zygosity to set
	 */
	public void setZygosity(List<String> zygosity) {

		this.zygosity = zygosity;
	}
	
	public void addZygosity(String zygosity) {
		
		if (this.zygosity == null){
			this.zygosity = new ArrayList<>();
		}
		this.zygosity.add(zygosity);
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "ImageDTO [id=" + id + ", associatedRoi=" + associatedRoi + ", associatedChannel=" + associatedChannel 
			+ ", height=" + height + ", hostUrl=" + hostUrl + ", hostName=" + hostName + ", imageGeneratedBy=" + imageGeneratedBy 
			+ ", imageType=" + imageType + ", sampleType=" + sampleType + ", imageUrl=" + imageUrl + ", imageContextUrl=" + imageContextUrl 
			+ ", imagingMethodId=" + imagingMethodId + ", imagingMethodLabel=" + imagingMethodLabel + ", imagingMethodSynonyms=" + imagingMethodSynonyms 
			+ ", samplePreparationId=" + samplePreparationId + ", samplePreparationLabel=" + samplePreparationLabel 
			+ ", samplePreparationSynonyms=" + samplePreparationSynonyms + ", visualisationMethodId=" + visualisationMethodId 
			+ ", visualisationMethodLabel=" + visualisationMethodLabel + ", visualisationMethodSynonyms=" + visualisationMethodSynonyms 
			+ ", machine=" + machine + ", width=" + width + ", genericSearch=" + genericSearch 
			+ ", ageSinceBirth=" + age + ", sampleGeneratedBy=" + sampleGeneratedBy + ", taxon=" + taxon + ", ncbiTaxonId=" + ncbiTaxonId 
			+ ", sex=" + sex + ", stage=" + stage + ", stageId=" + stageId + ", anatomyId=" + anatomyId + ", anatomyTerm=" + anatomyTerm 
			+ ", anatomyFreetext=" + anatomyFreetext + ", anatomySynonyms=" + anatomySynonyms + ", observations=" + observations 
			+ ", conditions=" + conditions + ", geneIds=" + geneIds + ", geneSymbols=" + geneSymbols + ", geneSynonyms=" + geneSynonyms 
			+ ", geneticFeatureIds=" + geneticFeatureIds + ", geneticFeatureSymbols=" + geneticFeatureSymbols + ", geneticFeatureSynonyms=" + geneticFeatureSynonyms 
			+ ", genetifFeatureEnsemlIds=" + genetifFeatureEnsemlIds + ", chromosome=" + chromosome + ", startPosition=" + startPosition 
			+ ", endPosition=" + endPosition + ", strand=" + strand + ", zygosity=" + zygosity + ", depth=" + depth + ", anatomyComputedIdBag=" + anatomyComputedIdBag 
			+ ", anatomyComputedLabelBag=" + anatomyComputedLabelBag + ", anatomyComputedSynonymsBag=" + anatomyComputedSynonymsBag 
			+ ", anatomyComputedAncestorsIdBag=" + anatomyComputedAncestors + ", depictedAnatomyIdBag=" + depictedAnatomyIdBag 
			+ ", depictedAnatomyTermBag=" + depictedAnatomyTermBag + ", depictedAnatomyFreetextBag=" + depictedAnatomyFreetextBag 
			+ ", depictedAnatomySynonymsBag=" + depictedAnatomySynonymsBag + ", depictedAnatomyAncestorsIdBag=" + depictedAnatomyAncestors 
			+ ", abnormalAnatomyIdBag=" + abnormalAnatomyIdBag + ", abnormalAnatomyTermBag=" + abnormalAnatomyTermBag 
			+ ", abnormalAnatomyFreetextBag=" + abnormalAnatomyFreetextBag + ", abnormalAnatomySynonymsBag=" + abnormalAnatomySynonymsBag 
			+ ", abnormalAnatomyAncestorsIdBag=" + abnormalAnatomyAncestors + ", expressedGfIdBag=" + expressedGfIdBag + ", expressedGfSymbolBag=" + expressedGfSymbolBag 
			+ ", expressedGfSynonymsBag=" + expressedGfSynonymsBag + ", expressionInIdBag=" + expressionInIdBag + ", expressionInLabelBag=" + expressionInLabelBag 
			+ ", expressionInFreetextBag=" + expressionInFreetextBag + ", expressionInSynonymsBag=" + expressionInSynonymsBag 
			+ ", expressionInAncestorsIdBag=" + expressionInAncestors + ", mutantGeneIdBag=" + mutantGeneIdBag + ", mutantGeneSymbolBag=" + mutantGeneSymbolBag 
			+ ", mutantGeneSynonymsBag=" + mutantGeneSynonymsBag + ", observationBag=" + observationBag + ", phenotypeIdBag=" + phenotypeIdBag 
			+ ", phenotypeLabelBag=" + phenotypeLabelBag + ", phenotypeFreetextBag=" + phenotypeFreetextBag + ", phenotypeSynonymsBag=" + phenotypeSynonymsBag 
			+ ", phenotypeAncestorsIdBag=" + phenotypeAncestors + "]";
	}

	/**
	 * @return the id
	 */
	public String getId() {

		return id;
	}


	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {

		this.id = id;
	}


	/**
	 * @return the anatomyComputedIdBag
	 */
	public ArrayList<String> getAnatomyComputedIdBag() {

		return anatomyComputedIdBag;
	}


	/**
	 * @param anatomyComputedIdBag
	 *            the anatomyComputedIdBag to set
	 */
	public void setAnatomyComputedIdBag(ArrayList<String> anatomyComputedIdBag) {

		this.anatomyComputedIdBag = anatomyComputedIdBag;
	}


	/**
	 * @return the anatomyComputedLabelBag
	 */
	public ArrayList<String> getAnatomyComputedLabelBag() {

		return anatomyComputedLabelBag;
	}


	/**
	 * @param anatomyComputedLabelBag
	 *            the anatomyComputedLabelBag to set
	 */
	public void setAnatomyComputedLabelBag(ArrayList<String> anatomyComputedLabelBag) {

		this.anatomyComputedLabelBag = anatomyComputedLabelBag;
	}


	/**
	 * @return the depictedAnatomyIdBag
	 */
	public ArrayList<String> getDepictedAnatomyIdBag() {

		return depictedAnatomyIdBag;
	}


	/**
	 * @param depictedAnatomyIdBag
	 *            the depictedAnatomyIdBag to set
	 */
	public void setDepictedAnatomyIdBag(ArrayList<String> depictedAnatomyIdBag) {

		this.depictedAnatomyIdBag = depictedAnatomyIdBag;
	}


	/**
	 * @return the depictedAnatomyTermBag
	 */
	public ArrayList<String> getDepictedAnatomyTermBag() {

		return depictedAnatomyTermBag;
	}


	/**
	 * @param depictedAnatomyTermBag
	 *            the depictedAnatomyTermBag to set
	 */
	public void setDepictedAnatomyTermBag(ArrayList<String> depictedAnatomyTermBag) {

		this.depictedAnatomyTermBag = depictedAnatomyTermBag;
	}


	/**
	 * @return the expressedGfIdBag
	 */
	public ArrayList<String> getExpressedGfIdBag() {

		return expressedGfIdBag;
	}


	/**
	 * @param expressedGfIdBag
	 *            the expressedGfIdBag to set
	 */
	public void setExpressedGfIdBag(ArrayList<String> expressedGfIdBag) {

		this.expressedGfIdBag = expressedGfIdBag;
	}


	/**
	 * @return the expressedGfSymbolBag
	 */
	public ArrayList<String> getExpressedGfSymbolBag() {

		return expressedGfSymbolBag;
	}


	/**
	 * @param expressedGfSymbolBag
	 *            the expressedGfSymbolBag to set
	 */
	public void setExpressedGfSymbolBag(ArrayList<String> expressedGfSymbolBag) {

		this.expressedGfSymbolBag = expressedGfSymbolBag;
	}


	/**
	 * @return the expressionInIdBag
	 */
	public ArrayList<String> getExpressionInIdBag() {

		return expressionInIdBag;
	}


	/**
	 * @param expressionInIdBag
	 *            the expressionInIdBag to set
	 */
	public void setExpressionInIdBag(ArrayList<String> expressionInIdBag) {

		this.expressionInIdBag = expressionInIdBag;
	}


	/**
	 * @return the expressionInLabelBag
	 */
	public ArrayList<String> getExpressionInLabelBag() {

		return expressionInLabelBag;
	}


	/**
	 * @param expressionInLabelBag
	 *            the expressionInLabelBag to set
	 */
	public void setExpressionInLabelBag(ArrayList<String> expressionInLabelBag) {

		this.expressionInLabelBag = expressionInLabelBag;
	}


	/**
	 * @return the mutantGeneIdBag
	 */
	public ArrayList<String> getMutantGeneIdBag() {

		return mutantGeneIdBag;
	}


	/**
	 * @param mutantGeneIdBag
	 *            the mutantGeneIdBag to set
	 */
	public void setMutantGeneIdBag(ArrayList<String> mutantGeneIdBag) {

		this.mutantGeneIdBag = mutantGeneIdBag;
	}


	/**
	 * @return the mutantGeneSymbolBag
	 */
	public ArrayList<String> getMutantGeneSymbolBag() {

		return mutantGeneSymbolBag;
	}


	/**
	 * @param mutantGeneSymbolBag
	 *            the mutantGeneSymbolBag to set
	 */
	public void setMutantGeneSymbolBag(ArrayList<String> mutantGeneSymbolBag) {

		this.mutantGeneSymbolBag = mutantGeneSymbolBag;
	}


	/**
	 * @return the observationBag
	 */
	public ArrayList<String> getObservationBag() {

		return observationBag;
	}


	/**
	 * @param observationBag
	 *            the observationBag to set
	 */
	public void setObservationBag(ArrayList<String> observationBag) {

		this.observationBag = observationBag;
	}


	/**
	 * @return the phenotypeIdBag
	 */
	public ArrayList<String> getPhenotypeIdBag() {

		return phenotypeIdBag;
	}


	/**
	 * @param phenotypeIdBag
	 *            the phenotypeIdBag to set
	 */
	public void setPhenotypeIdBag(ArrayList<String> phenotypeIdBag) {

		this.phenotypeIdBag = phenotypeIdBag;
	}

	
	public void addPhenotypeIdBag(List<String> phenotypeIdBag) {
		if (this.phenotypeIdBag == null){
			this.phenotypeIdBag = new ArrayList<>();
		}
		this.phenotypeIdBag.addAll(phenotypeIdBag);
	}
	public void addPhenotypeTermBag(List<String> phenotypeLabelBag) {
		if (this.phenotypeLabelBag == null){
			this.phenotypeLabelBag = new ArrayList<>();
		}
		this.phenotypeLabelBag.addAll(phenotypeLabelBag);
	}
	public void addPhenotypeFreetextBag(List<String> phenotypeFreetextBag) {
		if (this.phenotypeFreetextBag == null){
			this.phenotypeFreetextBag = new ArrayList<>();
		}
		this.phenotypeFreetextBag.addAll(phenotypeFreetextBag);	
	}
	
	
	public void addDepictedAnatomyIdBag(List<String> depictedAnatomyIdBag){
		if (this.depictedAnatomyIdBag == null){
			this.depictedAnatomyIdBag = new ArrayList<>();
		}
		this.depictedAnatomyIdBag.addAll(depictedAnatomyIdBag);	
	}	
	public void addDepictedAnatomyTermBag(List<String> depictedAnatomyTermBag){
		if (this.depictedAnatomyTermBag == null){
			this.depictedAnatomyTermBag = new ArrayList<>();
		}
		this.depictedAnatomyTermBag.addAll(depictedAnatomyTermBag);	
	}	
	public void addDepictedAnatomyFreetextBag(List<String> depictedAnatomyFreetextBag){
		if (this.depictedAnatomyFreetextBag == null){
			this.depictedAnatomyFreetextBag = new ArrayList<>();
		}
		this.depictedAnatomyFreetextBag.addAll(depictedAnatomyFreetextBag);	
	}

	
	public void addAbnormalAnatomyFreetextBag(List<String> abnormalAnatomyFreetextBag){
		if (this.abnormalAnatomyFreetextBag == null){
			this.abnormalAnatomyFreetextBag = new ArrayList<>();
		}
		this.abnormalAnatomyFreetextBag.addAll(abnormalAnatomyFreetextBag);	
	}
	public void addAbnormalAnatomyTermBag(List<String> abnormalAnatomyTermBag){
		if (this.abnormalAnatomyTermBag == null){
			this.abnormalAnatomyTermBag = new ArrayList<>();
		}
		this.abnormalAnatomyTermBag.addAll(abnormalAnatomyTermBag);	
	}
	public void addAbnormalAnatomyIdBag(List<String> abnormalAnatomyFreetextBag){
		if (this.abnormalAnatomyFreetextBag == null){
			this.abnormalAnatomyFreetextBag = new ArrayList<>();
		}
		this.abnormalAnatomyFreetextBag.addAll(abnormalAnatomyFreetextBag);	
	}
	
	
	public void addObservationBag(List<String> observationBag) {
		if (this.observationBag == null){
			this.observationBag = new ArrayList<>();
		}
		this.observationBag.addAll(observationBag);
	}

	
	public void addExpressionInIdBag(List<String> expressionInIdBag) {
		if (this.expressionInIdBag == null){
			this.expressionInIdBag = new ArrayList<>();
		}
		this.expressionInIdBag.addAll(expressionInIdBag);
	}
	public void addExpressionInTermBag(List<String> expressionInTermBag) {
		if (this.expressionInLabelBag == null){
			this.expressionInLabelBag = new ArrayList<>();
		}
		this.expressionInLabelBag.addAll(expressionInTermBag);
	}
	public void addExpressionInFreetextBag(List<String> ExpressionInFreetextBag) {
		if (this.expressionInFreetextBag == null){
			this.expressionInFreetextBag = new ArrayList<>();
		}
		this.expressionInFreetextBag.addAll(ExpressionInFreetextBag);
	}
	
	
	public void addAnatomyAncestors(List<String> anatomyAncestors) {
		
		if (anatomyAncestors != null){
			if (this.anatomyAncestors == null){
				this.anatomyAncestors = new ArrayList<>();
			}
			this.anatomyAncestors.addAll(anatomyAncestors);
		}
	}

	
	/**
	 * @return the phenotypelabelBag
	 */
	public ArrayList<String> getPhenotypeLabelBag() {

		return phenotypeLabelBag;
	}


	/**
	 * @param phenotypelabelBag
	 *            the phenotypelabelBag to set
	 */
	public void setPhenotypeLabelBag(ArrayList<String> phenotypelabelBag) {

		this.phenotypeLabelBag = phenotypelabelBag;
	}


	/**
	 * @return the depictedAnatomyFreetextBag
	 */
	public ArrayList<String> getDepictedAnatomyFreetextBag() {

		return depictedAnatomyFreetextBag;
	}


	/**
	 * @param depictedAnatomyFreetextBag
	 *            the depictedAnatomyFreetextBag to set
	 */
	public void setDepictedAnatomyFreetextBag(ArrayList<String> depictedAnatomyFreetextBag) {

		this.depictedAnatomyFreetextBag = depictedAnatomyFreetextBag;
	}


	/**
	 * @return the expressionInFreetextBag
	 */
	public ArrayList<String> getExpressionInFreetextBag() {

		return expressionInFreetextBag;
	}


	/**
	 * @param expressionInFreetextBag
	 *            the expressionInFreetextBag to set
	 */
	public void setExpressionInFreetextBag(ArrayList<String> expressionInFreetextBag) {

		this.expressionInFreetextBag = expressionInFreetextBag;
	}


	/**
	 * @return the phenotypeFreetextBag
	 */
	public ArrayList<String> getPhenotypeFreetextBag() {

		return phenotypeFreetextBag;
	}


	/**
	 * @param phenotypeFreetextBag
	 *            the phenotypeFreetextBag to set
	 */
	public void setPhenotypeFreetextBag(ArrayList<String> phenotypeFreetextBag) {

		this.phenotypeFreetextBag = phenotypeFreetextBag;
	}


	/**
	 * @return the abnormalAnatomyIdBag
	 */
	public ArrayList<String> getAbnormalAnatomyIdBag() {

		return abnormalAnatomyIdBag;
	}


	/**
	 * @param abnormalAnatomyIdBag
	 *            the abnormalAnatomyIdBag to set
	 */
	public void setAbnormalAnatomyIdBag(ArrayList<String> abnormalAnatomyIdBag) {

		this.abnormalAnatomyIdBag = abnormalAnatomyIdBag;
	}
	
	public void addAbnormalAnatomyIdBag(String abnormalAnatomyId) {
		if (this.abnormalAnatomyIdBag == null){
			this.abnormalAnatomyIdBag = new ArrayList<>();
		}
		
		this.abnormalAnatomyIdBag.add(abnormalAnatomyId);
	}

	/**
	 * @return the abnormalAnatomyTermBag
	 */
	public ArrayList<String> getAbnormalAnatomyTermBag() {

		return abnormalAnatomyTermBag;
	}


	/**
	 * @param abnormalAnatomyTermBag
	 *            the abnormalAnatomyTermBag to set
	 */
	public void setAbnormalAnatomyTermBag(ArrayList<String> abnormalAnatomyTermBag) {

		this.abnormalAnatomyTermBag = abnormalAnatomyTermBag;
	}


	/**
	 * @return the abnormalAnatomyFreetextBag
	 */
	public ArrayList<String> getAbnormalAnatomyFreetextBag() {

		return abnormalAnatomyFreetextBag;
	}

	public void addAbnormalAnatomyFreetextBag(String abnormalAnatomyFreetext) {
		if (this.abnormalAnatomyFreetextBag == null){
			this.abnormalAnatomyFreetextBag = new ArrayList<>();
		}
		
		this.abnormalAnatomyFreetextBag.add(abnormalAnatomyFreetext);
	}

	/**
	 * @param abnormalAnatomyFreetextBag
	 *            the abnormalAnatomyFreetextBag to set
	 */
	public void setAbnormalAnatomyFreetextBag(ArrayList<String> abnormalAnatomyFreetextBag) {

		this.abnormalAnatomyFreetextBag = abnormalAnatomyFreetextBag;
	}


	/**
	 * @return the imageType
	 */
	public ArrayList<String> getImageType() {

		return imageType;
	}


	/**
	 * @param imageType
	 *            the imageType to set
	 */
	public void setImageType(ArrayList<String> imageType) {

		this.imageType = imageType;
	}


	/**
	 * @return the sampleType
	 */
	public String getSampleType() {

		return sampleType;
	}


	/**
	 * @param sampleType
	 *            the sampleType to set
	 */
	public void setSampleType(String sampleType) {

		this.sampleType = sampleType;
	}

	
	public List<String> getPublication() {
	
		return publication;
	}

	
	public void setPublication(List<String> publication) {
	
		this.publication = publication;
	}

	
	public String getMagnificationLevel() {
	
		return magnificationLevel;
	}

	
	public void setMagnificationLevel(String magnificationLevel) {
	
		this.magnificationLevel = magnificationLevel;
	}

	
	public List<String> getGenomeAssembly() {
	
		return genomeAssembly;
	}

	
	public void setGenomeAssembly(List<String> genomeAssembly) {
	
		this.genomeAssembly = genomeAssembly;
	}

	
	
}

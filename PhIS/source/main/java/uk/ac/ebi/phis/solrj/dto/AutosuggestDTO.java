package uk.ac.ebi.phis.solrj.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;


public class AutosuggestDTO {
	

	public final static String ID = "id" ;
	public final static String IMAGE_DOC_ID = "image_doc_id" ;
	public final static String HOST_NAME = "host_name";
	public final static String IMAGE_GENERATED_BY = "image_generated_by";
	public final static String IMAGING_METHOD_LABEL = "imaging_method_label";
	public final static String IMAGINF_METHOD_SYNONYM = "imaging_method_synonym" ;
	public final static String SAMPLE_PREPARATION_LABEL = "sample_preparation_label";
	public final static String SAMPLE_PREPARATION_ID = "sample_preparation_id";
	public final static String SAMPLE_PREPARATION_SYNONYMS = "sample_preparation_synonyms" ;
	public final static String VISUALIZATION_METHOD_ID = "visualisation_method_id" ;
	public final static String VISUALIZATION_METHOD_LABEL = "visualisation_method_label" ;
	public final static String VISUALIZATION_METHOD_SYNONYMS = "visualisation_method_synonyms" ;
	public final static String IMAGE_TYPE = "image_type";
	public final static String SAMPLE_TYPE = "sample_type";
	public final static String AGE_SINCE_BIRTH = "age_since_birth";	
	public final static String SAMPLE_GENERATED_BY = "sample_generated_by" ;
	public final static String TAXON = "taxon" ;
	public final static String ANATOMY_ID = "anatomy_id";
	public final static String ANATOMY_TERM = "anatomy_term";
	public final static String ANATOMY_FREETEXT = "anatomy_freetext" ;
	public final static String ANATOMY_SYNONYMS = "anatomy_synonyms";
	public final static String CONDITIONS = "conditions" ;
	public final static String OBSERVATIONS = "observations" ;
	public final static String GENE_ID = "gene_id" ;
	public final static String GENE_SYMBOL = "gene_symbol" ;
	public final static String GENE_SYNONYMS = "gene_synonyms" ;
	public final static String GENETIC_FEATURE_ID = "genetic_feature_id" ;
	public final static String GENETIC_FEATURE_SYMBOL = "genetic_feature_symbol";
	public final static String GENETIC_FEATURE_SYNONYMS = "genetic_feature_synonyms" ;
	public final static String GENETIC_FEATURE_ENSEMBL_ID= "genetic_feature_ensembl_id";
	public final static String ZYGOSITY = "zygosity" ;
	public final static String ANATOMY_COMPUTED_ID = "anatomy_computed_id_bag" ;
	public final static String ANATOMY_COMPUTED_TERM_BAG = "anatomy_computed_term_bag" ;
	public final static String ABNORMAL_ANATOMY_SYNONYMS_BAG = "abnormal_anatomy_synonyms_bag" ;
	public final static String ABNORMAL_ANATOMY_ANCESTORS_SYNONYMS_BAG = "abnormal_anatomy_ancestors_synonyms_bag" ;
	public final static String ABNORMAL_ANATOMY_ANCESTORS_ID_BAG = "abnormal_anatomy_ancestors_id_bag";
	public final static String ABNORMAL_ANATOMY_ANCESTORS_TERM_BAG	 = "abnormal_anatomy_ancestors_term_bag" ;
	public final static String EXPRESSION_IN_ID_BAG= "expression_in_id_bag" ;
	public final static String EXPRESSION_IN_LABEL_BAG= "expression_in_label_bag" ;
	public final static String EXPRESSION_IN_FREETEXT_BAG= "expression_in_freetext_bag" ;
	public final static String EXPRESSION_IN_SYNONYMS_BAG= "expression_in_synonyms_bag";
	public final static String EXPRESSION_IN_ANCESTORS_SYNONYMS_BAG= "expression_in_ancestors_synonyms_bag";
	public final static String EXPRESSION_IN_ANCESTORS_ID_BAG= "expression_in_ancestors_id_bag";
	public final static String EXPRESSION_IN_ANCESTORS_TERM_BAG= "expression_in_ancestors_term_bag";
	public final static String OBSERVATION_BAG= "observation_bag" ;
	public final static String MUTANT_GENE_ID= "mutant_gene_id_bag";
	public final static String MUTANT_GENE_SYMBOL_BAG= "mutant_gene_symbol_bag";
	 public final static String MUTANT_GENE_SYNONYMS_BAG = "mutant_gene_synonyms_bag";
	 public final static String EXPRESSED_GF_ID_BAG = "expressed_gf_id_bag" ;
	 public final static String EXPRESSED_GF_SYMBOL_BAG = "expressed_gf_symbol_bag";
	 public final static String EXPRESSED_GF_SYNONYMS_BAG = "expressed_gf_synonyms_bag" ;
	 public final static String PHENOTYPE_ID_BAG = "phenotype_id_bag" ;
	 public final static String PHENOTYPE_FREETEXT_BAG = "phenotype_freetext_bag" ;
	 public final static String PHENOTYPE_LABEL_BAG = "phenotype_label_bag";
	 public final static String PHENOTYPE_SYNONYMS_BAG = "phenotype_synonyms_bag" ;
	 public final static String PHENOTYPE_ANCESTORS_SYNONYMS_BAG = "phenotype_ancestors_synonyms_bag";
	 public final static String PHENOTYPE_ANCESTORS_TERM_BAG = "phenotype_ancestors_term_bag";
	 public final static String PHENOTYPE_ANCESTORS_ID_BAG = "phenotype_ancestors_id_bag" ;
	 public final static String NCBI_TAXON_ID = "ncbi_taxon_id" ;
	 public final static String SEX = "sex" ;
	 public final static String STAGE_ID = "stage_id";
	 public final static String STAGE = "stage" ;
	 public final static String  EMBRYONIC_AGE = "embryonic_age" ;
	 

	 public final static String  TERM_AUTOSUGGEST = "term_autosuggest" ;
	 public final static String  TERM_AUTOSUGGEST_E = "term_autosuggest_e" ;
	 public final static String  TERM_AUTOSUGGEST_NA = "term_autosuggest_na" ;
	 public final static String  TERM_AUTOSUGGEST_WS = "term_autosuggest_ws" ;
		
		

		@Field(ID)
		private String id;

		@Field(HOST_NAME)
		private String hostName;

		@Field(IMAGE_GENERATED_BY)
		private String imageGeneratedBy;

		@Field(IMAGE_TYPE)
		private ArrayList<String> imageType;

		@Field(SAMPLE_TYPE)
		private String sampleType;

		@Field(IMAGING_METHOD_LABEL)
		private String imagingMethodLabel;

		@Field(SAMPLE_PREPARATION_ID)
		private String samplePreparationId;

		@Field(SAMPLE_PREPARATION_LABEL)
		private String samplePreparationLabel;

		@Field(SAMPLE_PREPARATION_SYNONYMS)
		private ArrayList<String> samplePreparationSynonyms;
		// Sample

		@Field(AGE_SINCE_BIRTH)
		private Float ageSinceBirth;

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

		@Field(EMBRYONIC_AGE)
		private Float embryonicAge;

		// annotations -->

		@Field(ANATOMY_ID)
		private String anatomyId;

		@Field(ANATOMY_TERM)
		private String anatomyTerm;

		@Field(ANATOMY_FREETEXT)
		private String anatomyFreetext;

		@Field(ANATOMY_SYNONYMS)
		private ArrayList<String> anatomySynonyms;

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

		@Field(ZYGOSITY)
		private List<String> zygosity;
		
		@Field(ANATOMY_COMPUTED_TERM_BAG)
		private ArrayList<String> anatomyComputedLabelBag;
		@Field(ABNORMAL_ANATOMY_SYNONYMS_BAG)
		private ArrayList<String> abnormalAnatomySynonymsBag;

		@Field(ABNORMAL_ANATOMY_ANCESTORS_ID_BAG)
		private ArrayList<String> abnormalAnatomyAncestorsIdBag;

		@Field(ABNORMAL_ANATOMY_ANCESTORS_SYNONYMS_BAG)
		private ArrayList<String> abnormalAnatomyAncestorsSynonymsBag;

		@Field(ABNORMAL_ANATOMY_ANCESTORS_TERM_BAG)
		private ArrayList<String> abnormalAnatomyAncestorsTermBag;

		@Field(EXPRESSED_GF_ID_BAG)
		private ArrayList<String> expressedGfIdBag;

		@Field(EXPRESSED_GF_SYMBOL_BAG)
		private ArrayList<String> expressedGfSymbolBag;

		@Field(EXPRESSED_GF_SYNONYMS_BAG)
		private ArrayList<String> expressedGfSynonymsBag;

		@Field(EXPRESSION_IN_ID_BAG)
		private ArrayList<String> expressionInIdBag;

		@Field(EXPRESSION_IN_LABEL_BAG)
		private ArrayList<String> ExpressionInLabelBag;

		@Field(EXPRESSION_IN_FREETEXT_BAG)
		private ArrayList<String> ExpressionInFreetextBag;

		@Field(EXPRESSION_IN_SYNONYMS_BAG)
		private ArrayList<String> expressionInSynonymsBag;

		@Field(EXPRESSION_IN_ANCESTORS_ID_BAG)
		private ArrayList<String> expressionInAncestorsIdBag;

		@Field(EXPRESSION_IN_ANCESTORS_SYNONYMS_BAG)
		private ArrayList<String> expressionInAncestorsSynonymsBag;

		@Field(EXPRESSION_IN_ANCESTORS_TERM_BAG)
		private ArrayList<String> expressionInAncestorsTermBag;

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

		@Field(PHENOTYPE_ANCESTORS_ID_BAG)
		private ArrayList<String> phenotypeAncestorsIdBag;

		@Field(PHENOTYPE_ANCESTORS_SYNONYMS_BAG)
		private ArrayList<String> phenotypeAncestorsSynonymsBag;

		@Field(PHENOTYPE_ANCESTORS_TERM_BAG)
		private ArrayList<String> phenotypeAncestorsTermBag;


		/**
		 * @return the abnormalAnatomyAncestorsIdBag
		 */
		public ArrayList<String> getAbnormalAnatomyAncestorsIdBag() {

			return abnormalAnatomyAncestorsIdBag;
		}


		/**
		 * @param abnormalAnatomyAncestorId
		 *            the abnormalAnatomyAncestorsIdBag to set
		 */
		public void addAbnormalAnatomyAncestorsIdBag(String abnormalAnatomyAncestorId) {

			if (this.abnormalAnatomyAncestorsIdBag == null){
				this.abnormalAnatomyAncestorsIdBag = new ArrayList<>();
			}
			if (abnormalAnatomyAncestorId != null && !this.abnormalAnatomyAncestorsIdBag.contains(abnormalAnatomyAncestorId)){
				this.abnormalAnatomyAncestorsIdBag.add(abnormalAnatomyAncestorId);
			}
		}


		/**
		 * @return the abnormalAnatomyAncestorsSynonymsBag
		 */
		public ArrayList<String> getAbnormalAnatomyAncestorsSynonymsBag() {

			return abnormalAnatomyAncestorsSynonymsBag;
		}


		/**
		 * @param abnormalAnatomyAncestorsSynonymsBag
		 *            the abnormalAnatomyAncestorsSynonymsBag to set
		 */
		public void addAbnormalAnatomyAncestorsSynonymsBag(ArrayList<String> abnormalAnatomyAncestorsSynonymsBag) {

			if (this.abnormalAnatomyAncestorsSynonymsBag == null){
				this.abnormalAnatomyAncestorsSynonymsBag = new ArrayList<>();
			}
			if (abnormalAnatomyAncestorsSynonymsBag != null && !this.abnormalAnatomyAncestorsSynonymsBag.contains(abnormalAnatomyAncestorsSynonymsBag)){
				this.abnormalAnatomyAncestorsSynonymsBag.addAll(abnormalAnatomyAncestorsSynonymsBag);
			}
		}


		/**
		 * @return the abnormalAnatomyAncestorsTermBag
		 */
		public ArrayList<String> getAbnormalAnatomyAncestorsTermBag() {

			return abnormalAnatomyAncestorsTermBag;
		}


		/**
		 * @param abnormalAnatomyAncestorTerm
		 *            the abnormalAnatomyAncestorsTermBag to set
		 */
		public void addAbnormalAnatomyAncestorsTermBag(String abnormalAnatomyAncestorTerm) {

			if (this.abnormalAnatomyAncestorsTermBag == null){
				this.abnormalAnatomyAncestorsTermBag = new ArrayList<>();
			}
			if (!this.abnormalAnatomyAncestorsTermBag.contains(abnormalAnatomyAncestorTerm)){
				this.abnormalAnatomyAncestorsTermBag.add(abnormalAnatomyAncestorTerm);
			}
		}


		/**
		 * @return the expressionInAncestorsIdBag
		 */
		public ArrayList<String> getExpressionInAncestorsIdBag() {

			return expressionInAncestorsIdBag;
		}


		/**
		 * @param expressionInAncestorId
		 *            the expressionInAncestorsIdBag to set
		 */
		public void addExpressionInAncestorsIdBag(String expressionInAncestorId) {

			if (this.expressionInAncestorsIdBag == null){
				this.expressionInAncestorsIdBag = new ArrayList<>();
			}
			if (!this.expressionInAncestorsIdBag.contains(expressionInAncestorId)){
				this.expressionInAncestorsIdBag.add(expressionInAncestorId);
			}
		}


		/**
		 * @return the expressionInAncestorsSynonymsBag
		 */
		public ArrayList<String> getExpressionInAncestorsSynonymsBag() {

			return expressionInAncestorsSynonymsBag;
		}


		/**
		 * @param expressionInAncestorsSynonymsBag
		 *            the expressionInAncestorsSynonymsBag to set
		 */
		public void addExpressionInAncestorsSynonymsBag(ArrayList<String> expressionInAncestorsSynonymsBag) {

			if (this.expressionInAncestorsSynonymsBag == null){
				this.expressionInAncestorsSynonymsBag = new ArrayList<>();
			}
			if (expressionInAncestorsSynonymsBag != null && !this.expressionInAncestorsSynonymsBag.contains(expressionInAncestorsSynonymsBag)){
				this.expressionInAncestorsSynonymsBag.addAll(expressionInAncestorsSynonymsBag);
			}
		}


		/**
		 * @return the expressionInAncestorsTermBag
		 */
		public ArrayList<String> getExpressionInAncestorsTermBag() {

			return expressionInAncestorsTermBag;
		}


		/**
		 * @param expressionInAncestorsTermBag
		 *            the expressionInAncestorsTermBag to set
		 */
		public void addExpressionInAncestorsTermBag(String expressionInAncestorsTermBag) {

			if (this.expressionInAncestorsTermBag == null){
				this.expressionInAncestorsTermBag = new ArrayList<>();
			}
			if (!this.expressionInAncestorsTermBag.contains(expressionInAncestorsTermBag)){
				this.expressionInAncestorsTermBag.add(expressionInAncestorsTermBag);
			}
		}


		/**
		 * @return the phenotypeAncestorsIdBag
		 */
		public ArrayList<String> getPhenotypeAncestorsIdBag() {

			return phenotypeAncestorsIdBag;
		}


		/**
		 * @param phenotypeAncestorId
		 *            the phenotypeAncestorsIdBag to set
		 */
		public void addPhenotypeAncestorsIdBag(String phenotypeAncestorId) {

			if (this.phenotypeAncestorsIdBag == null){
				this.phenotypeAncestorsIdBag = new ArrayList<>();
			}
			if (!this.phenotypeAncestorsIdBag.contains(phenotypeAncestorId)){
				this.phenotypeAncestorsIdBag.add(phenotypeAncestorId);
			}
		}


		/**
		 * @return the phenotypeAncestorsSynonymsBag
		 */
		public ArrayList<String> getPhenotypeAncestorsSynonymsBag() {

			return phenotypeAncestorsSynonymsBag;
		}


		/**
		 * @param phenotypeAncestorSynonyms
		 *            the phenotypeAncestorsSynonymsBag to set
		 */
		public void addPhenotypeAncestorsSynonymsBag(ArrayList<String> phenotypeAncestorSynonyms) {

			if (this.phenotypeAncestorsSynonymsBag == null){
				this.phenotypeAncestorsSynonymsBag = new ArrayList<>();
			}
			if (phenotypeAncestorSynonyms != null && !this.phenotypeAncestorsSynonymsBag.contains(phenotypeAncestorSynonyms)){
				this.phenotypeAncestorsSynonymsBag.addAll(phenotypeAncestorSynonyms);
			}
		}


		/**
		 * @return the phenotypeAncestorsTermBag
		 */
		public ArrayList<String> getPhenotypeAncestorsTermBag() {

			return phenotypeAncestorsTermBag;
		}


		/**
		 * @param phenotypeAncestorTerm
		 *            the phenotypeAncestorsTermBag to set
		 */
		public void addPhenotypeAncestorsTermBag(String phenotypeAncestorTerm) {

			if (this.phenotypeAncestorsTermBag == null){
				this.phenotypeAncestorsTermBag = new ArrayList<>();
			}
			if (phenotypeAncestorTerm != null && !this.phenotypeAncestorsTermBag.contains(phenotypeAncestorTerm)){
				this.phenotypeAncestorsTermBag.add(phenotypeAncestorTerm);
			}
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
		public String getImageGeneratedBy() {

			return imageGeneratedBy;
		}


		/**
		 * @param imageGeneratedBy
		 *            the imageGeneratedBy to set
		 */
		public void setImageGeneratedBy(String imageGeneratedBy) {

			this.imageGeneratedBy = imageGeneratedBy;
		}


		/**
		 * @return the samplePreparationId
		 */
		public String getSamplePreparationId() {

			return samplePreparationId;
		}


		/**
		 * @param samplePreparationId
		 *            the samplePreparationId to set
		 */
		public void setSamplePreparationId(String samplePreparationId) {

			this.samplePreparationId = samplePreparationId;
		}


		/**
		 * @return the imagingMethodLabel
		 */
		public String getImagingMethodLabel() {

			return imagingMethodLabel;
		}


		/**
		 * @param imagingMethodLabel
		 *            the imagingMethodLabel to set
		 */
		public void setImagingMethodLabel(String imagingMethodLabel) {

			this.imagingMethodLabel = imagingMethodLabel;
		}


		/**
		 * @return the samplePreparationLabel
		 */
		public String getSamplePreparationLabel() {

			return samplePreparationLabel;
		}


		/**
		 * @param samplePreparationLabel
		 *            the samplePreparationLabel to set
		 */
		public void setSamplePreparationLabel(String samplePreparationLabel) {

			this.samplePreparationLabel = samplePreparationLabel;
		}


		/**
		 * @return the ageSinceBirth
		 */
		public Float getAgeSinceBirth() {

			return ageSinceBirth;
		}


		/**
		 * @param ageSinceBirth
		 *            the ageSinceBirth to set
		 */
		public void setAgeSinceBirth(Float ageSinceBirth) {

			this.ageSinceBirth = ageSinceBirth;
		}


		/**
		 * @return the sampleGeneratedBy
		 */
		public String getSampleGeneratedBy() {

			return sampleGeneratedBy;
		}


		/**
		 * @param sampleGeneratedBy
		 *            the sampleGeneratedBy to set
		 */
		public void setSampleGeneratedBy(String sampleGeneratedBy) {

			this.sampleGeneratedBy = sampleGeneratedBy;
		}


		/**
		 * @return the taxon
		 */
		public String getTaxon() {

			return taxon;
		}


		/**
		 * @param taxon
		 *            the taxon to set
		 */
		public void setTaxon(String taxon) {

			this.taxon = taxon;
		}


		/**
		 * @return the ncbiTaxonId
		 */
		public String getNcbiTaxonId() {

			return ncbiTaxonId;
		}


		/**
		 * @param ncbiTaxonId
		 *            the ncbiTaxonId to set
		 */
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
		 * @return the embryonicAge
		 */
		public Float getEmbryonicAge() {

			return embryonicAge;
		}


		/**
		 * @param embryonicAge
		 *            the embryonicAge to set
		 */
		public void setEmbryonicAge(Float embryonicAge) {

			this.embryonicAge = embryonicAge;
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


		@Override
		public String toString() {

			return "ImagePojo []";
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

			return ExpressionInLabelBag;
		}


		/**
		 * @param expressionInLabelBag
		 *            the expressionInLabelBag to set
		 */
		public void setExpressionInLabelBag(ArrayList<String> expressionInLabelBag) {

			ExpressionInLabelBag = expressionInLabelBag;
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
		 * @return the expressionInFreetextBag
		 */
		public ArrayList<String> getExpressionInFreetextBag() {

			return ExpressionInFreetextBag;
		}


		/**
		 * @param expressionInFreetextBag
		 *            the expressionInFreetextBag to set
		 */
		public void setExpressionInFreetextBag(ArrayList<String> expressionInFreetextBag) {

			ExpressionInFreetextBag = expressionInFreetextBag;
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

	
	
}

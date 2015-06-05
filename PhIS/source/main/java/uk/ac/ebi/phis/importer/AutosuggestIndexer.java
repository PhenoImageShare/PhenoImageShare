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
package uk.ac.ebi.phis.importer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.solr.client.solrj.SolrServerException;

import uk.ac.ebi.phis.service.AutosuggestService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.solrj.dto.AutosuggestDTO;
import uk.ac.ebi.phis.solrj.dto.AutosuggestTypes;
import uk.ac.ebi.phis.solrj.dto.ImageDTO;
import uk.ac.ebi.phis.utils.ontology.OntologyObject;
import uk.ac.ebi.phis.utils.ontology.OntologyUtils;


public class AutosuggestIndexer {

	ClassLoader classloader;
	ImageService is;
	AutosuggestService as;
	OntologyUtils ou;
	
	public AutosuggestIndexer(ImageService is, AutosuggestService as, OntologyUtils ou) {
		classloader = Thread.currentThread().getContextClassLoader();
		this.is = is;
		this.as = as;
		this.ou = ou;
	}
	
	public void index() 
	throws Exception{

		int start = 0;
		int rows = 5000;
		long total = 0;
		int totalProcessed = 0;
		long time;
		long totalTime = 0;
		
		try {
			total = is.getDocuments(0, 0).getResults().getNumFound();
			
			while(start < total){
				
				List<ImageDTO> res = is.getDocuments(rows, start).getBeans(ImageDTO.class);
				System.out.println("start  " + start + " of " + total);
				start += rows;
				HashMap<String, AutosuggestDTO> newDocs = new HashMap();
				time = System.currentTimeMillis();
				
				for (ImageDTO imageDto : res){
					
					ArrayList<AutosuggestBean> terms = getBeans(imageDto); 
					
					for (AutosuggestBean term : terms){
						
						String id = term.getDocumentId();
						AutosuggestDTO termDto;
						termDto = getDtoForBean(term, newDocs);
						termDto = addAssociations(termDto, imageDto);
						newDocs.put(id, termDto);
					}	
					totalProcessed ++;
					
				}

				System.out.println("\t totalProcessed " + totalProcessed + "  beans added " + newDocs.size() + "  " + (System.currentTimeMillis() - time));
				totalTime += (System.currentTimeMillis() - time);
				as.addBeans(newDocs.values());
			}
			System.out.println("Indexing done in " + totalTime + " ms");
		} catch (SolrServerException e1) {
			e1.printStackTrace();
		}
		
	}
	
	protected AutosuggestDTO getDtoForBean (AutosuggestBean bean, HashMap<String, AutosuggestDTO> cacheMap) 
	throws SolrServerException{
		
		AutosuggestDTO res;
		String id = bean.getDocumentId();
		
		if (cacheMap.containsKey(id)){
			return  cacheMap.get(id); 
		}
		
		List<AutosuggestDTO> result = as.getDocumentsById(AutosuggestDTO.ID, id).getBeans(AutosuggestDTO.class);
		if (result != null && result.size() > 0){
			res = result.get(0);
		}else {
			res = fillAutosuggestDto(bean);
		}
		
		return res;
	}
	
	protected AutosuggestDTO fillAutosuggestDto(AutosuggestBean bean){
		
		AutosuggestDTO dto = new AutosuggestDTO();
		dto.setId(bean.getDocumentId());
		dto.setAutosuggestTermId(bean.getId());
		dto.setAutosuggestTermLabel(bean.getTerm());
		dto.setAutosuggestTermSynonyms(bean.getSynonyms());
		dto.setAutosuggestType(bean.getType());
		
		return dto;
	}
	
	protected AutosuggestDTO addAssociations(AutosuggestDTO termDto, ImageDTO imageDto){
		
		AutosuggestDTO res = termDto;
		res.addStage(imageDto.getStage());
		res.addStage(imageDto.getStageId());
		res.addTaxon(imageDto.getTaxon());
		res.addTaxon(imageDto.getNcbiTaxonId());
		res.addImagingMethod(imageDto.getImagingMethodLabel());
		res.addSampleType(imageDto.getSampleType());
		res.addImageType(imageDto.getImageType());
		res.addSamplePreparation(imageDto.getSamplePreparationId());
		res.addSamplePreparation(imageDto.getSamplePreparationSynonyms());
		res.addSamplePreparation(imageDto.getSamplePreparationLabel());
		res.addHostName(imageDto.getHostName());
		res.addImageGeneratedBy(imageDto.getImageGeneratedBy());
		res.addSampleGeneratedBy(imageDto.getSampleGeneratedBy());
		res.addVisualizationMethod(imageDto.getVisualisationMethodId());
		res.addVisualizationMethod(imageDto.getVisualisationMethodLabel());
		res.addVisualizationMethod(imageDto.getVisualisationMethodSynonyms());
		res.addPhenotype(imageDto.getPhenotypeIdBag());
		res.addPhenotype(imageDto.getPhenotypeLabelBag());
		res.addPhenotype(imageDto.getPhenotypeSynonymsBag());
		res.addExpressionIn(imageDto.getExpressionInIdBag());
		res.addExpressionIn(imageDto.getExpressionInLabelBag());
		res.addExpressionIn(imageDto.getExpressionInSynonymsBag());
		res.addAbnormalAnatomy(imageDto.getAbnormalAnatomyIdBag());
		res.addAbnormalAnatomy(imageDto.getAbnormalAnatomySynonymsBag());
		res.addAbnormalAnatomy(imageDto.getAbnormalAnatomyTermBag());
		res.addMutantGenes(imageDto.getMutantGeneIdBag());
		res.addMutantGenes(imageDto.getMutantGeneSymbolBag());
		res.addMutantGenes(imageDto.getMutantGeneSynonymsBag());
		res.addDepictedAnatomy(imageDto.getDepictedAnatomyTermBag());
		res.addDepictedAnatomy(imageDto.getDepictedAnatomyIdBag());
		res.addDepictedAnatomy(imageDto.getDepictedAnatomySynonymsBag());
		res.addSex(imageDto.getSex());
		res.addGene(imageDto.getGeneIds());
		res.addGene(imageDto.getGeneSymbols());
		res.addGene(imageDto.getGeneSynonyms());
		res.addGeneticFeature(imageDto.getGeneticFeatureIds());
		res.addGeneticFeature(imageDto.getGeneticFeatureSymbols());
		res.addGeneticFeature(imageDto.getGeneticFeatureSynonyms());		
		return res;
		
	}
	
	protected ArrayList<AutosuggestBean> getBeans(ImageDTO doc) 
	throws Exception{
		
		ArrayList<AutosuggestBean> res = new ArrayList<>();
		
		if (doc.getGeneIds() != null){
			res.addAll(getBeansForArrays(doc.getGeneIds(), doc.getGeneSymbols(), null, AutosuggestTypes.GENE));
		}
		
		if (doc.getMutantGeneIdBag() != null){
			res.addAll(getBeansForArrays(doc.getMutantGeneIdBag(), doc.getMutantGeneSymbolBag(), null, AutosuggestTypes.GENE));
		}
		if (doc.getHostName() != null){
			res.add(new AutosuggestBean(null, doc.getHostName() , null, AutosuggestTypes.GENERIC_AUTOSUGGEST));
		}
		if (doc.getSampleGeneratedBy() != null){
			res.add(new AutosuggestBean(null, doc.getSampleGeneratedBy(), null, AutosuggestTypes.GENERIC_AUTOSUGGEST));
		}
		if (doc.getImageGeneratedBy() != null){
			for (String igb : doc.getImageGeneratedBy()){
				res.add(new AutosuggestBean(null,  igb,  null,  AutosuggestTypes.GENERIC_AUTOSUGGEST));
			}
		}
		
		if (doc.getAnatomyId() != null){
			res.add(new AutosuggestBean(doc.getAnatomyId(), doc.getAnatomyTerm(), doc.getAnatomySynonyms(), AutosuggestTypes.ANATOMY));
		}
		if (doc.getAnatomyAncestors() != null){
			res.addAll(getBeansForAncestors(doc.getAnatomyAncestors(), AutosuggestTypes.ANATOMY));
		}
		
		if (doc.getExpressionInIdBag() != null){
			res.addAll(getBeansForArrays(doc.getExpressionInIdBag(), doc.getExpressionInLabelBag(), null, AutosuggestTypes.ANATOMY));
		}
		if (doc.getExpressionInAncestors() != null){
			res.addAll(getBeansForAncestors(doc.getExpressionInAncestors(), AutosuggestTypes.ANATOMY));
		}
		
		if (doc.getAnatomyComputedIdBag() != null){
			res.addAll(getBeansForArrays(doc.getAnatomyComputedIdBag(), doc.getAnatomyComputedLabelBag(), null, AutosuggestTypes.ANATOMY));
		}
		if (doc.getAnatomyComputedAncestors() != null){
			res.addAll(getBeansForAncestors(doc.getAnatomyComputedAncestors(), AutosuggestTypes.ANATOMY));
		}
		
		if (doc.getDepictedAnatomyIdBag() != null){
			res.addAll(getBeansForArrays(doc.getDepictedAnatomyIdBag(), doc.getDepictedAnatomyTermBag(), null, AutosuggestTypes.ANATOMY));
		}
		if (doc.getDepictedAnatomyAncestors() != null){
			res.addAll(getBeansForAncestors(doc.getDepictedAnatomyAncestors(), AutosuggestTypes.ANATOMY));
		}
		
		if (doc.getAbnormalAnatomyIdBag() != null){
			res.addAll(getBeansForArrays(doc.getAbnormalAnatomyIdBag(), doc.getAbnormalAnatomyTermBag(), null, AutosuggestTypes.ANATOMY));
		}
		if (doc.getAbnormalAnatomyAncestors() != null){
			res.addAll(getBeansForAncestors(doc.getAbnormalAnatomyAncestors(), AutosuggestTypes.ANATOMY));
		}
		
		if (doc.getPhenotypeIdBag() != null){
			res.addAll(getBeansForArrays(doc.getPhenotypeIdBag(), doc.getPhenotypeLabelBag(), null, AutosuggestTypes.PHENOTYPE));
		}
		if (doc.getPhenotypeAncestors() != null){
			res.addAll(getBeansForAncestors(doc.getPhenotypeAncestors(), AutosuggestTypes.PHENOTYPE));
		}
		
		if (doc.getVisualisationMethodId() != null){
			res.addAll(getBeansForArrays(doc.getVisualisationMethodId(), doc.getVisualisationMethodLabel(), null, AutosuggestTypes.GENERIC_AUTOSUGGEST));
		}	
		if (doc.getVisualisationMethodAncestors() != null){
			res.addAll(getBeansForAncestors(doc.getVisualisationMethodAncestors(), AutosuggestTypes.GENERIC_AUTOSUGGEST));	
		}
		
		if (doc.getSamplePreparationId() != null){
			res.addAll(getBeansForArrays(doc.getSamplePreparationId(), doc.getSamplePreparationLabel(), null, AutosuggestTypes.GENERIC_AUTOSUGGEST));
		}	
		if (doc.getSamplePreparationAncestors() != null){
			res.addAll(getBeansForAncestors(doc.getSamplePreparationAncestors(), AutosuggestTypes.GENERIC_AUTOSUGGEST));	
		}
		
		if (doc.getImagingMethodId() != null){
			res.addAll(getBeansForArrays(doc.getImagingMethodId(), doc.getImagingMethodLabel(), null, AutosuggestTypes.GENERIC_AUTOSUGGEST));
		}	
		if (doc.getImagingMethodAncestors() != null){
			res.addAll(getBeansForAncestors(doc.getImagingMethodAncestors(), AutosuggestTypes.GENERIC_AUTOSUGGEST));
		}
		
		return res;
	}
	
	protected ArrayList<AutosuggestBean> getBeansForArrays(List<String> ids, List<String> labels, List<List<String>> synonyms, AutosuggestTypes type) 
	throws Exception{

		ArrayList<AutosuggestBean> res = new ArrayList<>();
		if (ids != null && ids.size() == labels.size()){
			for (int i = 0; i < ids.size(); i++){
				List<String> syn = (synonyms != null ? synonyms.get(i) : null);
				res.add(new AutosuggestBean(ids.get(i), labels.get(i), syn, type));
			}
		}
		return res;
	}
	
	
	protected ArrayList<AutosuggestBean> getBeansForAncestors (List<String> ancestors, AutosuggestTypes type) 
	throws Exception{

		ArrayList<AutosuggestBean> res = new ArrayList<>();
		for (String value : ancestors){
			if (isId(value)){
				OntologyObject ot = ou.getOntologyTermById(value);
				res.add(new AutosuggestBean(value, ot.getLabel(), ot.getSynonyms(), type));
			}
		}
		return res;
	}
	
	protected boolean isId(String string){

		Pattern p = Pattern.compile("[A-Za-z]+_[0-9]+");
		return p.matcher(string).matches();
	}
		
	/*
	 * 
	  
   <copyField source="expression_in_freetext_bag" dest="generic_search"/>
   <copyField source="anatomy_id" dest="generic_search"/>
   <copyField source="anatomy_term" dest="generic_search"/>
   <copyField source="anatomy_freetext" dest="generic_search"/>
   <copyField source="depicted_anatomy_freetext_bag" dest="generic_search"/>
   <copyField source="abnormal_anatomy_freetext_bag" dest="generic_search"/>
   <copyField source="phenotype_freetext_bag" dest="generic_search"/>

   <copyField source="mutant_gene_synonyms_bag" dest="generic_search"/>
   <copyField source="mutant_gene_id_bag" dest="generic_search"/>
   <copyField source="observation_bag" dest="generic_search"/>
   <copyField source="zygosity" dest="generic_search"/>
   <copyField source="conditions" dest="generic_search"/>
   <copyField source="stage" dest="generic_search"/>
   <copyField source="stage_id" dest="generic_search"/>
   <copyField source="taxon" dest="generic_search"/>
   <copyField source="ncbi_taxon_id" dest="generic_search"/>
   <copyField source="sample_generated_by" dest="generic_search"/>
   <copyField source="host_name" dest="generic_search"/>
   <copyField source="image_generated_by" dest="generic_search"/>
   <copyField source="sample_preparation_label" dest="generic_search"/>
   <copyField source="sample_preparation_id" dest="generic_search"/>
   <copyField source="sample_preparation_synonyms" dest="generic_search"/>
   <copyField source="visualisation_method_id" dest="generic_search"/>
   <copyField source="visualisation_method_label" dest="generic_search"/>
   <copyField source="visualisation_method_synonyms" dest="generic_search"/>
   <copyField source="machine" dest="generic_search"/>
   */
	
	protected class AutosuggestBean{
		String term;
		String id;
		String type;
		List<String> synonyms;
		
		protected String getDocumentId(){
			return term + "#" + id + "#" + type;
		}
		
		protected AutosuggestBean(String id, String term, List<String> synonyms, AutosuggestTypes type) 
		throws Exception{
			this.term = term;
			this.id = id;
			this.synonyms = synonyms;
			this.type = type.name();
			if (term == null){
				throw new Exception("No term label in autosuggest bean");
			}
		}

		
		public String getId() {
		
			return id;
		}

		public String getTerm() {
		
			return term;
		}
		
		public void setTerm(String term) {
		
			this.term = term;
		}

		
		public String getType() {
		
			return type;
		}

		
		public void setType(String type) {
		
			this.type = type;
		}

		
		public List<String> getSynonyms() {
		
			return synonyms;
		}

		
		public void setSynonyms(List<String> synonyms) {
		
			this.synonyms = synonyms;
		}

		
		public void setId(String id) {
		
			this.id = id;
		}

		@Override
		public String toString() {

			return "AutosuggestBean [term=" + term + ", id=" + id + ", type=" + type + ", synonyms=" + synonyms + "]";
		}
		
	}
}

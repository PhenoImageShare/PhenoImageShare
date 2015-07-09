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
package uk.ac.ebi.phis.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.neo4j.cypher.ParameterNotFoundException;
import org.springframework.stereotype.Service;

import uk.ac.ebi.phis.exception.BasicPhisException;
import uk.ac.ebi.phis.solrj.dto.ChannelDTO;
import uk.ac.ebi.phis.solrj.dto.ImageDTO;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;
import uk.ac.ebi.phis.utils.web.JSONRestUtil;

@Service
public class ImageService extends BasicService{

	private static final Logger log = Logger.getLogger(ImageService.class);

	public ImageService(String solrUrl) {
		super(solrUrl);

	}
			
	public String getImages(String term, String phenotype, String mutantGene, String anatomy, String expressedGene, String sex, 
							String taxon, String image_type, String sample_type, String stage, String visualisationMethod, String samplePreparation, 
							String imagingMethod, Integer rows, Integer start, String genericGene) 
	throws SolrServerException{

		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
				
		if (term != null){
			solrQuery.setQuery(ImageDTO.GENERIC_SEARCH + ":" + handleSpecialCharacters(term));
			if (term.contains(" ")){
				String[] splittedQuery = term.split(" ");
				String query = ImageDTO.GENERIC_SEARCH + ":" + org.apache.commons.lang3.StringUtils.join(splittedQuery, "^10 " + ImageDTO.GENERIC_SEARCH + ":");			
				solrQuery.set("defType", "edismax");
				solrQuery.set("qf",  ImageDTO.GENERIC_SEARCH);
				solrQuery.set("bq", ImageDTO.GENERIC_SEARCH + ":\"" + term + "\"^100 " + handleSpecialCharacters(query) + " " + ImageDTO.GENERIC_SEARCH_ANCESTORS + ":\"" + term + "\"^1" );
			
			}else{
				solrQuery.addFilterQuery(ImageDTO.GENERIC_SEARCH + ":"+ handleSpecialCharacters(term));
			}
		}
		
		if (phenotype != null){
			phenotype = handleSpecialCharacters(phenotype);
			solrQuery.addFilterQuery(ImageDTO.PHENOTYPE_ID_BAG + ":\""+ phenotype + "\" OR " + 
				ImageDTO.PHENOTYPE_FREETEXT_BAG + ":\""+ phenotype + "\" OR " + 
				ImageDTO.PHENOTYPE_LABEL_BAG + ":\""+ phenotype + "\" OR " + ImageDTO.PHENOTYPE_ANCESTORS + ":\"" + phenotype + "\"");
		}
		
		if (mutantGene != null){
			mutantGene = handleSpecialCharacters(mutantGene);
			solrQuery.addFilterQuery(ImageDTO.GENE_ID + ":\""+ mutantGene + "\" OR " +
				ImageDTO.GENE_SYMBOL + ":\""+ mutantGene + "\"");		
		}
		
		if (genericGene != null){
			genericGene = handleSpecialCharacters(genericGene);
			solrQuery.addFilterQuery(ImageDTO.GENE_ID + ":\""+ genericGene + "\" OR " +	ImageDTO.GENE_SYMBOL + ":\""+ genericGene + "\" OR " + 
				ImageDTO.EXPRESSED_GF_ID_BAG + ":\"" + genericGene + "\" OR " + ImageDTO.EXPRESSED_GF_SYMBOL_BAG + ":\"" + genericGene + "\" OR " +  
				ImageDTO.MUTANT_GENE_ID_BAG + ":\"" + genericGene + "\" OR " + ImageDTO.MUTANT_GENE_SYMBOL_BAG + ":\"" + genericGene + "\" OR" +
				ImageDTO.MUTANT_GENE_SYNONYMS_BAG + ":\"" + genericGene +"\"");		
		}
		
		if (anatomy != null){
			anatomy = handleSpecialCharacters(anatomy);
			solrQuery.addFilterQuery(ImageDTO.GENERIC_ANATOMY + ":\""+ anatomy + "\" OR " + ImageDTO.GENERIC_ANATOMY_ANCESTORS + ":\"" + anatomy + "\"");
		}
		if (expressedGene != null){
			expressedGene = handleSpecialCharacters(expressedGene);
			solrQuery.addFilterQuery(ImageDTO.EXPRESSED_GF_ID_BAG + ":\"" + expressedGene + "\"");
		}
		if (taxon != null){
			taxon = handleSpecialCharacters(taxon);
			solrQuery.addFilterQuery(ImageDTO.TAXON + ":\"" + taxon + "\" OR " + 
				ImageDTO.NCBI_TAXON_ID + ":\"" + taxon + "\"");
		}
		if (image_type != null){
			image_type = handleSpecialCharacters(image_type);
			solrQuery.addFilterQuery(ImageDTO.IMAGE_TYPE + ":\"" + image_type + "\"");
		}
		if (sample_type != null){
			sample_type = handleSpecialCharacters(sample_type);
			solrQuery.addFilterQuery(ImageDTO.SAMPLE_TYPE + ":\"" + sample_type + "\"");
		}
		if (stage != null){
			stage = handleSpecialCharacters(stage);
			solrQuery.addFilterQuery(ImageDTO.STAGE + ":\"" + stage + "\" OR " + 
				ImageDTO.STAGE_ID + ":\"" + stage + "\" OR " + ImageDTO.STAGE_ANCESTORS + ":\"" + stage + "\"" );
		}
		if (visualisationMethod != null){
			visualisationMethod = handleSpecialCharacters (visualisationMethod);
			solrQuery.addFilterQuery(ImageDTO.VISUALISATION_METHOD_ID + ":\"" + visualisationMethod + "\" OR " + 
				ImageDTO.VISUALISATION_METHOD_LABEL + ":\"" + visualisationMethod + "\" OR " + 
				ImageDTO.VISUALISATION_METHOD_ANCESTORS + ":\"" + visualisationMethod + "\"");
		}
		if (samplePreparation != null){
			samplePreparation = handleSpecialCharacters(samplePreparation);
			solrQuery.addFilterQuery(ImageDTO.SAMPLE_PREPARATION_ID + ":\"" + samplePreparation + "\" OR " + 
				ImageDTO.SAMPLE_PREPARATION_LABEL + ":\"" + samplePreparation + "\" OR " + ImageDTO.SAMPLE_PREPARATION_ANCESTORS + ":\"" + samplePreparation + "\"");
		}
		if (imagingMethod != null){
			imagingMethod = handleSpecialCharacters(imagingMethod);
			solrQuery.addFilterQuery(ImageDTO.IMAGING_METHOD_LABEL_ANALYSED + ":\"" + imagingMethod + "\" OR " + 
			ImageDTO.IMAGING_METHOD_ID + ":\"" + imagingMethod + "\" OR " + ImageDTO.IMAGING_METHOD_ANCESTORS + ":\"" + imagingMethod + "\"");
		}
		if (sex != null){
			sex = handleSpecialCharacters(sex);
			solrQuery.addFilterQuery(ImageDTO.SEX + ":\"" + sex + "\"");
		}
		
		if (rows != null){
			solrQuery.setRows(rows);
		}
		else solrQuery.setRows(100);
		
		if (start != null){
			solrQuery.set("start", start);
		}
		solrQuery.set("wt", "json");
		solrQuery.setFacet(true);
		solrQuery.addFacetField(ImageDTO.STAGE);
		solrQuery.addFacetField(ImageDTO.IMAGING_METHOD_LABEL);
		solrQuery.addFacetField(ImageDTO.TAXON);
		solrQuery.addFacetField(ImageDTO.SAMPLE_TYPE);
		solrQuery.addFacetField(ImageDTO.IMAGE_GENERATED_BY);
		solrQuery.addFacetField(ImageDTO.HOST_NAME);
		solrQuery.addFacetField(ImageDTO.IMAGE_TYPE);
		solrQuery.setFacetMinCount(0);
		
		solrQuery.set("facet.pivot.mincount",0);
		
		// add pivot facets to get the number of image types per 
		solrQuery.set("facet.pivot", ImageDTO.SAMPLE_TYPE + "," + ImageDTO.IMAGE_TYPE);	
		
		System.out.println("\nSolr URL : " + solr.getBaseURL() + "/select?" + solrQuery);
		log.info("Solr URL in getImages : " + solr.getBaseURL() + "/select?" + solrQuery);
		
		try {
			return JSONRestUtil.getResults(getQueryUrl(solrQuery)).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "Couldn't get anything back from solr.";
	}
	
	public String getImageAsJsonString(String imageId){
		SolrQuery solrQuery = new SolrQuery();
		imageId = handleSpecialCharacters(imageId);
		solrQuery.setQuery(ImageDTO.ID + ":\""+ imageId + "\"");
		solrQuery.set("wt", "json");
		
		System.out.println("------ ImagePojo" + getQueryUrl(solrQuery));

		try {
			return JSONRestUtil.getResults(getQueryUrl(solrQuery)).toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return "Is this a valid id? Couldn't get anything back from solr.";
	}
	
	
	
	/**
	 * 
	 * @param roiToReplace must exist
	 * @param roiToAdd must have the same id as roiToReplace
	 * @throws BasicPhisException 
	 */
	public void updateImageFromRoi(RoiDTO roiToReplace, RoiDTO roiToAdd) throws BasicPhisException{
		if (imageIdExists(roiToAdd.getAssociatedImage()) && imageIdExists(roiToReplace.getAssociatedImage())){
			deleteRoiRefferences(roiToReplace);
			addToImageFromRoi(roiToAdd);
		}
	}
	
	
	public boolean imageIdExists(String id) throws BasicPhisException{

		return getImageDTOById(id) != null;
	}
	
	/**
	 * Delete all refferences to this roi (roi id, annotations from annotations bags)
	 * @param roi
	 * @throws BasicPhisException 
	 */
	public void deleteRoiRefferences(RoiDTO roi) throws BasicPhisException{
		
		ImageDTO img = getImageDTOById(roi.getAssociatedImage());

		System.out.println("Roi list before " + img.getAssociatedRoi().size() + "  " + img.getAssociatedRoi());
		List<String> list = img.getAssociatedRoi();
		list.remove(roi.getId());
		img.setAssociatedRoi(list);
		
		//abnormality
		if (roi.getAbnormalityAnatomyId() != null){
			img.setAbnormalAnatomyIdBag(removeOnce(img.getAbnormalAnatomyIdBag(), roi.getAbnormalityAnatomyId()));
		}
		if (roi.getAbnormalityAnatomyFreetext() != null){
			img.setAbnormalAnatomyFreetextBag(removeOnce(img.getAbnormalAnatomyFreetextBag(), roi.getAbnormalityAnatomyFreetext()));
		}
		if (roi.getAbnormalityAnatomyTerm() != null){
			img.setAbnormalAnatomyTermBag(removeOnce(img.getAbnormalAnatomyTermBag(), roi.getAbnormalityAnatomyTerm()));
		}
		
		//phenotpe
		if (roi.getPhenotypeId() != null){
			img.setPhenotypeIdBag(removeOnce(img.getPhenotypeIdBag(), roi.getPhenotypeId()));
		}
		if (roi.getPhenotypeFreetext() != null){
			img.setPhenotypeFreetextBag(removeOnce(img.getPhenotypeFreetextBag(), roi.getPhenotypeFreetext()));
		}
		if (roi.getPhenotypeTerm() != null){
			img.setPhenotypeLabelBag(removeOnce(img.getPhenotypeLabelBag(), roi.getPhenotypeTerm()));
		}
		
		//observation
		if (roi.getObservations() != null){
			img.setObservationBag(removeOnce(img.getObservationBag(), roi.getObservations()));
		}
		
		//depicted anat
		if (roi.getDepictedAnatomyId() != null){
			img.setDepictedAnatomyIdBag(removeOnce(img.getDepictedAnatomyIdBag(), roi.getDepictedAnatomyId()));
		}
		if (roi.getDepictedAnatomyFreetext() != null){
			img.setDepictedAnatomyFreetextBag(removeOnce(img.getDepictedAnatomyFreetextBag(), roi.getDepictedAnatomyFreetext()));
		}
		if (roi.getDepictedAnatomyTerm() != null){
			img.setDepictedAnatomyTermBag(removeOnce(img.getDepictedAnatomyTermBag(), roi.getDepictedAnatomyTerm()));
		}
		
		//expression 
		if (roi.getExpressedAnatomyFreetext() != null){
			img.setExpressionInFreetextBag(removeOnce(img.getExpressionInFreetextBag(), roi.getExpressedAnatomyFreetext()));
		}
		if (roi.getExpressedAnatomyId() != null){
			img.setExpressionInIdBag(removeOnce(img.getExpressionInIdBag(), roi.getExpressedAnatomyId()));
		}
		if (roi.getExpressedAnatomyTerm() != null){
			img.setExpressionInLabelBag(removeOnce(img.getExpressionInLabelBag(), roi.getExpressedAnatomyTerm()));
		}
		img.setGenericSearch(new ArrayList<String>());
				
		List<ImageDTO> update = new ArrayList<>();
		update.add(img);
		addBeans(update);
	}
	
	public ArrayList<String> removeOnce(ArrayList<String> from, List<String> whatToDelete){
		ArrayList<String> res = from;
		if ( res != null){
			for(String toRemove : whatToDelete){
				from.remove(toRemove);
			}
		}
		return from;
	}
	
	/**
	 * To be used for atomic updates when a user adds a new annotation
	 * @param roi
	 * @throws BasicPhisException 
	 * @throws Exception 
	 */
	public void addToImageFromRoi(RoiDTO roi) throws ParameterNotFoundException, BasicPhisException{
		
		ImageDTO img = getImageDTOById(roi.getAssociatedImage());
		
		if (img.getAssociatedRoi() == null){
			
			throw new ParameterNotFoundException("Image id does not exist");
			
		}else if(!img.getAssociatedRoi().contains(roi.getId())){
			
			img.addAssociatedRoi(roi.getId());
			
			// Abnorm
			if (roi.getAbnormalityAnatomyId() != null){
				img.addAbnormalAnatomyIdBag(roi.getAbnormalityAnatomyId());
			}
			if (roi.getAbnormalityAnatomyFreetext() != null){
				img.addAbnormalAnatomyFreetextBag(roi.getAbnormalityAnatomyFreetext());
			}
			if (roi.getAbnormalityAnatomyTerm() != null){
				img.addAbnormalAnatomyTermBag(roi.getAbnormalityAnatomyTerm());
			}
			
			//phenotype
			if (roi.getPhenotypeId() != null){
				img.addPhenotypeIdBag((ArrayList<String>) roi.getPhenotypeId());
			}
			if (roi.getPhenotypeFreetext() != null){
				img.addPhenotypeFreetextBag((ArrayList<String>) roi.getPhenotypeFreetext());
			}
			if (roi.getPhenotypeTerm() != null){
				img.addPhenotypeTermBag((ArrayList<String>) roi.getPhenotypeTerm());
			}
			
			//observation
			if (roi.getObservations() != null){
				img.addObservationBag((ArrayList<String>) roi.getObservations());
			}
			
			//depicted
			if (roi.getDepictedAnatomyId() != null){
				img.addDepictedAnatomyIdBag(roi.getDepictedAnatomyId());
			}
			if (roi.getDepictedAnatomyTerm() != null){
				img.addDepictedAnatomyTermBag(roi.getDepictedAnatomyTerm());
			}
			if (roi.getDepictedAnatomyFreetext() != null){
				img.addDepictedAnatomyFreetextBag(roi.getDepictedAnatomyFreetext());
			}
			
			//expression in
			if (roi.getExpressedAnatomyFreetext() != null){
				img.addExpressionInFreetextBag(roi.getExpressedAnatomyFreetext());
			}
			if (roi.getExpressedAnatomyId() != null){
				img.addExpressionInIdBag(roi.getExpressedAnatomyId());
			}
			if (roi.getExpressedAnatomyTerm() != null){
				img.addExpressionInTermBag(roi.getExpressedAnatomyTerm());
			}
								
			img.setGenericSearch(new ArrayList<String>());
			List<ImageDTO> update = new ArrayList<>();
			update.add(img);
			addBeans(update);
		}
	}
	
	public ImageDTO getImageDTOById(String imageId) throws BasicPhisException{
		ImageDTO img = null;
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(ImageDTO.ID + ":" + imageId);
		try {
			List<ImageDTO> images = solr.query(solrQuery).getBeans(ImageDTO.class);
			img = images.get(0);
		} catch (Exception e) { 
		//	e.printStackTrace();
			throw new BasicPhisException("Image id provided could not be found.");
		}
		return img;
	}
	
	public String getSolrUrl () {
		return solr.getBaseURL();
	}
	
	public void addBeans(List<ImageDTO> imageDocs){
		try {
			solr.addBeans(imageDocs);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void clear() {
		try {
			solr.deleteByQuery("*:*");
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
	

	public String getQueryUrl(SolrQuery q){
		return solr.getBaseURL() + "/select?" + q.toString();
	}
}

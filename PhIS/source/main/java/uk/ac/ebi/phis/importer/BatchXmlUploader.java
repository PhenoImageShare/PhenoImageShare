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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.solr.client.solrj.SolrServerException;
import org.xml.sax.SAXException;

import uk.ac.ebi.phis.jaxb.Annotation;
import uk.ac.ebi.phis.jaxb.AnnotationMode;
import uk.ac.ebi.phis.jaxb.Channel;
import uk.ac.ebi.phis.jaxb.Doc;
import uk.ac.ebi.phis.jaxb.GenotypeComponent;
import uk.ac.ebi.phis.jaxb.Image;
import uk.ac.ebi.phis.jaxb.ImageDescription;
import uk.ac.ebi.phis.jaxb.ImageType;
import uk.ac.ebi.phis.jaxb.Organism;
import uk.ac.ebi.phis.jaxb.Roi;
import uk.ac.ebi.phis.service.ChannelService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.service.RoiService;
import uk.ac.ebi.phis.solrj.dto.ChannelDTO;
import uk.ac.ebi.phis.solrj.dto.ImageDTO;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;
import uk.ac.ebi.phis.utils.ValidationUtils;
import uk.ac.ebi.phis.utils.ontology.OntologyObject;
import uk.ac.ebi.phis.utils.ontology.OntologyUtils;

public class BatchXmlUploader {

	HashMap<String, Image> imageIdMap = new HashMap<>();
	HashMap<String, Channel> channelIdMap = new HashMap<>();
	HashMap<String, Roi> roiIdMap = new HashMap<>();

	ClassLoader classloader;

	ValidationUtils vu = new ValidationUtils();
	OntologyUtils ou = vu.ou;

	ImageService is;
	RoiService rs;
	ChannelService cs;
	
	
	public BatchXmlUploader(ImageService is, RoiService rs, ChannelService cs) {
		classloader = Thread.currentThread().getContextClassLoader();
		this.is = is;
		this.rs = rs;
		this.cs = cs;
	}


	public boolean validate(String xmlLocation) {

		InputStream xsd;
		InputStream xml;
		boolean isValid = false;
		// Unmarshal XML
		Doc doc = convertXmlToObjects(xmlLocation);
		
		try {
			xsd = classloader.getResourceAsStream("phisSchema.xsd");
			xml = new FileInputStream(xmlLocation);
			isValid = validateAgainstXSD(xml, xsd);
			
			xsd.close();
			xml.close();
			isValid = (isValid && checkInformation(doc));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return isValid;
	}


	public void index(String xmlLocation, String datasource)
	throws IOException, SolrServerException {
		
		Doc doc = convertXmlToObjects(xmlLocation);
		addImageDocuments(doc.getImage(), datasource);
		addRoiDocuments(doc.getRoi(), datasource);
		addChannelDocuments(doc.getChannel(), datasource);
		
	}


	private void addImageDocuments(List<Image> images, String datasource)
	throws IOException, SolrServerException {
		
		int i = 0;
		List<ImageDTO> imageDocs = new ArrayList<>();
		for (Image img : images) {
			imageDocs.add(fillPojo(img, datasource));
			// flush every 1000 docs
			if (i++ % 1000 == 0) {
				is.addBeans(imageDocs);
				imageDocs = new ArrayList<>();
				System.out.println("Documents added " + i);
			}
		}
		is.addBeans(imageDocs);
	}

	private void addRoiDocuments(List<Roi> rois, String datasource)
	throws IOException, SolrServerException {
		
		int i = 0;
		System.out.println("rois list is " + rois.size());
		List<RoiDTO> roiDocs = new ArrayList<>();
		for (Roi roi : rois) {
			// add it
			roiDocs.add(fillPojo(roi, datasource));
			// flush every 1000 docs
			if (i++ % 1000 == 0) {
				rs.addBeans(roiDocs);
				roiDocs = new ArrayList<>();
			}
		}
		rs.addBeans(roiDocs);
	}

	
	private void addChannelDocuments(List<Channel> channels, String datasource)
	throws IOException, SolrServerException {
		
		int i = 0;
		List<ChannelDTO> chDocs = new ArrayList<>();
		System.out.println("channel list is " + channels.size());
		for (Channel channel : channels) {
			// add it
			chDocs.add(fillPojo(channel, datasource));
			// flush every 1000 docs
			if (i++ % 1000 == 0) {
				cs.addBeans(chDocs);
				chDocs = new ArrayList<>();
			}
		}
		cs.addBeans(chDocs);
	}

	
	private RoiDTO fillPojo(Roi roi, String host){
		
		RoiDTO bean = new RoiDTO();
		
		bean.setId(getRoiId(roi.getId(), host));

		bean.setAssociatedImage(getImageId(roi.getAssociatedImage(), host));
		
		if (roi.getAssociatedChannel() != null){
			bean.setAssociatedChannel(getChannelId(roi.getAssociatedChannel().getEl(), host));
		}
		
		if (roi.getDepictedAnatomicalStructure() != null){
			List<String> ids = new ArrayList<>(); 
			List<String> labels = new ArrayList<>(); 
			List<String> freetext = new ArrayList<>();
			// Depicted anatomy
			// TODO have to check if it's expression (from channel)
			for ( Annotation ann: roi.getDepictedAnatomicalStructure().getEl()){
				if (ann.getAnnotationFreetext() != null){
					freetext.add(ann.getAnnotationFreetext());
				}
				if (ann.getOntologyTerm() != null){
					OntologyObject oo = ou.getOntologyTermById(ann.getOntologyTerm().getTermId());
					if (ann.getAnnotationMode() != null && (ann.getAnnotationMode() == AnnotationMode.AUTOMATED)){
						bean.addComputedDepictedAnatomyId(oo.getId());
						bean.addComputedDepictedAnatomyTerm(oo.getLabel());
					}
					else {
						ids.add(oo.getId());
						labels.add(oo.getLabel());
					}
				}
			}
			//	if(roi.getIsExpressionPattern().equals(YesNo.YES)){
				if (ids.size() > 0){
					bean.setExpressedAnatomyId(ids);
					bean.setExpressedAnatomyTerm(labels);
				}
				if (freetext.size() > 0){
					bean.setExpressedAnatomyFreetext(freetext);
				}
		//	} else {
	/*			if (ids.size() > 0){
					bean.setDepictedAnatomyId(ids);
					bean.setDepictedAnatomyTerm(labels);
				}
				if (freetext.size() > 0){
					bean.setDepictedAnatomyFreetext(freetext);
				}
				if (computedIds.size() > 0){
					bean.setComputedDepictedAnatomyId(computedIds);
					bean.setComputedDepictedAnatomyTerm(computedLabels);
				}
	*/	//	}
		}
		
		if (roi.getAbnormalityInAnatomicalStructure() != null){
			
			// Abnormality in anatomical part
			for ( Annotation ann: roi.getAbnormalityInAnatomicalStructure().getEl()){
				if (ann.getAnnotationFreetext() != null){
					bean.addAbnormalityAnatomyFreetext(ann.getAnnotationFreetext());
				}
				if (ann.getOntologyTerm() != null){
					if (ann.getAnnotationMode() != null && (ann.getAnnotationMode() == AnnotationMode.AUTOMATED)){
						bean.addComputedAbnormalityAnatomyId(ann.getOntologyTerm().getTermId());
						bean.addComputedAbnormalityAnatomyTerm(ann.getOntologyTerm().getTermLabel());
					}
					else {
						bean.addAbnormalityAnatomyId(ann.getOntologyTerm().getTermId());
						bean.addAbnormalityAnatomyTerm(ann.getOntologyTerm().getTermLabel());
					}
				}
			}			
		}
		
		if (roi.getPhenotypeAnnotations() != null){
			// Phenotypes
			for ( Annotation ann: roi.getPhenotypeAnnotations().getEl()){
				if (ann.getAnnotationFreetext() != null){
					bean.addPhenotypeFreetext(ann.getAnnotationFreetext());
				}
				if (ann.getOntologyTerm() != null){
					OntologyObject obj = ou.getOntologyTermById(ann.getOntologyTerm().getTermId());
					bean.addPhenotypeId(obj.getId());
					bean.addPhenotypeTerm(obj.getLabel());
				}
			}
		}
		
		if (roi.getObservations() != null){
			bean.setObservations(roi.getObservations().getEl());
		}

		bean.setXCoordinates(roi.getCoordinates().getXCoordinates().getEl());
		bean.setYCoordinates(roi.getCoordinates().getYCoordinates().getEl());
		if (roi.getCoordinates().getZCoordinates() != null){
			bean.setZCoordinates(roi.getCoordinates().getZCoordinates().getEl());
		}
		
		return bean;
	}
	
	
	private ChannelDTO fillPojo(Channel channel, String datasource) {

		ChannelDTO bean = new ChannelDTO();
		
		bean.setId(getChannelId(channel.getId(), datasource));
		bean.setAssociatedImage(getImageId(channel.getAssociatedImage(), datasource));
		if (channel.getAssociatedRoi() != null){
			bean.setAssociatedRoi(getRoiId(channel.getAssociatedRoi().getEl(), datasource));
		}
		if (channel.getDepictsExpressionOf() != null){
			GenotypeComponent gc = channel.getDepictsExpressionOf();
			if (gc.getGeneId() != null){
				bean.setGeneId(gc.getGeneId());
				bean.setGeneSymbol(gc.getGeneSymbol());
			}
			if (gc.getGeneticFeatureEnsemblId() != null){
				bean.setGeneticFeatureEnsemlId(gc.getGeneticFeatureEnsemblId());
			}
			if (gc.getGeneticFeatureId() != null){
				bean.setGeneticFeatureId(gc.getGeneticFeatureId());
				bean.setGeneticFeatureSymbol(gc.getGeneticFeatureSymbol());
			}
			if (gc.getZygosity() != null){
				bean.setZygosity(gc.getZygosity().name());
			}
			if (gc.getMutationType() != null){
				bean.setMutationType(gc.getMutationType().getAnnotationFreetext());
			}
			if (gc.getGenomicLocation() != null){
				bean.setStartPos(gc.getGenomicLocation().getStartPos());
				if (gc.getGenomicLocation().getEndPos() != null){
					bean.setEndPos(gc.getGenomicLocation().getEndPos());
				}
				bean.setChromosome(gc.getGenomicLocation().getChromosone());
				bean.setStrand(gc.getGenomicLocation().getStrand());
			}
		}
		
		return bean;	
	}
	

	private ImageDTO fillPojo(Image img, String datasource) {

		ImageDTO bean = new ImageDTO();
		bean.setTaxon(img.getOrganism().getTaxon());
		bean.setBackgroundStrain(img.getOrganism().getBackgroundStrain().getEl());
		bean.setId(getImageId(img.getId(), datasource));

		ImageDescription desc = img.getImageDescription();
		if (desc.getImageGeneratedBy() != null){
			bean.addImageGeneratedBy(desc.getImageGeneratedBy().getDisplayName());
		}
		
		if (desc.getLicence() != null){
			bean.setLicence(desc.getLicence());
		}
		
		if (desc.getOrganismGeneratedBy() != null){
			bean.setSampleGeneratedBy(desc.getOrganismGeneratedBy().getDisplayName());
		}
		
		bean.setHostName(desc.getHost().getDisplayName());
		bean.setHostUrl(desc.getHost().getUrl());
		bean.setImageUrl(desc.getImageUrl());
		
		if (desc.getMagnificationLevel() != null){
			bean.setMagnificationLevel(desc.getMagnificationLevel());
		}
		if (desc.getPublication() != null && desc.getPublication().getEl() != null &&  desc.getPublication().getEl().size() > 0){
			bean.setPublication(desc.getPublication().getEl());
		}
		
		if (desc.getImageContextUrl() != null){
			bean.setImageContextUrl(desc.getImageContextUrl());
		}

		if (img.getAssociatedRoi() != null){
			bean.setAssociatedRoi(getRoiId(img.getAssociatedRoi().getEl(),datasource));
			// Need to copy some fields for search purposes
			bean = copyFieldsFromRoi(img, bean);
		}

		if (img.getAssociatedChannel() != null){
			bean.setAssociatedChannel(getChannelId(img.getAssociatedChannel().getEl(), datasource));
			// Need to copy some fields for search purposes
			bean = copyFieldsFromChannel(img, bean);
		}
		
		if (desc.getImageDimensions().getImageDepth() != null){
			bean.setDepth(desc.getImageDimensions().getImageDepth());
		}

		if (desc.getThumbnailUrl() != null){
			bean.setThumbnailUrl(desc.getThumbnailUrl());
		} else {
			bean.setThumbnailUrl(desc.getImageUrl());
		}
				
		bean.setHeight(desc.getImageDimensions().getImageHeight());

		bean.setWidth(desc.getImageDimensions().getImageWidth());

		if (desc.getImagingMethod() != null){
			for (Annotation im: desc.getImagingMethod().getEl()){
				OntologyObject oo = ou.getOntologyTermById(im.getOntologyTerm().getTermId().trim());
				bean.addImagingMethodId(oo.getId());
				bean.addImagingMethodLabel(oo.getLabel()); 
				bean.addImagingMethodSynonyms(oo.getSynonyms());
				bean.addImagingMethodAncestors(oo.getAncestorsBag());
				if (im.getAnnotationFreetext() != null && !im.getAnnotationFreetext().equalsIgnoreCase("")){
					bean.addImagingMethodFreetext(im.getAnnotationFreetext());
				}
			}
		}
		
		if (desc.getSamplePreparation() != null){
			for (Annotation sp: desc.getSamplePreparation().getEl()){
				if (sp.getOntologyTerm() != null){
					OntologyObject oo = ou.getOntologyTermById(sp.getOntologyTerm().getTermId().trim());
					bean.addSamplePreparationId(oo.getId());
					bean.addSamplePreparationLabel(oo.getLabel());
					bean.addSamplePreparationSynonyms(oo.getSynonyms());
					bean.addSamplePreparationAncestors(oo.getAncestorsBag());
				}
				if (sp.getAnnotationFreetext() != null && !sp.getAnnotationFreetext().equalsIgnoreCase("")){
					bean.addSamplePreparationFreetext(sp.getAnnotationFreetext());
				}
			}
		}
		
		if (desc.getMachine() != null){
			bean.setMachine(desc.getMachine());			
		}

		bean.setSampleType(desc.getSampleType().name());
		ArrayList<String> imageType = new ArrayList<>();
		for (ImageType it : desc.getImageType().getEl()){
			if (!imageType.contains(it.name())){
				imageType.add(it.name());
			}
		}
		
		bean.setImageType(imageType);		

		// Sample
		
		Organism org = img.getOrganism();
		
		if (org.getAge() != null){
				bean.setAge(org.getAge());
		}

		if (org.getNcbiTaxonId() != null){
			bean.setNcbiTaxonId(org.getNcbiTaxonId());
		}

		if (org.getSex() != null){
			bean.setSex(org.getSex().name());
		}

		if (org.getStage() != null){
			OntologyObject oo = ou.getOntologyTermById(org.getStage().getTermId());
			if (oo != null){
				bean.setStage(oo.getLabel());
				bean.setStageId(oo.getId());
				bean.addStageAncestors(oo.getAncestorsBag());
			}
		}
	
		// field name="anatomy_computed_id" /-->
		// field name="anatomy_computed_term" /-->
		// field name="anatomy_ann_bag" /-->
		// field name="other_ann_bag" /-->
		// field name="phenotype_ann_bag" /-->

		if (img.getObservations() != null){
			bean.setObservations(img.getObservations().getEl());
		}

		if (img.getConditions() != null ){
			bean.setConditions(img.getConditions().getEl());
		}
		
		// genetic features -->

		if (img.getMutantGenotypeTraits() != null){
			ArrayList<String> geneIds = new ArrayList<>();
			ArrayList<String> geneSymbols = new ArrayList<>();
			ArrayList<String> gfEnsembl = new ArrayList<>();
			ArrayList<String> gfIds = new ArrayList<>();
			ArrayList<String> gfSymbols = new ArrayList<>();
			ArrayList<String> chromosome = new ArrayList<>();
			ArrayList<Long> startPosition = new ArrayList<>();
			ArrayList<Long> endPosition = new ArrayList<>();
			ArrayList<String> strand = new ArrayList<>();
			ArrayList<String> zygosity = new ArrayList<>();
			List<String> genomeAssembly = new ArrayList<>();
			
			for (GenotypeComponent g : img.getMutantGenotypeTraits().getEl()){
				// We need to fill all of these arrays because they need to be parallel
				geneIds.add(g.getGeneId());
				geneSymbols.add(g.getGeneSymbol());
				//TODO add synonyms and names from ENSEMBL
				gfEnsembl.add(g.getGeneticFeatureEnsemblId());
				gfIds.add(g.getGeneticFeatureId());
				gfSymbols.add(g.getGeneSymbol());
				if (g.getGenomicLocation() != null){
					chromosome.add(g.getGenomicLocation().getChromosone());
					startPosition.add(g.getGenomicLocation().getStartPos());
					endPosition.add(g.getGenomicLocation().getEndPos());
					strand.add(g.getGenomicLocation().getStrand());
				}
				if (g.getZygosity() != null){
					zygosity.add(g.getZygosity().name());
				}
				if (g.getMutationType() != null){
					bean.addMutationType(g.getMutationType().getAnnotationFreetext());
				}
				if (g.getGenomeAssembly() != null){
					genomeAssembly.add(g.getGenomeAssembly());
				}
			}
			
			bean.setGeneIds(geneIds);
			bean.setGeneSymbols(geneSymbols);
			bean.setGeneticFeatureIds(gfIds);
			bean.setGenetifFeatureEnsemlIds(gfEnsembl);
			bean.setGeneticFeatureSymbols(gfSymbols);
			
			bean.setChromosome(chromosome);
			bean.setStartPosition(startPosition);
			bean.setEndPosition(endPosition);
			bean.setStrand(strand);
			bean.setZygosity(zygosity);
			bean.setGenomeAssembly(genomeAssembly);
			
		}

		return bean;
	}

	/**
	 * 
	 * @param img
	 * @return The passed image with added annotations copied from the ROI
	 */
	private ImageDTO copyFieldsFromRoi(Image img, ImageDTO pojo){
		
		ImageDTO res = pojo;

		ArrayList<String> phenotypeFreetext = new ArrayList<>();
		ArrayList<String> phenotypeIds = new ArrayList<>();
		ArrayList<String> phenotypeLabels = new ArrayList<>();
		ArrayList<String> depictedAnatomyFreetext = new ArrayList<>();
		ArrayList<String> depictedAnatomyIds = new ArrayList<>();
		ArrayList<String> depictedAnatomyLabels = new ArrayList<>();
		ArrayList<String> expressionInAnatomyFreetext = new ArrayList<>();
		ArrayList<String> expressionInAnatomyIds = new ArrayList<>();
		ArrayList<String> expressionInAnatomyLabels = new ArrayList<>();
		ArrayList<String> abnormalityInAnatomyFreetext = new ArrayList<>();
		ArrayList<String> abnormalityInAnatomyIds = new ArrayList<>();
		ArrayList<String> abnormalityInAnatomyLabels = new ArrayList<>();
		ArrayList<String> observations = new ArrayList<>();
		
		// For all associated ROIs, check available annotations and copy them as needed in the bag fields
		for (String roiId : img.getAssociatedRoi().getEl()){
			
			Roi roi = roiIdMap.get(roiId);		
			
			// phenotype annotations
			if (roi.getPhenotypeAnnotations() != null){
				for (Annotation ann: roi.getPhenotypeAnnotations().getEl()){
					if (ann.getAnnotationFreetext() != null){
						phenotypeFreetext.add(ann.getAnnotationFreetext());
					}
					if (ann.getOntologyTerm() != null){
						phenotypeIds.add(ann.getOntologyTerm().getTermId());
						OntologyObject oo = ou.getOntologyTermById(ann.getOntologyTerm().getTermId().trim());
						phenotypeLabels.add(oo.getLabel());
						if (oo == null){
							System.out.println("Ontology id not found in hash!! -> " + ann.getOntologyTerm().getTermId().trim());
						}
						res.addPhenotypeSynonymsBag(oo.getSynonyms());
						for (OntologyObject anc : oo.getIntermediateTerms()){
							res.addPhenotypeAncestors(anc.getId());
							res.addPhenotypeAncestors(anc.getSynonyms());
							res.addPhenotypeAncestors(anc.getLabel());
						}
						// add MAs from MP -> computedAnatomyTerms
						List<String> maIds =ou.getMaFromMp(oo.getId());
						for (String maId : maIds){
							OntologyObject maOo= ou.getOntologyTermById(maId);
							res.addAnatomyComputedIdBag(maOo.getId());
							res.addAnatomyComputedLabelBag(oo.getLabel());
							res.addAnatomyComputedSynonymsBag(maOo.getSynonyms());
							res.addAnatomyComputedAncestors(oo.getAncestorsBag());
						}
					}
				}
			}
			
			// depicted anatomy annotations
			// expression in anatomy annotation 
			if (roi.getDepictedAnatomicalStructure() != null){
				
				boolean expression = false;
				if (roi.getAssociatedChannel() != null){
					for (String channelId : roi.getAssociatedChannel().getEl()){
						if (channelIdMap.get(channelId).getDepictsExpressionOf() != null ){
							expression = true;
							break;
						}
					}
				}
				
				for (Annotation ann: roi.getDepictedAnatomicalStructure().getEl()){
					if (ann.getAnnotationFreetext() != null){
						if (expression){
							expressionInAnatomyFreetext.add(ann.getAnnotationFreetext());
						}
						else {
							depictedAnatomyFreetext.add(ann.getAnnotationFreetext());
						}
					}
					if (ann.getOntologyTerm() != null){
						if (expression){
							OntologyObject oo = ou.getOntologyTermById(ann.getOntologyTerm().getTermId());
							expressionInAnatomyIds.add(oo.getId());
							expressionInAnatomyLabels.add(oo.getLabel());
							res.addExpressionInSynonymsBag(oo.getSynonyms());
							for (OntologyObject anc : oo.getIntermediateTerms()){
								res.addExpressionInAncestors(anc.getId());
								res.addExpressionInAncestors(anc.getSynonyms());
								res.addExpressionInAncestors(anc.getLabel());
							}
						}
						else{
							OntologyObject oo = ou.getOntologyTermById(ann.getOntologyTerm().getTermId().replace(":", "_"));
							if (oo == null){
								System.out.println("Ontology id not found : " + ann.getOntologyTerm().getTermId());
							} else {
								depictedAnatomyIds.add(oo.getId());
								depictedAnatomyLabels.add(oo.getLabel());
								res.addDepictedAnatomySynonymsBag(oo.getSynonyms());
								for (OntologyObject anc : oo.getIntermediateTerms()){
									res.addDepictedAnatomyAncestors(anc.getId());
									res.addDepictedAnatomyAncestors(anc.getSynonyms());
									res.addDepictedAnatomyAncestors(anc.getLabel());
								}
							}
						}
					}
				}
			}
			
			//anatomy with abnormality
			if (roi.getAbnormalityInAnatomicalStructure() != null){
				for (Annotation ann: roi.getAbnormalityInAnatomicalStructure().getEl()){
					if (ann.getAnnotationFreetext() != null){
						abnormalityInAnatomyFreetext.add(ann.getAnnotationFreetext());
					}
					if (ann.getOntologyTerm() != null){
						abnormalityInAnatomyIds.add(ann.getOntologyTerm().getTermId());
						OntologyObject oo = ou.getOntologyTermById(ann.getOntologyTerm().getTermId());
						abnormalityInAnatomyLabels.add(oo.getLabel());
						res.addAbnormalAnatomySynonymsBag(oo.getSynonyms());
						for (OntologyObject anc : oo.getIntermediateTerms()){
							res.addAbnormalAnatomyAncestors(anc.getId());
							res.addAbnormalAnatomyAncestors(anc.getSynonyms());
							res.addAbnormalAnatomyAncestors(anc.getLabel());
						}
					}
				}
			}
			
			// observations
			if (roi.getObservations() != null){
				for (String obs : roi.getObservations().getEl()){
					observations.add(obs);
				}
			}
		}
		
		res.setDepictedAnatomyFreetextBag(depictedAnatomyFreetext);
		res.setDepictedAnatomyIdBag(depictedAnatomyIds);
		res.setDepictedAnatomyTermBag(depictedAnatomyLabels);
//		res.setAnatomyComputedIdBag(anatomyComputedIdBag);
//		res.setAnatomyComputedLabelBag(anatomyComputedLabelBag);
		
		res.setAbnormalAnatomyFreetextBag(abnormalityInAnatomyFreetext);
		res.setAbnormalAnatomyIdBag(abnormalityInAnatomyIds);
		res.setAbnormalAnatomyTermBag(abnormalityInAnatomyLabels);
		
		// TODO get this from channel
//		res.setExpressedGfIdBag(expressedGfIdBag);
//		res.setExpressedGfSymbolBag(expressedGfSymbolBag);
		
		res.setExpressionInFreetextBag(expressionInAnatomyFreetext);
		res.setExpressionInIdBag(expressionInAnatomyIds);
		res.setExpressionInLabelBag(expressionInAnatomyLabels);
		
		res.setPhenotypeFreetextBag(phenotypeFreetext);
		res.setPhenotypeIdBag(phenotypeIds);
		res.setPhenotypeLabelBag(phenotypeLabels);
		
		res.setObservationBag(observations);
		
		return res;
	}
	
	
	private ImageDTO copyFieldsFromChannel(Image img, ImageDTO pojo){
		
		ImageDTO res = pojo;

		ArrayList<String> expressedGfIdBag = new ArrayList<>();
		ArrayList<String> expressedGfLabelBag = new ArrayList<>();
		
		//TODO fill gf synonyms and name from ENSEMBL
		
		for (String channelId : img.getAssociatedChannel().getEl()){
			
			Channel channel = channelIdMap.get(channelId);		
			if (channel.getVisualisationMethod() != null){
				for (Annotation vm: channel.getVisualisationMethod().getEl()){
					OntologyObject oo = ou.getOntologyTermById(vm.getOntologyTerm().getTermId());
					if (oo != null){
						res.addVisualisationMethodId(oo.getId());
						res.addVisualisationMethodLabel(oo.getLabel());
						res.addVisualisationMethodSynonyms(oo.getSynonyms());
						res.addVisualisationMethodAncestors(oo.getAncestorsBag());
					}
					String freetext = vm.getAnnotationFreetext();
					if (freetext != null && !freetext.equals("")){
						res.addVisualisationMethodFreetext(freetext);
					}
				}
			}
			
			// expressed features
			if (channel.getDepictsExpressionOf() != null){
				GenotypeComponent gf = channel.getDepictsExpressionOf();
				if (gf.getGeneId() != null){
					expressedGfIdBag.add(gf.getGeneId());
				}
				if (gf.getGeneSymbol() != null){
					expressedGfLabelBag.add(gf.getGeneSymbol());
				}
				if (gf.getGeneticFeatureId() != null){
					expressedGfIdBag.add(gf.getGeneticFeatureId());
				}
				if (gf.getGeneticFeatureEnsemblId() != null){
					expressedGfIdBag.add(gf.getGeneticFeatureEnsemblId());
				}
				if (gf.getGeneticFeatureSymbol() != null){
					expressedGfLabelBag.add(gf.getGeneticFeatureSymbol());
				}	
				if (gf.getZygosity() != null){
					res.addZygosity(gf.getZygosity().name());
				}
				if (gf.getMutationType() != null){
					res.addMutationType(gf.getMutationType().getAnnotationFreetext());
				}
				if (gf.getGenomicLocation() != null){
					
					res.addStartPosition(gf.getGenomicLocation().getStartPos());
					
					if (gf.getGenomicLocation().getEndPos() != null){
						res.addEndPosition(gf.getGenomicLocation().getEndPos());
					}
					res.addChromosome(gf.getGenomicLocation().getChromosone());
					res.addStrand(gf.getGenomicLocation().getStrand());
				}
			}
			
		}

		res.setExpressedGfIdBag(expressedGfIdBag);
		res.setExpressedGfSymbolBag(expressedGfLabelBag);
		return res;
	}
	
	
	boolean validateAgainstXSD(InputStream xml, InputStream xsd) {

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(xsd));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
			return true;
		} catch (SAXException e) {
			System.out.println("NOT valid");
			System.out.println("Reason: " + e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}


	boolean checkInformation(Doc doc) {

		imageIdMap = new HashMap<>();
		channelIdMap = new HashMap<>();
		roiIdMap = new HashMap<>();

		// Create maps <id, Object> for quick reference
		for (Image img : doc.getImage()) {
			imageIdMap.put(img.getId(), img);
		}
		for (Roi roi : doc.getRoi()) {
			roiIdMap.put(roi.getId(), roi);
		}
		for (Channel ch : doc.getChannel()) {
			channelIdMap.put(ch.getId(), ch);
		}

		// Check associated image/channel/roi ids are valid a) they exist , b)
		// the link is reflezive
		boolean res = checkIdsReferenceExistingObjects();
		if (!res) { return false; }

		for (Image img : imageIdMap.values()) {
			if (!vu.hasValidOntologyTerms(img)) {
				System.out.println("there was something wrong with the ontology terms for img id = " + img.getId());
			}
			if (!vu.hasPositieDimensions(img.getImageDescription().getImageDimensions())) {
				System.out.println("Dimensions are not positive! Validation failed.");
				return false;
			}
		}

		for (Roi roi : roiIdMap.values()) {

			if (!vu.arePercentagesOk(roi.getCoordinates())) { return false; }
			if (!vu.hasValidOntologyTerms(roi)) {
				System.out.println("there was something wrong with the ontology terms for roi id = " + roi.getId());
				return false;
			}
		}
		return true;
	}


	private boolean checkIdsReferenceExistingObjects() {

		// Associated roi & channel for image really exist
		for (Image img : imageIdMap.values()) {
			if (img.getAssociatedRoi() != null) {
				for (String roiId : img.getAssociatedRoi().getEl()) {
					if (!roiIdMap.containsKey(roiId)) {
						System.out.println("roi id referenced without existing in image id = " + img.getId());
						return false;
					}
				}
			}
			if (img.getAssociatedChannel() != null) {
				for (String channelId : img.getAssociatedChannel().getEl()) {
					if (!channelIdMap.containsKey(channelId)) {
						System.out.println("channel id referenced without existing in image id = " + img.getId());
						return false;
					}
				}
			}
		}

		// Associated roi & image for channels really exist
		for (Channel channel : channelIdMap.values()) {
			if (channel.getAssociatedRoi() != null) {
				for (String roiId : channel.getAssociatedRoi().getEl()) {
					if (!roiIdMap.containsKey(roiId)) {
						System.out.println("roi id referenced without existing in channel id = " + channel.getId());
						return false;
					}
				}
			}
			if (channel.getAssociatedImage() != null) {
				if (!imageIdMap.containsKey(channel.getAssociatedImage())) {
					System.out.println("image id referenced without existing in channel id = " + channel.getId());
					return false;
				}
			}
		}

		// Associated image & channel for roi really exist
		for (Roi roi : roiIdMap.values()) {
			if (roi.getAssociatedChannel() != null) {
				for (String channelId : roi.getAssociatedChannel().getEl()) {
					if (!channelIdMap.containsKey(channelId)) {
						System.out.println("channel id referenced without existing in roi id = " + roi.getId());
						return false;
					}
				}
			}
			if (roi.getAssociatedImage() != null) {
				if (!imageIdMap.containsKey(roi.getAssociatedImage())) {
					System.out.println("image id referenced without existing in roi id = " + roi.getId());
					return false;
				}
			}
		}
		return true;
	}


	private Doc convertXmlToObjects(String xmlFullPathLocation){

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Doc.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			System.out.println(">>>>>>" + xmlFullPathLocation + "  " + (jaxbUnmarshaller != null));
			Doc doc = (Doc) jaxbUnmarshaller.unmarshal(new FileInputStream(xmlFullPathLocation));
			return doc;
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private String getImageId(String id, String datasource){
		return datasource.replaceAll(" ", "_") + "_" + id.replaceAll(":", "_");
	}
	
	
	private String getChannelId(String id, String datasource){
		return datasource.replaceAll(" ", "_") + "_channel_" + id.replaceAll(":", "_");
	}
	
	
	private List<String> getChannelId(List<String> ids, String datasource){
		
		List<String> newIds = new ArrayList<>();
		for (String id: ids){
			newIds.add(getChannelId(id, datasource));
		}
		
		return newIds;
	}
	
	
	private String getRoiId(String id, String datasource){
		
		return datasource.replaceAll(" ", "_") + "_roi_" + id.replaceAll(":", "_");
		
	}
	
	
	private List<String> getRoiId(List<String> ids, String datasource){
		
		List<String> newIds = new ArrayList<>();
		for (String id: ids){
			newIds.add(getRoiId(id, datasource));
		}
		
		return newIds;
		
	}
}

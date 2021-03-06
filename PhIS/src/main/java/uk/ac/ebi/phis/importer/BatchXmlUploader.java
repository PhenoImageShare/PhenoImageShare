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

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import uk.ac.ebi.phis.dto.solrj.ChannelDTO;
import uk.ac.ebi.phis.dto.solrj.ImageDTO;
import uk.ac.ebi.phis.dto.solrj.RoiDTO;
import uk.ac.ebi.phis.exception.PhenoImageShareException;
import uk.ac.ebi.phis.jaxb.*;
import uk.ac.ebi.phis.release.DatasourceInstance;
import uk.ac.ebi.phis.release.OntologyInstance;
import uk.ac.ebi.phis.service.ChannelService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.service.RoiService;
import uk.ac.ebi.phis.utils.ValidationUtils;
import uk.ac.ebi.phis.utils.ontology.OntologyGroups;
import uk.ac.ebi.phis.utils.ontology.OntologyObject;
import uk.ac.ebi.phis.utils.ontology.OntologyUtils;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class BatchXmlUploader {

	HashMap<String, Image> imageIdMap = new HashMap<>();
	HashMap<String, Channel> channelIdMap = new HashMap<>();
	HashMap<String, Roi> roiIdMap = new HashMap<>();

	ClassLoader classloader;

	ValidationUtils vu ;
	OntologyUtils ou;

	@Autowired
	ImageService is;

	@Autowired
	RoiService rs;

	@Autowired
	ChannelService cs;

	OntologyGroups ontologyGroups = new OntologyGroups();


	public BatchXmlUploader(){

		classloader = Thread.currentThread().getContextClassLoader();
		vu = new ValidationUtils();

	}

	/**
	 *
	 * @param is
	 * @param rs
	 * @param cs
	 * @param useOls can only use OLS to validate the fields. Not all methods implemented to index data into Solr.
	 */
	public BatchXmlUploader(ImageService is, RoiService rs, ChannelService cs, boolean useOls) {

		classloader = Thread.currentThread().getContextClassLoader();
		this.is = is;
		this.rs = rs;
		this.cs = cs;
		if (!useOls) {
			this.ou = new OntologyUtils();
			vu = new ValidationUtils(ou);
		} else {
			vu = new ValidationUtils();
		}

	}


	public List<OntologyInstance> getontologyInstances (){

		return ou.getOntologyInstances();

	}


	public Doc validate(String xmlLocation, Boolean strict) {

		InputStream xsd;
		InputStream xml;
		boolean isValid = false;
		// Unmarshal XML
		Doc doc = convertXmlToObjects(xmlLocation);

		try {

			xsd = classloader.getResourceAsStream("phisSchema.xsd");
			xml = new FileInputStream(xmlLocation);
			try {
				isValid = validateAgainstXSD(xml, xsd);
			} catch (PhenoImageShareException e){
				e.printStackTrace();
				isValid = false;
			}
			xsd.close();
			xml.close();
			isValid = (isValid && checkInformation(doc, strict));

		} catch (IOException | PhenoImageShareException e) {
			e.printStackTrace();
		}

		return isValid? doc : null;
	}


	public DatasourceInstance index(Doc doc , Integer datasourceId)
			throws IOException, SolrServerException, ParseException {

		addImageDocuments(doc.getImage(), datasourceId);
		addRoiDocuments(doc.getRoi(), datasourceId);
		addChannelDocuments(doc.getChannel(), datasourceId);

		DatasourceInstance dataSource = new DatasourceInstance();
		dataSource.setExportDate(doc.getExportDate());
		dataSource.setName(doc.getImage().get(0).getImageDescription().getHost().getDisplayName());

		return dataSource;
	}


	private void addImageDocuments(List<Image> images, Integer datasourceId)
			throws IOException, SolrServerException {

		System.out.println(is == null);
		for (Image img : images) {
			is.addBean(fillPojo(img, datasourceId));
		}
	}


	private void addRoiDocuments(List<Roi> rois, Integer datasourceId)
			throws IOException, SolrServerException {

		System.out.println("rois list is " + rois.size());
		for (Roi roi : rois) {
			rs.addBean(fillPojo(roi, datasourceId));
		}
	}


	private void addChannelDocuments(List<Channel> channels, Integer datasourceId)
			throws IOException, SolrServerException {

		System.out.println("channel list is " + channels.size());
		for (Channel channel : channels) {
			cs.addBean(fillPojo(channel, datasourceId));
		}
	}


	private RoiDTO fillPojo(Roi roi, Integer datasourceId){

		RoiDTO bean = new RoiDTO();

		bean.setId(getRoiId(roi.getId(), datasourceId));

		bean.setAssociatedImage(getImageId(roi.getAssociatedImage(), datasourceId));

		if (roi.getAssociatedChannel() != null){
			bean.setAssociatedChannel(getChannelId(roi.getAssociatedChannel().getEl(), datasourceId));
		}

		if (roi.getDepictedAnatomicalStructure() != null){
			// Expression in anatomy
			for ( ExpressionAnnotation ann: roi.getDepictedAnatomicalStructure().getEl()){
				String expressionConcat = "";
				if (ann.getAnnotationFreetext() != null && ann.getAnnotationFreetext().length() > 0){
					bean.addExpressedAnatomyFreetext(ann.getAnnotationFreetext());
					expressionConcat += ann.getAnnotationFreetext() + " " ;
				}
				if (ann.getOntologyTerm() != null){
					OntologyObject oo = ou.getOntologyTermById(ann.getOntologyTerm().getTermId());
					if(ou!= null) {
						bean.addExpressedAnatomyId(oo.getId());
						bean.addExpressedAnatomyTerm(oo.getLabel());
						expressionConcat += oo.getId() + " " + oo.getLabel() + " ";
					} else {
						System.out.println("Null pointer for " + ann.getOntologyTerm().getTermId());
					}
				}
				if (ann.getExpressionValue() != null && ann.getExpressionValue().length() > 0){
					bean.addExpressionValue(ann.getExpressionValue());
					expressionConcat += ann.getExpressionValue();
					bean.addExpressionConcat(expressionConcat);
				}
			}
		}

		if (roi.getAbnormalityInAnatomicalStructure() != null){

			// Abnormality in anatomical part
			for ( Annotation ann: roi.getAbnormalityInAnatomicalStructure().getEl()){
				if (ann.getAnnotationFreetext() != null){
					bean.addAbnormalityAnatomyFreetext(ann.getAnnotationFreetext());
				}
				if (ann.getOntologyTerm() != null){
					OntologyObject oo = ou.getOntologyTermById(ann.getOntologyTerm().getTermId());
					if (ann.getAnnotationMode() != null && (ann.getAnnotationMode() == AnnotationMode.AUTOMATED)){
						bean.addComputedAbnormalityAnatomyId(oo.getId());
						bean.addComputedAbnormalityAnatomyTerm(oo.getLabel());
					}
					else {
						bean.addAbnormalityAnatomyId(oo.getId());
						bean.addAbnormalityAnatomyTerm(oo.getLabel());
					}
				}
			}
		}

		if (roi.getEditDate() != null){
			bean.setEditDate(roi.getEditDate().normalize().toGregorianCalendar().getTime());
		}
		if (roi.getCreationDate() != null){
			bean.setCreationDate(roi.getCreationDate().normalize().toGregorianCalendar().getTime());
		}

		if (roi.getPhenotypeAnnotations() != null){
			// Phenotypes
			for ( Annotation ann: roi.getPhenotypeAnnotations().getEl()){
				if (ann.getAnnotationFreetext() != null){
					bean.addPhenotypeFreetext(ann.getAnnotationFreetext());
				}
				if (ann.getOntologyTerm() != null){
					OntologyObject obj = ou.getOntologyTermById(ann.getOntologyTerm().getTermId());
					if (obj != null) {
						bean.addPhenotypeId(obj.getId());
						bean.addPhenotypeTerm(obj.getLabel());
					} else {
						System.out.println("Bad id " + ann.getOntologyTerm().getTermId());
					}
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


	private ChannelDTO fillPojo(Channel channel, Integer datasourceId) {

		ChannelDTO bean = new ChannelDTO();

		bean.setId(getChannelId(channel.getId(), datasourceId));
		bean.setAssociatedImage(getImageId(channel.getAssociatedImage(), datasourceId));
		if (channel.getAssociatedRoi() != null){
			bean.setAssociatedRoi(getRoiId(channel.getAssociatedRoi().getEl(), datasourceId));
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
		if (channel.getVisualisationMethod() != null){
			for (Annotation vm: channel.getVisualisationMethod().getEl()){
				OntologyObject oo = ou.getOntologyTermById(vm.getOntologyTerm().getTermId().trim());
				if (oo != null){
					bean.addVisualisationMethodId(oo.getId());
					bean.addVisualisationMethodLabel(oo.getLabel());
					bean.addVisualisationMethodSynonyms(oo.getSynonyms());
				}
				String freetext = vm.getAnnotationFreetext();
				if (freetext != null && !freetext.equals("")){
					bean.addVisualisationMethodFreetext(freetext);
				}
			}
		}
		return bean;
	}


	private OntologyGroups.Species getSpecies(String taxon){

		if (taxon.toLowerCase().contains("mus musculus") || taxon.toLowerCase().contains("mouse")){
			return OntologyGroups.Species.MUS_MUSCULUS;
		} else if (taxon.toLowerCase().contains("homo sapiens") || taxon.toLowerCase().contains("human")){
			return OntologyGroups.Species.HOMO_SAPIENS;
		} else if (taxon.toLowerCase().contains("drosophila")){
			return OntologyGroups.Species.DROSOPHILA_MELANOGASTER;
		}

		return null;

	}


	private ImageDTO fillPojo(Image img, Integer datasourceId) {

		ImageDTO bean = new ImageDTO();
		bean.setTaxon(img.getOrganism().getTaxon());
		bean.setAnatomyDefaultOntologies(ontologyGroups.getDefaultOntologies(getSpecies(img.getOrganism().getTaxon()) , OntologyGroups.Subjects.ANATOMY));
		bean.setPhenotypeDefaultOntologies(ontologyGroups.getDefaultOntologies( getSpecies(img.getOrganism().getTaxon()), OntologyGroups.Subjects.PHENOTYPE));
		bean.setId(getImageId(img.getId(), datasourceId));

		if (img.getOrganism().getBackgroundStrain() != null){
			bean.setBackgroundStrain(img.getOrganism().getBackgroundStrain().getEl());
		}

		if (img.getOrganism().getGroup() != null){
			Group group = img.getOrganism().getGroup();
			if (group.getProjectId() != null){
				bean.setDynamicGroups("project_group", group.getProjectId());
			}
			if (group.getColonyId() != null){
				bean.setDynamicGroups("colony_group", group.getColonyId());
			}
			if (group.getExperimentId() != null){
				bean.setDynamicGroups("experiment_group", group.getExperimentId());
			}
			if (group.getSbId() != null){
				bean.setDynamicGroups("sb_group", group.getSbId());
			}
			if (group.getOther() != null){
				bean.setDynamicGroups("other_group", group.getOther());
			}
		}

		if (img.getProjectAnnotations() != null){
			if (img.getProjectAnnotations().getParameter() != null){
				bean.setParameter(img.getProjectAnnotations().getParameter());
			}
			if (img.getProjectAnnotations().getProcedure() != null){
				bean.setProcedure(img.getProjectAnnotations().getProcedure());
			}
			if (img.getProjectAnnotations().getPipeline() != null){
				bean.setPipeline(img.getProjectAnnotations().getPipeline());
			}
		}

		if (img.getOrganism().getOrganismId() != null){
			bean.setOrganismId(img.getOrganism().getOrganismId());
		}

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
			for ( Link link : desc.getPublication().getEl()){
				if (link.getUrl() != null){
					bean.addPublicationUrl(link.getUrl().trim());
				}
				if (link.getDetails() != null){
					bean.addPublicationDescription(link.getDetails().trim());
				}
				if (link.getDisplayName() != null){
					bean.addPublicationName(link.getDisplayName().trim());
				}
			}
		}

		if (desc.getImageContextUrl() != null){
			bean.setImageContextUrl(desc.getImageContextUrl());
		}

		if (img.getAssociatedRoi() != null){
			bean.setAssociatedRoi(getRoiId(img.getAssociatedRoi().getEl(), datasourceId));
			// Need to copy some fields for search purposes
			bean = copyFieldsFromRoi(img, bean);
		}

		if (img.getAssociatedChannel() != null){
			bean.setAssociatedChannel(getChannelId(img.getAssociatedChannel().getEl(), datasourceId));
			// Need to copy some fields for search purposes
			bean = copyFieldsFromChannel(img, bean);
		}

		if (desc.getImageDimensions() != null && desc.getImageDimensions().getImageDepth() != null){
			bean.setDepth(desc.getImageDimensions().getImageDepth());
		}

		if (desc.getThumbnailUrl() != null){
			bean.setThumbnailUrl(desc.getThumbnailUrl());
		} else {
			bean.setThumbnailUrl(desc.getImageUrl());
		}

		if (desc.getImageDimensions() != null) {
			bean.setHeight(desc.getImageDimensions().getImageHeight());
			bean.setWidth(desc.getImageDimensions().getImageWidth());
		}

		if (desc.getImagingMethod() != null){
			for (Annotation im: desc.getImagingMethod().getEl()){
				OntologyObject oo = ou.getOntologyTermById(im.getOntologyTerm().getTermId().trim());
				if (oo == null){
					System.out.println("Ontology id not found : " + im.getOntologyTerm().getTermId());
				} else {
					bean.addImagingMethodId(oo.getId());
					bean.addImagingMethodLabel(oo.getLabel());
					bean.addImagingMethodSynonyms(oo.getSynonyms());
					bean.addImagingMethodAncestors(oo.getAncestorsBag());
					if (im.getAnnotationFreetext() != null && !im.getAnnotationFreetext().equalsIgnoreCase("")){
						bean.addImagingMethodFreetext(im.getAnnotationFreetext());
					}
				}
			}
		}

		if (desc.getSamplePreparation() != null){
			for (Annotation sp: desc.getSamplePreparation().getEl()){
				if (sp.getOntologyTerm() != null){
					OntologyObject oo = ou.getOntologyTermById(sp.getOntologyTerm().getTermId().trim());
					if (oo == null){
						System.out.println("Ontology id not found : " + sp.getOntologyTerm().getTermId());
					} else {
						bean.addSamplePreparationId(oo.getId());
						bean.addSamplePreparationLabel(oo.getLabel());
						bean.addSamplePreparationSynonyms(oo.getSynonyms());
						bean.addSamplePreparationAncestors(oo.getAncestorsBag());
					}
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
				if (oo.getFacetTerms() == null || oo.getFacetTerms().size() == 0){
					System.out.println("No facet terms for " + oo.getLabel() + " " + oo.getId());
				} else {
					for (OntologyObject facetOo: oo.getFacetTerms()){
						bean.addStageFacet(facetOo.getLabel());
					}
				}
			}
		}

		// field name="anatomy_computed_id" 
		// field name="anatomy_computed_term" 
		// field name="anatomy_ann_bag" 
		// field name="other_ann_bag"
		// field name="phenotype_ann_bag" 

		if (img.getObservations() != null){
			bean.setObservations(img.getObservations().getEl());
		}

		if (img.getConditions() != null ){
			bean.setConditions(img.getConditions().getEl());
		}

		// genetic features 

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
						if (oo == null){
							System.out.println("Ontology id not found in hash!! -> " + ann.getOntologyTerm().getTermId().trim());
						} else {
							phenotypeLabels.add(oo.getLabel());
							res.addPhenotypeSynonymsBag(oo.getSynonyms());
							for (OntologyObject anc : oo.getIntermediateTerms()) {
								res.addPhenotypeAncestors(anc.getId());
								res.addPhenotypeAncestors(anc.getSynonyms());
								res.addPhenotypeAncestors(anc.getLabel());
							}
							// add MAs from MP -> computedAnatomyTerms
							List<String> maIds = ou.getMaFromMp(oo.getId());
							for (String maId : maIds) {
								OntologyObject maOo = ou.getOntologyTermById(maId);
								res.addAnatomyComputedIdBag(maOo.getId());
								res.addAnatomyComputedLabelBag(maOo.getLabel());
								res.addAnatomyComputedSynonymsBag(maOo.getSynonyms());
								res.addAnatomyComputedAncestors(maOo.getAncestorsBag());
							}
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

				for (ExpressionAnnotation ann: roi.getDepictedAnatomicalStructure().getEl()){
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
							if (oo == null){
								System.out.println("Ontology id not found : " + ann.getOntologyTerm().getTermId());
							} else {
								expressionInAnatomyIds.add(oo.getId());
								expressionInAnatomyLabels.add(oo.getLabel());
								res.addExpressionInSynonymsBag(oo.getSynonyms());
								for (OntologyObject anc : oo.getIntermediateTerms()){
									res.addExpressionInAncestors(anc.getId());
									res.addExpressionInAncestors(anc.getSynonyms());
									res.addExpressionInAncestors(anc.getLabel());
								}
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
					OntologyObject oo = ou.getOntologyTermById(vm.getOntologyTerm().getTermId().trim());
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
					res.addGenomeAssembly(gf.getGenomeAssembly());
				}
			}

		}

		res.setExpressedGfIdBag(expressedGfIdBag);
		res.setExpressedGfSymbolBag(expressedGfLabelBag);
		return res;
	}


	public boolean validateAgainstXSD(InputStream xml, InputStream xsd) throws PhenoImageShareException {

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(xsd));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
			return true;
		} catch (SAXException | IOException e) {
			System.out.println("NOT valid for reason: " + e.getLocalizedMessage());
			e.printStackTrace();
			throw new PhenoImageShareException("NOT valid for reason: " + e.getLocalizedMessage());
		}
	}


	/**
	 *
	 * @param doc
	 * @param strict On strict=true ontology term issues will throw an error. Otherwise will just print the issue and continue.
	 * @return
	 * @throws PhenoImageShareException
	 */
	public boolean checkInformation(Doc doc, Boolean strict) throws PhenoImageShareException {

		System.out.println("In checkInformation ......");
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
		if (!res) {
			System.out.println("IDS don\t reference existing objects. ");
			throw new PhenoImageShareException("IDS don't reference existing objects.");
		}

		for (Image img : imageIdMap.values()) {
			if (!vu.hasValidOntologyTerms(img, strict)) {
				System.out.println("there was something wrong with the ontology terms for img id = " + img.getId());
				if (strict) {
					throw new PhenoImageShareException("There was something wrong with the ontology terms for img id = " + img.getId());
				}
			}
			if (img.getImageDescription().getImageDimensions() != null && !vu.hasPositieDimensions(img.getImageDescription().getImageDimensions())) {
				System.out.println("Dimensions are not positive! Validation failed.");
				throw new PhenoImageShareException("Dimensions are not positive! Validation failed.");
			}
		}

		for (Roi roi : roiIdMap.values()) {

			if (!vu.arePercentagesOk(roi.getCoordinates())) { return false; }
			if (!vu.hasValidOntologyTerms(roi, strict)) {
				System.out.println("there was something wrong with the ontology terms for roi id = " + roi.getId());
				if(strict) {
					throw new PhenoImageShareException("There was something wrong with the ontology terms for roi id = " + roi.getId());
				}
			}
		}
		return true;
	}


	private boolean checkIdsReferenceExistingObjects()  throws PhenoImageShareException{

		// Associated roi & channel for image really exist
		for (Image img : imageIdMap.values()) {
			if (img.getAssociatedRoi() != null) {
				for (String roiId : img.getAssociatedRoi().getEl()) {
					if (!roiIdMap.containsKey(roiId)) {
						System.out.println("Roi id referenced without existing in image id = " + img.getId());
						throw new PhenoImageShareException("Roi id referenced without existing in image id = " + img.getId());
					}
				}
			}
			if (img.getAssociatedChannel() != null) {
				for (String channelId : img.getAssociatedChannel().getEl()) {
					if (!channelIdMap.containsKey(channelId)) {
						System.out.println("channel id referenced without existing in image id = " + img.getId());
						throw new PhenoImageShareException("Channel id referenced without existing in image id = " + img.getId());
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
						throw new PhenoImageShareException("Roi id referenced without existing in channel id = " + channel.getId());
					}
				}
			}
			if (channel.getAssociatedImage() != null) {
				if (!imageIdMap.containsKey(channel.getAssociatedImage())) {
					System.out.println("image id referenced without existing in channel id = " + channel.getId());
					throw new PhenoImageShareException("Image id referenced without existing in channel id = " + channel.getId());
				}
			}
		}

		// Associated image & channel for roi really exist
		for (Roi roi : roiIdMap.values()) {
			if (roi.getAssociatedChannel() != null) {
				for (String channelId : roi.getAssociatedChannel().getEl()) {
					if (!channelIdMap.containsKey(channelId)) {
						System.out.println("channel id referenced without existing in roi id = " + roi.getId());
						throw new PhenoImageShareException("Channel id referenced without existing in roi id = " + roi.getId());
					}
				}
			}
			if (roi.getAssociatedImage() != null) {
				if (!imageIdMap.containsKey(roi.getAssociatedImage())) {
					System.out.println("image id referenced without existing in roi id = " + roi.getId());
					throw new PhenoImageShareException("Image id referenced without existing in roi id = " + roi.getId());
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
			return (Doc) jaxbUnmarshaller.unmarshal(new FileInputStream(xmlFullPathLocation));
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


	private String getImageId(String id,  Integer datasourceId){

		return datasourceId + "_" + id.replaceAll(":", "_");

	}


	private String getChannelId(String id, Integer datasourceId){

		String newId = datasourceId + "_channel_" + id.replaceAll(":", "_");
		newId = newId.replace("channel_channel", "channel");
		return newId;

	}


	private List<String> getChannelId(List<String> ids, Integer datasourceId){

		List<String> newIds = new ArrayList<>();
		for (String id: ids){
			newIds.add(getChannelId(id, datasourceId));
		}

		return newIds;
	}


	private String getRoiId(String id, Integer datasourceId){

		String newId = datasourceId + "_roi_" + id.replaceAll(":", "_");
		newId = newId.replace("roi_roi", "roi");
		return newId;

	}


	private List<String> getRoiId(List<String> ids, Integer datasourceId){

		List<String> newIds = new ArrayList<>();
		for (String id: ids){
			newIds.add(getRoiId(id, datasourceId));
		}

		return newIds;

	}

}
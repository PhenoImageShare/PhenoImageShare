package uk.ac.ebi.phis.importer;

import uk.ac.ebi.phis.jaxb.Annotation;
import uk.ac.ebi.phis.jaxb.AnnotationMode;
import uk.ac.ebi.phis.jaxb.Channel;
import uk.ac.ebi.phis.jaxb.Doc;
import uk.ac.ebi.phis.jaxb.GenotypeComponent;
import uk.ac.ebi.phis.jaxb.Image;
import uk.ac.ebi.phis.jaxb.ImageDescription;
import uk.ac.ebi.phis.jaxb.ImageType;
import uk.ac.ebi.phis.jaxb.OntologyTerm;
import uk.ac.ebi.phis.jaxb.Organism;
import uk.ac.ebi.phis.jaxb.Roi;

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

import uk.ac.ebi.phis.service.ChannelService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.service.RoiService;
import uk.ac.ebi.phis.solrj.dto.ChannelDTO;
import uk.ac.ebi.phis.solrj.dto.ImageDTO;
import uk.ac.ebi.phis.solrj.dto.RoiDTO;
import uk.ac.ebi.phis.utils.ValidationUtils;

public class BatchXmlUploader {

	HashMap<String, Image> imageIdMap = new HashMap<>();
	HashMap<String, Channel> channelIdMap = new HashMap<>();
	HashMap<String, Roi> roiIdMap = new HashMap<>();

	ClassLoader classloader;

	
//	String solrImageBaseUrl = "http://localhost:8983/solr/collection1";
//	String solrImageBaseUrl; // = "http://localhost:8086/solr-example/images";

	ValidationUtils vu = new ValidationUtils();

	ImageService is;
	RoiService rs;
	ChannelService cs;
	
	public BatchXmlUploader(ImageService is, RoiService rs, ChannelService cs) {
		classloader = Thread.currentThread().getContextClassLoader();
		this.is = is;
		this.rs = rs;
		this.cs = cs;
	}


	public boolean validateAndUpload(String xmlLocationFullPath) {

		Doc doc;
		// Unmarshal XML
		doc = convertXmlToObjects(xmlLocationFullPath);
		boolean isValid = validate(xmlLocationFullPath, doc);
		try {
			doBatchSubmission(doc);
		} catch (IOException | SolrServerException e) {
			e.printStackTrace();
		}
		return isValid;
	}


	private boolean validate(String xmlLocation, Doc doc) {

		InputStream xsd;
		InputStream xml;
		boolean isValid = false;
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


	private void doBatchSubmission(Doc doc)
	throws IOException, SolrServerException {

		addImageDocuments(doc.getImage());
		addRoiDocuments(doc.getRoi());
		addChannelDocuments(doc.getChannel());
	}


	private void addImageDocuments(List<Image> images)
	throws IOException, SolrServerException {
		
		int i = 0;
		List<ImageDTO> imageDocs = new ArrayList<>();
		for (Image img : images) {
			// add it
			imageDocs.add(fillPojo(img));
			// flush every 1000 docs
			if (i++ % 1000 == 0) {
				is.addBeans(imageDocs);
				imageDocs = new ArrayList<>();
			}
		}
		is.addBeans(imageDocs);
	}

	private void addRoiDocuments(List<Roi> rois)
	throws IOException, SolrServerException {
		
		int i = 0;
		System.out.println("rois list is " + rois.size());
		List<RoiDTO> roiDocs = new ArrayList<>();
		for (Roi roi : rois) {
			// add it
			roiDocs.add(fillPojo(roi));
			// flush every 1000 docs
			if (i++ % 1000 == 0) {
				rs.addBeans(roiDocs);
				roiDocs = new ArrayList<>();
			}
		}
		rs.addBeans(roiDocs);
	}

	private void addChannelDocuments(List<Channel> channels)
	throws IOException, SolrServerException {
		
		int i = 0;
		List<ChannelDTO> chDocs = new ArrayList<>();
		System.out.println("channel list is " + channels.size());
		for (Channel channel : channels) {
			// add it
			chDocs.add(fillPojo(channel));
			// flush every 1000 docs
			if (i++ % 1000 == 0) {
				cs.addBeans(chDocs);
				chDocs = new ArrayList<>();
			}
		}
		cs.addBeans(chDocs);
	}

	// fillRoiPojo
	private RoiDTO fillPojo(Roi roi){
		
		RoiDTO bean = new RoiDTO();
		
		bean.setId(roi.getId());

		bean.setAssociatedImage(roi.getAssociatedImage());
		
		if (roi.getAssociatedChannel() != null){
			bean.setAssociatedChannel(roi.getAssociatedChannel().getEl());
		}
		
		if (roi.getDepictedAnatomicalStructure() != null){
			
			List<String> ids = new ArrayList<>(); // || with labels
			List<String> labels = new ArrayList<>(); // || with ids
			List<String> freetext = new ArrayList<>();
			List<String> computedIds = new ArrayList<>(); // || with computedLabels
			List<String> computedLabels = new ArrayList<>(); // || with computedId
			
			// Depicted anatomy
			// TODO have to check if it's expression (from channel)
			for ( Annotation ann: roi.getDepictedAnatomicalStructure().getEl()){
				if (ann.getAnnotationFreetext() != null){
					freetext.add(ann.getAnnotationFreetext());
				}
				if (ann.getOntologyTerm() != null){
					if (ann.getAnnotationMode() != null && (ann.getAnnotationMode() == AnnotationMode.AUTOMATED)){
						computedIds.add(ann.getOntologyTerm().getTermId());
						computedLabels.add(ann.getOntologyTerm().getTermLabel());
					}
					else {
						ids.add(ann.getOntologyTerm().getTermId());
						labels.add(ann.getOntologyTerm().getTermLabel());
					}
				}
			}
			if (ids.size() > 0){
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
		}
		
		if (roi.getAbnormalityInAnatomicalStructure() != null){
			
			List<String> ids = new ArrayList<>(); // || with labels
			List<String> labels = new ArrayList<>(); // || with ids
			List<String> freetext = new ArrayList<>();
			List<String> computedIds = new ArrayList<>(); // || with computedLabels
			List<String> computedLabels = new ArrayList<>(); // || with computedId
			// Abnormality in anatomical part
			for ( Annotation ann: roi.getAbnormalityInAnatomicalStructure().getEl()){
				if (ann.getAnnotationFreetext() != null){
					freetext.add(ann.getAnnotationFreetext());
				}
				if (ann.getOntologyTerm() != null){
					if (ann.getAnnotationMode() != null && (ann.getAnnotationMode() == AnnotationMode.AUTOMATED)){
						computedIds.add(ann.getOntologyTerm().getTermId());
						computedLabels.add(ann.getOntologyTerm().getTermLabel());
					}
					else {
						ids.add(ann.getOntologyTerm().getTermId());
						labels.add(ann.getOntologyTerm().getTermLabel());
					}
				}
			}
			if (ids.size() > 0){
				bean.setAbnormalityAnatomyId(ids);
				bean.setAbnormalityAnatomyTerm(labels);
			}
			if (freetext.size() > 0){
				bean.setAbnormalityAnatomyFreetext(freetext);
			}
			if (computedIds.size() > 0){
				bean.setComputedAbnormalityAnatomyId(computedIds);
				bean.setComputedAbnormalityAnatomyTerm(computedLabels);
			}
		}
		
		if (roi.getPhenotypeAnnotations() != null){
			// Phenotypes
			//TODO copy this to ann_bag in images
			List<String> ids = new ArrayList<>(); // || with labels
			List<String> labels = new ArrayList<>(); // || with ids
			List<String> freetext = new ArrayList<>();
			for ( Annotation ann: roi.getPhenotypeAnnotations().getEl()){
				if (ann.getAnnotationFreetext() != null){
					freetext.add(ann.getAnnotationFreetext());
				}
				if (ann.getOntologyTerm() != null){
					ids.add(ann.getOntologyTerm().getTermId());
					labels.add(ann.getOntologyTerm().getTermLabel());
				}
			}
			if (ids.size() > 0){
				bean.setPhenotypeId(ids);
				bean.setPhenotypeTerm(labels);
			}
			if (freetext.size() > 0){
				bean.setPhenotypeFreetext(freetext);
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
	
	
	// fillChannelPojo
	private ChannelDTO fillPojo(Channel channel) {

		ChannelDTO bean = new ChannelDTO();
		
		bean.setId(channel.getId());
		bean.setAssociatedImage(channel.getAssociatedImage());
		if (channel.getAssociatedRoi() != null){
			bean.setAssociatedRoi(channel.getAssociatedRoi().getEl());
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

	private ImageDTO fillPojo(Image img) {

		ImageDTO bean = new ImageDTO();
		bean.setTaxon(img.getOrganism().getTaxon());
		bean.setId(img.getId());

		ImageDescription desc = img.getImageDescription();
		bean.setImageGeneratedBy(desc.getImageGeneratedBy());
		bean.setSampleGeneratedBy(desc.getOrganismGeneratedBy());
		bean.setHostName(desc.getHostName());
		bean.setHostUrl(desc.getHostUrl());
		bean.setImageUrl(desc.getImageUrl());
		if (desc.getImageContextUrl() != null){
			bean.setImageContextUrl(desc.getImageContextUrl());
		}

		if (img.getAssociatedRoi() != null){
			bean.setAssociatedRoi(img.getAssociatedRoi().getEl());
			// Need to copy some fields for search purposes
			bean = copyFieldsFromRoi(img, bean);
		}

		if (img.getAssociatedChannel() != null){
			System.out.println("\t channel not null");
			bean.setAssociatedChannel(img.getAssociatedChannel().getEl());
			// Need to copy some fields for search purposes
			bean = copyFieldsFromChannel(img, bean);
		}
		
		if (desc.getImageDimensions().getImageDepth() != null){
			bean.setDepth(desc.getImageDimensions().getImageDepth());
		}

		bean.setHeight(desc.getImageDimensions().getImageHeight());

		bean.setWidth(desc.getImageDimensions().getImageWidth());

		if (desc.getImagingMethod() != null){
			for (OntologyTerm im: desc.getImagingMethod().getEl()){
				bean.setImagingMethodId(im.getTermId());
				bean.setImagingMethodLabel(im.getTermLabel());
			}
		}
		if (desc.getSamplePreparation() != null){
			for (OntologyTerm sp: desc.getSamplePreparation().getEl()){
				bean.setSamplePreparationId(sp.getTermId());
				bean.setSamplePreparationLabel(sp.getTermLabel());
			}
		}
		if (desc.getVisualisationMethod() != null){
			for (OntologyTerm vm: desc.getVisualisationMethod().getEl()){
				bean.setVisualisationMethodId(vm.getTermId());
				bean.setVisualisationMethodLabel(vm.getTermLabel());
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
		
		// TODO bean.setThumbnailPath(thumbnailPath);;

		// Sample
		
		Organism org = img.getOrganism();
		
		if (org.getAge() != null){
			if (org.getAge().getAgeSinceBirth() != null){
				bean.setAgeSinceBirth(org.getAge().getAgeSinceBirth());
			}
			if (org.getAge().getEmbryonicAge() != null){
				bean.setAgeSinceBirth(org.getAge().getEmbryonicAge());
			}
		}

		if (org.getNcbiTaxonId() != null){
			bean.setNcbiTaxonId(org.getNcbiTaxonId());
		}

		if (org.getSex() != null){
			bean.setSex(org.getSex().name());
		}

		if (org.getStage() != null){
			bean.setStage(org.getStage().getTermLabel());
			bean.setStageId(org.getStage().getTermId());
		}

		// annotations -->
		if (img.getDepictedAnatomicalStructure() != null){
			if (img.getDepictedAnatomicalStructure().getAnnotationFreetext() != null){
				bean.setAnatomyFreetext(img.getDepictedAnatomicalStructure().getAnnotationFreetext());
			}
			if (img.getDepictedAnatomicalStructure().getOntologyTerm() != null){
				bean.setAnatomyId(img.getDepictedAnatomicalStructure().getOntologyTerm().getTermId());
				bean.setAnatomyTerm(img.getDepictedAnatomicalStructure().getOntologyTerm().getTermLabel());
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
			
			for (GenotypeComponent g : img.getMutantGenotypeTraits().getEl()){
				//TODO maybe add empty strings if null? Test first if this works for the empty fields.
				// We need to fill all of these arrays because they need to be parallel
				geneIds.add(g.getGeneId());
				geneSymbols.add(g.getGeneSymbol());
				gfEnsembl.add(g.getGeneticFeatureEnsemblId());
				gfIds.add(g.getGeneticFeatureId());
				gfSymbols.add(g.getGeneSymbol());
				if (g.getGenomicLocation() != null){
					chromosome.add(g.getGenomicLocation().getChromosone());
					startPosition.add(g.getGenomicLocation().getStartPos());
					endPosition.add(g.getGenomicLocation().getEndPos());
					strand.add(g.getGenomicLocation().getStrand());
				}
				zygosity.add(g.getZygosity().name());
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
						phenotypeLabels.add(ann.getOntologyTerm().getTermLabel());
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
							expressionInAnatomyIds.add(ann.getOntologyTerm().getTermId());
							expressionInAnatomyLabels.add(ann.getOntologyTerm().getTermLabel());
						}
						else{
							depictedAnatomyIds.add(ann.getOntologyTerm().getTermId());
							depictedAnatomyLabels.add(ann.getOntologyTerm().getTermLabel());
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
						abnormalityInAnatomyLabels.add(ann.getOntologyTerm().getTermLabel());
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

		System.out.println("I get in copyFieldsFromChannel.");
		
		ArrayList<String> expressedGfBag = new ArrayList<>();
		
		// For all associated ROIs, check available annotations and copy them as needed in the bag fields
		for (String channelId : img.getAssociatedChannel().getEl()){
			
			System.out.println("I get In for loop.");
			
			Channel channel = channelIdMap.get(channelId);		
			
			// expressed features
			if (channel.getDepictsExpressionOf() != null){
				GenotypeComponent gf = channel.getDepictsExpressionOf();
				if (gf.getGeneId() != null){
					expressedGfBag.add(gf.getGeneId());
				}
				if (gf.getGeneSymbol() != null){
					expressedGfBag.add(gf.getGeneSymbol());
				}
				if (gf.getGeneticFeatureId() != null){
					expressedGfBag.add(gf.getGeneticFeatureId());
				}
				if (gf.getGeneticFeatureEnsemblId() != null){
					expressedGfBag.add(gf.getGeneticFeatureEnsemblId());
				}
				if (gf.getGeneticFeatureSymbol() != null){
					expressedGfBag.add(gf.getGeneticFeatureSymbol());
				}	
				System.out.println("\n\n\n\n " + img.getId() + " found expression of\n\n\n\n");
				break;
			}
		}

		res.setExpressedGfIdBag(expressedGfBag);
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
		boolean res = checkIdsReferenceEWxistingObjects();
		if (!res) { return false; }

		for (Image img : imageIdMap.values()) {

			// Check ontoloy fields contain ontology IDs and they are from the
			// right ontology
			// Check label & id match
			if (!vu.hasValidOntologyTerms(img)) {
				System.out.println("there was something wrong with the ontology terms for img id = " + img.getId());

			}

			// positive dimensions
			if (!vu.hasPositieDimensions(img.getImageDescription().getImageDimensions())) {
				System.out.println("Dimensions are not positive! Validation failed.");
				return false;
			}

		}

		for (Roi roi : roiIdMap.values()) {

			// percentages
			if (!vu.arePercentagesOk(roi.getCoordinates())) { return false; }

			// Check ontoloy fields contain ontology IDs and they are from the
			// right ontology
			// Check label & id match
			if (!vu.isValidOntologyTerms(roi)) {
				System.out.println("there was something wrong with the ontology terms for roi id = " + roi.getId());
				return false;
			}
		}
		return true;
	}


	private boolean checkIdsReferenceEWxistingObjects() {

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
}

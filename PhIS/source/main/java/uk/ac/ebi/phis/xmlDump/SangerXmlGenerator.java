package uk.ac.ebi.phis.xmlDump;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.DOMException;

import uk.ac.ebi.phis.jaxb.Age;
import uk.ac.ebi.phis.jaxb.Annotation;
import uk.ac.ebi.phis.jaxb.AnnotationArray;
import uk.ac.ebi.phis.jaxb.AnnotationMode;
import uk.ac.ebi.phis.jaxb.Channel;
import uk.ac.ebi.phis.jaxb.Coordinates;
import uk.ac.ebi.phis.jaxb.Dimensions;
import uk.ac.ebi.phis.jaxb.Doc;
import uk.ac.ebi.phis.jaxb.Genotype;
import uk.ac.ebi.phis.jaxb.GenotypeComponent;
import uk.ac.ebi.phis.jaxb.Image;
import uk.ac.ebi.phis.jaxb.ImageDescription;
import uk.ac.ebi.phis.jaxb.ImageType;
import uk.ac.ebi.phis.jaxb.ImageTypeArray;
import uk.ac.ebi.phis.jaxb.Link;
import uk.ac.ebi.phis.jaxb.OntologyTerm;
import uk.ac.ebi.phis.jaxb.OntologyTermArray;
import uk.ac.ebi.phis.jaxb.Organism;
import uk.ac.ebi.phis.jaxb.PercentArray;
import uk.ac.ebi.phis.jaxb.Roi;
import uk.ac.ebi.phis.jaxb.SampleType;
import uk.ac.ebi.phis.jaxb.Sex;
import uk.ac.ebi.phis.jaxb.StringArray;
import uk.ac.ebi.phis.jaxb.Zygosity;
import uk.ac.ebi.phis.utils.EnrichingUtils;
import uk.ac.ebi.phis.utils.Normalizer;

public class SangerXmlGenerator {
	
	Normalizer norm;
		
	public SangerXmlGenerator(){
		norm = new Normalizer();
	}
	
	public void exportImages() throws IOException{
		
        ApplicationContext ac = new ClassPathXmlApplicationContext("app-config.xml");
		DataSource dataSource = (DataSource) ac.getBean("komp2DataSource");
        
        String command = "SELECT iir.ID, iir.LARGE_THUMBNAIL_FILE_PATH, iir.PUBLISHED_STATUS_ID, " +
        					"iit.TAG_NAME, iit.TAG_VALUE, iit.X_START, iit.X_END, iit.Y_START, iit.Y_END, " +
        					"aa.TERM_ID, aa.TERM_NAME, aa.ONTOLOGY_DICT_ID, " +
        					"imam.MOUSE_ID, imam.MOUSE_NAME, imam.GENDER, imam.AGE_IN_WEEKS, imam.GENE, imam.ALLELE, imam.GENOTYPE, " +
        					"ied.NAME as procedure_name, " +
        					"allele.symbol, allele.name, allele.acc, allele.gf_acc " +
        				"FROM IMA_IMAGE_RECORD iir " +
        					"LEFT OUTER JOIN IMA_IMAGE_TAG iit ON iit.IMAGE_RECORD_ID=iir.ID " +
        					"LEFT OUTER JOIN ANN_ANNOTATION aa ON aa.FOREIGN_KEY_ID=iit.ID " +
        					"LEFT OUTER JOIN IMPC_MOUSE_ALLELE_MV imam on imam.MOUSE_ID=iir.FOREIGN_KEY_ID " +
        					"LEFT OUTER JOIN IMA_SUBCONTEXT isub ON iir.subcontext_id=isub.id " +
        					"LEFT OUTER JOIN IMA_EXPERIMENT_DICT ied ON isub.experiment_dict_id=ied.id " +
        					"LEFT OUTER JOIN allele ON allele.symbol=imam.ALLELE " + 
        				"WHERE ied.NAME != 'Mouse Necropsy' " +
        				"ORDER BY iir.ID;";
        
        try {
        	
        	PreparedStatement statement = dataSource.getConnection().prepareStatement(command);
     		ResultSet res = statement.executeQuery();
                  	
			Doc doc = new Doc();			
			int i = 0;
			
	        while ( res.next()){

	        	if (res.getString("gf_acc") != null && res.getString("gf_acc").startsWith("MGI:")){
    				
		        	boolean sameImage = true;
		        	
		        	String internalId =  "komp2_" + res.getString("ID");
		        	
		        	String imageId = res.getString("ID");		        
		    		
		    		String url = "http://www.mousephenotype.org/data/media/" + res.getString("LARGE_THUMBNAIL_FILE_PATH") ;
		    		Map<String, Integer> dimensions = EnrichingUtils.getImageMeasuresFromUrl(url);
		    		
		    		if (dimensions != null){// the image could be loaded 	    		

			    		Boolean expression = false;
			    		Boolean phenotypeAnatomy = false;
		    			Boolean isMutant = true;
			    		
		    			Image image = new Image();
			    		image.setId(internalId);
			    		
			    		Dimensions d = new Dimensions();
		    			d.setImageHeight(dimensions.get("height"));
		    			d.setImageWidth(dimensions.get("width"));
			    		String procedure = res.getString("procedure_name");
			    		// store procedure name as observation
			    		image.setObservations(new StringArray());
			    		image.getObservations().getEl().add("Procedure name: " + procedure);

			    		Organism organism = new Organism();
			    		Sex sex = Sex.fromValue(norm.normalizeSex(res.getString("GENDER")));
			    		organism.setSex(sex);
			    		organism.setOrganismId(res.getString("MOUSE_NAME"));
			    		Age age = new Age();
			    		if (ageIsRelevant(procedure)){
			    			if (norm.isEmbryonicAge(res.getString("AGE_IN_WEEKS"))){
				    			age.setEmbryonicAge(res.getString("AGE_IN_WEEKS"));
				    		} else {
				    			age.setAgeSinceBirth(res.getString("AGE_IN_WEEKS"));
				    		}
				    		organism.setAge(age);
			    		}
			    		OntologyTerm stageOt = getStageFromProcedureOrAge(procedure, res.getString("AGE_IN_WEEKS"));
			    		if (stageOt != null){
			    			organism.setStage(stageOt);
			    		}
			    			
			    		organism.setTaxon("Mus musculus");
			    		image.setOrganism(organism);
		  	    		GenotypeComponent gt = new GenotypeComponent();
			    		
			    		if (res.getString("GENOTYPE").equalsIgnoreCase("WT")){
			    			isMutant = false;
			    		} else {
				    		gt.setGeneId(res.getString("gf_acc"));
				    		gt.setGeneSymbol(res.getString("GENE"));
				    		gt.setGeneticFeatureId(res.getString("acc"));
				    		gt.setGeneticFeatureSymbol(res.getString("ALLELE"));
				    		Zygosity zyg = Zygosity.fromValue(norm.normalizeZygosity(res.getString("GENOTYPE")));
				    		gt.setZygosity(zyg);
				    		Genotype gta = new Genotype();
				    		gta.getEl().add(gt);
				    		image.setMutantGenotypeTraits(gta);
			    		}			    		
				        
				        /* 	Channel 	*/
			    		String imageType = norm.getImageType(res.getString("procedure_name"));
			    		Channel channel = null;
			    		String channelId = "";
			    		if (imageType.equalsIgnoreCase("expression")){
			    			expression = true;
			    			channelId = internalId.replace("komp2_", "komp2_channel_") + "_" + 0; // we know that for Sanger data there is at most one channel.
			    			channel = new Channel();
					        channel.setAssociatedImage(internalId);
					        // KOMP2 always has at most 1 channel
					        StringArray c = new StringArray();
					        c.getEl().add(channelId);
					        image.setAssociatedChannel(c);
				    		channel.setId(channelId);
				    		if (isMutant){
				    			channel.setDepictsExpressionOf(gt);
				    		}
		    			}
			    			    			

				        /*	ROI	*/
			    		int k = 0;
			    		// Go through all annotations for the same image
			    		while(sameImage){
			    			
			    			if (res.getString("ONTOLOGY_DICT_ID") != null){
					    		Roi roi = new Roi();
					    		String roiId = internalId.replace("komp2_", "komp2_roi_") + "_" + k;
					    		roi.setId(roiId);
					    		roi.setAssociatedImage(internalId);
					    		// Need to decide first if we associate annotations to a ROI or to the whole image
					    		// 1. Phenotypes should always be associated to a region of interest
					    		// 2. Existing ROI should be kept if the coordinates != 0 
					    		if ( res.getString("ONTOLOGY_DICT_ID").equalsIgnoreCase("1") || 
					    				notZeroCoordinates(res) ){ // 1 = MP
					    			roi = fillRoi(roi, res, d, imageType.equalsIgnoreCase("expression"));
					    			if (!imageType.equalsIgnoreCase("expression")){
					    				phenotypeAnatomy = true;
					    			}
					    		}
					    									       
					    		// 3. Anatomy from expression annotations should always be associated to it's ROI
					    		// Sanger expression images: if an anatomy term is associated to the whole expression image it means there is expression in that anatomical structure
					    		else if (imageType.equalsIgnoreCase("expression"))
					    		{
					    			roi = fillRoi(roi, res, d, imageType.equalsIgnoreCase("expression"));
					    			roi.setAssociatedChannel(new StringArray());
					    			roi.getAssociatedChannel().getEl().add(channelId);
					    			if (channel.getAssociatedRoi() == null){
						    			channel.setAssociatedRoi(new StringArray());
					    			}
					    			channel.getAssociatedRoi().getEl().add(roiId);
					    			expression = true;
					    		}
					    		// Otherwise associate annotation to the whole image
					    		else {
					    			if (res.getString("TERM_ID") != null && res.getString("TERM_NAME") != null){
						    			// Add annotation to the whole image
						    			if (!res.getString("TAG_VALUE").equalsIgnoreCase("null")){
											image.getObservations().getEl().add(res.getString("TAG_NAME") + ": " + res.getString("TAG_VALUE"));
										}
						    			image.setDepictedAnatomicalStructure(getAnnotation(res.getString("TERM_ID").toString(), res.getString("TERM_NAME").toString(), null, AnnotationMode.MANUAL));
					    			}
					    			roi = null;
					    		}
					    								        
						        if (roi != null){
						        	if (image.getAssociatedRoi() == null){
						    			image.setAssociatedRoi(new StringArray());
						        	}
						        	image.getAssociatedRoi().getEl().add(roi.getId());
						        	doc.getRoi().add(roi);
						        }
			    			}
			    			
			    			if (res.next() && imageId.equalsIgnoreCase(res.getString("ID"))){
						        k++;	
							}
					        else {
					        	sameImage=false;
					        	res.previous();
					        }
						}
			    		
						ImageDescription imageDesc = new ImageDescription();
						imageDesc.setImageUrl(url);
						imageDesc.setImageDimensions(d);
						Link ogb = new Link();
						ogb.setDisplayName("WTSI");
						imageDesc.setOrganismGeneratedBy(ogb);
						Link igb = new Link();
						igb.setDisplayName("WTSI");
						igb.setUrl("http://www.sanger.ac.uk");
						imageDesc.setImageGeneratedBy(igb);
						Link host = new Link();
						host.setDisplayName("Mouse Phenotype");
						host.setUrl("http://www.mousephenotype.org/");
						imageDesc.setHost(host);
						// Parse procedure names to get most info out of htem.
						// Mappings done by David can be found at
						// https://docs.google.com/spreadsheet/ccc?key=0AmK8olNJT0Z7dEN2MklCX2g1TmhJWTk0N3VlUERVaVE&usp=drive_web#gid=0
						imageDesc = setSamplePrep(procedure, imageDesc);

						ImageTypeArray it = new ImageTypeArray();
						if (phenotypeAnatomy) {
							it.getEl().add(ImageType.PHENOTYPE_ANATOMY);
						} else if (!expression) {
							// this will happen when no phenotype annotation was
							// found or no anatomy ann with ROI and we just
							// supposed the annotation referes to the whole
							// image. But since we have the image, it must be of
							// some anatomy/phenotype part of interest.
							it.getEl().add(ImageType.PHENOTYPE_ANATOMY);
						}
						if (expression) {
							it.getEl().add(ImageType.EXPRESSION);
						}
						imageDesc.setImageType(it);
						if (isMutant) {
							imageDesc.setSampleType(SampleType.MUTANT);
						} else {
							imageDesc.setSampleType(SampleType.WILD_TYPE);
						}
						image.setImageDescription(imageDesc);

						// !!! Last thing in this block !!!
						doc.getImage().add(image);

						if (channel != null) {
							channel = setVisualizationMethod(procedure, channel);
							doc.getChannel().add(channel);
						}
					}
	        }

				i++;
				if (i % 100 == 0) {
					System.out.println(i);
		//			if (i==10000){
		//				break;
		//			}
				}
			}
			File file = new File("source/main/resources/sangerExport.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Doc.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(doc, file);
			// jaxbMarshaller.marshal(doc, System.out);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private Roi fillRoi(Roi roi, ResultSet res, Dimensions d, Boolean isExpressionImg) {

		// From TAG NAMES & VALUES I need to make observations
		try {
			if (res.getString("TAG_VALUE") == null && res.getString("ONTOLOGY_DICT_ID") == null) {
				System.out.println(res.getString("ID"));
				return null;
			}

			if (res.getString("TAG_VALUE") != null && res.getString("TAG_NAME") != null && !res.getString("TAG_VALUE").equalsIgnoreCase("null")) {
				if (roi.getObservations() == null)
					roi.setObservations(new StringArray());
				roi.getObservations().getEl().add(res.getString("TAG_NAME") + ": " + res.getString("TAG_VALUE"));
			}

			if (res.getString("ONTOLOGY_DICT_ID") != null) {
				if (isAnatomy(res)) {																																								// 4=MA
					if (isExpressionImg) {
						// we have expression in the annotated anatomy term
						roi.setDepictedAnatomicalStructure(addToAnnotationArray(new AnnotationArray(), res.getString("TERM_ID").toString().trim(),
							res.getString("TERM_NAME").toString(), null, AnnotationMode.MANUAL));
					}
					else {
						// we have somthin interesting but not expression in the
						// anatomy term
						roi.setDepictedAnatomicalStructure(addToAnnotationArray(new AnnotationArray(), res.getString("TERM_ID").toString().trim(),
							res.getString("TERM_NAME").toString(), null, AnnotationMode.MANUAL));
					}
				}
				else if (isMp(res)) { 
					// we know there's only one phenotype associated so we don't
					// need to check if the array is empty
					roi.setPhenotypeAnnotations(addToAnnotationArray(new AnnotationArray(), res.getString("TERM_ID").toString().trim(), 
						res.getString("TERM_NAME").toString(), null, AnnotationMode.MANUAL));
				}
			}
			Coordinates coord = new Coordinates();
			PercentArray xCoord = new PercentArray();
			PercentArray yCoord = new PercentArray();

			if (notZeroCoordinates(res)) {
				// we have coordinates
				// Order is important: (start, end)
				// Sanger stores percentages so no need to compute them here, we
				// just copy
				xCoord.getEl().add((Float) res.getFloat("X_START"));
				xCoord.getEl().add((Float) res.getFloat("X_END"));
				coord.setXCoordinates(xCoord);

				// Order is important: (start, end)
				yCoord.getEl().add((Float) res.getFloat("Y_START"));
				yCoord.getEl().add((Float) res.getFloat("Y_END"));
				coord.setYCoordinates(yCoord);
			}
			else {
				float zero = 0;
				float hunderd = 100;
				// create the coordinates
				xCoord.getEl().add(zero);
				xCoord.getEl().add(hunderd);
				coord.setXCoordinates(xCoord);

				// Order is important: (start, end)
				yCoord.getEl().add(zero);
				yCoord.getEl().add(hunderd);
				coord.setYCoordinates(yCoord);
			}

			roi.setCoordinates(coord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roi;
	}

	private boolean notZeroCoordinates(ResultSet res)
	throws SQLException {

		return (Float) res.getFloat("X_START") + (Float) res.getFloat("X_END") + (Float) res.getFloat("Y_START") + (Float) res.getFloat("Y_END") != 0;
	}

	private boolean isAnatomy(ResultSet res)
	throws SQLException {

		return res.getString("ONTOLOGY_DICT_ID").toString().equalsIgnoreCase("2") || res.getString("ONTOLOGY_DICT_ID").toString().equalsIgnoreCase("4");
	}

	private boolean isMp(ResultSet res)
	throws SQLException {

		return res.getString("ONTOLOGY_DICT_ID").toString().equalsIgnoreCase("1");
	}
	 

	Channel setVisualizationMethod(String procedure, Channel channel) {

		AnnotationArray a = new AnnotationArray();
		Annotation vm = new Annotation();

		if (procedure.equalsIgnoreCase("Expression")) {
			vm.setOntologyTerm(getOntologyTerm("visualisation of genetically encoded beta-galactosidase", "FBbi_00000077"));
			a.getEl().add(vm);
		}
		else if (procedure.equalsIgnoreCase("Tail Epidermis Wholemount")) {
			vm.setOntologyTerm(getOntologyTerm("fluorescent protein tag", "FBbi_00000405"));
			a.getEl().add(vm);
		}
		else if (procedure.equalsIgnoreCase("Histology Slide")) {
			vm.setOntologyTerm(getOntologyTerm("optically dense stain", "FBbi_00000567"));
			a.getEl().add(vm);
		}
		/*
		 * else if (procedure.equalsIgnoreCase("Histology Slide")){
		 * 
		 * }
		 */
		else if (procedure.equalsIgnoreCase("Brain Histopathology")) {
			vm.setOntologyTerm(getOntologyTerm("fluorescent protein tag", "FBbi_00000405"));
			a.getEl().add(vm);
		}
		if (a.getEl().size() > 0) {
			channel.setVisualisationMethod(a);
		}
		return channel;
	}


	ImageDescription setSamplePrep(String procedure, ImageDescription desc) {

		AnnotationArray spArray = new AnnotationArray();
		AnnotationArray imArray = new AnnotationArray();
		
		Annotation ann = new Annotation();
		Annotation ann2 = new Annotation();
		
		if (procedure.equalsIgnoreCase("Dysmorphology")) {
			ann.setOntologyTerm(getOntologyTerm("living tissue", "FBbi_00000025"));
			spArray.getEl().add(ann);
			Annotation sp2 = new Annotation();
			sp2.setOntologyTerm(getOntologyTerm("whole mounted tissue", "FBbi_00000024"));
			spArray.getEl().add(sp2);
			ann2.setOntologyTerm(getOntologyTerm("macroscopy", "FBbi_00000240"));
			imArray.getEl().add(ann2);
		}
		else if (procedure.equalsIgnoreCase("Embryo dysmorphology")) {
			ann.setOntologyTerm(getOntologyTerm("whole mounted tissue", "FBbi_00000024"));
			spArray.getEl().add(ann);
			ann2.setOntologyTerm(getOntologyTerm("bright-field microscopy", "FBbi_00000243"));
			imArray.getEl().add(ann2);
		}
		else if (procedure.equalsIgnoreCase("Xray")) {
			ann.setOntologyTerm(getOntologyTerm("whole mounted tissue", "FBbi_00000024"));
			spArray.getEl().add(ann);
			Annotation sp2 = new Annotation();
			sp2.setOntologyTerm(getOntologyTerm("living tissue", "FBbi_00000025"));
			spArray.getEl().add(sp2);
			ann2.setOntologyTerm(getOntologyTerm("X-ray illumination", "FBbi_00000342"));
			imArray.getEl().add(ann2);
		}
		else if (procedure.equalsIgnoreCase("Eye Morphology")) { // && slit lamp
			ann.setOntologyTerm(getOntologyTerm("whole mounted tissue", "FBbi_00000024"));
			spArray.getEl().add(ann);
			Annotation sp2 = new Annotation();
			sp2.setOntologyTerm(getOntologyTerm("living tissue", "FBbi_00000025"));
			spArray.getEl().add(sp2);
			ann2.setOntologyTerm(getOntologyTerm("macroscopy", "FBbi_00000240"));
			imArray.getEl().add(ann2);
		}
		/*
		 * else if (procedure.equalsIgnoreCase("Eye Morphology")){ // && TEFI
		 * sp.getEl().add(getOntologyTerm("whole mounted tissue" ,
		 * "FBbi_00000024")); sp.getEl().add(getOntologyTerm("living tissue" ,
		 * "FBbi_00000025")); im.getEl().add(getOntologyTerm("light microscopy",
		 * "FBbi_00000345")); }
		 */
		else if (procedure.equalsIgnoreCase("Expression")) {
			ann.setOntologyTerm(getOntologyTerm("whole mounted tissue", "FBbi_00000024"));
			spArray.getEl().add(ann);
			ann2.setOntologyTerm(getOntologyTerm("bright-field microscopy", "FBbi_00000243"));
			imArray.getEl().add(ann2);
		}
		else if (procedure.equalsIgnoreCase("Tail Epidermis Wholemount")) {
			ann.setOntologyTerm(getOntologyTerm("whole mounted tissue", "FBbi_00000024"));
			spArray.getEl().add(ann);
			ann2.setOntologyTerm(getOntologyTerm("confocal microscopy", "FBbi_00000251"));
			imArray.getEl().add(ann2);
		}
		else if (procedure.equalsIgnoreCase("Histology Slide")) {
			ann2.setOntologyTerm(getOntologyTerm("bright-field microscopy", "FBbi_00000243"));
			imArray.getEl().add(ann2);
		}
		/*
		 * else if (procedure.equalsIgnoreCase("Histology Slide")){
		 * 
		 * }
		 */
		else if (procedure.equalsIgnoreCase("Brain Histopathology")) {
			ann.setOntologyTerm(getOntologyTerm("sectioned tissue", "FBbi_00000026"));
			spArray.getEl().add(ann);
			ann2.setOntologyTerm(getOntologyTerm("confocal microscopy", "FBbi_00000251"));
			imArray.getEl().add(ann2);
		}

		if (spArray.getEl().size() > 0) {
			desc.setSamplePreparation(spArray);
		}
		if (imArray.getEl().size() > 0) {
			desc.setImagingMethod(imArray);
		}
		return desc;
	}


	OntologyTerm getOntologyTerm(String label, String id) {

		OntologyTerm term = new OntologyTerm();
		term.setTermId(id);
		term.setTermLabel(label);
		return term;
	}

	 
	 /**
	  * 
	  * @param pa
	  * @param id
	  * @param label
	  * @param freetext
	  * @param annMode
	  * @return Creates an annotation and adds it to the given array. Returns the modified array & follows the XSD
	  */
	 AnnotationArray addToAnnotationArray(AnnotationArray pa, String id, String label, String freetext, AnnotationMode annMode){
		
		 Annotation p = getAnnotation(id, label, freetext, annMode);
		 pa.getEl().add(p);
		 return pa;
	 }
	 
	 /**
	  * 
	  * @param id
	  * @param label
	  * @param freetext
	  * @param annMode
	  * @return Annotation object matching the description in XSD
	  */
	 Annotation getAnnotation( String id, String label, String freetext, AnnotationMode annMode){
		 Annotation p = new Annotation();
		 
		 if (label != null && id != null)
			 p.setOntologyTerm(getOntologyTerm(label, id));

		 if (freetext != null)
			 p.setAnnotationFreetext(freetext);
		 p.setAnnotationMode(annMode);
		 return p;
	 }
	 
	 /**
	  * On live procedures the age is not relevant and we should not import it. It does not mean it's the age at which the picture was taken.
	  *  See David's document https://docs.google.com/spreadsheet/ccc?key=0AmK8olNJT0Z7dEN2MklCX2g1TmhJWTk0N3VlUERVaVE&usp=drive_web#gid=0. 
	  * This is only relevant for Sanger direct image import.
	  * @param procedure
	  * @return 
	  */
	 // 
	 boolean ageIsRelevant(String procedure){
		 if (procedure.equalsIgnoreCase("Dysmorphology") ||
				 procedure.equalsIgnoreCase("Xray") ||
				 procedure.equalsIgnoreCase("Eye Morphology") ){
			 return false;
		 }
		 else return true;
	 }
	 
	 /**
	  * This is only relevant for Sanger images. Mapping done by David after discussion wit Mark.
	  * @param procedure
	  * @return
	  */
	 OntologyTerm getStageFromProcedureOrAge(String procedure, String age){
		 OntologyTerm prenatal = new OntologyTerm();
		 prenatal.setTermId("MmusDv_0000002");
		 prenatal.setTermLabel("embryonic mouse stage");
		 
		 OntologyTerm postnatal = new OntologyTerm();
		 postnatal.setTermId("MmusDv_0000092");
		 postnatal.setTermLabel("postnatal stage");
			
		if (procedure.equalsIgnoreCase("Dysmorphology") ||
				 procedure.equalsIgnoreCase("Xray") ||
				 procedure.equalsIgnoreCase("Eye Morphology") ){
			return postnatal;
		}else if (procedure.equalsIgnoreCase("Embryo dysmorphology")){
			return prenatal;
		}else {
			age = age.toLowerCase();
			if (age.endsWith("w") || age.endsWith("w (at death)")){ 
				return postnatal;
			}
			else if (age.endsWith("e") || age.endsWith("e (at death)") || age.startsWith("e")){
				return prenatal;
			}
		 }
		 return null;
	 }
}

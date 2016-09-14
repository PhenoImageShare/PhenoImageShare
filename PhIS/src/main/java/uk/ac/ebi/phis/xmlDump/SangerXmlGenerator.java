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
package uk.ac.ebi.phis.xmlDump;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import uk.ac.ebi.phis.exception.PhenoImageShareException;
import uk.ac.ebi.phis.jaxb.*;
import uk.ac.ebi.phis.utils.EnrichingUtils;
import uk.ac.ebi.phis.utils.Normalizer;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class SangerXmlGenerator extends BasicXmlGenerator{
	
	Normalizer norm;
	GregorianCalendar gregorianCalendar = new GregorianCalendar();
    DatatypeFactory datatypeFactory;
    
	public SangerXmlGenerator() throws DatatypeConfigurationException{
		norm = new Normalizer();
		datatypeFactory = DatatypeFactory.newInstance();
	}
	
	public void exportImages(String pathToContextFile) throws IOException, JAXBException, SQLException, PhenoImageShareException{

        ApplicationContext ac = new FileSystemXmlApplicationContext("file:" + pathToContextFile);
		DataSource dataSource = (DataSource) ac.getBean("komp2DataSource");
        
        String command = "SELECT iir.ID, iir.LARGE_THUMBNAIL_FILE_PATH, iir.SMALL_THUMBNAIL_FILE_PATH, iir.PUBLISHED_STATUS_ID, iit.ID as TAG_ID, " +
        					"iit.TAG_NAME, iit.TAG_VALUE, iit.X_START, iit.X_END, iit.Y_START, iit.Y_END, iit.CREATED_DATE, iit.EDIT_DATE, " +
        					"aa.TERM_ID, aa.TERM_NAME, aa.ONTOLOGY_DICT_ID, " +
        					"imam.MOUSE_ID, imam.MOUSE_NAME, imam.GENDER, imam.AGE_IN_WEEKS, imam.GENE, imam.ALLELE, imam.GENOTYPE, " +
        					"ied.NAME as procedure_name, " +
        					"allele.symbol, allele.name, allele.acc, allele.gf_acc, imam.COLONY_PREFIX  " +
        				"FROM IMA_IMAGE_RECORD iir " +
        					"LEFT OUTER JOIN IMA_IMAGE_TAG iit ON iit.IMAGE_RECORD_ID=iir.ID " +
        					"LEFT OUTER JOIN ANN_ANNOTATION aa ON aa.FOREIGN_KEY_ID=iit.ID " +
        					"LEFT OUTER JOIN IMPC_MOUSE_ALLELE_MV imam on imam.MOUSE_ID=iir.FOREIGN_KEY_ID " +
        					"LEFT OUTER JOIN IMA_SUBCONTEXT isub ON iir.subcontext_id=isub.id " +
        					"LEFT OUTER JOIN IMA_EXPERIMENT_DICT ied ON isub.experiment_dict_id=ied.id " +
        					"LEFT OUTER JOIN allele ON allele.symbol=imam.ALLELE " + 
        				"WHERE ied.NAME != 'Mouse Necropsy' " +
        				"ORDER BY iir.ID, TAG_ID ;";
        
        	PreparedStatement statement = dataSource.getConnection().prepareStatement(command);
     		ResultSet res = statement.executeQuery();
     		
     		System.out.println("Finished execution of QL query.");
                  	
			int i = 0;
			String imageId = "";
			String tagId = "";
    		String channelId = "";
	        String internalId =  "";
        	Roi roi = null;
        	Image image = null;
    		Map<String,Roi> rois = new HashMap<>();
    		Channel channel = null;
	    	Dimensions dimensions = new Dimensions();
    		Genotype genotype = null;
			Doc doc = new Doc();
			doc.setExportDate(datatypeFactory.newXMLGregorianCalendar(gregorianCalendar));
    		 			
	        while ( res.next() ){

			    String url = "http://www.mousephenotype.org/data/media/" + res.getString("LARGE_THUMBNAIL_FILE_PATH") ;
			    dimensions = EnrichingUtils.getImageMeasuresFromUrl(url);
////	        	System.out.println("-- " + res.getString("ID"));
			    if (dimensions != null){// the image could be loaded 

			    	String procedure = res.getString("procedure_name");	
////			    	System.out.println("\t could download" );
				    
			    	if (!res.getString("ID").equals(imageId)){
			    		
////	        			System.out.println("Writing " + imageId);	    		
				        writeDocs(image, channel, doc, rois); // write current docs so we can move to the new image

				    	internalId =  "komp2_" + res.getString("ID");		        	
					    imageId = res.getString("ID");	    		
			    		image = new Image();
				    	image.setId(internalId);				    		
				    	image.setObservations(new StringArray());
				    	image.getObservations().getEl().add("Procedure name: " + procedure);
			    		image.setOrganism(getOrganism(res, procedure));
			    		genotype = null;
			    		if (!res.getString("GENOTYPE").equalsIgnoreCase("WT")){
			    			genotype = getGenotype(res);
				    		image.setMutantGenotypeTraits(genotype);
			    		}
		                image.setImageDescription(createImageDescription(url, dimensions, genotype, res));			    		
					        
				        /* 	Channel 	*/
			    		channel = null;
			    		channelId = "";
			    		if (isExpression(procedure)){
			    			channelId = internalId.replace("komp2_", "komp2_channel_") + "_" + 0; // we know that for Sanger data there is at most one channel.
			    			channel = new Channel();
				    		channel.setId(channelId);
				    		if (genotype != null){ // not WT
				    			channel.setDepictsExpressionOf(genotype.getEl().get(0));
				    		}
							channel = setVisualizationMethod(procedure, channel);
		    			}
				    			    			
				        /*	ROI	*/;
			    		tagId = "";
			    		roi = new Roi();
			    		rois = new HashMap<>();

			    	} 			    	
	        	
		        	
			    	// Add annotations to ROI
	    			if (res.getString("ONTOLOGY_DICT_ID") != null){
	
			    		if (!tagId.equalsIgnoreCase(res.getString("TAG_ID"))){
			    			tagId = res.getString("TAG_ID");
			    			roi = createROI(internalId, res);					    			
			    		}				    		
			    		// Need to decide first if we associate annotations to a ROI or to the whole image
			    		// 1. Phenotypes should always be associated to a region of interest
			    		// 2. Existing ROI should be kept if the coordinates != 0
			    		if ( res.getString("ONTOLOGY_DICT_ID").equalsIgnoreCase("1") || notZeroCoordinates(res) ){ // 1 = MP
			    			roi = fillRoi(roi, res, dimensions, isExpression(procedure));
				        	rois.put(roi.getId(), roi);
			    		}					    		
			    		// 3. Anatomy from expression annotations should always be associated to it's ROI
			    		// Sanger expression images: if an anatomy term is associated to the whole expression image it means there is expression in that anatomical structure
			    		else if ( isExpression(procedure))
			    		{
			    			roi = fillRoi(roi, res, dimensions,  isExpression(procedure));
			    			roi.setAssociatedChannel(new StringArray());
			    			roi.getAssociatedChannel().getEl().add(channelId);
				        	rois.put(roi.getId(), roi);
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
			    		}
	    			}
		        	
		        	if (res.isLast()){
		                writeDocs(image, channel, doc, rois);
		        	}
		        	
		        	i++;
					if (i % 100 == 0) {
						System.out.println(i);
//						if (i == 200){
//							break;
//						}
					}
			    }
			}
	        	
	        Date date = new Date();
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//	File file = new File("source/main/resources/" + dateFormat.format(date) + "_sangerExport.xml");
	    	File file = new File("/Users/ilinca/IdeaProjects/PhenoImageShare/PhIS/src/main/resources/sangerExport.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Doc.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(doc, file);
			// jaxbMarshaller.marshal(doc, System.out);
			
	}

	
	private boolean isPhenotypeAnatomy(ResultSet res) 
	throws SQLException{
		
		boolean isPa = false; 
		if ( (res.getString("ONTOLOGY_DICT_ID") != null && res.getString("ONTOLOGY_DICT_ID").equalsIgnoreCase("1")) || notZeroCoordinates(res) ){ // 1 = MP
			if (!isExpression(res.getString("PROCEDURE_NAME"))){
				isPa = true;
			}
		}	
		return isPa;
	}
	
	
	private void writeDocs(Image image, Channel channel, Doc doc, Map<String, Roi> rois){
			
			if (rois != null && rois.size() > 0){
	        	image.setAssociatedRoi(new StringArray());
	        	image.getAssociatedRoi().getEl().addAll(rois.keySet());
	        	doc.getRoi().addAll(rois.values());
			}
			
			if (channel != null ) {
				if(rois.size() > 0){
	        		if (channel.getAssociatedRoi() == null){
		    			channel.setAssociatedRoi(new StringArray());
	    			} 
	        		channel.getAssociatedRoi().getEl().addAll(rois.keySet());
				}
		        channel.setAssociatedImage(image.getId());
		        // KOMP2 always has at most 1 channel
		        StringArray c = new StringArray();
		        c.getEl().add(channel.getId());
		        image.setAssociatedChannel(c);
				doc.getChannel().add(channel);
			}
			
			// Last thing in this block, to get associated ids
			if (image != null){
				doc.getImage().add(image);
			}
			
	}
	
	
	private boolean isExpression(String procedure){

		return norm.getImageType(procedure).equalsIgnoreCase("expression");
	}
	
	
	private ImageDescription createImageDescription(String url, Dimensions dimensions, Genotype genotype, ResultSet res) 
	throws SQLException{
		
		String procedure = res.getString("PROCEDURE_NAME");
		ImageDescription imageDesc = new ImageDescription();
		imageDesc.setImageUrl(url);
		imageDesc.setThumbnailUrl("http://www.mousephenotype.org/data/media/" + res.getString("SMALL_THUMBNAIL_FILE_PATH"));
		imageDesc.setImageDimensions(dimensions);
		Link sanger = getLink("http://www.sanger.ac.uk", "WTSI", null);
		imageDesc.setOrganismGeneratedBy(sanger);
		imageDesc.setImageGeneratedBy(sanger);
		imageDesc.setHost(getLink("http://www.mousephenotype.org/", "IMPC Portal", null));
		// Parse procedure names to get most info out of them.
		// Mappings done by David can be found at
		// https://docs.google.com/spreadsheet/ccc?key=0AmK8olNJT0Z7dEN2MklCX2g1TmhJWTk0N3VlUERVaVE&usp=drive_web#gid=0
		imageDesc = setSamplePrep(procedure, imageDesc);

		ImageTypeArray it = new ImageTypeArray();
		if (isPhenotypeAnatomy(res)) {
			it.getEl().add(ImageType.PHENOTYPE_ANATOMY);
		} else if (!isExpression(procedure)) {
			// this will happen when no phenotype annotation was
			// found or no anatomy ann with ROI and we just
			// supposed the annotation referes to the whole
			// image. But since we have the image, it must be of
			// some anatomy/phenotype part of interest.
			it.getEl().add(ImageType.PHENOTYPE_ANATOMY);
		}
		if (isExpression(procedure)) {
			it.getEl().add(ImageType.EXPRESSION);
		}
		imageDesc.setImageType(it);
		if (genotype != null) {
			imageDesc.setSampleType(SampleType.MUTANT);
		} else {
			imageDesc.setSampleType(SampleType.WILD_TYPE);
		}
		
		return imageDesc;
	}
	
	
	private Roi createROI(String internalId, ResultSet res) 
	throws NumberFormatException, SQLException{
		
		Roi roi = new Roi();
		String roiId = internalId.replace("komp2_", "komp2_roi_") + "_" + res.getString("TAG_ID");
		roi.setId(roiId);
		roi.setAssociatedImage(internalId);
		if (res.getDate("EDIT_DATE") != null){
			String date = res.getString("EDIT_DATE");
			XMLGregorianCalendar xmlDate = datatypeFactory.newXMLGregorianCalendar(Integer.parseInt(date.split("-")[0]),
					Integer.parseInt(date.split("-")[1]), Integer.parseInt(date.split("-")[2]), 0, 0, 0, 0, 0);
			roi.setEditDate(xmlDate);
		}
		if (res.getDate("CREATED_DATE") != null){
			String date = res.getString("CREATED_DATE");
			XMLGregorianCalendar xmlDate = datatypeFactory.newXMLGregorianCalendar(Integer.parseInt(date.split("-")[0]),
					Integer.parseInt(date.split("-")[1]), Integer.parseInt(date.split("-")[2]), 0, 0, 0, 0, 0);
			roi.setCreationDate(xmlDate);
		}
		return roi;
	}

	private Genotype getGenotype(ResultSet res) 
	throws SQLException,PhenoImageShareException{

		Genotype genotype = null;
    	if (res.getString("gf_acc") != null && res.getString("gf_acc").startsWith("MGI:")){
    		GenotypeComponent gt = new GenotypeComponent();
	  		gt.setGeneId(res.getString("gf_acc"));
			gt.setGeneSymbol(res.getString("GENE"));
			gt.setGeneticFeatureId(res.getString("acc"));
			gt.setGeneticFeatureSymbol(res.getString("ALLELE"));
			Zygosity zyg = Zygosity.fromValue(norm.normalizeZygosity(res.getString("GENOTYPE")));
			gt.setZygosity(zyg);
			Annotation mt = new Annotation();
			if (norm.getImageType(res.getString("procedure_name")).equalsIgnoreCase("expression")){
				mt.setAnnotationFreetext("null allele with LacZ reporter");
				gt.setMutationType(mt);
			} else {
				mt.setAnnotationFreetext("null mutation");
				gt.setMutationType(mt);
			}
	
			genotype = new Genotype();
			genotype.getEl().add(gt);
    	}
		return genotype;
	}
	
	private Organism getOrganism(ResultSet res, String procedure) throws PhenoImageShareException, SQLException{
		
		Organism organism = new Organism();
		Sex sex = Sex.fromValue(norm.normalizeSex(res.getString("GENDER")));
		organism.setSex(sex);
		organism.setNcbiTaxonId("10090");
		organism.setOrganismId(res.getString("MOUSE_NAME"));
		Group group = new Group();
		group.setColonyId(res.getString("COLONY_PREFIX"));
		organism.setGroup(group);
		
		if (ageIsRelevant(procedure)){
	 		organism.setAge(res.getString("AGE_IN_WEEKS"));
		}
		OntologyTerm stageOt = getStageFromProcedureOrAge(procedure, res.getString("AGE_IN_WEEKS"));
		if (stageOt != null){
			organism.setStage(stageOt);
		}
			
		organism.setTaxon("Mus musculus");
		
		return organism;
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
						roi.setDepictedAnatomicalStructure(addToExpressionAnnotationArray(roi.getDepictedAnatomicalStructure(), res.getString("TERM_ID").toString().trim(),
							res.getString("TERM_NAME").toString(), null, AnnotationMode.MANUAL));
					} else {						
						System.out.println("WHAT to do here: " + roi.getId() + res + "????");
					}
				}
				else if (isMp(res)) { 
					roi.setPhenotypeAnnotations(addToAnnotationArray(roi.getPhenotypeAnnotations(), res.getString("TERM_ID").toString().trim(), 
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
		else if (procedure.equalsIgnoreCase("Skin Histopathology")) {
			vm.setOntologyTerm(getOntologyTerm("hematoxylin", "FFBbi_00000041"));
			Annotation vm1 = new Annotation();
			vm.setOntologyTerm(getOntologyTerm("eosin", "FFBbi_00000037"));			
			vm.setAnnotationFreetext("Stained with H&E on autostainer and coverslipper.");
			a.getEl().add(vm);			
			a.getEl().add(vm1);
		}
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

		else if (procedure.contains("Expression")) {
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
		else if (procedure.equalsIgnoreCase("Skin Histopathology")) {
			// TODO what sample preparation??
			ann2.setOntologyTerm(getOntologyTerm("mode of light microscopy", "FFBbi_00000345"));
			imArray.getEl().add(ann2);
		}
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


	protected static OntologyTerm getOntologyTerm(String label, String id) {

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
		 if (pa == null){
			 pa = new AnnotationArray();
		 }
		 pa.getEl().add(p);
		 return pa;
	 }
	 
	 ExpressionAnnotationArray addToExpressionAnnotationArray(ExpressionAnnotationArray pa, String id, String label, String freetext, AnnotationMode annMode){
			
		 ExpressionAnnotation p = new ExpressionAnnotation();
		 
		 if (id != null && label != null){
			 OntologyTerm term = new OntologyTerm();
			 term.setTermId(id);
			 term.setTermLabel(label);
			 p.setOntologyTerm(term);
		 }
		 
		 if (annMode != null){
			 p.setAnnotationMode(annMode);
		 }
		 
		 if (freetext != null && !freetext.isEmpty()){
			 p.setAnnotationFreetext(freetext);
		 }
		 
		 if(pa == null){
			 pa = new ExpressionAnnotationArray();
		 }
		 
		 pa.getEl().add(p);
		 
		 return pa;
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

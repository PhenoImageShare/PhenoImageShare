package uk.ac.ebi.phis.importer;

import j.*;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.wimpi.telnetd.io.terminal.ansi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import uk.ac.ebi.phis.utils.ontology.JsonFields;
import uk.ac.ebi.phis.utils.ontology.Normalizer;
import uk.ac.ebi.phis.utils.ontology.OntologyMapperPredefinedTypes;
import uk.ac.ebi.phis.utils.ontology.Utils;

public class SangerXmlGenerator {
	
	Normalizer norm;
	Utils utils;
		
	public SangerXmlGenerator(){
		norm = new Normalizer();
		utils = new Utils(OntologyMapperPredefinedTypes.MA_MP);
	}
	
	public void read() throws IOException{
		
        ApplicationContext ac = new ClassPathXmlApplicationContext("app-config.xml");
		DataSource dataSource = (DataSource) ac.getBean("komp2DataSource");
        
        String command = "SELECT iir.ID, iir.FULL_RESOLUTION_FILE_PATH, iir.PUBLISHED_STATUS_ID, " +
        					"iit.TAG_NAME, iit.TAG_VALUE, iit.X_START, iit.X_END, iit.Y_START, iit.Y_END, " +
        					"aa.TERM_ID, aa.TERM_NAME, aa.ONTOLOGY_DICT_ID, " +
        					"imam.MOUSE_ID, imam.MOUSE_NAME,	imam.GENDER, imam.AGE_IN_WEEKS, imam.GENE, imam.ALLELE, imam.GENOTYPE, " +
        					"ied.NAME as procedure_name, " +
        					"allele.symbol, allele.name, allele.acc, allele.gf_acc " +
        				"FROM komp2.IMA_IMAGE_RECORD iir " +
        					"INNER JOIN IMA_IMAGE_TAG iit ON iit.IMAGE_RECORD_ID=iir.ID " +
        					"INNER JOIN ANN_ANNOTATION aa ON aa.FOREIGN_KEY_ID=iit.ID " +
        					"INNER JOIN IMPC_MOUSE_ALLELE_MV imam on imam.MOUSE_ID=iir.FOREIGN_KEY_ID " +
        					"INNER JOIN IMA_SUBCONTEXT isub ON iir.subcontext_id=isub.id " +
        					"INNER JOIN IMA_EXPERIMENT_DICT ied ON isub.experiment_dict_id=ied.id " +
        					"INNER JOIN allele ON allele.symbol=imam.ALLELE " + 
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
		        	
		        	String internal_id =  "komp2_" + i;
		        	
		        	String imageId = res.getString("ID");		        
		    		
		    		String url = "http://www.mousephenotype.org/data/media/" + res.getString("FULL_RESOLUTION_FILE_PATH") ;
		    		Map<String, Integer> dimensions = utils.getImageMeasuresFromUrl(url);
		    		
		    		if (dimensions != null){// the image could be loaded 	    		

			    		Image image = new Image();
			    		image.setId(internal_id);
		    			
		    			Dimensions d = new Dimensions();
		    			d.setImageHeight(dimensions.get("height"));
		    			d.setImageWidth(dimensions.get("width"));
			    		ImageDescription imageDesc = new ImageDescription();
			    		imageDesc.setImageUrl(url);
			    		imageDesc.setOriginalImageId(res.getString("ID"));
			    		imageDesc.setImageDimensions(d);
			      		imageDesc.setOrganismGeneratedBy("WTSI");
			    		imageDesc.setImageGeneratedBy("WTSI");
			    		imageDesc.setHostName("Mouse Phenotype");
			    		imageDesc.setHostUrl("http://www.mousephenotype.org/");
			    		
			    		String procedure = res.getString("procedure_name");
			    		// Parse procedure names to get most info out of htem. Mappings done by David can be found at https://docs.google.com/spreadsheet/ccc?key=0AmK8olNJT0Z7dEN2MklCX2g1TmhJWTk0N3VlUERVaVE&usp=drive_web#gid=0
			    		imageDesc = setSamplePrep(procedure, imageDesc);
			    			    		
			    		image.setImageDescription(imageDesc);

			    		Organism organism = new Organism();
			    		Sex sex = Sex.fromValue(norm.normalizeSex(res.getString("GENDER")));
			    		organism.setSex(sex);
			    		Age age = new Age();
			    		if (ageIsRelevant(procedure)){
			    			if (norm.isEmbryonicAge(res.getString("AGE_IN_WEEKS"))){
				    			age.setEmbryonicAge(norm.getAgeInDays(res.getString("AGE_IN_WEEKS")));
				    		} else {
				    			age.setAgeSinceBirth(norm.getAgeInDays(res.getString("AGE_IN_WEEKS")));
				    		}
				    		organism.setAge(age);
			    		}
			    		OntologyTerm stageOt = getStageFromProcedure(procedure);
			    		if (stageOt != null){
			    			organism.setStageOntologyTerm(stageOt);
			    		}
			    			
			    		organism.setTaxon("Mus musculus");
			    		image.setOrganism(organism);
			    		
			    		GeneticTrait gt = new GeneticTrait();
			    		gt.setGeneId(res.getString("gf_acc"));
			    		gt.setGeneSymbol(res.getString("GENE"));
			    		gt.setGeneticFeatureId(res.getString("acc"));
			    		gt.setGeneticFeatureName(res.getString("ALLELE"));
			    		Zygosity zyg = Zygosity.fromValue(norm.normalizeZygosity(res.getString("GENOTYPE")));
			    		gt.setZygosity(zyg);
			    		GeneticTraitArray gta = new GeneticTraitArray();
			    		gta.getEl().add(gt);
			    		image.setMutantGenotypeTraits(gta);
			    		
			    		
				        
				        
				        /* 	Channel 	*/
			    		String imageType = norm.getImageType(res.getString("procedure_name"));
			    		Channel channel = null;
			    		String channelId = "";
			    		if (imageType.equalsIgnoreCase("expression")){
					    
			    			channel = new Channel();
					        channel.setAssociatedImage(internal_id);
				    		channelId = internal_id.replace("komp2_", "komp2_channel_") + "_" + 0; // we know that for Sanger data there is at most one channel.
				    		channel.setId(channelId);
				    		channel.setExpressedGenotypeTrait(gta);
		    			}
			    			    			

				        /*	ROI	*/
			    		int k = 0;
			    		// Go through all annotations for the same image
			    		while(sameImage){
			    			
				    		Roi roi = new Roi();
				    		String roiId = internal_id.replace("komp2_", "komp2_roi_") + "_" + k;
				    		System.out.println(roiId + " " + res);
				    		roi.setId(roiId);
				    		roi.setAssociatedImmage(internal_id);
				    		// Need to decide first if we associate annotations to a ROI or to the whole image
				    		// 1. Phenotypes should always be associated to a region of interest
				    		// 2. Existing ROI should be kept if the coordinates != 0 
				    		if (res.getString("ONTOLOGY_DICT_ID").equalsIgnoreCase("1") || 
				    				(Float)res.getFloat("X_START") + (Float) res.getFloat("X_END") + (Float)res.getFloat("Y_START") + (Float)res.getFloat("Y_END") != 0 ){ // 1 = MP
				    			roi = fillRoi(roi, res, d, imageType.equalsIgnoreCase("expression"));
				    		}
				    									       
				    		// 3. Anatomy from ixpression annotations should always be associated to it's ROI
				    		// Sanger expression images: if an anatomy term is associated to the whole expression image it means there is expression in that anatomical structure
				    		else if (imageType.equalsIgnoreCase("expression"))
				    		{
				    			roi = fillRoi(roi, res, d, imageType.equalsIgnoreCase("expression"));
				    			roi.setAssociatedChannel(new StringArray());
				    			roi.getAssociatedChannel().getEl().add(channelId);
				    			channel.setAssociatedRoi(new StringArray());
				    			channel.getAssociatedRoi().getEl().add(roiId);
				    		}
				    		// Otherwise associate annotation to the whole image
				    		else {
				    			// Add annotation to the whole image
				    			if (!res.getString("TAG_VALUE").equalsIgnoreCase("null")){
									image.getObservations().getEl().add(res.getString("TAG_NAME") + ": " + res.getString("TAG_VALUE"));
								}
				    			image.setDepictedAnatomicalStructure(getAnatomyArray(new AnatomyArray(), res.getString("TERM_ID").toString(),
										res.getString("TERM_NAME").toString(), null));
				    			roi = null;
				    		}
				    		

					        if (res.next() && imageId.equalsIgnoreCase(res.getString("ID"))){
								i++;
							}
					        else {
					        	sameImage=false;
					        	res.previous();
					        }
					        
					        k++;	
					        if (roi != null){
					        	doc.getRoi().add(roi);
					        }
			    		}
			    		

			    		/*
		    		HashSet<String> anatomyIds = new HashSet<String>(); 
		    		HashSet<String> anatomyTerms = new HashSet<String>(); 
		    		HashSet<String> phenotypeIds = new HashSet<String>(); 
		    		HashSet<String> phenotypeTerms = new HashSet<String>(); 
		    		HashSet<String> observations = new HashSet<String>(); 
		    	//	HashSet<String> anatomyAnnBag = new HashSet<String>(); 
		    		
	    			image.appendChild(utils.getNewElement(JsonFields.IMAGE_TYPE, imageType , imageDoc));
	    			if (imageType.equalsIgnoreCase("expression")){
	    				
	    				// create channel
	    				channel = channelDoc.createElement("entry");
//			    		channel.appendChild(utils.getNewElement(JsonFields.DOCUMENT_TYPE, "channel", channelDoc));
	    				channel.appendChild(utils.getNewElement(JsonFields.ID, channelId, channelDoc));
	    				// associate ids
	    				channel.appendChild(utils.getNewElement(JsonFields.ASSOCIATED_IMAGE, internal_id, channelDoc));
	    				image.appendChild(utils.getNewArrrayElement(JsonFields.ASSOCIATED_CHANNEL, channelId, imageDoc));
	    				// fill expressed genetic features
	    				channel = utils.addElementToArray(channel, JsonFields.GENE_ID, res.getString("gf_acc"), channelDoc);
	    				channel = utils.addElementToArray(channel, JsonFields.GENE_NAME , res.getString("GENE"), channelDoc);
	    				channel = utils.addElementToArray(channel, JsonFields.GENETIC_FEATURE_ID , res.getString("acc"), channelDoc);
	    				channel = utils.addElementToArray(channel, JsonFields.GENETIC_FEATURE_NAME , res.getString("ALLELE"), channelDoc);
	    				// mark them as expressed genetic features too
	    				image = utils.addElementToArray(image, JsonFields.EXPRESSED_GF_LIST, res.getString("gf_acc"), imageDoc);
		    			image = utils.addElementToArray(image, JsonFields.EXPRESSED_GF_LIST , res.getString("GENE"), imageDoc);
		    			image = utils.addElementToArray(image, JsonFields.EXPRESSED_GF_LIST , res.getString("acc"), imageDoc);
		    			image = utils.addElementToArray(image, JsonFields.EXPRESSED_GF_LIST , res.getString("ALLELE"), imageDoc);
		    			    			
	    			}
		 */
			    		
			    		// !!!  Last thing in this block  !!!
				        doc.getImage().add(image);
				        if (channel != null){
				        	doc.getChannel().add(channel);
				        }
		    		}
	        	}
	    		i++;
	    		if (i == 100)
	    			break;
	        }
			
	        File file = new File("source/main/resources/sangerExport.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Doc.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(doc, file);
			jaxbMarshaller.marshal(doc, System.out);
					
			
			}catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			} catch (DOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
       
	}
	 private Roi fillRoi(Roi roi, ResultSet res, Dimensions d, Boolean isExpressionImg){
     		// From TAG NAMES  & VALUES  I need to make observations
			try {
				if (!res.getString("TAG_VALUE").equalsIgnoreCase("null")){
					roi.getObservations().getEl().add(res.getString("TAG_NAME") + ": " + res.getString("TAG_VALUE"));
				}
		
				// Add anat. terms
				if (res.getString("ONTOLOGY_DICT_ID").toString().equalsIgnoreCase("2") || res.getString("ONTOLOGY_DICT_ID").toString().equalsIgnoreCase("4")){ //2=EMAP, 4=MA
					if (isExpressionImg){
						// we have expression in the annotated anatomy term
						roi.setAnatomyExpressionAnnotations(getAnatomyArray(new AnatomyArray(), res.getString("TERM_ID").toString(),
								res.getString("TERM_NAME").toString(), null));
					}
					else { 
						// we have somthin interesting but not expression in the anatomy term
						roi.setAnatomicalPartOfInterest(getAnatomyArray(new AnatomyArray(), res.getString("TERM_ID").toString(),
								res.getString("TERM_NAME").toString(), null));
					}
				}
				// Add phenotuype terms
				else if (res.getString("ONTOLOGY_DICT_ID").toString().equalsIgnoreCase("1") ){ // 1 = MP
					
					// we know there's only one phenotype associated so we don't need to check if the array is empty					
					roi.setPhenotypeAnnotations(getPhenotypeArray(new PhenotypeArray(), res.getString("TERM_ID").toString(), res.getString("TERM_NAME").toString(), null));
				}
				
	        	Coordinates coord = new Coordinates();
	        	FloatArray xCoord = new FloatArray();
	        	FloatArray yCoord = new FloatArray();
	        	
		        // Add coordinates
		        if ( (Float) res.getFloat("X_START") + (Float) res.getFloat("X_END") + (Float)res.getFloat("Y_START") + (Float)res.getFloat("Y_END") != 0 ){
		        	// we have coordinates 
		        	// Order is important: (start, end)
		        	// Sanger stores percentages so no need to compute them here, we just copy 
		        	xCoord.getEl().add((Float) res.getFloat("X_START") ); 
		        	xCoord.getEl().add((Float) res.getFloat("X_END") );
		        	coord.setXCoordinates(xCoord);
		        	
		        	// Order is important: (start, end)
		        	yCoord.getEl().add((Float) res.getFloat("Y_START") ); 
		        	yCoord.getEl().add((Float) res.getFloat("Y_END") );
		        	coord.setYCoordinates(yCoord);
		        }	
		        else  {
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
	 
	 ImageDescription setSamplePrep (String procedure, ImageDescription desc){
			OntologyTermArray sp = new OntologyTermArray();
			OntologyTermArray im = new OntologyTermArray();
			OntologyTermArray vm = new OntologyTermArray();
			
		if (procedure.equalsIgnoreCase("Dysmorphology")){
			sp.getEl().add(getOntologyTerm("living tissue" , "FBbi_00000025"));
			sp.getEl().add(getOntologyTerm("whole mounted tissue" , "FBbi_00000024"));
			im.getEl().add(getOntologyTerm("macroscopy", "FBbi_00000240"));
		}
		else if (procedure.equalsIgnoreCase("Embryo dysmorphology")){
			sp.getEl().add(getOntologyTerm("whole mounted tissue" , "FBbi_00000024"));
			im.getEl().add(getOntologyTerm("bright-field microscopy", "FBbi_00000243"));
		}
		else if (procedure.equalsIgnoreCase("Xray")){
			sp.getEl().add(getOntologyTerm("whole mounted tissue" , "FBbi_00000024"));
			sp.getEl().add(getOntologyTerm("living tissue" , "FBbi_00000025"));
			im.getEl().add(getOntologyTerm("X-ray radiography", "FBbi_00001001"));
		}
		else if (procedure.equalsIgnoreCase("Eye Morphology")){ // &&  slit lamp
			sp.getEl().add(getOntologyTerm("whole mounted tissue" , "FBbi_00000024"));
			sp.getEl().add(getOntologyTerm("living tissue" , "FBbi_00000025"));
			im.getEl().add(getOntologyTerm("macroscopy", "FBbi_00000240"));
		}
/*		else if (procedure.equalsIgnoreCase("Eye Morphology")){ // && TEFI
			sp.getEl().add(getOntologyTerm("whole mounted tissue" , "FBbi_00000024"));
			sp.getEl().add(getOntologyTerm("living tissue" , "FBbi_00000025"));
			im.getEl().add(getOntologyTerm("light microscopy", "FBbi_00000345"));
		}
		*/
		else if (procedure.equalsIgnoreCase("Expression")){
			sp.getEl().add(getOntologyTerm("whole mounted tissue" , "FBbi_00000024"));
			im.getEl().add(getOntologyTerm("bright-field microscopy", "FBbi_00000243"));
			vm.getEl().add(getOntologyTerm("visualisation of genetically encoded beta-galactosidase", "FBbi_00000077"));
		}
		else if (procedure.equalsIgnoreCase("Tail Epidermis Wholemount")){
			sp.getEl().add(getOntologyTerm("whole mounted tissue" , "FBbi_00000024"));
			im.getEl().add(getOntologyTerm("confocal microscopy", "FBbi_00000251"));
			vm.getEl().add(getOntologyTerm("fluorescent protein tag", "FBbi_00000405"));
		}
		else if (procedure.equalsIgnoreCase("Histology Slide")){
			im.getEl().add(getOntologyTerm("bright-field microscopy", "FBbi_00000243"));
			vm.getEl().add(getOntologyTerm("optically dense stain", "FBbi_00000567"));
		}
/*		else if (procedure.equalsIgnoreCase("Histology Slide")){
			
		}
		*/
		else if (procedure.equalsIgnoreCase("Brain Histopathology")){
			sp.getEl().add(getOntologyTerm("sectioned tissue" , "FBbi_00000026"));
			im.getEl().add(getOntologyTerm("confocal microscopy", "FBbi_00000251"));
			vm.getEl().add(getOntologyTerm("fluorescent protein tag", "FBbi_00000405"));
		}
		
		desc.setSamplePreparation(sp);
		desc.setImagingMethod(im);
		desc.setVisualisationMethod(vm);
		return desc;
	 }
	 
	 OntologyTerm getOntologyTerm (String label, String id){
		 OntologyTerm term = new OntologyTerm();
		 term.setTermId(id);
		 term.setTermLabel(label);
		 return term;
	 }
	 
	 PhenotypeArray getPhenotypeArray(PhenotypeArray pa, String id, String label, String freetext){
		 Phenotype p = new Phenotype();
		 if (id != null)
			 p.setPhenotypeOntologyId(id);

		 if (label != null)
			 p.setPhenotypeOntologyTerm(label);

		 if (freetext != null)
			 p.setPhenotypeFreetext(freetext);
		 
		 pa.getEl().add(p);
		 return pa;
	 }
	 
	 AnatomyArray getAnatomyArray(AnatomyArray pa, String id, String label, String freetext){
		 Anatomy p = new Anatomy();
		 if (id != null)
			 p.setAnatomyOntologyId(id);

		 if (label != null)
			 p.setAnatomyOntologyTerm(label);

		 if (freetext != null)
			 p.setAnatomyFreetext(freetext);
		 
		 pa.getEl().add(p);
		 return pa;
	 }
	 
	 // On live procedures the age is not relevant and we should not import it. It does not mean it's the age at which the picture was taken.
	 // See David's document https://docs.google.com/spreadsheet/ccc?key=0AmK8olNJT0Z7dEN2MklCX2g1TmhJWTk0N3VlUERVaVE&usp=drive_web#gid=0
	 boolean ageIsRelevant(String procedure){
		 if (procedure.equalsIgnoreCase("Dysmorphology") ||
				 procedure.equalsIgnoreCase("Xray") ||
				 procedure.equalsIgnoreCase("Eye Morphology") ){
			 return false;
		 }
		 else return true;
	 }
	 
	 OntologyTerm getStageFromProcedure(String procedure){
		 OntologyTerm ot = null;
		 if (procedure.equalsIgnoreCase("Dysmorphology") ||
				 procedure.equalsIgnoreCase("Xray") ||
				 procedure.equalsIgnoreCase("Eye Morphology") ){
			ot = new OntologyTerm();
			ot.setTermId("MmusDv_0000092");
			ot.setTermLabel("postnatal stage");
		 }
		 return ot;
	 }
}

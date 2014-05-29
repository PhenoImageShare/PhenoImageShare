package uk.ac.ebi.phis.importer;

import j.Age;
import j.Dimensions;
import j.Image;
import j.ImageDescription;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
                  	
						
			int i = 0;
	        while ( res.next()){

	        	if (res.getString("gf_acc") != null && res.getString("gf_acc").startsWith("MGI:")){
    				
		        	boolean sameImage = true;
		        	
		        	String internal_id =  "komp2_" + i;
		        	
		        	String imageId = res.getString("ID");		        
		    		
		    		String url = "http://www.mousephenotype.org/data/media/" + res.getString("FULL_RESOLUTION_FILE_PATH") ;
		    		Map<String, Integer> dimensions = utils.getImageMeasuresFromUrl(url);
		    		if (dimensions != null){// the image could be loaded 
		    		
		    			Dimensions d = new Dimensions();
		    			d.setImageHeight(dimensions.get("height"));
		    			d.setImageWidth(dimensions.get("width"));
		    			
			    		Image image = new Image();
			    		
			    		ImageDescription imageDesc = new ImageDescription();
			    		imageDesc.setImageUrl(url);
			    		imageDesc.setOriginalImageId(res.getString("ID"));
			    		imageDesc.setImageDimensions(d);
			    		
			    		image.setId(internal_id);
			    		
			    		Age age = new Age();
			    		if (norm.isEmbryonicAge(res.getString("AGE_IN_WEEKS"))){
			    			age.setEmbryonicAge(norm.getAgeInDays(res.getString("AGE_IN_WEEKS")));
			    		} else {
			    			age.setAgeSinceBirth(norm.getAgeInDays(res.getString("AGE_IN_WEEKS")));
			    		}
			    		
		    		
	    			image.appendChild(utils.getNewElement( JsonFields.AGE, "" + norm.normalizeAge(res.getString("AGE_IN_WEEKS")), imageDoc));  	
	    			image.appendChild(utils.getNewElement( JsonFields.CENTER , "WTSI", imageDoc));
	    			image.appendChild(utils.getNewElement( JsonFields.SEX , norm.normalizeSex(res.getString("GENDER")), imageDoc));
	    			image.appendChild(utils.getNewElement( JsonFields.ORGANISM , "Mus musculus" , imageDoc));
	    			
	    			image= utils.addElementToArray(image, JsonFields.GENE_ID, res.getString("gf_acc"), imageDoc);
	    			image= utils.addElementToArray(image, JsonFields.GENE_NAME , res.getString("GENE"), imageDoc);
	    			image= utils.addElementToArray(image, JsonFields.GENETIC_FEATURE_ID , res.getString("acc"), imageDoc);
	    			image= utils.addElementToArray(image, JsonFields.GENETIC_FEATURE_NAME , res.getString("ALLELE"), imageDoc);
	    			image= utils.addElementToArray(image, JsonFields.ZYGOSITY , norm.normalizeZygosity(res.getString("GENOTYPE")), imageDoc);
	    			
	    			image.appendChild(utils.getNewElement( JsonFields.PROCEDURE , res.getString("procedure_name"), imageDoc));
	    			
	    			
		    		HashSet<String> anatomyIds = new HashSet<String>(); 
		    		HashSet<String> anatomyTerms = new HashSet<String>(); 
		    		HashSet<String> phenotypeIds = new HashSet<String>(); 
		    		HashSet<String> phenotypeTerms = new HashSet<String>(); 
		    		HashSet<String> observations = new HashSet<String>(); 
		    	//	HashSet<String> anatomyAnnBag = new HashSet<String>(); 
		    		
		    		String imageType = norm.getImageType(res.getString("procedure_name"));
	    			image.appendChild(utils.getNewElement(JsonFields.IMAGE_TYPE, imageType , imageDoc));
	    			if (imageType.equalsIgnoreCase("expression")){
	    				
	    				// create channel
	    				channel = channelDoc.createElement("entry");
//			    		channel.appendChild(utils.getNewElement(JsonFields.DOCUMENT_TYPE, "channel", channelDoc));
			    		String channelId = internal_id.replace("komp2_", "komp2_channel_") + 0;
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
		    		
		        	// get all anatomy anntoations
		        	while(sameImage){
		        		
		        		// From TAG NAMES  & VALUES  I need to make observations
		    			if (!res.getString("TAG_VALUE").equalsIgnoreCase("null")){
		    				observations.add(res.getString("TAG_NAME") + ": " + res.getString("TAG_VALUE"));
		    			}
		    			
		    			// Add pehnotype & anat. terms
		    			if (res.getString("ONTOLOGY_DICT_ID").toString().equalsIgnoreCase("2") || res.getString("ONTOLOGY_DICT_ID").toString().equalsIgnoreCase("4")){ //2=EMAP, 4=MA
		    				anatomyIds.add(res.getString("TERM_ID").toString());
		    				anatomyTerms.add(res.getString("TERM_NAME").toString());
		    			}
				        // Add ROIs
				        if ((Float) res.getFloat("X_START") +(Float) res.getFloat("X_END") + (Float)res.getFloat("Y_START") + (Float)res.getFloat("Y_END") != 0 
				        		|| res.getString("ONTOLOGY_DICT_ID").toString().equalsIgnoreCase("1")){
				        	HashMap<String, Element> list = utils.addRoiSangerData(image, channel, roiDoc, imageDoc,channelDoc, res, 0);
				        	image = list.get("image");
				        	roiRoot.appendChild(list.get("roi"));
				        	channel = list.get("channel");
				        }	
		    			
				        if (res.next() && imageId.equalsIgnoreCase(res.getString("ID"))){
	    					i++;
	    				}
				        else {
				        	sameImage=false;
				        	res.previous();
				        }
		    		}
			        	
		        	image.appendChild(utils.getNewArrrayElement( JsonFields.ANATOMY_ID , anatomyIds , imageDoc));
		        	image.appendChild(utils.getNewArrrayElement( JsonFields.ANATOMY_TERM , anatomyTerms , imageDoc));
		        	image.appendChild(utils.getNewArrrayElement( JsonFields.PHENOTYPE_ID, phenotypeIds, imageDoc));
		        	image.appendChild(utils.getNewArrrayElement( JsonFields.PHENOTYPE_TERM , phenotypeTerms , imageDoc));
		        	image.appendChild(utils.getNewArrrayElement( JsonFields.OBSERVATIONS , observations  , imageDoc));
	
	    			imageRoot.appendChild(image);
		    		}
	        	}
	    		i++;
		        
	        }
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(imageDoc);
			StreamResult result = new StreamResult(new File("source/main/resources/sangerImagesDump_imageCore.xml"));
			transformer.transform(source, result);
			
			source = new DOMSource(roiDoc);
			result = new StreamResult(new File("source/main/resources/sangerImagesDump_roiCore.xml"));
			transformer.transform(source, result);
			
			source = new DOMSource(channelDoc);
			result = new StreamResult(new File("source/main/resources/sangerImagesDump_channelCore.xml"));
			transformer.transform(source, result);
				
			System.out.println("Files saved!");
			
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
}

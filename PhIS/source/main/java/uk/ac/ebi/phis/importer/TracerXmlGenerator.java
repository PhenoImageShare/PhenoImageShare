package uk.ac.ebi.phis.importer;

import j.Dimensions;
import j.Doc;
import j.Image;
import j.ImageDescription;
import j.OntologyTerm;
import j.OntologyTermArray;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import uk.ac.ebi.phis.utils.ontology.GenomicLocation;
import uk.ac.ebi.phis.utils.ontology.JsonFields;
import uk.ac.ebi.phis.utils.ontology.Normalizer;
import uk.ac.ebi.phis.utils.ontology.OntologyMapperPredefinedTypes;
import uk.ac.ebi.phis.utils.ontology.Utils;


public class TracerXmlGenerator {
	
	Normalizer norm;
	Utils utils;
	
	// hand made mapping between TRACER anatomy terms and EMAPA ids. See e-mail thread Fwd: [Phenoimageshare] Call 6 February 2014
	Map<String, String> emapLabels = new HashMap<String, String>(); 
	Map<String, String> emapIds = new HashMap<String, String>(); 
	
	public TracerXmlGenerator(){
		norm = new Normalizer();
		utils = new Utils(OntologyMapperPredefinedTypes.MA_MP);
		emapLabels.put("cranial ganglia", "cranial ganglion"); emapIds.put("cranial ganglia", "EMAPA:16659");
		emapLabels.put("digestive", "alimentary system"); emapIds.put("digestive", "EMAPA:16246");
		emapLabels.put("dorsal root ganglia", "dorsal root ganglion"); emapIds.put("dorsal root ganglia", "EMAPA:16668");
		emapLabels.put("ear", "ear"); emapIds.put("ear", "EMAPA:16193");
		emapLabels.put("eye", "eye"); emapIds.put("eye", "EMAPA:16198");
		emapLabels.put("face", "head"); emapIds.put("face", "EMAPA:31858");
		emapLabels.put("forebrain", "forebrain"); emapIds.put("forebrain", "EMAPA:16895");
		emapLabels.put("heart", "heart"); emapIds.put("heart", "EMAPA:16105");
		emapLabels.put("hindbrain", "hindbrain"); emapIds.put("hindbrain", "EMAPA:16916");
		emapLabels.put("limb", "limb"); emapIds.put("limb", "EMAPA:16405");
		emapLabels.put("midbrain", "midbrain"); emapIds.put("midbrain", "EMAPA:16974");
		emapLabels.put("somites", "somite group"); emapIds.put("somites", "EMAPA:31169");
	}
	
	public void read() throws IOException{
    
        ApplicationContext ac = new ClassPathXmlApplicationContext("app-config.xml");
		DataSource dataSource = (DataSource) ac.getBean("tracerDB");
        
		
        String command = "SELECT sl.sb_id, sl.sbname, l.name as source, c.name as chr_name, si.position, si.strand, "
        		+ "it.name AS insertion_type, ei.name AS image_name, ei.file_path, ei.stage, ei.comment AS image_comment, "
        		+ "ed.name AS expression_domain_name, sbg.name AS gene_name, sbg.ensembl_gene_id, sbg.mgi_id "
        		+ "FROM "
        		+ "sb_lines sl, labs l, sb_insertions si, insertion_types it, chromosomes c, expression_images ei, expression_image_domains eid, "
        		+ "expression_domains ed, sb_genes sbg  "
        		+ "WHERE"
        		+ " sl.sb_id=si.sb_id AND sl.lab_id=l.lab_id AND si.chr_id=c.chr_id AND si.insertion_type_id=it.insertion_type_id "
        		+ "AND sl.sb_id=ei.sb_id AND ei.exp_image_id=eid.exp_image_id AND eid.exp_domain_id=ed.exp_domain_id AND "
        		+ "ei.display_mode='public' AND sbg.sb_id=sl.sb_id AND sl.display_mode='public'";
        
       // List<Map<String, Object>> results = jdbcTemplate.queryForList(command);
        
       
        try {
        	
        	PreparedStatement statement = dataSource.getConnection().prepareStatement(command);
     		ResultSet res = statement.executeQuery();
             
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root element = doc
			Doc doc = new Doc();			
			int i = 0;
			
	        while ( res.next()){
	        	boolean sameImage = true;
	        	String imageName = res.getString("image_name");
	        	String internal_id =  "tracer_" + i;
	        		    		
	    		String url = "http://www.ebi.ac.uk/panda-srv/tracer/sblac/" + res.getString("file_path") + "/" + res.getString("image_name") + ".jpg";
	    		
	    		Map<String, Integer> dimensions = utils.getImageMeasuresFromUrl(url);
	    		
	    		if (dimensions != null){ //index info only if the image could be loaded 	    		

		    		Image image = new Image();
		    		image.setId(internal_id);
	    			
	    			Dimensions d = new Dimensions();
	    			d.setImageHeight(dimensions.get("height"));
	    			d.setImageWidth(dimensions.get("width"));
		    		ImageDescription imageDesc = new ImageDescription();
		    		imageDesc.setImageUrl(url);
		    		imageDesc.setOriginalImageId(res.getString("ID"));
		    		imageDesc.setImageDimensions(d);
		      		imageDesc.setOrganismGeneratedBy("Spitz Lab, EMBL");
		    		imageDesc.setImageGeneratedBy("Spitz Lab, EMBL");
		    		imageDesc.setHostName("Tracer Database");
		    		imageDesc.setHostUrl("http://www.ebi.ac.uk/panda-srv/tracer/");
		    		
		    		OntologyTermArray sp = new OntologyTermArray();
		    		sp.getEl().add(getOntologyTerm("whole mounted tissue", "FBbi_00000024"));
		    		imageDesc.setSamplePreparation(sp);
		    		
		    		OntologyTermArray vm = new OntologyTermArray();
		    		vm.getEl().add(getOntologyTerm("visualisation of genetically encoded beta-galactosidase", "FBbi_00000077"));
		    		imageDesc.setVisualisationMethod(vm);
		    		
		    		OntologyTermArray im = new OntologyTermArray();
		    		im.getEl().add(getOntologyTerm("light microscopy", "FBbi_00000345"));
		    		imageDesc.setImagingMethod(im);
		    		
		    		image.setImageDescription(imageDesc);
		    		
	    		}
	    		
	    		
	        }
	        
        }catch(Exception e){
        	e.printStackTrace();
        }
	    		/*
	    		image.appendChild(utils.getNewElement(JsonFields.IMAGE_URL, url, imageDoc));
	    		image.appendChild(utils.getNewElement(JsonFields.DATA_SOURCE, "TRACER", imageDoc));
	    			    		
	    		// in TRACER there are no regions of interest so I need to set the default whole image ROI
	    		Map<String, Integer> dimensions = utils.getImageMeasuresFromUrl(url);
	    		if (dimensions != null && res.getString("mgi_id") != null){// the image could be loaded 
	    			Element expressedAnatomyList = utils.getNewElement(JsonFields.EXPRESSED_ANATOMY_ANN_LIST, "", imageDoc);
	    			ArrayList<Object> x = new ArrayList<Object>();
	    			x.add(0);
	    			x.add(dimensions.get("width"));
	    			ArrayList<Object> y = new ArrayList<Object>();
	    			y.add(0);
	    			y.add(dimensions.get("height"));
	    			
	    			roi.appendChild(utils.getNewArrrayElement(JsonFields.X_COORDINATES, x, roiDoc));
	    			roi.appendChild(utils.getNewArrrayElement( JsonFields.Y_COORDINATES, y, roiDoc));
	    			
	    			image.appendChild(utils.getNewElement(JsonFields.WIDTH, ""+dimensions.get("width"), imageDoc));
	    			image.appendChild(utils.getNewElement( JsonFields.HEIGHT , ""+dimensions.get("height") , imageDoc ));
	    			
	    			// Generate internal_id
	    			String uniqueId = internal_id;
	    			image.appendChild(utils.getNewElement( JsonFields.ID , uniqueId, imageDoc ));
	    			
	    			String roiId = "tracer_roi_" + i;
	    			roi.appendChild(utils.getNewElement( JsonFields.ID , roiId, roiDoc ));
	    			image.appendChild(utils.getNewArrrayElement(JsonFields.ASSOCIATED_ROI, roiId, imageDoc));
	    			roi.appendChild(utils.getNewElement(JsonFields.ASSOCIATED_IMAGE, uniqueId, roiDoc));
	    			
	    			String channelId = "tracer_channel_" + i;
	    			channel.appendChild(utils.getNewElement( JsonFields.ID , channelId, channelDoc ));
	    			channel.appendChild(utils.getNewElement(JsonFields.ASSOCIATED_IMAGE, uniqueId, channelDoc));
	    			channel.appendChild(utils.getNewArrrayElement(JsonFields.ASSOCIATED_ROI, roiId, channelDoc));
	    			image.appendChild(utils.getNewArrrayElement(JsonFields.ASSOCIATED_CHANNEL, channelId, imageDoc));
	    			roi.appendChild(utils.getNewArrrayElement(JsonFields.ASSOCIATED_CHANNEL, channelId, roiDoc));
	    			
	    			
	    			// in the TRACER fields 'stage' they actually store dpc, with values like E11.5
	    			String age = norm.normalizeAge(res.getString("stage"));
	    			image.appendChild(utils.getNewElement( JsonFields.EMBRYO_AGE, ""+age, imageDoc));
	    					
	    			// TODO image type
	    			image.appendChild(utils.getNewElement( JsonFields.IMAGE_TYPE , "expression" , imageDoc));
	    				    			
	    			image.appendChild(utils.getNewElement( JsonFields.CENTER , res.getString("source")  , imageDoc));
	    			
	    			image.appendChild(utils.getNewElement( JsonFields.ORGANISM , "Mus musculus" , imageDoc));
	    			
    				channel.appendChild(utils.getNewArrrayElement( JsonFields.CHROMOSOME , res.getString("chr_name") , channelDoc));

    				channel.appendChild(utils.getNewArrrayElement( JsonFields.STRAND , res.getString("strand") , channelDoc));
    				
    				channel.appendChild(utils.getNewArrrayElement( JsonFields.INSERTION_SITE , res.getString("position") , channelDoc));
	    			
	    			if (res.getString("image_comment") != null)
	    				image.appendChild(utils.getNewArrrayElement( JsonFields.OBSERVATIONS , res.getString("image_comment") , imageDoc));

	    		HashSet<String> anatomyIds = new HashSet<String>(); 
	    		HashSet<String> anatomyTerms = new HashSet<String>(); 
	    		HashSet<String> anatomyComputedIds = new HashSet<String>(); 
	    		HashSet<String> anatomyComputedTerms = new HashSet<String>(); 
	    		HashSet<String> anatomyFreeText = new HashSet<String>(); 
	    		HashSet<String> anatomyAnnBag = new HashSet<String>(); 
	    		
	        	// get all anntoations associated to the same image => need to collapse rows
	        	while(sameImage){
	        		String anat =  res.getString("expression_domain_name");
	    			anatomyFreeText.add(anat);
	    			anatomyAnnBag.add(anat);
	    			if (emapIds.containsKey(anat)){	
	    				anatomyIds.add(emapIds.get(anat));
	    				anatomyTerms.add(emapLabels.get(anat));
	    				anatomyAnnBag.add(emapIds.get(anat));
	    				anatomyAnnBag.add(emapLabels.get(anat));
	    			}
	    			else {	
	    				try {
	    					if (!getAnatomyId(anat, age).equalsIgnoreCase("")){
	    						anatomyComputedIds.add(getAnatomyId(anat, age));
	    						anatomyComputedTerms.add(getAnatomyLabels(anat, age));
	    						anatomyAnnBag.add(getAnatomyId(anat, age));
	    						anatomyAnnBag.add(getAnatomyLabels(anat, age));	    						
	    					}
	    				} catch (JSONException e) {
	    					e.printStackTrace();
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}
	    			}
	    			image.appendChild(expressedAnatomyList);
	    			
	    			if (res.next() && imageName.equalsIgnoreCase(res.getString("image_name"))){
	    					i++;
	    				}
	    			else {
	    				sameImage=false;
	    				res.previous();
	        		}
	    		}

				roi.appendChild(utils.getNewArrrayElement( JsonFields.ANATOMY_ID , anatomyIds , roiDoc));
				roi.appendChild(utils.getNewArrrayElement( JsonFields.ANATOMY_TERM , anatomyTerms , roiDoc));
    			roi.appendChild(utils.getNewArrrayElement( JsonFields.ANATOMY_FREETEXT, anatomyFreeText, roiDoc));
				roi.appendChild(utils.getNewArrrayElement( JsonFields.ANATOMY_COMPUTED_ID , anatomyComputedIds , roiDoc));
				roi.appendChild(utils.getNewArrrayElement( JsonFields.ANATOMY_COMPUTED_TERM , anatomyComputedTerms  , roiDoc));
				image.appendChild(utils.getNewArrrayElement( JsonFields.EXPRESSED_ANATOMY_ANN_LIST , anatomyAnnBag , imageDoc));

    			imageRroot.appendChild(image);
    			roiRoot.appendChild(roi);
    			channelRoot.appendChild(channel);
    			
	        }
	        }
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(imageDoc);
			StreamResult result = new StreamResult(new File("source/main/resources/tracerImagesDump_imageCore.xml"));
			transformer.transform(source, result);
			
			source = new DOMSource(roiDoc);
			result = new StreamResult(new File("source/main/resources/tracerImagesDump_roiCore.xml"));
			transformer.transform(source, result);
			
			source = new DOMSource(channelDoc);
			result = new StreamResult(new File("source/main/resources/tracerImagesDump_channelCore.xml"));
			transformer.transform(source, result);
			
			System.out.println("File saved!");
			
			}catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        */
	}
	
	
	private String getAnatomyId(String anat, String ageInDays) throws Exception{
		String res = "";
		if (ageInDays.equalsIgnoreCase(""))
			return res;
		float age = new Float(ageInDays);
		int ts = convertAgeToTs(age);
		if (anat.equalsIgnoreCase("genital bud")){
			if (ts <= 18)
				return "EMAPA:30074";
			else if (ts == 19)
				return "EMAPA:30074";
//			else throw new Exception("I don't know the sex!!!");
		}
		else if (anat.equalsIgnoreCase("urogenital")){
			if (ts <= 18)
				return "EMAPA:16367";
			else 
				return "EMAPA:17381";	
		}
		else if (anat.equalsIgnoreCase("neural tube")){
			if (ts <= 19)
				return "EMAPA:16525";
			else return "EMAPA:17577";
		}		
		return res;
	}
	
	private String getAnatomyLabels(String anat, String ageInDays) throws Exception{
		String res = "";
		if (ageInDays.equalsIgnoreCase(""))
			return "";
		float age = new Float(ageInDays);
		int ts = convertAgeToTs(age);
		if (anat.equalsIgnoreCase("genital bud")){
			if (ts <= 18)
				return "genital swelling";
			else if (ts == 19)
				return "genital tubercle";
//			else throw new Exception("I don't know the sex!!!");
		}
		else if (anat.equalsIgnoreCase("urogenital")){
			if (ts <= 18)
				return "genitourinary system";
			else return "reproductive system";
		}
		else if (anat.equalsIgnoreCase("neural tube")){
			if (ts <= 19)
				return "future spinal cord";
			else return "spinal cord";
		}		
		return res;
	}
	
	private int convertAgeToTs(Float eAge){
		if (eAge <= 1)	return 2;
		if (eAge == 2) return 3;
		if (eAge == 3) return 4;
		if (eAge == 4) return 5;
		if (eAge == 4.5) return 6;
		if (eAge == 5) return 7;
		if (eAge == 6) return 8;
		if (eAge == 6.5) return 9;
		if (eAge == 7) return 10;
		if (eAge == 7.5) return 11;
		if (eAge == 8) return 12;
		if (eAge == 8.5) return 13;
		if (eAge == 9) return 14;
		if (eAge == 9.5) return 15;
		if (eAge == 10) return 16;
		if (eAge == 10.5) return 17;
		if (eAge == 11) return 18;
		if (eAge == 11.5) return 19;
		if (eAge == 12) return 20;
		if (eAge == 13) return 21;
		if (eAge == 14) return 22;
		if (eAge == 15) return 23;
		if (eAge == 16) return 24;
		if (eAge == 17) return 25;
		if (eAge == 18) return 26;
		if (eAge == 19) return 27;
		return -1;
	}

	OntologyTerm getOntologyTerm (String label, String id){
		 OntologyTerm term = new OntologyTerm();
		 term.setTermId(id);
		 term.setTermLabel(label);
		 return term;
	 }
}
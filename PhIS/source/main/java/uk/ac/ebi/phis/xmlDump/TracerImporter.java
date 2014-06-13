package uk.ac.ebi.phis.xmlDump;

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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import uk.ac.ebi.phis.utils.EnrichingUtils;
import uk.ac.ebi.phis.utils.JsonFields;
import uk.ac.ebi.phis.utils.Normalizer;
import uk.ac.ebi.phis.utils.Utils_deprecated;
import uk.ac.ebi.phis.utils.ontology.OntologyMapperPredefinedTypes;


public class TracerImporter {
	
	Normalizer norm;
	Utils_deprecated utils;
	
	// hand made mapping between TRACER anatomy terms and EMAPA ids. See e-mail thread Fwd: [Phenoimageshare] Call 6 February 2014
	Map<String, String> labels = new HashMap<String, String>(); 
	Map<String, String> emapIds = new HashMap<String, String>(); 
	public TracerImporter(){
		norm = new Normalizer();
		utils = new Utils_deprecated(OntologyMapperPredefinedTypes.MA_MP);
		labels.put("cranial ganglia", "cranial ganglion"); emapIds.put("cranial ganglia", "EMAPA:16659");
		labels.put("digestive", "alimentary system"); emapIds.put("digestive", "EMAPA:16246");
		labels.put("dorsal root ganglia", "dorsal root ganglion"); emapIds.put("dorsal root ganglia", "EMAPA:16668");
		labels.put("ear", "ear"); emapIds.put("ear", "EMAPA:16193");
		labels.put("eye", "eye"); emapIds.put("eye", "EMAPA:16198");
		labels.put("face", "head"); emapIds.put("face", "EMAPA:31858");
		labels.put("forebrain", "forebrain"); emapIds.put("forebrain", "EMAPA:16895");
		labels.put("heart", "heart"); emapIds.put("heart", "EMAPA:16105");
		labels.put("hindbrain", "hindbrain"); emapIds.put("hindbrain", "EMAPA:16916");
		labels.put("limb", "limb"); emapIds.put("limb", "EMAPA:16405");
		labels.put("midbrain", "midbrain"); emapIds.put("midbrain", "EMAPA:16974");
		labels.put("somites", "somite group"); emapIds.put("somites", "EMAPA:31169");
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
       
        try {
        	
        	PreparedStatement statement = dataSource.getConnection().prepareStatement(command);
     		ResultSet res = statement.executeQuery();
             
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root elements
			Document imageDoc = docBuilder.newDocument();
			Element imageRroot = imageDoc.createElement("doc");
			
			Document roiDoc = docBuilder.newDocument();
			Element roiRoot = roiDoc.createElement("doc");
			
			Document channelDoc = docBuilder.newDocument();
			Element channelRoot = channelDoc.createElement("doc");

			imageDoc.appendChild(imageRroot);
			roiDoc.appendChild(roiRoot);
			channelDoc.appendChild(channelRoot);
			int i = 0;
	        while (res.next()){
	        	boolean sameImage = true;
	        	String imageName = res.getString("image_name");
	        	String internal_id =  "tracer_" + i;
	        	
	        	Element image = imageDoc.createElement("entry");
	        	Element roi = roiDoc.createElement("entry");
	        	Element channel = channelDoc.createElement("entry");
	    		
	    		String url = "http://www.ebi.ac.uk/panda-srv/tracer/sblac/" + res.getString("file_path") + "/" + res.getString("image_name") + ".jpg";
	    		
	    		image.appendChild(utils.getNewElement(JsonFields.IMAGE_URL, url, imageDoc));
	    		image.appendChild(utils.getNewElement(JsonFields.DATA_SOURCE, "TRACER", imageDoc));
//	    		image.appendChild(utils.getNewElement(JsonFields.DOCUMENT_TYPE, "image", imageDoc));
//	    		roi.appendChild(utils.getNewElement(JsonFields.DOCUMENT_TYPE, "roi", roiDoc));
//	    		channel.appendChild(utils.getNewElement(JsonFields.DOCUMENT_TYPE, "channel", channelDoc));
	    			    		
	    		// in TRACER there are no regions of interest so I need to set the default whole image ROI
	    		Map<String, Integer> dimensions = EnrichingUtils.getImageMeasuresFromUrl(url);
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
	    				anatomyTerms.add(labels.get(anat));
	    				anatomyAnnBag.add(emapIds.get(anat));
	    				anatomyAnnBag.add(labels.get(anat));
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
	
}
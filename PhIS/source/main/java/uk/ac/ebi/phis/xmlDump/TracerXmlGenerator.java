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

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;

import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.ac.ebi.phis.jaxb.Annotation;
import uk.ac.ebi.phis.jaxb.AnnotationArray;
import uk.ac.ebi.phis.jaxb.AnnotationMode;
import uk.ac.ebi.phis.jaxb.Channel;
import uk.ac.ebi.phis.jaxb.Coordinates;
import uk.ac.ebi.phis.jaxb.Dimensions;
import uk.ac.ebi.phis.jaxb.Doc;
import uk.ac.ebi.phis.jaxb.ExpressionAnnotation;
import uk.ac.ebi.phis.jaxb.ExpressionAnnotationArray;
import uk.ac.ebi.phis.jaxb.GenomicLocation;
import uk.ac.ebi.phis.jaxb.GenotypeComponent;
import uk.ac.ebi.phis.jaxb.Image;
import uk.ac.ebi.phis.jaxb.ImageDescription;
import uk.ac.ebi.phis.jaxb.ImageType;
import uk.ac.ebi.phis.jaxb.ImageTypeArray;
import uk.ac.ebi.phis.jaxb.Link;
import uk.ac.ebi.phis.jaxb.OntologyTerm;
import uk.ac.ebi.phis.jaxb.Organism;
import uk.ac.ebi.phis.jaxb.PercentArray;
import uk.ac.ebi.phis.jaxb.Roi;
import uk.ac.ebi.phis.jaxb.SampleType;
import uk.ac.ebi.phis.jaxb.StringArray;
import uk.ac.ebi.phis.utils.EnrichingUtils;
import uk.ac.ebi.phis.utils.Normalizer;


public class TracerXmlGenerator {
	
	Normalizer norm;
	
	// hand made mapping between TRACER anatomy terms and EMAPA ids. See e-mail thread Fwd: [Phenoimageshare] Call 6 February 2014
	Map<String, String> emapLabels = new HashMap<String, String>(); 
	Map<String, String> emapIds = new HashMap<String, String>(); 
	
	public TracerXmlGenerator(){
		norm = new Normalizer();
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
	
	public void exportImages() throws IOException{
    
        ApplicationContext ac = new ClassPathXmlApplicationContext("app-config.xml");
		DataSource dataSource = (DataSource) ac.getBean("tracerDB");
        
		
        String command = "SELECT sl.sb_id, sl.sbname, l.name as source, c.name as chr_name, si.position, si.strand, "
        		+ "it.name AS insertion_type, ei.name AS image_name, ei.file_path, ei.stage, ei.comment AS image_comment, "
        		+ "ed.name AS expression_domain_name, sbg.name AS gene_name, sbg.ensembl_gene_id, sbg.mgi_id, "
        		+ "sbg.name AS gene_name, sbg.ensembl_gene_id AS gene_ensembl_id, sbg.mgi_id AS mgi_id "
        		+ "FROM "
        		+ "sb_lines sl, labs l, sb_insertions si, insertion_types it, chromosomes c, expression_images ei, expression_image_domains eid, "
        		+ "expression_domains ed, sb_genes sbg  "
        		+ "WHERE"
        		+ " sl.sb_id=si.sb_id AND sl.lab_id=l.lab_id AND si.chr_id=c.chr_id AND si.insertion_type_id=it.insertion_type_id "
        		+ "AND sl.sb_id=ei.sb_id AND ei.exp_image_id=eid.exp_image_id AND eid.exp_domain_id=ed.exp_domain_id AND "
        		+ "ei.display_mode='public' AND sbg.sb_id=sl.sb_id AND sl.display_mode='public' LIMIT 10000000";
                
       
        try {
        	
        	PreparedStatement statement = dataSource.getConnection().prepareStatement(command);
     		ResultSet res = statement.executeQuery();
            
			int i = 0;
			
			Doc doc = new Doc();
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
	        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
			doc.setExportDate(datatypeFactory.newXMLGregorianCalendar(gregorianCalendar));
			
	        while ( res.next()){
	        	boolean sameImage = true;
	        	String imageName = res.getString("image_name");
	        	String internalId =  "tracer_" + i;
	        		    		
	    		String url = "http://www.ebi.ac.uk/panda-srv/tracer/sblac/" + res.getString("file_path") + "/" + res.getString("image_name") + ".jpg";
	    		
	    		Map<String, Integer> dimensions = EnrichingUtils.getImageMeasuresFromUrl(url);
	    		
	    		if (dimensions != null){ //index info only if the image could be loaded 	    		

		    		Image image = new Image();
		    		image.setId(internalId);
	    			
	    			Dimensions d = new Dimensions();
	    			d.setImageHeight(dimensions.get("height"));
	    			d.setImageWidth(dimensions.get("width"));
		    		ImageDescription imageDesc = new ImageDescription();
		    		imageDesc.setImageUrl(url);
		    		imageDesc.setImageDimensions(d);
		    		Link ogb = new Link();
		    		ogb.setDisplayName("Spitz Lab, EMBL");
		    		ogb.setUrl("http://www.embl.de/research/units/dev_biology/spitz/");
		      		imageDesc.setOrganismGeneratedBy(ogb);
		    		Link igb = new Link();
		    		igb.setDisplayName("Spitz Lab, EMBL");
		    		igb.setUrl("http://www.embl.de/research/units/dev_biology/spitz/");
		    		imageDesc.setImageGeneratedBy(igb);
		    		Link host = new Link();
		    		host.setDisplayName("Tracer Database");
		    		host.setUrl("http://www.ebi.ac.uk/panda-srv/tracer/");
		    		imageDesc.setHost(host);
		    		
		    		ImageTypeArray ita = new ImageTypeArray();
		    		ita.getEl().add(ImageType.EXPRESSION);
		    		imageDesc.setImageType(ita);
		    		
		    		imageDesc.setSampleType(SampleType.WILD_TYPE);
		    		
		    		AnnotationArray sp = new AnnotationArray();
		    		Annotation spAnn = new Annotation();
		    		spAnn.setOntologyTerm(getOntologyTerm("whole mounted tissue", "FBbi_00000024"));
		    		sp.getEl().add(spAnn);
		    		imageDesc.setSamplePreparation(sp);
		    		
		    		AnnotationArray im = new AnnotationArray();
		    		Annotation imAnn = new Annotation();
		    		imAnn.setOntologyTerm(getOntologyTerm("light microscopy", "FBbi_00000345"));
		    		im.getEl().add(imAnn);
		    		imageDesc.setImagingMethod(im);
		    		
		    		image.setImageDescription(imageDesc);
		    		
	    			if (res.getString("image_comment") != null){
	    				if (image.getObservations() == null){
	    					image.setObservations(new StringArray());
	    				}
	    				image.getObservations().getEl().add(res.getString("image_comment"));
	    			}
		    		
		    		Organism org = new Organism();
	    			// in the TRACER fields 'stage' they actually store dpc, with values like E11.5
	    			String a = norm.normalizeAge(res.getString("stage"));
	    			org.setAge(res.getString("stage"));
	    			org.setTaxon("Mus musculus");
	    			OntologyTerm stage = new OntologyTerm();
	    			stage.setTermId("MmusDv_0000002");
	    			stage.setTermLabel("embryonic mouse stage");
	    			org.setStage(stage);
	    			image.setOrganism(org);

	    			// We always have 1 channel
	    			Channel channel = new Channel();
	    			String channelId = "tracer_channel_" + i + "_0";
	    			channel.setId(channelId);
	    			// Link it to the image
	    			channel.setAssociatedImage(internalId);
		    		AnnotationArray vm = new AnnotationArray();
		    		Annotation vmAnn = new Annotation();
		    		vmAnn.setOntologyTerm(getOntologyTerm("visualisation of genetically encoded beta-galactosidase", "FBbi_00000077"));
		    		vm.getEl().add(vmAnn);
		    		channel.setVisualisationMethod(vm);
	    			// Link image to channel too
	    			image.setAssociatedChannel(new StringArray());
	    			image.getAssociatedChannel().getEl().add(channelId);
	    			
	    			GenotypeComponent expressed = new GenotypeComponent();
	    			GenomicLocation gl = new GenomicLocation();
	    			gl.setChromosone(res.getString("chr_name"));
	    			gl.setStartPos(res.getLong("position"));
	    			gl.setEndPos(res.getLong("position"));
	    			gl.setStrand(res.getString("strand"));
	    			expressed.setGenomicLocation(gl);
	    			if (res.getString("mgi_id") != null){
	    				expressed.setGeneId(res.getString("mgi_id"));
	    			} else if (res.getString("gene_ensembl_id") != null){
	    				expressed.setGeneId(res.getString("gene_ensembl_id"));
	    			}
	    			expressed.setGeneSymbol(res.getString("gene_name"));
	    			Annotation mt = new Annotation ();
	    			mt.setAnnotationFreetext("transgenic insertion generated by a Sleeping Beauty transposon-based system");
	    			expressed.setMutationType(mt);
	    			channel.setDepictsExpressionOf(expressed);
	    			Roi roi = null;
	    			
	    			ArrayList<String> addedAnnoations = new ArrayList<>();
	    			// get all anntoations associated to the same image => need to collapse rows
		        	while(sameImage){
		        		
		        		String anat =  res.getString("expression_domain_name");

        				// as long as we have an annotation create a roi
		        		if (anat != null && !addedAnnoations.contains(anat)){
		        			if (roi == null){
		    	    			String roiId = "tracer_roi_" + i;
		    	    			roi = new Roi();
		    	    			// link image and channel
		    	    			roi.setId(roiId);
		    	    			roi.setAssociatedChannel(new StringArray());
		    	    			roi.getAssociatedChannel().getEl().add(channelId);
		    	    			roi.setAssociatedImage(internalId);
		    	    			roi.setDepictedAnatomicalStructure(new ExpressionAnnotationArray());
		    	    			// set coordinates
		    	    			Coordinates coords = new Coordinates();
		    	    			PercentArray xcoords = new PercentArray();
		    	    			PercentArray ycoords = new PercentArray();
		    	    			xcoords.getEl().add((float)0);
		    	    			xcoords.getEl().add((float)100); // whole image because tracer doesn't do rois by it's own
		    	    			ycoords.getEl().add((float)0);
		    	    			ycoords.getEl().add((float)100);
		    	    			coords.setYCoordinates(ycoords);
		    	    			coords.setXCoordinates(xcoords);
		    	    			roi.setCoordinates(coords);
		    	    		}
		        			ExpressionAnnotation anatomy = new ExpressionAnnotation();
		        			anatomy.setAnnotationFreetext(anat);
		        			addedAnnoations.add(anat); // in the DB the entries are repeated for each neighbouring gene to the insertions site.
			    	    	
			    			if (emapIds.containsKey(anat)){	
			    				anatomy.setOntologyTerm(getOntologyTerm(emapLabels.get(anat), emapIds.get(anat)));
			    			}
			    			else {	
			    				try {
			    					if (!getAnatomyId(anat, a).equalsIgnoreCase("")){
			    						ExpressionAnnotation automatedAnn = new ExpressionAnnotation();
			    						automatedAnn.setOntologyTerm(getOntologyTerm(getAnatomyLabels(anat, a), getAnatomyId(anat, a)));
			    						automatedAnn.setAnnotationMode(AnnotationMode.AUTOMATED); 		
			    						roi.getDepictedAnatomicalStructure().getEl().add(automatedAnn);
			    					}
			    				} catch (JSONException e) {
			    					e.printStackTrace();
			    				} catch (Exception e) {
			    					e.printStackTrace();
			    				}
			    			}
			    			roi.getDepictedAnatomicalStructure().getEl().add(anatomy);
		    				anatomy.setAnnotationMode(AnnotationMode.MANUAL);
		        		}
		        				    			
		    			if (res.next() && imageName.equalsIgnoreCase(res.getString("image_name"))){
		    					i++;
		    				}
		    			else {
		    				sameImage=false;
		    				res.previous();
		        		}
		    		}
		    		if (roi != null){
		    			image.setAssociatedRoi(new StringArray());
		    			image.getAssociatedRoi().getEl().add(roi.getId());
		    			channel.setAssociatedRoi(new StringArray());
		    			channel.getAssociatedRoi().getEl().add(roi.getId());
		    			doc.getRoi().add(roi);
		    		}

			        doc.getImage().add(image);
			        if (channel != null){
			        	doc.getChannel().add(channel);
			        }
	    		} 
	    	//	if (i == 50 ){
	    	//		break;
	    	//	}
	    	}// end while ( res.next())
	        

	        Date date = new Date();
	        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	        // File file = new File("source/main/resources/" + dateFormat.format(date) + "tracerExport.xml");
	        File file = new File("source/main/resources/tracerExport.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Doc.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(doc, file);
	        
	        }catch(Exception e){
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

	OntologyTerm getOntologyTerm (String label, String id){
		 OntologyTerm term = new OntologyTerm();
		 term.setTermId(id);
		 term.setTermLabel(label);
		 return term;
	 }
}


package uk.ac.ebi.phis.xmlDump;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import uk.ac.ebi.phis.utils.ontology.JsonFields;
import uk.ac.ebi.phis.utils.ontology.Normalizer;
import uk.ac.ebi.phis.utils.ontology.Utils;

public class Komp2Importer {
	
	/*HttpSolrServer solr;
	Normalizer norm;
	Utils utils;
	
	public Komp2Importer(){
		norm = new Normalizer();
		utils = new Utils();
	}
	
	public void downloadAndParseToXml(){
		System.out.println("start the function");
		
		String solrURL = "http://ves-ebi-d0.ebi.ac.uk:8090/mi/impc/dev/solr/images"; //default
		String dbPrefix = "komp2_";
		solr = new HttpSolrServer(solrURL);
		
		SolrQuery query = new SolrQuery()
        .setQuery("*:*")
        .setStart(0).setRows(1000000);
		
		try {
			

			SolrDocumentList documents = solr.query(query).getResults();
			System.out.println(solr.getBaseURL()+"/select?"+query);
			int internal_id = 0; 
			
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			// root elements
			Document xmlDoc = docBuilder.newDocument();
			Element root = xmlDoc.createElement("doc");
			xmlDoc.appendChild(root);
			long time = System.currentTimeMillis();
			int k = 0; 
			for (SolrDocument doc : documents ){
				if (doc.containsKey("accession") && doc.containsKey("geneName") ){ // we don't import control images
				try{

					Element image = xmlDoc.createElement("entry");
					// Generate internal_id
					String uniqueId = dbPrefix + (internal_id);
					image.appendChild(utils.getNewElement(JsonFields.ID, uniqueId, xmlDoc));
					image.appendChild(utils.getNewElement(JsonFields.DOCUMENT_TYPE, "image", xmlDoc));
					image.appendChild(utils.getNewElement(JsonFields.DATA_SOURCE, "KOMP2", xmlDoc));
					
					// figure the type of the image
					String type = getImageType (doc.getFieldValue("procedure_name").toString());
					if (type.equalsIgnoreCase("expression")) {
						// Expression images are a bit different
						// we need to create a virtual channel (KOMP2 doesn't have images with phisically distinct planes so far.
						Element channel = utils.getNewElement("entry", "", xmlDoc);
						String chId = dbPrefix + "channel_" + internal_id;
						channel.appendChild(utils.getNewArrrayElement(JsonFields.ASSOCIATED_IMAGE, uniqueId, xmlDoc));
						image.appendChild(utils.getNewArrrayElement(JsonFields.ASSOCIATED_CHANNEL, chId, xmlDoc));
						
						image = utils.addCoordinates((ArrayList<Object>) doc.getFieldValue("xStart"), (ArrayList<Object>) doc.getFieldValue("xEnd"),
								(ArrayList<Object>) doc.getFieldValue("yStart"), (ArrayList<Object>) doc.getFieldValue("yEnd"), image, channel, chId, root, xmlDoc);
					}
					else {
						image = utils.addCoordinates((ArrayList<Object>) doc.getFieldValue("xStart"), (ArrayList<Object>) doc.getFieldValue("xEnd"),
								(ArrayList<Object>) doc.getFieldValue("yStart"), (ArrayList<Object>) doc.getFieldValue("yEnd"), image, xmlDoc);
						
					}
					
					
					String days = (String) doc.getFieldValue("ageInWeeks");
					if(norm.isEmbryonicAge(days))
						image.appendChild(utils.getNewElement(JsonFields.EMBRYO_AGE, "" + norm.normalizeAge(days), xmlDoc));
					else 
						image.appendChild(utils.getNewElement(JsonFields.AGE, "" + norm.normalizeAge(days), xmlDoc));
					// stage 	->	see dataStructuringDraft.docx. At the moment we won't compute this, olny have it if it is already provided, but we should allow the user to annotate it!
					if (doc.containsKey("maTermId")){
						image.appendChild(utils.getNewArrrayElement(JsonFields.ANATOMY_ID, (ArrayList<Object>) doc.getFieldValue("maTermId"), xmlDoc));
					}
					if (doc.containsKey("maTermName")){
						image.appendChild(utils.getNewArrrayElement(JsonFields.ANATOMY_TERM, (ArrayList<Object>) doc.getFieldValue("maTermName"), xmlDoc));
					}
					//TODO image type
					// need to infere
					
					//TODO genotype type
					// also need to infere & check if that information is in the images core at all. We have: tm1a, tm1b, gene trap.
					
					image.appendChild(utils.getNewElement(JsonFields.IMAGE_URL, "http://www.mousephenotype.org/data/media/" + doc.getFieldValue("fullResolutionFilePath").toString(), xmlDoc));
					//TODO  Image context URL must be computed but at the moment we can't distinguish between genes and alleles (Wait for the weirdness int he image core to dissappear)
					// jsondoc.put("imageContextUrl" , "" + doc.getFieldValue(""));
					image.appendChild(utils.getNewElement(JsonFields.CENTER, doc.getFieldValue("institute").toString(), xmlDoc));
					
					image.appendChild(utils.getNewArrrayElement(JsonFields.GENE_ID, (ArrayList<Object>)doc.getFieldValue("accession"), xmlDoc));
					
					image.appendChild(utils.getNewArrrayElement(JsonFields.GENE_NAME, (ArrayList<Object>) doc.getFieldValue("geneName"), xmlDoc));
					
					// TODO
					// jsonDoc.put("ensemblId", ?);
					image.appendChild(utils.getNewElement(JsonFields.ORGANISM, "Mus musculus", xmlDoc));
					if (doc.containsKey("mpTermId")){
						image.appendChild(utils.getNewArrrayElement(JsonFields.PHENOTYPE_ID,(ArrayList<Object>) doc.getFieldValue("mpTermId"), xmlDoc));
					}
					if (doc.containsKey("mpTermName")){
						image.appendChild(utils.getNewArrrayElement(JsonFields.PHENOTYPE_TERM, (ArrayList<Object>)doc.getFieldValue("mpTermName"), xmlDoc));
					}
					image.appendChild(utils.getNewElement(JsonFields.PROCEDURE, doc.getFieldValue("procedure_name").toString(), xmlDoc));
					image.appendChild(utils.getNewElement(JsonFields.SEX, norm.normalizeSex((String) doc.getFieldValue("gender").toString()), xmlDoc));
					image.appendChild(utils.getNewArrrayElement(JsonFields.ZYGOSITY, norm.normalizeZygosity((String) doc.getFieldValue("genotype")), xmlDoc));
					//TODO sequence
					// get from ENSEMBL
					// I will need to uncomment this when we can get the gene name/symbol from the index
					
					// conditions - DON'T apply
					if (doc.containsKey("tagName")){
						ArrayList<String> tagNames = (ArrayList<String>) doc.getFieldValue("tagName");
						ArrayList<String> tagValues = (ArrayList<String>) doc.getFieldValue("tagValue");
						Element obs = utils.getNewElement(JsonFields.OBSERVATIONS, "", xmlDoc);
						for (int i = 0; i < tagValues.size(); i ++)
						{
							obs.appendChild(utils.getNewElement("el", tagNames.get(i) + ": " + tagValues.get(i), xmlDoc));
						}
						image.appendChild(obs);
					}
					
					internal_id ++;
					root.appendChild(image);
					
					k++;
					} catch (Exception e) {
						e.printStackTrace();
						break;
					}
				}
			}
			System.out.println("Done building the XML with " + k + " entries in " + (System.currentTimeMillis()-time));
			time = System.currentTimeMillis();
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(xmlDoc);
			StreamResult result = new StreamResult(new File("source/main/resources/komp2ImagesDump.xml"));
			transformer.transform(source, result);
			
			System.out.println("Done writing to file in " + (System.currentTimeMillis()-time));
			
		}catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	private String getImageType (String procedure){
		if (procedure.equalsIgnoreCase("Adult LacZ"))
			return "expression";
		else if (procedure.equalsIgnoreCase("FACS Analysis"))
			return "cytometry";
		else if (procedure.equalsIgnoreCase("Histopathology") || procedure.equalsIgnoreCase("Skin Histopathology"))
			return "histopathology";
		else if (procedure.equalsIgnoreCase("X-ray") || procedure.equalsIgnoreCase("X-ray Imaging"))
			return "xray";
		else if (procedure.equalsIgnoreCase("Combined SHIRPA and Dysmorphology") || procedure.equalsIgnoreCase("Dysmorphology"))
			return "dysmorphology";
		return "???"; // see rest of types here http://ves-ebi-d0.ebi.ac.uk:8090/mi/impc/dev/solr/images/select?q=*:*&group.field=procedure_name&group=true&fl=procedure_name
	}
	*/
}

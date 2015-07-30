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
package uk.ac.ebi.phis.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import uk.ac.ebi.phis.utils.ontology.OntologyMapper;
import uk.ac.ebi.phis.utils.ontology.OntologyMapperPredefinedTypes;

public class Utils_deprecated {

	 OntologyMapper mapper;
	 
	 public Utils_deprecated(OntologyMapperPredefinedTypes mapperType){
		 mapper = new OntologyMapper(mapperType);
	 }
	
	public Element getNewElement(String tag, String text, Document doc){
		Element newEl = doc.createElement(tag);
		Text txt = doc.createTextNode(text);
		newEl.appendChild(txt);
		return newEl;
	}
	
	public Element getNewArrrayElement (String tag, ArrayList<Object> lst, Document doc){
		Element parent = doc.createElement(tag);
		for (Object entry : lst){
			parent.appendChild(getNewElement("el", entry.toString(), doc));
		}
		return parent;
	}
	
	public Element getNewArrrayElement (String tag, HashSet<String> lst, Document doc){
		Element parent = doc.createElement(tag);
		for (String entry : lst){
			parent.appendChild(getNewElement("el", entry, doc));
		}
		return parent;
	}
	
	public Element getNewArrrayElement (String tag, String entry, Document doc){
		Element parent = doc.createElement(tag);
		parent.appendChild(getNewElement("el", entry, doc));
		return parent;
	}
	
	/**
	 * 
	 * @param parent element
	 * @param doc
	 * @param newElTag
	 * @param newElValue
	 * @return The parent element with the new element added inside.
	 */
	public Element addElementToArray (Element parent, String arrayTag, String newElValue, Document doc){
		Element array;
		Element arrayItem = getNewElement("el", newElValue, doc);
		if (parent.getElementsByTagName(arrayTag).getLength() != 0){
			array = (Element) parent.getElementsByTagName(arrayTag).item(0);
			array.appendChild(arrayItem);
			parent.replaceChild(parent.getElementsByTagName(arrayTag).item(0), array);
		}
		else {
			array = getNewElement(arrayTag, "", doc);	
			array.appendChild(arrayItem);
			parent.appendChild(array);	
			}
		return parent;
	}
	
	public Element addCoordinates(ArrayList<Object> xStart, ArrayList<Object> xEnd, ArrayList<Object> yStart, ArrayList<Object> yEnd, Element image, Document doc){
		if (xStart != null){
			for (int i = 0; i < xStart.size(); i++){
				if ((Float) xStart.get(i) +(Float) xEnd.get(i) 
					+ (Float)yStart.get(i) + (Float)yEnd.get(i) != 0 ){
					ArrayList<Object> x = new ArrayList<Object>();
					ArrayList<Object> y = new ArrayList<Object>();
					x.add(xStart.get(i));
					x.add(xEnd.get(i));
					y.add(yStart.get(i));
					y.add(yEnd.get(i));
					image.appendChild(getNewArrrayElement(JsonFields.X_COORDINATES, x, doc));
					image.appendChild(getNewArrrayElement(JsonFields.Y_COORDINATES, y, doc));
				}
			}
		}
		return image;
	}
	
	public HashMap<String, Element >  addRoiSangerData(Element image, Element channel, Document roiDoc, Document imageDoc, 
			Document channelDoc, ResultSet res, int i){
		Element img = image;
		System.out.println(image.getElementsByTagName(JsonFields.ID).item(0).getTextContent());
    	Element roi = roiDoc.createElement("entry");
		String roiId = image.getElementsByTagName(JsonFields.ID).item(0).getTextContent().replace("komp2", "komp2_roi") + "_" 
				+ image.getElementsByTagName(JsonFields.ASSOCIATED_ROI).getLength();
		roi.appendChild(getNewElement(JsonFields.ID, roiId, roiDoc));
		// associate ids
		img = addElementToArray(img, JsonFields.ASSOCIATED_ROI, roiId, imageDoc);
    	roi.appendChild(getNewElement(JsonFields.ASSOCIATED_IMAGE, image.getElementsByTagName(JsonFields.ID).item(0).getTextContent(), roiDoc));
    	if (channel != null) { // expression images
    		String channelId = channel.getElementsByTagName(JsonFields.ID).item(0).getTextContent();
        	roi = addElementToArray(roi, JsonFields.ASSOCIATED_CHANNEL, channelId, roiDoc);
        	channel = addElementToArray(channel, JsonFields.ASSOCIATED_ROI, roiId, channelDoc);
    	}
    	
    	try {
	    	// ASSUMPTION: If a table row has a non-empty phenotype & anatomy annotations they are meant for the ROI
			if (res.getString("ONTOLOGY_DICT_ID").equalsIgnoreCase("2") || res.getString("ONTOLOGY_DICT_ID").equalsIgnoreCase("4")){ //2=EMAP, 4=MA
				roi = addElementToArray(roi, JsonFields.ANATOMY_ID, res.getString("TERM_ID"), roiDoc);
				roi = addElementToArray(roi, JsonFields.ANATOMY_TERM, res.getString("TERM_NAME"), roiDoc);
				if (image.getElementsByTagName(JsonFields.IMAGE_TYPE).item(0).equals("expression")){
					image = addElementToArray(image, JsonFields.EXPRESSED_ANATOMY_ANN_LIST, res.getString("TERM_ID"), imageDoc);
					image = addElementToArray(image, JsonFields.EXPRESSED_ANATOMY_ANN_LIST, res.getString("TERM_NAME"), imageDoc);
				}
				image = addElementToArray(image, JsonFields.ANATOMY_ANN_LIST, res.getString("TERM_ID"), imageDoc);
				image = addElementToArray(image, JsonFields.ANATOMY_ANN_LIST, res.getString("TERM_NAME"), imageDoc);
			}
			else if (res.getString("ONTOLOGY_DICT_ID").equalsIgnoreCase("1")){ // 1 = MP
				String mpId = res.getString("TERM_ID");
				roi = addElementToArray(roi, JsonFields.PHENOTYPE_ID, mpId, roiDoc);
				roi = addElementToArray(roi, JsonFields.PHENOTYPE_TERM, res.getString("TERM_NAME"), roiDoc);
				// Link phtnotypes directly to the image too
				image = addElementToArray(image, JsonFields.PHENOTYPE_ANN_LIST, mpId, imageDoc);
				image = addElementToArray(image, JsonFields.PHENOTYPE_ANN_LIST, res.getString("TERM_NAME"), imageDoc);
				
				// Check if it's mapped to an anatomy term
				String mpUri = "http://purl.obolibrary.org/obo/" + mpId.replace(":", "_");
				System.out.println("\n" + mpUri);
				List<String> maList =  mapper.getMappings(mpUri, "MA_");
				if (maList != null){
					for (String ma : maList){
						System.out.println("\t" + ma);
						String label = mapper.getAnatomyLabel(ma);
						roi = addElementToArray(roi, JsonFields.ANATOMY_COMPUTED_ID, ma, roiDoc);
	//					roi = addElementToArray(roi, JsonFields.ANATOMY_COMPUTED_TERM, label , roiDoc);
						image = addElementToArray(image, JsonFields.ANATOMY_PHENOTYPE_LIST, ma, imageDoc);
	//					image = addElementToArray(image, JsonFields.ANATOMY_PHENOTYPE_LIST, label, imageDoc);
						image = addElementToArray(image, JsonFields.ANATOMY_ANN_LIST, ma, imageDoc);
	//					image = addElementToArray(image, JsonFields.ANATOMY_ANN_LIST, label, imageDoc);
						image = addElementToArray(image, JsonFields.ANATOMY_COMPUTED_LIST, ma, imageDoc);
	//					image = addElementToArray(image, JsonFields.ANATOMY_COMPUTED_LIST, label, imageDoc);
					}
				}
			}
			
			
			// Add ROI coordinates		
			roi = addElementToArray(roi, JsonFields.X_COORDINATES, res.getString("X_START"), roiDoc);
			roi = addElementToArray(roi, JsonFields.Y_COORDINATES, res.getString("Y_START"), roiDoc);
			Map<String, Integer> dimensions = null;
			// If the ends are 0, 0 replace them with the height & width
			
			if ( res.getFloat("Y_END") == 0){// height
				 dimensions = EnrichingUtils.getImageMeasuresFromUrl("http://www.mousephenotype.org/data/media/" + res.getString("FULL_RESOLUTION_FILE_PATH").replaceAll(" ", "%20"));
				 if (dimensions == null)
					 System.out.println("http://www.mousephenotype.org/data/media/" + res.getString("FULL_RESOLUTION_FILE_PATH").replaceAll(" ", "%20"));
				 roi = addElementToArray(roi, JsonFields.Y_COORDINATES, "" + dimensions.get("height"), roiDoc);
			} else{ 
				roi = addElementToArray(roi, JsonFields.Y_COORDINATES, "" + res.getFloat("Y_END"), roiDoc);
			}
			
			if (res.getFloat("X_END") == 0){ // width
				if (dimensions == null){
					 dimensions = EnrichingUtils.getImageMeasuresFromUrl("http://www.mousephenotype.org/data/media/" + res.getString("FULL_RESOLUTION_FILE_PATH").replaceAll(" ", "%20"));		
				}
				roi = addElementToArray(roi, JsonFields.X_COORDINATES, "" + dimensions.get("width"), roiDoc);
			} else {
				roi = addElementToArray(roi, JsonFields.X_COORDINATES, "" + res.getFloat("X_END"), roiDoc);
			}
    	}catch (SQLException e){
    		e.printStackTrace();
    	}
		// Add elements to map
    	HashMap<String, Element > resultMap = new HashMap<String, Element>();
    	resultMap.put("image", img);
    	resultMap.put("roi", roi);
    	resultMap.put("channel", channel);
    	return resultMap;
	}
	
	public Element addCoordinates(ArrayList<Object> xStart, ArrayList<Object> xEnd, ArrayList<Object> yStart, ArrayList<Object> yEnd, Element image, 
			Element channel, String channelId, Element root, Document doc){
		
		HashSet<String> roiIds = new HashSet<String>();
		if (xStart != null){
			for (int i = 0; i < xStart.size(); i++){
				if ((Float) xStart.get(i) +(Float) xEnd.get(i) 
					+ (Float)yStart.get(i) + (Float)yEnd.get(i) != 0 ){
					// Create ROI
					Element roi = getNewElement("entry", "", doc);
					System.out.println(channelId);
					String roiId = channelId.replace("channel", "roi") + "_" + i;
					roi.appendChild(getNewElement(JsonFields.ID, roiId, doc));
//					roi.appendChild(getNewElement(JsonFields.DOCUMENT_TYPE, "roi", doc));
					roi.appendChild(getNewArrrayElement(JsonFields.ASSOCIATED_CHANNEL, channelId, doc));
					roi.appendChild(getNewArrrayElement(JsonFields.ASSOCIATED_IMAGE, channelId.replace("channel_", ""), doc));
					roiIds.add(roiId);
					ArrayList<Object> x = new ArrayList<Object>();
					ArrayList<Object> y = new ArrayList<Object>();
					x.add(xStart.get(i));
					x.add(xEnd.get(i));
					y.add(yStart.get(i));
					y.add(yEnd.get(i));
					roi.appendChild(getNewArrrayElement(JsonFields.X_COORDINATES, x, doc));
					roi.appendChild(getNewArrrayElement(JsonFields.Y_COORDINATES, y, doc));	
					root.appendChild(roi);
				}
			}
			if (roiIds.size() > 0){
				// add them to the image and channel
				image.appendChild(getNewArrrayElement(JsonFields.ASSOCIATED_ROI, roiIds, doc));
				channel.appendChild(getNewArrrayElement(JsonFields.ASSOCIATED_ROI, roiIds, doc));
			}
		}
		return image;
	}
	
}

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

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.ac.ebi.phis.exception.PhenoImageShareException;
import uk.ac.ebi.phis.exception.PhisQueryException;

public class Normalizer {

	public Normalizer(){
	}
	
	
	public String normalizeZygosity(String zygosity) throws PhenoImageShareException{
		String z = zygosity.toLowerCase();
		
		if (z.startsWith("hom")){
			return "homozygous";
		}
		else if (z.startsWith("het")){
			return "heterozygous";
		}
		else if (z.startsWith("hemi")){
			return "hemizygous";
		}
		else if (z.equals("wt")){
			return "wild type";
		}
		else if (z.equals("?") || z.equals("fail")){
			return "unspecified";
		}
		else throw new PhenoImageShareException("Could not match \"" + zygosity + "\"to an existing zygosity");
	}
	
	public String normalizeSex(String sex) 
	throws PhenoImageShareException{
	
		String z = sex.toLowerCase();
		
		if (z.startsWith("m")){
			return "male";
		}
		else if (z.startsWith("f")){
			return "female";
		}
		else if (z.equalsIgnoreCase("unsexed")){
			return "unsexed";
		}
		else if (z.equalsIgnoreCase("") || z == null){
			return "unknown";
		}
		else throw new PhenoImageShareException("Could not match \"" + sex + "\"to an existing zygosity");
	}
	
	public float getAgeInDays(String inputAge){
		String age = inputAge.toLowerCase();
		if (age.endsWith("w")){
			Float weeks = new Float(age.replace("w", ""));
			return weeks * 7; // we need the age in days
		}
		else if (age.endsWith("w (at death)")){
			Float weeks = new Float(age.replace("w (at death)", ""));
			return  weeks * 7; // we need the age in days
		}
		else if (age.endsWith("e")){
			return new Float(age.replace("e", ""));
		}
		else if (age.endsWith("e (at death)")){
			return new Float(age.replace("e (at death)", ""));
		}
		else if (age.startsWith("unknown")){
			return -1; 	// "" stands for unspecified age
		}
		else if (age.startsWith("e")){
			return new Float(age.replace("e", ""));
		}
		return new Float(age);
	}
	
	public String normalizeAge(String inputAge){
		String age = inputAge.toLowerCase();
		if (age.endsWith("w")){
			Float weeks = new Float(age.replace("w", ""));
			return String.format("%.2f", weeks * 7); // we need the age in days
		}
		else if (age.endsWith("w (at death)")){
			Float weeks = new Float(age.replace("w (at death)", ""));
			return String.format("%.2f", weeks * 7); // we need the age in days
		}
		else if (age.endsWith("e")){
			return String.format("%.2f", new Float(age.replace("e", "")));
		}
		else if (age.endsWith("e (at death)")){
			return String.format("%.2f", new Float(age.replace("e (at death)", "")));
		}
		else if (age.startsWith("unknown")){
			return "-1"; 	// "" stands for unspecified age
		}
		else if (age.startsWith("e")){
			return String.format("%.2f", new Float(age.replace("e", "")));
		}
		return String.format("%.2f", new Float(age));
	}
	
	public boolean isEmbryonicAge(String age){
		if  (age.endsWith("e") || age.endsWith("e (at death)"))
			return true;
		else return false;
	}
	
	
	public String getImageType (String procedure){
		if (procedure.equalsIgnoreCase("Adult LacZ") || procedure.equalsIgnoreCase("Wholemount Expression") || procedure.equalsIgnoreCase("Anti-nuclear Antibody Assay"))
			return "expression";
		else if (procedure.equalsIgnoreCase("FACS Analysis") || procedure.equalsIgnoreCase("Flow Cytometry"))
			return "cytometry";
		else if (procedure.equalsIgnoreCase("Histopathology") || procedure.equalsIgnoreCase("Skin Histopathology") || procedure.equalsIgnoreCase("Histology Slide") 
				|| procedure.equalsIgnoreCase("Brain Histopathology") || procedure.equalsIgnoreCase("DSS Histology Scores"))
			return "histopathology";
		else if (procedure.equalsIgnoreCase("X-ray") || procedure.equalsIgnoreCase("X-ray Imaging") || procedure.equalsIgnoreCase("Xray"))
			return "xray";
		else if (procedure.equalsIgnoreCase("Combined SHIRPA and Dysmorphology") || procedure.equalsIgnoreCase("Dysmorphology") || procedure.equalsIgnoreCase("Embryo Dysmorphology"))
			return "dysmorphology";
		return "other"; // see rest of types here http://ves-ebi-d0.ebi.ac.uk:8090/mi/impc/dev/solr/images/select?q=*:*&group.field=procedure_name&group=true&fl=procedure_name
	}
}

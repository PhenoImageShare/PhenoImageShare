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

import uk.ac.ebi.phis.jaxb.GenomicLocation;

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

public class EnrichingUtils {

	public static Map<String, Integer> getImageMeasuresFromUrl(String url) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			BufferedImage image = ImageIO.read(new URL(url));
			map.put("height", image.getHeight());
			map.put("width", image.getWidth());
		} catch (IOException e) {
			// e.printStackTrace();
			return null;
		}
		return map;
	}

	public String getSequence(String geneSymbol, String specieSnakeCase)
			throws JSONException, IOException {
		// http://beta.rest.ensembl.org/xrefs/symbol/mus_musculus/AKT2?content-type=application/json
		String uri = "http://beta.rest.ensembl.org/xrefs/symbol/"
				+ specieSnakeCase + "/" + geneSymbol
				+ "?content-type=application/json";
		JSONArray arr = readJsonArrayFromUrl(uri);
		String ensemblId = null;
		int i = 0;
		while (i++ < arr.length()) {
			JSONObject obj = (JSONObject) arr.get(0);
			if (obj.getString("type").equalsIgnoreCase("gene")) {
				ensemblId = obj.getString("id");
				break;
			}
		}
		// http://beta.rest.ensembl.org/sequence/id/ENSMUSG00000004056?content-type=text/plain
		if (ensemblId != null) {
			String sequenceUrl = "http://beta.rest.ensembl.org/sequence/id/"
					+ ensemblId + "?content-type=text/plain";
			return getStringFromUrl(sequenceUrl);
		}
		return null;
	}

	public String getStringFromUrl(String url) throws MalformedURLException, IOException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String text = readAll(rd);
			return text;
		} finally {
			is.close();
		}
	}

	public GenomicLocation getGenomeLocation(String ensemblId) throws JSONException, IOException {
		String url = "http://beta.rest.ensembl.org/lookup/id/" + ensemblId
				+ "?content-type=application/json";
		// http://beta.rest.ensembl.org/lookup/id/ENSG00000157764?content-type=application/json
		JSONObject json = readJsonFromUrl(url);
		GenomicLocation loc = new GenomicLocation();
		loc.setChromosone(json.getString("seq_region_name"));
		loc.setEndPos(json.getLong("end"));
		loc.setStartPos(json.getLong("start"));
		loc.setStrand(json.getString("strand"));
		return loc;
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static JSONArray readJsonArrayFromUrl(String url)
			throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONArray json = new JSONArray(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

}

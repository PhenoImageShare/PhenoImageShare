package uk.ac.ebi.phis.utils.web;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;



public class JSONRestUtil {

	private static final Logger log = Logger.getLogger(JSONRestUtil.class);


	private static int CONNECTION_TIMEOUT = 30000;


	/**
	 * Get the results of a query from the provided url. make sure the url requests JSON!!!
	 * 
	 * @param url
	 *            the URL from which to get the content
	 * @return a JSONObject representing the result of the query
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static JSONObject getResults(String url) throws IOException, URISyntaxException {

		log.debug("GETTING CONTENT FROM: " + url);

		return new JSONObject(getContent(new URL(url)));
	}
	
	public static int getNumberFoundFromJsonResponse(JSONObject response) {
		int numberFound = (int) response.getJSONObject("response").getInt("numFound");
		return numberFound;
	}
	
	public static JSONArray getDocArray(JSONObject jsonResponse) {
		JSONArray docs = jsonResponse.getJSONObject(
				"response").getJSONArray("docs");
		return docs;
	}
	
	public static String getContent(URL url) {

		String content = "no results";

		URL escapedUrl;
		try {
			escapedUrl = new URL(url.toExternalForm().replace(" ", "%20"));
			content = getNonSecureContent(escapedUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return content;
	}
	

	public static String getNonSecureContent(URL url) throws IOException {

		HttpURLConnection urlConn;
		InputStreamReader inStream;
		String content = "";
		urlConn = (HttpURLConnection) url.openConnection();

		urlConn.setReadTimeout(CONNECTION_TIMEOUT);
		urlConn.connect();
		try {
			inStream = new InputStreamReader(urlConn.getInputStream());
		} catch (Exception e) {
			System.out.println("throwing url not found exception in getNonSecureContent method");
			throw new IOException("solr url not found");
		}

		content = IOUtils.toString(inStream).trim();

		return content;
	}

}
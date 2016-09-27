/*
 * Copyright BISEL (www.bisel.org) 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bisel.phis.iss.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.bisel.phis.iss.model.Request;
import org.bisel.phis.iss.model.Request_Delete;

/**
 * Client to the EBI Solr submission service that enables the addition, editing
 * and deletion of annotations.
 *
 * Use the {@link #getMessage() getMessage} method to see what message the EBI
 * returns. Or, if the URL fails to be generated, use the same method to get the
 * error.
 *
 *
 * @author kcm
 */
public class EBIClient {

    private static final Logger LOG = Logger.getLogger(EBIClient.class.getName());
    private String message;

    /**
     * Creates a new annotation using
     * {@link #buildURL(org.bisel.phis.iss.model.Request, java.lang.String) buildURL}.
     * Communication with EBI carried out by using
     * {@link #talk(java.lang.String) talk}.
     *
     * @param guiRequest The request from the phis client
     * @param version Which version of the ISS API is being called
     * @return True if success otherwise False
     * @see org.bisel.phis.iss.model.Request
     * @see #buildURL(org.bisel.phis.iss.model.Request, java.lang.String)
     */
    public boolean sendAnnotation(Request guiRequest, String version) {

        String query;
        if (version.equals("103") | version.equals("102")) {
            query = buildURL(guiRequest, GetISSURL.getEBI(version) + "createAnnotation?");
        } else {
            query = buildURL(guiRequest, GetISSURL.getEBI("103") + "createAnnotation?");
        }

        if (query == null) {
            return false;
        }

        // talk to EBI
        LOG.log(Level.SEVERE, "[EBI QUERY] {0}", query);
        boolean result = talk(query, version);
        return result;
    }

    /**
     * Deletes an existing annotation. Communication with EBI carried out by
     * using {@link #talk(java.lang.String) talk}.
     *
     * @param guiRequest The request from the phis client
     * @param version Which version of the ISS API is being called
     * @return True if success otherwise false
     * @see org.bisel.phis.iss.model.Request
     * @see #talk(java.lang.String)
     */
    public boolean deleteAnnotation(Request_Delete guiRequest, String version) {

        String query;

        if (version.equals("103") | version.equals("102")) {
            query = GetISSURL.getEBI(version) + "deleteAnnotation?";
        } else {
            query = GetISSURL.getEBI("103") + "deleteAnnotation?";
        }

        try {
            query += "annotationId=" + URLEncoder.encode(guiRequest.getPhisID(), "UTF-8");
            query += "&userId=" + URLEncoder.encode(guiRequest.getCreatorID(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            message = "failed to encode URL for EBI";
            return false;
        }

        // talk to EBI
        LOG.log(Level.INFO, "[EBI QUERY] {0}", query);
        boolean result = talk(query, version);
        return result;
    }

    /**
     * Updates an existing annotation using
     * {@link #buildURL(org.bisel.phis.iss.model.Request, java.lang.String) buildURL}.
     * Communication with EBI carried out by using
     * {@link #talk(java.lang.String) talk}.
     *
     * @param guiRequest The request from the phis client
     * @param version Which version of the ISS API is being called
     * @return True if success otherwise False
     * @see org.bisel.phis.iss.model.Request
     * @see #buildURL(org.bisel.phis.iss.model.Request, java.lang.String)
     * @see #talk(java.lang.String)
     */
    public boolean updateAnnotation(Request guiRequest, String version) {
        String query;

        if (version.equals("103") | version.equals("102")) {
            query = buildURL(guiRequest, GetISSURL.getEBI(version) + "updateAnnotation?");
        } else {
            query = buildURL(guiRequest, GetISSURL.getEBI("103") + "updateAnnotation?");
        }

        if (query == null) {
            return false;
        }

        // talk to EBI
        LOG.log(Level.SEVERE, "[EBI QUERY] {0}", query);
        boolean result = talk(query, version);
        return result;
    }

    /**
     * Creates the full URL by adding parameters given by the client. Only
     * needed for
     * {@link #sendAnnotation(org.bisel.phis.iss.model.Request, java.lang.String) sendAnnotation}
     * and
     * {@link #updateAnnotation(org.bisel.phis.iss.model.Request, java.lang.String) updateAnnotation}
     *
     * @param guiRequest
     * @param query
     * @return The completed URL that should be called by
     * @see #talk(java.lang.String)
     */
    private String buildURL(Request guiRequest, String query) {

        try {
            query += "associatedImageId=" + URLEncoder.encode(guiRequest.getImageID(), "UTF-8");
            query += "&annotationId=" + URLEncoder.encode(guiRequest.getPhisID(), "UTF-8");
            query += "&userId=" + URLEncoder.encode(guiRequest.getCreatorID(), "UTF-8");
            query += "&publish=" + URLEncoder.encode(guiRequest.getPublish().toString(), "UTF-8");

            // deal with whole images   
            // don't think this is needed now as everything is a "real value"
            for (String temp : guiRequest.getxValues()) {
                query += "&xCoordinates=" + URLEncoder.encode(temp, "UTF-8");
            }
            for (String temp : guiRequest.getyValues()) {
                query += "&yCoordinates=" + URLEncoder.encode(temp, "UTF-8");
            }
            for (String temp : guiRequest.getzValues()) {
                query += "&zCoordinates=" + URLEncoder.encode(temp, "UTF-8");
            }

            // deal with all the optional ones ... assuming they exist 
            if (guiRequest.getUserGroupID() != null && !guiRequest.getUserGroupID().equals("")) {
                query += "&userGroupId=" + URLEncoder.encode(guiRequest.getUserGroupID(), "UTF-8");
            }
            if (guiRequest.getChannelID() != null && !guiRequest.getChannelID().equals("")) {
                query += "&associatedChannel=" + URLEncoder.encode(guiRequest.getChannelID(), "UTF-8");
            }

            if (guiRequest.getDepictedAnatomyID() != null) {
                for (String temp : guiRequest.getDepictedAnatomyID()) {
                    query += "&depictedAnatomyId=" + URLEncoder.encode(temp, "UTF-8");
                }
            }
            if (guiRequest.getDepictedAnatomyTerm() != null) {
                for (String temp : guiRequest.getDepictedAnatomyTerm()) {
                    query += "&depictedAnatomyTerm=" + URLEncoder.encode(temp, "UTF-8");
                }
            }
            if (guiRequest.getDepictedAnatomyDescription() != null) {
                for (String temp : guiRequest.getDepictedAnatomyDescription()) {
                    query += "&depictedAnatomyFreeText=" + URLEncoder.encode(temp, "UTF-8");
                }
            }

            if (guiRequest.getAbnAnatomyID() != null) {
                for (String temp : guiRequest.getAbnAnatomyID()) {
                    query += "&abnInAnatomyId=" + URLEncoder.encode(temp, "UTF-8");
                }
            }
            if (guiRequest.getAbnAnatomyTerm() != null) {
                for (String temp : guiRequest.getAbnAnatomyTerm()) {
                    query += "&abnInAnatomyTerm=" + URLEncoder.encode(temp, "UTF-8");
                }
            }
            if (guiRequest.getAbnAnatomyDescription() != null) {
                for (String temp : guiRequest.getAbnAnatomyDescription()) {
                    query += "&abnInAnatomyFreetext=" + URLEncoder.encode(temp, "UTF-8");
                }
            }

            if (guiRequest.getGeneExpressionAnatomyID() != null) {
                for (String temp : guiRequest.getGeneExpressionAnatomyID()) {
                    query += "&expressionInAnatomyId=" + URLEncoder.encode(temp, "UTF-8");
                }
            }
            if (guiRequest.getGeneExpressionAnatomyTerm() != null) {
                for (String temp : guiRequest.getGeneExpressionAnatomyTerm()) {
                    query += "&expressionInAnatomyTerm=" + URLEncoder.encode(temp, "UTF-8");
                }
            }
            if (guiRequest.getGeneExpressionAnatomyDescription() != null) {
                for (String temp : guiRequest.getGeneExpressionAnatomyDescription()) {
                    query += "&expressionInAnatomyFreetext=" + URLEncoder.encode(temp, "UTF-8");
                }
            }

            if (guiRequest.getPhenotypeID() != null) {
                for (String temp : guiRequest.getPhenotypeID()) {
                    query += "&phenotypeId=" + URLEncoder.encode(temp, "UTF-8");
                }
            }
            if (guiRequest.getPhenotypeTerm() != null) {
                for (String temp : guiRequest.getPhenotypeTerm()) {
                    query += "&phenotypeTerm=" + URLEncoder.encode(temp, "UTF-8");
                }
            }
            if (guiRequest.getPhenotypeDescription() != null) {
                for (String temp : guiRequest.getPhenotypeDescription()) {
                    query += "&phenotypeFreetext=" + URLEncoder.encode(temp, "UTF-8");
                }
            }
        } catch (UnsupportedEncodingException e) {
            message = "cannot encode EBI URL";
            return null;
        }

        return query;
    }

    /**
     * Communicates with the EBI API.
     *
     * @param queryURL The URI which should be used to perform the action
     * desired by the end user.
     * @return True if no error otherwise false.
     */
    private boolean talk(String queryURL, String version) {
        BufferedReader in = null;
        try {
            // connect to SOLR and run query
            URL url = new URL(queryURL);
            // 101 onwards must be https
            String toBeEncoded = "iss:" + GetISSURL.getEBIToken();
            String encoding = Base64.encodeBase64URLSafeString(toBeEncoded.getBytes());
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            // HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            InputStream content = (InputStream) connection.getInputStream();
            in = new BufferedReader(new InputStreamReader(content));
            // read JSON result
            String inputLine;
            if ((inputLine = in.readLine()) != null) { // should only be 1 line of result
                // can currently return the API result unchanged
                message = inputLine;  // might need to change this
            }
            in.close();
            LOG.log(Level.INFO, "EBI response: {0}", message);

            // program around EBI's error messages to
            // make them fit my standard
            if (message.contains("FAIL"));
            StringTokenizer st = new StringTokenizer(inputLine, "\"");
            st.nextToken();
            st.nextToken();
            st.nextToken();
            message = st.nextToken();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "[EBI COMM] {0}", e.getMessage());
            message = "error communicating with the EBI";
            return false;
        }
        return true;
    }

    /**
     * Returns any error/success message that was recorded.
     *
     * @return The message.
     */
    public String getMessage() {
        return this.message;
    }
}

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
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bisel.phis.iss.model.Request;
import org.bisel.phis.iss.model.Request_Delete;

/**
 * Communicates with SDB service in order to update spatial database.
 *
 * Currently assuming that the
 * <ol>
 * <li>SDB service (& associated DB) will always be run on lxbisel;</li>
 * <li>ISS will always run on lxbisel.</li>
 * </ol>
 *
 * The documentation for the SDB is available here:
 * http://lxbisel.macs.hw.ac.uk:8080/SDB/
 *
 *
 * @author kcm
 */
public class SpatialDBClient {

    private static final Logger LOG = Logger.getLogger(SpatialDBClient.class.getName());
    private String message;

    public boolean sendAnnotation(Request guiRequest) {
        /*
         // SDB only supports creation at the moment...
         String query = "http://lxbisel.macs.hw.ac.uk:8080/" + "SDB/annotation?action=create"; // &markup=" + guiRequest.getType();
         try {
         query += "&imageid=" + URLEncoder.encode(guiRequest.getImageID(), "UTF-8");
         query += "&phisid=" + URLEncoder.encode(guiRequest.getPhisID(), "UTF-8");
         query += "&creatorid=" + URLEncoder.encode(guiRequest.getCreatorID(), "UTF-8");

         // optional params below
         if (!guiRequest.getType().equalsIgnoreCase("whole_image")) {
         query += "&x_values=" + URLEncoder.encode(guiRequest.getxValues(), "UTF-8");
         query += "&y_values=" + URLEncoder.encode(guiRequest.getyValues(), "UTF-8");
         query += "&z_values=" + URLEncoder.encode(guiRequest.getzValues(), "UTF-8");
         }

         //
         if (guiRequest.getChannelID() != null && !guiRequest.getChannelID().equals("")) {
         query += "&channelid=" + URLEncoder.encode(guiRequest.getChannelID(), "UTF-8");
         }

         //
         if (guiRequest.getPhenotypeID() != null && !guiRequest.getPhenotypeID().equals("")) {
         query += "&phenotypeids=" + URLEncoder.encode(guiRequest.getPhenotypeID(), "UTF-8");
         }

         if (guiRequest.getPhenotypeDescription() != null && !guiRequest.getPhenotypeDescription().equals("")) {
         query += "&phenotype_text=" + URLEncoder.encode(guiRequest.getPhenotypeDescription(), "UTF-8");
         }

         //
         if (guiRequest.getAbnAnatomyID() != null && !guiRequest.getAbnAnatomyID().equals("")) {
         query += "&abn_anatomyids=" + URLEncoder.encode(guiRequest.getAbnAnatomyID(), "UTF-8");
         }

         if (guiRequest.getAbnAnatomyDescription() != null && !guiRequest.getAbnAnatomyDescription().equals("")) {
         query += "&abn_anatomy_text=" + URLEncoder.encode(guiRequest.getAbnAnatomyDescription(), "UTF-8");
         }

         //
         if (guiRequest.getGeneExpressionAnatomyID() != null && !guiRequest.getGeneExpressionAnatomyID().equals("")) {
         query += "&ge_anatomyids=" + URLEncoder.encode(guiRequest.getGeneExpressionAnatomyID(), "UTF-8");
         }

         if (guiRequest.getGeneExpressionAnatomyDescription() != null && !guiRequest.getGeneExpressionAnatomyDescription().equals("")) {
         query += "&ge_anatomy_text=" + URLEncoder.encode(guiRequest.getGeneExpressionAnatomyDescription(), "UTF-8");
         }

         //
         if (guiRequest.getDepictedAnatomyID() != null && !guiRequest.getDepictedAnatomyID().equals("")) {
         query += "&dpt_anatomyids=" + URLEncoder.encode(guiRequest.getDepictedAnatomyID(), "UTF-8");
         }

         if (guiRequest.getDepictedAnatomyDescription() != null && !guiRequest.getDepictedAnatomyDescription().equals("")) {
         query += "&dpt_anatomy_text=" + URLEncoder.encode(guiRequest.getDepictedAnatomyDescription(), "UTF-8");
         }
         } catch (UnsupportedEncodingException e) {
         message = "cannot encode SDB URL";
         return false;
         }

         // end of params
         LOG.log(Level.INFO, "[SDB QUERY] {0}", query);
         boolean result = talk(query);
         return result;
         */
        return false;
    }

    public boolean deleteAnnotation(Request_Delete guiRequest) {
        String query = "http://lxbisel.macs.hw.ac.uk:8080/" + "SDB/annotation?action=delete";
        try {
            query += "&phisid=" + URLEncoder.encode(guiRequest.getPhisID(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            message = "cannot encode SDB URL";
            return false;
        }
        LOG.log(Level.INFO, "[SDB QUERY] {0}", query);
        boolean result = talk(query);
        return result;
    }

    public boolean editAnnotation(Request guiRequest) {
        /*
         // SDB only supports creation at the moment...
         String query = "http://lxbisel.macs.hw.ac.uk:8080/" + "SDB/annotation?action=edit"; // &markup=" + guiRequest.getType();
         try {
         query += "&imageid=" + URLEncoder.encode(guiRequest.getImageID(), "UTF-8");
         query += "&phisid=" + URLEncoder.encode(guiRequest.getPhisID(), "UTF-8");
         query += "&creatorid=" + URLEncoder.encode(guiRequest.getCreatorID(), "UTF-8");

         // optional params below
         if (!guiRequest.getType().equalsIgnoreCase("whole_image")) {
         query += "&x_values=" + URLEncoder.encode(guiRequest.getxValues(), "UTF-8");
         query += "&y_values=" + URLEncoder.encode(guiRequest.getyValues(), "UTF-8");
         }

         //
         if (guiRequest.getChannelID() != null && !guiRequest.getChannelID().equals("")) {
         query += "&channelid=" + URLEncoder.encode(guiRequest.getChannelID(), "UTF-8");
         }

         //
         if (guiRequest.getPhenotypeID() != null && !guiRequest.getPhenotypeID().equals("")) {
         query += "&phenotypeids=" + URLEncoder.encode(guiRequest.getPhenotypeID(), "UTF-8");
         }

         if (guiRequest.getPhenotypeDescription() != null && !guiRequest.getPhenotypeDescription().equals("")) {
         query += "&phenotype_text=" + URLEncoder.encode(guiRequest.getPhenotypeDescription(), "UTF-8");
         }

         //
         if (guiRequest.getAbnAnatomyID() != null && !guiRequest.getAbnAnatomyID().equals("")) {
         query += "&abn_anatomyids=" + URLEncoder.encode(guiRequest.getAbnAnatomyID(), "UTF-8");
         }

         if (guiRequest.getAbnAnatomyDescription() != null && !guiRequest.getAbnAnatomyDescription().equals("")) {
         query += "&abn_anatomy_text=" + URLEncoder.encode(guiRequest.getAbnAnatomyDescription(), "UTF-8");
         }

         //
         if (guiRequest.getGeneExpressionAnatomyID() != null && !guiRequest.getGeneExpressionAnatomyID().equals("")) {
         query += "&ge_anatomyids=" + URLEncoder.encode(guiRequest.getGeneExpressionAnatomyID(), "UTF-8");
         }

         if (guiRequest.getGeneExpressionAnatomyDescription() != null && !guiRequest.getGeneExpressionAnatomyDescription().equals("")) {
         query += "&ge_anatomy_text=" + URLEncoder.encode(guiRequest.getGeneExpressionAnatomyDescription(), "UTF-8");
         }

         //
         if (guiRequest.getDepictedAnatomyID() != null && !guiRequest.getDepictedAnatomyID().equals("")) {
         query += "&dpt_anatomyids=" + URLEncoder.encode(guiRequest.getDepictedAnatomyID(), "UTF-8");
         }

         if (guiRequest.getDepictedAnatomyDescription() != null && !guiRequest.getDepictedAnatomyDescription().equals("")) {
         query += "&dpt_anatomy_text=" + URLEncoder.encode(guiRequest.getDepictedAnatomyDescription(), "UTF-8");
         }
         } catch (UnsupportedEncodingException e) {
         message = "cannot encode SDB URL";
         return false;
         }

         // end of params
         LOG.log(Level.INFO, "[SDB QUERY] {0}", query);
         boolean result = talk(query);
         return result;
         */
        return false;
    }

    private boolean talk(String queryURL) {
        BufferedReader in = null;
        try {
            // connect to SOLR and run query
            LOG.severe(queryURL);
            URL url = new URL(queryURL);
            in = new BufferedReader(new InputStreamReader(url.openStream()));

            // read JSON result
            message = "";
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                message += inputLine;
            }
            LOG.log(Level.INFO, "SDB response: {0}", message);
            in.close();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "[SDB COMM] {0}", e.getMessage());
            message = "unknown error communicating with spatialDB";
            return false;
        }
        return true;
    }

    public String getMessage() {
        return this.message;
    }
}

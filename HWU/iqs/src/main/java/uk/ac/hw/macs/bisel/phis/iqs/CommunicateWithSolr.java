/*
 * Copyright 2015 BISEL, Heriot-Watt University, Edinburgh, UK (http://www.bisel.org).
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
package uk.ac.hw.macs.bisel.phis.iqs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the actual communication with the SOLR API
 *
 * @author kcm
 */
public class CommunicateWithSolr {

    private static final Logger logger = Logger.getLogger(System.class.getName());

    /**
     * Contacts the EBI and returns whatever JSON the EBI returns.
     *
     * @param queryURL The full URL that should be used to query SOLR
     * @return JSON output from SOLR
     */
    public String talk(String queryURL) {
        // record all queries sent to SOLR
        logger.log(Level.INFO, "[EBI QUERY] {0}", queryURL);
        String solrResult = "";
        try {
            solrResult = actualTalk(queryURL);
        } catch (IOException e) {
            logger.log(Level.WARNING, "1st attempt failed to communicate with SOLR");
            logger.log(Level.WARNING, e.getMessage());
            StackTraceElement[] stes = e.getStackTrace();
            if (stes.length == 0) {
                logger.log(Level.INFO, "no stracktrace!");
            } else {
                logger.log(Level.INFO, "starting stacktrace...");
                for (StackTraceElement ste : stes) {
                    logger.log(Level.INFO, ste.toString());
                }
                logger.log(Level.INFO, "... end of stacktrace!");
            }

            // making a second call immediately fails
            // try a 1 second delay
            try {
                Thread.sleep(1000); //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            try {
                solrResult = actualTalk(queryURL);
            } catch (IOException e2) {
                logger.log(Level.SEVERE, "2nd attempt failed to communicate with SOLR");
                solrResult = "{\"server_error\": \"" + e2.getMessage() + "\"}";
            }

        }
        return solrResult;
    }

    /**
     * Communicates directly with EBI SOLR API
     *
     * @param queryURL the query URL to be called
     * @return the JSON returned by the EBI
     * @throws IOException Any issue with connection results in exception
     */
    private String actualTalk(String queryURL) throws IOException {
        // connect to SOLR and run query

        URL url = new URL(queryURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);        
        InputStream content = (InputStream) connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(content));         

        // read JSON result
        String inputLine;
        if ((inputLine = in.readLine()) != null) { // should only be 1 line of result
            // can currently return the API result unchanged
            return inputLine;
        }
        in.close();
        connection.disconnect();

        return "";
    }

}

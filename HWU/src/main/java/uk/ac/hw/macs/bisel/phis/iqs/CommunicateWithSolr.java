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
import java.io.InputStreamReader;
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
    
    public String talk(String queryURL) {
        // record all queries sent to SOLR
        logger.log(Level.INFO, "[EBI QUERY] {0}", queryURL);        
        
        BufferedReader in = null;
        String solrResult = "";
        try {
            // connect to SOLR and run query
            URL url = new URL(queryURL);
            in = new BufferedReader(new InputStreamReader(url.openStream()));

            // read JSON result
            String inputLine;
            if ((inputLine = in.readLine()) != null) { // should only be 1 line of result
                // can currently return the API result unchanged
                solrResult = inputLine;
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
            solrResult = "{\"server_error\": \"" + e.getMessage() + "\"}";
        }
        return solrResult;
    }
}

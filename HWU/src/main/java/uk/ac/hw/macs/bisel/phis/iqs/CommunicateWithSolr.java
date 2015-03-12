/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        logger.log(Level.INFO, "[QUERY] {0}", queryURL);        
        
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

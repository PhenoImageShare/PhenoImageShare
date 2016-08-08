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
import java.net.URL;
import java.util.StringTokenizer;
import java.util.logging.Logger;

/**
 * Obtains a phis ID from the EBI accession id generator.
 * 
 * @author kcm
 */
public class PIGClient {
    private static final String uriEnd = "idGen/getAnnotationId";    
    private static final Logger LOG = Logger.getLogger(PIGClient.class.getName());
    
    
    /**
     * Returns a unique phis accession id from the EBI generator service
     * @return Unique ID
     */
    public String getID(String version) {
        String id="";
        BufferedReader in;
        try {
            // connect to SOLR and run query
            URL url;
            
            String ipa = GetISSURL.getEBI(version);
            ipa = ipa.replace("submission/", uriEnd);
            url = new URL(ipa);
            
            in = new BufferedReader(new InputStreamReader(url.openStream()));

            // read JSON result
            String inputLine;
            if ((inputLine = in.readLine()) != null) { // should only be 1 line of result                                
                StringTokenizer st = new StringTokenizer(inputLine, ":\"{}");
                st.nextToken(); // throw away
                id = st.nextToken();                
            }
            in.close();
        } catch (IOException e) {
            LOG.severe(e.getMessage());                        
        }
        return id;
    }       
}

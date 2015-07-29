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

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Returns the URI of the current host assuming that the properties file has been updated using the correct maven profile.
 * 
 * 
 * @author kcm
 */
public class GetHost {
    
    private static final Logger LOGGER = Logger.getLogger(GetHost.class.getName());
    
    public static String getURI() {
        String uri = "error";
        ResourceBundle rb;
        try {
            rb = ResourceBundle.getBundle("host");
            uri = rb.getString("host");
        } catch (MissingResourceException e) {
            LOGGER.warning("File 'host.properties' was not found or error while reading current version.");
        }   
        return uri;
    }
    
    public static String getEBI() {
        String uri = "error";
        ResourceBundle rb;
        try {
            rb = ResourceBundle.getBundle("host");
            uri = rb.getString("ebi");            
        } catch (MissingResourceException e) {
            LOGGER.warning("File 'host.properties' was not found or error while reading current version.");
        }   
        return uri;        
    }
    
    public static void main(String[] args) {        
        System.out.println(getURI());
    }
    
}

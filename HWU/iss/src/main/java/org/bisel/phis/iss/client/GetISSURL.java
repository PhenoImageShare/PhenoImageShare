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

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 *
 * @author dev
 */
public class GetISSURL {

    private static final Logger LOG = Logger.getLogger(GetISSURL.class.getName());

    public static String getURL() {
        String uri = "";
        ResourceBundle rb;
        try {
            rb = ResourceBundle.getBundle("host");
            uri = rb.getString("host");
        } catch (MissingResourceException e) {
            LOG.severe(e.getMessage());
        }
        return uri;
    }

    public static String getEBI(String version) {
        String machine = "";
        ResourceBundle rb;
        try {
            rb = ResourceBundle.getBundle("host");
            machine = rb.getString("ebi");
        } catch (MissingResourceException e) {
            LOG.severe(e.getMessage());
        }
        
        if (version.equals("101")) {
            return "https://" + machine + ".phenoimageshare.org/data/v1.0.1/rest/submission/";
        } else if (version.equals("102")) {
            return "https://" + machine + ".phenoimageshare.org/data/v1.0.2/rest/submission/";
        }
        return "https://" + machine + ".phenoimageshare.org/data/v1.0.3/rest/submission/";
    }

    public static String getEBIToken() {
        ResourceBundle rb;
        String token = "";
        try {
            rb = ResourceBundle.getBundle("host");
            token = rb.getString("pass");
        } catch (MissingResourceException e) {
            LOG.severe(e.getMessage());
        }
        return token;
    }
    
    public static void main(String[] args) {
        System.out.println(GetISSURL.getURL());
        System.out.println(GetISSURL.getEBIToken());
    }
}

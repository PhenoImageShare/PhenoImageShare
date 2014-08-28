/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    public static void main(String[] args) {        
        System.out.println(getURI());
    }
    
}

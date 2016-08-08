/*
 * Copyright 2015 BISEL, Heriot-Watt University, Edinburgh, UK (www.bisel.org)
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
package org.bisel.phis.iss.model;

import java.util.logging.Logger;

/**
 * Model that represents the user's request to delete an existing annotation
 * 
 * @author kcm
 */
public class Request_Delete {
                
    private String message;    
    private String phisID;
    private String creatorID;
    
    private static final Logger LOG = Logger.getLogger(Request_Delete.class.getName());
    
    /** Ensures the current instance is correct and proper
     * 
     * @return True if valid otherwise false
     */
    public boolean validate() {
        if (phisID == null || phisID.equals("")) {
            message = "missing phis id";
            return false;
        } else {
            phisID = phisID.trim();
        }  
        if (creatorID == null || creatorID.equalsIgnoreCase("")) {
            message = "missing creator";
            return false;
        } else {
            creatorID = creatorID.trim();
        }        
        return true;
    }    
    
    /*
    
    
    
     */
    public Request_Delete() {
    }

    public Request_Delete(String phisID, String creatorID) {
        this.phisID = phisID;
        this.creatorID = creatorID;
    }
    
    public String getMessage() {
        return this.message;
    }    
    
    public String getCreatorID() {
        return this.creatorID;
    }
    
    public String getPhisID() {
        return this.phisID;
    }
}

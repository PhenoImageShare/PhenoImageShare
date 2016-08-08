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
package org.bisel.phis.iss.model;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Data model for the input to the ISS, which normally comes from a GUI
 * developed by the HGU.
 *
 *
 * @author kcm
 */
public class Request {

    private boolean validated = false;
    private String phisID;
    private String creatorID;
    private String imageID;
    private String userGroupID;
    private String channelID;
    private ArrayList<String> xValues;
    private ArrayList<String> yValues;
    private ArrayList<String> zValues;
    private ArrayList<String> depictedAnatomyID;
    private ArrayList<String> depictedAnatomyTerm;
    private ArrayList<String> depictedAnatomyDescription;
    private ArrayList<String> geneExpressionAnatomyID;
    private ArrayList<String> geneExpressionAnatomyTerm;
    private ArrayList<String> geneExpressionAnatomyDescription;
    private ArrayList<String> abnAnatomyID;
    private ArrayList<String> abnAnatomyTerm;
    private ArrayList<String> abnAnatomyDescription;
    private ArrayList<String> phenotypeID;
    private ArrayList<String> phenotypeTerm;
    private ArrayList<String> phenotypeDescription;
    private String message;

    private static final Logger LOG = Logger.getLogger(Request.class.getName());

    /**
     * Validates the data supplied to the model and ensures the minimum data is
     * present
     *
     * @return True if valid otherwise false
     */
    public boolean validate() {

        if (phisID == null || phisID.equals("")) {
            message = "missing phis id";
            return false;
        }

        if (creatorID == null || creatorID.equalsIgnoreCase("")) {
            message = "missing creator";
            return false;
        }

        if (imageID == null || imageID.equalsIgnoreCase("")) {
            message = "missing image id";
            return false;
        }

        // otherwise points needed
        if (xValues == null || xValues.isEmpty()) {
            message = "[missing x coordinates";
            return false;
        }

        if (yValues == null || yValues.isEmpty()) {
            message = "missing y coordinates";
            return false;
        }

        if (zValues == null || zValues.isEmpty()) {
            message = "missing z coordinates";
            return false;
        }

        // must have same number of x & y values
        if (xValues.size() != yValues.size() || xValues.size() != zValues.size()) {
            message = "you must supply the same number of x, y & z values";
            return false;
        }

        // cords are %, so must be between 0 & 100
        try {
            for (String temp : xValues) {
                Float x = new Float(temp);
                if (x >= 0 && x <= 100) {

                } else {
                    message = "some of the x values are outwith bounds";
                    return false;
                }
            }
            for (String temp : yValues) {
                Float y = new Float(temp);
                if (y >= 0 && y <= 100) {

                } else {
                    message = "some of the y values are outwith bounds";
                    return false;
                }                
            }
            for(String temp : zValues) {
                Float z = new Float(temp);
                if (z >= 0 && z <= 100) {

                } else {
                    message = "some of the z values are outwith bounds";
                    return false;
                }                
            }
        } catch (RuntimeException e) {
            message = "some of the coordinate values cannot be converted into floats";
            return false;
        }

        if (abnAnatomyID == null && this.geneExpressionAnatomyID == null && this.depictedAnatomyID == null && this.phenotypeID == null) {
            message = "you must supply some form of annotation (anatomical term or phenotype term)";
            return false;
        }

        // pointless test for EBI
        if (depictedAnatomyID != null && depictedAnatomyTerm == null) {
            message = "you must supply the ontology term AND matching id";
            return false;
        }

        if (depictedAnatomyID == null && depictedAnatomyTerm != null) {
            message = "you must supply the ontology term AND matching id";
            return false;
        }

        if (geneExpressionAnatomyID != null && geneExpressionAnatomyTerm == null) {
            message = "you must supply the ontology term AND matching id";
            return false;
        }

        if (geneExpressionAnatomyID == null && geneExpressionAnatomyTerm != null) {
            message = "you must supply the ontology term AND matching id";
            return false;
        }

        if (abnAnatomyID != null && abnAnatomyTerm == null) {
            message = "you must supply the ontology term AND matching id";
            return false;
        }

        if (abnAnatomyID != null && abnAnatomyTerm == null) {
            message = "you must supply the ontology term AND matching id";
            return false;
        }

        if (phenotypeID != null && phenotypeTerm == null) {
            message = "you must supply the ontology term AND matching id";
            return false;
        }

        if (phenotypeID != null && phenotypeTerm == null) {
            message = "you must supply the ontology term AND matching id";
            return false;
        }

        validated = true;
        return true;
    }

    /*
    
    
    
     */
    
    /**
     * Returns the error message if validation failed
     * @return Error message
     * @see #validate() 
     */
    public String getMessage() {
        return this.message;
    }

    /*
    
    
    
     */
    public Request() {
    }

    /**
     * Constructor that allows creation of a Request object
     * @param phisID Mandatory
     * @param creatorID Mandatory
     * @param imageID Mandatory
     * @param userGroupID Optional
     * @param channelID Optional 
     * @param xValues Mandatory - can have more than 1
     * @param yValues Mandatory - can have more than 1
     * @param zValues Mandatory - can have more than 1
     * @param depictedAnatomyID Optional - if supplied must have equal number of phenoTypeTerms
     * @param depictedAnatomyTerm Optional
     * @param depictedAnatomyDescription Optional
     * @param geneExpressionAnatomyID Optional - if supplied must have equal number of phenoTypeTerms
     * @param geneExpressionAnatomyTerm Optional
     * @param geneExpressionAnatomyDescription
     * @param abnAnatomyID Optional - if supplied must have equal number of phenoTypeTerms
     * @param abnAnatomyTerm Optional
     * @param abnAnatomyDescription Optional
     * @param phenotypeID Optional - if supplied must have equal number of phenoTypeTerms
     * @param phenotypeTerm Optional
     * @param phenotypeDescription Optional
     */
    public Request(String phisID, String creatorID, String userGroupID, String imageID, String channelID, ArrayList<String> xValues, ArrayList<String> yValues, ArrayList<String> zValues, ArrayList<String> depictedAnatomyID, ArrayList<String> depictedAnatomyTerm, ArrayList<String> depictedAnatomyDescription, ArrayList<String> geneExpressionAnatomyID, ArrayList<String> geneExpressionAnatomyTerm, ArrayList<String> geneExpressionAnatomyDescription, ArrayList<String> abnAnatomyID, ArrayList<String> abnAnatomyTerm, ArrayList<String> abnAnatomyDescription, ArrayList<String> phenotypeID, ArrayList<String> phenotypeTerm, ArrayList<String> phenotypeDescription) {
        this.phisID = phisID;
        this.creatorID = creatorID;
        this.imageID = imageID;
        this.userGroupID = userGroupID;
        this.channelID = channelID;
        this.xValues = xValues;
        this.yValues = yValues;
        this.zValues = zValues;
        this.depictedAnatomyID = depictedAnatomyID;
        this.depictedAnatomyTerm = depictedAnatomyTerm;
        this.depictedAnatomyDescription = depictedAnatomyDescription;
        this.geneExpressionAnatomyID = geneExpressionAnatomyID;
        this.geneExpressionAnatomyTerm = geneExpressionAnatomyTerm;
        this.geneExpressionAnatomyDescription = geneExpressionAnatomyDescription;
        this.abnAnatomyID = abnAnatomyID;
        this.abnAnatomyTerm = abnAnatomyTerm;
        this.abnAnatomyDescription = abnAnatomyDescription;
        this.phenotypeID = phenotypeID;
        this.phenotypeTerm = phenotypeTerm;
        this.phenotypeDescription = phenotypeDescription;
    }

    /*
    
    
     */
    public boolean isValidated() {
        return validated;
    }

    public String getPhisID() {
        return phisID;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public String getImageID() {
        return imageID;
    }

    public String getUserGroupID() {
        return userGroupID;
    }
    
    public String getChannelID() {
        return channelID;
    }

    public ArrayList<String> getxValues() {
        return xValues;
    }

    public ArrayList<String> getyValues() {
        return yValues;
    }

    public ArrayList<String> getzValues() {
        return zValues;
    }

    public ArrayList<String> getDepictedAnatomyID() {
        return depictedAnatomyID;
    }

    public ArrayList<String> getDepictedAnatomyTerm() {
        return depictedAnatomyTerm;
    }

    public ArrayList<String> getDepictedAnatomyDescription() {
        return depictedAnatomyDescription;
    }

    public ArrayList<String> getGeneExpressionAnatomyID() {
        return geneExpressionAnatomyID;
    }

    public ArrayList<String> getGeneExpressionAnatomyTerm() {
        return geneExpressionAnatomyTerm;
    }

    public ArrayList<String> getGeneExpressionAnatomyDescription() {
        return geneExpressionAnatomyDescription;
    }

    public ArrayList<String> getAbnAnatomyID() {
        return abnAnatomyID;
    }

    public ArrayList<String> getAbnAnatomyTerm() {
        return abnAnatomyTerm;
    }

    public ArrayList<String> getAbnAnatomyDescription() {
        return abnAnatomyDescription;
    }

    public ArrayList<String> getPhenotypeID() {
        return phenotypeID;
    }

    public ArrayList<String> getPhenotypeTerm() {
        return phenotypeTerm;
    }

    public ArrayList<String> getPhenotypeDescription() {
        return phenotypeDescription;
    }

}

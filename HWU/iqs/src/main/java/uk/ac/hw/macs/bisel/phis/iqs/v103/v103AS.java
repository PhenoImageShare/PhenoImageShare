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
package uk.ac.hw.macs.bisel.phis.iqs.v103;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uk.ac.hw.macs.bisel.phis.iqs.CommunicateWithSolr;
import uk.ac.hw.macs.bisel.phis.iqs.GetHost;

/**
 *
 * @author kcm
 */
@WebServlet(name = "v103AS", urlPatterns = {"/v103AS"})
public class v103AS extends HttpServlet {

    private static final String url = GetHost.getEBI("103")+"getAutosuggest?"; // stem of every SOLR query
    private static final Logger logger = Logger.getLogger(System.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        boolean error = false; // has an error been detected?
        String solrResult = ""; // JSON doc sent back to UI

        // create URL for SOLR query
        String queryURL = url;
        boolean first = true;
        Map<String, String[]> params = request.getParameterMap(); // get map of parameters and their values
        Enumeration<String> allParams = request.getParameterNames(); // get a list of parameter names
        while (allParams.hasMoreElements()) {
            String param = allParams.nextElement();
            if (param.equals("term")) { // ID of channel
                if (!first) { // at the moment it will always be the first (and only) param
                    queryURL += "&";
                }

                queryURL += "term=" + URLEncoder.encode(params.get("term")[0], "UTF-8"); // extend stem with parameter                
                first = false; // next time you need a separator

                // choose number of results to ask for... lots of results is very costly    
            } else if (param.equals("num")) { // number of results to return
                if (!first) {
                    queryURL += "&";
                }
                // ensure a number is supplied by GUI
                Integer temp = 1;
                try {
                    temp = new Integer(params.get("num")[0]);                                                       
                } catch (NumberFormatException nfe) {
                    error = true;
                    solrResult = "{\"invalid_num_specified\": \"" + params.get("num")[0] + "\"}";     
                    break;                    
                }                
                
                queryURL += "resultNo=" + URLEncoder.encode(params.get("num")[0], "UTF-8");
                first = false;  // next time you need a separator                            
                
                // 2015-03-17
            } else if (param.equals("type")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "type=" + URLEncoder.encode(params.get("type")[0], "UTF-8");
                first = false;  // next time you need a separator                 
            } else if (param.equals("stage")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "stage=" + URLEncoder.encode(params.get("stage")[0], "UTF-8");
                first = false;  // next time you need a separator                 
            } else if (param.equals("imagingMethod")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "imagingMethod=" + URLEncoder.encode(params.get("imagingMethod")[0], "UTF-8");
                first = false;  // next time you need a separator                 
            } else if (param.equals("taxon")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "taxon=" + URLEncoder.encode(params.get("taxon")[0], "UTF-8");
                first = false;  // next time you need a separator                 
            } else if (param.equals("sampleType")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "sampleType=" + URLEncoder.encode(params.get("sampleType")[0], "UTF-8");
                first = false;  // next time you need a separator                 
            } else if (param.equals("imageGeneratedBy")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "imageGeneratedBy=" + URLEncoder.encode(params.get("imageGeneratedBy")[0], "UTF-8");
                first = false;  // next time you need a separator 
                
            } else if (param.equalsIgnoreCase("asType")) {
                if (!first) {
                    queryURL += "&";
                }
                
                String asType = params.get("asType")[0];
                if(asType.equals("GENE") || asType.equals("ANATOMY") || asType.equals("PHENOTYPE")) {
                    queryURL += "autosuggestType=" + URLEncoder.encode(asType, "UTF-8");
                } else {
                    error = true;
                    solrResult = "{\"invalid_paramater_value\": \"" + asType + "\"}";     
                    break;
                }                                
                first = false;  // next time you need a separator  
             
            // v102    
            } else if(param.equalsIgnoreCase("hostName")) {
                if (!first) {
                    queryURL += "&";
                }
                
                String host = params.get("hostName")[0];
                queryURL += "hostName=" + URLEncoder.encode(host, "UTF-8");
                first = false;  // next time you need a separator  
                
            } else if (param.equalsIgnoreCase("version")) {
                // do nothing
                
                //
                //
                //
                // @depreciated   
            } else if (param.equals("mutantGene")) {
                if (!first) { // at the moment it will always be the first (and only) param
                    queryURL += "&";
                }                
                queryURL += "mutantGene=" + URLEncoder.encode(params.get("mutantGene")[0], "UTF-8"); // extend stem with parameter
                first = false; // next time you need a separator                
                
                
                // @depreciated
            } else if (param.equals("expressedGeneOrAllele")) {
                if (!first) { // at the moment it will always be the first (and only) param
                    queryURL += "&";
                }

                queryURL += "expressedGeneOrAllele=" + URLEncoder.encode(params.get("expressedGeneOrAllele")[0], "UTF-8"); // extend stem with parameter
                first = false; // next time you need a separator     
                
                
                // @depreciated
            } else if (param.equals("phenotype")) {
                if (!first) { // at the moment it will always be the first (and only) param
                    queryURL += "&";
                }

                queryURL += "phenotype=" + URLEncoder.encode(params.get("phenotype")[0], "UTF-8"); // extend stem with parameter
                first = false; // next time you need a separator                 
                
                
                
                
            } else { // parameter was not recognised, send error
                error = true; // error has been detected
                logger.log(Level.WARNING, "Client sent invalid parameter: {0}", param);
                solrResult = "{\"invalid_paramater\": \"" + param + "\"}";
                break;
            }
        }

        // run query against SOLR API
        if (!error) { // if no error detected            
            CommunicateWithSolr cws = new CommunicateWithSolr();
            solrResult = cws.talk(queryURL);
        } else {
            logger.log(Level.SEVERE, "[BAD QUERY] {0}", queryURL);
        }

        try ( // send result to client (UI)
                PrintWriter out = response.getWriter()) {
            out.println(solrResult); // may be error or genuine result
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

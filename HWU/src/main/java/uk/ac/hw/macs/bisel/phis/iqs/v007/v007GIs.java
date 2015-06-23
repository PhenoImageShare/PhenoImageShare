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
package uk.ac.hw.macs.bisel.phis.iqs.v007;

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

/**
 *
 * @author kcm
 */
@WebServlet(name = "v007GIs", urlPatterns = {"/v007GIs"})
public class v007GIs extends HttpServlet {

    private static final String url = "http://beta.phenoimageshare.org/data/v0.0.7/rest/getImages?"; // stem of every SOLR query
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

        // set response type to JS and allow programs from other servers to send and receive
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
            if (param.equalsIgnoreCase("phenotype")) { // deal with phenotypes
                if (!first) { // if this is not the first parameter added to queryURL include separator
                    queryURL += "&";
                }
                queryURL += "phenotype=" + URLEncoder.encode(params.get("phenotype")[0], "UTF-8"); // extend stem with parameter
                first = false; // next time you need a separator
            } else if (param.equalsIgnoreCase("ptId")) { // deal with phenotypes
                if (!first) { // if this is not the first parameter added to queryURL include separator
                    queryURL += "&";
                }
                queryURL += "phenotype=" + URLEncoder.encode(params.get("ptId")[0], "UTF-8"); // extend stem with parameter
                first = false; // next time you need a separator                
            } else if (param.equalsIgnoreCase("anatomy")) { // deal with anatomy 
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "anatomy=" + URLEncoder.encode(params.get("anatomy")[0], "UTF-8");
                first = false;
            } else if (param.equalsIgnoreCase("aId")) { // deal with anatomy 
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "anatomy=" + URLEncoder.encode(params.get("aId")[0], "UTF-8");
                first = false;
            } else if (param.equalsIgnoreCase("mutantGene")) { // deal with genes
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "mutantGene=" + URLEncoder.encode(params.get("mutantGene")[0], "UTF-8");
                first = false;

                // depreciated    
            } else if (param.equalsIgnoreCase("gene")) { // deal with genes
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "gene=" + URLEncoder.encode(params.get("gene")[0], "UTF-8");
                first = false;
            } else if (param.equalsIgnoreCase("gId")) { // deal with genes
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "gene=" + URLEncoder.encode(params.get("gId")[0], "UTF-8");
                first = false;

                // generic free text    
            } else if (param.equals("term")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "term=" + URLEncoder.encode(params.get("term")[0], "UTF-8");
                first = false;

                // 2014-08-28
                // params not really needed but added to comply with EBI SOLR API
            } else if (param.equals("expressedFeature")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "expressedFeature=" + URLEncoder.encode(params.get("expressedFeature")[0], "UTF-8");
                first = false;
            } else if (param.equals("sex")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "sex=" + URLEncoder.encode(params.get("sex")[0], "UTF-8");
                first = false;
            } else if (param.equals("taxon")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "taxon=" + URLEncoder.encode(params.get("taxon")[0], "UTF-8");
                first = false;
            } else if (param.equals("stage")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "stage=" + URLEncoder.encode(params.get("stage")[0], "UTF-8");
                first = false;

            } else if (param.equals("sampleType")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "sampleType=" + URLEncoder.encode(params.get("sampleType")[0], "UTF-8");
                first = false;
            } else if (param.equals("imageType")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "imageType=" + URLEncoder.encode(params.get("imageType")[0], "UTF-8");
                first = false;
            } else if (param.equals("samplePreparation")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "samplePreparation=" + URLEncoder.encode(params.get("samplePreparation")[0], "UTF-8");
                first = false;
            } else if (param.equals("imagingMethod")) {
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "imagingMethod=" + URLEncoder.encode(params.get("imagingMethod")[0], "UTF-8");
                first = false;

                // pagination    
            } else if (param.equalsIgnoreCase("start")) { // number of initial result
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "start=" + URLEncoder.encode(params.get("start")[0], "UTF-8");
                first = false;
            } else if (param.equalsIgnoreCase("num")) { // number of results to return
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "resultNo=" + URLEncoder.encode(params.get("num")[0], "UTF-8");
                first = false;
            } else if (param.equalsIgnoreCase("version")) {
                // do nothing

            } else { // parameter was not recognised, send error
                error = true; // error has been detected
                logger.log(Level.WARNING, "Client sent invalid parameter: {0}", param);
                solrResult = "{\"invalid_paramater\": \"" + param + "\"}";
                break;
            }
        }

        // run solr query
        if (!error) { // if no error detected && not a test

            CommunicateWithSolr cws = new CommunicateWithSolr();
            solrResult = cws.talk(queryURL);
        } else {
            logger.log(Level.SEVERE, "[BAD QUERY] {0}", request.getRequestURI()+request.getQueryString());
        }

        // send result to client (UI)
        PrintWriter out = response.getWriter();
        try {
            out.println(solrResult); // may be error or genuine result
        } finally {
            out.close();
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

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
@WebServlet(name = "v007GC", urlPatterns = {"/v007GC"})
public class v007GC extends HttpServlet {
    
    private static final String url = "http://beta.phenoimageshare.org/data/v0.0.7/rest/getChannel?"; // stem of every SOLR query
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
            if (param.equalsIgnoreCase("id")) { // ID of channel
                if (!first) { // at the moment it will always be the first (and only) param
                    queryURL += "&";
                }

                queryURL += "channelId=" + URLEncoder.encode(params.get("id")[0], "UTF-8"); // extend stem with parameter
                first = false; // next time you need a separator
            } else if (param.equalsIgnoreCase("version")) {
                // do nothing

            } else { // parameter was not recognised, send error
                error = true; // error has been detected
                logger.log(Level.WARNING, "Client sent invalid parameter: " + param);
                solrResult = "{\"invalid_paramater\": \"" + param + "\"}";
                break;
            }
        }

        // run query against SOLR API
        if (!error) { // if no error detected            
            CommunicateWithSolr cws = new CommunicateWithSolr();
            solrResult = cws.talk(queryURL);
        } else {
            logger.log(Level.SEVERE, "[BAD QUERY] "+queryURL);
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

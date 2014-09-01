/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.hw.macs.bisel.phis.iqs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Facilitates auto complete services from the SOLR API
 * 
 * @author kcm
 */
public class AutoSuggest extends HttpServlet {

    private static final String url = "http://beta.phenoimageshare.org/data/rest/getAutosuggest?"; // stem of every SOLR query
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
        if (allParams.hasMoreElements()) {
            String param = allParams.nextElement();
            if (param.equals("term")) { // ID of channel
                if (!first) { // at the moment it will always be the first (and only) param
                    queryURL += "&";
                }

                queryURL += "term=" + params.get("term")[0]; // extend stem with parameter
                first = false; // next time you need a separator
            
                
            // IT offers a number of paramaters that I do not:
            // mutantGene
            // expressedGeneOrAllele
            // phenotype
            // I am not sure when these would be used so I have not implemented them    
                
                
            // choose number of results to ask for... lots of results is very costly    
            } else if (param.equals("num")) { // number of results to return
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "resultNo=" + params.get("num")[0];                
                
             
            } else { // parameter was not recognised, send error
                error = true; // error has been detected
                logger.log(Level.WARNING, "Client sent invalid parameter: " + param);
                solrResult = "{\"invalid_paramater\": \"" + param + "\"}";
            }
        }

        // should write query to log?
        // run query against SOLR API
        if (!error) { // if no error detected
            BufferedReader in = null;
            try {
                // connect to SOLR API and run query
                URL url = new URL(queryURL);
                in = new BufferedReader(new InputStreamReader(url.openStream()));

                // read JSON result
                String inputLine;
                if ((inputLine = in.readLine()) != null) { // should only be 1 line of result
                    // send result directly to client, should be no need to process (at the moment)
                    solrResult = inputLine;
                    
                    // remove the html tags IT leaves in output of SOLR
                    //solrResult = solrResult.replaceAll("<b>", "");
                    //solrResult = solrResult.replaceAll("</b>", "");
                }
            } catch (IOException e) {
                logger.log(Level.WARNING, e.getMessage());
                solrResult = "{\"server_error\": \"" + e.getMessage() + "\"}";
            }
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
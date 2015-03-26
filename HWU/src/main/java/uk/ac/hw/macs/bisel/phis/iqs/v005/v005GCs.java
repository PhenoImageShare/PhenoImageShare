/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.hw.macs.bisel.phis.iqs.v005;

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
@WebServlet(name = "v005GCs", urlPatterns = {"/v005GCs"})
public class v005GCs extends HttpServlet {

    private static final String url = "http://beta.phenoimageshare.org/data/v0.0.5/rest/getChannels?"; // stem of every SOLR query
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
            if (param.equalsIgnoreCase("imageId")) { // ID of channel
                if (!first) { // at the moment it will always be the first (and only) param
                    queryURL += "&";
                }

                queryURL += "imageId=" + URLEncoder.encode(params.get("imageId")[0], "UTF-8"); // extend stem with parameter
                first = false; // next time you need a separator

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
            } else if (param.equalsIgnoreCase("version")) {
                // do nothing

                // error handling    
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

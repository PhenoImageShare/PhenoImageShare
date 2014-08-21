package uk.ac.hw.macs.bisel.phis.iqs;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
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
 *
 * Servlet connects client (presumably a GUI) to the SOLR API that wraps the
 * SOLR repository for image channels
 *
 * @author kcm
 */
public class GetChannels extends HttpServlet {

    private static final String url = "http://beta.phenoimageshare.org/data/rest/getChannels?"; // stem of every SOLR query
    private static final Logger logger = Logger.getLogger(System.class.getName());

    /**
     * Enables discovery of channel information for multiple channels.
     *
     * Handles requests from the PhIS UI by simply forwarding them to the SOLR
     * API and then returning the result directly to the UI. Provides very basic
     * error handling (only deals with unknown parameters).
     *
     *
     * NOTE: this communicates with SOLR API not the SOLR core directly
     *
     * Parameters expected:
     * <ol>
     * <li>imageId = image ID, e.g., komp2_112003</li>
     * <li>start = pagination, the first result that should be returned</li>
     * <li>num = pagination, the total number of results that should be
     * returned</li>
     * </ol>
     *
     * Future versions will:
     * <ol>
     * <li>send queries to the SIS, and then integrate the results with those
     * from SOLR</li>
     * <li>likely to include sorting the results</li>
     * <li>include a wider range of query parameters</li>
     * <li>provide access to the "OLS" functionality from SOLR</li>
     * </ol>
     *
     *
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
            if (param.equalsIgnoreCase("imageId")) { // ID of channel
                if (!first) { // at the moment it will always be the first (and only) param
                    queryURL += "&";
                }

                queryURL += "imageId=" + params.get("imageId")[0]; // extend stem with parameter
                first = false; // next time you need a separator

            // pagination    
            } else if (param.equalsIgnoreCase("start")) { // number of initial result
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "start=" + params.get("start")[0];
                first = false;
            } else if (param.equalsIgnoreCase("num")) { // number of results to return
                if (!first) {
                    queryURL += "&";
                }
                queryURL += "resultNo=" + params.get("num")[0];

            // error handling    
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
        return "Simple service that wraps the SOLR API to enable searching of channel information";
    }// </editor-fold>

}

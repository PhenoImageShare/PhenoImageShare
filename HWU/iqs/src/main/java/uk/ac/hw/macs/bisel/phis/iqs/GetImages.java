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

package uk.ac.hw.macs.bisel.phis.iqs;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet connects client (presumably a GUI) to the SOLR API that wraps the
 * SOLR repository for images
 *
 * @author kcm
 */
public class GetImages extends HttpServlet {

    private static final Logger logger = Logger.getLogger(System.class.getName());

    /**
     * Enables discovery of images that meet the user's requirements. Handles
     * requests from the PhIS UI by simply forwarding them to the SOLR API and
     * then returning the result directly to the UI. Provides very basic error
     * handling (only deals with unknown parameters).
     *
     * Queries the SOLR API, not the SOLR core. Use the pagination facilities
     * offered by the SOLR API
     *
     * Parameters expected:
     * <ol>
     * <li>ptId = phenotype ID, i.e., a MP ID</li>
     * <li>aId = EMAPA or MA ID</li>
     * <li>gId = MGI gene ID, e.g., MGI:12344567</li>
     * <li>start = pagination, the first result that should be returned>/li>
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
        
        logger.log(Level.INFO, "[GUI QUERY] {0}", request.getRequestURI()+"?"+request.getQueryString());        

        // check to find version and forward
        Map<String, String[]> params = request.getParameterMap(); // get map of parameters and their values
        String[] versions = params.get("version");
        
        if(versions == null || versions[0] == null) {
            // default is v003
            request.getRequestDispatcher("/v101GIs").forward(request, response);
        } else if(versions[0].equals("101")) {
            request.getRequestDispatcher("/v101GIs").forward(request, response);
        } else if(versions[0].equals("102")) {
            request.getRequestDispatcher("/v102GIs").forward(request, response);
        } else {                
            // otherwise forward to default
            request.getRequestDispatcher("/v101GIs").forward(request, response);
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
        return "Simple service that wraps the SOLR API to enable searching of image information";
    }// </editor-fold>

}

package uk.ac.hw.macs.bisel.phis.iqs;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet connects client (presumably a GUI) to the SOLR API that wraps the
 * SOLR repository for ROIs
 *
 * @author kcm
 */
public class GetROI extends HttpServlet {

    private static final String url = "http://beta.phenoimageshare.org/data/v0.0.3/rest/getRoi?"; // stem of every SOLR query
    private static final Logger logger = Logger.getLogger(System.class.getName());

    /**
     * Enables discovery of ROI information for a single ROI. Handles requests
     * from the PhIS UI by simply forwarding them to the SOLR API and then
     * returning the result directly to the UI. Provides very basic error
     * handling (only deals with unknown parameters).
     *
     * Parameters expected:
     * <ol>
     * <li>id = ROI ID, e.g., komp2_roi_112003_0</li>
     * </ol>
     *
     * Should be no need for pagination as the query returns just a single ROI
     * at a time
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

        logger.info(request.getQueryString());

        // check to find version and forward
        Map<String, String[]> params = request.getParameterMap(); // get map of parameters and their values
        String[] versions = params.get("version");

        if (versions == null) {
            // default is v003
            request.getRequestDispatcher("/v003GR").forward(request, response);
        } else if (versions[0].equals("003")) {
            request.getRequestDispatcher("/v003GR").forward(request, response);
        } else if (versions[0].equals("004")) {
            request.getRequestDispatcher("/v004GR").forward(request, response);
        } else if (versions[0].equals("005")) {
            request.getRequestDispatcher("/v005GR").forward(request, response);

        } else {
            // otherwise forward to default
            request.getRequestDispatcher("/v003GR").forward(request, response);
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
        return "Simple service that wraps the SOLR API to enable searching of ROI information";
    }// </editor-fold>

}

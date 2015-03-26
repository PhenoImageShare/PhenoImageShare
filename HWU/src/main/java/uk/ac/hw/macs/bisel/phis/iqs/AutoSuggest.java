/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * Facilitates auto complete services from the SOLR API
 *
 * @author kcm
 */
public class AutoSuggest extends HttpServlet {

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

        logger.log(Level.SEVERE, "[QUERY] {0}", request.getQueryString());

        // check to find version and forward
        Map<String, String[]> params = request.getParameterMap(); // get map of parameters and their values
        String[] versions = params.get("version");

        if (versions == null) {
            // default is v003
            request.getRequestDispatcher("/v003AS").forward(request, response);
        } else if (versions[0].equals("003")) {
            request.getRequestDispatcher("/v003AS").forward(request, response);
        } else if (versions[0].equals("004")) {
            request.getRequestDispatcher("/v004AS").forward(request, response);
        } else if (versions[0].equals("005")) {
            request.getRequestDispatcher("/v005AS").forward(request, response);            
        } else {
            // otherwise forward to default
            request.getRequestDispatcher("/v003AS").forward(request, response);
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

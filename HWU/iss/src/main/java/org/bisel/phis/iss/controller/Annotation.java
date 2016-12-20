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
package org.bisel.phis.iss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bisel.phis.iss.client.EBIClient;
import org.bisel.phis.iss.client.PIGClient;
import org.bisel.phis.iss.model.Request;
import org.bisel.phis.iss.model.Request_Delete;

/**
 * Controller servlet that organises the flow of requests into the ISS. The ISS
 * is middleware that sits between the end user and the EBI's annotation
 * service. It enables annotations to be created, edited or deleted.
 *
 * Will eventually wrap the SDB, assuming it is actually needed.
 *
 * @author kcm
 */
@WebServlet(name = "Annotation", urlPatterns = {"/Annotation"})
public class Annotation extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Annotation.class.getName());
    private String message = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Validates the GUI request using
     * {@link org.bisel.phis.iss.model.Request Request} or
     * {@link org.bisel.phis.iss.model.Request_Delete Request_Delete}. Then uses
     * {@link EBIClient EbIClient} to contact the EBI.
     *
     * To build Request,
     * {@link #buildGuiRequest(javax.servlet.http.HttpServletRequest, java.lang.String) buildGuiRequest}
     * is used.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @see #buildGuiRequest(javax.servlet.http.HttpServletRequest,
     * java.lang.String)
     * @see org.bisel.phis.iss.model.Request
     * @see org.bisel.phis.iss.model.Request_Delete
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try (PrintWriter out = response.getWriter()) {

            LOG.log(Level.INFO, "[GUI QUERY] {0}", request.getRequestURI() + "?" + request.getQueryString());

            String action = request.getParameter("action");
            if (action == null || action.equalsIgnoreCase("")) {
                String JSON = "{\"status\" : \"fail\", \"message\" : \"no action specified\", \"time\" : \"" + getTime() + "\", \"action\" : \"unspecified\"}";
                out.println(JSON);

                /**
                 * ***************************************************************
                 */
            } else if (action.equalsIgnoreCase("delete")) {
                String version = request.getParameter("version");
                String phisID = request.getParameter("phisid");
                if (version == null || version.equalsIgnoreCase("")) {
                    String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"fail\", \"message\" : \"you must supply a version\", \"time\" : \"" + getTime() + "\", \"action\" : \"delete annotation\"}";
                    out.println(JSON);
                    return;
                }

                if (!(version.equals("103") || version.equals("104"))) {
                    String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"fail\", \"message\" : \"invalid version number!\", \"time\" : \"" + getTime() + "\", \"action\" : \"delete annotation\"}";
                    out.println(JSON);
                    return;
                }

                String creatorId = request.getParameter("creatorid");

                Request_Delete guiRequest = new Request_Delete(phisID, creatorId);
                if (guiRequest.validate()) {

                    // EBI
                    EBIClient ebi = new EBIClient();
                    boolean successEbi = ebi.deleteAnnotation(guiRequest, version);
                    if (successEbi && ebi.getMessage().contains("SUCCESS")) {

                        // SDB   
                        // communicate EBI result to user
                        LOG.log(Level.SEVERE, "SUCCESS!");
                        String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"success\", \"message\" : \"annotation deleted!\", \"time\" : \"" + getTime() + "\", \"action\" : \"delete annotation\"}";
                        out.println(JSON);
                        return;
                    } else {
                        LOG.log(Level.SEVERE, "EBI rejected annotation deletion:{0}", ebi.getMessage());
                        String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"fail\", \"message\" : \"" + ebi.getMessage() + "\", \"time\" : \"" + getTime() + "\", \"action\" : \"delete annotation\"}";
                        out.println(JSON);
                        return;
                    }

                } else {
                    LOG.log(Level.SEVERE, "ISS delete request did not validate", guiRequest.getMessage());
                    String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"fail\", \"message\" : \"" + guiRequest.getMessage() + "\", \"time\" : \"" + getTime() + "\", \"action\" : \"delete annotation\"}";
                    out.println(JSON);
                    return;
                }

                /**
                 * ***************************************************************
                 */
            } else if (action.equalsIgnoreCase("create")) {
                String version = request.getParameter("version");

                if (version == null || version.equalsIgnoreCase("")) {
                    String JSON = "{\"annotationId\" : \" new annotation with no ID \", \"status\" : \"fail\", \"message\" : \"you must supply a version\", \"time\" : \"" + getTime() + "\", \"action\" : \"create annotation\"}";
                    out.println(JSON);
                    return;
                }

                if (!(version.equals("103") || version.equals("104"))) {
                    String JSON = "{\"annotationId\" : \" new annotation with no ID \", \"status\" : \"fail\", \"message\" : \"invalid version number!\", \"time\" : \"" + getTime() + "\", \"action\" : \"create annotation\"}";
                    out.println(JSON);
                    return;
                }

                // 2015-08-13
                // SA wants PhIS ID to be optional for create
                // so if supplied will use it
                // if not supplied will request one from EBI
                String phisID = request.getParameter("phisid");
                if (phisID == null) {
                    phisID = new PIGClient().getID(version);
                }

                Request guiRequest = buildGuiRequest(request, phisID);
                if (guiRequest == null) {
                    String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"fail\", \"message\" : \"" + message + "\", \"time\" : \"" + getTime() + "\", \"action\" : \"create annotation\"}";
                    out.println(JSON);
                    return;
                }

                if (guiRequest.validate()) {
                    // send to EBI  
                    EBIClient ebi = new EBIClient();
                    boolean successEbi = ebi.sendAnnotation(guiRequest, version);
                    if (successEbi && ebi.getMessage().contains("SUCCESS")) {

                        // send to spatialDB
                        LOG.log(Level.SEVERE, "SUCCESS!");
                        String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"success\", \"message\" : \"annotation added!\", \"time\" : \"" + getTime() + "\", \"action\" : \"create annotation\"}";
                        out.println(JSON);
                        return;
                    } else {
                        LOG.log(Level.SEVERE, "EBI rejected new annotation: {0}", ebi.getMessage());
                        String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"fail\", \"message\" : \"[EBI] " + ebi.getMessage() + "\", \"time\" : \"" + getTime() + "\", \"action\" : \"create annotation\"}";
                        out.println(JSON);
                        return;
                    }

                } else {
                    LOG.log(Level.SEVERE, "[ISS] Annotation did not validate!  Error was: {0}", guiRequest.getMessage());
                    String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"fail\", \"message\" : \"[ISS] " + guiRequest.getMessage() + "\", \"time\" : \"" + getTime() + "\", \"action\" : \"create annotation\"}";
                    out.println(JSON);
                }

                /**
                 * ***************************************************************
                 */
            } else if (action.equalsIgnoreCase("edit")) {
                String version = request.getParameter("version");
                String phisID = request.getParameter("phisid");
                if (version == null || version.equalsIgnoreCase("")) {
                    String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"fail\", \"message\" : \"you must supply a version\", \"time\" : \"" + getTime() + "\", \"action\" : \"edit annotation\"}";
                    out.println(JSON);
                    return;
                }

                if (!(version.equals("103") || version.equals("104"))) {
                    String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"fail\", \"message\" : \"invalid version number!\", \"time\" : \"" + getTime() + "\", \"action\" : \"edit annotation\"}";
                    out.println(JSON);
                    return;
                }

                Request guiRequest = buildGuiRequest(request, phisID);
                if (guiRequest == null) {
                    String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"fail\", \"message\" : \"" + message + "\", \"time\" : \"" + getTime() + "\", \"action\" : \"edit annotation\"}";
                    out.println(JSON);
                    return;
                }

                if (guiRequest.validate()) {

                    // send to EBI  
                    EBIClient ebi = new EBIClient();
                    boolean successEbi = ebi.updateAnnotation(guiRequest, version);
                    if (successEbi && ebi.getMessage().contains("SUCCESS")) {

                        // send to spatialDB
                        // communicate EBI result to user                        
                        LOG.log(Level.SEVERE, "SUCCESS!");
                        String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"success\", \"message\" : \"annotation updated!\", \"time\" : \"" + getTime() + "\", \"action\" : \"edit annotation\"}";
                        out.println(JSON);
                        return;
                    } else {
                        LOG.log(Level.SEVERE, "EBI rejected annotation edit: {0}", ebi.getMessage());
                        String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"fail\", \"message\" : \" [EBI]" + ebi.getMessage() + "\", \"time\" : \"" + getTime() + "\", \"action\" : \"edit annotation\"}";
                        out.println(JSON);
                        return;
                    }

                } else {
                    LOG.log(Level.SEVERE, "[ISS] Annotation did not validate!  Error was: {0}", guiRequest.getMessage());
                    String JSON = "{\"annotationId\" : \"" + phisID + "\", \"status\" : \"fail\", \"message\" : \"[ISS] " + guiRequest.getMessage() + "\", \"time\" : \"" + getTime() + "\", \"action\" : \"edit annotation\"}";
                    out.println(JSON);
                }

            } else {
                LOG.log(Level.SEVERE, "ISS only supports create, edit or delete!");
                String JSON = "{\"status\" : \"fail\", \"message\" : \"[ISS] only types currently supported are create, edit & delete\", \"time\" : \"" + getTime() + "\", \"action\" : \"unknown action\"}";
                out.println(JSON);
            }
        }
    }

    /**
     * Processes the client request and build a Request object which is sent
     * returned. Only used by the create & update actions.
     *
     * @param request The client request
     * @param phisID The ID of relating to the annotation at the focus of this
     * request.
     * @return A completed Request object
     * @see org.bisel.phis.iss.model.Request
     */
    private Request buildGuiRequest(HttpServletRequest request, String phisID) {

        String imageId = request.getParameter("imageid");
        String creatorId = request.getParameter("creatorid");
        String userGroupId = request.getParameter("usergroupid");
        String channelId = request.getParameter("channelid");
        
        Boolean publish = null;
        try {
            publish = new Boolean(request.getParameter("publish"));
        } catch(RuntimeException e) {
            message = "invalid value for publish param";
            return null;
        }

        ArrayList<String> xValues = new ArrayList<>();
        ArrayList<String> yValues = new ArrayList<>();
        ArrayList<String> zValues = new ArrayList<>();

        ArrayList<String> phenoIDs = new ArrayList<>();
        ArrayList<String> phenoTs = new ArrayList<>();
        ArrayList<String> phenoDes = new ArrayList<>();

        ArrayList<String> dptIDs = new ArrayList<>();
        ArrayList<String> dptTs = new ArrayList<>();
        ArrayList<String> dptDes = new ArrayList<>();

        ArrayList<String> geIDs = new ArrayList<>();
        ArrayList<String> geTs = new ArrayList<>();
        ArrayList<String> geDes = new ArrayList<>();

        ArrayList<String> abnIDs = new ArrayList<>();
        ArrayList<String> abnTs = new ArrayList<>();
        ArrayList<String> abnDes = new ArrayList<>();

        Enumeration<String> allNames = request.getParameterNames();
        while (allNames.hasMoreElements()) {
            String name = allNames.nextElement();

            if (name.equalsIgnoreCase("x_value")) {
                xValues.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("y_value")) {
                yValues.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("z_value")) {
                zValues.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("phenotype_id") || name.equalsIgnoreCase("phenotypeid")) {
                phenoIDs.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("phenotype_term")) {
                phenoTs.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("phenotype_text")) {
                phenoDes.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("dpt_anatomyid")) {
                dptIDs.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("dpt_anatomy_term")) {
                dptTs.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("dpt_anatomy_text")) {
                dptDes.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("ge_anatomyid")) {
                geIDs.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("ge_anatomy_term")) {
                geTs.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("ge_anatomy_text")) {
                geDes.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("abn_anatomyid")) {
                abnIDs.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("abn_anatomy_term")) {
                abnTs.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("abn_anatomy_text")) {
                abnDes.addAll(Arrays.asList(request.getParameterValues(name)));
            } else if (name.equalsIgnoreCase("action") || name.equalsIgnoreCase("phisid") || name.equalsIgnoreCase("version") || name.equalsIgnoreCase("creatorid") || name.equalsIgnoreCase("imageid") || name.equals("publish")) {
            } else {
                message = "invalid parameter (" + name + ")";
                return null;
            }
        }

        Request guiRequest = new Request(phisID, creatorId, userGroupId, imageId, channelId, publish, xValues, yValues, zValues, dptIDs, dptTs, dptDes, geIDs, geTs, geDes, abnIDs, abnTs, abnDes, phenoIDs, phenoTs, phenoDes);
        return guiRequest;
    }

    /**
     * Provides the current date and time, used within JSON responses to client
     *
     * @return Date and time yyyy/mm/dd hh:mm:ss format
     */
    private String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
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

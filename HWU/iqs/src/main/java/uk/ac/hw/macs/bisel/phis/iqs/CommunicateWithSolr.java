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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the actual communication with the SOLR API
 *
 * @author kcm
 */
public class CommunicateWithSolr {

    private static final Logger logger = Logger.getLogger(System.class.getName());

    /**
     * Contacts the EBI and returns whatever JSON the EBI returns.
     *
     * @param queryURL The full URL that should be used to query SOLR
     * @return JSON output from SOLR
     */
    public String talk(String queryURL) {
        // record all queries sent to SOLR
        logger.log(Level.INFO, "[EBI QUERY] {0}", queryURL);
        String solrResult = "";
        try {
            solrResult = actualTalk(queryURL);
        } catch (IOException e) {
            logger.log(Level.WARNING, "1st attempt failed to communicate with SOLR");
            logger.log(Level.WARNING, e.getMessage());
            StackTraceElement[] stes = e.getStackTrace();
            if (stes.length == 0) {
                logger.log(Level.INFO, "no stracktrace!");
            } else {
                logger.log(Level.INFO, "starting stacktrace...");
                for (StackTraceElement ste : stes) {
                    logger.log(Level.INFO, ste.toString());
                }
                logger.log(Level.INFO, "... end of stacktrace!");
            }

            // making a second call immediately fails
            // try a 1 second delay
            try {
                Thread.sleep(1000); //1000 milliseconds is one second.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            try {
                solrResult = actualTalk(queryURL);
            } catch (IOException e2) {
                logger.log(Level.SEVERE, "2nd attempt failed to communicate with SOLR");
                solrResult = "{\"server_error\": \"" + e2.getMessage() + "\"}";
            }

        }
        return solrResult;
    }

    /**
     * Communicates directly with EBI SOLR API
     *
     * @param queryURL the query URL to be called
     * @return the JSON returned by the EBI
     * @throws IOException Any issue with connection results in exception
     */
    private String actualTalk(String queryURL) throws IOException {
        // connect to SOLR and run query
        
        /*
        if(queryURL.contains("/getImage")) return "{\"response\":{\"docs\":[{\"host_url\":\"http://www.emouseatlas.org\",\"expression_in_id_bag\":[\"EMAPA_16039\"],\"publication_name\":\"Reymond A, Marigo V, Yaylaoglu MB, Leoni A, Ucla C, Scamuffa N, Caccioppoli C, Dermitzakis ET, Lyle R, Banfi S, Eichele G, Antonarakis SE, Ballabio A 2002 Human chromosome 21 gene expression atlas in the mouse. Nature (420):582-6\",\"associated_channel\":[\"emage_channel_EMAGE_1107.1\"],\"publication_details\":\"Reymond A, Marigo V, Yaylaoglu MB, Leoni A, Ucla C, Scamuffa N, Caccioppoli C, Dermitzakis ET, Lyle R, Banfi S, Eichele G, Antonarakis SE, Ballabio A 2002 Human chromosome 21 gene expression atlas in the mouse. Nature (420):582-6\",\"thumbnail_url\":\"\\nhttp://www.emouseatlas.org/gxdb/dbPart/segment1/1107/1107_assay_singleMappingimage1.jpg\\n\",\"sample_type\":\"WILD_TYPE\",\"sample_preparation_freetext\":[\"\\nSignal Detection Method: alkaline phosphatase + NBT/BCIP\\n\",\"Fixation Reagent: 4% paraformaldehyde\",\"Specimen Type: wholemount\"],\"expressed_gf_symbol_bag\":[\"Mcm3ap\"],\"associated_roi\":[\"emage_roi_EMAGE_1107.1\"],\"image_context_url\":\"\\nhttp://www.emouseatlas.org/emagewebapp/pages/emage_entry_page.jsf?id=EMAGE:1107\\n\",\"taxon\":\"Mus musculus\",\"id\":\"emage_EMAGE_1107.1\",\"height\":472,\"image_type\":[\"EXPRESSION\"],\"image_generated_by\":[\"TIGEM\"],\"image_url\":\"\\nhttp://www.emouseatlas.org/gxdb/dbPart/segment1/1107/1107_assay_singleMappingimage1.jpg\\n\",\"stage_id\":\"TS15\",\"concat\":[\"EMAPA_16039_embryo\"],\"publication_url\":\"http://www.ncbi.nlm.nih.gov/pubmed/12466854\",\"depth\":0,\"expressed_gf_id_bag\":[\"MGI:1930089\"],\"stage\":\"TS15\",\"zygosity\":[\"WILD_TYPE\"],\"expression_in_label_bag\":[\"embryo\"],\"width\":372,\"ncbi_taxon_id\":\"NCBITaxon_862507\",\"host_name\":\"Mouse Atlas EMAGE\",\"age\":\"dpc 9.5\"}],\"numFound\":1,\"start\":0},\"facet_counts\":{\"facet_pivot\":{\"sample_type,image_type\":[{\"field\":\"sample_type\",\"count\":1,\"pivot\":[{\"field\":\"image_type\",\"count\":1,\"value\":\"EXPRESSION\"}],\"value\":\"WILD_TYPE\"}]},\"facet_queries\":{},\"facet_fields\":{\"image_generated_by\":[\"TIGEM\",1],\"stage_facet\":[],\"taxon\":[\"Mus musculus\",1],\"sample_type\":[\"WILD_TYPE\",1],\"host_name\":[\"Mouse Atlas EMAGE\",1],\"imaging_method_label\":[],\"image_type\":[\"EXPRESSION\",1]},\"facet_dates\":{},\"facet_ranges\":{}}}";
        if(queryURL.contains("/getRoi")) return "{\"response\":{\"docs\":[{\"expression_concat\":[\"EMAPA_16039 embryo detected\"],\"associated_channel\":[\"emage_channel_EMAGE_1107.1\"],\"associated_image\":\"emage_EMAGE_1107.1\",\"expressed_in_anatomy_id\":[\"EMAPA_16039\"],\"expression_value\":[\"detected\"],\"y_coordinates\":[0,100],\"id\":\"emage_roi_EMAGE_1107.1\",\"expressed_in_anatomy_term\":[\"embryo\"],\"x_coordinates\":[0,100]}],\"numFound\":1,\"start\":0},\"responseHeader\":{\"QTime\":0,\"params\":{\"q\":\"*:*\",\"rows\":\"10000\",\"wt\":\"json\"},\"status\":0}}";
        if(queryURL.contains("/getChannel")) return "{\"response\":{\"docs\":[{\"associated_roi\":[\"emage_roi_EMAGE_1107.1\"],\"zygosity\":\"WILD_TYPE\",\"gene_symbol\":\"Mcm3ap\",\"associated_image\":\"emage_EMAGE_1107.1\",\"end_pos\":0,\"id\":\"emage_channel_EMAGE_1107.1\",\"gene_id\":\"MGI:1930089\",\"start_pos\":0}],\"numFound\":1,\"start\":0},\"responseHeader\":{\"QTime\":0,\"params\":{\"q\":\"*:*\",\"rows\":\"10\",\"wt\":\"json\"},\"status\":0}}";
        */
        
        
        URL url = new URL(queryURL);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        // read JSON result
        String inputLine;
        if ((inputLine = in.readLine()) != null) { // should only be 1 line of result
            // can currently return the API result unchanged
            return inputLine;
        }
        in.close();        
                
        return "";                
    }

}

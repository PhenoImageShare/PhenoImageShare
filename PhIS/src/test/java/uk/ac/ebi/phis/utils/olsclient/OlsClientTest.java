package uk.ac.ebi.phis.utils.olsclient;

import org.junit.Test;
import uk.ac.ebi.phis.exception.PhenoImageShareException;

import static org.junit.Assert.assertTrue;

/**
 * Created by ilinca on 12/12/2016.
 */
public class OlsClientTest {

    OlsClient olsClient = new OlsClient();
    String termId = "EFO:0007119";


    @Test
    public void testGetTerm(){

        Term ontologyTerm = null;
        try {
            ontologyTerm = olsClient.getTerm("efo",termId);
        } catch (PhenoImageShareException e) {
            e.printStackTrace();
        }
        assertTrue (ontologyTerm != null);
        assertTrue (ontologyTerm.getLabel().equals("Sasang constitutional medicine type"));
        assertTrue (ontologyTerm.getShort_form().equalsIgnoreCase(termId.replace(":", "_")));

    }

}

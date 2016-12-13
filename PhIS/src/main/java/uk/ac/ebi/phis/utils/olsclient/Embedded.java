package uk.ac.ebi.phis.utils.olsclient;


import java.util.ArrayList;

/**
 * Created by ilinca on 12/12/2016.
 */
public class Embedded {

    ArrayList<Term> terms; // Rest template needs a concrete implementation. Don't change to List.

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }
}

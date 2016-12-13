package uk.ac.ebi.phis.utils.olsclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by ilinca on 12/12/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Term {


    private String short_form;
    private String label;
    private ArrayList<String> synonyms;
    private ArrayList<String> description;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(ArrayList<String> synonyms) {
        this.synonyms = synonyms;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    public String getShort_form() {
        return short_form;
    }

    public void setShort_form(String short_form) {
        this.short_form = short_form;
    }

    @Override
    public String toString() {
        return "Term{" +
                "label='" + label + '\'' +
                ", synonyms=" + synonyms +
                ", description='" + description + '\'' +
                '}';
    }
}

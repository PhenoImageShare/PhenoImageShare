package uk.ac.ebi.phis.xmlDump;

import uk.ac.ebi.phis.jaxb.*;

import static uk.ac.ebi.phis.xmlDump.SangerXmlGenerator.getOntologyTerm;

/**
 * Created by ilinca on 13/09/2016.
 */
public class BasicXmlGenerator {

    public static Link getLink(String url, String title, String description){

        Link link = new Link();
        if (description != null ) {link.setDetails(description);}
        if (url != null){ link.setUrl(url); }
        if (title != null) { link.setDisplayName(title);}

        return link;
    }


    /**
     *
     * @param id
     * @param label
     * @param freetext
     * @param annMode
     * @return Annotation object matching the description in XSD
     */
    public static Annotation getAnnotation(String id, String label, String freetext, AnnotationMode annMode){

        Annotation p = new Annotation();

        if (label != null && !label.isEmpty() && !id.isEmpty()){
            p.setOntologyTerm(getOntologyTerm(label, id));
        }
        if (freetext != null && !freetext.isEmpty()){
            p.setAnnotationFreetext(freetext);
        }
        if (annMode != null) {p.setAnnotationMode(annMode);}
        return p;

    }

    public static PercentArray getCoordinatesWholeImage(){

        PercentArray xCoord = new PercentArray();
        xCoord.getEl().add(new Float(0));
        xCoord.getEl().add(new Float(100));

        return xCoord;

    }

    public static StringArray getStringArray(String str){

        StringArray array = new StringArray();
        array.getEl().add(str);
        return array;

    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.08 at 05:24:47 PM BST 
//


package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AnnotationMode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AnnotationMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="manual"/>
 *     &lt;enumeration value="automated"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AnnotationMode")
@XmlEnum
public enum AnnotationMode {

    @XmlEnumValue("manual")
    MANUAL("manual"),
    @XmlEnumValue("automated")
    AUTOMATED("automated");
    private final String value;

    AnnotationMode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AnnotationMode fromValue(String v) {
        for (AnnotationMode c: AnnotationMode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

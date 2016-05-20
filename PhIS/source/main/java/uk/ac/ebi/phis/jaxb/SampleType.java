//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.19 at 05:22:21 PM BST 
//


package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SampleType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SampleType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="mutant"/>
 *     &lt;enumeration value="wild type"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SampleType")
@XmlEnum
public enum SampleType {

    @XmlEnumValue("mutant")
    MUTANT("mutant"),
    @XmlEnumValue("wild type")
    WILD_TYPE("wild type");
    private final String value;

    SampleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SampleType fromValue(String v) {
        for (SampleType c: SampleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

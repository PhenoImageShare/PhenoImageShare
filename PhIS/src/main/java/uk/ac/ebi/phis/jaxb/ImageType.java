
package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ImageType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ImageType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="phenotype/anatomy"/>
 *     &lt;enumeration value="expression"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ImageType", namespace = "http://www.example.org/phisSchema")
@XmlEnum
public enum ImageType {

    @XmlEnumValue("phenotype/anatomy")
    PHENOTYPE_ANATOMY("phenotype/anatomy"),
    @XmlEnumValue("expression")
    EXPRESSION("expression");
    private final String value;

    ImageType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ImageType fromValue(String v) {
        for (ImageType c: ImageType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

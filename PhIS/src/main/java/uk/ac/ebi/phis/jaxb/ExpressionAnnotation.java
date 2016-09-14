
package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExpressionAnnotation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExpressionAnnotation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="ontology_term" type="{http://www.example.org/phisSchema}OntologyTerm" minOccurs="0"/>
 *         &lt;element name="annotation_freetext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="annotation_mode" type="{http://www.example.org/phisSchema}AnnotationMode" minOccurs="0"/>
 *         &lt;element name="expression_value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExpressionAnnotation", namespace = "http://www.example.org/phisSchema", propOrder = {

})
public class ExpressionAnnotation {

    @XmlElement(name = "ontology_term")
    protected OntologyTerm ontologyTerm;
    @XmlElement(name = "annotation_freetext")
    protected String annotationFreetext;
    @XmlElement(name = "annotation_mode")
    @XmlSchemaType(name = "string")
    protected AnnotationMode annotationMode;
    @XmlElement(name = "expression_value")
    protected String expressionValue;

    /**
     * Gets the value of the ontologyTerm property.
     * 
     * @return
     *     possible object is
     *     {@link OntologyTerm }
     *     
     */
    public OntologyTerm getOntologyTerm() {
        return ontologyTerm;
    }

    /**
     * Sets the value of the ontologyTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link OntologyTerm }
     *     
     */
    public void setOntologyTerm(OntologyTerm value) {
        this.ontologyTerm = value;
    }

    /**
     * Gets the value of the annotationFreetext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnnotationFreetext() {
        return annotationFreetext;
    }

    /**
     * Sets the value of the annotationFreetext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnnotationFreetext(String value) {
        this.annotationFreetext = value;
    }

    /**
     * Gets the value of the annotationMode property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotationMode }
     *     
     */
    public AnnotationMode getAnnotationMode() {
        return annotationMode;
    }

    /**
     * Sets the value of the annotationMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotationMode }
     *     
     */
    public void setAnnotationMode(AnnotationMode value) {
        this.annotationMode = value;
    }

    /**
     * Gets the value of the expressionValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpressionValue() {
        return expressionValue;
    }

    /**
     * Sets the value of the expressionValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpressionValue(String value) {
        this.expressionValue = value;
    }

}

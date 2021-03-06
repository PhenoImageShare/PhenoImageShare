
package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Channel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Channel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="visualisation_method" type="{http://www.example.org/phisSchema}AnnotationArray" minOccurs="0"/>
 *         &lt;element name="associated_image" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="associated_roi" type="{http://www.example.org/phisSchema}StringArray" minOccurs="0"/>
 *         &lt;element name="depicts_expression_of" type="{http://www.example.org/phisSchema}GenotypeComponent" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Channel", namespace = "http://www.example.org/phisSchema", propOrder = {

})
public class Channel {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(name = "visualisation_method")
    protected AnnotationArray visualisationMethod;
    @XmlElement(name = "associated_image", required = true)
    protected String associatedImage;
    @XmlElement(name = "associated_roi")
    protected StringArray associatedRoi;
    @XmlElement(name = "depicts_expression_of")
    protected GenotypeComponent depictsExpressionOf;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the visualisationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotationArray }
     *     
     */
    public AnnotationArray getVisualisationMethod() {
        return visualisationMethod;
    }

    /**
     * Sets the value of the visualisationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotationArray }
     *     
     */
    public void setVisualisationMethod(AnnotationArray value) {
        this.visualisationMethod = value;
    }

    /**
     * Gets the value of the associatedImage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociatedImage() {
        return associatedImage;
    }

    /**
     * Sets the value of the associatedImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociatedImage(String value) {
        this.associatedImage = value;
    }

    /**
     * Gets the value of the associatedRoi property.
     * 
     * @return
     *     possible object is
     *     {@link StringArray }
     *     
     */
    public StringArray getAssociatedRoi() {
        return associatedRoi;
    }

    /**
     * Sets the value of the associatedRoi property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringArray }
     *     
     */
    public void setAssociatedRoi(StringArray value) {
        this.associatedRoi = value;
    }

    /**
     * Gets the value of the depictsExpressionOf property.
     * 
     * @return
     *     possible object is
     *     {@link GenotypeComponent }
     *     
     */
    public GenotypeComponent getDepictsExpressionOf() {
        return depictsExpressionOf;
    }

    /**
     * Sets the value of the depictsExpressionOf property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenotypeComponent }
     *     
     */
    public void setDepictsExpressionOf(GenotypeComponent value) {
        this.depictsExpressionOf = value;
    }

}

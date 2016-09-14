
package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Group complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Group">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="experiment_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="colony_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="project_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sb_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="other" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Group", namespace = "http://www.example.org/phisSchema", propOrder = {

})
public class Group {

    @XmlElement(name = "experiment_id")
    protected String experimentId;
    @XmlElement(name = "colony_id")
    protected String colonyId;
    @XmlElement(name = "project_id")
    protected String projectId;
    @XmlElement(name = "sb_id")
    protected String sbId;
    protected String other;

    /**
     * Gets the value of the experimentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExperimentId() {
        return experimentId;
    }

    /**
     * Sets the value of the experimentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExperimentId(String value) {
        this.experimentId = value;
    }

    /**
     * Gets the value of the colonyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColonyId() {
        return colonyId;
    }

    /**
     * Sets the value of the colonyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColonyId(String value) {
        this.colonyId = value;
    }

    /**
     * Gets the value of the projectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Sets the value of the projectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectId(String value) {
        this.projectId = value;
    }

    /**
     * Gets the value of the sbId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSbId() {
        return sbId;
    }

    /**
     * Sets the value of the sbId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSbId(String value) {
        this.sbId = value;
    }

    /**
     * Gets the value of the other property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOther() {
        return other;
    }

    /**
     * Sets the value of the other property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOther(String value) {
        this.other = value;
    }

}

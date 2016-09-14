
package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 		 	in pixels
 * 		  
 * 
 * <p>Java class for Dimensions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Dimensions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="image_depth" type="{http://www.example.org/phisSchema}PositiveInt" minOccurs="0"/>
 *         &lt;element name="image_height" type="{http://www.example.org/phisSchema}PositiveInt"/>
 *         &lt;element name="image_width" type="{http://www.example.org/phisSchema}PositiveInt"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dimensions", namespace = "http://www.example.org/phisSchema", propOrder = {

})
public class Dimensions {

    @XmlElement(name = "image_depth")
    protected Integer imageDepth;
    @XmlElement(name = "image_height")
    protected int imageHeight;
    @XmlElement(name = "image_width")
    protected int imageWidth;

    /**
     * Gets the value of the imageDepth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getImageDepth() {
        return imageDepth;
    }

    /**
     * Sets the value of the imageDepth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setImageDepth(Integer value) {
        this.imageDepth = value;
    }

    /**
     * Gets the value of the imageHeight property.
     * 
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * Sets the value of the imageHeight property.
     * 
     */
    public void setImageHeight(int value) {
        this.imageHeight = value;
    }

    /**
     * Gets the value of the imageWidth property.
     * 
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * Sets the value of the imageWidth property.
     * 
     */
    public void setImageWidth(int value) {
        this.imageWidth = value;
    }

}

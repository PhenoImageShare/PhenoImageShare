
package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ImageDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ImageDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="image_url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="original_image_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="thumbnail_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="image_context_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="image_dimensions" type="{http://www.example.org/phisSchema}Dimensions" minOccurs="0"/>
 *         &lt;element name="machine" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="image_date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="image_generated_by" type="{http://www.example.org/phisSchema}Link" minOccurs="0"/>
 *         &lt;element name="image_processed_by" type="{http://www.example.org/phisSchema}LinkArray" minOccurs="0"/>
 *         &lt;element name="organism_generated_by" type="{http://www.example.org/phisSchema}Link" minOccurs="0"/>
 *         &lt;element name="host" type="{http://www.example.org/phisSchema}Link"/>
 *         &lt;element name="imaging_method" type="{http://www.example.org/phisSchema}AnnotationArray" minOccurs="0"/>
 *         &lt;element name="sample_preparation" type="{http://www.example.org/phisSchema}AnnotationArray" minOccurs="0"/>
 *         &lt;element name="image_type" type="{http://www.example.org/phisSchema}ImageTypeArray"/>
 *         &lt;element name="sample_type" type="{http://www.example.org/phisSchema}SampleType"/>
 *         &lt;element name="licence" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="magnification_level" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publication" type="{http://www.example.org/phisSchema}LinkArray" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImageDescription", namespace = "http://www.example.org/phisSchema", propOrder = {

})
public class ImageDescription {

    @XmlElement(name = "image_url", required = true)
    protected String imageUrl;
    @XmlElement(name = "original_image_url")
    protected String originalImageUrl;
    @XmlElement(name = "thumbnail_url")
    protected String thumbnailUrl;
    @XmlElement(name = "image_context_url")
    protected String imageContextUrl;
    @XmlElement(name = "image_dimensions")
    protected Dimensions imageDimensions;
    protected String machine;
    @XmlElement(name = "image_date")
    protected String imageDate;
    @XmlElement(name = "image_generated_by")
    protected Link imageGeneratedBy;
    @XmlElement(name = "image_processed_by")
    protected LinkArray imageProcessedBy;
    @XmlElement(name = "organism_generated_by")
    protected Link organismGeneratedBy;
    @XmlElement(required = true)
    protected Link host;
    @XmlElement(name = "imaging_method")
    protected AnnotationArray imagingMethod;
    @XmlElement(name = "sample_preparation")
    protected AnnotationArray samplePreparation;
    @XmlElement(name = "image_type", required = true)
    protected ImageTypeArray imageType;
    @XmlElement(name = "sample_type", required = true)
    @XmlSchemaType(name = "string")
    protected SampleType sampleType;
    protected String licence;
    @XmlElement(name = "magnification_level")
    protected String magnificationLevel;
    protected LinkArray publication;

    /**
     * Gets the value of the imageUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the value of the imageUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageUrl(String value) {
        this.imageUrl = value;
    }

    /**
     * Gets the value of the originalImageUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalImageUrl() {
        return originalImageUrl;
    }

    /**
     * Sets the value of the originalImageUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalImageUrl(String value) {
        this.originalImageUrl = value;
    }

    /**
     * Gets the value of the thumbnailUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * Sets the value of the thumbnailUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThumbnailUrl(String value) {
        this.thumbnailUrl = value;
    }

    /**
     * Gets the value of the imageContextUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageContextUrl() {
        return imageContextUrl;
    }

    /**
     * Sets the value of the imageContextUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageContextUrl(String value) {
        this.imageContextUrl = value;
    }

    /**
     * Gets the value of the imageDimensions property.
     * 
     * @return
     *     possible object is
     *     {@link Dimensions }
     *     
     */
    public Dimensions getImageDimensions() {
        return imageDimensions;
    }

    /**
     * Sets the value of the imageDimensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dimensions }
     *     
     */
    public void setImageDimensions(Dimensions value) {
        this.imageDimensions = value;
    }

    /**
     * Gets the value of the machine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMachine() {
        return machine;
    }

    /**
     * Sets the value of the machine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMachine(String value) {
        this.machine = value;
    }

    /**
     * Gets the value of the imageDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageDate() {
        return imageDate;
    }

    /**
     * Sets the value of the imageDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageDate(String value) {
        this.imageDate = value;
    }

    /**
     * Gets the value of the imageGeneratedBy property.
     * 
     * @return
     *     possible object is
     *     {@link Link }
     *     
     */
    public Link getImageGeneratedBy() {
        return imageGeneratedBy;
    }

    /**
     * Sets the value of the imageGeneratedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Link }
     *     
     */
    public void setImageGeneratedBy(Link value) {
        this.imageGeneratedBy = value;
    }

    /**
     * Gets the value of the imageProcessedBy property.
     * 
     * @return
     *     possible object is
     *     {@link LinkArray }
     *     
     */
    public LinkArray getImageProcessedBy() {
        return imageProcessedBy;
    }

    /**
     * Sets the value of the imageProcessedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinkArray }
     *     
     */
    public void setImageProcessedBy(LinkArray value) {
        this.imageProcessedBy = value;
    }

    /**
     * Gets the value of the organismGeneratedBy property.
     * 
     * @return
     *     possible object is
     *     {@link Link }
     *     
     */
    public Link getOrganismGeneratedBy() {
        return organismGeneratedBy;
    }

    /**
     * Sets the value of the organismGeneratedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Link }
     *     
     */
    public void setOrganismGeneratedBy(Link value) {
        this.organismGeneratedBy = value;
    }

    /**
     * Gets the value of the host property.
     * 
     * @return
     *     possible object is
     *     {@link Link }
     *     
     */
    public Link getHost() {
        return host;
    }

    /**
     * Sets the value of the host property.
     * 
     * @param value
     *     allowed object is
     *     {@link Link }
     *     
     */
    public void setHost(Link value) {
        this.host = value;
    }

    /**
     * Gets the value of the imagingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotationArray }
     *     
     */
    public AnnotationArray getImagingMethod() {
        return imagingMethod;
    }

    /**
     * Sets the value of the imagingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotationArray }
     *     
     */
    public void setImagingMethod(AnnotationArray value) {
        this.imagingMethod = value;
    }

    /**
     * Gets the value of the samplePreparation property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotationArray }
     *     
     */
    public AnnotationArray getSamplePreparation() {
        return samplePreparation;
    }

    /**
     * Sets the value of the samplePreparation property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotationArray }
     *     
     */
    public void setSamplePreparation(AnnotationArray value) {
        this.samplePreparation = value;
    }

    /**
     * Gets the value of the imageType property.
     * 
     * @return
     *     possible object is
     *     {@link ImageTypeArray }
     *     
     */
    public ImageTypeArray getImageType() {
        return imageType;
    }

    /**
     * Sets the value of the imageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageTypeArray }
     *     
     */
    public void setImageType(ImageTypeArray value) {
        this.imageType = value;
    }

    /**
     * Gets the value of the sampleType property.
     * 
     * @return
     *     possible object is
     *     {@link SampleType }
     *     
     */
    public SampleType getSampleType() {
        return sampleType;
    }

    /**
     * Sets the value of the sampleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link SampleType }
     *     
     */
    public void setSampleType(SampleType value) {
        this.sampleType = value;
    }

    /**
     * Gets the value of the licence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicence() {
        return licence;
    }

    /**
     * Sets the value of the licence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicence(String value) {
        this.licence = value;
    }

    /**
     * Gets the value of the magnificationLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMagnificationLevel() {
        return magnificationLevel;
    }

    /**
     * Sets the value of the magnificationLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMagnificationLevel(String value) {
        this.magnificationLevel = value;
    }

    /**
     * Gets the value of the publication property.
     * 
     * @return
     *     possible object is
     *     {@link LinkArray }
     *     
     */
    public LinkArray getPublication() {
        return publication;
    }

    /**
     * Sets the value of the publication property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinkArray }
     *     
     */
    public void setPublication(LinkArray value) {
        this.publication = value;
    }

}

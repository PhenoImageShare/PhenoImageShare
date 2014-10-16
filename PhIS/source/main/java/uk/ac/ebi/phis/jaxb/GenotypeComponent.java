//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.16 at 10:03:23 AM BST 
//


package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GenotypeComponent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenotypeComponent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="gene_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gene_symbol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="genetic_feature_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="genetic_feature_symbol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="genetic_feature_ensembl_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="genomic_location" type="{http://www.example.org/phisSchema}GenomicLocation" minOccurs="0"/>
 *         &lt;element name="zygosity" type="{http://www.example.org/phisSchema}Zygosity" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenotypeComponent", propOrder = {

})
public class GenotypeComponent {

    @XmlElement(name = "gene_id")
    protected String geneId;
    @XmlElement(name = "gene_symbol")
    protected String geneSymbol;
    @XmlElement(name = "genetic_feature_id")
    protected String geneticFeatureId;
    @XmlElement(name = "genetic_feature_symbol")
    protected String geneticFeatureSymbol;
    @XmlElement(name = "genetic_feature_ensembl_id")
    protected String geneticFeatureEnsemblId;
    @XmlElement(name = "genomic_location")
    protected GenomicLocation genomicLocation;
    protected Zygosity zygosity;

    /**
     * Gets the value of the geneId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneId() {
        return geneId;
    }

    /**
     * Sets the value of the geneId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneId(String value) {
        this.geneId = value;
    }

    /**
     * Gets the value of the geneSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneSymbol() {
        return geneSymbol;
    }

    /**
     * Sets the value of the geneSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneSymbol(String value) {
        this.geneSymbol = value;
    }

    /**
     * Gets the value of the geneticFeatureId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneticFeatureId() {
        return geneticFeatureId;
    }

    /**
     * Sets the value of the geneticFeatureId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneticFeatureId(String value) {
        this.geneticFeatureId = value;
    }

    /**
     * Gets the value of the geneticFeatureSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneticFeatureSymbol() {
        return geneticFeatureSymbol;
    }

    /**
     * Sets the value of the geneticFeatureSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneticFeatureSymbol(String value) {
        this.geneticFeatureSymbol = value;
    }

    /**
     * Gets the value of the geneticFeatureEnsemblId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneticFeatureEnsemblId() {
        return geneticFeatureEnsemblId;
    }

    /**
     * Sets the value of the geneticFeatureEnsemblId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneticFeatureEnsemblId(String value) {
        this.geneticFeatureEnsemblId = value;
    }

    /**
     * Gets the value of the genomicLocation property.
     * 
     * @return
     *     possible object is
     *     {@link GenomicLocation }
     *     
     */
    public GenomicLocation getGenomicLocation() {
        return genomicLocation;
    }

    /**
     * Sets the value of the genomicLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenomicLocation }
     *     
     */
    public void setGenomicLocation(GenomicLocation value) {
        this.genomicLocation = value;
    }

    /**
     * Gets the value of the zygosity property.
     * 
     * @return
     *     possible object is
     *     {@link Zygosity }
     *     
     */
    public Zygosity getZygosity() {
        return zygosity;
    }

    /**
     * Sets the value of the zygosity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zygosity }
     *     
     */
    public void setZygosity(Zygosity value) {
        this.zygosity = value;
    }

}

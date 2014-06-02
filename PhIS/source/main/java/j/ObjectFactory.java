//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.02 at 05:35:29 PM BST 
//


package j;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the j package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Doc_QNAME = new QName("http://www.example.org/phisSchema", "Doc");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: j
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Doc }
     * 
     */
    public Doc createDoc() {
        return new Doc();
    }

    /**
     * Create an instance of {@link Roi }
     * 
     */
    public Roi createRoi() {
        return new Roi();
    }

    /**
     * Create an instance of {@link GeneticTraitArray }
     * 
     */
    public GeneticTraitArray createGeneticTraitArray() {
        return new GeneticTraitArray();
    }

    /**
     * Create an instance of {@link Age }
     * 
     */
    public Age createAge() {
        return new Age();
    }

    /**
     * Create an instance of {@link Channel }
     * 
     */
    public Channel createChannel() {
        return new Channel();
    }

    /**
     * Create an instance of {@link ZygArray }
     * 
     */
    public ZygArray createZygArray() {
        return new ZygArray();
    }

    /**
     * Create an instance of {@link GenomicLocation }
     * 
     */
    public GenomicLocation createGenomicLocation() {
        return new GenomicLocation();
    }

    /**
     * Create an instance of {@link StringArray }
     * 
     */
    public StringArray createStringArray() {
        return new StringArray();
    }

    /**
     * Create an instance of {@link GeneticTrait }
     * 
     */
    public GeneticTrait createGeneticTrait() {
        return new GeneticTrait();
    }

    /**
     * Create an instance of {@link Anatomy }
     * 
     */
    public Anatomy createAnatomy() {
        return new Anatomy();
    }

    /**
     * Create an instance of {@link Phenotype }
     * 
     */
    public Phenotype createPhenotype() {
        return new Phenotype();
    }

    /**
     * Create an instance of {@link IntArray }
     * 
     */
    public IntArray createIntArray() {
        return new IntArray();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link Dimensions }
     * 
     */
    public Dimensions createDimensions() {
        return new Dimensions();
    }

    /**
     * Create an instance of {@link GenomicLocationArray }
     * 
     */
    public GenomicLocationArray createGenomicLocationArray() {
        return new GenomicLocationArray();
    }

    /**
     * Create an instance of {@link ImageDescription }
     * 
     */
    public ImageDescription createImageDescription() {
        return new ImageDescription();
    }

    /**
     * Create an instance of {@link Organism }
     * 
     */
    public Organism createOrganism() {
        return new Organism();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Doc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/phisSchema", name = "Doc")
    public JAXBElement<Doc> createDoc(Doc value) {
        return new JAXBElement<Doc>(_Doc_QNAME, Doc.class, null, value);
    }

}

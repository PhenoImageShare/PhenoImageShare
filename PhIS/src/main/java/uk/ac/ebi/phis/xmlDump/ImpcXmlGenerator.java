package uk.ac.ebi.phis.xmlDump;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import uk.ac.ebi.phis.jaxb.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static uk.ac.ebi.phis.xmlDump.SangerXmlGenerator.getOntologyTerm;

/**
 * Created by ilinca on 25/08/2016.
 */
public class ImpcXmlGenerator extends BasicXmlGenerator{

    private HttpSolrServer solr;
    private final static String ulr = "http://ves-ebi-d0.ebi.ac.uk:8090/mi/impc/dev/solr/impc_images/";

    public void exportImages() throws IOException, SolrServerException, NoSuchAlgorithmException, JAXBException {

        solr = new HttpSolrServer(ulr);
        List<Image> images = new ArrayList<>();
        List<Roi> rois = new ArrayList<>();
        List<Channel> channels = new ArrayList<>();
        Doc doc = new Doc();

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.addFilterQuery("-" + ImpcImageDto.PROCEDURE_NAME + ":\"Sleep Wake\"");
        solrQuery.addFilterQuery("-" + ImpcImageDto.PROCEDURE_NAME + ":\"Auditory Brain Stem Response\"");
        solrQuery.addFilterQuery("-" + ImpcImageDto.PROCEDURE_NAME + ":Electroretinography*");
        solrQuery.addFilterQuery("-" + ImpcImageDto.PROCEDURE_NAME + ":Echo");
        solrQuery.setRows(100000);
        List<ImpcImageDto> impcImages = solr.query(solrQuery).getBeans(ImpcImageDto.class);

        System.out.println("SOLR QUERY " + solr.getBaseURL() + "/select?" + solrQuery);
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/Users/ilinca/IdeaProjects/PhenoImageShare/PhIS/src/main/resources/impcExport.xml")));
        JAXBContext jaxbContext = JAXBContext.newInstance(Doc.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        int count = 0;

        while(impcImages.size() > 0) {
            for (ImpcImageDto dto : impcImages) {

                Image img = addImageSpecificDecorations(dto);
                img = addImageSpecificDecorations(dto);

                Roi roi = addRoiSpecificInformation(dto);
                if (roi != null) {
                    roi.setAssociatedImage(img.getId());
                    img.setAssociatedRoi(getStringArray(roi.getId()));
                }

                Channel channel = addChannelSpecificInformation(dto);
                if (channel != null) {

                    if (roi != null) {
                        roi.setAssociatedChannel(getStringArray(channel.getId()));
                        channel.setAssociatedRoi(getStringArray(roi.getId()));
                    }

                    img.setAssociatedChannel(getStringArray(channel.getId()));
                    channel.setAssociatedImage(img.getId());
                }

                images.add(img);

                if (roi != null) {rois.add(roi);}

                if (channel != null) {channels.add(channel);}

                if (count++ % 1000 == 0) {
                    System.out.println("ADDED " + count + " images to xml. ");
                }
            }

            solrQuery.setStart(count);
            impcImages = solr.query(solrQuery).getBeans(ImpcImageDto.class);
        }
        doc.getImage().addAll(images);
        doc.getRoi().addAll(rois);
        doc.getChannel().addAll(channels);

        jaxbMarshaller.marshal(doc, writer);

    }


    private Channel addChannelSpecificInformation(ImpcImageDto dto) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Channel channel = new Channel();
        boolean notEmpty = false;
        channel.setId("channel_" + getMd5Id(dto.getFullResolutionFilePath()));

        AnnotationArray viz = getVisualization(dto.getProcedureName());
        if (viz != null){
            channel.setVisualisationMethod(viz);
            notEmpty = true;
        }

        if (dto.getProcedureStableId().contains("ELZ") || dto.getProcedureStableId().contains("ALZ")){
            channel.setDepictsExpressionOf(getGenotypeComponent(dto));
            notEmpty = true;
        }

        return notEmpty ? channel : null;

    }


    private GenotypeComponent getGenotypeComponent(ImpcImageDto dto){

        GenotypeComponent gc = new GenotypeComponent();
        gc.setMutationType(getAnnotation(null, null, "null mutation", null));
        gc.setGeneId(dto.getGeneAccession());
        gc.setGeneSymbol(dto.getGeneSymbol());
        gc.setGeneticFeatureId(dto.getAlleleAccession());
        gc.setGeneticFeatureSymbol(dto.getAlleleSymbol());
        if (dto.getZygosity() != null){
            gc.setZygosity(Zygosity.valueOf(dto.getZygosity().toUpperCase().replace("ZYGOTE", "ZYGOUS")));
        }
        return gc;

    }


    private Roi addRoiSpecificInformation(ImpcImageDto dto ) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Roi roi = new Roi();
        Boolean hasAnnotations = false;
        roi.setId("roi_" + getMd5Id(dto.getFullResolutionFilePath()));

        if ( dto.getMpTerm() != null ) {

            AnnotationArray annArray = new AnnotationArray();
            for (String id: dto.getMpTermIds()){
                annArray.getEl().add(getAnnotation(id, "", null, AnnotationMode.MANUAL));
            }
            roi.setPhenotypeAnnotations(annArray);
            hasAnnotations = true;

        }

        if ( (dto.getAnatomyId() != null && dto.getProcedureStableId().contains("ALZ") || dto.getProcedureStableId().contains("ELZ")) && dto.getAnatomyId() != null) {

            ExpressionAnnotationArray annArray = new ExpressionAnnotationArray();

            if (dto.getParameterAssociationValue() != null && dto.getParameterAssociationValue().size() < dto.getAnatomyId().size()){
                System.out.println("Less expression than anatomy annotations " + dto.getId());
            } else { // if they don't have the same length don't know how to associate the values. They're supposed to be parallel arrays.
                for (int i = 0; i < dto.getAnatomyId().size(); i++) {
                    String expressionValue = dto.getParameterAssociationValue() != null ? dto.getParameterAssociationValue().get(i) : null;
                    String term = dto.getAnatomyTerm() != null ? dto.getAnatomyTerm().get(i) : null;
                    annArray.getEl().add(getExpressionAnnotation(dto.getAnatomyId().get(i), term, null, expressionValue, AnnotationMode.MANUAL));
                }
                roi.setDepictedAnatomicalStructure(annArray);
                hasAnnotations = true;
            }
        }

        if (hasAnnotations){
            Coordinates coordinates = new Coordinates();
            coordinates.setXCoordinates(getCoordinatesWholeImage());
            coordinates.setYCoordinates(getCoordinatesWholeImage());
            roi.setCoordinates(coordinates);
        }

        return hasAnnotations ?  roi : null;

    }


    private Image addImageSpecificDecorations(ImpcImageDto dto) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Image img = new Image();
        img.setId(getMd5Id(dto.getFullResolutionFilePath()));

        Organism org = new Organism();
        org.setTaxon("Mus musculus");
        org.setNcbiTaxonId("NCBITaxon_10090");
        img.setOrganism(org);
        org.setAge(dto.getDevelopmentalStageName());
        StringArray strain = new StringArray();
        strain.getEl().add(dto.getStrainName());
        org.setBackgroundStrain(strain);
        if (!dto.getSex().equalsIgnoreCase("NO_DATA")){
            org.setSex(Sex.valueOf(dto.getSex().toUpperCase()));
        }

        img.setOrganism(org);

        ImageDescription desc = new ImageDescription();
        desc.setImageGeneratedBy(getLink(null, dto.getPhenotypingCenter(), null));
        desc.setImageUrl(dto.getJpegUrl());
        desc.setThumbnailUrl(dto.getJpegUrl().replace("render_image", "render_birds_eye_view"));
        desc.setOrganismGeneratedBy(getLink(null, dto.getProductionCenter(), null));

        AnnotationArray samplePrep = getSamplePrep(dto.getProcedureName());
        if (samplePrep != null) {desc.setSamplePreparation(samplePrep);}

        desc.setHost(getLink("http://www.mousephenotype.org/", "IMPC Mouse Phenotype", null));

        AnnotationArray imagingMethod = getImagingMethod(dto.getProcedureName());
        if (imagingMethod != null) { desc.setImagingMethod(imagingMethod);}

        ImageTypeArray imgtype = new ImageTypeArray();
        imgtype.getEl().add(getImageType(dto.getProcedureStableId()));
        desc.setImageType(imgtype);
        desc.setSampleType(getsampleType(dto));

        img.setImageDescription(desc);

        if (dto.getAlleleAccession() != null){
            Genotype genotype = new Genotype();
            GenotypeComponent gc = getGenotypeComponent(dto);
            genotype.getEl().add(gc);
            img.setMutantGenotypeTraits(genotype);
        }

        return img;

    }

    private static ImageType getImageType(String procedureStableId){
        return (procedureStableId.contains("ALZ") || procedureStableId.contains("ELZ")) ? ImageType.EXPRESSION : ImageType.PHENOTYPE_ANATOMY;
    }

    private SampleType getsampleType(ImpcImageDto dto){
        return  (dto.getGeneAccession() != null) ? SampleType.MUTANT : SampleType.WILD_TYPE;
    }

    private AnnotationArray getSamplePrep(String procedureName) {

        AnnotationArray samplePrep = new AnnotationArray();
        Annotation ann = new Annotation();

        switch (procedureName) {
            case "X-ray":
                ann.setOntologyTerm(SangerXmlGenerator.getOntologyTerm("whole mounted tissue", "FBbi_00000024"));
                Annotation ann2 = new Annotation();
                ann2.setOntologyTerm(SangerXmlGenerator.getOntologyTerm("living tissue", "FBbi_00000025"));
                samplePrep.getEl().add(ann);
                samplePrep.getEl().add(ann2);
                break;

            case "Gross Morphology Embryo E12.5":
            case "Gross Morphology Embryo E14.5-E15.5":
            case "Gross Morphology Embryo E9.5":
            case "Gross Morphology Embryo E18.5":
            case "Gross Pathology and Tissue Collection":
            case "Combined SHIRPA and Dysmorphology":
            case "Eye Morphology":
                ann.setOntologyTerm(getOntologyTerm("whole mounted tissue", "FBbi_00000024"));
                samplePrep.getEl().add(ann);
                break;
            case "Adult LacZ":
            case "Embryo LacZ":
                ann.setOntologyTerm(getOntologyTerm("whole mounted tissue", "FBbi_00000024"));
                samplePrep.getEl().add(ann);
                break;
            case "Histopathology":
                ann.setOntologyTerm(getOntologyTerm("tissue in paraffin embedment", "FBbi_00000020"));
                samplePrep.getEl().add(ann);
                break;
            default:
                System.out.println("What is the sample prep method for  " + procedureName);
                samplePrep = null;
                break;
        }

        return samplePrep;

    }


    private AnnotationArray getImagingMethod(String procedureName) {

        AnnotationArray im = new AnnotationArray();
        Annotation ann = new Annotation();
        OntologyTerm ot = new OntologyTerm();

        switch (procedureName) {
            case "X-ray":
                ann.setOntologyTerm(getOntologyTerm("X-ray illumination", "FBbi_00000342"));
                im.getEl().add(ann);
                break;
            case "Adult LacZ":
            case "Embryo LacZ":
                ann.setOntologyTerm(getOntologyTerm("bright-field microscopy", "FBbi_00000243"));
                im.getEl().add(ann);
                break;
            case "Combined SHIRPA and Dysmorphology":
            case "Eye Morphology":
            case "Gross Morphology Embryo E12.5":
            case "Gross Morphology Embryo E14.5-E15.5":
            case "Gross Morphology Embryo E9.5":
            case "Gross Pathology and Tissue Collection":
                ann.setOntologyTerm(getOntologyTerm("macroscopy", "FBbi_00000240"));
                im.getEl().add(ann);
                break;
            case "Histopathology":
                ann.setOntologyTerm(getOntologyTerm("bright-field microscopy", "FBbi_00000243"));
                im.getEl().add(ann);
                break;
            default:
                System.out.println("What is the imaging method for  " + procedureName);
                im = null;
                break;
        }

        return im;

    }


    private AnnotationArray getVisualization(String procedureName) {

        AnnotationArray viz = new AnnotationArray();
        Annotation ann = new Annotation();
        OntologyTerm ot = new OntologyTerm();

        switch (procedureName) {
            case "X-ray":
                ann.setOntologyTerm(getOntologyTerm("", ""));
                viz.getEl().add(ann);
            case "Eye Morphology":
                ann.setOntologyTerm(getOntologyTerm("", ""));
                viz.getEl().add(ann);
                break;
            case "Combined SHIRPA and Dysmorphology":
                ann.setOntologyTerm(getOntologyTerm("", ""));
                viz.getEl().add(ann);
                break;
            case "Adult LacZ":
                ann.setOntologyTerm(getOntologyTerm("visualisation of genetically encoded beta-galactosidase", "FBbi_00000077"));
                viz.getEl().add(ann);
                break;
            case "Embryo LacZ":
                ann.setOntologyTerm(getOntologyTerm("visualisation of genetically encoded beta-galactosidase", "FBbi_00000077"));
                viz.getEl().add(ann);
                break;
            case "Gross Morphology Embryo E12.5":
            case "Gross Morphology Embryo E14.5-E15.5":
            case "Gross Morphology Embryo E9.5":
            case "Gross Pathology and Tissue Collection":
                viz = null;
                break;
            case "Histopathology":
                ann.setOntologyTerm(getOntologyTerm("cresyl fast violet", "FBbi_00000035"));
                ann.setAnnotationFreetext("double staining cresol fast violet combined with luxol last blue");
                viz.getEl().add(ann);
                break;
            default:
                System.out.println("Don't have viz method for " + procedureName);
                viz = null;
                break ;
        }

        return viz;
    }

    private String getMd5Id(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String text = "This is some text";

        md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();

        return digest.toString();

    }


}

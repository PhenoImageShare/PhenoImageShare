package uk.ac.ebi.phis.xmlDump;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import uk.ac.ebi.phis.jaxb.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static uk.ac.ebi.phis.xmlDump.SangerXmlGenerator.getOntologyTerm;

/**
 * Created by ilinca on 25/08/2016.
 */
public class ImpcXmlGenerator extends BasicXmlGenerator{

    private HttpSolrServer solr;
    private final static String ulr = "http://ves-ebi-d0.ebi.ac.uk:8090/mi/impc/dev/solr/impc_images/";

    public void exportImages() throws IOException, SolrServerException, NoSuchAlgorithmException {

        //Initialize Solr Client
        solr = new HttpSolrServer(ulr);
        Map<String, Image> images = new HashMap<>();
        Set<Image> rois = new HashSet<>();
        Set<Image> channels = new HashSet<>();

        //get all DTOs
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.setFilterQueries("-" + ImpcImageDto.PROCEDURE_NAME + ":\"Sleep Wake\"");
        solrQuery.setFilterQueries("-" + ImpcImageDto.PROCEDURE_NAME + ":\"Auditory Brain Stem Response\"");
        solrQuery.setFilterQueries("-" + ImpcImageDto.PROCEDURE_NAME + ":Electroretinography*");
        solrQuery.setFilterQueries("-" + ImpcImageDto.PROCEDURE_NAME + ":Echo");
        List<ImpcImageDto> impcImages = solr.query(solrQuery).getBeans(ImpcImageDto.class);

        //TODO iterate all and convert to xml objects

        for (ImpcImageDto dto : impcImages) {
            Image img = new Image();
            if (images.containsKey(dto.getImageLink())) {
                img = images.get(dto.getImageLink());
            } else {
                img = getBasicImageDecorations();
            }
            img = addImageSpecificDecorations(dto);
        }

        //TODO write



    }


    private Image addImageSpecificDecorations(ImpcImageDto dto) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Image img = new Image();
        img.setId(getMd5Id(dto.getFullResolutionFilePath()));

        Organism org = img.getOrganism();
        OntologyTerm stage = new OntologyTerm();
        stage.setTermId(dto.getDevelopmentalStageAcc());
        stage.setTermLabel(dto.getDevelopmentalStageName());
        org.setStage(stage);
        StringArray strain = new StringArray();
        strain.getEl().add(dto.getStrainName());
        org.setBackgroundStrain(strain);
        org.setSex(Sex.valueOf(dto.getSex()));

        img.setOrganism(org);

        ImageDescription desc = img.getImageDescription();
        desc.setImageGeneratedBy(getLink(null, dto.getPhenotypingCenter(), null));
        desc.setImageUrl(dto.getJpegUrl());
        desc.setThumbnailUrl(dto.getJpegUrl().replace("render_image", "render_birds_eye_view"));
        desc.setOrganismGeneratedBy(getLink(null, dto.getProductionCenter(), null));
        desc.setSamplePreparation(getSamplePrep(dto.getProcedureName()));
        desc.setHost(getLink("http://www.mousephenotype.org/", "IMPC Mouse Phenotype", null));
        desc.setImagingMethod(getImagingMethod(dto.getProcedureName()));
        desc.setSamplePreparation(getSamplePrep(dto.getProcedureName()));

        img.setImageDescription(desc);


        Genotype genotype = new Genotype();
        GenotypeComponent gc = new GenotypeComponent();

        img.setMutantGenotypeTraits(genotype);

        return img;

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
        }

        return viz;
    }

    private String getMd5Id(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String text = "This is some text";

        md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
        byte[] digest = md.digest();
        System.out.println(digest.toString());

        return digest.toString();

    }

    // All images from IMP have a few things in common. Fill them out here.
    private Image getBasicImageDecorations() {

        Image img = new Image();

        Organism org = new Organism();
        org.setTaxon("Mus musculus");
        org.setNcbiTaxonId("NCBITaxon_10090");
        img.setOrganism(org);

        ImageDescription desc = new ImageDescription();
        Link host = new Link();
        host.setUrl("http://www.mousephenotype.org/");
        host.setDisplayName("IMPC Portal");
        desc.setHost(host);
        img.setImageDescription(desc);

        return img;

    }

}

package uk.ac.ebi.phis.xmlDump;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uk.ac.ebi.phis.jaxb.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by ilinca on 08/09/2016.
 */
public class IdrXmlGenerator extends BasicXmlGenerator {

    String listDatasetsUrl = "http://idr-demo.openmicroscopy.org/webclient/api/datasets/?id=";
    String listImagesUrl = "http://idr-demo.openmicroscopy.org/webclient/api/images/?id=";
    String thumbnailUrl = "http://idr-demo.openmicroscopy.org/webclient/render_thumbnail/size/";
    String mapUrl = "http://idr-demo.openmicroscopy.org/webclient/api/annotations/?type=map&image=";

    public void export() throws IOException, JSONException, JAXBException {


        Doc idr = new Doc();
        List<Image> images = new ArrayList();
        List<Roi> rois = new ArrayList();
        List<Channel> channels = new ArrayList();

        List<Integer> datasetIds = new ArrayList<>();
        datasetIds.add(101);
        datasetIds.add(52);
        datasetIds.add(51);

        for (Integer datasetId : datasetIds) {

            JSONArray datasets = getJsonArray(listDatasetsUrl + datasetId, "datasets");
            for (int i = 0; i < datasets.length(); i++) {

                System.out.println("IMAGES LIST " + listImagesUrl + datasets.getJSONObject(i).getInt("id"));
                JSONArray imagesArray = getJsonArray(listImagesUrl + datasets.getJSONObject(i).getInt("id"), "images");
                for (int j = 0; j < imagesArray.length(); j++) {

                    int imageId = imagesArray.getJSONObject(j).getInt("id");
                    JSONArray annotationsArray = getJsonArray(mapUrl + imageId, "annotations");
                    System.out.println(imageId + " ++ " + annotationsArray);

                    if (annotationsArray.length() > 0) {
                        Map<String, String> map = convertAnnotationValuesToMap(annotationsArray);
                        Image img = createImageFromMap(map, imageId, datasetId);

                        if (img != null) {

                            Roi roi = createRoiFromMap(map, img);
                            Channel channel = createChannelFrom(img, roi, datasetId);

                            if (roi != null) {
                                rois.add(roi);
                            }

                            if (channel != null) {
                                channels.add(channel);
                            }
                            images.add(img);
                        }
                    }
//                    if (j == 100) {
//                        break;
//                    }
                }
            }
        }

        idr.getImage().addAll(images);
        idr.getRoi().addAll(rois);
        idr.getChannel().addAll(channels);
        File file = new File("/Users/ilinca/IdeaProjects/PhenoImageShare/PhIS/src/main/resources/idrExport.xml");
        JAXBContext jaxbContext;

        jaxbContext = JAXBContext.newInstance(Doc.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(idr, file);

    }


    private Channel createChannelFrom (Image img, Roi roi, Integer datasetId){

        Channel channel = new Channel();

        if (datasetId == 101) {

            channel.setId("channel_" + img.getId());

            channel.setAssociatedImage(img.getId());
            img.setAssociatedChannel(getStringArray(channel.getId()));

            if (roi != null) {
                channel.setAssociatedRoi(getStringArray(roi.getId()));
                roi.setAssociatedChannel(getStringArray(channel.getId()));
            }

            AnnotationArray visualisationMethod = new AnnotationArray();
            visualisationMethod.getEl().add(getAnnotation("FBbi_00000041", "hematoxylin", null, null));
            visualisationMethod.getEl().add(getAnnotation("FBbi_00000037", "eosin", null, null));

            channel.setVisualisationMethod(visualisationMethod);

        } else {
            channel = null;
        }
        return channel;

    }


    private Roi createRoiFromMap ( Map<String, String> map, Image image){

        Boolean hasAnnotations = false;

        Roi roi  = new Roi();
        roi.setAssociatedImage(image.getId());

        Coordinates coordinates = new Coordinates();
        coordinates.setXCoordinates(getCoordinatesWholeImage());
        coordinates.setYCoordinates(getCoordinatesWholeImage());
        roi.setCoordinates(coordinates);

        roi.setId("roi_" + image.getId());


//        StringArray channels = new StringArray();
//        channels.getEl().add("channel_" + getI);
//        roi.setAssociatedChannel(channels);

        // phenotype terms are numbered. need to get all of them.
        // map.get("Phenotype 3 Term Name");
        // map.get("Phenotype 3 Term Accession");
        Pattern phenotypePattern = Pattern.compile("Phenotype (.+) Term Name");
        for (String key : map.keySet()) {
            Matcher matcher = phenotypePattern.matcher(key);
            if (matcher.matches()) {
                String number = matcher.group();
                AnnotationArray phenotypes = new AnnotationArray();
                phenotypes.getEl().add(getAnnotation(map.get("Phenotype " + number + " Term Accession"), map.get("Phenotype " + number + " Term Name"), null, AnnotationMode.MANUAL));
                roi.setPhenotypeAnnotations(phenotypes);
                hasAnnotations = true;
            }
        }

        if (!hasAnnotations){
            roi = null;
        } else {
            image.setAssociatedRoi(getStringArray("roi_" + image.getId()));
        }

        return roi;

    }

    private Image createImageFromMap (Map<String, String> map, Integer imageId, Integer dataseId){

        Image img = new Image();
        img.setId(imageId.toString());

        Organism org = new Organism();
        if (map.get("Sex") != null) {
            org.setSex(Sex.fromValue(map.get("Sex")));
        }
        org.setOrganismId(map.get("Individual"));
        img.setOrganism(org);

        org = addStudySpecificToOrg(org, dataseId);

        Genotype genotype = new Genotype();
        GenotypeComponent gc = new GenotypeComponent();
        gc.setGeneId(map.get("Gene Identifier"));
        gc.setGeneSymbol(map.get("Gene Symbol"));

        if (map.get("Genotype") != null && map.get("Genotype").contains("knockout")) {
            gc.setMutationType(getAnnotation(null, null, "null mutation", null));
        } else {
            System.out.println("What mutation is this   " + map.get("Genotype"));
        }

        genotype.getEl().add(gc);
        img.setMutantGenotypeTraits(genotype);

        ImageDescription description = new ImageDescription();
        description.setHost(getLink("http://idr-demo.openmicroscopy.org/", "Image Data Repository (IDR)", null));
        description.setImageUrl(thumbnailUrl + "3000/" + imageId);
        description.setThumbnailUrl(thumbnailUrl + "200/" + imageId);
        description.setSampleType(SampleType.MUTANT);
        ImageTypeArray type = new ImageTypeArray();
        type.getEl().add(ImageType.PHENOTYPE_ANATOMY);
        description.setImageType(type);

        addProjectSpecificAnnotations(dataseId, description);

        img.setImageDescription(description);


        img.setDepictedAnatomicalStructure(getAnnotation(null, null, map.get("Organism Part"), AnnotationMode.MANUAL));

        return img;
    }


    private Organism addStudySpecificToOrg(Organism org, Integer datasetId){

        if (datasetId == 101){
            org.setTaxon("Mus musculus");
        } else if (datasetId == 52 || datasetId == 51){
            org.setTaxon("Homo sapiens");
        }
        return org;

    }


    private void addProjectSpecificAnnotations(Integer datasetId,  ImageDescription description){

        // neff = 018
        if (datasetId == 108 ) { // neff

            AnnotationArray samplePreparation = new AnnotationArray();
            samplePreparation.getEl().add(getAnnotation("FBbi_00000020", "tissue in paraffin embedment", "tissue embedded in block banking", AnnotationMode.MANUAL));
            description.setSamplePreparation(samplePreparation);

            AnnotationArray imagingMethod = new AnnotationArray();
            imagingMethod.getEl().add(getAnnotation("FBbi_00000243", "bright-field microscopy", null, null));
            description.setImagingMethod(imagingMethod);

            description.setImageGeneratedBy(getLink("https://www.helmholtz-muenchen.de/", "Institute of Pathology, Helmholtz Zentrum Muenchen, Germany", null));

        } else if (datasetId == 51) { // idr0021-lawo-pericentriolarmaterial/experimentA

            description.setImageGeneratedBy(getLink("http://www.lunenfeld.ca/about-us", "The Lunenfeld-Tanenbaum Research Institute", null));

            AnnotationArray imagingMethod = new AnnotationArray();
            imagingMethod.getEl().add(getAnnotation("FBbi_00000332", "structured illumination microscopy (SIM)", "protein localization using 3D SIM", null));
            description.setImagingMethod(imagingMethod);

            AnnotationArray samplePrep = new AnnotationArray();
            samplePrep.getEl().add(getAnnotation(null, null, "HeLa and RPE-1 cells were grown in DulbeccoÕs modified EagleÕs medium supplemented with 10% fetal bovine serum," +
                    " 2mM l-glutamine and maintained using standard procedures. Immunofluorescence microscopy. Cells grown on coverslips were fixed in ice-cold methanol at _20 _ C " +
                    "for 10 min. After blocking in 0.2% gelatine from cold-water fish (Sigma) in PBS (PBS/FSG) for 15 min, coverslips were incubated with primary antibodies in " +
                    "blocking solution for 1h. Following washes with 0.2% PBS/FSG, the cells were incubated with a 1:500 dilution of secondary antibodies for 1 h " +
                    "(donkey anti- mouse/rabbit/goat/sheep conjugated to Alexa 488 or Alexa 594; Molecular Probes  or donkey anti-mouse conjugated to DyLight 405, Jackson ImmunoResearch). " +
                    "The cells were counterstained with 1 _g ml_1 Hoechst 33342 (Sigma) to visualize chromatin. After washing with 0.2% PBS/FSG, the coverslips were mounted on glass slides " +
                    "by inverting them into mounting solution (ProLong Gold antifade, Molecular Probes). The samples were allowed to cure for 24Ð48 h. " +
                    "The two antibodies targeting the N terminus of PCNT (N1: Abcam ab4448 and N2: Santa Cruz N20) produced similar results in immunofluorescence microscopy, " +
                    "interphase ring measurements and mitotic co-localization analysis. Therefore, the two antibodies were used interchangeably as indicated. " +
                    "Microscopy. Super-resolution microscopy was performed on a three-dimensional (3D) structured illumination microscope " +
                    "(OMX v3, Applied Precision) equipped with 405, 488 and 592.5 nm diode lasers, electron multiplying CCD (charge-coupled device) " +
                    "cameras (Cascade II 512 _ 512, Photometrics), and a _100/1.40 NA planApochromat oil-immersion objective (Olympus). " +
                    "Where indicated, 3D image stacks (0.125 _m apart) were first imaged in the conventional (deconvolution) imag- ing mode, followed by the " +
                    "three-dimensional structured illumination microscopy (3D-SIM) imaging mode with sequential excitation of fluorophores. " +
                    "Photobleaching was measured to be <20% across a typical two-channel, 3D image stack with these conditions and ProLong-mounted samples. " +
                    "Optical transfer functions (Fourier transforms of the point spread functions) were created for each colour channel from recordings of 0.1-_m-diameter fluorescent beads. " +
                    "3D-SIM image stacks were reconstructed using the softWoRx 5.0 software package (Applied Precision) with the following settings: pixel size 39.5 nm; " +
                    "channel-specific optical transfer functions; Wiener filter 0.002; keeping negative intensities; default value of 65 for background intensity; " +
                    "drift correction with respect to first angle; and custom K0 guess angles for camera positions. " +
                    "After reconstruction, individual channels were imported as 32-bit floating points into ImageJ (v1.46m) and maximum intensity projected. " +
                    "When negative values were present after projection, a constant corresponding to the absolute value of the lowest intensity was added to the value of each pixel in the image. " +
                    "Pixel values were then rounded to the nearest lower integer before exporting the entire dynamic range to an 8-bit TIFF and aligning the differ- ent channels using Acapella software " +
                    "(v2.18, Perkin Elmer). Alignment between channels was accurate to 1 pixel unit. The pixel size of our imaging set-up was 39.5 nm; thus, we achieved a Nyquist sampling ratio " +
                    "of _3 pixels per Airy disc with our X ÐY resolution being 120 nm (525 nm wavelength). Relative pixel intensities were quantitatively preserved throughout the reconstruction " +
                    "procedure, which is deterministic69. If images were acquired in the conventional imaging mode, they were deconvolved, 2_ resampled and maximum intensity projected " +
                    "using softWoRx (v5.0, Applied Precision). For final display of figures, cropped centrosomal regions (of deconvolution and 3D-SIM micrographs) were further " +
                    "resampled (4_ bicubic smoother). ", null));

            description.setSamplePreparation(samplePrep);

            LinkArray publications = new LinkArray();
            publications.getEl().add(getLink("http://www.ncbi.nlm.nih.gov/pubmed/23086237", "Subdiffraction imaging of centrosomes reveals higher-order organizational features of pericentriolar material.", "Lawo S, Hasegan M, Gupta GD, Pelletier L., Nat Cell Biol. 2012 Nov;14(11):1148-58. doi: 10.1038/ncb2591. Epub 2012 Oct 21."));
            description.setPublication(publications);

        } else  if (datasetId == 52 ) { // 23

            AnnotationArray imagingMethod = new AnnotationArray();
            imagingMethod.getEl().add(getAnnotation("FBbi_00000246", "fluorescence microscopy", "Super-resolution fluorescence microscopy", null));
            description.setImagingMethod(imagingMethod);

            description.setImageGeneratedBy(getLink("http://www.embl.de/", " EMBL Heidelberg, Germany", null));

            LinkArray publications = new LinkArray();
            publications.getEl().add(getLink("http://dx.doi.org/10.1126/science.1240672", "Nuclear pore scaffold structure analyzed by super-resolution microscopy and particle averaging.", "Szymborska A, de Marco A, Daigle N, Cordes VC, Briggs JA, Ellenberg J."));
            description.setPublication(publications);

        }

    }


    private Map<String, String> convertAnnotationValuesToMap(JSONArray array){

        Map<String, String> map = new HashMap<>();

        if (array.length() > 0 && array.getJSONObject(0).getJSONArray("values") != null) {
            JSONArray annotations = array.getJSONObject(0).getJSONArray("values");
//            System.out.println("=== " + annotations);
            // Annotation format : [["Organism Part", "gastrointestinal system"],[ "Sex", "female"],...]
            for (int i = 0; i < annotations.length(); i++) {
                JSONArray ann = annotations.getJSONArray(i);
                map.put(ann.getString(0), ann.getString(1));
            }
        }

        return map;

    }


    private JSONArray getJsonArray(String url, String fieldName) throws IOException {

        return (new JSONObject(IOUtils.toString(new URL(url), Charset.forName("UTF-8")))).getJSONArray(fieldName);

    }



}

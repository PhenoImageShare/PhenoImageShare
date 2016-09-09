package uk.ac.ebi.phis.xmlDump;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uk.ac.ebi.phis.jaxb.*;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ilinca on 08/09/2016.
 */
public class IdrXmlGenerator {


    //Connect to API
    // PARSE XML

//    containers = (101,)
//
//    list_datasets_url = "http://idr-demo.openmicroscopy.org/webclient/api/datasets/?id={container_id}&page=0"
//    list_images_url = "http://idr-demo.openmicroscopy.org/webclient/api/images/?id={id}&page=1"
//    image_link = "http://idr-demo.openmicroscopy.org/webclient/?show=image-{id}"
//    image_viewer_link = "http://idr-demo.openmicroscopy.org/webclient/img_detail/{id}/"
//    thumbnail_url = "http://idr-demo.openmicroscopy.org/webclient/render_thumbnail/size/96/{id}/"
//    map_url = "http://idr-demo.openmicroscopy.org/webclient/api/annotations/?type=map&image={id}"
//
//
//            for c in containers:
//    print 'Container:', c
//    for d in requests.get(list_datasets_url.format(**{'container_id': c })).json()['datasets']:
//    _url = list_images_url.format(**d)
//            for i in requests.get(_url).json()['images']:
//    print 'image iD:', i['id']
//    print "image link:", image_link.format(**i)
//    print "image viewer link:", image_viewer_link.format(**i)
//    print 'thumbnail URL:', thumbnail_url.format(**i)
//            for a in requests.get(map_url.format(**i)).json()['annotations']:
//    print 'Annotaitons:'
//    print a['values']


    String listDatasetsUrl = "http://idr-demo.openmicroscopy.org/webclient/api/datasets/?id=";
    String listImagesUrl = "http://idr-demo.openmicroscopy.org/webclient/api/images/?id=";
    String thumbnailUrl = "http://idr-demo.openmicroscopy.org/webclient/render_thumbnail/size/";
    String mapUrl = "http://idr-demo.openmicroscopy.org/webclient/api/annotations/?type=map&image=";

    public void export() throws IOException, JSONException {

        JSONArray datasets = getJsonArray(listDatasetsUrl + "101", "datasets");
        for (int i = 0 ; i < datasets.length() ; i ++){
            JSONArray imagesArray =getJsonArray(listImagesUrl + datasets.getJSONObject(i).getInt("id"), "images");
            for (int j = 0 ; j < imagesArray.length(); j ++){
                int imageId = imagesArray.getJSONObject(j).getInt("id");
                JSONArray annotationsArray = getJsonArray(mapUrl + imageId, "annotations");
                System.out.println(imageId + " ++ " + annotationsArray);

                Image img = createImageFromMap(annotationsArray);

            }
        }
    }

    private Image createImageFromMap (JSONArray array){

        Image img = new Image();
        Map<String, String> map = convertAnnotationValuesToMap(array);

        Organism org = new Organism();
        org.setSex(Sex.fromValue(map.get("Sex")));
        org.setOrganismId(map.get("Individual"));
        img.setOrganism(org);

        Genotype genotype = new Genotype();
        GenotypeComponent gc = new GenotypeComponent();
        gc.setGeneId(map.get("Gene Identifier"));
        gc.setGeneSymbol(map.get("Gene Symbol"));
        if (map.get("Genotype").contains("knockout")){
            gc.setMutationType(SangerXmlGenerator.getAnnotation(null, null, "null mutation", null));
        } else {
            System.out.println("What mutation is this   " + map.get("Genotype"));
        }
        genotype.getEl().add(gc);
        img.setMutantGenotypeTraits(genotype);

        img.setDepictedAnatomicalStructure(SangerXmlGenerator.getAnnotation(null, null,  map.get("Organism Part"), AnnotationMode.MANUAL));

        // phenotype terms are numbered. need to get all of them.
        // map.get("Phenotype 3 Term Name");
        // map.get("Phenotype 3 Term Accession");
        Pattern phenotypePattern = Pattern.compile("Phenotype (.+) Term Name");
        for (String key : map.keySet()){

            Matcher matcher = phenotypePattern.matcher(key);
            if (matcher.matches()){
                // TODO add to ROIS, not IMAGE
                String number = matcher.group();
                map.get("Phenotype " + number + " Term Name");
                map.get("Phenotype " + number + " Term Accession");
            }
        }

        return img;
    }


    private Map<String, String> convertAnnotationValuesToMap(JSONArray array){

        Map<String, String> map = new HashMap<>();

        // Annotation format : [["Organism Part", "gastrointestinal system"],[ "Sex", "female"],...]
        for (int i = 0; i < array.length(); i++){
            JSONArray ann = array.getJSONArray(i);
            map.put(ann.getString(0), ann.getString(1));
        }

        return map;

    }

    private JSONArray getJsonArray(String url, String fieldName) throws IOException {

        return (new JSONObject(IOUtils.toString(new URL(url), Charset.forName("UTF-8")))).getJSONArray(fieldName);

    }



}

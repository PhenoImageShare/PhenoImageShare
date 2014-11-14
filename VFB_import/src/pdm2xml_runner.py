import json
import VFB2PhisXML as VP
import phisSchema


# open lookup

# open pdm

# For individual in pdm


def load_json(path):
    json_file = open(path, "r")
    json_string = json_file.read()
    json_file.close()
    return json.loads(json_string)


ont_dict = load_json('')  # Load as json dump, produced by Brain queries over merged onts

pdm = load_json("/repos/VFB_owl/src/code/export/Cachero2010.json")

source_name = 'Cachero2010'
source_link = '' # Link to FlyBase pub page
image_set = VP.imageDataSet(source_name, source_link)
# TO add to VP

x= VP.VfbWtAdultBrainImage(ont_dict, image_dataset)

x.

x.add_background_depicted_entity(sfid, text, mode)
x.add_depicted_anatomy_for_expressed_feature(sfid, text, mode)
x.set_is_expression_pattern(s)
x.set_sex(sex)
x.set_source(source)
x.
### 


for i, v in pdm.items():
    image_url = ''  ### Where is this coming from?
    
    out = VP.VfbWtAdultBrainImage(ont_dict, image_set, i, image_url)
    out.set_source(VP.gen_Link("FlyCircuit", "http://flycircuit.net"))
    out.set_is_expression_pattern(False) # Set as false unless types as expression pattern 
    for t in v["Types"]:
        if not t["is_anonymous"]:
            if t["object_id"] == "http://purl.obolibrary.org/obo/vfb/B8C6934B-C27C-4528-BE59-E75F5B9F61B6" :
                out.set_is_expression_pattern(True)
            else:
                out.add_depicted_anatomy_for_expressed_feature(t["object_id"], "", "Manual")
        else:
            #overlaps:
            if t["rel_id"] == "RO_0002131":
                out.add_depicted_anatomy_for_expressed_feature(t["object_id"], "", "Manual")  # But better if could check for subproperties of overlaps 
            #expresses
            if t["rel_id"] == "RO_":
                # set expressed feature to t["object_if"]
                stub = 1



outfile = open("tmp.xml", "w")
outfile.write(d.toxml())


import json
import VFB2PhisXML as VP
import phisSchema
from phisSchema import GenotypeComponent
from VFB2PhisXML import gen_GenotypeComponent
import re
import pyxb


# open lookup

# open pdm

# For individual in pdm

#     """Reads an owl file from ont_url; Writes a JSON file (outfile) of 
#     types and annotations on individuals in the file.
#     JSON structure: 
#     id: 
#        label: string
#        def: string
#        types:
#          - isAnonymous:boolean; 
#          - relId:URI_string; 
#          - objectId:URI_string.
#     """

def load_json(path):
    json_file = open(path, "r")
    json_string = json_file.read()
    json_file.close()
    return json.loads(json_string)


# ont_dict = { "FBdv_00005369": "adult stage", 
#        "FBbt_00003624" : "adult brain", 
#        "FBbi_00000251": "confocal microscopy",  
#        "FBbi_00000024": "whole mounted tissue", 
#        "FBbi_00000002": "chemically fixed tissue", 
#        "FBbi_00000156" : "primary antibody plus labeled secondary antibody"
# }

ont_dict = load_json('id_name.json')  # Load as json dump, produced by Brain queries over merged onts

pdm = load_json("/repos/VFB_owl/src/code/export/Cachero2010.json")

source_name = 'Cachero2010'
source_link = 'http://fu' # Link to FlyBase pub page
image_set = VP.imageDataSet(ont_dict)
image_set.set_source(source_name, source_link)
image_set.set_background_channel_marker(VP.gen_GenotypeComponent(gene_symbol = 'bruchpilot')) # Need to find ID

for i, v in pdm.items():
    image_url = 'http://fubar'  ### Where is this coming from?
    out = VP.VfbWtAdultBrainImage(ont = ont_dict, image_dataset = image_set, vfb_image_id = i, 
     image_url = image_url)
    # Problem with binding to doc second time round... Not clear why.
    out.set_signal_channel_visualisation_method('FBbi_00000437')
    out.set_sex("Unknown")  # Setting default in case no indication of sex in data.
    for t in v["Types"]:
        # If named class
        if not t["isAnonymous"]:
            # if expression pattern
            if t["objectId"] == "B8C6934B-C27C-4528-BE59-E75F5B9F61B6" :
                out.set_is_expression_pattern(True)
            # Named class & not expression pattern - Add as expressed in
            else:
                out.add_depicted_anatomy_for_expressed_feature(sfid = t["objectId"] , text = "", mode = "Manual")
        else:
            if (t["relId"] == 'BFO_0000050') and (t['objectId'] == 'FBbt_00007004'):
                out.set_sex(sex = "Male")
            elif (t["relId"] == 'BFO_0000050') and (t['objectId'] == 'FBbt_00007011'):
                out.set_sex(sex = "Female")
            #overlaps or subproperties.  Manual list should be replaced with reasoner call
            elif t["relId"] in ["RO_0002131", "RO_0002100", "RO_0002130", "RO_0002110", "RO_0002113", "BFO_0000050"]:
                out.add_depicted_anatomy_for_expressed_feature(t["objectId"], "", "Manual")  # But better if could check for subproperties of overlaps 
            #expresses
            if t["relId"] == "RO_0002292":
                # Typing by string parsing FB IDs...  Should be replaced with explicit typing.
                gc = ''
                if re.match('FB(tp|ti)\d{7}', t['objectId']):
                    gc = gen_GenotypeComponent(gf_symbol=t['objectId']) # Need to add name - from ont_dict
                elif re.match('FBgn\d{7}', t['objectId']):
                    gc=gen_GenotypeComponent(gene_id = t['objectId']) # Need to add name - from ont_dict
                if gc:  
                    out.set_expressed_feature_for_signal_channel(gc)
    try:
        out.doc.validateBinding()
    except pyxb.ValidationError as e:
        print e.details()
        raise
    
xml_out = ''

try:
    xml_out = image_set.doc.toxml()
except pyxb.ValidationError as e:
    print e.details()

outfile = open("VFB.xml", "w")
outfile.write(xml_out)


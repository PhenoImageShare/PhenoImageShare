import json
import VFB2PhisXML as VP
from VFB2PhisXML import gen_GenotypeComponent
import re
import pyxb
import warnings
import xml.dom.minidom

# TODO: Add in ensembl id lookup for genes

## For reference

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

def parse_types(types, out, ont_dict):
    for t in types:
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
                    gc = gen_GenotypeComponent(gf_id=t['objectId'], gf_symbol=ont_dict[t['objectId']]) # If these are always in ont_dict, then can standardise rolling.
                elif re.match('FBgn\d{7}', t['objectId']):
                    gc=gen_GenotypeComponent(gene_id = t['objectId'], gene_symbol=[t['objectId']]) # Need to add name - from ont_dict
                if gc:  
                    out.set_expressed_feature_for_signal_channel(gc)
    

ont_dict = load_json('id_name.json')  # Load as json dump, produced by Brain queries over merged onts
datasets = ['Cachero2010', 'Yu2013', 'Ito2013', 'flycircuit_plus', 'Jenett2012']  

ds_dict = [ { "name": 'Cachero2010', 'source': 'Greg Jefferis Lab', "viz_method": 'FBbi_00000437',
             'source_link': 'http://flybase.org/reports/FBrf0211926.html' } ,
            { "name": 'Yu2013', 'source': 'Tzumin Lee Lab', "viz_method": 'FBbi_00000437',
             'source_link': 'http://flybase.org/reports/FBrf0221412.html' } ,
            { "name": 'Ito2013', 'source': 'Kei Ito Lab', "viz_method": 'FBbi_00000437',
             'source_link': 'http://www.ncbi.nlm.nih.gov/pubmed/23541729' } ,
            { "name": 'Jenett2012', 'source': 'FlyLight/Janelia', "viz_method": 'FBbi_00000437',
             'source_link': 'http://www.janelia.org/team-project/fly-light' },
            { "name": 'flycircuit_plus', 'source': 'FlyCircuit', "viz_method": 'FBbi_00000437',
             'source_link': 'http://flycircuit.tw' }
           ] #  Hard wiring for now. Can all be pulled from DB, but not yet all in OWL.  Need to check viz_methods

# In the OWL right now - have source_data_link - but this is only used where there is a specific link to the data - so only for flycircuit & flylight.  Use default source_link if this is not present.
# 

# TODO: still need to be able to set visualisation details by dataset.

for ds in ds_dict:
    warnings.warn("Processing %s." % ds['name'])
    pdm = load_json("/repos/VFB_owl/src/code/export/%s.json" % ds['name'])
    source_name = ds['source']
    source_link =  ds['source_link']# Link to FlyBase pub page. # Really should be able to pull from OWL....
    image_set = VP.imageDataSet(ont_dict)
    image_set.set_source(source_name, source_link)
    image_set.set_background_channel_marker(VP.gen_GenotypeComponent(gene_symbol = 'brp', gene_id = 'FBgn0259246')) # Need to find ID.  In fact, need to set up standard JSON for templates.
    for i, v in pdm.items():
        image_url = 'http://fubar'  ### Placeholder for pulling from from OWL
        out = VP.VfbWtAdultBrainImage(ont = ont_dict, image_dataset = image_set, vfb_image_id = i, 
         image_url = image_url)
        out.set_image_context_url("http://www.virtualflybrain.org/owl/%s" % i)
        # Problem with binding to doc second time round... Not clear why.
        out.set_signal_channel_visualisation_method(ds['viz_method'])  
        out.set_sex("Unknown")  # Setting default in case no indication of sex in data.
        parse_types(types = v['Types'], out = out, ont_dict = ont_dict)
#         try:
#             out.doc.validateBinding()
#         except pyxb.ValidationError as e:
#             print e.details()
#             raise
    
    xml_out = ''
    
    try:
        xml_out = image_set.doc.toxml()
    except pyxb.ValidationError as e:
        print e.details()
    
    outfile = open("../xml_out/VFB_%s.xml" % ds['name'], "w")
    xout = xml.dom.minidom.parseString(xml_out)
    outfile.write(xout.toprettyxml())


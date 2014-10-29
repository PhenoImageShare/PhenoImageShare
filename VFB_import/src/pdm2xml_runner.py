import json
import VFB2PhisXML as VP
import phisSchema


# open lookup

# open pdm

# For individual in pdm

lookup = ''
pdm = ''

d = phisSchema.Doc()


for i, v in pdm.items():
    image_url = ''  ### Where is this coming from?
    
    out = VP.VFB_wt_adult_brain_image(lookup, d, i, image_url)
    out.set_source(VP.gen_Link("FlyCircuit", "http://flycircuit.net"))
    out.set_is_expression_pattern(False) # Set as false unless types as expression pattern 
    for t in v["Types"]:
        if t["is_anonymous"] == True:
            if t["object_id"] == "http://purl.obolibrary.org/obo/vfb/B8C6934B-C27C-4528-BE59-E75F5B9F61B6" :
                out.set_is_expression_pattern(True)
            elif t["object"]: == ""
        if t["rel_id"] == "":
            out.add_depicted_anatomy_for_expressed_feature(, "stuff", "Manual")
            out.set_sex("Male")


outfile = open("", "")
outfile.write(d.toxml())


import VFB2PhisXML as VP
import phisSchema
import pyxb

ont = { "FBdv_00005369": "adult stage", 
       "FBbt_00003624" : "adult brain", 
       "FBbi_00000251": "confocal microscopy",  
       "FBbi_00000024": "whole mounted tissue", 
       "FBbi_00000002": "chemically fixed tissue", 
       "FBbi_00000156" : "primary antibody plus labeled secondary antibody"
}

vfb_image_id = "VFBimage_0000001"
image_url = "http://fu.bar"

d = phisSchema.Doc()
test = VP.VFB_wt_adult_brain_image(ont, d, vfb_image_id, image_url)
test.set_is_expression_pattern(False)
test.set_sex("Male")
test.set_source(VP.gen_Link("FlyCircuit", "http://flycircuit.net"))

c = 0
while c < 9:
    ID = "FuBar_000000" + str(c)
    ont[ID] = "BarFu"
    c += 1
    test.add_depicted_anatomy_for_expressed_feature(ID, "stuff", "Manual")

try:
    print d.toxml()
except pyxb.ValidationError as e:
    print e.details()


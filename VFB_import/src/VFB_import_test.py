import VFB2PhisXML as VP
import pyxb

ont = { "FBdv_00005369": "adult stage", 
       "FBbt_00003624" : "adult brain", 
       "FBbi_00000251": "confocal microscopy",  
       "FBbi_00000024": "whole mounted tissue", 
       "FBbi_00000002": "chemically fixed tissue", 
       "FBbi_00000156" : "primary antibody plus labeled secondary antibody"
}

ids = VP.imageDataSet(ont)

images = ["VFBimage_0000001", "VFBimage_0000002"]
image_url = "http://fu.bar"

ids.set_source(source_name = "FlyCircuit",  source_url = "http://flycircuit.net")
ids.set_background_channel_marker(VP.gen_GenotypeComponent(gene_symbol = 'bruchpilot')) # Need to find ID


for i in images:
    test = VP.VfbWtAdultBrainImage(ont = ont,  image_dataset = ids, vfb_image_id = i, image_url= image_url)
    test.set_is_expression_pattern(False)
    test.set_sex("Male")
    test.set_expressed_feature_for_signal_channel(VP.gen_GenotypeComponent(gene_id = 'FBgn1234567'))
    test.set_signal_channel_visualisation_method('FBbi_00000156')
    c = 0
    while c < 9:
        ID = "FuBar_000000" + str(c)
        ont[ID] = "BarFu"
        c += 1
        test.add_depicted_anatomy_for_expressed_feature(ID, "stuff", "Manual")
        

try:
    print ids.doc.toxml()
except pyxb.ValidationError as e:
    print e.details()


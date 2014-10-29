#!/usr/bin/env python

import sys
#from httplib2 import URI
sys.path.append("../build/")
import phisSchema
import pyxb  # Probably using pyxb in a rather basal way...


# Sketch of interface:
# vars to set (for now):
# expressed feature (+ its type - gene or transgene); classification of struc & overlapped region

# A few convenience methods, currently rely on phisSchema global
def gen_OntologyTerm(ont, ID):
    # ont is an id/name lookup for ontology terms used here.  Populate with separate script calling brain
    ot = phisSchema.OntologyTerm()
    ot.termId = ID
    ot.termLabel = ont[ID]
    return ot

def gen_Link(display_name, url):
    """Takes display_name and URI as args and returns a gen_Link"""
    gen_Link = phisSchema.Link()
    gen_Link.display_name = display_name
    gen_Link.url = url
    return gen_Link
    
    
    
#fbbi = Brain()
#fbbi.learn('file://repos/fbbi/releases/fbbi.owl') # local path for testing

# Create a doc instance for the whole image record

doc = phisSchema.Doc()

# Create instances for the various components

image = phisSchema.Image()
channel1 = phisSchema.Channel()
channel2 = phisSchema.Channel()
roi1 = phisSchema.Roi()
roi2 = phisSchema.Roi()

# Glue the main bits together:
#doc.image.append(image)
#doc.channel.append(channel1)
#doc.channel.append(channel2)
#doc.roi.append(roi1)
#doc.roi.append(roi2)

# This pattern seems to work for binding a complex element where 0-many allowed:

doc.append(image)
doc.append(channel1)
doc.append(channel2)
doc.append(roi1)
doc.append(roi2)


# Populate IDs - There has to be a better way!!!!
image.id = '274eff21-3e6e-11e4-8b34-10ddb1f09160'  ## Using UUID for testing
# Generate IDs and add to the many denormalised ID fields
# Generate IDs for two channels and corresponding ROIs according to the scheme: roi_id-a/b; channel_id-a/b.
# Assign IDs to correct cross-referencing fields.

channel1.id = "channel_" + image.id + "-a"
channel2.id = "channel_" + image.id + "-b"
roi1.id  = "roi_" + image.id + "-a"
roi2.id = "roi_" + image.id + "-b"

image.associated_roi = pyxb.BIND()  # Special magic
image.associated_roi.el.append(roi1.id) # Is this correct, or should I be populating a string array and appending that?  
image.associated_roi.el.append(roi2.id)
image.associated_channel = pyxb.BIND()
image.associated_channel.el.append(channel1.id)
image.associated_channel.el.append(channel2.id)
channel1.associated_image = image.id
channel2.associated_image = image.id
roi1.associated_image = image.id
roi2.associated_image = image.id
roi1.associated_channel = pyxb.BIND()
roi1.associated_channel.el.append(channel1.id)
roi2.associated_channel = pyxb.BIND()
roi2.associated_channel.el.append(channel2.id)
channel1.associated_roi = pyxb.BIND()
channel1.associated_roi.el.append(roi1.id)
channel2.associated_roi = pyxb.BIND()
channel2.associated_roi.el.append(roi2.id)

# Populating organisms:

o = phisSchema.Organism()
o.taxon = "Drosophila melanogaster"
o.sex = "Male"
o.ncbi_taxon_id = "NCBItaxon_7227"
ot = phisSchema.OntologyTerm()
ot.termLabel = "adult stage"
ot.termId = "FBdv_00005369"
image.organism = o # Simple assignment as only one organism field. Must be complete path up to doc in order to run .toxml() function
o.toxml()

# image description

imageDesc = phisSchema.ImageDescription()
image.image_description = imageDesc  # Firimage.image_descriptionst construct object
image.image_description.image_url = "http://some_image_url.here"

dimensions = phisSchema.Dimensions()
dimensions.image_width = 512
dimensions.image_height = 512
dimensions.image_depth = 512
image.image_description.image_dimensions = dimensions

#check details added.
print image.image_description.image_dimensions.toxml()

image.image_description.host = gen_Link("Virtual Fly Brain", "http://www.virtualflybrain.org")
image.image_description.image_generated_by = gen_Link("fubar", "http://fu.bar")
image.image_description.organism_generated_by = gen_Link("fubar", "http://fu.bar")

print image.image_description.host.toxml()


image.image_description.sample_preparation = pyxb.BIND()
image.image_description.imaging_method = pyxb.BIND()

# Populate image description
# create ont term object
# append

# defining dict for testing purposes

fbbi = { "FBbi_00000251": "confocal microscopy",  "FBbi_00000024": "whole mounted tissue", "FBbi_00000002": "chemically fixed tissue", "FBbi_00000156" : "primary antibody plus labeled secondary antibody" }

# Actually - both methods seem to work:
# Method 1 - intermediate node and directly bind
imaging_methods = phisSchema.OntologyTermArray()
image.image_description.imaging_method = imaging_methods # But remember - this is only possible because of an earlier pyxB expansion
imaging_methods.append(gen_OntologyTerm(fbbi, "FBbi_00000251"))

print image.image_description.imaging_method.toxml()
# <?xml version="1.0" ?><imaging_method><el><termLabel>confocal microscopy</termLabel><termId>FBbi_00000251</termId></el></imaging_method>
                      
image.image_description.sample_preparation = pyxb.BIND()
image.image_description.sample_preparation.append(gen_OntologyTerm(fbbi, "FBbi_00000024")) # whole mount tissue
image.image_description.sample_preparation.append(gen_OntologyTerm(fbbi, "FBbi_00000002")) # chemically fixed tissue

print image.image_description.sample_preparation.toxml()
# <?xml version="1.0" ?><sample_preparation><el><termLabel>whole mounted tissue</termLabel><termId>FBbi_00000024</termId></el><el><termLabel>chemically fixed tissue</termLabel><termId>FBbi_00000002</termId></el></sample_preparation>

image.image_description.visualisation_method =  pyxb.BIND()
image.image_description.visualisation_method.append(gen_OntologyTerm(fbbi, "FBbi_00000156")) # - is actually channel 
#    specific!!! Adding likely one here. - primary antibody plus labeled secondary antibody

# Testing 

ita = phisSchema.ImageTypeArray()
ita.append("expression") # Use Expression if depicts expression pattern - otherwise use anatomy/phenotype.  Don't this there is any case for using both.
image.image_description.image_type = ita
image.image_description.sample_type = "wild type"

print "validating imageDesc"

try:
    imageDesc.validateBinding()
except pyxb.ValidationError as e:
    print e.details()
    
print "validating image"
try:
    image.validateBinding()
except pyxb.ValidationError as e:
    print e.details()
    
print image.toxml()

print "validating doc"
try:
    doc.validateBinding()
except pyxb.ValidationError as e:
    print e.details()
      
  
print doc.toxml()


roi1.depicted_anatomical_structure = pyxb.BIND()  # Special magic to me right now.  Would be good to understand.
roi2.depicted_anatomical_structure = pyxb.BIND()  # Special magic to me right now.  Would be good to understand.

dep = roi2.depicted_anatomical_structure.el

# Each branch of the schema must be expanded via pyxb.BIND() to reveal attributes:
def populate_depicted_anatomy_exp(classDict, dep):
    for Id, Name in classDict:
        depicted = phisSchema.OntologyTerm()
        depicted.Termid = Id       
        depicted.TermLabel = Name
        dep.append(depicted)
        #  How to add text fields here??
        
        

        
        
# Assignment is simple - once you get all the way out to a node.
#depicted.termId = "FBbi_1234567"
#depicted.termLabel = "fubar"

# Append and instance of depicted to the list (el)
#image.depicted_anatomical_structure =  pyxb.BIND()  
#image.depicted_anatomical_structure.append(depicted)

# Testing
#print image.depicted_anatomical_structure.toxml()

# '<?xml version="1.0" ?><depicted_anatomical_structure><el><anatomy_ontology_id>FBbi_1234567</anatomy_ontology_id><anatomy_ontology_term>fubar</anatomy_ontology_term></el></depicted_anatomical_structure>'

# - Can't test anything more deeply nested until I fill up enough compulsory elements.

#  But all this feels quite verbose - can I make use of the Factory methods on some nodes to make this easier?

# Generating IDs and cross linking



    

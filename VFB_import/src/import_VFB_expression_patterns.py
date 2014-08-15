#!/usr/bin/env python

import sys
sys.path.append("../build/")
import phisSchema
import pyxb
#from uk.ac.ebi.brain.core import Brain # potentially difficult to mix in Jython here?  Perhaps better to just run Jython separately.

def gen_ont_term(ont, id):
    # ont is an id/name lookup for ontology terms used here.  Populate with separate script calling brain
    
    ot = phisSchema.OntologyTerm()
    ot.termId = id
    ot.termLabel = ont.getLabel(id)
    return ot

#fbbi = Brain()
#fbbi.learn('file://repos/fbbi/releases/fbbi.owl') # local path for testing

# Create a doc instance for the whole image record

doc = phisSchema.Doc()

# Create instances for the various components

image = phisSchema.Image()
channel = phisSchema.Channel()
roi = phisSchema.Roi()

# Where 0-many subcomponents are allowed, these need to be appended to the doc instance

doc.image.append(image)
doc.channel.append(channel)
doc.roi.append(roi)

# Each branch of the schema must be expanded via pyxb.BIND() to reveal attributes:

roi.depicted_anatomical_structure = pyxb.BIND()  # Special magic to me right now.  Would be good to understand.
depicted = phisSchema.Anatomy()  # instance of a root class in the schema

# Assignment is simple - once you get all the way out to a node.
depicted.anatomy_ontology_id = "FBbi_1234567"
depicted.anatomy_ontology_term = "fubar"

# Append and instance of depicted to the list (el)

image.depicted_anatomical_structure.el.append(depicted)

# Testing
image.depicted_anatomical_structure.toxml()

# '<?xml version="1.0" ?><depicted_anatomical_structure><el><anatomy_ontology_id>FBbi_1234567</anatomy_ontology_id><anatomy_ontology_term>fubar</anatomy_ontology_term></el></depicted_anatomical_structure>'

# - Can't test anything more deeply nested until I fill up enough compulsory elements.

#  But all this feels quite verbose - can I make use of the Factory methods on some nodes to make this easier?

image.image_description = pyxb.BIND()
image.image_description.host_name = "Virtual Fly Brain"
image.image_description.host_url = "http://www.virtualflybrain.org"

# Expand dimensions
image.image_description.image_dimensions = pyxb.BIND()
# Attribute assignment for integers is easy.
# Check dimensions  - should these really be compulsory?
image.image_description.image_dimensions.image_width = 512
image.image_description.image_dimensions.image_height = 512
image.image_description.image_dimensions.image_width = 512
#check details added.
image.image_description.image_dimensions.content()
image.image_description.sample_preparation = pyxb.BIND()
image.image_description.imaging_method = pyxb.BIND()

# Populate image description
image.image_description.imaging_method = pyxb.BIND()
# create ont term object
# append
image.image_description.imaging_method.el.append(gen_ont_term(fbbi, FBbi_00000251)) # confocal micoscopy
image.image_description.sample_preparation = pyxb.BIND()
image.image_description.sample_preparation.append(gen_ont_term(fbbi, FBbi_00000024)) # whole mount tissue
image.image_description.sample_preparation.append(gen_ont_term(fbbi, FBbi_00000002)) # chemically fixed tissue
image.image_description.visualisation_method =  pyxb.BIND()
image.image_description.visualisation_method.append(gen_ont_term(fbbi, FBbi_00000156)) # - is actually channel specific!!! Adding likely one here. - primary antibody plus labeled secondary antibody



    

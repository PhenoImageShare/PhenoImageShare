#!/usr/bin/env python

import sys
sys.path.append("../build/")
import phisSchema
import pyxb  
import warnings


# Strategy:
# Perhaps cleanest would be to build a separate interface for data that may vary from VFB.
# This also allows separation of Jython code
# OTOH - this gives another layer of mappings to maintain.



# Sketch of interface:
# minimal vars to set (for now):
# image_id, image URL, source links; expressed feature (+ its type - gene or transgene); classification of struc & overlapped region

# Generator functions live outside the classes. They generate objects that must then be bound.

def gen_OntologyTerm(id_name, ID):
    """Takes id_name lookup dict for ontology terms and an ID
    Returns a phisSchema.OntologyTerm object"""
    ot = phisSchema.OntologyTerm()
    ot.termId = ID
    ot.termLabel = id_name[ID]
    return ot

def gen_Link(display_name, url):
    """Takes display_name and URI as args and returns a phisSchema.Link object"""
    gen_Link = phisSchema.Link()
    gen_Link.display_name = display_name
    gen_Link.url = url
    return gen_Link

def gen_Annotation(ot, text, mode):
    """Generate a phisSchema.Annotation object based on specified:
    ot: ontology term
    text: free text
    mode:  Manual/Automated"""
    annotation = phisSchema.Annotation()
    annotation.annotation_freetext = text
    annotation.ontology_term = ot
    annotation.annotationMode = mode
    return annotation

def gen_roi_Coordinates(x, y, z):
    """Generate a phisSchema.Coordinates object for an roi 
    Each arg specifies a range in the form of a list or tuple 
    with 2 elements
    """
    try:
        assert len(x) == 2
        assert len(y) == 2
        assert len(z) == 2
    except:
        warnings.warn("Percent arrays should have only 2 members - specifying a range.")
        
    coord = phisSchema.Coordinates()
    coord.x_coordinates = _gen_PercentArray(*x)
    coord.y_coordinates = _gen_PercentArray(*y)
    coord.z_coordinates = _gen_PercentArray(*z)
    return coord

def _gen_PercentArray(a, b):
    AB = (a, b)
    pa = phisSchema.PercentArray()
    pa.extend(AB)
    return pa

def gen_GenotypeComponent(gf_symbol=False, gf_id=False, gene_symbol=False, gene_id=False, gf_ensembl_id=False):
    ## How to specify channel.  Use defaults? ###
    """Generate a phisSchema.GenotypeComponent object. 
    All args are strings. Please specify each arg with a keyword
    """
    gc = phisSchema.GenotypeComponent()
    if gene_id:
        gc.gene_id = gene_id
    if gene_symbol:
        gc.gene_symbol = gene_symbol
    if gf_symbol:
        gc.genetic_feature_symbol = gf_symbol
    if gf_id:
        gc.genetic_feature_id = gf_id
    if gf_ensembl_id:
        gc.genetic_feature_ensembl_id = gf_ensembl_id
    return gc

class imageDataSet():
    """Class to use for generating sets of images from a common source.
    Assumes all datasets have common source name and URL. 
    And that they share a background channel marker and visualization methods 
    for background and signal channels.  All of these are set by methods rather than KWARGS.
    """
    # May not be worth bothering with a class here
    def __init__(self, ont_dict):
        ### Do we have a way to distinguish general source from specific source links?
        self.doc = phisSchema.Doc() 
        self.source = ''
        self.background_channel_marker = ''
        self.signal_channel_visualisation_methods = []
        self.background_channel_visualisation_methods = []
        self.ont_dict = ont_dict
        
    def set_source(self, source_name, source_url):
        """source_name and source_url are strings"""
        self.source = gen_Link(source_name, source_url)
        
    def set_background_channel_marker(self, genotype_component):
        """Takes a phisSchema.genotypeComponent object as an arg"""
        self.background_channel_marker = genotype_component
        
    def add_signal_channel_visualisation_method(self, sfid):
        """sfid is the shortFormId of and FBbi visualisation method"""
        self.signal_channel_visualisation_methods.append(gen_OntologyTerm(self.ont_dict, sfid))
        
    def add_background_channel_visualisation_method(self, sfid):
        """sfid is the shortFormId of and FBbi visualisation method"""
        self.background_channel_visualisation_methods.append(gen_OntologyTerm(self.ont_dict, sfid))
        
    
class VfbImage():
    """Interface class for loading VFB data.
    Assumes 3D confocal image with 2 channels - 
    a background stain channel and  a signal channel 
    depicting some interesting expression/anatomy"""
    # Define constants here:  Or should this just jump straight to populating model?
    host = gen_Link("Virtual Fly Brain", "http://www.virtualflybrain.org")  # for image_description.host
    def __init__(self, ont, image_dataset):
        """ont: an ID:name dict of ontology terms used in XML to be produced
            d: A image_dataset object
        """
        self.ont = ont
        self._initialise_image()
        self._unpack_image_dataset(image_dataset)
        self.image.image_description.host = self.host

        
    def _unpack_image_dataset(self, image_dataset):
        self.set_source(image_dataset.source)
#        self.set_signal_channel_visualisation_method(image_dataset.)  # Needs extend rather than append?
#        self.set_background_channel_visualisation_method(image_dataset.) # Needs extend rather than append?
        self.set_expressed_feature_for_background_channel(image_dataset.background_channel_marker)
        
    def set_organism(self, stage, sex):
        """stage must be a phisSchema.ontologyTerm object; sex must be the string 'Male' or 'Female'"""
        organism = phisSchema.Organism()
        organism.taxon = "Drosophila melanogaster"
        organism.sex = sex
        organism.ncbi_taxon_id = "NCBItaxon_7227"
        organism.stage=stage
        self.image.organism = organism
        
    def _initialise_image(self):
        """Assume 2 channels each with an associated ROI at 100%.
        All objects generated by multiple iterations appended to common doc.
        Generate IDs for two channels and corresponding ROIs according to the scheme:
        image_id-a/b roi_id-a/b; channel_id-a/b - where id = self.vfb_image_id.
        channel1/roi1 = background.  channel2/roi2 = signal."""
        
        # Generate Root objects
        self.image = phisSchema.Image()
        self.channel1 = phisSchema.Channel()
        self.channel2 = phisSchema.Channel()
        self.roi1 = phisSchema.Roi()
        self.roi2 = phisSchema.Roi()
        
        # bind root objects to doc  
        # Which pattern??
        
        # This doesn't work for multiple images rois: self.doc.append(image)
        # Need to work on checking the more obvious self.doc.image.append(self.image)
        self.doc.image.append(self.image)
        self.doc.channel.append(self.channel1)
        self.doc.channel.append(self.channel2)
        self.doc.roi.append(self.roi1)
        self.doc.roi.append(self.roi2)
        
        # Populate IDs
        self.image.id = "image_" + self.vfb_image_id
        self.channel1.id = "channel_" + self.vfb_image_id + "-a"
        self.channel2.id = "channel_" + self.vfb_image_id + "-b"
        self.roi1.id  = "roi_" + self.vfb_image_id + "-a"
        self.roi2.id = "roi_" + self.vfb_image_id + "-b"
        self.image.associated_roi = pyxb.BIND()  # Special magic
        self.image.associated_roi.el.append(self.roi1.id) # Is this correct, or should I be populating a string array and appending that?  
        self.image.associated_roi.el.append(self.roi2.id)
        self.image.associated_channel = pyxb.BIND()
        self.image.associated_channel.el.append(self.channel1.id)
        self.image.associated_channel.el.append(self.channel2.id)
        self.channel1.associated_image = self.image.id
        self.channel2.associated_image = self.image.id
        self.roi1.associated_image = self.image.id
        self.roi2.associated_image = self.image.id
        self.roi1.associated_channel = pyxb.BIND()
        self.roi1.associated_channel.el.append(self.channel1.id)
        self.roi2.associated_channel = pyxb.BIND()
        self.roi2.associated_channel.el.append(self.channel2.id)
        self.channel1.associated_roi = pyxb.BIND()
        self.channel1.associated_roi.el.append(self.roi1.id)
        self.channel2.associated_roi = pyxb.BIND()
        self.channel2.associated_roi.el.append(self.roi2.id)
        
        # both ROIs cover whole image:

        self.roi1.coordinates = gen_roi_Coordinates((0,100), (0,100), (0,100))
        self.roi2.coordinates = gen_roi_Coordinates((0,100), (0,100), (0,100))
        

        
        self.depicted_anatomy_background = phisSchema.AnnotationArray()
        self.roi1.depicted_anatomical_structure =  self.depicted_anatomy_background
        self.depicted_anatomy_exp_channel = phisSchema.AnnotationArray()
        self.roi2.depicted_anatomical_structure = self.depicted_anatomy_exp_channel

        
        # Expansions.  Add more here as needed.
        self.image_description = phisSchema.ImageDescription()
        self.image.image_description = self.image_description
        self.image.image_description.sample_preparation = pyxb.BIND()
        self.image.image_description.imaging_method = pyxb.BIND()

        
# Method 1 - intermediate node and directly bind
        imaging_methods = phisSchema.OntologyTermArray()
        self.image.image_description.imaging_method = imaging_methods # But remember - this is only possible because of an earlier pyxB expansion
        imaging_methods.append(gen_OntologyTerm(self.ont, "FBbi_00000251"))

# Method 2 - pyxB.BIND() expansion                      
        self.image.image_description.sample_preparation = pyxb.BIND()
        self.image.image_description.sample_preparation.append(gen_OntologyTerm(self.ont, "FBbi_00000024")) # whole mount tissue
        self.image.image_description.sample_preparation.append(gen_OntologyTerm(self.ont, "FBbi_00000002")) # chemically fixed

# Set methods generate the relevant object and bind it.
        
    def set_dimensions(self, x, y, z=0):        
        """x, y and z are dimensions in pixels. Z is optional (default 0)""" 
        dimensions = phisSchema.Dimensions()
        dimensions.image_width = x
        dimensions.image_height = y
        dimensions.image_depth = z
        self.image_description.image_dimensions = dimensions

        
    def set_image_and_sample_type(self, wt_or_mut, exp_anat_phen):
        self.image.image_description.sample_type = "wild type"
        ita = phisSchema.ImageTypeArray()
        ita.append("expression") # Use Expression if depicts expression pattern - otherwise use anatomy/phenotype.  Don't this there is any case for using both.
        self.image.image_description.image_type = ita
        
    def set_source(self, source):
        """source must be a phisSchema.Link object. 
        Assumes source of image and organism are the same."""
        self.image.image_description.image_generated_by = source
        self.image.image_description.organism_generated_by = source
    
    def set_background_channel_visualisation_method(self, sfid):
        self.channel2.visualisation_method =  pyxb.BIND() 
        self.channel2.visualisation_method.append(gen_OntologyTerm(self.ont, sfid)) 
        
    def set_signal_channel_visualisation_method(self, sfid):
        self.channel2.visualisation_method =  pyxb.BIND() 
        self.channel2.visualisation_method.append(gen_OntologyTerm(self.ont, sfid))
        
    def add_background_depicted_entity(self, sfid, text, mode):
        # By convention, background channel is always roi1
        annotation = gen_Annotation(gen_OntologyTerm(self.ont, sfid), text, mode)
        self.depicted_anatomy_background.append(annotation)
    
    def add_depicted_anatomy_for_expressed_feature(self, sfid, text, mode):
        # By convention, background channel is always roi1
        annotation = gen_Annotation(gen_OntologyTerm(self.ont, sfid), text, mode)
        self.depicted_anatomy_exp_channel.append(annotation)
    
    def set_is_expression_pattern(self, s = True):
        """By convention channel2 is signal channel."""
        # Should really just be a boolean.
        if s:
            self.channel2.is_expression_pattern = "Yes"
        else:
            self.channel2.is_expression_pattern = "No"

    def set_expressed_feature_for_signal_channel(self, genotype_component):
        """genotype_component: a phisSchema.GenotypeComponent object."""
        self.channel2.depicts_expression_of = genotype_component
        
    def set_expressed_feature_for_background_channel(self, genotype_component):
        """genotype_component: a phisSchema.GenotypeComponent object."""
        self.channel1.depicts_expression_of = genotype_component
    
    def set_image_context_url(self, url):
        self.image.image_description.image_context_url = url

class VfbWtAdultBrainImage(VfbImage):
    """Args:
       - ont is a name_id dict lookup for ontology terms. 
       - image_dataset is an imageDataSet object
       - vfb_image_id is an id string for the image
       - image_url is also a string
    Compulsory fields to set in order to generate XML:
       - set_sex("Male/Female")
       - set_is_expression_pattern(True/False)
       - add_depicted_anatomy_for_expressed_feature(ont_term)
    Other necessary fields to set for usable XML:
       - set_expressed_feature
       - set_visualisation_method
    Set by default:
       - sample prep: chemically fixed; whole mount tissue
       - imaging methods: confocal microscopy
       - image has 2 channels - one background, and one signal.
       - organism: Dmel
       - stage: adult
       - Background channel anatomy: adult brain
       - Dimensions = 512,512,512
    """

    # Consider ditching this subclass if don't find a bunch of more specific things to say.  Might be better to have subclasses for neuron, clone and expression pattern
    # One doc for all images.
    def __init__(self, ont, image_dataset, vfb_image_id, image_url):
        
        self.ont = ont
        self.doc = image_dataset.doc
        self.vfb_image_id = vfb_image_id        
        self._initialise_image()
        self.image.image_description.image_url = image_url
        self.set_source(image_dataset.source)
        self.stage = gen_OntologyTerm(ont, "FBdv_00005369")  # Hmmmm - global!
        self.image.image_description.host = self.host
        self.set_dimensions(512, 512, 512)
        self.add_background_depicted_entity("FBbt_00003624", "background channel", "Manual")
        ita = phisSchema.ImageTypeArray()
        ita.append("expression") # Use Expression if depicts expression pattern - otherwise use anatomy/phenotype.  Don't this there is any case for using both.
        self.image.image_description.image_type = ita
        self.image.image_description.sample_type = "wild type"
    
    def set_sex(self, sex):
        """sex = string "Male"/"Femle". Automatically sets doc.image.organism"""
        self.set_organism(self.stage, sex)
    
# Test
# For testing purposes.  Will be autogenerated from ontology files in full run)





      
        

# Notes        
        
# Assignment is simple - once you get all the way out to a node.
#depicted.termId = "FBbi_1234567"
#depicted.termLabel = "fubar"

# Append and instance of depicted to the list (el)
#image.depicted_anatomical_structure =  pyxb.BIND()  
#image.depicted_anatomical_structure.append(depicted)

# Testing
#print image.depicted_anatomical_structure.toxml()

# '<?xml version="1.0" ?><depicted_anatomical_structure><el><anatomy_ontology_id>FBbi_1234567</anatomy_ontology_id><anatomy_ontology_term>fubar</anatomy_ontology_term></el></depicted_anatomical_structure>'

#  But all this feels quite verbose - can I make use of the Factory methods on some nodes to make this easier?



    

# ./phisSchema.py
# -*- coding: utf-8 -*-
# PyXB bindings for NM:4a11b7f056635b900095d2a98a5d7168825518c5
# Generated 2014-06-03 11:35:37.305983 by PyXB version 1.2.3
# Namespace http://www.example.org/phisSchema

import pyxb
import pyxb.binding
import pyxb.binding.saxer
import io
import pyxb.utils.utility
import pyxb.utils.domutils
import sys

# Unique identifier for bindings created at the same time
_GenerationUID = pyxb.utils.utility.UniqueIdentifier('urn:uuid:ccf61d26-eb0a-11e3-ac03-a8bbcf19a3b6')

# Version of PyXB used to generate the bindings
_PyXBVersion = '1.2.3'
# Generated bindings are not compatible across PyXB versions
if pyxb.__version__ != _PyXBVersion:
    raise pyxb.PyXBVersionError(_PyXBVersion)

# Import bindings for namespaces imported into schema
import pyxb.binding.datatypes

# NOTE: All namespace declarations are reserved within the binding
Namespace = pyxb.namespace.NamespaceForURI(u'http://www.example.org/phisSchema', create_if_missing=True)
Namespace.configureCategories(['typeBinding', 'elementBinding'])

def CreateFromDocument (xml_text, default_namespace=None, location_base=None):
    """Parse the given XML and use the document element to create a
    Python instance.

    @param xml_text An XML document.  This should be data (Python 2
    str or Python 3 bytes), or a text (Python 2 unicode or Python 3
    str) in the L{pyxb._InputEncoding} encoding.

    @keyword default_namespace The L{pyxb.Namespace} instance to use as the
    default namespace where there is no default namespace in scope.
    If unspecified or C{None}, the namespace of the module containing
    this function will be used.

    @keyword location_base: An object to be recorded as the base of all
    L{pyxb.utils.utility.Location} instances associated with events and
    objects handled by the parser.  You might pass the URI from which
    the document was obtained.
    """

    if pyxb.XMLStyle_saxer != pyxb._XMLStyle:
        dom = pyxb.utils.domutils.StringToDOM(xml_text)
        return CreateFromDOM(dom.documentElement)
    if default_namespace is None:
        default_namespace = Namespace.fallbackNamespace()
    saxer = pyxb.binding.saxer.make_parser(fallback_namespace=default_namespace, location_base=location_base)
    handler = saxer.getContentHandler()
    xmld = xml_text
    if isinstance(xmld, unicode):
        xmld = xmld.encode(pyxb._InputEncoding)
    saxer.parse(io.BytesIO(xmld))
    instance = handler.rootObject()
    return instance

def CreateFromDOM (node, default_namespace=None):
    """Create a Python instance from the given DOM node.
    The node tag must correspond to an element declaration in this module.

    @deprecated: Forcing use of DOM interface is unnecessary; use L{CreateFromDocument}."""
    if default_namespace is None:
        default_namespace = Namespace.fallbackNamespace()
    return pyxb.binding.basis.element.AnyCreateFromDOM(node, default_namespace)


# Atomic simple type: {http://www.example.org/phisSchema}Sex
class Sex (pyxb.binding.datatypes.string, pyxb.binding.basis.enumeration_mixin):

    """An atomic simple type."""

    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Sex')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 4, 8)
    _Documentation = None
Sex._CF_enumeration = pyxb.binding.facets.CF_enumeration(value_datatype=Sex, enum_prefix=None)
Sex.Male = Sex._CF_enumeration.addEnumeration(unicode_value=u'Male', tag=u'Male')
Sex.Female = Sex._CF_enumeration.addEnumeration(unicode_value=u'Female', tag=u'Female')
Sex.Unknown = Sex._CF_enumeration.addEnumeration(unicode_value=u'Unknown', tag=u'Unknown')
Sex._InitializeFacetMap(Sex._CF_enumeration)
Namespace.addCategoryObject('typeBinding', u'Sex', Sex)

# Atomic simple type: {http://www.example.org/phisSchema}Zygosity
class Zygosity (pyxb.binding.datatypes.string, pyxb.binding.basis.enumeration_mixin):

    """An atomic simple type."""

    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Zygosity')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 11, 8)
    _Documentation = None
Zygosity._CF_enumeration = pyxb.binding.facets.CF_enumeration(value_datatype=Zygosity, enum_prefix=None)
Zygosity.homozygous = Zygosity._CF_enumeration.addEnumeration(unicode_value=u'homozygous', tag=u'homozygous')
Zygosity.heterozygous = Zygosity._CF_enumeration.addEnumeration(unicode_value=u'heterozygous', tag=u'heterozygous')
Zygosity.hemizygous = Zygosity._CF_enumeration.addEnumeration(unicode_value=u'hemizygous', tag=u'hemizygous')
Zygosity._InitializeFacetMap(Zygosity._CF_enumeration)
Namespace.addCategoryObject('typeBinding', u'Zygosity', Zygosity)

# Atomic simple type: {http://www.example.org/phisSchema}Strand
class Strand (pyxb.binding.datatypes.string, pyxb.binding.basis.enumeration_mixin):

    """An atomic simple type."""

    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Strand')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 52, 8)
    _Documentation = None
Strand._CF_enumeration = pyxb.binding.facets.CF_enumeration(value_datatype=Strand, enum_prefix=None)
Strand.emptyString = Strand._CF_enumeration.addEnumeration(unicode_value=u'+', tag='emptyString')
Strand.emptyString_ = Strand._CF_enumeration.addEnumeration(unicode_value=u'-', tag='emptyString_')
Strand._InitializeFacetMap(Strand._CF_enumeration)
Namespace.addCategoryObject('typeBinding', u'Strand', Strand)

# Complex type {http://www.example.org/phisSchema}Age with content type ELEMENT_ONLY
class Age (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Age with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Age')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 19, 8)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element age_since_birth uses Python identifier age_since_birth
    __age_since_birth = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'age_since_birth'), 'age_since_birth', '__httpwww_example_orgphisSchema_Age_age_since_birth', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 21, 10), )

    
    age_since_birth = property(__age_since_birth.value, __age_since_birth.set, None, None)

    
    # Element embryonic_age uses Python identifier embryonic_age
    __embryonic_age = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'embryonic_age'), 'embryonic_age', '__httpwww_example_orgphisSchema_Age_embryonic_age', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 22, 10), )

    
    embryonic_age = property(__embryonic_age.value, __embryonic_age.set, None, None)

    _ElementMap.update({
        __age_since_birth.name() : __age_since_birth,
        __embryonic_age.name() : __embryonic_age
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Age', Age)


# Complex type {http://www.example.org/phisSchema}StringArray with content type ELEMENT_ONLY
class StringArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}StringArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'StringArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 26, 8)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_StringArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 28, 13), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'StringArray', StringArray)


# Complex type {http://www.example.org/phisSchema}IntArray with content type ELEMENT_ONLY
class IntArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}IntArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'IntArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 32, 8)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_IntArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 34, 13), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'IntArray', IntArray)


# Complex type {http://www.example.org/phisSchema}ZygArray with content type ELEMENT_ONLY
class ZygArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}ZygArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'ZygArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 38, 8)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_ZygArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 40, 13), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'ZygArray', ZygArray)


# Complex type {http://www.example.org/phisSchema}Dimensions with content type ELEMENT_ONLY
class Dimensions (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Dimensions with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Dimensions')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 44, 8)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element image_depth uses Python identifier image_depth
    __image_depth = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_depth'), 'image_depth', '__httpwww_example_orgphisSchema_Dimensions_image_depth', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 46, 13), )

    
    image_depth = property(__image_depth.value, __image_depth.set, None, None)

    
    # Element image_height uses Python identifier image_height
    __image_height = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_height'), 'image_height', '__httpwww_example_orgphisSchema_Dimensions_image_height', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 47, 13), )

    
    image_height = property(__image_height.value, __image_height.set, None, None)

    
    # Element image_width uses Python identifier image_width
    __image_width = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_width'), 'image_width', '__httpwww_example_orgphisSchema_Dimensions_image_width', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 48, 13), )

    
    image_width = property(__image_width.value, __image_width.set, None, None)

    _ElementMap.update({
        __image_depth.name() : __image_depth,
        __image_height.name() : __image_height,
        __image_width.name() : __image_width
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Dimensions', Dimensions)


# Complex type {http://www.example.org/phisSchema}GenomicLocation with content type ELEMENT_ONLY
class GenomicLocation (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}GenomicLocation with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'GenomicLocation')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 59, 8)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element start_pos uses Python identifier start_pos
    __start_pos = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'start_pos'), 'start_pos', '__httpwww_example_orgphisSchema_GenomicLocation_start_pos', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 61, 13), )

    
    start_pos = property(__start_pos.value, __start_pos.set, None, None)

    
    # Element end_pos uses Python identifier end_pos
    __end_pos = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'end_pos'), 'end_pos', '__httpwww_example_orgphisSchema_GenomicLocation_end_pos', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 62, 13), )

    
    end_pos = property(__end_pos.value, __end_pos.set, None, None)

    
    # Element chromosone uses Python identifier chromosone
    __chromosone = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'chromosone'), 'chromosone', '__httpwww_example_orgphisSchema_GenomicLocation_chromosone', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 63, 13), )

    
    chromosone = property(__chromosone.value, __chromosone.set, None, None)

    
    # Element strand uses Python identifier strand
    __strand = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'strand'), 'strand', '__httpwww_example_orgphisSchema_GenomicLocation_strand', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 64, 13), )

    
    strand = property(__strand.value, __strand.set, None, None)

    _ElementMap.update({
        __start_pos.name() : __start_pos,
        __end_pos.name() : __end_pos,
        __chromosone.name() : __chromosone,
        __strand.name() : __strand
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'GenomicLocation', GenomicLocation)


# Complex type {http://www.example.org/phisSchema}GenomicLocationArray with content type ELEMENT_ONLY
class GenomicLocationArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}GenomicLocationArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'GenomicLocationArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 68, 8)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_GenomicLocationArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 70, 13), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'GenomicLocationArray', GenomicLocationArray)


# Complex type {http://www.example.org/phisSchema}ImageDescription with content type ELEMENT_ONLY
class ImageDescription (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}ImageDescription with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'ImageDescription')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 74, 8)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element image_url uses Python identifier image_url
    __image_url = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_url'), 'image_url', '__httpwww_example_orgphisSchema_ImageDescription_image_url', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 76, 13), )

    
    image_url = property(__image_url.value, __image_url.set, None, None)

    
    # Element image_context_url uses Python identifier image_context_url
    __image_context_url = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_context_url'), 'image_context_url', '__httpwww_example_orgphisSchema_ImageDescription_image_context_url', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 77, 13), )

    
    image_context_url = property(__image_context_url.value, __image_context_url.set, None, None)

    
    # Element image_dimensions uses Python identifier image_dimensions
    __image_dimensions = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_dimensions'), 'image_dimensions', '__httpwww_example_orgphisSchema_ImageDescription_image_dimensions', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 78, 13), )

    
    image_dimensions = property(__image_dimensions.value, __image_dimensions.set, None, None)

    
    # Element machine uses Python identifier machine
    __machine = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'machine'), 'machine', '__httpwww_example_orgphisSchema_ImageDescription_machine', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 79, 13), )

    
    machine = property(__machine.value, __machine.set, None, None)

    
    # Element image_type uses Python identifier image_type
    __image_type = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_type'), 'image_type', '__httpwww_example_orgphisSchema_ImageDescription_image_type', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 80, 13), )

    
    image_type = property(__image_type.value, __image_type.set, None, None)

    
    # Element original_image_id uses Python identifier original_image_id
    __original_image_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'original_image_id'), 'original_image_id', '__httpwww_example_orgphisSchema_ImageDescription_original_image_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 81, 13), )

    
    original_image_id = property(__original_image_id.value, __original_image_id.set, None, None)

    
    # Element source_project_url uses Python identifier source_project_url
    __source_project_url = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'source_project_url'), 'source_project_url', '__httpwww_example_orgphisSchema_ImageDescription_source_project_url', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 82, 13), )

    
    source_project_url = property(__source_project_url.value, __source_project_url.set, None, None)

    
    # Element data_source uses Python identifier data_source
    __data_source = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'data_source'), 'data_source', '__httpwww_example_orgphisSchema_ImageDescription_data_source', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 83, 13), )

    
    data_source = property(__data_source.value, __data_source.set, None, None)

    _ElementMap.update({
        __image_url.name() : __image_url,
        __image_context_url.name() : __image_context_url,
        __image_dimensions.name() : __image_dimensions,
        __machine.name() : __machine,
        __image_type.name() : __image_type,
        __original_image_id.name() : __original_image_id,
        __source_project_url.name() : __source_project_url,
        __data_source.name() : __data_source
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'ImageDescription', ImageDescription)


# Complex type {http://www.example.org/phisSchema}Organism with content type ELEMENT_ONLY
class Organism (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Organism with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Organism')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 87, 8)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element age uses Python identifier age
    __age = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'age'), 'age', '__httpwww_example_orgphisSchema_Organism_age', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 89, 13), )

    
    age = property(__age.value, __age.set, None, None)

    
    # Element taxon uses Python identifier taxon
    __taxon = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'taxon'), 'taxon', '__httpwww_example_orgphisSchema_Organism_taxon', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 90, 13), )

    
    taxon = property(__taxon.value, __taxon.set, None, None)

    
    # Element sex uses Python identifier sex
    __sex = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'sex'), 'sex', '__httpwww_example_orgphisSchema_Organism_sex', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 91, 13), )

    
    sex = property(__sex.value, __sex.set, None, None)

    
    # Element ncbi_taxon_id uses Python identifier ncbi_taxon_id
    __ncbi_taxon_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'ncbi_taxon_id'), 'ncbi_taxon_id', '__httpwww_example_orgphisSchema_Organism_ncbi_taxon_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 92, 13), )

    
    ncbi_taxon_id = property(__ncbi_taxon_id.value, __ncbi_taxon_id.set, None, None)

    
    # Element stage uses Python identifier stage
    __stage = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'stage'), 'stage', '__httpwww_example_orgphisSchema_Organism_stage', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 93, 13), )

    
    stage = property(__stage.value, __stage.set, None, None)

    _ElementMap.update({
        __age.name() : __age,
        __taxon.name() : __taxon,
        __sex.name() : __sex,
        __ncbi_taxon_id.name() : __ncbi_taxon_id,
        __stage.name() : __stage
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Organism', Organism)


# Complex type {http://www.example.org/phisSchema}Phenotype with content type ELEMENT_ONLY
class Phenotype (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Phenotype with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Phenotype')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 97, 8)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element phenotype_ontology_id uses Python identifier phenotype_ontology_id
    __phenotype_ontology_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'phenotype_ontology_id'), 'phenotype_ontology_id', '__httpwww_example_orgphisSchema_Phenotype_phenotype_ontology_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 99, 13), )

    
    phenotype_ontology_id = property(__phenotype_ontology_id.value, __phenotype_ontology_id.set, None, None)

    
    # Element phenotype_freetext uses Python identifier phenotype_freetext
    __phenotype_freetext = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'phenotype_freetext'), 'phenotype_freetext', '__httpwww_example_orgphisSchema_Phenotype_phenotype_freetext', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 100, 13), )

    
    phenotype_freetext = property(__phenotype_freetext.value, __phenotype_freetext.set, None, None)

    
    # Element phenotype_ontology_term uses Python identifier phenotype_ontology_term
    __phenotype_ontology_term = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'phenotype_ontology_term'), 'phenotype_ontology_term', '__httpwww_example_orgphisSchema_Phenotype_phenotype_ontology_term', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 101, 13), )

    
    phenotype_ontology_term = property(__phenotype_ontology_term.value, __phenotype_ontology_term.set, None, None)

    _ElementMap.update({
        __phenotype_ontology_id.name() : __phenotype_ontology_id,
        __phenotype_freetext.name() : __phenotype_freetext,
        __phenotype_ontology_term.name() : __phenotype_ontology_term
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Phenotype', Phenotype)


# Complex type {http://www.example.org/phisSchema}Anatomy with content type ELEMENT_ONLY
class Anatomy (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Anatomy with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Anatomy')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 107, 8)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element anatomy_ontology_id uses Python identifier anatomy_ontology_id
    __anatomy_ontology_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'anatomy_ontology_id'), 'anatomy_ontology_id', '__httpwww_example_orgphisSchema_Anatomy_anatomy_ontology_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 109, 13), )

    
    anatomy_ontology_id = property(__anatomy_ontology_id.value, __anatomy_ontology_id.set, None, None)

    
    # Element anatomy_ontology_term uses Python identifier anatomy_ontology_term
    __anatomy_ontology_term = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'anatomy_ontology_term'), 'anatomy_ontology_term', '__httpwww_example_orgphisSchema_Anatomy_anatomy_ontology_term', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 110, 13), )

    
    anatomy_ontology_term = property(__anatomy_ontology_term.value, __anatomy_ontology_term.set, None, None)

    
    # Element anatomy_freetext uses Python identifier anatomy_freetext
    __anatomy_freetext = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'anatomy_freetext'), 'anatomy_freetext', '__httpwww_example_orgphisSchema_Anatomy_anatomy_freetext', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 112, 13), )

    
    anatomy_freetext = property(__anatomy_freetext.value, __anatomy_freetext.set, None, None)

    _ElementMap.update({
        __anatomy_ontology_id.name() : __anatomy_ontology_id,
        __anatomy_ontology_term.name() : __anatomy_ontology_term,
        __anatomy_freetext.name() : __anatomy_freetext
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Anatomy', Anatomy)


# Complex type {http://www.example.org/phisSchema}Image with content type ELEMENT_ONLY
class Image (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Image with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Image')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 116, 10)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element id uses Python identifier id
    __id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'id'), 'id', '__httpwww_example_orgphisSchema_Image_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 118, 13), )

    
    id = property(__id.value, __id.set, None, None)

    
    # Element organism uses Python identifier organism
    __organism = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'organism'), 'organism', '__httpwww_example_orgphisSchema_Image_organism', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 119, 13), )

    
    organism = property(__organism.value, __organism.set, None, None)

    
    # Element image_description uses Python identifier image_description
    __image_description = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_description'), 'image_description', '__httpwww_example_orgphisSchema_Image_image_description', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 120, 13), )

    
    image_description = property(__image_description.value, __image_description.set, None, None)

    
    # Element phenotyping_center uses Python identifier phenotyping_center
    __phenotyping_center = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'phenotyping_center'), 'phenotyping_center', '__httpwww_example_orgphisSchema_Image_phenotyping_center', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 121, 13), )

    
    phenotyping_center = property(__phenotyping_center.value, __phenotyping_center.set, None, None)

    
    # Element gene_id uses Python identifier gene_id
    __gene_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'gene_id'), 'gene_id', '__httpwww_example_orgphisSchema_Image_gene_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 122, 13), )

    
    gene_id = property(__gene_id.value, __gene_id.set, None, None)

    
    # Element gene_name uses Python identifier gene_name
    __gene_name = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'gene_name'), 'gene_name', '__httpwww_example_orgphisSchema_Image_gene_name', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 123, 13), )

    
    gene_name = property(__gene_name.value, __gene_name.set, None, None)

    
    # Element genetic_feature_id uses Python identifier genetic_feature_id
    __genetic_feature_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'genetic_feature_id'), 'genetic_feature_id', '__httpwww_example_orgphisSchema_Image_genetic_feature_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 124, 19), )

    
    genetic_feature_id = property(__genetic_feature_id.value, __genetic_feature_id.set, None, None)

    
    # Element genetic_feature_name uses Python identifier genetic_feature_name
    __genetic_feature_name = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'genetic_feature_name'), 'genetic_feature_name', '__httpwww_example_orgphisSchema_Image_genetic_feature_name', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 125, 19), )

    
    genetic_feature_name = property(__genetic_feature_name.value, __genetic_feature_name.set, None, None)

    
    # Element genetic_feature_ensembl_id uses Python identifier genetic_feature_ensembl_id
    __genetic_feature_ensembl_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'genetic_feature_ensembl_id'), 'genetic_feature_ensembl_id', '__httpwww_example_orgphisSchema_Image_genetic_feature_ensembl_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 126, 19), )

    
    genetic_feature_ensembl_id = property(__genetic_feature_ensembl_id.value, __genetic_feature_ensembl_id.set, None, None)

    
    # Element genomic_location uses Python identifier genomic_location
    __genomic_location = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'genomic_location'), 'genomic_location', '__httpwww_example_orgphisSchema_Image_genomic_location', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 127, 19), )

    
    genomic_location = property(__genomic_location.value, __genomic_location.set, None, None)

    
    # Element zygosity uses Python identifier zygosity
    __zygosity = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'zygosity'), 'zygosity', '__httpwww_example_orgphisSchema_Image_zygosity', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 128, 13), )

    
    zygosity = property(__zygosity.value, __zygosity.set, None, None)

    
    # Element procedure uses Python identifier procedure
    __procedure = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'procedure'), 'procedure', '__httpwww_example_orgphisSchema_Image_procedure', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 129, 13), )

    
    procedure = property(__procedure.value, __procedure.set, None, None)

    
    # Element conditions uses Python identifier conditions
    __conditions = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'conditions'), 'conditions', '__httpwww_example_orgphisSchema_Image_conditions', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 130, 13), )

    
    conditions = property(__conditions.value, __conditions.set, None, None)

    
    # Element observations uses Python identifier observations
    __observations = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'observations'), 'observations', '__httpwww_example_orgphisSchema_Image_observations', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 131, 13), )

    
    observations = property(__observations.value, __observations.set, None, None)

    
    # Element associated_roi uses Python identifier associated_roi
    __associated_roi = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'associated_roi'), 'associated_roi', '__httpwww_example_orgphisSchema_Image_associated_roi', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 132, 13), )

    
    associated_roi = property(__associated_roi.value, __associated_roi.set, None, None)

    
    # Element associated_channel uses Python identifier associated_channel
    __associated_channel = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'associated_channel'), 'associated_channel', '__httpwww_example_orgphisSchema_Image_associated_channel', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 133, 13), )

    
    associated_channel = property(__associated_channel.value, __associated_channel.set, None, None)

    
    # Element depicted_anatomical_structure uses Python identifier depicted_anatomical_structure
    __depicted_anatomical_structure = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'depicted_anatomical_structure'), 'depicted_anatomical_structure', '__httpwww_example_orgphisSchema_Image_depicted_anatomical_structure', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 134, 13), )

    
    depicted_anatomical_structure = property(__depicted_anatomical_structure.value, __depicted_anatomical_structure.set, None, None)

    _ElementMap.update({
        __id.name() : __id,
        __organism.name() : __organism,
        __image_description.name() : __image_description,
        __phenotyping_center.name() : __phenotyping_center,
        __gene_id.name() : __gene_id,
        __gene_name.name() : __gene_name,
        __genetic_feature_id.name() : __genetic_feature_id,
        __genetic_feature_name.name() : __genetic_feature_name,
        __genetic_feature_ensembl_id.name() : __genetic_feature_ensembl_id,
        __genomic_location.name() : __genomic_location,
        __zygosity.name() : __zygosity,
        __procedure.name() : __procedure,
        __conditions.name() : __conditions,
        __observations.name() : __observations,
        __associated_roi.name() : __associated_roi,
        __associated_channel.name() : __associated_channel,
        __depicted_anatomical_structure.name() : __depicted_anatomical_structure
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Image', Image)


# Complex type {http://www.example.org/phisSchema}Channel with content type ELEMENT_ONLY
class Channel (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Channel with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Channel')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 138, 9)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element associated_immage uses Python identifier associated_immage
    __associated_immage = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'associated_immage'), 'associated_immage', '__httpwww_example_orgphisSchema_Channel_associated_immage', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 140, 13), )

    
    associated_immage = property(__associated_immage.value, __associated_immage.set, None, None)

    
    # Element associated_roi uses Python identifier associated_roi
    __associated_roi = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'associated_roi'), 'associated_roi', '__httpwww_example_orgphisSchema_Channel_associated_roi', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 141, 13), )

    
    associated_roi = property(__associated_roi.value, __associated_roi.set, None, None)

    
    # Element gene_id uses Python identifier gene_id
    __gene_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'gene_id'), 'gene_id', '__httpwww_example_orgphisSchema_Channel_gene_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 142, 13), )

    
    gene_id = property(__gene_id.value, __gene_id.set, None, None)

    
    # Element gene_name uses Python identifier gene_name
    __gene_name = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'gene_name'), 'gene_name', '__httpwww_example_orgphisSchema_Channel_gene_name', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 143, 13), )

    
    gene_name = property(__gene_name.value, __gene_name.set, None, None)

    
    # Element genetic_feature_id uses Python identifier genetic_feature_id
    __genetic_feature_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'genetic_feature_id'), 'genetic_feature_id', '__httpwww_example_orgphisSchema_Channel_genetic_feature_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 144, 19), )

    
    genetic_feature_id = property(__genetic_feature_id.value, __genetic_feature_id.set, None, None)

    
    # Element genetic_feature_name uses Python identifier genetic_feature_name
    __genetic_feature_name = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'genetic_feature_name'), 'genetic_feature_name', '__httpwww_example_orgphisSchema_Channel_genetic_feature_name', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 145, 19), )

    
    genetic_feature_name = property(__genetic_feature_name.value, __genetic_feature_name.set, None, None)

    
    # Element genetic_feature_ensembl_id uses Python identifier genetic_feature_ensembl_id
    __genetic_feature_ensembl_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'genetic_feature_ensembl_id'), 'genetic_feature_ensembl_id', '__httpwww_example_orgphisSchema_Channel_genetic_feature_ensembl_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 146, 19), )

    
    genetic_feature_ensembl_id = property(__genetic_feature_ensembl_id.value, __genetic_feature_ensembl_id.set, None, None)

    
    # Element genomic_location uses Python identifier genomic_location
    __genomic_location = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'genomic_location'), 'genomic_location', '__httpwww_example_orgphisSchema_Channel_genomic_location', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 147, 19), )

    
    genomic_location = property(__genomic_location.value, __genomic_location.set, None, None)

    
    # Element zygosity uses Python identifier zygosity
    __zygosity = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'zygosity'), 'zygosity', '__httpwww_example_orgphisSchema_Channel_zygosity', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 148, 13), )

    
    zygosity = property(__zygosity.value, __zygosity.set, None, None)

    _ElementMap.update({
        __associated_immage.name() : __associated_immage,
        __associated_roi.name() : __associated_roi,
        __gene_id.name() : __gene_id,
        __gene_name.name() : __gene_name,
        __genetic_feature_id.name() : __genetic_feature_id,
        __genetic_feature_name.name() : __genetic_feature_name,
        __genetic_feature_ensembl_id.name() : __genetic_feature_ensembl_id,
        __genomic_location.name() : __genomic_location,
        __zygosity.name() : __zygosity
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Channel', Channel)


# Complex type {http://www.example.org/phisSchema}Roi with content type ELEMENT_ONLY
class Roi (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Roi with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Roi')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 152, 9)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element associated_immage uses Python identifier associated_immage
    __associated_immage = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'associated_immage'), 'associated_immage', '__httpwww_example_orgphisSchema_Roi_associated_immage', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 154, 13), )

    
    associated_immage = property(__associated_immage.value, __associated_immage.set, None, None)

    
    # Element id uses Python identifier id
    __id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'id'), 'id', '__httpwww_example_orgphisSchema_Roi_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 155, 13), )

    
    id = property(__id.value, __id.set, None, None)

    
    # Element x_coordinates uses Python identifier x_coordinates
    __x_coordinates = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'x_coordinates'), 'x_coordinates', '__httpwww_example_orgphisSchema_Roi_x_coordinates', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 156, 13), )

    
    x_coordinates = property(__x_coordinates.value, __x_coordinates.set, None, None)

    
    # Element y_coordinates uses Python identifier y_coordinates
    __y_coordinates = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'y_coordinates'), 'y_coordinates', '__httpwww_example_orgphisSchema_Roi_y_coordinates', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 157, 13), )

    
    y_coordinates = property(__y_coordinates.value, __y_coordinates.set, None, None)

    
    # Element z_coordinates uses Python identifier z_coordinates
    __z_coordinates = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'z_coordinates'), 'z_coordinates', '__httpwww_example_orgphisSchema_Roi_z_coordinates', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 158, 13), )

    
    z_coordinates = property(__z_coordinates.value, __z_coordinates.set, None, None)

    
    # Element anatomy_expression_annotations uses Python identifier anatomy_expression_annotations
    __anatomy_expression_annotations = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'anatomy_expression_annotations'), 'anatomy_expression_annotations', '__httpwww_example_orgphisSchema_Roi_anatomy_expression_annotations', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 159, 13), )

    
    anatomy_expression_annotations = property(__anatomy_expression_annotations.value, __anatomy_expression_annotations.set, None, None)

    
    # Element phenotype_annotations uses Python identifier phenotype_annotations
    __phenotype_annotations = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'phenotype_annotations'), 'phenotype_annotations', '__httpwww_example_orgphisSchema_Roi_phenotype_annotations', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 160, 13), )

    
    phenotype_annotations = property(__phenotype_annotations.value, __phenotype_annotations.set, None, None)

    
    # Element observations uses Python identifier observations
    __observations = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'observations'), 'observations', '__httpwww_example_orgphisSchema_Roi_observations', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 161, 13), )

    
    observations = property(__observations.value, __observations.set, None, None)

    
    # Element associated_channel uses Python identifier associated_channel
    __associated_channel = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'associated_channel'), 'associated_channel', '__httpwww_example_orgphisSchema_Roi_associated_channel', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 162, 13), )

    
    associated_channel = property(__associated_channel.value, __associated_channel.set, None, None)

    _ElementMap.update({
        __associated_immage.name() : __associated_immage,
        __id.name() : __id,
        __x_coordinates.name() : __x_coordinates,
        __y_coordinates.name() : __y_coordinates,
        __z_coordinates.name() : __z_coordinates,
        __anatomy_expression_annotations.name() : __anatomy_expression_annotations,
        __phenotype_annotations.name() : __phenotype_annotations,
        __observations.name() : __observations,
        __associated_channel.name() : __associated_channel
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Roi', Roi)


# Complex type {http://www.example.org/phisSchema}Doc with content type ELEMENT_ONLY
class Doc_ (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Doc with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Doc')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 167, 9)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element image uses Python identifier image
    __image = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image'), 'image', '__httpwww_example_orgphisSchema_Doc__image', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 169, 13), )

    
    image = property(__image.value, __image.set, None, None)

    
    # Element channel uses Python identifier channel
    __channel = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'channel'), 'channel', '__httpwww_example_orgphisSchema_Doc__channel', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 170, 13), )

    
    channel = property(__channel.value, __channel.set, None, None)

    
    # Element roi uses Python identifier roi
    __roi = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'roi'), 'roi', '__httpwww_example_orgphisSchema_Doc__roi', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 171, 13), )

    
    roi = property(__roi.value, __roi.set, None, None)

    _ElementMap.update({
        __image.name() : __image,
        __channel.name() : __channel,
        __roi.name() : __roi
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Doc', Doc_)


Doc = pyxb.binding.basis.element(pyxb.namespace.ExpandedName(Namespace, u'Doc'), Doc_, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 175, 8))
Namespace.addCategoryObject('elementBinding', Doc.name().localName(), Doc)



Age._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'age_since_birth'), pyxb.binding.datatypes.float, scope=Age, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 21, 10)))

Age._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'embryonic_age'), pyxb.binding.datatypes.float, scope=Age, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 22, 10)))

def _BuildAutomaton ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton
    del _BuildAutomaton
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Age._UseForTag(pyxb.namespace.ExpandedName(None, u'age_since_birth')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 21, 10))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Age._UseForTag(pyxb.namespace.ExpandedName(None, u'embryonic_age')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 22, 10))
    st_1 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_1)
    transitions = []
    st_0._set_transitionSet(transitions)
    transitions = []
    st_1._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Age._Automaton = _BuildAutomaton()




StringArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), pyxb.binding.datatypes.string, scope=StringArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 28, 13)))

def _BuildAutomaton_ ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_
    del _BuildAutomaton_
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(StringArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 28, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
StringArray._Automaton = _BuildAutomaton_()




IntArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), pyxb.binding.datatypes.int, scope=IntArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 34, 13)))

def _BuildAutomaton_2 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_2
    del _BuildAutomaton_2
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(IntArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 34, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
IntArray._Automaton = _BuildAutomaton_2()




ZygArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), Zygosity, scope=ZygArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 40, 13)))

def _BuildAutomaton_3 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_3
    del _BuildAutomaton_3
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=None, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 40, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(ZygArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 40, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=None)
ZygArray._Automaton = _BuildAutomaton_3()




Dimensions._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_depth'), pyxb.binding.datatypes.int, scope=Dimensions, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 46, 13)))

Dimensions._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_height'), pyxb.binding.datatypes.int, scope=Dimensions, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 47, 13)))

Dimensions._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_width'), pyxb.binding.datatypes.int, scope=Dimensions, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 48, 13)))

def _BuildAutomaton_5 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_5
    del _BuildAutomaton_5
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 46, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Dimensions._UseForTag(pyxb.namespace.ExpandedName(None, u'image_depth')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 46, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_6 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_6
    del _BuildAutomaton_6
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Dimensions._UseForTag(pyxb.namespace.ExpandedName(None, u'image_height')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 47, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_7 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_7
    del _BuildAutomaton_7
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Dimensions._UseForTag(pyxb.namespace.ExpandedName(None, u'image_width')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 48, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_4 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_4
    del _BuildAutomaton_4
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 46, 13))
    counters.add(cc_0)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_5())
    sub_automata.append(_BuildAutomaton_6())
    sub_automata.append(_BuildAutomaton_7())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 45, 9)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Dimensions._Automaton = _BuildAutomaton_4()




GenomicLocation._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'start_pos'), pyxb.binding.datatypes.int, scope=GenomicLocation, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 61, 13)))

GenomicLocation._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'end_pos'), pyxb.binding.datatypes.int, scope=GenomicLocation, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 62, 13)))

GenomicLocation._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'chromosone'), pyxb.binding.datatypes.string, scope=GenomicLocation, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 63, 13)))

GenomicLocation._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'strand'), Strand, scope=GenomicLocation, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 64, 13)))

def _BuildAutomaton_9 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_9
    del _BuildAutomaton_9
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 61, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenomicLocation._UseForTag(pyxb.namespace.ExpandedName(None, u'start_pos')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 61, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_10 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_10
    del _BuildAutomaton_10
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 62, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenomicLocation._UseForTag(pyxb.namespace.ExpandedName(None, u'end_pos')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 62, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_11 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_11
    del _BuildAutomaton_11
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 63, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenomicLocation._UseForTag(pyxb.namespace.ExpandedName(None, u'chromosone')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 63, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_12 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_12
    del _BuildAutomaton_12
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 64, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenomicLocation._UseForTag(pyxb.namespace.ExpandedName(None, u'strand')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 64, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_8 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_8
    del _BuildAutomaton_8
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 61, 13))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 62, 13))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 63, 13))
    counters.add(cc_2)
    cc_3 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 64, 13))
    counters.add(cc_3)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_9())
    sub_automata.append(_BuildAutomaton_10())
    sub_automata.append(_BuildAutomaton_11())
    sub_automata.append(_BuildAutomaton_12())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 60, 9)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=None)
GenomicLocation._Automaton = _BuildAutomaton_8()




GenomicLocationArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), GenomicLocation, scope=GenomicLocationArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 70, 13)))

def _BuildAutomaton_13 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_13
    del _BuildAutomaton_13
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=None, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 70, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenomicLocationArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 70, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=None)
GenomicLocationArray._Automaton = _BuildAutomaton_13()




ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_url'), pyxb.binding.datatypes.string, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 76, 13)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_context_url'), pyxb.binding.datatypes.string, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 77, 13)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_dimensions'), Dimensions, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 78, 13)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'machine'), pyxb.binding.datatypes.string, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 79, 13)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_type'), pyxb.binding.datatypes.string, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 80, 13)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'original_image_id'), pyxb.binding.datatypes.string, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 81, 13)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'source_project_url'), pyxb.binding.datatypes.string, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 82, 13)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'data_source'), pyxb.binding.datatypes.string, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 83, 13)))

def _BuildAutomaton_15 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_15
    del _BuildAutomaton_15
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'image_url')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 76, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_16 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_16
    del _BuildAutomaton_16
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 77, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'image_context_url')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 77, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_17 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_17
    del _BuildAutomaton_17
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'image_dimensions')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 78, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_18 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_18
    del _BuildAutomaton_18
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 79, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'machine')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 79, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_19 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_19
    del _BuildAutomaton_19
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 80, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'image_type')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 80, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_20 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_20
    del _BuildAutomaton_20
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'original_image_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 81, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_21 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_21
    del _BuildAutomaton_21
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'source_project_url')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 82, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_22 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_22
    del _BuildAutomaton_22
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'data_source')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 83, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_14 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_14
    del _BuildAutomaton_14
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 77, 13))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 79, 13))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 80, 13))
    counters.add(cc_2)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_15())
    sub_automata.append(_BuildAutomaton_16())
    sub_automata.append(_BuildAutomaton_17())
    sub_automata.append(_BuildAutomaton_18())
    sub_automata.append(_BuildAutomaton_19())
    sub_automata.append(_BuildAutomaton_20())
    sub_automata.append(_BuildAutomaton_21())
    sub_automata.append(_BuildAutomaton_22())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 75, 9)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
ImageDescription._Automaton = _BuildAutomaton_14()




Organism._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'age'), Age, scope=Organism, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 89, 13)))

Organism._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'taxon'), pyxb.binding.datatypes.string, scope=Organism, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 90, 13)))

Organism._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'sex'), Sex, scope=Organism, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 91, 13)))

Organism._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'ncbi_taxon_id'), pyxb.binding.datatypes.string, scope=Organism, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 92, 13)))

Organism._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'stage'), pyxb.binding.datatypes.string, scope=Organism, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 93, 13)))

def _BuildAutomaton_24 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_24
    del _BuildAutomaton_24
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Organism._UseForTag(pyxb.namespace.ExpandedName(None, u'age')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 89, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_25 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_25
    del _BuildAutomaton_25
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Organism._UseForTag(pyxb.namespace.ExpandedName(None, u'taxon')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 90, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_26 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_26
    del _BuildAutomaton_26
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Organism._UseForTag(pyxb.namespace.ExpandedName(None, u'sex')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 91, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_27 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_27
    del _BuildAutomaton_27
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 92, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Organism._UseForTag(pyxb.namespace.ExpandedName(None, u'ncbi_taxon_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 92, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_28 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_28
    del _BuildAutomaton_28
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 93, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Organism._UseForTag(pyxb.namespace.ExpandedName(None, u'stage')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 93, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_23 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_23
    del _BuildAutomaton_23
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 92, 13))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 93, 13))
    counters.add(cc_1)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_24())
    sub_automata.append(_BuildAutomaton_25())
    sub_automata.append(_BuildAutomaton_26())
    sub_automata.append(_BuildAutomaton_27())
    sub_automata.append(_BuildAutomaton_28())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 88, 9)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Organism._Automaton = _BuildAutomaton_23()




Phenotype._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'phenotype_ontology_id'), StringArray, scope=Phenotype, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 99, 13)))

Phenotype._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'phenotype_freetext'), StringArray, scope=Phenotype, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 100, 13)))

Phenotype._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'phenotype_ontology_term'), StringArray, scope=Phenotype, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 101, 13)))

def _BuildAutomaton_30 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_30
    del _BuildAutomaton_30
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 99, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Phenotype._UseForTag(pyxb.namespace.ExpandedName(None, u'phenotype_ontology_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 99, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_31 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_31
    del _BuildAutomaton_31
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 100, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Phenotype._UseForTag(pyxb.namespace.ExpandedName(None, u'phenotype_freetext')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 100, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_32 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_32
    del _BuildAutomaton_32
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 101, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Phenotype._UseForTag(pyxb.namespace.ExpandedName(None, u'phenotype_ontology_term')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 101, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_29 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_29
    del _BuildAutomaton_29
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 99, 13))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 100, 13))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 101, 13))
    counters.add(cc_2)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_30())
    sub_automata.append(_BuildAutomaton_31())
    sub_automata.append(_BuildAutomaton_32())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 98, 9)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=None)
Phenotype._Automaton = _BuildAutomaton_29()




Anatomy._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'anatomy_ontology_id'), StringArray, scope=Anatomy, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 109, 13)))

Anatomy._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'anatomy_ontology_term'), StringArray, scope=Anatomy, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 110, 13)))

Anatomy._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'anatomy_freetext'), StringArray, scope=Anatomy, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 112, 13)))

def _BuildAutomaton_34 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_34
    del _BuildAutomaton_34
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 109, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Anatomy._UseForTag(pyxb.namespace.ExpandedName(None, u'anatomy_ontology_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 109, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_35 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_35
    del _BuildAutomaton_35
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 110, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Anatomy._UseForTag(pyxb.namespace.ExpandedName(None, u'anatomy_ontology_term')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 110, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_36 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_36
    del _BuildAutomaton_36
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 112, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Anatomy._UseForTag(pyxb.namespace.ExpandedName(None, u'anatomy_freetext')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 112, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_33 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_33
    del _BuildAutomaton_33
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 109, 13))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 110, 13))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 112, 13))
    counters.add(cc_2)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_34())
    sub_automata.append(_BuildAutomaton_35())
    sub_automata.append(_BuildAutomaton_36())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 108, 9)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=None)
Anatomy._Automaton = _BuildAutomaton_33()




Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'id'), pyxb.binding.datatypes.string, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 118, 13)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'organism'), Organism, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 119, 13)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_description'), ImageDescription, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 120, 13)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'phenotyping_center'), pyxb.binding.datatypes.string, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 121, 13)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'gene_id'), StringArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 122, 13)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'gene_name'), StringArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 123, 13)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'genetic_feature_id'), StringArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 124, 19)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'genetic_feature_name'), StringArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 125, 19)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'genetic_feature_ensembl_id'), StringArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 126, 19)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'genomic_location'), GenomicLocationArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 127, 19)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'zygosity'), ZygArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 128, 13)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'procedure'), pyxb.binding.datatypes.string, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 129, 13)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'conditions'), pyxb.binding.datatypes.string, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 130, 13)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'observations'), StringArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 131, 13)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'associated_roi'), StringArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 132, 13)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'associated_channel'), StringArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 133, 13)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'depicted_anatomical_structure'), Anatomy, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 134, 13)))

def _BuildAutomaton_38 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_38
    del _BuildAutomaton_38
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 118, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_39 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_39
    del _BuildAutomaton_39
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'organism')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 119, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_40 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_40
    del _BuildAutomaton_40
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'image_description')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 120, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_41 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_41
    del _BuildAutomaton_41
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'phenotyping_center')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 121, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_42 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_42
    del _BuildAutomaton_42
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 122, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'gene_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 122, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_43 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_43
    del _BuildAutomaton_43
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 123, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'gene_name')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 123, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_44 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_44
    del _BuildAutomaton_44
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 124, 19))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'genetic_feature_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 124, 19))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_45 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_45
    del _BuildAutomaton_45
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 125, 19))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'genetic_feature_name')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 125, 19))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_46 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_46
    del _BuildAutomaton_46
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 126, 19))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'genetic_feature_ensembl_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 126, 19))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_47 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_47
    del _BuildAutomaton_47
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 127, 19))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'genomic_location')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 127, 19))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_48 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_48
    del _BuildAutomaton_48
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 128, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'zygosity')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 128, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_49 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_49
    del _BuildAutomaton_49
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 129, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'procedure')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 129, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_50 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_50
    del _BuildAutomaton_50
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 130, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'conditions')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 130, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_51 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_51
    del _BuildAutomaton_51
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 131, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'observations')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 131, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_52 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_52
    del _BuildAutomaton_52
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 132, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'associated_roi')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 132, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_53 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_53
    del _BuildAutomaton_53
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 133, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'associated_channel')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 133, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_54 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_54
    del _BuildAutomaton_54
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 134, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'depicted_anatomical_structure')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 134, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_37 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_37
    del _BuildAutomaton_37
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 122, 13))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 123, 13))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 124, 19))
    counters.add(cc_2)
    cc_3 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 125, 19))
    counters.add(cc_3)
    cc_4 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 126, 19))
    counters.add(cc_4)
    cc_5 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 127, 19))
    counters.add(cc_5)
    cc_6 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 128, 13))
    counters.add(cc_6)
    cc_7 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 129, 13))
    counters.add(cc_7)
    cc_8 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 130, 13))
    counters.add(cc_8)
    cc_9 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 131, 13))
    counters.add(cc_9)
    cc_10 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 132, 13))
    counters.add(cc_10)
    cc_11 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 133, 13))
    counters.add(cc_11)
    cc_12 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 134, 13))
    counters.add(cc_12)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_38())
    sub_automata.append(_BuildAutomaton_39())
    sub_automata.append(_BuildAutomaton_40())
    sub_automata.append(_BuildAutomaton_41())
    sub_automata.append(_BuildAutomaton_42())
    sub_automata.append(_BuildAutomaton_43())
    sub_automata.append(_BuildAutomaton_44())
    sub_automata.append(_BuildAutomaton_45())
    sub_automata.append(_BuildAutomaton_46())
    sub_automata.append(_BuildAutomaton_47())
    sub_automata.append(_BuildAutomaton_48())
    sub_automata.append(_BuildAutomaton_49())
    sub_automata.append(_BuildAutomaton_50())
    sub_automata.append(_BuildAutomaton_51())
    sub_automata.append(_BuildAutomaton_52())
    sub_automata.append(_BuildAutomaton_53())
    sub_automata.append(_BuildAutomaton_54())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 117, 11)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Image._Automaton = _BuildAutomaton_37()




Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'associated_immage'), pyxb.binding.datatypes.string, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 140, 13)))

Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'associated_roi'), StringArray, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 141, 13)))

Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'gene_id'), StringArray, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 142, 13)))

Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'gene_name'), StringArray, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 143, 13)))

Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'genetic_feature_id'), StringArray, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 144, 19)))

Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'genetic_feature_name'), StringArray, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 145, 19)))

Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'genetic_feature_ensembl_id'), StringArray, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 146, 19)))

Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'genomic_location'), GenomicLocationArray, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 147, 19)))

Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'zygosity'), ZygArray, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 148, 13)))

def _BuildAutomaton_56 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_56
    del _BuildAutomaton_56
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'associated_immage')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 140, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_57 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_57
    del _BuildAutomaton_57
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 141, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'associated_roi')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 141, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_58 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_58
    del _BuildAutomaton_58
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 142, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'gene_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 142, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_59 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_59
    del _BuildAutomaton_59
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 143, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'gene_name')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 143, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_60 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_60
    del _BuildAutomaton_60
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 144, 19))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'genetic_feature_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 144, 19))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_61 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_61
    del _BuildAutomaton_61
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 145, 19))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'genetic_feature_name')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 145, 19))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_62 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_62
    del _BuildAutomaton_62
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 146, 19))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'genetic_feature_ensembl_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 146, 19))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_63 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_63
    del _BuildAutomaton_63
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 147, 19))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'genomic_location')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 147, 19))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_64 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_64
    del _BuildAutomaton_64
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 148, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'zygosity')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 148, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_55 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_55
    del _BuildAutomaton_55
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 141, 13))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 142, 13))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 143, 13))
    counters.add(cc_2)
    cc_3 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 144, 19))
    counters.add(cc_3)
    cc_4 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 145, 19))
    counters.add(cc_4)
    cc_5 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 146, 19))
    counters.add(cc_5)
    cc_6 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 147, 19))
    counters.add(cc_6)
    cc_7 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 148, 13))
    counters.add(cc_7)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_56())
    sub_automata.append(_BuildAutomaton_57())
    sub_automata.append(_BuildAutomaton_58())
    sub_automata.append(_BuildAutomaton_59())
    sub_automata.append(_BuildAutomaton_60())
    sub_automata.append(_BuildAutomaton_61())
    sub_automata.append(_BuildAutomaton_62())
    sub_automata.append(_BuildAutomaton_63())
    sub_automata.append(_BuildAutomaton_64())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 139, 10)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Channel._Automaton = _BuildAutomaton_55()




Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'associated_immage'), pyxb.binding.datatypes.string, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 154, 13)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'id'), pyxb.binding.datatypes.string, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 155, 13)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'x_coordinates'), IntArray, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 156, 13)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'y_coordinates'), IntArray, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 157, 13)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'z_coordinates'), IntArray, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 158, 13)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'anatomy_expression_annotations'), Anatomy, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 159, 13)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'phenotype_annotations'), Phenotype, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 160, 13)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'observations'), StringArray, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 161, 13)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'associated_channel'), StringArray, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 162, 13)))

def _BuildAutomaton_66 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_66
    del _BuildAutomaton_66
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'associated_immage')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 154, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_67 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_67
    del _BuildAutomaton_67
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 155, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_68 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_68
    del _BuildAutomaton_68
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'x_coordinates')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 156, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_69 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_69
    del _BuildAutomaton_69
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'y_coordinates')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 157, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_70 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_70
    del _BuildAutomaton_70
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'z_coordinates')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 158, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_71 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_71
    del _BuildAutomaton_71
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 159, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'anatomy_expression_annotations')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 159, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_72 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_72
    del _BuildAutomaton_72
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 160, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'phenotype_annotations')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 160, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_73 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_73
    del _BuildAutomaton_73
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 161, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'observations')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 161, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_74 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_74
    del _BuildAutomaton_74
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 162, 13))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'associated_channel')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 162, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_65 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_65
    del _BuildAutomaton_65
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 159, 13))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 160, 13))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 161, 13))
    counters.add(cc_2)
    cc_3 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 162, 13))
    counters.add(cc_3)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_66())
    sub_automata.append(_BuildAutomaton_67())
    sub_automata.append(_BuildAutomaton_68())
    sub_automata.append(_BuildAutomaton_69())
    sub_automata.append(_BuildAutomaton_70())
    sub_automata.append(_BuildAutomaton_71())
    sub_automata.append(_BuildAutomaton_72())
    sub_automata.append(_BuildAutomaton_73())
    sub_automata.append(_BuildAutomaton_74())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 153, 10)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Roi._Automaton = _BuildAutomaton_65()




Doc_._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image'), Image, scope=Doc_, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 169, 13)))

Doc_._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'channel'), Channel, scope=Doc_, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 170, 13)))

Doc_._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'roi'), Roi, scope=Doc_, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 171, 13)))

def _BuildAutomaton_75 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_75
    del _BuildAutomaton_75
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Doc_._UseForTag(pyxb.namespace.ExpandedName(None, u'image')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 169, 13))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Doc_._UseForTag(pyxb.namespace.ExpandedName(None, u'channel')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 170, 13))
    st_1 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_1)
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Doc_._UseForTag(pyxb.namespace.ExpandedName(None, u'roi')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhiS/source/main/resources/phisSchema.xsd', 171, 13))
    st_2 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_2)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    transitions = []
    transitions.append(fac.Transition(st_1, [
         ]))
    st_1._set_transitionSet(transitions)
    transitions = []
    transitions.append(fac.Transition(st_2, [
         ]))
    st_2._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Doc_._Automaton = _BuildAutomaton_75()


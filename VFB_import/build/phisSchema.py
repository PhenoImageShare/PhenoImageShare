# ./phisSchema.py
# -*- coding: utf-8 -*-
# PyXB bindings for NM:4a11b7f056635b900095d2a98a5d7168825518c5
# Generated 2015-03-31 13:36:00.351299 by PyXB version 1.2.3
# Namespace http://www.example.org/phisSchema

import pyxb
import pyxb.binding
import pyxb.binding.saxer
import io
import pyxb.utils.utility
import pyxb.utils.domutils
import sys

# Unique identifier for bindings created at the same time
_GenerationUID = pyxb.utils.utility.UniqueIdentifier('urn:uuid:7c904619-d7a2-11e4-a45d-10ddb1f09160')

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
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 8, 1)
    _Documentation = None
Sex._CF_enumeration = pyxb.binding.facets.CF_enumeration(value_datatype=Sex, enum_prefix=None)
Sex.Male = Sex._CF_enumeration.addEnumeration(unicode_value=u'Male', tag=u'Male')
Sex.Female = Sex._CF_enumeration.addEnumeration(unicode_value=u'Female', tag=u'Female')
Sex.Unknown = Sex._CF_enumeration.addEnumeration(unicode_value=u'Unknown', tag=u'Unknown')
Sex.Unsexed = Sex._CF_enumeration.addEnumeration(unicode_value=u'Unsexed', tag=u'Unsexed')
Sex._InitializeFacetMap(Sex._CF_enumeration)
Namespace.addCategoryObject('typeBinding', u'Sex', Sex)

# Atomic simple type: {http://www.example.org/phisSchema}Zygosity
class Zygosity (pyxb.binding.datatypes.string, pyxb.binding.basis.enumeration_mixin):

    """An atomic simple type."""

    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Zygosity')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 17, 1)
    _Documentation = None
Zygosity._CF_enumeration = pyxb.binding.facets.CF_enumeration(value_datatype=Zygosity, enum_prefix=None)
Zygosity.homozygous = Zygosity._CF_enumeration.addEnumeration(unicode_value=u'homozygous', tag=u'homozygous')
Zygosity.heterozygous = Zygosity._CF_enumeration.addEnumeration(unicode_value=u'heterozygous', tag=u'heterozygous')
Zygosity.hemizygous = Zygosity._CF_enumeration.addEnumeration(unicode_value=u'hemizygous', tag=u'hemizygous')
Zygosity.wild_type = Zygosity._CF_enumeration.addEnumeration(unicode_value=u'wild type', tag=u'wild_type')
Zygosity.unspecified = Zygosity._CF_enumeration.addEnumeration(unicode_value=u'unspecified', tag=u'unspecified')
Zygosity._InitializeFacetMap(Zygosity._CF_enumeration)
Namespace.addCategoryObject('typeBinding', u'Zygosity', Zygosity)

# Atomic simple type: {http://www.example.org/phisSchema}SampleType
class SampleType (pyxb.binding.datatypes.string, pyxb.binding.basis.enumeration_mixin):

    """An atomic simple type."""

    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'SampleType')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 32, 1)
    _Documentation = None
SampleType._CF_enumeration = pyxb.binding.facets.CF_enumeration(value_datatype=SampleType, enum_prefix=None)
SampleType.mutant = SampleType._CF_enumeration.addEnumeration(unicode_value=u'mutant', tag=u'mutant')
SampleType.wild_type = SampleType._CF_enumeration.addEnumeration(unicode_value=u'wild type', tag=u'wild_type')
SampleType._InitializeFacetMap(SampleType._CF_enumeration)
Namespace.addCategoryObject('typeBinding', u'SampleType', SampleType)

# Atomic simple type: {http://www.example.org/phisSchema}ImageType
class ImageType (pyxb.binding.datatypes.string, pyxb.binding.basis.enumeration_mixin):

    """An atomic simple type."""

    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'ImageType')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 39, 1)
    _Documentation = None
ImageType._CF_enumeration = pyxb.binding.facets.CF_enumeration(value_datatype=ImageType, enum_prefix=None)
ImageType.phenotypeanatomy = ImageType._CF_enumeration.addEnumeration(unicode_value=u'phenotype/anatomy', tag=u'phenotypeanatomy')
ImageType.expression = ImageType._CF_enumeration.addEnumeration(unicode_value=u'expression', tag=u'expression')
ImageType._InitializeFacetMap(ImageType._CF_enumeration)
Namespace.addCategoryObject('typeBinding', u'ImageType', ImageType)

# Atomic simple type: {http://www.example.org/phisSchema}PositiveInt
class PositiveInt (pyxb.binding.datatypes.int):

    """An atomic simple type."""

    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'PositiveInt')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 58, 1)
    _Documentation = None
PositiveInt._CF_minInclusive = pyxb.binding.facets.CF_minInclusive(value_datatype=PositiveInt, value=pyxb.binding.datatypes.int(1))
PositiveInt._InitializeFacetMap(PositiveInt._CF_minInclusive)
Namespace.addCategoryObject('typeBinding', u'PositiveInt', PositiveInt)

# Atomic simple type: {http://www.example.org/phisSchema}Percentage
class Percentage (pyxb.binding.datatypes.float):

    """An atomic simple type."""

    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Percentage')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 87, 1)
    _Documentation = None
Percentage._CF_maxInclusive = pyxb.binding.facets.CF_maxInclusive(value_datatype=Percentage, value=pyxb.binding.datatypes.float(100.0))
Percentage._CF_minInclusive = pyxb.binding.facets.CF_minInclusive(value_datatype=Percentage, value=pyxb.binding.datatypes.float(0.0))
Percentage._InitializeFacetMap(Percentage._CF_maxInclusive,
   Percentage._CF_minInclusive)
Namespace.addCategoryObject('typeBinding', u'Percentage', Percentage)

# Atomic simple type: {http://www.example.org/phisSchema}Strand
class Strand (pyxb.binding.datatypes.string, pyxb.binding.basis.enumeration_mixin):

    """An atomic simple type."""

    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Strand')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 115, 1)
    _Documentation = None
Strand._CF_enumeration = pyxb.binding.facets.CF_enumeration(value_datatype=Strand, enum_prefix=None)
Strand.emptyString = Strand._CF_enumeration.addEnumeration(unicode_value=u'+', tag='emptyString')
Strand.emptyString_ = Strand._CF_enumeration.addEnumeration(unicode_value=u'-', tag='emptyString_')
Strand._InitializeFacetMap(Strand._CF_enumeration)
Namespace.addCategoryObject('typeBinding', u'Strand', Strand)

# Atomic simple type: {http://www.example.org/phisSchema}AnnotationMode
class AnnotationMode (pyxb.binding.datatypes.string, pyxb.binding.basis.enumeration_mixin):

    """An atomic simple type."""

    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'AnnotationMode')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 176, 1)
    _Documentation = None
AnnotationMode._CF_enumeration = pyxb.binding.facets.CF_enumeration(value_datatype=AnnotationMode, enum_prefix=None)
AnnotationMode.Manual = AnnotationMode._CF_enumeration.addEnumeration(unicode_value=u'Manual', tag=u'Manual')
AnnotationMode.Automated = AnnotationMode._CF_enumeration.addEnumeration(unicode_value=u'Automated', tag=u'Automated')
AnnotationMode._InitializeFacetMap(AnnotationMode._CF_enumeration)
Namespace.addCategoryObject('typeBinding', u'AnnotationMode', AnnotationMode)

# Atomic simple type: {http://www.example.org/phisSchema}YesNo
class YesNo (pyxb.binding.datatypes.string, pyxb.binding.basis.enumeration_mixin):

    """An atomic simple type."""

    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'YesNo')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 183, 1)
    _Documentation = None
YesNo._CF_enumeration = pyxb.binding.facets.CF_enumeration(value_datatype=YesNo, enum_prefix=None)
YesNo.Yes = YesNo._CF_enumeration.addEnumeration(unicode_value=u'Yes', tag=u'Yes')
YesNo.No = YesNo._CF_enumeration.addEnumeration(unicode_value=u'No', tag=u'No')
YesNo._InitializeFacetMap(YesNo._CF_enumeration)
Namespace.addCategoryObject('typeBinding', u'YesNo', YesNo)

# Complex type {http://www.example.org/phisSchema}ZygArray with content type ELEMENT_ONLY
class ZygArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}ZygArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'ZygArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 26, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_ZygArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 28, 3), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'ZygArray', ZygArray)


# Complex type {http://www.example.org/phisSchema}ImageTypeArray with content type ELEMENT_ONLY
class ImageTypeArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}ImageTypeArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'ImageTypeArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 45, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_ImageTypeArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 47, 3), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'ImageTypeArray', ImageTypeArray)


# Complex type {http://www.example.org/phisSchema}Age with content type ELEMENT_ONLY
class Age (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Age with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Age')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 51, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element age_since_birth uses Python identifier age_since_birth
    __age_since_birth = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'age_since_birth'), 'age_since_birth', '__httpwww_example_orgphisSchema_Age_age_since_birth', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 53, 3), )

    
    age_since_birth = property(__age_since_birth.value, __age_since_birth.set, None, None)

    
    # Element embryonic_age uses Python identifier embryonic_age
    __embryonic_age = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'embryonic_age'), 'embryonic_age', '__httpwww_example_orgphisSchema_Age_embryonic_age', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 54, 3), )

    
    embryonic_age = property(__embryonic_age.value, __embryonic_age.set, None, None)

    _ElementMap.update({
        __age_since_birth.name() : __age_since_birth,
        __embryonic_age.name() : __embryonic_age
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Age', Age)


# Complex type {http://www.example.org/phisSchema}PositiveIntArray with content type ELEMENT_ONLY
class PositiveIntArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}PositiveIntArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'PositiveIntArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 63, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_PositiveIntArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 65, 3), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'PositiveIntArray', PositiveIntArray)


# Complex type {http://www.example.org/phisSchema}IntArray with content type ELEMENT_ONLY
class IntArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}IntArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'IntArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 69, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_IntArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 71, 3), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'IntArray', IntArray)


# Complex type {http://www.example.org/phisSchema}FloatArray with content type ELEMENT_ONLY
class FloatArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}FloatArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'FloatArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 75, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_FloatArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 77, 3), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'FloatArray', FloatArray)


# Complex type {http://www.example.org/phisSchema}StringArray with content type ELEMENT_ONLY
class StringArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}StringArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'StringArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 81, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_StringArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 83, 3), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'StringArray', StringArray)


# Complex type {http://www.example.org/phisSchema}PercentArray with content type ELEMENT_ONLY
class PercentArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}PercentArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'PercentArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 93, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_PercentArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 95, 3), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'PercentArray', PercentArray)


# Complex type {http://www.example.org/phisSchema}Dimensions with content type ELEMENT_ONLY
class Dimensions (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Dimensions with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Dimensions')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 99, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element image_depth uses Python identifier image_depth
    __image_depth = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_depth'), 'image_depth', '__httpwww_example_orgphisSchema_Dimensions_image_depth', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 101, 3), )

    
    image_depth = property(__image_depth.value, __image_depth.set, None, None)

    
    # Element image_height uses Python identifier image_height
    __image_height = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_height'), 'image_height', '__httpwww_example_orgphisSchema_Dimensions_image_height', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 102, 3), )

    
    image_height = property(__image_height.value, __image_height.set, None, None)

    
    # Element image_width uses Python identifier image_width
    __image_width = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_width'), 'image_width', '__httpwww_example_orgphisSchema_Dimensions_image_width', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 103, 3), )

    
    image_width = property(__image_width.value, __image_width.set, None, None)

    _ElementMap.update({
        __image_depth.name() : __image_depth,
        __image_height.name() : __image_height,
        __image_width.name() : __image_width
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Dimensions', Dimensions)


# Complex type {http://www.example.org/phisSchema}Coordinates with content type ELEMENT_ONLY
class Coordinates (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Coordinates with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Coordinates')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 107, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element z_coordinates uses Python identifier z_coordinates
    __z_coordinates = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'z_coordinates'), 'z_coordinates', '__httpwww_example_orgphisSchema_Coordinates_z_coordinates', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 109, 3), )

    
    z_coordinates = property(__z_coordinates.value, __z_coordinates.set, None, None)

    
    # Element y_coordinates uses Python identifier y_coordinates
    __y_coordinates = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'y_coordinates'), 'y_coordinates', '__httpwww_example_orgphisSchema_Coordinates_y_coordinates', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 110, 3), )

    
    y_coordinates = property(__y_coordinates.value, __y_coordinates.set, None, None)

    
    # Element x_coordinates uses Python identifier x_coordinates
    __x_coordinates = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'x_coordinates'), 'x_coordinates', '__httpwww_example_orgphisSchema_Coordinates_x_coordinates', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 111, 3), )

    
    x_coordinates = property(__x_coordinates.value, __x_coordinates.set, None, None)

    _ElementMap.update({
        __z_coordinates.name() : __z_coordinates,
        __y_coordinates.name() : __y_coordinates,
        __x_coordinates.name() : __x_coordinates
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Coordinates', Coordinates)


# Complex type {http://www.example.org/phisSchema}GenomicLocation with content type ELEMENT_ONLY
class GenomicLocation (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}GenomicLocation with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'GenomicLocation')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 122, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element start_pos uses Python identifier start_pos
    __start_pos = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'start_pos'), 'start_pos', '__httpwww_example_orgphisSchema_GenomicLocation_start_pos', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 124, 3), )

    
    start_pos = property(__start_pos.value, __start_pos.set, None, None)

    
    # Element end_pos uses Python identifier end_pos
    __end_pos = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'end_pos'), 'end_pos', '__httpwww_example_orgphisSchema_GenomicLocation_end_pos', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 125, 3), )

    
    end_pos = property(__end_pos.value, __end_pos.set, None, None)

    
    # Element chromosone uses Python identifier chromosone
    __chromosone = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'chromosone'), 'chromosone', '__httpwww_example_orgphisSchema_GenomicLocation_chromosone', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 126, 3), )

    
    chromosone = property(__chromosone.value, __chromosone.set, None, None)

    
    # Element strand uses Python identifier strand
    __strand = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'strand'), 'strand', '__httpwww_example_orgphisSchema_GenomicLocation_strand', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 127, 3), )

    
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
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 130, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_GenomicLocationArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 132, 3), )

    
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
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 136, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element image_url uses Python identifier image_url
    __image_url = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_url'), 'image_url', '__httpwww_example_orgphisSchema_ImageDescription_image_url', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 138, 3), )

    
    image_url = property(__image_url.value, __image_url.set, None, None)

    
    # Element image_context_url uses Python identifier image_context_url
    __image_context_url = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_context_url'), 'image_context_url', '__httpwww_example_orgphisSchema_ImageDescription_image_context_url', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 139, 3), )

    
    image_context_url = property(__image_context_url.value, __image_context_url.set, None, None)

    
    # Element image_dimensions uses Python identifier image_dimensions
    __image_dimensions = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_dimensions'), 'image_dimensions', '__httpwww_example_orgphisSchema_ImageDescription_image_dimensions', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 140, 3), )

    
    image_dimensions = property(__image_dimensions.value, __image_dimensions.set, None, None)

    
    # Element machine uses Python identifier machine
    __machine = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'machine'), 'machine', '__httpwww_example_orgphisSchema_ImageDescription_machine', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 141, 3), )

    
    machine = property(__machine.value, __machine.set, None, None)

    
    # Element image_generated_by uses Python identifier image_generated_by
    __image_generated_by = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_generated_by'), 'image_generated_by', '__httpwww_example_orgphisSchema_ImageDescription_image_generated_by', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 142, 3), )

    
    image_generated_by = property(__image_generated_by.value, __image_generated_by.set, None, None)

    
    # Element image_processed_by uses Python identifier image_processed_by
    __image_processed_by = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_processed_by'), 'image_processed_by', '__httpwww_example_orgphisSchema_ImageDescription_image_processed_by', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 143, 3), )

    
    image_processed_by = property(__image_processed_by.value, __image_processed_by.set, None, None)

    
    # Element organism_generated_by uses Python identifier organism_generated_by
    __organism_generated_by = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'organism_generated_by'), 'organism_generated_by', '__httpwww_example_orgphisSchema_ImageDescription_organism_generated_by', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 144, 3), )

    
    organism_generated_by = property(__organism_generated_by.value, __organism_generated_by.set, None, None)

    
    # Element host uses Python identifier host
    __host = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'host'), 'host', '__httpwww_example_orgphisSchema_ImageDescription_host', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 145, 3), )

    
    host = property(__host.value, __host.set, None, None)

    
    # Element imaging_method uses Python identifier imaging_method
    __imaging_method = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'imaging_method'), 'imaging_method', '__httpwww_example_orgphisSchema_ImageDescription_imaging_method', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 146, 3), )

    
    imaging_method = property(__imaging_method.value, __imaging_method.set, None, None)

    
    # Element sample_preparation uses Python identifier sample_preparation
    __sample_preparation = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'sample_preparation'), 'sample_preparation', '__httpwww_example_orgphisSchema_ImageDescription_sample_preparation', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 147, 3), )

    
    sample_preparation = property(__sample_preparation.value, __sample_preparation.set, None, None)

    
    # Element image_type uses Python identifier image_type
    __image_type = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_type'), 'image_type', '__httpwww_example_orgphisSchema_ImageDescription_image_type', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 148, 3), )

    
    image_type = property(__image_type.value, __image_type.set, None, None)

    
    # Element sample_type uses Python identifier sample_type
    __sample_type = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'sample_type'), 'sample_type', '__httpwww_example_orgphisSchema_ImageDescription_sample_type', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 149, 3), )

    
    sample_type = property(__sample_type.value, __sample_type.set, None, None)

    _ElementMap.update({
        __image_url.name() : __image_url,
        __image_context_url.name() : __image_context_url,
        __image_dimensions.name() : __image_dimensions,
        __machine.name() : __machine,
        __image_generated_by.name() : __image_generated_by,
        __image_processed_by.name() : __image_processed_by,
        __organism_generated_by.name() : __organism_generated_by,
        __host.name() : __host,
        __imaging_method.name() : __imaging_method,
        __sample_preparation.name() : __sample_preparation,
        __image_type.name() : __image_type,
        __sample_type.name() : __sample_type
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
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 153, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element age uses Python identifier age
    __age = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'age'), 'age', '__httpwww_example_orgphisSchema_Organism_age', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 155, 3), )

    
    age = property(__age.value, __age.set, None, None)

    
    # Element taxon uses Python identifier taxon
    __taxon = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'taxon'), 'taxon', '__httpwww_example_orgphisSchema_Organism_taxon', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 156, 3), )

    
    taxon = property(__taxon.value, __taxon.set, None, None)

    
    # Element sex uses Python identifier sex
    __sex = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'sex'), 'sex', '__httpwww_example_orgphisSchema_Organism_sex', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 157, 3), )

    
    sex = property(__sex.value, __sex.set, None, None)

    
    # Element ncbi_taxon_id uses Python identifier ncbi_taxon_id
    __ncbi_taxon_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'ncbi_taxon_id'), 'ncbi_taxon_id', '__httpwww_example_orgphisSchema_Organism_ncbi_taxon_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 158, 3), )

    
    ncbi_taxon_id = property(__ncbi_taxon_id.value, __ncbi_taxon_id.set, None, None)

    
    # Element stage uses Python identifier stage
    __stage = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'stage'), 'stage', '__httpwww_example_orgphisSchema_Organism_stage', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 159, 3), )

    
    stage = property(__stage.value, __stage.set, None, None)

    
    # Element organism_id uses Python identifier organism_id
    __organism_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'organism_id'), 'organism_id', '__httpwww_example_orgphisSchema_Organism_organism_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 160, 3), )

    
    organism_id = property(__organism_id.value, __organism_id.set, None, None)

    _ElementMap.update({
        __age.name() : __age,
        __taxon.name() : __taxon,
        __sex.name() : __sex,
        __ncbi_taxon_id.name() : __ncbi_taxon_id,
        __stage.name() : __stage,
        __organism_id.name() : __organism_id
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Organism', Organism)


# Complex type {http://www.example.org/phisSchema}OntologyTerm with content type ELEMENT_ONLY
class OntologyTerm (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}OntologyTerm with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'OntologyTerm')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 164, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element termLabel uses Python identifier termLabel
    __termLabel = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'termLabel'), 'termLabel', '__httpwww_example_orgphisSchema_OntologyTerm_termLabel', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 166, 3), )

    
    termLabel = property(__termLabel.value, __termLabel.set, None, None)

    
    # Element termId uses Python identifier termId
    __termId = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'termId'), 'termId', '__httpwww_example_orgphisSchema_OntologyTerm_termId', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 167, 3), )

    
    termId = property(__termId.value, __termId.set, None, None)

    _ElementMap.update({
        __termLabel.name() : __termLabel,
        __termId.name() : __termId
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'OntologyTerm', OntologyTerm)


# Complex type {http://www.example.org/phisSchema}OntologyTermArray with content type ELEMENT_ONLY
class OntologyTermArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}OntologyTermArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'OntologyTermArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 170, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_OntologyTermArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 172, 3), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'OntologyTermArray', OntologyTermArray)


# Complex type {http://www.example.org/phisSchema}Annotation with content type ELEMENT_ONLY
class Annotation (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Annotation with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Annotation')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 190, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element ontology_term uses Python identifier ontology_term
    __ontology_term = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'ontology_term'), 'ontology_term', '__httpwww_example_orgphisSchema_Annotation_ontology_term', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 192, 3), )

    
    ontology_term = property(__ontology_term.value, __ontology_term.set, None, None)

    
    # Element annotation_freetext uses Python identifier annotation_freetext
    __annotation_freetext = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'annotation_freetext'), 'annotation_freetext', '__httpwww_example_orgphisSchema_Annotation_annotation_freetext', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 193, 3), )

    
    annotation_freetext = property(__annotation_freetext.value, __annotation_freetext.set, None, None)

    
    # Element annotationMode uses Python identifier annotationMode
    __annotationMode = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'annotationMode'), 'annotationMode', '__httpwww_example_orgphisSchema_Annotation_annotationMode', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 194, 3), )

    
    annotationMode = property(__annotationMode.value, __annotationMode.set, None, None)

    _ElementMap.update({
        __ontology_term.name() : __ontology_term,
        __annotation_freetext.name() : __annotation_freetext,
        __annotationMode.name() : __annotationMode
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Annotation', Annotation)


# Complex type {http://www.example.org/phisSchema}AnnotationArray with content type ELEMENT_ONLY
class AnnotationArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}AnnotationArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'AnnotationArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 197, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_AnnotationArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 199, 3), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'AnnotationArray', AnnotationArray)


# Complex type {http://www.example.org/phisSchema}GenotypeComponent with content type ELEMENT_ONLY
class GenotypeComponent (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}GenotypeComponent with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'GenotypeComponent')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 203, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element gene_id uses Python identifier gene_id
    __gene_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'gene_id'), 'gene_id', '__httpwww_example_orgphisSchema_GenotypeComponent_gene_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 205, 3), )

    
    gene_id = property(__gene_id.value, __gene_id.set, None, None)

    
    # Element gene_symbol uses Python identifier gene_symbol
    __gene_symbol = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'gene_symbol'), 'gene_symbol', '__httpwww_example_orgphisSchema_GenotypeComponent_gene_symbol', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 206, 3), )

    
    gene_symbol = property(__gene_symbol.value, __gene_symbol.set, None, None)

    
    # Element genetic_feature_id uses Python identifier genetic_feature_id
    __genetic_feature_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'genetic_feature_id'), 'genetic_feature_id', '__httpwww_example_orgphisSchema_GenotypeComponent_genetic_feature_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 207, 3), )

    
    genetic_feature_id = property(__genetic_feature_id.value, __genetic_feature_id.set, None, None)

    
    # Element genetic_feature_symbol uses Python identifier genetic_feature_symbol
    __genetic_feature_symbol = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'genetic_feature_symbol'), 'genetic_feature_symbol', '__httpwww_example_orgphisSchema_GenotypeComponent_genetic_feature_symbol', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 208, 3), )

    
    genetic_feature_symbol = property(__genetic_feature_symbol.value, __genetic_feature_symbol.set, None, None)

    
    # Element genetic_feature_ensembl_id uses Python identifier genetic_feature_ensembl_id
    __genetic_feature_ensembl_id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'genetic_feature_ensembl_id'), 'genetic_feature_ensembl_id', '__httpwww_example_orgphisSchema_GenotypeComponent_genetic_feature_ensembl_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 209, 3), )

    
    genetic_feature_ensembl_id = property(__genetic_feature_ensembl_id.value, __genetic_feature_ensembl_id.set, None, None)

    
    # Element genomic_location uses Python identifier genomic_location
    __genomic_location = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'genomic_location'), 'genomic_location', '__httpwww_example_orgphisSchema_GenotypeComponent_genomic_location', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 210, 3), )

    
    genomic_location = property(__genomic_location.value, __genomic_location.set, None, None)

    
    # Element zygosity uses Python identifier zygosity
    __zygosity = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'zygosity'), 'zygosity', '__httpwww_example_orgphisSchema_GenotypeComponent_zygosity', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 211, 3), )

    
    zygosity = property(__zygosity.value, __zygosity.set, None, None)

    _ElementMap.update({
        __gene_id.name() : __gene_id,
        __gene_symbol.name() : __gene_symbol,
        __genetic_feature_id.name() : __genetic_feature_id,
        __genetic_feature_symbol.name() : __genetic_feature_symbol,
        __genetic_feature_ensembl_id.name() : __genetic_feature_ensembl_id,
        __genomic_location.name() : __genomic_location,
        __zygosity.name() : __zygosity
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'GenotypeComponent', GenotypeComponent)


# Complex type {http://www.example.org/phisSchema}Genotype with content type ELEMENT_ONLY
class Genotype (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Genotype with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Genotype')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 215, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_Genotype_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 217, 3), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Genotype', Genotype)


# Complex type {http://www.example.org/phisSchema}Link with content type ELEMENT_ONLY
class Link (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Link with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Link')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 221, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element display_name uses Python identifier display_name
    __display_name = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'display_name'), 'display_name', '__httpwww_example_orgphisSchema_Link_display_name', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 223, 3), )

    
    display_name = property(__display_name.value, __display_name.set, None, None)

    
    # Element url uses Python identifier url
    __url = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'url'), 'url', '__httpwww_example_orgphisSchema_Link_url', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 224, 3), )

    
    url = property(__url.value, __url.set, None, None)

    _ElementMap.update({
        __display_name.name() : __display_name,
        __url.name() : __url
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Link', Link)


# Complex type {http://www.example.org/phisSchema}LinkArray with content type ELEMENT_ONLY
class LinkArray (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}LinkArray with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'LinkArray')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 227, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element el uses Python identifier el
    __el = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'el'), 'el', '__httpwww_example_orgphisSchema_LinkArray_el', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 229, 3), )

    
    el = property(__el.value, __el.set, None, None)

    _ElementMap.update({
        __el.name() : __el
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'LinkArray', LinkArray)


# Complex type {http://www.example.org/phisSchema}Image with content type ELEMENT_ONLY
class Image (pyxb.binding.basis.complexTypeDefinition):
    """Complex type {http://www.example.org/phisSchema}Image with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = pyxb.namespace.ExpandedName(Namespace, u'Image')
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 236, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element id uses Python identifier id
    __id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'id'), 'id', '__httpwww_example_orgphisSchema_Image_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 238, 3), )

    
    id = property(__id.value, __id.set, None, None)

    
    # Element organism uses Python identifier organism
    __organism = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'organism'), 'organism', '__httpwww_example_orgphisSchema_Image_organism', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 239, 3), )

    
    organism = property(__organism.value, __organism.set, None, None)

    
    # Element image_description uses Python identifier image_description
    __image_description = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image_description'), 'image_description', '__httpwww_example_orgphisSchema_Image_image_description', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 240, 3), )

    
    image_description = property(__image_description.value, __image_description.set, None, None)

    
    # Element mutant_genotype_traits uses Python identifier mutant_genotype_traits
    __mutant_genotype_traits = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'mutant_genotype_traits'), 'mutant_genotype_traits', '__httpwww_example_orgphisSchema_Image_mutant_genotype_traits', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 241, 3), )

    
    mutant_genotype_traits = property(__mutant_genotype_traits.value, __mutant_genotype_traits.set, None, None)

    
    # Element conditions uses Python identifier conditions
    __conditions = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'conditions'), 'conditions', '__httpwww_example_orgphisSchema_Image_conditions', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 242, 3), )

    
    conditions = property(__conditions.value, __conditions.set, None, None)

    
    # Element observations uses Python identifier observations
    __observations = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'observations'), 'observations', '__httpwww_example_orgphisSchema_Image_observations', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 243, 3), )

    
    observations = property(__observations.value, __observations.set, None, None)

    
    # Element associated_roi uses Python identifier associated_roi
    __associated_roi = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'associated_roi'), 'associated_roi', '__httpwww_example_orgphisSchema_Image_associated_roi', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 244, 3), )

    
    associated_roi = property(__associated_roi.value, __associated_roi.set, None, None)

    
    # Element associated_channel uses Python identifier associated_channel
    __associated_channel = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'associated_channel'), 'associated_channel', '__httpwww_example_orgphisSchema_Image_associated_channel', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 245, 3), )

    
    associated_channel = property(__associated_channel.value, __associated_channel.set, None, None)

    
    # Element depicted_anatomical_structure uses Python identifier depicted_anatomical_structure
    __depicted_anatomical_structure = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'depicted_anatomical_structure'), 'depicted_anatomical_structure', '__httpwww_example_orgphisSchema_Image_depicted_anatomical_structure', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 246, 3), )

    
    depicted_anatomical_structure = property(__depicted_anatomical_structure.value, __depicted_anatomical_structure.set, None, None)

    _ElementMap.update({
        __id.name() : __id,
        __organism.name() : __organism,
        __image_description.name() : __image_description,
        __mutant_genotype_traits.name() : __mutant_genotype_traits,
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
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 252, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element id uses Python identifier id
    __id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'id'), 'id', '__httpwww_example_orgphisSchema_Channel_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 254, 3), )

    
    id = property(__id.value, __id.set, None, None)

    
    # Element visualisation_method uses Python identifier visualisation_method
    __visualisation_method = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'visualisation_method'), 'visualisation_method', '__httpwww_example_orgphisSchema_Channel_visualisation_method', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 255, 3), )

    
    visualisation_method = property(__visualisation_method.value, __visualisation_method.set, None, None)

    
    # Element associated_image uses Python identifier associated_image
    __associated_image = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'associated_image'), 'associated_image', '__httpwww_example_orgphisSchema_Channel_associated_image', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 256, 3), )

    
    associated_image = property(__associated_image.value, __associated_image.set, None, None)

    
    # Element associated_roi uses Python identifier associated_roi
    __associated_roi = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'associated_roi'), 'associated_roi', '__httpwww_example_orgphisSchema_Channel_associated_roi', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 257, 3), )

    
    associated_roi = property(__associated_roi.value, __associated_roi.set, None, None)

    
    # Element depicts_expression_of uses Python identifier depicts_expression_of
    __depicts_expression_of = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'depicts_expression_of'), 'depicts_expression_of', '__httpwww_example_orgphisSchema_Channel_depicts_expression_of', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 258, 3), )

    
    depicts_expression_of = property(__depicts_expression_of.value, __depicts_expression_of.set, None, None)

    _ElementMap.update({
        __id.name() : __id,
        __visualisation_method.name() : __visualisation_method,
        __associated_image.name() : __associated_image,
        __associated_roi.name() : __associated_roi,
        __depicts_expression_of.name() : __depicts_expression_of
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
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 264, 1)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element id uses Python identifier id
    __id = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'id'), 'id', '__httpwww_example_orgphisSchema_Roi_id', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 266, 3), )

    
    id = property(__id.value, __id.set, None, None)

    
    # Element associated_image uses Python identifier associated_image
    __associated_image = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'associated_image'), 'associated_image', '__httpwww_example_orgphisSchema_Roi_associated_image', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 267, 3), )

    
    associated_image = property(__associated_image.value, __associated_image.set, None, None)

    
    # Element associated_channel uses Python identifier associated_channel
    __associated_channel = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'associated_channel'), 'associated_channel', '__httpwww_example_orgphisSchema_Roi_associated_channel', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 268, 3), )

    
    associated_channel = property(__associated_channel.value, __associated_channel.set, None, None)

    
    # Element coordinates uses Python identifier coordinates
    __coordinates = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'coordinates'), 'coordinates', '__httpwww_example_orgphisSchema_Roi_coordinates', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 269, 3), )

    
    coordinates = property(__coordinates.value, __coordinates.set, None, None)

    
    # Element depicted_anatomical_structure uses Python identifier depicted_anatomical_structure
    __depicted_anatomical_structure = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'depicted_anatomical_structure'), 'depicted_anatomical_structure', '__httpwww_example_orgphisSchema_Roi_depicted_anatomical_structure', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 272, 3), )

    
    depicted_anatomical_structure = property(__depicted_anatomical_structure.value, __depicted_anatomical_structure.set, None, None)

    
    # Element is_expression_pattern uses Python identifier is_expression_pattern
    __is_expression_pattern = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'is_expression_pattern'), 'is_expression_pattern', '__httpwww_example_orgphisSchema_Roi_is_expression_pattern', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 273, 3), )

    
    is_expression_pattern = property(__is_expression_pattern.value, __is_expression_pattern.set, None, None)

    
    # Element abnormality_in_anatomical_structure uses Python identifier abnormality_in_anatomical_structure
    __abnormality_in_anatomical_structure = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'abnormality_in_anatomical_structure'), 'abnormality_in_anatomical_structure', '__httpwww_example_orgphisSchema_Roi_abnormality_in_anatomical_structure', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 274, 3), )

    
    abnormality_in_anatomical_structure = property(__abnormality_in_anatomical_structure.value, __abnormality_in_anatomical_structure.set, None, None)

    
    # Element phenotype_annotations uses Python identifier phenotype_annotations
    __phenotype_annotations = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'phenotype_annotations'), 'phenotype_annotations', '__httpwww_example_orgphisSchema_Roi_phenotype_annotations', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 275, 3), )

    
    phenotype_annotations = property(__phenotype_annotations.value, __phenotype_annotations.set, None, None)

    
    # Element observations uses Python identifier observations
    __observations = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'observations'), 'observations', '__httpwww_example_orgphisSchema_Roi_observations', False, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 276, 3), )

    
    observations = property(__observations.value, __observations.set, None, None)

    _ElementMap.update({
        __id.name() : __id,
        __associated_image.name() : __associated_image,
        __associated_channel.name() : __associated_channel,
        __coordinates.name() : __coordinates,
        __depicted_anatomical_structure.name() : __depicted_anatomical_structure,
        __is_expression_pattern.name() : __is_expression_pattern,
        __abnormality_in_anatomical_structure.name() : __abnormality_in_anatomical_structure,
        __phenotype_annotations.name() : __phenotype_annotations,
        __observations.name() : __observations
    })
    _AttributeMap.update({
        
    })
Namespace.addCategoryObject('typeBinding', u'Roi', Roi)


# Complex type [anonymous] with content type ELEMENT_ONLY
class CTD_ANON (pyxb.binding.basis.complexTypeDefinition):
    """Complex type [anonymous] with content type ELEMENT_ONLY"""
    _TypeDefinition = None
    _ContentTypeTag = pyxb.binding.basis.complexTypeDefinition._CT_ELEMENT_ONLY
    _Abstract = False
    _ExpandedName = None
    _XSDLocation = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 282, 2)
    _ElementMap = {}
    _AttributeMap = {}
    # Base type is pyxb.binding.datatypes.anyType
    
    # Element image uses Python identifier image
    __image = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'image'), 'image', '__httpwww_example_orgphisSchema_CTD_ANON_image', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 284, 4), )

    
    image = property(__image.value, __image.set, None, None)

    
    # Element channel uses Python identifier channel
    __channel = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'channel'), 'channel', '__httpwww_example_orgphisSchema_CTD_ANON_channel', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 285, 4), )

    
    channel = property(__channel.value, __channel.set, None, None)

    
    # Element roi uses Python identifier roi
    __roi = pyxb.binding.content.ElementDeclaration(pyxb.namespace.ExpandedName(None, u'roi'), 'roi', '__httpwww_example_orgphisSchema_CTD_ANON_roi', True, pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 286, 4), )

    
    roi = property(__roi.value, __roi.set, None, None)

    _ElementMap.update({
        __image.name() : __image,
        __channel.name() : __channel,
        __roi.name() : __roi
    })
    _AttributeMap.update({
        
    })



Doc = pyxb.binding.basis.element(pyxb.namespace.ExpandedName(Namespace, u'Doc'), CTD_ANON, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 281, 1))
Namespace.addCategoryObject('elementBinding', Doc.name().localName(), Doc)



ZygArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), Zygosity, scope=ZygArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 28, 3)))

def _BuildAutomaton ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton
    del _BuildAutomaton
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=None, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 28, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(ZygArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 28, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=None)
ZygArray._Automaton = _BuildAutomaton()




ImageTypeArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), ImageType, scope=ImageTypeArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 47, 3)))

def _BuildAutomaton_ ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_
    del _BuildAutomaton_
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageTypeArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 47, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
ImageTypeArray._Automaton = _BuildAutomaton_()




Age._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'age_since_birth'), pyxb.binding.datatypes.float, scope=Age, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 53, 3)))

Age._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'embryonic_age'), pyxb.binding.datatypes.float, scope=Age, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 54, 3)))

def _BuildAutomaton_2 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_2
    del _BuildAutomaton_2
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Age._UseForTag(pyxb.namespace.ExpandedName(None, u'age_since_birth')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 53, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Age._UseForTag(pyxb.namespace.ExpandedName(None, u'embryonic_age')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 54, 3))
    st_1 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_1)
    transitions = []
    st_0._set_transitionSet(transitions)
    transitions = []
    st_1._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Age._Automaton = _BuildAutomaton_2()




PositiveIntArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), PositiveInt, scope=PositiveIntArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 65, 3)))

def _BuildAutomaton_3 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_3
    del _BuildAutomaton_3
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(PositiveIntArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 65, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
PositiveIntArray._Automaton = _BuildAutomaton_3()




IntArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), pyxb.binding.datatypes.int, scope=IntArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 71, 3)))

def _BuildAutomaton_4 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_4
    del _BuildAutomaton_4
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(IntArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 71, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
IntArray._Automaton = _BuildAutomaton_4()




FloatArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), pyxb.binding.datatypes.float, scope=FloatArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 77, 3)))

def _BuildAutomaton_5 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_5
    del _BuildAutomaton_5
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(FloatArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 77, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
FloatArray._Automaton = _BuildAutomaton_5()




StringArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), pyxb.binding.datatypes.string, scope=StringArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 83, 3)))

def _BuildAutomaton_6 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_6
    del _BuildAutomaton_6
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(StringArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 83, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
StringArray._Automaton = _BuildAutomaton_6()




PercentArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), Percentage, scope=PercentArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 95, 3)))

def _BuildAutomaton_7 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_7
    del _BuildAutomaton_7
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(PercentArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 95, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
PercentArray._Automaton = _BuildAutomaton_7()




Dimensions._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_depth'), PositiveInt, scope=Dimensions, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 101, 3)))

Dimensions._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_height'), PositiveInt, scope=Dimensions, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 102, 3)))

Dimensions._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_width'), PositiveInt, scope=Dimensions, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 103, 3)))

def _BuildAutomaton_9 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_9
    del _BuildAutomaton_9
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 101, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Dimensions._UseForTag(pyxb.namespace.ExpandedName(None, u'image_depth')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 101, 3))
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
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Dimensions._UseForTag(pyxb.namespace.ExpandedName(None, u'image_height')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 102, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_11 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_11
    del _BuildAutomaton_11
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Dimensions._UseForTag(pyxb.namespace.ExpandedName(None, u'image_width')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 103, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_8 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_8
    del _BuildAutomaton_8
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 101, 3))
    counters.add(cc_0)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_9())
    sub_automata.append(_BuildAutomaton_10())
    sub_automata.append(_BuildAutomaton_11())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 100, 2)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Dimensions._Automaton = _BuildAutomaton_8()




Coordinates._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'z_coordinates'), PercentArray, scope=Coordinates, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 109, 3)))

Coordinates._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'y_coordinates'), PercentArray, scope=Coordinates, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 110, 3)))

Coordinates._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'x_coordinates'), PercentArray, scope=Coordinates, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 111, 3)))

def _BuildAutomaton_13 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_13
    del _BuildAutomaton_13
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 109, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Coordinates._UseForTag(pyxb.namespace.ExpandedName(None, u'z_coordinates')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 109, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_14 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_14
    del _BuildAutomaton_14
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Coordinates._UseForTag(pyxb.namespace.ExpandedName(None, u'y_coordinates')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 110, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_15 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_15
    del _BuildAutomaton_15
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Coordinates._UseForTag(pyxb.namespace.ExpandedName(None, u'x_coordinates')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 111, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_12 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_12
    del _BuildAutomaton_12
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 109, 3))
    counters.add(cc_0)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_13())
    sub_automata.append(_BuildAutomaton_14())
    sub_automata.append(_BuildAutomaton_15())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 108, 2)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Coordinates._Automaton = _BuildAutomaton_12()




GenomicLocation._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'start_pos'), pyxb.binding.datatypes.long, scope=GenomicLocation, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 124, 3)))

GenomicLocation._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'end_pos'), pyxb.binding.datatypes.long, scope=GenomicLocation, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 125, 3)))

GenomicLocation._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'chromosone'), pyxb.binding.datatypes.string, scope=GenomicLocation, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 126, 3)))

GenomicLocation._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'strand'), Strand, scope=GenomicLocation, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 127, 3)))

def _BuildAutomaton_17 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_17
    del _BuildAutomaton_17
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 124, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenomicLocation._UseForTag(pyxb.namespace.ExpandedName(None, u'start_pos')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 124, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_18 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_18
    del _BuildAutomaton_18
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 125, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenomicLocation._UseForTag(pyxb.namespace.ExpandedName(None, u'end_pos')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 125, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 126, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenomicLocation._UseForTag(pyxb.namespace.ExpandedName(None, u'chromosone')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 126, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 127, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenomicLocation._UseForTag(pyxb.namespace.ExpandedName(None, u'strand')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 127, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_16 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_16
    del _BuildAutomaton_16
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 124, 3))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 125, 3))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 126, 3))
    counters.add(cc_2)
    cc_3 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 127, 3))
    counters.add(cc_3)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_17())
    sub_automata.append(_BuildAutomaton_18())
    sub_automata.append(_BuildAutomaton_19())
    sub_automata.append(_BuildAutomaton_20())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 123, 2)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=None)
GenomicLocation._Automaton = _BuildAutomaton_16()




GenomicLocationArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), GenomicLocation, scope=GenomicLocationArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 132, 3)))

def _BuildAutomaton_21 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_21
    del _BuildAutomaton_21
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=None, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 132, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenomicLocationArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 132, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=None)
GenomicLocationArray._Automaton = _BuildAutomaton_21()




ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_url'), pyxb.binding.datatypes.string, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 138, 3)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_context_url'), pyxb.binding.datatypes.string, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 139, 3)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_dimensions'), Dimensions, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 140, 3)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'machine'), pyxb.binding.datatypes.string, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 141, 3)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_generated_by'), Link, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 142, 3)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_processed_by'), LinkArray, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 143, 3)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'organism_generated_by'), Link, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 144, 3)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'host'), Link, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 145, 3)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'imaging_method'), OntologyTermArray, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 146, 3)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'sample_preparation'), OntologyTermArray, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 147, 3)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_type'), ImageTypeArray, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 148, 3)))

ImageDescription._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'sample_type'), SampleType, scope=ImageDescription, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 149, 3)))

def _BuildAutomaton_23 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_23
    del _BuildAutomaton_23
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'image_url')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 138, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_24 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_24
    del _BuildAutomaton_24
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 139, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'image_context_url')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 139, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_25 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_25
    del _BuildAutomaton_25
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'image_dimensions')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 140, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 141, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'machine')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 141, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_27 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_27
    del _BuildAutomaton_27
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'image_generated_by')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 142, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_28 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_28
    del _BuildAutomaton_28
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 143, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'image_processed_by')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 143, 3))
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
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'organism_generated_by')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 144, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_30 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_30
    del _BuildAutomaton_30
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'host')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 145, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_31 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_31
    del _BuildAutomaton_31
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 146, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'imaging_method')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 146, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 147, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'sample_preparation')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 147, 3))
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
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'image_type')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 148, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_34 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_34
    del _BuildAutomaton_34
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(ImageDescription._UseForTag(pyxb.namespace.ExpandedName(None, u'sample_type')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 149, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 139, 3))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 141, 3))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 143, 3))
    counters.add(cc_2)
    cc_3 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 146, 3))
    counters.add(cc_3)
    cc_4 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 147, 3))
    counters.add(cc_4)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_23())
    sub_automata.append(_BuildAutomaton_24())
    sub_automata.append(_BuildAutomaton_25())
    sub_automata.append(_BuildAutomaton_26())
    sub_automata.append(_BuildAutomaton_27())
    sub_automata.append(_BuildAutomaton_28())
    sub_automata.append(_BuildAutomaton_29())
    sub_automata.append(_BuildAutomaton_30())
    sub_automata.append(_BuildAutomaton_31())
    sub_automata.append(_BuildAutomaton_32())
    sub_automata.append(_BuildAutomaton_33())
    sub_automata.append(_BuildAutomaton_34())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 137, 2)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
ImageDescription._Automaton = _BuildAutomaton_22()




Organism._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'age'), Age, scope=Organism, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 155, 3)))

Organism._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'taxon'), pyxb.binding.datatypes.string, scope=Organism, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 156, 3)))

Organism._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'sex'), Sex, scope=Organism, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 157, 3)))

Organism._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'ncbi_taxon_id'), pyxb.binding.datatypes.string, scope=Organism, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 158, 3)))

Organism._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'stage'), OntologyTerm, scope=Organism, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 159, 3)))

Organism._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'organism_id'), pyxb.binding.datatypes.string, scope=Organism, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 160, 3)))

def _BuildAutomaton_36 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_36
    del _BuildAutomaton_36
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 155, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Organism._UseForTag(pyxb.namespace.ExpandedName(None, u'age')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 155, 3))
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
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Organism._UseForTag(pyxb.namespace.ExpandedName(None, u'taxon')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 156, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_38 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_38
    del _BuildAutomaton_38
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 157, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Organism._UseForTag(pyxb.namespace.ExpandedName(None, u'sex')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 157, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_39 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_39
    del _BuildAutomaton_39
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 158, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Organism._UseForTag(pyxb.namespace.ExpandedName(None, u'ncbi_taxon_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 158, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_40 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_40
    del _BuildAutomaton_40
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 159, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Organism._UseForTag(pyxb.namespace.ExpandedName(None, u'stage')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 159, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_41 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_41
    del _BuildAutomaton_41
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 160, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Organism._UseForTag(pyxb.namespace.ExpandedName(None, u'organism_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 160, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 155, 3))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 157, 3))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 158, 3))
    counters.add(cc_2)
    cc_3 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 159, 3))
    counters.add(cc_3)
    cc_4 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 160, 3))
    counters.add(cc_4)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_36())
    sub_automata.append(_BuildAutomaton_37())
    sub_automata.append(_BuildAutomaton_38())
    sub_automata.append(_BuildAutomaton_39())
    sub_automata.append(_BuildAutomaton_40())
    sub_automata.append(_BuildAutomaton_41())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 154, 2)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Organism._Automaton = _BuildAutomaton_35()




OntologyTerm._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'termLabel'), pyxb.binding.datatypes.string, scope=OntologyTerm, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 166, 3)))

OntologyTerm._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'termId'), pyxb.binding.datatypes.string, scope=OntologyTerm, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 167, 3)))

def _BuildAutomaton_43 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_43
    del _BuildAutomaton_43
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(OntologyTerm._UseForTag(pyxb.namespace.ExpandedName(None, u'termLabel')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 166, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_44 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_44
    del _BuildAutomaton_44
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(OntologyTerm._UseForTag(pyxb.namespace.ExpandedName(None, u'termId')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 167, 3))
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
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_43())
    sub_automata.append(_BuildAutomaton_44())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 165, 2)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
OntologyTerm._Automaton = _BuildAutomaton_42()




OntologyTermArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), OntologyTerm, scope=OntologyTermArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 172, 3)))

def _BuildAutomaton_45 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_45
    del _BuildAutomaton_45
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(OntologyTermArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 172, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
OntologyTermArray._Automaton = _BuildAutomaton_45()




Annotation._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'ontology_term'), OntologyTerm, scope=Annotation, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 192, 3)))

Annotation._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'annotation_freetext'), pyxb.binding.datatypes.string, scope=Annotation, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 193, 3)))

Annotation._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'annotationMode'), AnnotationMode, scope=Annotation, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 194, 3)))

def _BuildAutomaton_47 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_47
    del _BuildAutomaton_47
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 192, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Annotation._UseForTag(pyxb.namespace.ExpandedName(None, u'ontology_term')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 192, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 193, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Annotation._UseForTag(pyxb.namespace.ExpandedName(None, u'annotation_freetext')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 193, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 194, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Annotation._UseForTag(pyxb.namespace.ExpandedName(None, u'annotationMode')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 194, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 192, 3))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 193, 3))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 194, 3))
    counters.add(cc_2)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_47())
    sub_automata.append(_BuildAutomaton_48())
    sub_automata.append(_BuildAutomaton_49())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 191, 2)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=None)
Annotation._Automaton = _BuildAutomaton_46()




AnnotationArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), Annotation, scope=AnnotationArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 199, 3)))

def _BuildAutomaton_50 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_50
    del _BuildAutomaton_50
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(AnnotationArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 199, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
AnnotationArray._Automaton = _BuildAutomaton_50()




GenotypeComponent._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'gene_id'), pyxb.binding.datatypes.string, scope=GenotypeComponent, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 205, 3)))

GenotypeComponent._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'gene_symbol'), pyxb.binding.datatypes.string, scope=GenotypeComponent, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 206, 3)))

GenotypeComponent._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'genetic_feature_id'), pyxb.binding.datatypes.string, scope=GenotypeComponent, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 207, 3)))

GenotypeComponent._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'genetic_feature_symbol'), pyxb.binding.datatypes.string, scope=GenotypeComponent, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 208, 3)))

GenotypeComponent._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'genetic_feature_ensembl_id'), pyxb.binding.datatypes.string, scope=GenotypeComponent, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 209, 3)))

GenotypeComponent._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'genomic_location'), GenomicLocation, scope=GenotypeComponent, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 210, 3)))

GenotypeComponent._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'zygosity'), Zygosity, scope=GenotypeComponent, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 211, 3)))

def _BuildAutomaton_52 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_52
    del _BuildAutomaton_52
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 205, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenotypeComponent._UseForTag(pyxb.namespace.ExpandedName(None, u'gene_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 205, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 206, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenotypeComponent._UseForTag(pyxb.namespace.ExpandedName(None, u'gene_symbol')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 206, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 207, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenotypeComponent._UseForTag(pyxb.namespace.ExpandedName(None, u'genetic_feature_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 207, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 208, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenotypeComponent._UseForTag(pyxb.namespace.ExpandedName(None, u'genetic_feature_symbol')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 208, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_56 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_56
    del _BuildAutomaton_56
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 209, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenotypeComponent._UseForTag(pyxb.namespace.ExpandedName(None, u'genetic_feature_ensembl_id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 209, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_57 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_57
    del _BuildAutomaton_57
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 210, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenotypeComponent._UseForTag(pyxb.namespace.ExpandedName(None, u'genomic_location')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 210, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 211, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(GenotypeComponent._UseForTag(pyxb.namespace.ExpandedName(None, u'zygosity')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 211, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 205, 3))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 206, 3))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 207, 3))
    counters.add(cc_2)
    cc_3 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 208, 3))
    counters.add(cc_3)
    cc_4 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 209, 3))
    counters.add(cc_4)
    cc_5 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 210, 3))
    counters.add(cc_5)
    cc_6 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 211, 3))
    counters.add(cc_6)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_52())
    sub_automata.append(_BuildAutomaton_53())
    sub_automata.append(_BuildAutomaton_54())
    sub_automata.append(_BuildAutomaton_55())
    sub_automata.append(_BuildAutomaton_56())
    sub_automata.append(_BuildAutomaton_57())
    sub_automata.append(_BuildAutomaton_58())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 204, 2)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=None)
GenotypeComponent._Automaton = _BuildAutomaton_51()




Genotype._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), GenotypeComponent, scope=Genotype, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 217, 3)))

def _BuildAutomaton_59 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_59
    del _BuildAutomaton_59
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Genotype._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 217, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Genotype._Automaton = _BuildAutomaton_59()




Link._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'display_name'), pyxb.binding.datatypes.string, scope=Link, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 223, 3)))

Link._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'url'), pyxb.binding.datatypes.string, scope=Link, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 224, 3)))

def _BuildAutomaton_61 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_61
    del _BuildAutomaton_61
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Link._UseForTag(pyxb.namespace.ExpandedName(None, u'display_name')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 223, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_62 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_62
    del _BuildAutomaton_62
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 224, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Link._UseForTag(pyxb.namespace.ExpandedName(None, u'url')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 224, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 224, 3))
    counters.add(cc_0)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_61())
    sub_automata.append(_BuildAutomaton_62())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 222, 2)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Link._Automaton = _BuildAutomaton_60()




LinkArray._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'el'), Link, scope=LinkArray, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 229, 3)))

def _BuildAutomaton_63 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_63
    del _BuildAutomaton_63
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(LinkArray._UseForTag(pyxb.namespace.ExpandedName(None, u'el')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 229, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
LinkArray._Automaton = _BuildAutomaton_63()




Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'id'), pyxb.binding.datatypes.string, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 238, 3)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'organism'), Organism, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 239, 3)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image_description'), ImageDescription, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 240, 3)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'mutant_genotype_traits'), Genotype, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 241, 3)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'conditions'), StringArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 242, 3)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'observations'), StringArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 243, 3)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'associated_roi'), StringArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 244, 3)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'associated_channel'), StringArray, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 245, 3)))

Image._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'depicted_anatomical_structure'), Annotation, scope=Image, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 246, 3)))

def _BuildAutomaton_65 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_65
    del _BuildAutomaton_65
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 238, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_66 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_66
    del _BuildAutomaton_66
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'organism')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 239, 3))
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
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'image_description')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 240, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 241, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'mutant_genotype_traits')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 241, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_69 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_69
    del _BuildAutomaton_69
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 242, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'conditions')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 242, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_70 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_70
    del _BuildAutomaton_70
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 243, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'observations')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 243, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_71 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_71
    del _BuildAutomaton_71
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 244, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'associated_roi')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 244, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 245, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'associated_channel')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 245, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 246, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Image._UseForTag(pyxb.namespace.ExpandedName(None, u'depicted_anatomical_structure')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 246, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 241, 3))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 242, 3))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 243, 3))
    counters.add(cc_2)
    cc_3 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 244, 3))
    counters.add(cc_3)
    cc_4 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 245, 3))
    counters.add(cc_4)
    cc_5 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 246, 3))
    counters.add(cc_5)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_65())
    sub_automata.append(_BuildAutomaton_66())
    sub_automata.append(_BuildAutomaton_67())
    sub_automata.append(_BuildAutomaton_68())
    sub_automata.append(_BuildAutomaton_69())
    sub_automata.append(_BuildAutomaton_70())
    sub_automata.append(_BuildAutomaton_71())
    sub_automata.append(_BuildAutomaton_72())
    sub_automata.append(_BuildAutomaton_73())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 237, 2)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Image._Automaton = _BuildAutomaton_64()




Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'id'), pyxb.binding.datatypes.string, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 254, 3)))

Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'visualisation_method'), OntologyTermArray, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 255, 3)))

Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'associated_image'), pyxb.binding.datatypes.string, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 256, 3)))

Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'associated_roi'), StringArray, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 257, 3)))

Channel._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'depicts_expression_of'), GenotypeComponent, scope=Channel, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 258, 3)))

def _BuildAutomaton_75 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_75
    del _BuildAutomaton_75
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 254, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_76 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_76
    del _BuildAutomaton_76
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 255, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'visualisation_method')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 255, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_77 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_77
    del _BuildAutomaton_77
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'associated_image')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 256, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_78 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_78
    del _BuildAutomaton_78
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 257, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'associated_roi')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 257, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_79 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_79
    del _BuildAutomaton_79
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 258, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Channel._UseForTag(pyxb.namespace.ExpandedName(None, u'depicts_expression_of')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 258, 3))
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
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 255, 3))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 257, 3))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 258, 3))
    counters.add(cc_2)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_75())
    sub_automata.append(_BuildAutomaton_76())
    sub_automata.append(_BuildAutomaton_77())
    sub_automata.append(_BuildAutomaton_78())
    sub_automata.append(_BuildAutomaton_79())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 253, 2)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Channel._Automaton = _BuildAutomaton_74()




Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'id'), pyxb.binding.datatypes.string, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 266, 3)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'associated_image'), pyxb.binding.datatypes.string, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 267, 3)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'associated_channel'), StringArray, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 268, 3)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'coordinates'), Coordinates, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 269, 3)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'depicted_anatomical_structure'), AnnotationArray, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 272, 3)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'is_expression_pattern'), YesNo, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 273, 3)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'abnormality_in_anatomical_structure'), AnnotationArray, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 274, 3)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'phenotype_annotations'), AnnotationArray, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 275, 3)))

Roi._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'observations'), StringArray, scope=Roi, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 276, 3)))

def _BuildAutomaton_81 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_81
    del _BuildAutomaton_81
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'id')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 266, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_82 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_82
    del _BuildAutomaton_82
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'associated_image')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 267, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_83 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_83
    del _BuildAutomaton_83
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 268, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'associated_channel')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 268, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_84 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_84
    del _BuildAutomaton_84
    import pyxb.utils.fac as fac

    counters = set()
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'coordinates')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 269, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=st_0)

def _BuildAutomaton_85 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_85
    del _BuildAutomaton_85
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 272, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'depicted_anatomical_structure')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 272, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_86 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_86
    del _BuildAutomaton_86
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 273, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'is_expression_pattern')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 273, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_87 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_87
    del _BuildAutomaton_87
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 274, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'abnormality_in_anatomical_structure')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 274, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_88 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_88
    del _BuildAutomaton_88
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 275, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'phenotype_annotations')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 275, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_89 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_89
    del _BuildAutomaton_89
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 276, 3))
    counters.add(cc_0)
    states = []
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(Roi._UseForTag(pyxb.namespace.ExpandedName(None, u'observations')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 276, 3))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    transitions = []
    transitions.append(fac.Transition(st_0, [
        fac.UpdateInstruction(cc_0, True) ]))
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, True, containing_state=st_0)

def _BuildAutomaton_80 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_80
    del _BuildAutomaton_80
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 268, 3))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 272, 3))
    counters.add(cc_1)
    cc_2 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 273, 3))
    counters.add(cc_2)
    cc_3 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 274, 3))
    counters.add(cc_3)
    cc_4 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 275, 3))
    counters.add(cc_4)
    cc_5 = fac.CounterCondition(min=0L, max=1L, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 276, 3))
    counters.add(cc_5)
    states = []
    sub_automata = []
    sub_automata.append(_BuildAutomaton_81())
    sub_automata.append(_BuildAutomaton_82())
    sub_automata.append(_BuildAutomaton_83())
    sub_automata.append(_BuildAutomaton_84())
    sub_automata.append(_BuildAutomaton_85())
    sub_automata.append(_BuildAutomaton_86())
    sub_automata.append(_BuildAutomaton_87())
    sub_automata.append(_BuildAutomaton_88())
    sub_automata.append(_BuildAutomaton_89())
    final_update = set()
    symbol = pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 265, 2)
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=True)
    st_0._set_subAutomata(*sub_automata)
    states.append(st_0)
    transitions = []
    st_0._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
Roi._Automaton = _BuildAutomaton_80()




CTD_ANON._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'image'), Image, scope=CTD_ANON, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 284, 4)))

CTD_ANON._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'channel'), Channel, scope=CTD_ANON, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 285, 4)))

CTD_ANON._AddElement(pyxb.binding.basis.element(pyxb.namespace.ExpandedName(None, u'roi'), Roi, scope=CTD_ANON, location=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 286, 4)))

def _BuildAutomaton_90 ():
    # Remove this helper function from the namespace after it is invoked
    global _BuildAutomaton_90
    del _BuildAutomaton_90
    import pyxb.utils.fac as fac

    counters = set()
    cc_0 = fac.CounterCondition(min=0L, max=None, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 285, 4))
    counters.add(cc_0)
    cc_1 = fac.CounterCondition(min=0L, max=None, metadata=pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 286, 4))
    counters.add(cc_1)
    states = []
    final_update = set()
    symbol = pyxb.binding.content.ElementUse(CTD_ANON._UseForTag(pyxb.namespace.ExpandedName(None, u'image')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 284, 4))
    st_0 = fac.State(symbol, is_initial=True, final_update=final_update, is_unordered_catenation=False)
    states.append(st_0)
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_0, False))
    symbol = pyxb.binding.content.ElementUse(CTD_ANON._UseForTag(pyxb.namespace.ExpandedName(None, u'channel')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 285, 4))
    st_1 = fac.State(symbol, is_initial=False, final_update=final_update, is_unordered_catenation=False)
    states.append(st_1)
    final_update = set()
    final_update.add(fac.UpdateInstruction(cc_1, False))
    symbol = pyxb.binding.content.ElementUse(CTD_ANON._UseForTag(pyxb.namespace.ExpandedName(None, u'roi')), pyxb.utils.utility.Location('/repos/PhenoImageShare/PhIS/source/main/resources/phisSchema.xsd', 286, 4))
    st_2 = fac.State(symbol, is_initial=False, final_update=final_update, is_unordered_catenation=False)
    states.append(st_2)
    transitions = []
    transitions.append(fac.Transition(st_0, [
         ]))
    transitions.append(fac.Transition(st_1, [
         ]))
    transitions.append(fac.Transition(st_2, [
         ]))
    st_0._set_transitionSet(transitions)
    transitions = []
    transitions.append(fac.Transition(st_1, [
        fac.UpdateInstruction(cc_0, True) ]))
    transitions.append(fac.Transition(st_2, [
        fac.UpdateInstruction(cc_0, False) ]))
    st_1._set_transitionSet(transitions)
    transitions = []
    transitions.append(fac.Transition(st_2, [
        fac.UpdateInstruction(cc_1, True) ]))
    st_2._set_transitionSet(transitions)
    return fac.Automaton(states, counters, False, containing_state=None)
CTD_ANON._Automaton = _BuildAutomaton_90()


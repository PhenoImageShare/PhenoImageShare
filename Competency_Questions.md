# Pheno Image Share - competency questions

This document is for recording: the basic queries we intended to support; what the results of queries should communicate.

## Simplifying assumptions:

1. We will only support images with one sample per image and one genotype per sample.

## User queries
1. Find images illustrating the expression pattern of gene/transgene X.<br>
_Entry point:_ Search for a gene/transgene by name (include synonyms?) - with auto-suggest.<br>
Then look for images of the expression pattern of that gene/transgene.<br>
[Example SOLR query:](http://wwwdev.ebi.ac.uk/mi/phis/images/select?q=expressed_gf_bag:Sesn3)<br>
Optionally refine by stage &/or anatomical structure name with results including those grouped by subsumption under selected term.<br>
Optionally refine by image type (2D, 3D, confocal…).<br>
Optionally refine by magnification level.
 
2. Find images illustrating the phenotypes due a specific allele X<br>
_Entry point:_ Search for a allele by name (include synonyms?) - with auto-suggest<br> 
Then look for  images of phenotypes due to genotypes that include this allele.<br>
[Example SOLR query](http://wwwdev.ebi.ac.uk/mi/phis/images/select?q=genetic_feature_name:"Sesn3<tm1a(EUCOMM)Wtsi>")<br>
Optionally refine by mutation type (knockout)<br>
Optionally refine by stage &/or anatomical structure name with results including those grouped by subsumption under selected term<br>
Optionally refine by magnification level.

3.  Find images illustrating the phenotypes due to mutations in gene X <br>
__Entry point:__ Search for a gene by name (include synonyms?) - with auto-suggest<br>
Then look for  images of phenotypes due to genotypes that include alleles of that gene.<br>
[Example SOLR query](http://wwwdev.ebi.ac.uk/mi/phis/images/select?q=gene_name:Ube3b) Note - this will only cover parent genes of elements of genotype, as parent genes for expressed features are stored separately in expressed\_gf\_bag - attached to whole image.<br>
Optionally refine by mutation type (knockout)<br>
	Not currently possible.  Note, may not be so useful to biologists if > 1 genotype element.<br>
Optionally refine by stage &/or anatomical structure name with results including those grouped by subsumption under selected term.<br>
Possible with simple intersection on anatomy/stage indexes (will support subsumption once transitive closure is stored).
[Example query](http://wwwdev.ebi.ac.uk/mi/phis/images/select?q=gene_name:Ube3b&fq=anatomy_term:eye&fq=age:[80%20TO%20100])
Optionally refine by magnification level.<br>
			Not currently stored.  But could be.<br>
Optionally refine by image type:<br>
[Example query](http://wwwdev.ebi.ac.uk/mi/phis/images/select?q=gene_name:Ube3b&fq=image_type:xray)>br>

4. Find images of phenotypes and gene expression in region defined by painting on an image:<br>
__Entry point:__ Click or draw on a region of an image (Perhaps drawing on region makes more sense here as defined limits of region)<br>
[Starting point SOLR query, specifying co-ordinates](http://wwwdev.ebi.ac.uk/mi/phis/rois/select?q=y_coordinates:[0%20TO%20100]&fq=x_coordinates:[0%20TO%20100])<br>
Find images of gene expression in this region (results based on spatial reasoning)<br>
Find images of gene expression in spatially related structure<br>
adjacent; near to; adjacent along some axis.<br>
Find images of phenotypes in this region (results based on spatial reasoning).<br>


5. Find images of phenotypes and gene expression in some named anatomical structure:<br>
6. Right now won't work becuase not combined bag.
__Entry point:__ Search for a species, then search for an anatomical structure by auto-suggest (including synonyms).<br> Provide feedback to user on terms found by autocomplete (e.g. definition; defining images).<br>
Find images of gene expression in this structure  (include results found by subsumption)<br>
Find images of phenotypes in this structure (include results found by subsumption)<br> 
[Example query](http://wwwdev.ebi.ac.uk/mi/phis/images/select?q=anatomy_term:brain&fq=phenotype_term:*)

52. Find images showing expression in some anatomy structure X<br>
__Entry point:__ Search for a species, then search for an anatomical structure by auto-suggest (including synonyms). <br> Provide feedback to user on terms found by autocomplete (e.g. definition; defining images).<br>
[Example Query](http://wwwdev.ebi.ac.uk/mi/phis/images/select?q=expressed_anatomy_bag:ear) 

55. Find images depicting anatomical structure X<br>
__Entry point:__ Search for a species, then search for an anatomical structure by auto-suggest (including synonyms). <br> Provide feedback to user on terms found by autocomplete (e.g. definition; defining images).<br>
[Example query](http://wwwdev.ebi.ac.uk/mi/phis/images/select?q=anatomy_term:brain)

57. Find images depicting phenotypes in anatomical structure X<br>
[__Entry point:__] Search for a species, then search for an anatomical structure by auto-suggest (including synonyms).<br> Provide feedback to user on terms found by autocomplete (e.g. definition; defining images).<br>
Note - anatomy terms inferred from phenotype terms currently go into a general inferred terms bag - which includes some terms inferred from expression (Tracer mapping).   It is useful to mark inferred terms in some way, but phenotype and expression should still be kept separate.  In some cases for KOMP2 there are also MA terms associated with images - where these can be clearly assigned to phenotype or expression they should be.  But if this is not possible, they should live only in an aggregated index of all anatomy terms for image.

6. Search on phenotype term annotation.
http://wwwdev.ebi.ac.uk/mi/phis/rois/select?q=phenotype_term:"hard cataracts"

7. Find similar phenotypes to phenotype X<br>
Semantic similarity<br>
Spatial similarity<br>

8. Find images illustrating the expression patterns of insertions in some specified genome region.
__Entry point:__
[Example query for  expressed features between locations x and Y](http://wwwdev.ebi.ac.uk/mi/phis/channels/select?q=*:*&fq=chromosome:12&fq=strand:%22-%22&insertion_site:[20000000%20TO%203000000]). Currently this just does insertions (enhancer traps from Tracer). This will be extended to include any expressed features in the specified region. It should be possible to do a similar search for mutations at a specific location but at the moment we don’t have any entries with mutations at location x.

## Spec for display of results

### potential levels of detail:

* Image montage
* Large images only?
* Table with thumbnails
* Image report
 * More easily allows information to be displayed so that connection between elements can be made clear.


### Questions: 
1. Queries will all return tables that can be further refined using the faceted browsing capabilities of SOLR. As our ability to provide structured information in a table is limited - should we consider providing single image report pages with more detailed and structured content?
2. Should ROIs be used simply for search purposes, or do we have a good use case for displaying them?
3. A large number of fields have been suggested -  too many to display in a default results table.  Should we allow users to configure their display, adding extra columns?
4. Some fields May have multiple components. If displayed in a table, have multiple per box?

## Results display competency questions.  

Users should be able to use the displayed results to rapidly assess:

* What does the image look like? 
 * Thumbnail essential  <- +Higher resolution popup?
* What does the whole image depict? (will require display of multiple fields)
 * e.g. whole, male mouse embryo at TS3; section through the adrenal gland of a 5 day old female mouse.  <- All indexed on whole image e.g. anatomy_term:brain 
* What gene/transgene expression is depicted? *
* Which channel/colour corresponds to which expression pattern?  
 * in channel document
* What anatomy terms has the expression pattern been annotated with? *
 * in channel document
* What is the genotype?  
 * Stored on whole image
* What are the phenotype annotations? *
 * stored on whole image.  For ROIs - each ROI has co-ords and annotations.
* What is the image source?
 * On image doc
* Is the image 2D or 3D?
 * TBA - depends on mappings to imaging ontology FBbi
* What techniques were used to collect the image?
 * TBA - depends on mappings to imaging ontology FBbi
 * 


Lots of other fields suggested - are they all essential for display?


### Some notes on queries:

Tracer:  These things are enhancer traps - can we have a general class for them?  Is there something in SO.  DOS to check.

Look at what from image ontology to map Tracer images to.

Notes on SOLR indexes:
Expressed features - stored denormalized: as bag on whole image and as individual features associated with each channel.





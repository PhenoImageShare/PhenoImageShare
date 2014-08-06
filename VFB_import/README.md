## Code etc for importing images and associated expression annotation from VFB

## Strategy:

* A PyXB python datamodel is automatically generated from the XSD schema spec.
* Annotations are pulled from VFB CHADO (via views), or directly from the VFB OWL knowledgeBase - depending on the dataset:
  * Clones and individual neurons - annotations pulled from OWL
  * Expression patterns - simplest strategy is to pull from DB, advantage of converting to OWL first is 

## Code etc for importing images and associated expression annotation from VFB

## Strategy:

* A PyXB python datamodel is automatically generated from the XSD schema spec.
* Annotations are pulled from VFB CHADO (via views), or directly from the VFB OWL knowledgeBase - depending on the dataset:
  * Clones and individual neurons - annotations pulled from OWL (using Jython over Brain/OWLtools)
  * Expression patterns - simplest strategy is to pull from DB, but for a unified/sustainable code base perhaps better to go from SQL -> OWL first [see draft code on VFB repo](https://github.com/VirtualFlyBrain/VFB_owl/blob/master/src/code/owl_gen/gen_exp_pattern_classes.py)

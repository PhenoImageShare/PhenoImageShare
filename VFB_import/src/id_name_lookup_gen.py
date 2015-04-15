#!/usr/bin/env jython
import json
from uk.ac.ebi.brain.core import Brain
import sys

"""Takes a list of ontology URIs as args, writes a JSON lookup of ID:name."""

out = {}

for path in sys.argv[1:]:
    entities = []
    o = Brain()
    o.learn(path)
    entities.extend(list(o.getSubClasses('Thing', 0)))
    entities.extend(list(o.getInstances('Thing', 0)))
    for e in entities:
        # Need check for if label exists.  Should be able to do that by iterating over all annotations on class to check.  Will slow things down a lot...
        out[e] = o.getLabel(e)
    o.sleep()


OUT = open('id_name.json', 'w')    
OUT.write(json.dumps(out))

from uk.ac.ebi.brain.core import Brain
import json


all_ont = Brain()
all_ont.learn = ("") # fbbi
all_ont.learn = ("") # fbbt
all_ont.learn = ("") # fbdv

classlist = all_ont.getSubClasses("Thing", 0)

id_name = {}

for c in classlist:
    id_name[c] = all_ont.getLabel(c)
    
lookup = open("lookup", "w")
    
lookup.write(json.dump(id_name))


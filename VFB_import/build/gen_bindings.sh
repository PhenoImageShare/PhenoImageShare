# Generate pretty svg for doc purposes
java -jar ../lib/xsdvi.jar ../../PhIS/source/main/resources/phisSchema.xsd
# update file for easy viewing of svg and for recording date.

# generate pyxb python data model lib
pyxbgen \
   -u ../../PhiS/source/main/resources/phisSchema.xsd -m phisSchema



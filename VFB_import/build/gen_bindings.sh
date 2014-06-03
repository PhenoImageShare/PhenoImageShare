# Generate pretty svg for doc purposes
java -jar ${JLPATH}/xsdvi.jar ../../PhiS/source/main/resources/phisSchema.xsd
# generate pyxb python data model lib
pyxbgen \
   -u ../../PhiS/source/main/resources/phisSchema.xsd -m phisSchema

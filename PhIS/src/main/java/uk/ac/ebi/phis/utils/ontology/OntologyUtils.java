/*******************************************************************************
 * Copyright 2015 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 *******************************************************************************/
package uk.ac.ebi.phis.utils.ontology;

import org.apache.log4j.Logger;
import org.semanticweb.elk.owlapi.ElkReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredClassAssertionAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredSubClassAxiomGenerator;
import owltools.graph.OWLGraphWrapper;
import uk.ac.ebi.phis.exception.PhenoImageShareException;
import uk.ac.ebi.phis.release.OntologyInstance;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyImpl;

import java.io.File;
import java.util.*;


//@Service
public class OntologyUtils {
	
	private static final OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	private static final OWLDataFactory factory = manager.getOWLDataFactory();
	public static final OWLAnnotationProperty LABEL_ANNOTATION = factory.getRDFSLabel();	
	public static final OWLAnnotationProperty ALT_ID = factory.getOWLAnnotationProperty(IRI.create("http://www.geneontology.org/formats/oboInOwl#hasAlternativeId"));	
	public static final OWLAnnotationProperty X_REF = factory.getOWLAnnotationProperty(IRI.create("http://www.geneontology.org/formats/oboInOwl#hasDbXref"));
	private static ArrayList<String> synonymRelations = new ArrayList<>();
	private static Set<OWLPropertyExpression> partOfRelations = new HashSet<>();	
	
	private ArrayList<String> anatomyOntologies = new ArrayList<String>();
	private ArrayList<String> phenotypeOntologies = new ArrayList<String>();
	private ArrayList<String> stageOntologies = new ArrayList<>();
	private ArrayList<String> xrefOntologies = new ArrayList<>();
	
	// Hashes <termId, termLabel>
	private HashMap<String, OntologyObject> anatomyTerms = new HashMap<>();
	private HashMap<String, OntologyObject> phenotypeTerms = new HashMap<>();
	private HashMap<String, OntologyObject> stageTerms = new HashMap<>();
	private HashMap<String, OntologyObject> imTerms = new HashMap<>();
	private HashMap<String, OntologyObject> spTerms = new HashMap<>();
	private HashMap<String, OntologyObject> vmTerms = new HashMap<>();
	private HashMap<String, OntologyObject> uberonTerms = new HashMap<>();
	private HashMap<String, String> alternateIds = new HashMap<>();
	private HashMap<String, List<String>> externalToUberon = new HashMap<>();
	
	private OntologyMapper om = new OntologyMapper(OntologyMapperPredefinedTypes.MA_MP);
	
	private List<OntologyInstance> ontologyInstances = new ArrayList<>();

	Logger logger = Logger.getLogger(OntologyMapper.class);

	private String fbbi = System.getProperty("user.home") + "/phis_ontologies/fbbi.owl";

	public OntologyUtils(){
		

		synonymRelations.add("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym");
		synonymRelations.add("http://www.geneontology.org/formats/oboInOwl#hasNarrowSynonym");
		synonymRelations.add("http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym");
		synonymRelations.add("http://www.geneontology.org/formats/oboInOwl#hasBroadSynonym");

		phenotypeOntologies.add(System.getProperty("user.home") + "/phis_ontologies/mp.owl");
		phenotypeOntologies.add(System.getProperty("user.home") + "/phis_ontologies/cmpo.owl");

		anatomyOntologies.add(System.getProperty("user.home") + "/phis_ontologies/fbbt.owl");
		anatomyOntologies.add(System.getProperty("user.home") + "/phis_ontologies/ma.owl");
		anatomyOntologies.add(System.getProperty("user.home") + "/phis_ontologies/emapa.owl");
		anatomyOntologies.add(System.getProperty("user.home") + "/phis_ontologies/emap.owl");
		
		stageOntologies.add(System.getProperty("user.home") + "/phis_ontologies/mmusdv.owl");
		stageOntologies.add(System.getProperty("user.home") + "/phis_ontologies/fbdv.owl");
		
		xrefOntologies.add(System.getProperty("user.home") + "/phis_ontologies/uberon.owl");

		partOfRelations.add(new OWLObjectPropertyImpl(IRI.create("http://purl.obolibrary.org/obo/ma#part_of")));
		partOfRelations.add(new OWLObjectPropertyImpl(IRI.create("http://purl.obolibrary.org/obo/emap#part_of")));
		partOfRelations.add(new OWLObjectPropertyImpl(IRI.create("http://purl.obolibrary.org/obo/part_of")));
		partOfRelations.add(new OWLObjectPropertyImpl(IRI.create("http://purl.obolibrary.org/obo/BFO_0000050")));
		
		
		long time = System.currentTimeMillis();
		try {
			loadHashes();
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}
		logger.info("Loading all ontologies took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public void getRoots() throws OWLOntologyCreationException{

	//	OWLOntology ontology = manager.loadOntologyFromOntologyDocument(IRI.create(new File(System.getProperty("user.home") + "/phis_ontologies/ma.owl")));
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(IRI.create(new File(System.getProperty("user.home") + "/Documents/ontologies/newMAOnt20160223.owl")));
        OWLGraphWrapper graph = new OWLGraphWrapper(ontology);
		String ontologyNamespace = "http://purl.obolibrary.org/obo/MP_";
        
		System.out.println(graph.getOntologyRoots().size() + "  " + graph.getOntologyRoots());
		// This also contains "orphaned" classes imported from other ontologies
		Set<OWLClass> allRoots = graph.getOntologyRoots();
		
		// This will be the top levels from the current ontology , i.e. "Mammalian prhenotype"
		Set<OWLClass> currentOntologyRoots = new HashSet<>();
		for (OWLClass root: allRoots){
			System.out.println(root +  "  " + root.getIRI().getNamespace() + "  "+ ontologyNamespace);
			if (root.getIRI().toString().contains(ontologyNamespace)){ // Class comes from current ontology
				currentOntologyRoots.add(root);
			}
		}
		
		Set<OWLClass> topLevels = new HashSet<>();
		for (OWLClass root: currentOntologyRoots){
			topLevels.addAll(graph.getOWLClassDirectDescendants(root));
		}

		System.out.println("TOP LEVELS " + topLevels.size() + "  " + topLevels);
		
		System.out.println("NAMED ANCESTORS (NamedAncestors):: " + graph.getNamedAncestors(graph.getOWLClass("http://purl.obolibrary.org/obo/MA_0000517")).size());
		System.out.println("NAMED ANCESTORS 2 (getNamedAncestorsWithGCI) :: " + graph.getNamedAncestorsWithGCI(graph.getOWLClass("http://purl.obolibrary.org/obo/MA_0000517")).size());
		
		Set<OWLNamedObject> onobjs = graph.getNamedAncestorsWithGCI(graph.getOWLClass("http://purl.obolibrary.org/obo/MA_0000517"), partOfRelations);
		for ( OWLNamedObject onobj : onobjs ){
		    if (onobj instanceof OWLClass) {
		        OWLClass ocls = (OWLClass) onobj;
		        System.out.println("GCI GOT: "+ocls);
		    }
		}
		
	} 
	
	public boolean isAnatomy(String id, Boolean strict) throws PhenoImageShareException {
		return checkIdIsIn(id, anatomyTerms, strict);
	}
	
	public boolean isPhenotype(String id, Boolean strict) throws PhenoImageShareException {
		return checkIdIsIn(id, phenotypeTerms, strict);
	}
	
	public boolean isStage(String id, Boolean strict) throws PhenoImageShareException {
		return checkIdIsIn(id, stageTerms, strict);
	}
	
	public boolean isImaveVisualization(String id, Boolean strict) throws PhenoImageShareException {
		return checkIdIsIn(id, imTerms, strict);
	}
	
	public boolean isSamplePreparation(String id, Boolean strict) throws PhenoImageShareException {
		return checkIdIsIn(id, spTerms, strict);
	}
	
	public boolean isImagingMethod(String id, Boolean strict) throws PhenoImageShareException {
		return checkIdIsIn(id, imTerms, strict);
	}
	
	public List<String> getMaFromMp(String mpId){

		String mpUri = "http://purl.obolibrary.org/obo/" + mpId.replace(":", "_");
		return om.getMappings(mpUri, "MA_");		
	}
	
	public boolean checkIdIsIn(String id, HashMap<String, OntologyObject> list, Boolean strict) throws PhenoImageShareException{

		boolean res = list.containsKey(id) || list.containsKey(id.replace(":", "_")) || list.containsKey(alternateIds.get(id)) || list.containsKey(alternateIds.get(id.replace(":", "_")));
		if (res == false){
			System.out.println("--- Ontology term " + id  + " not found.");
			if (strict) {
				throw new PhenoImageShareException("Ontology term " + id + " not found.");
			}
		}
		return res;
	}
	
	public boolean labelMatchesId(String label, String termId){
		boolean res = false;
		String id = termId.replace("_",  ":");
		
		if (anatomyTerms.containsKey(id)){
			res = (anatomyTerms.get(id).getLabel().equalsIgnoreCase(label) );
		}
		else if (uberonTerms.containsKey(id)){
			res = (uberonTerms.get(id).getLabel().equalsIgnoreCase(label) );
		}
		else if (phenotypeTerms.containsKey(id)){
			res = (phenotypeTerms.get(id).getLabel().equalsIgnoreCase(label) );
		}
		else if (stageTerms.containsKey(id)){
			res = (stageTerms.get(id).getLabel().equalsIgnoreCase(label) );
		}
		else if (imTerms.containsKey(id)){
			res = (imTerms.get(id).getLabel().equalsIgnoreCase(label) );
		}
		else if (vmTerms.containsKey(id)){
			res = (vmTerms.get(id).getLabel().equalsIgnoreCase(label) );
		}
		else if (spTerms.containsKey(id)){
			res = (spTerms.get(id).getLabel().equalsIgnoreCase(label) );
		}
		return res;
	}

	/*
	/**
	 * Fills all ontology hashes, i.e. anatomyTerms, spTerms, phenotypeTerms etc.
	 * @return true if all ontologies could be loaded, false otherwise
	 * @throws IOException 
	 * @throws OBOFormatParserException 
	 * @throws OWLOntologyCreationException 
	 */
	private void loadHashes() throws OWLOntologyStorageException{

		for (String path: xrefOntologies){
			fillHashesFor(path, uberonTerms, null, true, true, false);
		}
		for (String path: stageOntologies){
			fillHashesFor(path, stageTerms, null, true, true, true);
		}
		for (String path: anatomyOntologies){
			fillHashesFor(path, anatomyTerms, null, true, false, false);
		}
		for (String path: phenotypeOntologies){
			fillHashesFor(path, phenotypeTerms, null, false, false, false);				
		}
		fillHashesFor(fbbi, spTerms, "http://purl.obolibrary.org/obo/FBbi_00000001", false, false, false); 
		fillHashesFor(fbbi, vmTerms, "http://purl.obolibrary.org/obo/FBbi_00000031", false, false, false); 
		fillHashesFor(fbbi, imTerms, "http://purl.obolibrary.org/obo/FBbi_00000222", false, false, false); 
		
		computeFacetTopLevelTerms(stageTerms);
		
	}

	/**
	 * Higher level terms to be used on the GUI facets.
	 * @since 2015/08/24 
	 */
	private void computeFacetTopLevelTerms(HashMap<String, OntologyObject> terms){
				
		for (String key:  terms.keySet()){
			terms.get(key).setFacetTerms(getStageTopLevel(terms.get(key)));
		}
		
	}
	
	
	/**Used for testing only. 
	 * @since 2016/06/21
	 * @param id Ontology id in OWL short form from a stage ontology, species non-specific.
	 * @return Stage facets from UBERON.
	 */
	public List<OntologyObject> getStageTopLevel( String id){
	
		return getStageTopLevel(stageTerms.get(id));
		
	}
	
	/**
	 * [WARNING] This method should only be used for stages as the top levels are disjunct. This method might miss parents for multiple inheritance.
	 * @param obj
	 * @return
	 */
	private List<OntologyObject> getStageTopLevel( OntologyObject obj){
		
		List<OntologyObject> list = getStageHigherLevel(obj);
		
		if (list.size() > 0){
			return list;
		} else if (obj.directParentTerms.size() > 0){
			HashSet<OntologyObject> res = new HashSet<>();
			for (OntologyObject parent : obj.directParentTerms){
				List<OntologyObject> temp = getStageTopLevel(parent);
				if (temp != null){
					res.addAll(temp);
				}
			}
			return new ArrayList<>(res);
		} else {
			return null;
		}
	}
	
	/**
	 * @since 2015/08/25
	 * @return
	 */
	private List<OntologyObject> getStageHigherLevel(OntologyObject obj){

		List<OntologyObject> topLevels = new ArrayList<>();
		topLevels.add(getOntologyTermById("UBERON_0000068")); // embryo stage
		topLevels.add(getOntologyTermById("UBERON_0000071")); // death stage
		topLevels.add(getOntologyTermById("UBERON_0000092")); // post-embryonic stage

		HashSet<OntologyObject> list = new HashSet<OntologyObject>();
		if (externalToUberon.get(obj.getId()) != null){
			for (String id: externalToUberon.get(obj.getId())){	
				OntologyObject oo = getOntologyTermById(id);
				if (oo != null){
					list.add(oo);
					list.addAll(oo.getIntermediateTerms());
				} 
			}
		}
				
		topLevels.retainAll(list);				
		return topLevels;
		
	}
	
	/**
	 * 
	 * @param path
	 * @param idLabelMap
	 * @param rootId
	 * @param includePartOf
	 * @param storeXrefs
	 * @param external Whether external ont or Uberon. The field is only used when populating the mapping has "externalToUberon", to know which is the key and which the value
	 * @throws OWLOntologyStorageException
	 */
	private void fillHashesFor(String path, HashMap<String, OntologyObject> idLabelMap, String rootId, Boolean includePartOf, Boolean storeXrefs, Boolean external) 
	throws OWLOntologyStorageException{
		  
		OntologyInstance ontologyInst = new OntologyInstance();
		
		try {
			logger.info("Lading: " + path);
			
			System.out.println("Loading: " + path);
			OWLOntology ontology = manager.loadOntologyFromOntologyDocument(IRI.create(new File(path)));
			ontologyInst.setVersion(ontology.getOntologyID().getVersionIRI().toString());
			ontologyInst.setName(path.split("/")[path.split("/").length - 1]);
			
	        System.out.println("Axioms before :" + ontology.getAxiomCount());
	        
	        OWLReasonerFactory reasonerFactory = new ElkReasonerFactory();
	        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
	        reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);     
	        List<InferredAxiomGenerator<? extends OWLAxiom>> gens = new ArrayList<InferredAxiomGenerator<? extends OWLAxiom>>();
	        gens.add(new InferredSubClassAxiomGenerator());
	        gens.add(new InferredClassAssertionAxiomGenerator());
	        InferredOntologyGenerator iog = new InferredOntologyGenerator(reasoner, gens);
	        iog.fillOntology(manager.getOWLDataFactory(), ontology);
	        manager.saveOntology(ontology, new StringDocumentTarget());

	        System.out.println("Axioms after :" + ontology.getAxiomCount());
	        
	        OWLGraphWrapper gr = new OWLGraphWrapper(ontology);
			Set<OWLClass> classesSubSet = ontology.getClassesInSignature();;
			
			if (rootId != null){
				OWLClass root = gr.getOWLClassByIdentifier(rootId); // Wasn't working any more, possibly related to berkley maven repo being down and 
//				for (OWLClass cls : classesSubSet){
//					if (cls.getIRI().toString().equalsIgnoreCase(rootId)){
//						classesSubSet = reasoner.getSubClasses(cls, false).getFlattened();
//					}
//				}
			}			
			for (OWLClass cls : classesSubSet){
				
				if (!cls.getIRI().isNothing()){
					OntologyObject temp = new OntologyObject();
					temp.setId(getIdentifierShortForm(cls));
					
					if (EntitySearcher.getAnnotations(cls,ontology, LABEL_ANNOTATION).size() > 0){
						temp.setLabel(((OWLLiteral)EntitySearcher.getAnnotations(cls,ontology, LABEL_ANNOTATION).iterator().next().getValue()).getLiteral());
						
						for (String synonymType : synonymRelations){
							OWLAnnotationProperty label = factory.getOWLAnnotationProperty(IRI.create(synonymType));	
							
							// Get the annotations on the class that use the label property
							for (OWLAnnotation annotation : EntitySearcher.getAnnotations(cls,ontology, label)) {
								
								if (annotation.getValue() instanceof OWLLiteral) {
									OWLLiteral val = (OWLLiteral) annotation.getValue();
									temp.addSynonym(val.getLiteral());
								}
							}
						}
						idLabelMap.put(getIdentifierShortForm(cls), temp);
					}
				}
				
				if (!cls.getIRI().isNothing() && EntitySearcher.getAnnotations(cls,ontology, ALT_ID) != null){
					for (OWLAnnotation annotation : EntitySearcher.getAnnotations(cls,ontology, ALT_ID)) {
						if (annotation.getValue() instanceof OWLLiteral) {
							OWLLiteral val = (OWLLiteral) annotation.getValue();
							alternateIds.put(val.getLiteral().replace(":", "_"), getIdentifierShortForm(cls));
						}
					}
				}
				
				if (storeXrefs && !cls.getIRI().isNothing() && EntitySearcher.getAnnotations(cls,ontology, X_REF) != null){
					for (OWLAnnotation annotation : EntitySearcher.getAnnotations(cls,ontology, X_REF)) {
						if (annotation.getValue() instanceof OWLLiteral) {
							OWLLiteral val = (OWLLiteral) annotation.getValue();
							String id = val.getLiteral().replace(":", "_");
							if (!external){
								List<String> mappings = externalToUberon.containsKey(id) ? externalToUberon.get(id) : new ArrayList<>(); 
								if (!mappings.contains(getIdentifierShortForm(cls))) {
									mappings.add(getIdentifierShortForm(cls));
								}
								externalToUberon.put(id, mappings);
							} else {
								if (id.startsWith("UBERON")){
									List<String> mappings = externalToUberon.containsKey(getIdentifierShortForm(cls)) ? externalToUberon.get(getIdentifierShortForm(cls)) : new ArrayList<>(); 
									if (!mappings.contains(id)) {
										mappings.add(id); 
									}
									externalToUberon.put(getIdentifierShortForm(cls), mappings);
								}
							}
							
						}
					}
				}
				
			}
			
			System.out.println("Done, going for ancestors");
			
			for (OWLClass cls : classesSubSet) {
				
				if (!cls.getIRI().isNothing() && EntitySearcher.getAnnotations(cls,ontology, LABEL_ANNOTATION).size() > 0) {
					
					OntologyObject temp = idLabelMap.get(getIdentifierShortForm(cls));
					Set<OWLClass> ancestors = new HashSet<>();
					Set<OWLClass> parents = new HashSet<>();
					
					if (includePartOf){
						ancestors = new HashSet(gr.getNamedAncestorsWithGCI(cls, partOfRelations));
						parents = new HashSet(getParentsClassifiedPartOf(cls, gr));
					} else {
						ancestors = getAncestors(reasoner, cls);
						parents = new HashSet(getParentsClassifiedPartOf(cls, gr));
					}
					
					for (OWLObject obj : ancestors) {
						OntologyObject ancestorObject = idLabelMap.get(getIdentifierShortForm((OWLClass) obj));
						if (ancestorObject != null){
							temp.addIntermediateTerms(ancestorObject);
						}
					}
					
					for (OWLObject obj : parents) {
						OntologyObject parent = idLabelMap.get(getIdentifierShortForm((OWLClass) obj));
						if (parent != null){
							temp.addDirectparentTerms(parent);
						} 
					}
					
					idLabelMap.put(getIdentifierShortForm(cls), temp);
				}
			}
			
			manager.removeOntology(ontology);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if (!ontologyInstances.contains(ontologyInst)){
			ontologyInstances.add(ontologyInst);
		}
		
	}
	
	
	public List<OntologyInstance> getOntologyInstances() {
	
		return ontologyInstances;
	}

	
	public void setOntologyInstances(List<OntologyInstance> ontologyInstances) {
	
		this.ontologyInstances = ontologyInstances;
	}

	public Set<OWLClass> getAncestors(OWLReasoner reasoner, OWLClass cls){
		
		return reasoner.getSuperClasses(cls, false).getFlattened();
		
	}
	
	
	public Set<OWLNamedObject> getParentsClassifiedPartOf(OWLClass cls, OWLGraphWrapper graph){
		
		Set<OWLNamedObject> res = new HashSet<>();
				
		for ( OWLClassExpression classExpression: EntitySearcher.getSuperClasses(cls, graph.getSourceOntology())){
			if (classExpression.isClassExpressionLiteral()){
				res.add(classExpression.asOWLClass());
			} else if (classExpression instanceof OWLObjectSomeValuesFrom){
				OWLObjectSomeValuesFrom svf = (OWLObjectSomeValuesFrom) classExpression;
				if (partOfRelations.contains(svf.getProperty().asOWLObjectProperty())){
					if (svf.getFiller() instanceof OWLNamedObject){
						res.add((OWLNamedObject) svf.getFiller());
					}
				}
			}
		}
		
		return res;
			
	}
	
	
	public String getIdentifierShortForm(OWLClass cls){
		String id = cls.getIRI().toString();
		return id.split("/|#")[id.split("/|#").length-1];
	}
	
	
	public OntologyObject getOntologyTermById(String id){
		id = id.replace(":", "_");
		if (anatomyTerms.containsKey(id)){
			return anatomyTerms.get(id);
		}
		else if (phenotypeTerms.containsKey(id)){
			return phenotypeTerms.get(id);
		}
		else if (stageTerms.containsKey(id)){
			return stageTerms.get(id);
		}
		else if (imTerms.containsKey(id)){
			return imTerms.get(id);
		}
		else if (vmTerms.containsKey(id)){
			return vmTerms.get(id);
		}
		else if (spTerms.containsKey(id)){
			return spTerms.get(id);
		}
		else if (uberonTerms.containsKey(id)){
			return uberonTerms.get(id);
		}
		// Id was not found until here so it might be deprecated
		if (alternateIds.containsKey(id)){
			return getOntologyTermById(alternateIds.get(id));
		}
		return null;
	}
	
	public ArrayList<String> getSynonyms (String id){
		
		OntologyObject oo = getOntologyTermById(id);
		if (oo != null){
			return oo.getSynonyms();
		}
		return null;
	}
	
/*
	private OWLGraphWrapper readOntologyFromUrl (String ontIri) throws IOException, OWLOntologyCreationException, OBOFormatParserException{
		ParserWrapper pw = new ParserWrapper();
		OWLGraphWrapper graph = pw.parseToOWLGraph(ontIri);
		logger.debug("OWL file parsed and graph is created");
		logger.info("Ontology is initialised completely");
		return graph;
	}
	*/
	public HashMap<String, OntologyObject> getAnatomyTerms() {
		
		return anatomyTerms;
	}

	public HashMap<String, OntologyObject> getPhenotypeTerms() {
	
		return phenotypeTerms;
	}

	public HashMap<String, OntologyObject> getStageTerms() {
	
		return stageTerms;
	}

	public HashMap<String, OntologyObject> getImTerms() {	
		return imTerms;
	}
	
	public HashMap<String, OntologyObject> getSpTerms() {	
		return spTerms;
	}

	public HashMap<String, OntologyObject> getVmTerms() {
		return vmTerms;
	}


}

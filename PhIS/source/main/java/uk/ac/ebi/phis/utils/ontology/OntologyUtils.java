package uk.ac.ebi.phis.utils.ontology;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.tools.ant.types.resources.selectors.InstanceOf;
import org.obolibrary.oboformat.parser.OBOFormatParserException;
import org.semanticweb.elk.owlapi.ElkReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedObject;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLPropertyExpression;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.util.*;

import owltools.graph.OWLGraphWrapper;
import owltools.io.ParserWrapper;


//@Service
public class OntologyUtils {
	
	private static final OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	private static final OWLDataFactory factory = manager.getOWLDataFactory();
	public static final OWLAnnotationProperty LABEL_ANNOTATION = factory.getRDFSLabel();	
	public static final OWLAnnotationProperty ALT_ID = factory.getOWLAnnotationProperty(IRI.create("http://www.geneontology.org/formats/oboInOwl#hasAlternativeId"));	
	private static ArrayList<String> synonymRelations = new ArrayList<>();
	private static Set<String> partOfRelations = new HashSet<>();	
	
	private ArrayList<String> anatomyOntologies = new ArrayList<String>();
	private ArrayList<String> phenotypeOntologies = new ArrayList<String>();
	private ArrayList<String> stageOntologies = new ArrayList<>();
	
	// Hashes <termId, termLabel>
	private HashMap<String, OntologyObject> anatomyTerms = new HashMap<>();
	private HashMap<String, OntologyObject> phenotypeTerms = new HashMap<>();
	private HashMap<String, OntologyObject> stageTerms = new HashMap<>();
	private HashMap<String, OntologyObject> imTerms = new HashMap<>();
	private HashMap<String, OntologyObject> spTerms = new HashMap<>();
	private HashMap<String, OntologyObject> vmTerms = new HashMap<>();
	
	private HashMap<String, String> alternateIds = new HashMap<>();

	Logger logger = Logger.getLogger(OntologyMapper.class);

	private String fbbi = System.getProperty("user.home") + "/phis_ontologies/fbbi.owl";
	
	public OntologyUtils(){

		synonymRelations.add("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym");
		synonymRelations.add("http://www.geneontology.org/formats/oboInOwl#hasNarrowSynonym");
		synonymRelations.add("http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym");
		synonymRelations.add("http://www.geneontology.org/formats/oboInOwl#hasBroadSynonym");
		
		phenotypeOntologies.add(System.getProperty("user.home") + "/phis_ontologies/mp.owl");

		anatomyOntologies.add(System.getProperty("user.home") + "/phis_ontologies/fbbt.owl");
		anatomyOntologies.add(System.getProperty("user.home") + "/phis_ontologies/ma.owl");
		anatomyOntologies.add(System.getProperty("user.home") + "/phis_ontologies/emapa.owl");
		anatomyOntologies.add(System.getProperty("user.home") + "/phis_ontologies/emap.owl");
		
		stageOntologies.add(System.getProperty("user.home") + "/phis_ontologies/mmusdv.owl");
		stageOntologies.add(System.getProperty("user.home") + "/phis_ontologies/fbdv.owl");

		partOfRelations.add("part_of");
		partOfRelations.add("part of");
		
		long time = System.currentTimeMillis();
		try {
			loadHashes();
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}
		logger.info("Loading all ontologies took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public boolean isAnatomy(String id){
		return checkIdIsIn(id, anatomyTerms);
	}
	
	public boolean isPhenotype(String id){
		return checkIdIsIn(id, phenotypeTerms);
	}
	
	public boolean isStage(String id){
		return checkIdIsIn(id, stageTerms);
	}
	
	public boolean isImaveVisualization(String id){
		return checkIdIsIn(id, imTerms);
	}
	
	public boolean isSamplePreparation(String id){
		return checkIdIsIn(id, spTerms);
	}
	
	public boolean isImagingMethod(String id){
		return checkIdIsIn(id, imTerms);
	}
	
	public boolean checkIdIsIn(String id, HashMap<String, OntologyObject> list){
		return list.containsKey(id) || list.containsKey(id.replace(":", "_")) || list.containsKey(alternateIds.get(id)) || list.containsKey(alternateIds.get(id.replace(":", "_")));
	}
	
	public boolean labelMatchesId(String label, String termId){
		boolean res = false;
		String id = termId.replace("_",  ":");
		
		if (anatomyTerms.containsKey(id)){
			res = (anatomyTerms.get(id).getLabel().equalsIgnoreCase(label) );
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
	private boolean loadHashes() throws OWLOntologyStorageException{


		for (String path: anatomyOntologies){
			fillHashesFor(path, anatomyTerms, null, true);
		}
		for (String path: phenotypeOntologies){
			fillHashesFor(path, phenotypeTerms, null, false);				
		}
		for (String path: stageOntologies){
			fillHashesFor(path, stageTerms, null, true);
		}
		
		fillHashesFor(fbbi, spTerms, "http://purl.obolibrary.org/obo/FBbi_00000001", false); 
		fillHashesFor(fbbi, vmTerms, "http://purl.obolibrary.org/obo/FBbi_00000031", false); 
		fillHashesFor(fbbi, imTerms, "http://purl.obolibrary.org/obo/FBbi_00000222", false); 
		
		return false;
	}

	
	private void fillHashesFor(String path, HashMap<String, OntologyObject> idLabelMap, String rootId, Boolean includePartOf) 
	throws OWLOntologyStorageException{
		  
		try {
			logger.info("Lading: " + path);
			
			System.out.println("Lading: " + path);
			OWLOntology ontology = manager.loadOntologyFromOntologyDocument(IRI.create(new File(path)));
			
	        System.out.println("Axioms before :" + ontology.getAxiomCount());
	        
	        OWLReasonerFactory reasonerFactory = new ElkReasonerFactory();
	        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
	        reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
	        	        
	        List<InferredAxiomGenerator<? extends OWLAxiom>> gens = new ArrayList<InferredAxiomGenerator<? extends OWLAxiom>>();
	        gens.add(new InferredSubClassAxiomGenerator());
	        gens.add(new InferredClassAssertionAxiomGenerator());
	        InferredOntologyGenerator iog = new InferredOntologyGenerator(reasoner, gens);
	        iog.fillOntology(manager, ontology);
	        manager.saveOntology(ontology, new StringDocumentTarget());

	        System.out.println("Axioms after :" + ontology.getAxiomCount());
	        
	        OWLGraphWrapper gr = new OWLGraphWrapper(ontology);
			Set<OWLClass> classesSubSet;
			
			if (rootId != null){
				OWLClass root = gr.getOWLClassByIdentifier(rootId);
				classesSubSet = reasoner.getSubClasses(root, false).getFlattened();
			}else {
				classesSubSet = ontology.getClassesInSignature();
			}
			
			for (OWLClass cls : classesSubSet){
				if (!cls.getIRI().isNothing()){
					OntologyObject temp = new OntologyObject();
					temp.setId(getIdentifierShortForm(cls));
					if (cls.getAnnotations(ontology, LABEL_ANNOTATION).size() > 0){
						temp.setLabel(((OWLLiteral)cls.getAnnotations(ontology, LABEL_ANNOTATION).iterator().next().getValue()).getLiteral());
						for (String synonymType : synonymRelations){
							OWLAnnotationProperty label = factory.getOWLAnnotationProperty(IRI.create(synonymType));	
							// Get the annotations on the class that use the label property
							for (OWLAnnotation annotation : cls.getAnnotations(ontology, label)) {
								if (annotation.getValue() instanceof OWLLiteral) {
									OWLLiteral val = (OWLLiteral) annotation.getValue();
									temp.addSynonym(val.getLiteral());
								}
							}
						}
						idLabelMap.put(getIdentifierShortForm(cls), temp);
					}
				}
				
				if (!cls.getIRI().isNothing() && cls.getAnnotations(ontology, ALT_ID) != null){
					for (OWLAnnotation annotation : cls.getAnnotations(ontology, ALT_ID)) {
						if (annotation.getValue() instanceof OWLLiteral) {
							OWLLiteral val = (OWLLiteral) annotation.getValue();
							alternateIds.put(val.getLiteral().replace(":", "_"), getIdentifierShortForm(cls));
						}
					}
				}
			}
			
			System.out.println("Done, going for ancestors");
			
			for (OWLClass cls : classesSubSet) {
				
				if (!cls.getIRI().isNothing() && cls.getAnnotations(ontology, LABEL_ANNOTATION).size() > 0) {
					
					OntologyObject temp = idLabelMap.get(getIdentifierShortForm(cls));
					Set<OWLClass> ancestors = new HashSet<>();
					
					if (includePartOf){
						ancestors = new HashSet(getAncestorsClassifiedPartOf(cls, gr));
					} else {
						ancestors = getAncestors(reasoner, cls);
					}
					
					for (OWLObject obj : ancestors) {
						OntologyObject ancestorObject = idLabelMap.get(getIdentifierShortForm((OWLClass) obj));
						if (ancestorObject != null){
							temp.addIntermediateTerms(ancestorObject);
						}
					}
					
					idLabelMap.put(getIdentifierShortForm(cls), temp);
				}
			}
			
			manager.removeOntology(ontology);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	public Set<OWLClass> getAncestors(OWLReasoner reasoner, OWLClass cls){
		
		return reasoner.getSuperClasses(cls, false).getFlattened();
		
	}
	
	
	public Set<OWLNamedObject> getAncestorsClassifiedPartOf(OWLClass cls, OWLGraphWrapper graph){
		
		Set<OWLPropertyExpression> overProps = new HashSet<>();
		Set<OWLObject> res = graph.getAncestors(cls, overProps, false);
		
		if (partOfRelations != null) {
			for (String lbl: partOfRelations){
				overProps.add((OWLPropertyExpression) graph.getOWLObjectByLabel(lbl));
			}
		}
		
		return graph.getNamedAncestorsWithGCI(cls, overProps);
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
	

	private OWLGraphWrapper readOntologyFromUrl (String ontIri) throws IOException, OWLOntologyCreationException, OBOFormatParserException{
		ParserWrapper pw = new ParserWrapper();
		OWLGraphWrapper graph = pw.parseToOWLGraph(ontIri);
		logger.debug("OWL file parsed and graph is created");
		logger.info("Ontology is initialised completely");
		return graph;
	}
	
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

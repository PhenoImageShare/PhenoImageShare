package uk.ac.ebi.phis.utils.ontology;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;
import org.obolibrary.oboformat.parser.OBOFormatParserException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import owltools.graph.OWLGraphWrapper;
import owltools.io.ParserWrapper;
import uk.ac.manchester.cs.jfact.JFactFactory;


//@Service
public class OntologyUtils {
	
	private static final OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	private static final OWLDataFactory factory = manager.getOWLDataFactory();
	public static final OWLAnnotationProperty LABEL_ANNOTATION = factory.getRDFSLabel();	
	private static ArrayList<String> synonymRelations = new ArrayList<>();
	
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

	Logger logger = Logger.getLogger(OntologyMapper.class);

	//http://www.berkeleybop.org/ontologies/fbbi.owl
	private String FBBI = System.getProperty("user.home") + "/phis_ontologies/fbbi.owl";
	
	public OntologyUtils(){

		synonymRelations.add("http://www.geneontology.org/formats/oboInOwl#hasExactSynonym");
		synonymRelations.add("http://www.geneontology.org/formats/oboInOwl#hasNarrowSynonym");
		synonymRelations.add("http://www.geneontology.org/formats/oboInOwl#hasRelatedSynonym");
		synonymRelations.add("http://www.geneontology.org/formats/oboInOwl#hasBroadSynonym");

		//http://purl.obolibrary.org/obo/mp.owl
		phenotypeOntologies.add(System.getProperty("user.home") + "/phis_ontologies/mp.owl");
		//http://purl.obolibrary.org/obo/emapa.owl
		anatomyOntologies.add(System.getProperty("user.home") + "/phis_ontologies/emapa.owl");
		//http://purl.obolibrary.org/obo/emap.owl
		anatomyOntologies.add(System.getProperty("user.home") + "/phis_ontologies/emap.owl");
		//http://purl.obolibrary.org/obo/ma.owl
		anatomyOntologies.add(System.getProperty("user.home") + "/phis_ontologies/ma.owl");
		//http://www.berkeleybop.org/ontologies/mmusdv.owl
		stageOntologies.add(System.getProperty("user.home") + "/phis_ontologies/mmusdv.owl");
		
		long time = System.currentTimeMillis();
		loadHashes();
		loadFbbi();
		logger.info("Loading all ontologies took " + (System.currentTimeMillis() - time) + "ms.");
	}
	


	// isAnatomyId(String id)
	public boolean isAnatomy(String id){
		return anatomyTerms.containsKey(id) || anatomyTerms.containsKey(id.replace("_", ":"));
	}
	
	// isPhenotype(String id)
	public boolean isPhenotype(String id){
		return phenotypeTerms.containsKey(id) || phenotypeTerms.containsKey(id.replace("_", ":"));
	}
	
	// isStage(String id)
	public boolean isStage(String id){
		return stageTerms.containsKey(id) || stageTerms.containsKey(id.replace("_", ":"));
	}
	
	// isImaveVisualization (String id)
	public boolean isImaveVisualization(String id){
		return imTerms.containsKey(id) || imTerms.containsKey(id.replace("_", ":"));
	}
	
	// isSamplePreparation(String id)
	public boolean isSamplePreparation(String id){
		return spTerms.containsKey(id) || spTerms.containsKey(id.replace("_", ":"));
	}
	
	// isImaginfMethod(String id)
	public boolean isImagingMethod(String id){
		return imTerms.containsKey(id) || imTerms.containsKey(id.replace("_", ":"));
	}
	
		
	// labelMatchesId(String label, String id)
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
	

	private void loadFbbi(){
		try {
			OWLGraphWrapper gr;
			logger.info("Lading: " + FBBI);
			gr = readOntologyFronUrl(FBBI);
			OWLClass sp = gr.getOWLClass("http://purl.obolibrary.org/obo/FBbi_00000001");
			OWLClass vm = gr.getOWLClass("http://purl.obolibrary.org/obo/FBbi_00000031");
			OWLClass im = gr.getOWLClass("http://purl.obolibrary.org/obo/FBbi_00000222");
			
			
			Set<OWLClass> classes = gr.getOWLClassDescendants(sp);
			for (OWLClass cls : classes){
				OntologyObject temp = new OntologyObject();
				temp.setId(gr.getIdentifier(cls));
				temp.setLabel(gr.getLabel(cls));
				spTerms.put(gr.getIdentifier(cls), temp);
			}
			
			classes = gr.getOWLClassDescendants(vm);
			for (OWLClass cls : classes){
				OntologyObject temp = new OntologyObject();
				temp.setId(gr.getIdentifier(cls));
				temp.setLabel(gr.getLabel(cls));
				vmTerms.put(gr.getIdentifier(cls), temp);
			}
			
			classes = gr.getOWLClassDescendants(im);
			for (OWLClass cls : classes){
				OntologyObject temp = new OntologyObject();
				temp.setId(gr.getIdentifier(cls));
				temp.setLabel(gr.getLabel(cls));
				imTerms.put(gr.getIdentifier(cls), temp);
			}
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		} catch (OBOFormatParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Fills all ontology hashes, i.e. anatomyTerms, spTerms, phenotypeTerms etc.
	 * @return true if all ontologies could be loaded, false otherwise
	 * @throws IOException 
	 * @throws OBOFormatParserException 
	 * @throws OWLOntologyCreationException 
	 */
	private boolean loadHashes(){

		for (String path: phenotypeOntologies){
			fillHashesFor(path, phenotypeTerms);				
		}
		for (String path: anatomyOntologies){
			fillHashesFor(path, anatomyTerms);
		}
		for (String path: stageOntologies){
			fillHashesFor(path, stageTerms);
		}
		return false;
		
	}
	
	private void fillHashesFor(String path, HashMap<String, OntologyObject> idLabelMap){
		  
		try {
			logger.info("Lading: " + path);
			System.out.println("Lading: " + path);
			OWLOntology ontology = manager.loadOntologyFromOntologyDocument(IRI.create(new File(path)));
			OWLReasoner reasoner = (new JFactFactory()).createReasoner(ontology);
			
			Set<OWLClass> classes = ontology.getClassesInSignature();
			
			for (OWLClass cls : classes){
				OntologyObject temp = new OntologyObject();
				temp.setId(getIdentifierShortForm(cls));
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
			// link parents / ancestors
			for (OWLClass cls : classes){
				OntologyObject temp = idLabelMap.get(getIdentifierShortForm(cls));
				for (OWLObject obj: getAncestors(reasoner, cls)){
					temp.getIntermediateTerms().add(idLabelMap.get(getIdentifierShortForm(cls)));
				}
				if (getIdentifierShortForm(cls).contains("MP_0012765"))
					System.out.println("YES, it was added the second time " + getIdentifierShortForm(cls));
				idLabelMap.put(getIdentifierShortForm(cls), temp);
			}
			
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
	}
	
	public Set<OWLClass> getAncestors(OWLReasoner reasoner, OWLClass cls){
		return reasoner.getSuperClasses(cls, false).getFlattened();
	}
	
	public String getIdentifierShortForm(OWLClass cls){
		String id = cls.getIRI().toString();
		return id.split("/|#")[id.split("/|#").length-1];
	}
	
	public OntologyObject getOntologyTermById(String id){
		id = id.replace(":", "_");
		System.out.println(id);
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
		return null;
	}
	
	public ArrayList<String> getSynonyms (String id){
		
		OntologyObject oo = getOntologyTermById(id);
		if (oo != null){
			return oo.getSynonyms();
		}
		return null;
	}
	

	private OWLGraphWrapper readOntologyFronUrl (String ontIri) throws IOException, OWLOntologyCreationException, OBOFormatParserException{
		ParserWrapper pw = new ParserWrapper();
		OWLGraphWrapper graph = pw.parseToOWLGraph(ontIri);
		logger.debug("OWL file parsed and graph is created");
		logger.info("Ontology is initialised completely");
		return graph;
	}
}

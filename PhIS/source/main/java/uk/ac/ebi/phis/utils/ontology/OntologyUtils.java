package uk.ac.ebi.phis.utils.ontology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;
import org.obolibrary.oboformat.parser.OBOFormatParserException;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.springframework.stereotype.Service;

import owltools.graph.OWLGraphWrapper;
import owltools.io.ParserWrapper;


@Service
public class OntologyUtils {

	private ArrayList<String> anatomyOntologies = new ArrayList<String>();
	private ArrayList<String> phenotypeOntologies = new ArrayList<String>();
	private ArrayList<String> stageOntologies = new ArrayList<>();
	// Hashes <termId, termLabel>
	private HashMap<String, String> anatomyTerms = new HashMap<>();
	private HashMap<String, String> phenotypeTerms = new HashMap<>();
	private HashMap<String, String> stageTerms = new HashMap<>();
	private HashMap<String, String> imTerms = new HashMap<>();
	private HashMap<String, String> spTerms = new HashMap<>();
	private HashMap<String, String> vmTerms = new HashMap<>();

	Logger logger = Logger.getLogger(OntologyMapper.class);

	private String FBBI = "http://www.berkeleybop.org/ontologies/fbbi.owl";
	
	public OntologyUtils(){
		anatomyOntologies.add("http://purl.obolibrary.org/obo/ma.owl");
		anatomyOntologies.add("http://purl.obolibrary.org/obo/emapa.owl");
		anatomyOntologies.add("http://purl.obolibrary.org/obo/emap.owl");
		
		phenotypeOntologies.add("http://purl.obolibrary.org/obo/mp.owl");
		
		stageOntologies.add("http://www.berkeleybop.org/ontologies/mmusdv.owl");
		
		long time = System.currentTimeMillis();
		loadHashes();
		loadFbbi();
		logger.info("Loading all ontologies took " + (System.currentTimeMillis() - time) + "ms.");
	}
	


	// isAnatomyId(String id)
	public boolean isAnatomy(String id){
		return anatomyTerms.containsKey(id) || anatomyTerms.containsKey(id.replace(":", "_"));
	}
	
	// isPhenotype(String id)
	public boolean isPhenotype(String id){
		return phenotypeTerms.containsKey(id) || phenotypeTerms.containsKey(id.replace(":", "_"));
	}
	
	// isStage(String id)
	public boolean isStage(String id){
		return stageTerms.containsKey(id) || stageTerms.containsKey(id.replace(":", "_"));
	}
	
	// isImaveVisualization (String id)
	public boolean isImaveVisualization(String id){
		return imTerms.containsKey(id) || imTerms.containsKey(id.replace(":", "_"));
	}
	
	// isSamplePreparation(String id)
	public boolean isSamplePreparation(String id){
		return spTerms.containsKey(id) || spTerms.containsKey(id.replace(":", "_"));
	}
	
	// isImaginfMethod(String id)
	public boolean isImaginfMethod(String id){
		return imTerms.containsKey(id) || imTerms.containsKey(id.replace(":", "_"));
	}
	
		
	// labelMatchesId(String label, String id)
	public boolean labelMatchesId(String label, String id){
		boolean res = false;
		if (anatomyTerms.containsKey(id)){
			res = anatomyTerms.get(id).equalsIgnoreCase(label);
		}
		else if (phenotypeTerms.containsKey(id)){
			res = phenotypeTerms.get(id).equalsIgnoreCase(label);
		}
		//TODO add FBBI, stage ontology
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
				spTerms.put(gr.getIdentifier(cls), gr.getLabel(cls));
			}
			
			classes = gr.getOWLClassDescendants(vm);
			for (OWLClass cls : classes){
				vmTerms.put(gr.getIdentifier(cls), gr.getLabel(cls));
			}
			
			classes = gr.getOWLClassDescendants(im);
			for (OWLClass cls : classes){
				imTerms.put(gr.getIdentifier(cls), gr.getLabel(cls));
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

		for (String path: anatomyOntologies){
			fillHashesFor(path, anatomyTerms);
		}
		for (String path: phenotypeOntologies){
			fillHashesFor(path, phenotypeTerms);				
		}
		for (String path: stageOntologies){
			fillHashesFor(path, stageTerms);
		}
		return false;
		
	}
	
	private void fillHashesFor(String path, HashMap<String, String> idLabelMap){

		try {
			OWLGraphWrapper gr;
			logger.info("Lading: " + path);
			gr = readOntologyFronUrl(path);
			
			Set<OWLClass> classes = gr.getAllOWLClasses();
			for (OWLClass cls : classes){
				idLabelMap.put(gr.getIdentifier(cls), gr.getLabel(cls));
			}
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		} catch (OBOFormatParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private OWLGraphWrapper readOntologyFronUrl (String ontIri) throws IOException, OWLOntologyCreationException, OBOFormatParserException{
		ParserWrapper pw = new ParserWrapper();

		OWLGraphWrapper graph = pw.parseToOWLGraph(ontIri);
		logger.debug("OWL file parsed and graph is created");
		logger.info("Ontology is initialised completely");
		return graph;
	}
}

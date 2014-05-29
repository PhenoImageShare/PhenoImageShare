package uk.ac.ebi.phis.utils.ontology;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.obolibrary.oboformat.parser.OBOFormatParserException;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLPropertyExpression;

import owltools.graph.OWLGraphEdge;
import owltools.graph.OWLGraphWrapper;
import owltools.io.ParserWrapper;

/**
 * Loads an ontology as a graph representation.
 */
public class OntologyMapper {

	transient Logger logger = Logger.getLogger(OntologyMapper.class);
	private String ONTOLOGY_IRI;
	private static OWLGraphWrapper graph;
	private static ArrayList<String> overProperties = null;
	
	
	private OWLGraphWrapper anatomyGraph ;
	private static String maBaseUrl;
	
	
	public OntologyMapper(OntologyMapperPredefinedTypes type){
		switch (type){
		case MA_MP:
			ONTOLOGY_IRI = "http://phenotype-ontologies.googlecode.com/svn/trunk/src/ontology/mp/mp-ext-merged.owl";
			overProperties = new ArrayList<String>();
			overProperties.add("http://purl.obolibrary.org/obo/BFO_0000052");
			overProperties.add("http://purl.obolibrary.org/obo/BFO_0000070");
			overProperties.add("http://purl.obolibrary.org/obo/mp/mp-logical-definitions#inheres_in_part_of");
			maBaseUrl = "http://purl.obolibrary.org/obo";
			try{
				anatomyGraph = readOntology("http://www.berkeleybop.org/ontologies/ma.owl");
				}catch(Exception e){
					e.printStackTrace();
					logger.error(e.getMessage());
				}
		}
		try{
			
			graph = readOntology(ONTOLOGY_IRI);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
	}
	
	
	public OntologyMapper(String ontologyUrl){
		ONTOLOGY_IRI = ontologyUrl;
		try{
			graph = readOntology(ONTOLOGY_IRI);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
	}
	
	public void setOverProperties(ArrayList<String> properties){
		overProperties = properties;
	}
	
	public OWLGraphWrapper getGraph(){
		return graph;
	}

	public String idToName(String id){
		return graph.getLabel(graph.getOWLObjectByIdentifier(id));
	}
	
	
	// Two methods to read ontologies from HTTP URLs
	protected  String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			String line;
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	public String getFTPAsString() {
		String content = "";
		try {
			URL url = new URL(ONTOLOGY_IRI);
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();
			content = getStringFromInputStream(is);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return content;
	}

	public HashSet<String> getMappings (String subjectTermId, ArrayList<String> relationsToFollow, String ontologyPrefix){
		HashSet<String> results = new HashSet<String> ();
				
		OWLClass subject = graph.getOWLClassByIdentifier(subjectTermId);
		try {
			if ( subject != null){
				
				Set<OWLClassExpression> eqList = subject.getEquivalentClasses(graph.getSourceOntology());
				for (OWLClassExpression eq: eqList){
					for (String rel : relationsToFollow){
						Pattern p = Pattern.compile(".*ObjectSomeValuesFrom\\(<" + rel + "> <(.*?)>\\)(.*)");
						Matcher m = p.matcher(eq.toString());
						while (m.find()){
							// add axiom if the object is MA
							if (m.group(1).contains("MA_")){
								System.out.println("1 " + m.group(1));
								results.add(m.group(1).split("/")[m.group(1).split("/").length-1]);
							}
						}
					}
				}				
			}
				System.out.println("Identified " + results.size()+ " referenced MA terms.");
		}catch(Exception e){
			e.printStackTrace();
		}
		return results;
	}
	
	public HashSet<String> getMappings (String subjectTermId, String ontologyPrefix){
		
		if (overProperties == null) {
			throw new Error("You must set overProperties before calling this function. ");
		}
		
		HashSet<String> results = new HashSet<String> ();
				
		OWLClass subject = graph.getOWLClassByIdentifier(subjectTermId, true);
		
		
		try {
			if ( subject != null){
				
				Set<OWLClassExpression> eqList = subject.getEquivalentClasses(graph.getSourceOntology());
				for (OWLClassExpression eq: eqList){
					for (String rel : overProperties){
						Pattern p = Pattern.compile(".*ObjectSomeValuesFrom\\(<" + rel + "> <(.*?)>\\)(.*)");
						Matcher m = p.matcher(eq.toString());
						while (m.find()){
							// add axiom if the object is MA
							if (m.group(1).contains("MA_")){
								results.add(m.group(1).split("/")[m.group(1).split("/").length-1]);
							}
						}
					}
				}				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return results;
	}
	
	private OWLGraphWrapper readOntology (String ontIri) throws IOException, OWLOntologyCreationException, OBOFormatParserException{
		ParserWrapper pw = new ParserWrapper();
		File temp = File.createTempFile("tempfile", ontIri.substring(ontIri.lastIndexOf(".")));
		BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
		bw.write(getFTPAsString());
		bw.close();

		OWLGraphWrapper graph = pw.parseToOWLGraph(temp.getAbsolutePath());
		temp.delete();
		logger.debug("OWL file parsed and graph is created");
		logger.info("Ontology is initialised completely");
		return graph;
	}
	
	public String getAnatomyLabel (String id){
		if (!id.startsWith("http")){ // it's just the id
			id = maBaseUrl + "/" +  id;
		}
		OWLClass x = anatomyGraph.getOWLClassByIdentifier(id);

		return anatomyGraph.getLabel(x);
	}
	
}

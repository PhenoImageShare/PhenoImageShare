package uk.ac.ebi.pish.utils.ontology;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import owltools.graph.OWLGraphWrapper;
import owltools.io.ParserWrapper;

/**
 * Loads an ontology as a graph representation.
 */
public class Ontology {

	transient Logger logger = Logger.getLogger(Ontology.class);

	private String ONTOLOGY_IRI = "http://svn.code.sf.net/p/fbbtdv/code/fbbt/releases/fbbt-simple.owl";
	private static OWLGraphWrapper graph;
	
	
	
	public Ontology(){
		try{
			ParserWrapper pw = new ParserWrapper();
			File temp = File.createTempFile("tempfile", ONTOLOGY_IRI.substring(ONTOLOGY_IRI.lastIndexOf(".")));
			BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
			bw.write(getFTPAsString());
			bw.close();

//			To load from local resources.
//			String newIri = IRI.create(Ontology.class.getClassLoader().getResource(ONTOLOGY_IRI)).toString();
//			OWLOntology chebiOntology = pw.parse(newIri);

			logger.debug("Ontology written to temp file");
			graph = pw.parseToOWLGraph(temp.getAbsolutePath());

			temp.delete();
			logger.debug("OWL file parsed and graph is created");
			logger.info("Ontology is initialised completely");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
	}
	
	public OWLGraphWrapper getGraph(){
		return graph;
	}

	public String idToName(String id){
		return graph.getLabel(graph.getOWLObjectByIdentifier(id));
	}
	
	
	// EMAP specific methods
	public Map<String, String> getEmapIdLabel(String tracerLabel , String stage){ // this only serves the 20 something anatomy terms  used in tracer
		HashMap<String, String> res = new HashMap<>();
		
		
		
		return res;
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

}

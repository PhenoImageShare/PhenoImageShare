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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.obolibrary.oboformat.parser.OBOFormatParserException;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.search.EntitySearcher;

import owltools.graph.OWLGraphWrapper;
import owltools.io.ParserWrapper;

/**
 * Loads an ontology as a graph representation.
 */
public class OntologyMapper {

	Logger logger = Logger.getLogger(OntologyMapper.class);

	private String ONTOLOGY_IRI;
	private static OWLGraphWrapper graph;
	private static ArrayList<String> overProperties = null;
	private OWLGraphWrapper anatomyGraph ;
	private static String baseUrl;
	
	
	public OntologyMapper(OntologyMapperPredefinedTypes type){
		
		switch (type){
		case MA_MP:
			
			ONTOLOGY_IRI = System.getProperty("user.home") + "/phis_ontologies/mp-ext-merged.owl";
			overProperties = new ArrayList<String>();
			overProperties.add("http://purl.obolibrary.org/obo/BFO_0000052");
			overProperties.add("http://purl.obolibrary.org/obo/BFO_0000070");
			overProperties.add("http://purl.obolibrary.org/obo/mp/mp-logical-definitions#inheres_in_part_of");
			baseUrl = "http://purl.obolibrary.org/obo";
			try{
				anatomyGraph = readOntology(System.getProperty("user.home") + "/phis_ontologies/ma.owl");
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
	
		
	public void setOverProperties(ArrayList<String> properties){
		overProperties = properties;
	}
	
	
	public OWLGraphWrapper getGraph(){
		return graph;
	}
	

	public String idToName(String id){
		return graph.getLabel(graph.getOWLObjectByIdentifier(id));
	}
	
	
	// Read ontologies from HTTP URLs
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
		System.out.println();
		return content;
	}

	
	public HashSet<String> getMappings (String subjectTermId, ArrayList<String> relationsToFollow, String ontologyPrefix){
		HashSet<String> results = new HashSet<String> ();
				
		OWLClass subject = graph.getOWLClassByIdentifier(subjectTermId);
		try {
			if ( subject != null){
				
				Collection<OWLClassExpression> eqList = EntitySearcher.getEquivalentClasses(subject, graph.getSourceOntology());
				for (OWLClassExpression eq: eqList){
					for (String rel : relationsToFollow){
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
			System.out.println("Identified " + results.size()+ " referenced MA terms.");
		}catch(Exception e){
			e.printStackTrace();
		}
		return results;
	}
	
	
	public List<String> getMappings (String subjectTermId, String ontologyPrefix){
		
		if (overProperties == null) {
			throw new Error("You must set overProperties before calling this function. ");
		}
		
		HashSet<String> results = new HashSet<String> ();				
		OWLClass subject = graph.getOWLClassByIdentifier(subjectTermId, true);		
		
		try {
			if ( subject != null){
				
				Collection<OWLClassExpression> eqList = EntitySearcher.getEquivalentClasses(subject, graph.getSourceOntology());
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
		
		List<String> res = new ArrayList<String>(results);
		return res;
	}
	
	
	private OWLGraphWrapper readOntology (String ontIri) throws IOException, OWLOntologyCreationException, OBOFormatParserException{
		
		ParserWrapper pw = new ParserWrapper();
		OWLGraphWrapper graph = pw.parseToOWLGraph(ontIri);
		logger.debug("OWL file parsed and graph is created");
		logger.info("Ontology is initialised completely");
		
		return graph;
		
	}
	
	
	public String getAnatomyLabel (String id){
		if (!id.startsWith("http")){ // it's just the id
			id = baseUrl + "/" +  id;
		}
		OWLClass x = anatomyGraph.getOWLClassByIdentifier(id);

		return anatomyGraph.getLabel(x);
	}
	
}

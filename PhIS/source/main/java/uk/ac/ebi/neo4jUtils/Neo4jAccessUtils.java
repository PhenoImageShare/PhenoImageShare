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
package uk.ac.ebi.neo4jUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;
import org.springframework.stereotype.Service;

import uk.ac.ebi.phis.exception.PhisSubmissionException;
import uk.ac.ebi.phis.release.DatasourceInstance;
import uk.ac.ebi.phis.release.OntologyInstance;
import uk.ac.ebi.phis.release.ReleaseDocument;
import uk.ac.ebi.phis.release.SpeciesData;


@Service 
public class Neo4jAccessUtils {

	 private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 private static RestGraphDatabase db;
	 private static RestCypherQueryEngine engine;
	 public static Label annLabel;
	 public static Label ontologyTermLabel;
	 public static Label channelLabel;
	 public static Label imageLabel;
	 public static Label userLabel;
	 public static Label releaseLabel;
	 public static Label datasourceLabel;
	 public static Label speciesLabel;
	 public static Label ontologyLabel;
	 
	public Neo4jAccessUtils (String dbURI) {
		
		db = new RestGraphDatabase(dbURI); 
        engine = new RestCypherQueryEngine( db.getRestAPI() );
        annLabel = createLabel("Annotation");
        ontologyTermLabel = createLabel("OntologyTerm");
        channelLabel = createLabel("Channel");
        imageLabel = createLabel("Image");
        userLabel = createLabel("User");   
        releaseLabel = createLabel("Release");
        datasourceLabel = createLabel("DataSource");
        speciesLabel = createLabel("Species");
        ontologyLabel = createLabel("Ontology");
        
	}
	
	public void writeRelease(ReleaseDocument releaseDoc) 
	throws PhisSubmissionException{

		Node release = getOrCreateNode(releaseDoc.getReleaseVersion(), releaseDoc.getReleaseVersion(), releaseLabel, true);

		release.setProperty(ReleaseProperties.VERSION.toString(), releaseDoc.getReleaseVersion().toString());
		release.setProperty(ReleaseProperties.IMAGES_NUMBER.toString(), releaseDoc.getNumberOfImages());
		release.setProperty(ReleaseProperties.GENES_NUMBER.toString(), releaseDoc.getGeneIds().size());
		release.setProperty(ReleaseProperties.ROIS_NUMBER.toString(), releaseDoc.getNumberOfRois());
		
		for (DatasourceInstance datasource : releaseDoc.getDatasourcesUsed()){
			
			String id = releaseDoc.getReleaseVersion() + "_" + datasource.getName();
			Node node = getOrCreateNode(id, datasource.getName(), datasourceLabel, true);
			node.setProperty(ReleaseProperties.IMAGES_NUMBER.toString(), datasource.getNumberOfImages());
			node.setProperty(ReleaseProperties.EXPORT_DATE.toString(), "" + datasource.getExportDate().getYear() + "-" + datasource.getExportDate().getMonth() + "-" + datasource.getExportDate().getDay());
			node.setProperty(ReleaseProperties.NAME.toString(), datasource.getName());
			
			addUnidirectionalRelation(release, Neo4jRelationship.CONTAINS, node, true);
		}
		
		for (SpeciesData species : releaseDoc.getSpeciesWithData()){
			
			String id = releaseDoc.getReleaseVersion() + "_" + species.getName();
			Node node = getOrCreateNode(id, species.getName(), speciesLabel, true);
			node.setProperty(ReleaseProperties.IMAGES_NUMBER.toString(), species.getNumberOfImages());
			node.setProperty(ReleaseProperties.NAME.toString(), species.getName());

			addUnidirectionalRelation(release, Neo4jRelationship.CONTAINS, node, true);
		}
		
		for (OntologyInstance oi : releaseDoc.getOntologiesUsed()){
			
			String id = releaseDoc.getReleaseVersion() + "_" + oi.getVersion();
			Node node = getOrCreateNode(id, oi.getName(), ontologyLabel, true);
			node.setProperty(ReleaseProperties.VERSION.toString(), oi.getVersion());
			node.setProperty(ReleaseProperties.NAME.toString(), oi.getName());		
			
			addUnidirectionalRelation(release, Neo4jRelationship.CONTAINS, node, true);
			
		}
		
	}
	
	
	
	public void createAnnotation( String userId, String annotationId, String associatedImageId, List<Float> xCoordinates,
	List<Float> yCoordinates, List<Float> zCoordinates, List<String> associatedChannelId, 
	List<String> depictedAnatomyId, List<String> depictedAnatomyFreetext, List<String> depictedAnatomyTerm,
    List<String> abnInAnatomyId, List<String> abnInAnatomyFreetext,  List<String> abnInAnatomyTerm, 
    List<String> phenotypeId, List<String> phenotypeFreetext, List<String> phenotypeTerm, 
    List<String> observation, 
    List<String> expressedInAnatomyId, List<String> expressedInAnatomyTerm, List<String> expressedInAnatomyFreetext) 
    throws PhisSubmissionException  {
	
		Date today = new Date();
		createAnnotationWithDates(userId, annotationId, associatedImageId, xCoordinates, yCoordinates, zCoordinates, associatedChannelId,
			depictedAnatomyId, depictedAnatomyFreetext, depictedAnatomyTerm, abnInAnatomyId, abnInAnatomyFreetext, abnInAnatomyTerm, phenotypeId, 
			phenotypeFreetext, phenotypeTerm, observation, expressedInAnatomyId, expressedInAnatomyTerm, expressedInAnatomyFreetext, 
			today, today);
	}
	
	
	public void createAnnotationWithDates( String userId, String annotationId, String associatedImageId, List<Float> xCoordinates,
	List<Float> yCoordinates, List<Float> zCoordinates, List<String> associatedChannelId, 
	List<String> depictedAnatomyId, List<String> depictedAnatomyFreetext, List<String> depictedAnatomyTerm,
    List<String> abnInAnatomyId, List<String> abnInAnatomyFreetext,  List<String> abnInAnatomyTerm, 
    List<String> phenotypeId, List<String> phenotypeFreetext, List<String> phenotypeTerm, 
    List<String> observation, 
    List<String> expressedInAnatomyId, List<String> expressedInAnatomyTerm, List<String> expressedInAnatomyFreetext,
    Date creationDate, Date lastModifiedDate) 
    throws PhisSubmissionException{
	
		
			Node user;
			Node image;
			Node annotation;
					
			// Don't create another object with the same ID
			if (existsId(annotationId, imageLabel)){
				throw getEmptyIdException();
			} 
			
			// there must be at least one annotation with the ROI coordinates
			if (depictedAnatomyId == null && depictedAnatomyFreetext == null && abnInAnatomyId == null && abnInAnatomyFreetext == null &&
				phenotypeId == null && phenotypeFreetext == null & observation == null && expressedInAnatomyFreetext == null && expressedInAnatomyId == null){
				
				throw getNoAnnotationException();
			}
			
			// ontology objects label-id should be 1-1.
			
			if(!(ontologyTermsValid(abnInAnatomyId, abnInAnatomyTerm) && ontologyTermsValid(phenotypeId, phenotypeTerm) 
				&& ontologyTermsValid(expressedInAnatomyId, expressedInAnatomyTerm) && ontologyTermsValid(depictedAnatomyId, depictedAnatomyTerm))){
				throw getNotMatchingIdAndTerm();
			}
			
			
			user = getOrCreateNode(userId, userId, userLabel, false);
			image = getOrCreateNode(associatedImageId,associatedImageId, imageLabel, false);
					
			
			
			annotation = addAnnotationNode(annotationId, observation, creationDate, lastModifiedDate, xCoordinates, yCoordinates, zCoordinates);
			
			addBidirectionalRelation(annotation, Neo4jRelationship.HAS_ASSOCIATED_IMAGE, image);
			addBidirectionalRelation(annotation, Neo4jRelationship.CREATED_BY, user);
			
			if (associatedChannelId != null){
				for (String s: associatedChannelId){
					Node channel = getOrCreateNode(s, s, channelLabel, false);
					addBidirectionalRelation(annotation, Neo4jRelationship.HAS_ASSOCIATED_CHANNEL, channel);
				}
			}		
			if (depictedAnatomyId != null){
				for (int i = 0; i < depictedAnatomyId.size(); i++){
					Node ot = getOrCreateNode(depictedAnatomyId.get(i), depictedAnatomyTerm.get(i), ontologyTermLabel, false);
					addBidirectionalRelation(annotation, Neo4jRelationship.DEPICTS, ot);
				}				
			}
			if (abnInAnatomyId != null){
				for (int i = 0; i < abnInAnatomyId.size(); i++) {
					Node ot = getOrCreateNode(abnInAnatomyId.get(i), abnInAnatomyTerm.get(i), ontologyTermLabel, false);
					addBidirectionalRelation(annotation, Neo4jRelationship.DEPICTS_ABNORMALITY_IN, ot);
				}
			}
			if (phenotypeId != null){
				for (int i = 0; i < phenotypeId.size(); i++) {
					Node ot = getOrCreateNode(phenotypeId.get(i), phenotypeTerm.get(i), ontologyTermLabel, false);
					addBidirectionalRelation(annotation, Neo4jRelationship.DEPICTS_PHENOTYPE, ot);
				}
			}
			if (expressedInAnatomyId != null){
				for (int i = 0; i < expressedInAnatomyId.size(); i++) {
					Node ot = getOrCreateNode(expressedInAnatomyId.get(i), expressedInAnatomyTerm.get(i), ontologyTermLabel, false);
					addBidirectionalRelation(annotation, Neo4jRelationship.EXPRESSED_IN, ot);
				}
			}
			if (depictedAnatomyFreetext != null){
				for (String s: depictedAnatomyFreetext){
					annotation = setAnnotationProperty(annotation, AnnotationProperties.DEPICTED_ANATOMY_FREETEXT, s);
				}
			}
			if (expressedInAnatomyFreetext != null){
				for (String s: expressedInAnatomyFreetext){
					annotation = setAnnotationProperty(annotation, AnnotationProperties.EXPRESSION_INANATOMY_FREETEXT, s);
				}
			}
			if (abnInAnatomyFreetext != null){
				for (String s: abnInAnatomyFreetext){
					annotation = setAnnotationProperty(annotation, AnnotationProperties.ABN_IN_ANATOMY_FREETEXT, s);
				}
			}
			if (phenotypeFreetext != null){
				for (String s: phenotypeFreetext){
					annotation = setAnnotationProperty(annotation, AnnotationProperties.PHENOTYPE_FREETEXT, s);
				}
			}
		
	}
	
	
	private boolean ontologyTermsValid(List<String> ids, List<String> labels){
		return ((ids != null && labels != null && ids.size() == labels.size()) || (ids == null && labels == null)); 
	}
		
	
	public void addBidirectionalRelation(Node fromNode, Neo4jRelationship relation, Node toNode) 
	throws PhisSubmissionException{

		Boolean toRight = existsUnidirectionalRelationship(fromNode, relation, toNode);
		Boolean toLeft = existsUnidirectionalRelationship(toNode, relation, fromNode);
		if ( toRight && toLeft){
			throw new PhisSubmissionException(PhisSubmissionException.RELATIONSHIP_EXISTS_EXCEPTION);
		}
		try ( Transaction tx = db.beginTx() )
        {				
			if (!toRight){
				fromNode.createRelationshipTo( toNode, relation );
			}
			if (!toLeft){
				toNode.createRelationshipTo( fromNode, relation );
			}
			tx.success();
			tx.close();
        }   
	}
	
	
	public void addUnidirectionalRelation(Node fromNode, Neo4jRelationship relation, Node toNode, boolean overwrite) 
	throws PhisSubmissionException{
		
		if (existsUnidirectionalRelationship(fromNode, relation, toNode) ){
			if (!overwrite){
				throw new PhisSubmissionException(PhisSubmissionException.RELATIONSHIP_EXISTS_EXCEPTION);
			} else {
				deleteUnidirectionalRelationship(fromNode, relation, toNode);
			}		
		}
		try ( Transaction tx = db.beginTx() )  {
			
			fromNode.createRelationshipTo( toNode, relation );
			tx.success();
			tx.close();
        }   
	}
	
	
	public Node addAnnotationNode(String id, List<String> observation, Date creationDate, Date lastModifiedDate, List<Float> xCoordinates, 
	List<Float> yCoordinates, List<Float> zCoordinates) 
	throws PhisSubmissionException{
		
		Node myNode = null;
		if (id == null || id.equals("")){
			throw getEmptyIdException();
		} 
		else if (existsId(id, annLabel)){
			throw getIdAlreadyExists(id);
		}
		else if (xCoordinates == null || yCoordinates == null){
			throw new PhisSubmissionException(PhisSubmissionException.NO_COORDINATES_EXCEPTION);
		}
		
		try ( Transaction tx = db.beginTx() ) {
			
            myNode = db.createNode(annLabel);
            myNode.setProperty( AnnotationProperties.ID.name(), id );
            if (observation != null){
            	myNode.setProperty( AnnotationProperties.OBSERVATION.name(), observation );
            }
            myNode.setProperty( AnnotationProperties.CREATION_DATE.name(), DATE_FORMAT.format(creationDate) );
            if (lastModifiedDate != null){
            	myNode.setProperty( AnnotationProperties.LAST_MODIFIED_DATE.name(), DATE_FORMAT.format(lastModifiedDate) );
            }
            myNode.setProperty( AnnotationProperties.X_COORDINATES.name(), xCoordinates );
            myNode.setProperty( AnnotationProperties.Y_COORDINATES.name(), yCoordinates );
            if (zCoordinates != null){
            	myNode.setProperty(AnnotationProperties.Z_COORDINATES.name(), zCoordinates );
            }
            tx.success();
            tx.close();
        }
		return myNode;
	}
	
	
	private Node setAnnotationProperty(Node node, AnnotationProperties property, String value){
		try ( Transaction tx = db.beginTx() )  {
			node.setProperty(property.name(), value);
			tx.success();
        	tx.close();
	    }
		
		return node;
	}
	
	
	public Node addUser(String id) 
	throws PhisSubmissionException{
		return createNode(id, userLabel);	
	}
	
	
	public Node addOntologyTerm(String id, String termLabel) 
	throws PhisSubmissionException{
		Node ot = createNode(id, ontologyTermLabel);
		if (termLabel != null){
			try ( Transaction tx = db.beginTx() )
	        {
				ot.setProperty("label", termLabel);
	            tx.success();
	            tx.close();
	        }
		}
        return ot;
	}
	
	
	public Node addImage(String id) 
	throws PhisSubmissionException{
		return createNode(id, imageLabel);
	}
	
	
	public Node addChannel(String id) 
	throws PhisSubmissionException{
		return createNode(id, channelLabel);
	}
	
	
	private Node createNode(String id, Label label) 
	throws PhisSubmissionException{
		Node myNode;
		System.out.println("Label + " + label);
		if (id == null || id.equals("")){
			throw getEmptyIdException();
		} 
		else if (existsId(id, label)){
			throw getIdAlreadyExists(id);
		}
		try ( Transaction tx = db.beginTx() )
        {
            myNode = db.createNode(label);
            myNode.setProperty( AnnotationProperties.ID.name(), id );
            tx.success();
            tx.close();
        }   
		return myNode;
	}
	
	
	private PhisSubmissionException getEmptyIdException(){
		return new PhisSubmissionException(PhisSubmissionException.ID_NOT_FOUND_EXCEPTION_MESSAGE);
	}
	
	
	private PhisSubmissionException getIdAlreadyExists(String id){
		return new PhisSubmissionException(PhisSubmissionException.EXISTING_ID_EXCEPTION_MESSAGE + id);
	}
	
	
	private PhisSubmissionException getNotMatchingIdAndTerm(){
		return new PhisSubmissionException(PhisSubmissionException.NOT_MATCHING_ID_AND_TERM_ARRAYS);
	}
	
	
	private  PhisSubmissionException getNoAnnotationException(){
		return new PhisSubmissionException(PhisSubmissionException.NO_ANNOTATION_EXCEPTION);
	}
	
	
	public void addSomething(){
	
		try ( Transaction tx = db.beginTx() )
        {
            Node myNode = db.createNode();
            myNode.setProperty( "name", "my node" );
            tx.success();
            tx.close();
        }
        
	}
	
	
	public String readAnnotations (){
		return readThisQuery("match (n:Annotation) return n");
	}
	

	public String readAllNodes(){
		return readThisQuery("match (n) return n."+AnnotationProperties.ID.name());
	}
	
	
	public String readSomething(){

        String resultString = "";        
        try ( Transaction ignored = db.beginTx() )
        {
    	    resultString = engine.query( "match (n {name: 'my node'}) return n, n.name", null ).toString();
            System.out.println("resultString: " +  resultString);
        }
        
        return resultString;
	}
	
	public List<Node> getNodesByLabel(Label label){

		List<Node> nodes = new ArrayList<>();
		QueryResult<Map<String, Object>> result = engine.query( "match (obj:" + label + ") return obj", null);
		
		for (Map<String, Object> row : result) {
        	nodes.add((Node) row.get("obj")); // should have only one anyway as ids are unique
        }
		
        return nodes;
        
	}
	
	
	public List<Node> getFirtsNodesFor(Long rootNodeId, Neo4jRelationship relation, String toNodeLabel){

		List<Node> nodes = new ArrayList<>();
		QueryResult<Map<String, Object>> result = engine.query( "match (obj)-[r:" + relation.name() + "]-(n:" + toNodeLabel + ") WHERE id(obj)=" + rootNodeId + " return n", null);
		
		for (Map<String, Object> row : result) {
        	nodes.add((Node) row.get("n")); // should have only one anyway as ids are unique
        }
		
        return nodes;
        
	}
	
	
	public Node getOrCreateNode(String id, String name, Label label, boolean overWrite) 
	throws PhisSubmissionException{
		
		Node res ;
		
		if ( existsId(id, label) ){
			if (overWrite){
				deleteNodeWithRelations(id);
				res = createNode(id, label);
			} else {
				res = getNodeById(id);
			}
		} else {
			res = createNode(id, label);
		}
		res.setProperty(AnnotationProperties.ID.name(), name);
		
		return res;
		
	}
	
		
	public Node getNodeById(String id){
		
		QueryResult<Map<String, Object>> result = engine.query( "match (obj) WHERE obj."+AnnotationProperties.ID.name()+"=\"" + id + "\" return id(obj)" , null);
		
		for (Map<String, Object> row : result) {
        //	return (Node) row.get("obj"); // should have only one anyway as ids are unique
			System.out.println("ID: " + ((Number)row.get("id(obj)")).longValue());
			return db.getNodeById(((Number)row.get("id(obj)")).longValue());
        }
		
        return null;
        
	}
		
	
	public Label createLabel(String label){
		Label l = null;
		try ( Transaction tx = db.beginTx(); ){
			l = DynamicLabel.label(label);
			tx.close();
		}
		
		return l;
		
	}
	
		
	public void addUniqueIdConstraint(Label l){
		
		try ( Transaction tx = db.beginTx(); ){
			db.schema().constraintFor(l).assertPropertyIsUnique(AnnotationProperties.ID.name()).create();
			tx.close();
		}
	}
		
	
	public boolean existsId(String id, Label label){
		
		System.out.println("ENGINE:: " + engine.toString());
		System.out.println("DATABASE ::: " + db.getRestAPI().getBaseUri());
		
		return (engine.query( "MATCH (obj:" + label + ") WHERE obj."+AnnotationProperties.ID.name()+"=\"" + id + "\" RETURN obj" , null).iterator().hasNext());
		
	}	
	
	
	public String readThisQuery(String query){
		
        try ( Transaction ignored = db.beginTx() )
        {
            String resultString = "";            
            for (Map<String, Object> row : engine.query( query , null)) {
            	for (String key : row.keySet()){
            		resultString += row.get(key)+ "\t";
            	}
            	resultString += "\n";
            }
            ignored.close();
            
            return resultString;
            
        }
	}		
	
	
	public String getDirectRelationsTo(String nodeId){
		
		String query = "MATCH (a)-[r]-(b) WHERE b."+AnnotationProperties.ID.name()+"=\"" + nodeId + "\" RETURN a."+AnnotationProperties.ID.name()+", type(r), b."+AnnotationProperties.ID.name();
		
		return readThisQuery(query);
		
	}
	
	
	public void deleteUnidirectionalRelationship(Node fromNode, Neo4jRelationship relation, Node toNode)
	throws PhisSubmissionException	{
		
		String rel;
		String from; 
		String to;
		if (fromNode == null || toNode == null){
			throw new PhisSubmissionException(PhisSubmissionException.ID_NOT_FOUND_EXCEPTION_MESSAGE);
		}
		try ( Transaction tx = db.beginTx() )
        {
			from = fromNode.getProperty(AnnotationProperties.ID.name()).toString();
			to = toNode.getProperty(AnnotationProperties.ID.name()).toString();
			rel = relation.name();
        }
		String testQuery = "MATCH (a {"+AnnotationProperties.ID.name()+":\"" + from + "\"})-[r:" + rel + "]->(b {"+AnnotationProperties.ID.name()+":\"" + to + "\"}) DELETE a,r,b";
	 	System.out.println("DELETE query " + testQuery);
	}
	
	
	public boolean existsUnidirectionalRelationship(Node fromNode, Neo4jRelationship relation, Node toNode){
		
		String rel;
		String from; 
		String to;
		if (fromNode == null || toNode == null){
			return false;
		}
		try ( Transaction tx = db.beginTx() )
        {
			from = fromNode.getProperty(AnnotationProperties.ID.name()).toString();
			to = toNode.getProperty(AnnotationProperties.ID.name()).toString();
			rel = relation.name();
        }
		String testQuery = "MATCH (from {"+AnnotationProperties.ID.name()+":\"" + from + "\"})-[:" + rel + "]->(to {"+AnnotationProperties.ID.name()+":\"" + to + "\"}) RETURN to";
	 	System.out.println("TEST query " + testQuery);
		return (engine.query( testQuery, null ).iterator().hasNext());
	}
	
	
	public void deleteNodeWithRelations(String nodeId){
		
		try ( Transaction tx = db.beginTx() )
        {
			String query = "MATCH (n {"+AnnotationProperties.ID.name()+":'" + nodeId + "'}) OPTIONAL MATCH (n {"+AnnotationProperties.ID.name()+":'" + nodeId + "'})-[r]-() DELETE n,r";
			System.out.println("DELETE query is : " + query);
			engine.query( query, null );
			tx.success();
			tx.close();
        }
		
	}
	
	
	public void updateAnnotation(String userId, String annotationId, String associatedImageId, List<Float> xCoordinates,
		List<Float> yCoordinates, List<Float> zCoordinates, List<String> associatedChannelId, 
		List<String> depictedAnatomyId, List<String> depictedAnatomyFreetext, List<String> depictedAnatomyTerm,
		List<String> abnInAnatomyId, List<String> abnInAnatomyFreetext,  List<String> abnInAnatomyTerm, 
		List<String> phenotypeId, List<String> phenotypeFreetext, List<String> phenotypeTerm, 
		List<String> observation, 
		List<String> expressedInAnatomyId, List<String> expressedInAnatomyTerm, List<String> expressedInAnatomyFreetext) 
	throws PhisSubmissionException{
		
		Date today = new Date();
		
		if (existsId(annotationId, annLabel)){
			if (hasSameUser(userId, annotationId)){
				deleteNodeWithRelations(annotationId);
				createAnnotationWithDates(userId, annotationId, associatedImageId, xCoordinates, yCoordinates, zCoordinates, associatedChannelId,
					depictedAnatomyId, depictedAnatomyFreetext, depictedAnatomyTerm, abnInAnatomyId, abnInAnatomyFreetext, abnInAnatomyTerm, 
					phenotypeId, phenotypeFreetext, phenotypeTerm, observation, expressedInAnatomyId, expressedInAnatomyTerm, expressedInAnatomyFreetext, 
					today, today);
			}
			else {
				throw new PhisSubmissionException("User provided does not match the owner.");
			}
		} else {
			throw new PhisSubmissionException("Annotation id does not exist.");
		}
	
	}
	
	
	public String getCreationDate(String annId){
		
		String date = null;
		try ( Transaction tx = db.beginTx() )
        {
			Node ann = getNodeById(annId);
            date = (String)ann.getProperty(AnnotationProperties.CREATION_DATE.name());
            tx.close();
        }
		System.out.println("Date : " + date);
		
		return date;
		
	}
	
	
	public boolean hasSameUser(String userId, String nodeId){
		
		Node node = getNodeById(nodeId);
		Node user = getNodeById(userId);
		return existsUnidirectionalRelationship(node, Neo4jRelationship.CREATED_BY, user);
	}
	
	
	public void closeDb(){
		
		db.shutdown();
		
	}
}

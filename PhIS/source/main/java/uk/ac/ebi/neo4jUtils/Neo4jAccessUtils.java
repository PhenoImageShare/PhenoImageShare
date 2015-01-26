package uk.ac.ebi.neo4jUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.collection.IteratorUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service 
public class Neo4jAccessUtils {

	 private static final String DB_PATH = "/Users/tudose/Documents/servers/neo4j-community-2.1.6/data/phis_2.1.6.db";
	 private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 private static String resultString;
	 private static GraphDatabaseService db;
	 private static ExecutionEngine engine;
	 public static Label annLabel;
	 public static Label ontologyTermLabel;
	 public static Label channelLabel;
	 public static Label imageLabel;
	 public static Label userLabel;
	 private static final String EXISTING_ID_EXCEPTION_MESSAGE = "Id already exists. The id provided is ";
	 private static final String EMPTY_ID_EXCEPTION_MESSAGE = "No valid id provided. Please provide one and run again.";
	 private static final String NO_COORDINATES_EXCEPTION = "Please provide coordinates for each Annotation. At least x and y coordinates are required.";
	 private static final String RELATIONSHIP_EXISTS_EXCEPTION = "Relationship exits and was not added again.";	
	 private static final String NO_ANNOTATION_EXCEPTION = "We cannot add fill in ROIs with no annotations attached. Please provide some observation, phenotype, expression or anatomy information. ";
	 private static final String NOT_MATCHING_ID_AND_TERM_ARRAYS = "The label and id arrays do not have matching lengths. Ontology terms should be provided with both label and id in parallel arrays.";
	 
	public Neo4jAccessUtils () {
		
        db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
        engine = new ExecutionEngine( db );
        annLabel = createLabel("Annotation");
        ontologyTermLabel = createLabel("OntologyTerm");
        channelLabel = createLabel("Channel");
        imageLabel = createLabel("Image");
        userLabel = createLabel("User");

 //       addUniqueIdConstraint(annLabel);
        
	}
	
	
	public boolean createAnnotation( String userId, String annotationId, String associatedImageId, List<Float> xCoordinates,
	List<Float> yCoordinates, List<Float> zCoordinates, List<String> associatedChannelId, 
	List<String> depictedAnatomyId, List<String> depictedAnatomyFreetext, List<String> depictedAnatomyTerm,
    List<String> abnInAnatomyId, List<String> abnInAnatomyFreetext,  List<String> abnInAnatomyTerm, 
    List<String> phenotypeId, List<String> phenotypeFreetext, List<String> phenotypeTerm, 
    List<String> observation, 
    List<String> expressedInAnatomyId, List<String> expressedInAnatomyTerm, List<String> expressedInAnatomyFreetext, 
    Model model )  {
	
		Date today = new Date();
		try{
			createAnnotationWithDates(userId, annotationId, associatedImageId, xCoordinates, yCoordinates, zCoordinates, associatedChannelId,
			depictedAnatomyId, depictedAnatomyFreetext, depictedAnatomyTerm, abnInAnatomyId, abnInAnatomyFreetext, abnInAnatomyTerm, phenotypeId, 
			phenotypeFreetext, phenotypeTerm, observation, expressedInAnatomyId, expressedInAnatomyTerm, expressedInAnatomyFreetext, 
			today, today);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void createAnnotationWithDates( String userId, String annotationId, String associatedImageId, List<Float> xCoordinates,
	List<Float> yCoordinates, List<Float> zCoordinates, List<String> associatedChannelId, 
	List<String> depictedAnatomyId, List<String> depictedAnatomyFreetext, List<String> depictedAnatomyTerm,
    List<String> abnInAnatomyId, List<String> abnInAnatomyFreetext,  List<String> abnInAnatomyTerm, 
    List<String> phenotypeId, List<String> phenotypeFreetext, List<String> phenotypeTerm, 
    List<String> observation, 
    List<String> expressedInAnatomyId, List<String> expressedInAnatomyTerm, List<String> expressedInAnatomyFreetext,
    Date creationDate, Date lastModifiedDate) 
	throws IllegalArgumentException {
	
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
		
		if(depictedAnatomyId.size() != depictedAnatomyTerm.size() || abnInAnatomyId.size() != abnInAnatomyTerm.size() || phenotypeId.size() != phenotypeTerm.size() || expressedInAnatomyId.size() != expressedInAnatomyTerm.size()){
			throw getNotMatchingIdAndTerm();
		}
		
		
		user = getOrCreateNode(userId, userId, userLabel);
		image = getOrCreateNode(associatedImageId,associatedImageId, imageLabel);
				
		
		
		annotation = addAnnotationNode(annotationId, observation, creationDate, lastModifiedDate, xCoordinates, yCoordinates, zCoordinates);
		
		addBidirectionalRelation(annotation, Neo4jRelationship.HAS_ASSOCIATED_IMAGE, image);
		addBidirectionalRelation(annotation, Neo4jRelationship.CREATED_BY, user);
		
		if (associatedChannelId != null){
			for (String s: associatedChannelId){
				Node channel = getOrCreateNode(s, s, channelLabel);
				addBidirectionalRelation(annotation, Neo4jRelationship.HAS_ASSOCIATED_CHANNEL, channel);
			}
		}		
		if (depictedAnatomyId != null){
			for (int i = 0; i < depictedAnatomyId.size(); i++){
				Node ot = getOrCreateNode(depictedAnatomyId.get(i), depictedAnatomyTerm.get(i), ontologyTermLabel);
				addBidirectionalRelation(annotation, Neo4jRelationship.DEPICTS, ot);
			}				
		}
		if (abnInAnatomyId != null){
			for (int i = 0; i < abnInAnatomyId.size(); i++) {
				Node ot = getOrCreateNode(abnInAnatomyId.get(i), abnInAnatomyTerm.get(i), ontologyTermLabel);
				addBidirectionalRelation(annotation, Neo4jRelationship.DEPICTS_ABNORMALITY_IN, ot);
			}
		}
		if (phenotypeId != null){
			for (int i = 0; i < phenotypeId.size(); i++) {
				Node ot = getOrCreateNode(phenotypeId.get(i), phenotypeTerm.get(i), ontologyTermLabel);
				addBidirectionalRelation(annotation, Neo4jRelationship.DEPICTS_PHENOTYPE, ot);
			}
		}
		if (expressedInAnatomyId != null){
			for (int i = 0; i < expressedInAnatomyId.size(); i++) {
				Node ot = getOrCreateNode(expressedInAnatomyId.get(i), expressedInAnatomyTerm.get(i), ontologyTermLabel);
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
		
	public void addBidirectionalRelation(Node fromNode, Neo4jRelationship relation, Node toNode) throws IllegalArgumentException{

		Boolean toRight = existsUnidirectionalRelationship(fromNode, relation, toNode);
		Boolean toLeft = existsUnidirectionalRelationship(toNode, relation, fromNode);
		if ( toRight && toLeft){
			throw new IllegalArgumentException(RELATIONSHIP_EXISTS_EXCEPTION);
		}
		try ( Transaction tx = db.beginTx() )
        {				
			if (!toRight){
				fromNode.createRelationshipTo( toNode, relation );
			}
			if (!toLeft){
				toNode.createRelationshipTo( fromNode, relation );
			}
			for (Relationship r: fromNode.getRelationships()){
				System.out.println(" Found this rel : " + r + " of type " + r.getType() + " from " + r.getStartNode() + " to " + r.getEndNode());
			}
			tx.success();
			tx.close();
        }   
	}
	
	public void addUnidirectionalRelation(Node fromNode, Neo4jRelationship relation, Node toNode) throws Exception{
		
		if (existsUnidirectionalRelationship(fromNode, relation, toNode)){
			throw new Exception(RELATIONSHIP_EXISTS_EXCEPTION);
		}
		try ( Transaction tx = db.beginTx() )  {
			
			fromNode.createRelationshipTo( toNode, relation );
			for (Relationship rel: fromNode.getRelationships()){
				System.out.println(" Found this rel : " + rel + " of type " + rel.getType() + " from " + rel.getStartNode() + " to " + rel.getEndNode());
			}
			tx.success();
			tx.close();
        }   
	}
	
	public Node addAnnotationNode(String id, List<String> observation, Date creationDate, Date lastModifiedDate, List<Float> xCoordinates, 
	List<Float> yCoordinates, List<Float> zCoordinates) throws IllegalArgumentException{
		
		Node myNode = null;
		if (id == null || id.equals("")){
			throw getEmptyIdException();
		} 
		else if (existsId(id, annLabel)){
			throw getIdAlreadyExists(id);
		}
		else if (xCoordinates == null || yCoordinates == null){
			throw new IllegalArgumentException(NO_COORDINATES_EXCEPTION);
		}
		
		try ( Transaction tx = db.beginTx() ) {
			
            myNode = db.createNode(annLabel);
            myNode.setProperty( AnnotationProperties.id.name(), id );
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
	
	public Node addUser(String id) throws IllegalArgumentException{
		return createNode(id, userLabel);	
	}
	
	public Node addOntologyTerm(String id, String termLabel) throws IllegalArgumentException{
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
	
	public Node addImage(String id) throws IllegalArgumentException{
		return createNode(id, imageLabel);
	}
	
	public Node addChannel(String id) throws IllegalArgumentException{
		return createNode(id, channelLabel);
	}
	
	private Node createNode(String id, Label label) throws IllegalArgumentException{
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
            myNode.setProperty( "id", id );
            tx.success();
            tx.close();
        }   
		return myNode;
	}
	
	
	private IllegalArgumentException getEmptyIdException(){
		return new IllegalArgumentException(EMPTY_ID_EXCEPTION_MESSAGE);
	}
	
	private IllegalArgumentException getIdAlreadyExists(String id){
		return new IllegalArgumentException(EXISTING_ID_EXCEPTION_MESSAGE + id);
	}
	
	private IllegalArgumentException getNotMatchingIdAndTerm(){
		return new IllegalArgumentException(NOT_MATCHING_ID_AND_TERM_ARRAYS);
	}
	
	private  IllegalArgumentException getNoAnnotationException(){
		return new IllegalArgumentException (NO_ANNOTATION_EXCEPTION);
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
		return readThisQuery("match (n) return n");
	}
	
	public String readSomething(){

		String nodeResult;
		String rows = "";		
        ExecutionResult result;
        
        try ( Transaction ignored = db.beginTx() )
        {
            result = engine.execute( "match (n {name: 'my node'}) return n, n.name" );
            Iterator<Node> n_column = result.columnAs( "n" );
            for ( Node node : IteratorUtil.asIterable( n_column ) )
            {
                // note: we're grabbing the name property from the node,
                // not from the n.name in this case.
                nodeResult = node + ": " + node.getProperty( "name" );
            }
            ignored.close();
        }

        // the result is now empty, get a new one
        result = engine.execute( "match (n {name: 'my node'}) return n, n.name" );
        for ( Map<String, Object> row : result )
        {
            for ( Entry<String, Object> column : row.entrySet() )
            {
                rows += column.getKey() + ": " + column.getValue() + "; ";
            }
            rows += "\n";
        }
        resultString = engine.execute( "match (n {name: 'my node'}) return n, n.name" ).dumpToString();
        System.out.println("resultString: " +  resultString);
        return resultString;
	}
	
	
	public Node getOrCreateNode(String id, String name, Label label) throws IllegalArgumentException{
		Node res ;
		if ( existsId(id, label) ){
			res = getNodeById(id);
		} else {
			res = createNode(id, label);
		}
		res.setProperty(AnnotationProperties.name.name(), name);
		return res;
	}
	
	public Node getNodeById(String id){
		ExecutionResult result = engine.execute( "match (obj) WHERE obj.id=\"" + id + "\" return obj" );
        Iterator<Node> n_column = result.columnAs( "obj" );
        for ( Node node : IteratorUtil.asIterable( n_column ) )
        {
            return node; // should have only one anyway as ids are unique
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
			db.schema().constraintFor(l).assertPropertyIsUnique("id").create();
			tx.close();
		}
	}
	
	public boolean existsId(String id, Label label){
		System.out.println("Label:: " + label);
		return (engine.execute( "MATCH (obj:" + label + ") WHERE obj.id=\"" + id + "\" RETURN obj" ).iterator().hasNext());
	}
	
	public String readThisQuery(String query){
        try ( Transaction ignored = db.beginTx() )
        {
            resultString = engine.execute( query ).dumpToString();
            System.out.println("resultString: " +  resultString);
            ignored.close();
            return resultString;
        }
	}	
	
	public String getDirectRelationsTo(String nodeId){
		String query = "MATCH (a)-[:" + Neo4jRelationship.CREATED_BY + "]-(user) RETURN user";
		return readThisQuery(query);
	}
	
	public boolean existsUnidirectionalRelationship(Node fromNode, Neo4jRelationship relation, Node toNode){
		String rel;
		String from; 
		String to;
		try ( Transaction tx = db.beginTx() )
        {
			from = fromNode.getProperty("id").toString();
			to = toNode.getProperty("id").toString();
			rel = relation.name();
        }
		String testQuery = "MATCH (from {id:\"" + from + "\"})-[:" + rel + "]->(to {id:\"" + to + "\"}) RETURN to";
	 	return (engine.execute( testQuery ).iterator().hasNext());
	}
	
	public void deleteNodeWithRelations(String nodeId){
		try ( Transaction tx = db.beginTx() )
        {
			String query = "MATCH (n {id:'" + nodeId + "'})-[r]-() DELETE n,r";
			System.out.println("DELETE query is : " + query);
			engine.execute( query );
			tx.success();
			tx.close();
        }
	}
	
	public boolean updateAnnotation(String userId, String annotationId, String associatedImageId, List<Float> xCoordinates,
		List<Float> yCoordinates, List<Float> zCoordinates, List<String> associatedChannelId, 
		List<String> depictedAnatomyId, List<String> depictedAnatomyFreetext, List<String> depictedAnatomyTerm,
		List<String> abnInAnatomyId, List<String> abnInAnatomyFreetext,  List<String> abnInAnatomyTerm, 
		List<String> phenotypeId, List<String> phenotypeFreetext, List<String> phenotypeTerm, 
		List<String> observation, 
		List<String> expressedInAnatomyId, List<String> expressedInAnatomyTerm, List<String> expressedInAnatomyFreetext){
		Date today = new Date();
		try{
			if (existsId(annotationId, annLabel)){
				if (hasSameUser(userId, annotationId)){
					deleteNodeWithRelations(annotationId);
					createAnnotationWithDates(userId, annotationId, associatedImageId, xCoordinates, yCoordinates, zCoordinates, associatedChannelId,
						depictedAnatomyId, depictedAnatomyFreetext, depictedAnatomyTerm, abnInAnatomyId, abnInAnatomyFreetext, abnInAnatomyTerm, 
						phenotypeId, phenotypeFreetext, phenotypeTerm, observation, expressedInAnatomyId, expressedInAnatomyTerm, expressedInAnatomyFreetext, 
						today, today);
					return true;
				}
				else {
					return false;
				}
			} else {
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public String getCreationDate(String annId){		
		String date = null;
		try ( Transaction tx = db.beginTx() )
        {
			Node ann = getNodeById(annId);
            date = ann.getProperty(AnnotationProperties.CREATION_DATE.name()).toString();
            tx.close();
        }
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

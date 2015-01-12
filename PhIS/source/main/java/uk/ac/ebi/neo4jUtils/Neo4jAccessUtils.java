package uk.ac.ebi.neo4jUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.AnnotationIntrospector;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Service 
public class Neo4jAccessUtils {

	 private static final String DB_PATH = "/Users/tudose/Documents/servers/neo4j-community-2.1.6/data/phis_2.1.6.db";
	 private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 private static String resultString;
	 private static String columnsString;
	 private static String nodeResult;
	 private static String rows = "";
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
	 
	 
	public Neo4jAccessUtils () {
		
        db = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
        engine = new ExecutionEngine( db );
        annLabel = createLabel("Annotation");
        ontologyTermLabel = createLabel("OntologyTerm");
        channelLabel = createLabel("Channel");
        imageLabel = createLabel("Image");
        userLabel = createLabel("User");

        addUniqueIdConstraint(annLabel);
        
	}
	
	
	public String createAnnotation( String userId, String anntoationId, String associatedImageId, float[] xCoordinates,
    float[] yCoordinates, float[] zCoordinates, String associatedChannel, String depictedAnatomyId, String depictedAnatomyLabel,
    String abnInAnatomyId, String abnInAnatomyLabel, String phenotypeId, String phenotypeLabel, String observation,	Model model    ) throws Exception {
	
		Node user;
		Node channel;
		Node image;
		Node annotation;
		
		Date today = new Date();
		
		// Don't create another object with the same ID
		if (existsId(anntoationId, imageLabel)){
			throw getEmptyIdException();
		} 
		
		// there must be at least one annotation with the ROI coordinates
		if (depictedAnatomyId == null && depictedAnatomyLabel == null && abnInAnatomyId == null && abnInAnatomyLabel == null &&
		phenotypeId == null && phenotypeLabel == null & observation == null){
			throw getNoAnnotationException();
		}
		
		
		if (existsId(userId, userLabel)){
			user = getNodeById(userId);
		} else {
			user = addUser(userId);
		}
		
		if (existsId(associatedImageId, imageLabel)){
			image = getNodeById(associatedImageId);
		}else {
			image = addImage(associatedImageId);
		}
		
		if (associatedChannel != null){
			if (existsId(associatedChannel, channelLabel)){
				channel = getNodeById(associatedChannel);
			} else {
				channel = addChannel(associatedChannel);
			}
		}
		
		annotation = addAnnotation(anntoationId, observation, today, xCoordinates, yCoordinates, zCoordinates);
		
		if (depictedAnatomyId != null){
			Node ot = getOrCreateNode(depictedAnatomyId, ontologyTermLabel);
			addBidirectionalRelation(annotation, Neo4jRelationship.DEPICTS, ot);
		}
		return "" ;
	}
	
	public void addBidirectionalRelation(Node fromNode, Neo4jRelationship relation, Node toNode) throws Exception{

		String testQuery = "MATCH (from {id:\"" + fromNode.getProperty("id") + "\"})-[:" + relation + "]-(to {id:\"" + toNode.getProperty("id") + "\"}) RETURN to";
		if (existsRelationship(testQuery)){
			throw new Exception(RELATIONSHIP_EXISTS_EXCEPTION);
		}
		try ( Transaction tx = db.beginTx() )
        {		
			Relationship relationship = fromNode.createRelationshipTo( toNode, relation );
			relationship.setProperty( "message", "brave Neo4j " );
			for (Relationship rel: fromNode.getRelationships()){
				System.out.println(" Found this rel : " + rel + " of type " + rel.getType() + " from " + rel.getStartNode() + " to " + rel.getEndNode());
			}
			tx.success();
			tx.close();
        }   
	}
	
	public void addUnidirectionalRelation(String fromNodeId, Neo4jRelationship relation, String toNodeId) throws Exception{

		Node fromNode = getNodeById(fromNodeId);
		Node toNode = getNodeById(toNodeId);
		String testQuery = "MATCH (from {id:\"" + fromNodeId + "\"})-[:" + relation + "]->(to {id:\"" + toNodeId + "\"}) RETURN to";
		if (existsRelationship(testQuery)){
			throw new Exception(RELATIONSHIP_EXISTS_EXCEPTION);
		}
		try ( Transaction tx = db.beginTx() )
        {
			Relationship relationship = fromNode.createRelationshipTo( toNode, relation );
			relationship.setProperty( "message", "brave Neo4j " );
			for (Relationship rel: fromNode.getRelationships()){
				System.out.println(" Found this rel : " + rel + " of type " + rel.getType() + " from " + rel.getStartNode() + " to " + rel.getEndNode());
			}
			tx.success();
			tx.close();
        }   
	}
	
	public Node addAnnotation(String id, String observation, Date creationDate, float[] xCoordinates, 
	float[] yCoordinates, float[] zCoordinates) throws Exception{
		
		Node myNode = null;
		if (id == null || id.equals("")){
			throw new Exception(EMPTY_ID_EXCEPTION_MESSAGE);
		} 
		else if (existsId(id, annLabel)){
			throw new Exception(EXISTING_ID_EXCEPTION_MESSAGE + id);
		}
		else if (xCoordinates == null || yCoordinates == null){

			throw new Exception(NO_COORDINATES_EXCEPTION);
		}
		try ( Transaction tx = db.beginTx() )
        {
            myNode = db.createNode(annLabel);
            myNode.setProperty( "id", id );
            if (observation != null){
            	myNode.setProperty( "observation", observation );
            }
            myNode.setProperty( "creationDate", DATE_FORMAT.format(creationDate) );
//            myNode.setProperty( "lastModifiedDate", DATE_FORMAT.format(lastModifiedDate) );
            myNode.setProperty( "xCoordinates", xCoordinates );
            myNode.setProperty( "yCoordinates", yCoordinates );
            myNode.setProperty( "zCoordinates", zCoordinates );
            tx.success();
            tx.close();
        }
		return myNode;
	}
	
	public Node addUser(String id) throws Exception{
		return createNode(id, userLabel);	
	}
	
	public Node addOntologyTerm(String id, String termLabel) throws Exception{
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
	
	public Node addImage(String id) throws Exception{
		return createNode(id, imageLabel);
	}
	
	public Node addChannel(String id) throws Exception{
		return createNode(id, channelLabel);
	}
	
	private Node createNode(String id, Label label) throws Exception{
		Node myNode;
		if (id == null || id.equals("")){
			throw getEmptyIdException();
		} 
		else if (existsId(id, channelLabel)){
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
	
	
	private Exception getEmptyIdException(){
		return new Exception(EMPTY_ID_EXCEPTION_MESSAGE);
	}
	
	private Exception getIdAlreadyExists(String id){
		return new Exception(EXISTING_ID_EXCEPTION_MESSAGE + id);
	}
	
	private  Exception getNoAnnotationException(){
		return new Exception (NO_ANNOTATION_EXCEPTION);
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
	
	
	public Node getOrCreateNode(String id, Label label) throws Exception{
		Node res ;
		if ( existsId(id, label) ){
			res = getNodeById(id);
		} else {
			res = addOntologyTerm(id, null);
		}
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
			l = DynamicLabel.label("Annotation");
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
		ExecutionResult result;
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
	
	public boolean existsRelationship(String query){
		System.out.println("Query:: " + query);
		return (engine.execute( query ).iterator().hasNext());
	}
	
	
	public void closeDb(){
		db.shutdown();
	}
}

package uk.ac.ebi.neo4jUtils;

import org.neo4j.graphdb.RelationshipType;


public enum Neo4jRelationship implements RelationshipType{ 
	CREATED_BY, DEPICTS_PHENOTYPE, HAS_ASSOCIATED_IMAGE, DEPICTS_ABNORMALITY_IN, DEPICTS, HAS_ASSOCIATED_CHANNEL, EXPRESSED_IN
	};	
	

/**
 * @author tudose
 */
package uk.ac.ebi.phis.neo4j.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;


/**
 * @author tudose
 *
 */
public class User {
	@NodeEntity
	public class Movie {

	  @GraphId Long id;

	}
}

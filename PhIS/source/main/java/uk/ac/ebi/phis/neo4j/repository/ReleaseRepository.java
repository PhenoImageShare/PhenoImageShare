/**
 * @author tudose
 */
package uk.ac.ebi.phis.neo4j.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import uk.ac.ebi.phis.neo4j.model.User;


/**
 * @author tudose
 *
 */
	interface ReleaseRepository extends GraphRepository<User> {

		  @Query("MATCH (n:User) RETURN n LIMIT 25")
		  Iterable<User> getUsers();

		}


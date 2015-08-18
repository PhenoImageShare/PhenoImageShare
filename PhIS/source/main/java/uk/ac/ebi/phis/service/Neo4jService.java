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
/**
 * @author tudose
 */
package uk.ac.ebi.phis.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.ebi.neo4jUtils.Neo4jAccessUtils;


/**
 * @author tudose
 *@since 2015/08/18
 */
@Service
public class Neo4jService {

	@Autowired
	Neo4jAccessUtils neo;
	
	public JSONObject getAllReleases(){
		
		JSONObject res = new JSONObject();
		
		//get all release nodes
		List<Node> releases = neo.getNodesByLabel(neo.releaseLabel);
		
		return res;
		
	}
	
}

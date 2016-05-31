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

import org.json.JSONArray;
import org.json.JSONObject;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.ac.ebi.neo4jUtils.Neo4jAccessUtils;
import uk.ac.ebi.neo4jUtils.Neo4jRelationship;
import uk.ac.ebi.neo4jUtils.ReleaseProperties;


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
		JSONArray releasesArray = new JSONArray();
		
		//get all release nodes
		List<Node> releases = neo.getNodesByLabel(Neo4jAccessUtils.releaseLabel);
		System.out.println("Releases size " + releases.size());
		
		for ( Node release : releases ){
			
			JSONObject releaseObj = new JSONObject();
			releaseObj.accumulate(ReleaseProperties.VERSION.name(), release.getProperty(ReleaseProperties.VERSION.name()));
			releaseObj.accumulate(ReleaseProperties.IMAGES_NUMBER.name(), release.getProperty(ReleaseProperties.IMAGES_NUMBER.name()));
			releaseObj.accumulate(ReleaseProperties.GENES_NUMBER.name(), release.getProperty(ReleaseProperties.GENES_NUMBER.name()));
			releaseObj.accumulate(ReleaseProperties.ROIS_NUMBER.name(), release.getProperty(ReleaseProperties.ROIS_NUMBER.name()));
			if (release.hasProperty(ReleaseProperties.RELEASE_DATE.name())){
				releaseObj.accumulate(ReleaseProperties.RELEASE_DATE.name(), release.getProperty(ReleaseProperties.RELEASE_DATE.name()));
			}
			
			List<Node> ontologies = neo.getFirtsNodesFor(release.getId(), Neo4jRelationship.CONTAINS, "Ontology");
			JSONArray ontologiesArray = new JSONArray();
			
			for (Node ont : ontologies){				
				JSONObject ontologyObj = new JSONObject();
				ontologyObj.accumulate(ReleaseProperties.VERSION.name(), ont.getProperty(ReleaseProperties.VERSION.name()));
				ontologyObj.accumulate(ReleaseProperties.NAME.name(), ont.getProperty(ReleaseProperties.NAME.name()));
				ontologiesArray.put(ontologyObj);
			}
			
			releaseObj.accumulate("ontologies", ontologiesArray);
			
			JSONArray datasourcesArray = new JSONArray();
			for (Node datasource : neo.getFirtsNodesFor(release.getId(), Neo4jRelationship.CONTAINS, "DataSource")){
				JSONObject dsObj = new JSONObject();
				dsObj.accumulate(ReleaseProperties.IMAGES_NUMBER.name(), datasource.getProperty(ReleaseProperties.IMAGES_NUMBER.name()));
				dsObj.accumulate(ReleaseProperties.NAME.name(), datasource.getProperty(ReleaseProperties.NAME.name()));				
				dsObj.accumulate(ReleaseProperties.EXPORT_DATE.name(), datasource.getProperty(ReleaseProperties.EXPORT_DATE.name()));
				datasourcesArray.put(dsObj);
			}
			
			releaseObj.accumulate("datasources", datasourcesArray);
			
			
			JSONArray speciesArray = new JSONArray();
			for (Node species : neo.getFirtsNodesFor(release.getId(), Neo4jRelationship.CONTAINS, "Species")){
				JSONObject sObj = new JSONObject();
				sObj.accumulate(ReleaseProperties.IMAGES_NUMBER.name(), species.getProperty(ReleaseProperties.IMAGES_NUMBER.name()));
				sObj.accumulate(ReleaseProperties.NAME.name(), species.getProperty(ReleaseProperties.NAME.name()));
				speciesArray.put(sObj);
			}
			
			releaseObj.accumulate("species", speciesArray);					
			
			releasesArray.put(releaseObj);
	
		}
		
		res.accumulate("releases", releasesArray);
		
		return res;
		
	}
	
}

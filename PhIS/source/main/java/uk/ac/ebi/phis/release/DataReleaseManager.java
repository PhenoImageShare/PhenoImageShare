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
package uk.ac.ebi.phis.release;

import java.io.File;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import uk.ac.ebi.neo4jUtils.Neo4jAccessUtils;
import uk.ac.ebi.phis.exception.PhisSubmissionException;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.service.RoiService;


/**
 * @author tudose
 *
 */
public class DataReleaseManager {


	static final String version =  "beta-v0.0.81"; // id
	static final String contextFile = "/Users/tudose/git/PhenoImageShare_Jul_22/PhIS/WebContent/WEB-INF/app-config.xml";
	
	private static ApplicationContext applicationContext;
	
	public static void main(String[] args){

		
		File f = new File(contextFile);
		if (!f.isFile() || !f.canRead()) {
			System.err.println("Context file " + contextFile + " not readable.");
		}
		applicationContext = new  FileSystemXmlApplicationContext("file:" + contextFile);
		ImageService is = (ImageService) applicationContext.getBean("imageService");
		RoiService rs = (RoiService) applicationContext.getBean("roiService");
		Neo4jAccessUtils neo = (Neo4jAccessUtils) applicationContext.getBean("neo4jAccessUtils");
		
		try {
			
			ReleaseDocument release = createDocument(version, is, rs);
			neo.writeRelease(release);
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (PhisSubmissionException e) {
			e.printStackTrace();
		}

	}
	
	
	public static ReleaseDocument createDocument(String version, ImageService is , RoiService rs ) 
	throws SolrServerException{
	
		ReleaseDocument release = new ReleaseDocument();
		
		release.setReleaseEnvironment(ReleaseEnvironment.BETA);
		release.setReleaseVersion(version);
		release.setNumberOfImages(is.getNumberOfDocuments());
		release.setNumberOfRois(rs.getNumberOfDocuments());
		release.setSpeciesWithData(is.getSpecies());
		release.setDatasourcesUsed(is.getDatasources());
		
		return release;
	}
	
}

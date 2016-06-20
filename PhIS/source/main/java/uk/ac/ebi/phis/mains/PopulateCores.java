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
package uk.ac.ebi.phis.mains;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import uk.ac.ebi.neo4jUtils.Neo4jAccessUtils;
import uk.ac.ebi.phis.importer.BatchXmlUploader;
import uk.ac.ebi.phis.release.DatasourceInstance;
import uk.ac.ebi.phis.release.ReleaseDocument;
import uk.ac.ebi.phis.release.ReleaseEnvironment;
import uk.ac.ebi.phis.service.ChannelService;
import uk.ac.ebi.phis.service.ImageService;
import uk.ac.ebi.phis.service.RoiService;


public class PopulateCores {

	private static ApplicationContext applicationContext;
	Map<String, DatasourceInstance> datasources = new HashMap<String, DatasourceInstance>();

	private static Logger logger = Logger.getLogger(PopulateCores.class);

	public static void main(String[] args) {
		
		OptionParser parser = new OptionParser();
		parser.accepts( "context" ).withRequiredArg();
		parser.accepts( "dataDir" ).withRequiredArg();
		parser.accepts( "releaseVersion" ).withRequiredArg();

		OptionSet options = parser.parse( args );

		if (!options.has("context")) {
			help();
		}
		if (!options.has("dataDir")) {
			help();
		}
		if (!options.has("releaseVersion")) {
			help();
		}
		
		
		// Check context file exists
		String contextFile = (String) options.valueOf("context");
		File f = new File(contextFile);
		if (!f.isFile() || !f.canRead()) {
			System.err.println("Context file " + contextFile + " not readable.");
			help();
		} else {
			logger.info("--context OK");
		}
		
		// Check data dir exists
		String dataDir = (String) options.valueOf("dataDir");
		File d = new File(dataDir);
		if (!d.isDirectory() || !d.canRead()) {
			System.err.println("dataDir file " + dataDir + " not readable.");
			help();
		}else {
			logger.info("--dataDir OK"); //local path to releases: /Users/ilinca/Documents/phisReleases/v1.0.1
		}
		
		String releaseVersion = (String) options.valueOf("releaseVersion");

		logger.info("Started PopulateCores with context=" + contextFile + " , dataDir=" + dataDir + ", releaseVersion" + releaseVersion);
		
		ReleaseDocument release = new ReleaseDocument();
		
		release.setReleaseEnvironment(ReleaseEnvironment.BETA);
		release.setReleaseVersion(releaseVersion);
				
		try {

			applicationContext = new  FileSystemXmlApplicationContext("file:" + contextFile);
			
			ImageService is = (ImageService) applicationContext.getBean("imageService");
			RoiService rs = (RoiService) applicationContext.getBean("roiService");
			ChannelService cs = (ChannelService) applicationContext.getBean("channelService"); 
			String xmlToLoad;
			
			// delete everything in the cores. This will likely change as we might want to do updates only.
			is.clear();
			rs.clear();
			cs.clear();
			
			BatchXmlUploader reader = new BatchXmlUploader(is, rs, cs);
			release.setOntologiesUsed(reader.getontologyInstances());
			
			Map<String, DatasourceInstance> exportDates = new HashMap<>(); // <resourceName, resource object>
			
//			xmlToLoad = dataDir + "/tracerExport.xml";
//			DatasourceInstance ds1 = processXml(xmlToLoad, "tracer", reader);
//			exportDates.put(ds1.getName(), ds1);
//			
//			xmlToLoad = dataDir + "/VFB_Cachero2010.xml";
//			DatasourceInstance ds2 = processXml(xmlToLoad, "vfb", reader);
//			exportDates.put(ds2.getName(), ds2);
//
//			xmlToLoad = dataDir + "/VFB_Ito2013.xml";
//			DatasourceInstance ds3 = processXml(xmlToLoad, "vfb", reader);
//			exportDates.put(ds3.getName(), ds3);
//
//			xmlToLoad = dataDir + "/VFB_Jenett2012_full.xml";
//			DatasourceInstance ds4 = processXml(xmlToLoad, "vfb", reader);
//			exportDates.put(ds4.getName(), ds4);
//
//			xmlToLoad = dataDir + "/VFB_Yu2013.xml";
//			DatasourceInstance ds5 = processXml(xmlToLoad, "vfb", reader);
//			exportDates.put(ds5.getName(), ds5);
//
//			xmlToLoad = dataDir + "/VFB_flycircuit_plus.xml";
//			DatasourceInstance ds6 = processXml(xmlToLoad, "vfb", reader);
//			exportDates.put(ds6.getName(), ds6);

//			xmlToLoad = dataDir + "/emageExport.xml";
//			DatasourceInstance ds7 = processXml(xmlToLoad, "emage", reader);
//			exportDates.put(ds7.getName(), ds7);
			
			xmlToLoad = dataDir + "/sangerExport.xml";
			DatasourceInstance ds8 = processXml(xmlToLoad, "wtsi", reader);
			exportDates.put(ds8.getName(), ds8);
//			
//			xmlToLoad = dataDir + "/VFB_flycircuit_plus.xml";
//			DatasourceInstance ds9 = processXml(xmlToLoad, "vfb", reader);
//			exportDates.put(ds9.getName(), ds9);
			
			System.out.println("Solr url is : " + is.getSolrUrl());			

			System.out.println("Persisting release data...");
			release.setNumberOfImages(is.getNumberOfDocuments());
			release.setNumberOfRois(rs.getNumberOfDocuments());
			release.setSpeciesWithData(is.getSpecies());
			release.setDatasourcesUsed(is.getDatasources(exportDates));
			release.addGeneIds(is.getGeneIds());
			release.addGeneIds(cs.getGeneIds());
			
			Neo4jAccessUtils neo = (Neo4jAccessUtils) applicationContext.getBean("neo4jAccessUtils");
			neo.writeRelease(release);
			
			System.out.println("Release " + releaseVersion + "is ready.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
	}
	
	/**
	 * @author tudose
	 * @since 2015/08/17
	 * @param xmlToLoad
	 * @param resourceName
	 * @param reader
	 * @throws SolrServerException 
	 * @throws IOException 
	 * @throws ParseException 
	 */
	private static DatasourceInstance processXml (String xmlToLoad, String resourceName, BatchXmlUploader reader) 
	throws IOException, SolrServerException, ParseException{

		Long time = System.currentTimeMillis();
		DatasourceInstance ds = null;
		
		if (reader.validate(xmlToLoad)){
			System.out.println(xmlToLoad + " is valid.");
			ds = reader.index(xmlToLoad, resourceName);
			System.out.println("Importing " + xmlToLoad + " XML took " + (System.currentTimeMillis() - time));
		} else {
			System.out.println(xmlToLoad + " is NOT valid.");
		}
		
		return ds;
		
	}
	
	
	public static void help() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("PopulateCores usage:\n\n");
		buffer.append("PopulateCores --context <Spring context>\n");
		buffer.append("\t--context|-c\tSpring application context configuration file\n");
		buffer.append("PopulateCores --dataDir\n");
		buffer.append("\t--dataDir|-d\tFull path to the directory containing all the xml files to be used for the Solr index.\n");
		buffer.append("PopulateCores --releaseVersion <Spring context>\n");
		buffer.append("\t--releaseVersion|-c\tRelease version to persist in the database. If the release version already exists it wil be overwritten.\n");
		System.out.println(buffer);
		System.exit(1);
	}

}

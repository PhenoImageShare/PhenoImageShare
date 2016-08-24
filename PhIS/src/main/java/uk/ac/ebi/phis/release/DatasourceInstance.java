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

import javax.xml.datatype.XMLGregorianCalendar;


/**
 * @author tudose
 *
 */
public class DatasourceInstance {
	
	long numberOfImages;
	long numberOfRois;
	String name;
	XMLGregorianCalendar exportDate;
	
	
	public DatasourceInstance(){
		
	}
		
	public String getName() {
	
		return name;
	}
	
	public void setName(String name) {
	
		this.name = name;
	}
		
	public XMLGregorianCalendar getExportDate() {
	
		return exportDate;
	}
	
	public void setExportDate(XMLGregorianCalendar exportDate) {
	
		this.exportDate = exportDate;
	}
	
	public long getNumberOfImages() {
	
		return numberOfImages;
	}

	public void setNumberOfImages(long numberOfImages) {
	
		this.numberOfImages = numberOfImages;
	}
	
	public long getNumberOfRois() {
	
		return numberOfRois;
	}
	
	public void setNumberOfRois(long numberOfRois) {
	
		this.numberOfRois = numberOfRois;
	}
	
}

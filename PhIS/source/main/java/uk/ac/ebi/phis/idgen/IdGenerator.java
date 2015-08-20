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
package uk.ac.ebi.phis.idgen;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class IdGenerator {
	
	public String getAnnotationId(){
		long ct = System.currentTimeMillis();
		ct = ct - 1420070400; // Substract Jan 1st, 2015
		return "roi_ua_" + ct + "" + UUID.randomUUID().getMostSignificantBits()%100;
		//return UUID.randomUUID().toString();
	}
	
}

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
package uk.ac.ebi.phis.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;


/**
 * @author tudose
 *
 */
public class CustomStringToArrayConverter implements Converter<String, List<String>>{
	   @Override
	    public List<String> convert(String source) {
		   	List<String> parsed = new ArrayList();
		   	parsed.add(source);
	       	return parsed;
	    }
	}

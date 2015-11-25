<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- /*******************************************************************************
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
 *******************************************************************************/ -->

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>PhIS rest</title>
</head>

<body>

	<h1>PhIS RESTful Sevice Documentation</h1>
	<p>Base URL for dev website is
		http://dev.phenoimageshare.org/data/rest .</p>
		
	<br/>
	<h2>/getImages</h2>
	<h3>Parameters</h3>
	<table class="table table-striped">
		<thead>
			<tr  style="background-color: lightSteelBlue;">
				<th>Name</th>
				<th>Type</th>
				<th>Required</th>
				<th>Description</th>
				<th>Default</th>
				<th>Example Values</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>term</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Term to search in any searchable field. Can be id or text for any field accessible. These are anatomy, gene and phenotype fields as well as image source and image visualization method, sample preparation.</td>
				<td><var> * </var></td>
				<td><var>eye</var><br></td>
			</tr>
			<tr>
				<td>phenotype</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Images depicting the phenotype term passed.</td>
				<td><var> * </var></td>
				<td><var>MP:0010254</var><br></td>
			</tr>
			<tr>
				<td>anatomy</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Images depicting the given anatomical structure, regardless
					of abnormalities.</td>
				<td><var> * </var></td>
				<td><var>MA:0000261, eye</var></td>
			</tr>
			<tr>
				<td>gene</td>
				<td><var>String</var></td>
				<td>false</td>
				<td> Use mutantGene instead. Filters for samples with a mutation in the specified gene.</td>
				<td><var> * </var></td>
				<td><var>MGI:1891295, Spns2</var></td>
			</tr>			
			<tr>
				<td>mutantGene</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Filters for samples with a mutation in the specified gene.</td>
				<td><var> * </var></td>
				<td><var>MGI:1891295, Spns2</var></td>
			</tr>	
					
			<tr>
				<td>expressedFeature</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Filters for images depicting expression of the specified feature (gene or allele symbol or id).</td>
				<td><var> * </var></td>
				<td><var> Sesn3 </var></td>
			</tr>			
			
			<tr>
				<td>sex</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Returns only images of samples with the specified sex.</td>
				<td><var> * </var></td>
				<td><var> FEMALE , MALE </var> </td>
			</tr>
			
			<tr>
				<td>taxon</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>NCBI id or text / label of a taxon.</td>
				<td><var> * </var></td>
				<td><var> Mus musculus </var> </td>
			</tr>
			
			<tr>
				<td>sampleType</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>For mutant/wildType facet filtering.</td>
				<td><var> * </var></td>
				<td><var> WILD_TYPE </var> or <var>MUTANT</var> </td>
			</tr>
			
			
			<tr>
				<td>imageType</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Filter on expression or anatomy/phenotype images for faceting.</td>
				<td><var> * </var></td>
				<td><var> EXPRESSION </var> , <var>PHENOTYPE_ANATOMY </var> </td>
			</tr>
			
			<tr>
				<td>stage</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Stage ontology id or text.</td>
				<td><var> * </var></td>
				<td><var> postnatal stage , MmusDv_0000092 </var> </td>
			</tr>
			
			<tr>
				<td>samplePreparation</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Label or id of a child of Sample Preparation in FBBI ontology.</td>
				<td><var> * </var></td>
				<td><var> living tissue </var> </td>
			</tr>	
					
			<tr>
				<td>imagingMethod</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Label or id of a child of Imaging Method in FBBI ontology..</td>
				<td><var> * </var></td>
				<td><var> macroscopy </var> </td>
			</tr>	
				
			<tr>
				<td>visualisationMethod</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Label or id of a child of Visualisation Method in FBBI ontology..</td>
				<td><var> * </var></td>
				<td><var> fluorescent protein tag </var> </td>
			</tr>		
			<tr>
				<td>position</td>
				<td><var>Long</var></td>
				<td>false</td>
				<td>Position for genomic search. For better results with point mutations use interval search defined by startPosition and endPosition.</td>
				<td><var> * </var></td>
				<td><var>91008288</var><br></td>
			</tr>
			<tr>
				<td>startPosition</td>
				<td><var>Long</var></td>
				<td>false</td>
				<td>Start position for genomic location interval search.</td>
				<td><var> * </var></td>
				<td><var>91008288</var><br></td>
			</tr>
			<tr>
				<td>endPosition</td>
				<td><var>Long</var></td>
				<td>false</td>
				<td>End position for genomic location interval search.</td>
				<td><var> * </var></td>
				<td><var>91018288</var><br></td>
			</tr>
			<tr>
				<td>chromosome</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Chromosome for genomic location search.</td>
				<td><var> * </var></td>
				<td><var>X</var><br></td>
			</tr>
			<tr>
				<td>strand</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Strand for genomic location search.</td>
				<td><var> * </var></td>
				<td><var>-</var><br></td>
			</tr>			
				
			<tr>
				<td>resultNo</td>
				<td><var>Integer</var></td>
				<td>false</td>
				<td>Number of result objects to be returned back.</td>
				<td><var>100</var></td>
				<td><var></var><br></td>
			</tr>			
			<tr>
				<td>start</td>
				<td><var>Integer</var></td>
				<td>false</td>
				<td>Start position to return the results. Useful for pagination.</td>
				<td><var>0</var></td>
				<td><var></var><br></td>
			</tr>
		</tbody>
	</table>


	<br/>
	<h2>/getImage</h2>
	<h3>Parameters</h3>
	<table class="table table-striped">
		<thead>
			<tr style="background-color: lightSteelBlue;">
				<th>Name</th>
				<th>Type</th>
				<th>Required</th>
				<th>Description</th>
				<th>Default</th>
				<th>Example Values</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>imageId</td>
				<td><var>String</var></td>
				<td> true</td>
				<td>Image document with the specified id.</td>
				<td><var> (none) </var></td>
				<td><var>emage_EMAGE_2333.1</var><br></td>
			</tr>
		</tbody>
	</table>



	<!-- h2>Getting ROIs & annotations</h2-->

	<br/>
	<h2>/getRoi</h2>
	<h3>Parameters</h3>
	<table class="table table-striped">
		<thead>
			<tr style="background-color: lightSteelBlue;">
				<th>Name</th>
				<th>Type</th>
				<th>Required</th>
				<th>Description</th>
				<th>Default</th>
				<th>Example Values</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>roiId</td>
				<td><var>String</var></td>
				<td> true</td>
				<td>ROI with the specified id.</td>
				<td><var> (none) </var></td>
				<td><var>emage_roi_EMAGE_2333.1</var><br></td>
			</tr>
		</tbody>
	</table>

	
	<br/>
	<h2>/getRois</h2>
	<h3>Parameters</h3>
	<table class="table table-striped">
		<thead>
			<tr style="background-color: lightSteelBlue;">
				<th>Name</th>
				<th>Type</th>
				<th>Required</th>
				<th>Description</th>
				<th>Default</th>
				<th>Example Values</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>imageId</td>
				<td><var>String</var></td>
				<td> false</td>
				<td>ROI associated to the image.</td>
				<td><var> * </var></td>
				<td><var>emage_EMAGE_2333.1</var><br></td>
			</tr>
			<tr style="background-color: olive;">
				<td>userId</td>
				<td><var>String</var></td>
				<td> false </td>
				<td>Id of the user who created the annotation.</td>
				<td><var>(none)</var></td>
				<td><var>user_021897401</var></td>
			</tr>
			<tr style="background-color: olive;">
				<td>userGroup</td>
				<td><var>String</var></td>
				<td> false </td>
				<td>Group to which the user belongs.</td>
				<td><var>(none)</var></td>
				<td><var>groupA</var></td>
			</tr>
			<tr style="background-color: olive;">
				<td>lastEditBefore</td>
				<td><var>Date</var></td>
				<td> false </td>
				<td>Get annotations edited before the given date.</td>
				<td><var>(none)</var></td>
				<td><var>2016-03-06T23:59:59.999Z</var></td>
			</tr>
			<tr style="background-color: olive;">
				<td>lastEditAfter</td>
				<td><var>Date</var></td>
				<td> false </td>
				<td>Get annotations edited after the given date.</td>
				<td><var>(none)</var></td>
				<td><var>1976-03-06T23:59:59.999Z</var></td>
			</tr>
			<tr style="background-color: olive;">
				<td>createdBefore</td>
				<td><var>Date</var></td>
				<td> false </td>
				<td>Get annotations created before the given date.</td>
				<td><var>(none)</var></td>
				<td><var>2016-03-06T23:59:59.999Z</var></td>
			</tr>
			<tr style="background-color: olive;">
				<td>createdAfter</td>
				<td><var>Date</var></td>
				<td> false </td>
				<td>Get annotations created after the given date..</td>
				<td><var>(none)</var></td>
				<td><var>1976-03-06T23:59:59.999Z</var></td>
			</tr>
			
			<tr>
				<td>resultNo</td>
				<td><var>Integer</var></td>
				<td>false</td>
				<td>Number of result objects to be returned back.</td>
				<td><var>100</var></td>
				<td><var></var><br></td>
			</tr>
			<tr>
				<td>start</td>
				<td><var>Integer</var></td>
				<td>false</td>
				<td>Start position to return the results. Useful for pagination.</td>
				<td><var>0</var></td>
				<td><var></var><br></td>
			</tr>
		</tbody>
	</table>
	
	
	
	<!-- h2>Getting channel information</h2-->
	
	<br/>
	<h2>/getChannel</h2>
	<h3>Parameters</h3>
	<table class="table table-striped">
		<thead>
			<tr style="background-color: lightSteelBlue;">
				<th>Name</th>
				<th>Type</th>
				<th>Required</th>
				<th>Description</th>
				<th>Default</th>
				<th>Example Values</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>channelId</td>
				<td><var>String</var></td>
				<td> true</td>
				<td>Channel with the specified id.</td>
				<td><var> (none) </var></td>
				<td><var>emage_channel_EMAGE_2333.1</var><br></td>
			</tr>
		</tbody>
	</table>
	
	
	<br/>
	<h2>/getChannels</h2>
	<h3>Parameters</h3>
	<table class="table table-striped">
		<thead>
			<tr style="background-color: lightSteelBlue;">
				<th>Name</th>
				<th>Type</th>
				<th>Required</th>
				<th>Description</th>
				<th>Default</th>
				<th>Example Values</th>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<td>imageId</td>
				<td><var>String</var></td>
				<td> true</td>
				<td>Channel with the specified id.</td>
				<td><var> * </var></td>
				<td><var>emage_EMAGE_2333.1</var><br></td>
			</tr>
		
			<tr>
				<td>resultNo</td>
				<td><var>Integer</var></td>
				<td>false</td>
				<td>Number of result objects to be returned back.</td>
				<td><var>100</var></td>
				<td><var></var><br></td>
			</tr>
			
			<tr>
				<td>start</td>
				<td><var>Integer</var></td>
				<td>false</td>
				<td>Start position to return the results. Useful for pagination.</td>
				<td><var>0</var></td>
				<td><var></var><br></td>
			</tr>
		
		</tbody>

	</table>

	<br/>
	<h2>/getAutosuggest</h2>
	<p>Returns a JSONArray of suggestions for autosuggest. One of the parameters passing the typed text is required.</p> 
	<h3>Parameters</h3>
	<table class="table table-striped">
		<thead>
			<tr style="background-color: lightSteelBlue;">
				<th>Name</th>
				<th>Type</th>
				<th>Required</th>
				<th>Description</th>
				<th>Default</th>
				<th>Example Values</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>term</td>
				<td><var>String</var></td>
				<td> false </td>
				<td>JSON array of suggested terms. To be used with the "type" parameter for specific autosuggests.</td>
				<td><var> * </var></td>
				<td><var>abn</var><br></td>
			</tr>
			<!-- tr  style="background-color: OliveDrab ;" -->
			<tr>
				<td>autosuggestType</td>
				<td><var>String</var></td>
				<td> false </td>
				<td> Possible values are [GENE, ANATOMY, PHENOTYPE]. If one is specified autosuggest will be typed (i.e. return only gene names matching th typed string). If left out autosuggest will be in generic mode.</td>
				<td><var> * </var></td>
				<td><var>GENE</var><br></td>
			</tr>
			<tr>
				<td>stage</td>
				<td><var>String</var></td>
				<td> false </td>
				<td> Restricts results for autosuggest to documents for the stage passed.</td>
				<td><var> * </var></td>
				<td><var>postnatal stage</var><br></td>
			</tr>
			<tr>
				<td>imagingMethod</td>
				<td><var>String</var></td>
				<td> false </td>
				<td> Restricts results for autosuggest to documents for the imaging method passed.</td>
				<td><var> * </var></td>
				<td><var>X-ray illumination</var><br></td>
			</tr>
			<tr>
				<td>taxon</td>
				<td><var>String</var></td>
				<td> false </td>
				<td> Restricts results for autosuggest to documents for the taxon passed.</td>
				<td><var> * </var></td>
				<td><var>Mus musculus</var><br></td>
			</tr>
			<tr>
				<td>sampleType</td>
				<td><var>String</var></td>
				<td> false </td>
				<td> Restricts results for autosuggest to documents for the sampleType passed.</td>
				<td><var> * </var></td>
				<td><var>MUTANT</var><br></td>
			</tr>
			<tr>
				<td>imageGeneratedBy</td>
				<td><var>String</var></td>
				<td> false </td>
				<td> Restricts results for autosuggest to documents for images generated by the passed institute.</td>
				<td><var> * </var></td>
				<td><var>WTSI</var><br></td>
			</tr>
			<tr>
				<td>resultNo</td>
				<td><var>Integer</var></td>
				<td> false</td>
				<td>Max number of suggestions to be returned. It is not recommended to put a bigger number than you actually need as the time cost increses quite significanlty with it.</td>
				<td><var> 10 </var></td>
				<td><var>20</var><br></td>
			</tr>
		</tbody>
	</table>




	<br/>
	<h2 style="background-color: olive;">/getComplexAutosuggest</h2>
	<p>Returns a JSONArray of suggestions for autosuggest. Like /getAutosuggest but offers more info on the term such as ontology id, type of annotation in which it is used and synonyms when available.</p> 
	<h3>Parameters</h3>
	<table class="table table-striped">
		<thead>
			<tr style="background-color: olive;">
				<th>Name</th>
				<th>Type</th>
				<th>Required</th>
				<th>Description</th>
				<th>Default</th>
				<th>Example Values</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>term</td>
				<td><var>String</var></td>
				<td> false </td>
				<td>JSON array of suggested terms. To be used with the "type" parameter for specific autosuggests.</td>
				<td><var> * </var></td>
				<td><var>abn</var><br></td>
			</tr>
			<!-- tr  style="background-color: OliveDrab ;" -->
			<tr>
				<td>autosuggestType</td>
				<td><var>String</var></td>
				<td> false </td>
				<td> Possible values are [GENE, ANATOMY, PHENOTYPE]. If one is specified autosuggest will be typed (i.e. return only gene names matching th typed string). If left out autosuggest will be in generic mode.</td>
				<td><var> * </var></td>
				<td><var>GENE</var><br></td>
			</tr>
			<tr>
				<td>stage</td>
				<td><var>String</var></td>
				<td> false </td>
				<td> Restricts results for autosuggest to documents for the stage passed.</td>
				<td><var> * </var></td>
				<td><var>postnatal stage</var><br></td>
			</tr>
			<tr>
				<td>imagingMethod</td>
				<td><var>String</var></td>
				<td> false </td>
				<td> Restricts results for autosuggest to documents for the imaging method passed.</td>
				<td><var> * </var></td>
				<td><var>X-ray illumination</var><br></td>
			</tr>
			<tr>
				<td>taxon</td>
				<td><var>String</var></td>
				<td> false </td>
				<td> Restricts results for autosuggest to documents for the taxon passed.</td>
				<td><var> * </var></td>
				<td><var>Mus musculus</var><br></td>
			</tr>
			<tr>
				<td>sampleType</td>
				<td><var>String</var></td>
				<td> false </td>
				<td> Restricts results for autosuggest to documents for the sampleType passed.</td>
				<td><var> * </var></td>
				<td><var>MUTANT</var><br></td>
			</tr>
			<tr>
				<td>imageGeneratedBy</td>
				<td><var>String</var></td>
				<td> false </td>
				<td> Restricts results for autosuggest to documents for images generated by the passed institute.</td>
				<td><var> * </var></td>
				<td><var>WTSI</var><br></td>
			</tr>
			<tr>
				<td>resultNo</td>
				<td><var>Integer</var></td>
				<td> false</td>
				<td>Max number of suggestions to be returned. It is not recommended to put a bigger number than you actually need as the time cost increses quite significanlty with it.</td>
				<td><var> 10 </var></td>
				<td><var>20</var><br></td>
			</tr>
		</tbody>
	</table>

	<br/>
	<h2 >/getDataReleases</h2>
	<h3>Parameters</h3>
	<table class="table table-striped">
		<thead>
			<tr style="background-color: lightSteelBlue;">
				<th>Name</th>
				<th>Type</th>
				<th>Required</th>
				<th>Description</th>
				<th>Default</th>
				<th>Example Values</th>
			</tr>
			<tr>
				<td>(none)</td>
				<td>(none)</td>
				<td>(none)</td>
				<td>(none)</td>
				<td>(none)</td>
				<td>(none)</td>
			</tr>
		</thead>
		
		<tbody>
		
		</tbody>

	</table>



</body>
</html>
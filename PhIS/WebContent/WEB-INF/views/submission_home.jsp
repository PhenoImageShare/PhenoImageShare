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
	<title>PhIS Annotation submission rest</title>
</head>

<body>

	<h1>PhIS RESTful Sevice Documentation</h1>
	<p>Base URL is http://[dev|beta].phenoimageshare.org/data/rest/submission .</p>
		
	<p>Example urls to try out: <br/> [host]/rest/submission/createAnnotation?annotationId=ann_ua_5&userId=user_1&associatedImageId=komp2_289863&xCoordinates=1,20&yCoordinates=0,100&expressionInAnatomyFreetext=my%20description
	<br/>
	[host]/rest/submission/deleteAnnotation?annotationId=ann_ua_5&userId=user_1</p>
	

	<h1>/createAnnotation</h1>
	<table>
		<tbody>
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
				<td>userId</td>
				<td><var>String</var></td>
				<td> true </td>
				<td>Unique user id. If the id does not exist a node will be created.</td>
				<td><var>(none)</var></td>
				<td><var>user_021897401</var><br></td>
			</tr>
			<tr>
				<td>annotationId</td>
				<td><var>String</var></td>
				<td> true </td>
				<td>Unique annotation id. If the id exists an error will be thrown. Otherwise a node will be created. Should get it from the ID generator.</td>
				<td><var>(none)</var></td>
				<td><var>roi_ua_e3a48d04-b7fb-41c6-825e-7a7d30cf0a83</var><br></td>
			</tr>			
			<tr>
				<td>associatedImageId</td>
				<td><var>String</var></td>
				<td> true </td>
				<td>Must be a real image id because addAnnotation does not add new documents in the image core.</td>
				<td><var>(none)</var></td>
				<td><var>komp2_289863</var></td>
			</tr>
			<tr>
				<td>xCoordinates</td>
				<td><var>float[]</var></td>
				<td> true </td>
				<td>A set of 1 coordinate pair (x,y or x,y,z) -> point. <br/>
						2 coordinate pairs -> rectangle. <br/>
						>2 coordinate pairs -> polygon. <br/>
<!-- 					Multiple shapes can be delimited by a dot (.) on each axis coordinate sets. A dot and a rectangular would look like:<br/>
						x = ["32",".","10", "22"], y = ["45",".","30", "42"]; -->
						</td>
				<td><var>(none)</var></td>
				<td><var>7.5,100</var><br></td>
			</tr>
			<tr>
				<td>yCoordinates</td>
				<td><var>float[]</var></td>
				<td> true </td>
				<td>See xCoordinates.</td>
				<td><var>(none)</var></td>
				<td><var>10,30</var><br></td>
			</tr>
			<tr>
				<td>zCoordinates</td>
				<td><var>float[]</var></td>
				<td> false </td>
				<td>See xCoordinates.</td>
				<td><var>(none)</var></td>
				<td><var>0,100</var><br></td>
			</tr>
			<tr>
				<td>associatedChannel</td>
				<td><var>String</var></td>
				<td> false </td>
				<td>Id of associated channel. Must be an existing id. </td>
				<td><var>(none)</var></td>
				<td><var></var><br></td>
			</tr>
			<tr>
				<td>depictedAnatomyId</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>(none)</var></td>
				<td><var>MA:0000261</var><br></td>
			</tr>
			<tr>
				<td>depictedAnatomyTerm</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>(none)</var></td>
				<td><var>heart</var><br></td>
			</tr>
			<tr>
				<td>depictedAnatomyFreetext</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>(none)</var></td>
				<td><var>some description of an anatomical part</var><br></td>
			</tr>
			<tr>
				<td>abnInAnatomyId</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>(none)</var></td>
				<td><var>MA:0000261</var><br></td>
			</tr>
			<tr>
				<td>abnInAnatomyTerm</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>(none)</var></td>
				<td><var>heart</var><br></td>
			</tr>
			<tr>
				<td>abnInAnatomyFreetext</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>(none)</var></td>
				<td><var>some description of an anatomical part</var><br></td>
			</tr>
			<tr>
				<td>phenotypeId</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>(none)</var></td>
				<td><var>MP:0010254</var><br></td>
			</tr>
			<tr>
				<td>phenotypeTerm</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>(none)</var></td>
				<td><var>abnormal heart</var><br></td>
			</tr>
			<tr>
				<td>phenotypeFreetext</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>(none)</var></td>
				<td><var>some observation about the phenotype</var><br></td>
			</tr>
			<tr>
				<td>expressionInAnatomyId</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>(none)</var></td>
				<td><var>MA:0000261</var><br></td>
			</tr>
			<tr>
				<td>expressionInAnatomyTerm</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>(none)</var></td>
				<td><var>abnormal heart</var><br></td>
			</tr>
			<tr>
				<td>expressionInAnatomyFreetext</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>(none)</var></td>
				<td><var>some observation about the expression</var><br></td>
			</tr>
		</tbody>
	</table>


	<h1>/updateAnnotation</h1>
	<p> This method should be used only to modify existing annotations. If a field is ommitted (empty) it's value will be deleted from the database  (if it had one). So every single correct parameter should be sent for every update.</p>
	<p> <b>All parameters from 	/createAnnotation</b> with the following meaning distinction: </p>
	
	
	<table>
		<tbody>
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
				<td>userId</td>
				<td><var>String</var></td>
				<td> true </td>
				<td>Unique user id. User must be the same one who created the annotation. Otherwise the update will not be made.</td>
				<td><var>(none)</var></td>
				<td><var>user_021897401</var><br></td>
			</tr>
			<tr>
				<td>annotationId</td>
				<td><var>String</var></td>
				<td> true </td>
				<td>Unique id of the annotation to be modified. If the id does not exist in the database the annotation will not be added. You should call getAnnotationId to get one each time.</td>
				<td><var>(none)</var></td>
				<td><var>roi_ua_e3a48d04-b7fb-41c6-825e-7a7d30cf0a83</var><br></td>
			</tr>
			
		</tbody>
	</table>


<h1>/deleteAnnotation</h1>
	<table>
		<tbody>
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
				<td>userId</td>
				<td><var>String</var></td>
				<td> true </td>
				<td>Unique user id. Must be the same user who created the image, otherwise permission is not granted.</td>
				<td><var>(none)</var></td>
				<td><var>user_021897401</var><br></td>
			</tr>
			<tr>
				<td>annotationId</td>
				<td><var>String</var></td>
				<td> true </td>
				<td>Unique id of the annotation to be deleted.</td>
				<td><var>(none)</var></td>
				<td><var></var><br></td>
			</tr>			
		</tbody>
	</table>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>PhIS Annotation submission rest</title>
</head>

<body>

	<h1>PhIS RESTful Sevice Documentation</h1>
	<p>Base URL for dev website is
		http://dev.phenoimageshare.org/data/rest/submission .</p>
		
	<br/>

	<h1>/createAnnotation</h1>
	<table>
		<tbody>
		<thead>
			<tr style="background-color: green;">
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
				<td>Unique user id. If the id does not exist a node will be created</td>
				<td><var>-</var></td>
				<td><var>user_021897401</var><br></td>
			</tr>
			<tr>
				<td>anntoationId</td>
				<td><var>String</var></td>
				<td> true </td>
				<td>Unique annotation id. If the id exists an error will be thrown. Otherwise a node will be created.</td>
				<td><var></var></td>
				<td><var></var><br></td>
			</tr>
			<tr>
				<td>xCoordinates</td>
				<td><var>float[]</var></td>
				<td> true </td>
				<td>A set of 1 coordinate pair (x,y or x,y,z) -> point. <br/>
						2 coordinate pairs -> rectangular. <br/>
						>2 coordinate pairs -> polygon. <br/>
<!-- 					Multiple shapes can be delimited by a dot (.) on each axis coordinate sets. A dot and a rectangular would look like:<br/>
						x = ["32",".","10", "22"], y = ["45",".","30", "42"]; -->
						</td>
				<td><var>-</var></td>
				<td><var>??</var><br></td>
			</tr>
			<tr>
				<td>yCoordinates</td>
				<td><var>float[]</var></td>
				<td> true </td>
				<td>See xCoordinates.</td>
				<td><var>-</var></td>
				<td><var>??</var><br></td>
			</tr>
			<tr>
				<td>zCoordinates</td>
				<td><var>string[]</var></td>
				<td> false </td>
				<td>See xCoordinates.</td>
				<td><var>-</var></td>
				<td><var>??</var><br></td>
			</tr>
			<tr>
				<td>associatedChannel</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var></var></td>
				<td><var></var><br></td>
			</tr>
			<tr>
				<td>depictedAnatomyId</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>-</var></td>
				<td><var>MA:0000261</var><br></td>
			</tr>
			<tr>
				<td>abnInAnatomyId</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>-</var></td>
				<td><var>MA:0000261</var><br></td>
			</tr>
			<tr>
				<td>phenotypeId</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>-</var></td>
				<td><var>MP_0001340</var><br></td>
			</tr>
			
		</tbody>
	</table>


	<h1>/updateAnnotation</h1>
	<table>
		<tbody>
		<thead>
			<tr style="background-color: green;">
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
				<td><var>-</var></td>
				<td><var>user_021897401</var><br></td>
			</tr>
			<tr>
				<td>anntoationId</td>
				<td><var>String</var></td>
				<td> true </td>
				<td>Unique id of the annotation to be modified.</td>
				<td><var></var></td>
				<td><var></var><br></td>
			</tr>
			<tr>
				<td>xCoordinates</td>
				<td><var>float[]</var></td>
				<td> true </td>
				<td>A set of 1 coordinate pair (x,y or x,y,z) -> point. <br/>
						2 coordinate pairs -> rectangular. <br/>
						>2 coordinate pairs -> polygon. <br/>
<!-- 					Multiple shapes can be delimited by a dot (.) on each axis coordinate sets. A dot and a rectangular would look like:<br/>
						x = ["32",".","10", "22"], y = ["45",".","30", "42"]; -->
						</td>
				<td><var>-</var></td>
				<td><var>??</var><br></td>
			</tr>
			<tr>
				<td>yCoordinates</td>
				<td><var>float[]</var></td>
				<td> true </td>
				<td>See xCoordinates.</td>
				<td><var>-</var></td>
				<td><var>??</var><br></td>
			</tr>
			<tr>
				<td>zCoordinates</td>
				<td><var>string[]</var></td>
				<td> false </td>
				<td>See xCoordinates.</td>
				<td><var>-</var></td>
				<td><var>??</var><br></td>
			</tr>
			<tr>
				<td>associatedChannel</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>-</var></td>
				<td><var></var><br></td>
			</tr>
			<tr>
				<td>depictedAnatomyId</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>-</var></td>
				<td><var>MA:0000261</var><br></td>
			</tr>
			<tr>
				<td>abnInAnatomyId</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>-</var></td>
				<td><var>MA:0000261</var><br></td>
			</tr>
			<tr>
				<td>phenotypeId</td>
				<td><var>String</var></td>
				<td> false </td>
				<td></td>
				<td><var>-</var></td>
				<td><var>MP_0001340</var><br></td>
			</tr>
			
		</tbody>
	</table>


<h1>/deleteAnnotation</h1>
	<table>
		<tbody>
		<thead>
			<tr style="background-color: green;">
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
				<td><var>-</var></td>
				<td><var>user_021897401</var><br></td>
			</tr>
			<tr>
				<td>anntoationId</td>
				<td><var>String</var></td>
				<td> true </td>
				<td>Unique id of the annotation to be deleted.</td>
				<td><var></var></td>
				<td><var></var><br></td>
			</tr>			
		</tbody>
	</table>

</body>
</html>
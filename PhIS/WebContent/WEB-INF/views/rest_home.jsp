<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title> PhIS rest </title>
</head>
<body>

<h1>PhIS RESTful Sevice Documentation</h1>
<p>
Base URL for dev website is http://dev.phenoimageshare.org/data/rest .
</p>
<h2>/getImages</h2>


<h3>Parameters</h3>


	<table class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				<th>Type</th>
				<th>Description</th>
				<th>Default</th>
				<th>Example Values</th>
			</tr>
		</thead>
		<tbody>


			<tr>
				<td>phenotypeId</td>
				<td><var>String</var></td>
				<td>Images depicting the phenotype term passed.</td>
				<td><var> * </var></td>
				<td><var>MP:0010254</var><br></td>
			</tr>

			<tr>
				<td>anatomyId</td>
				<td><var>String</var></td>
				<td>Images depicting the given anatomical structure, regardless of abnormalities.</td>
				<td><var> * </var></td>
				<td><var>MA:0000261</var></td>
			</tr>

			<tr>
				<td>geneId</td>
				<td><var>String</var></td>
				<td>Filters for samples with a mutation in the specified gene.</td>
				<td><var> * </var></td>
				<td><var>MGI:1891295</var></td>
			</tr>

			<tr>
				<td>resultNo</td>
				<td><var>Integer</var></td>
				<td>Number of result objects to be returned back.</td>
				<td><var>10</var></td>
				<td><var></var><br></td>
			</tr>

		</tbody>
	</table>


	<!-- h2>Getting ROIs & annotations</h2-->


	<!-- h2>Getting channel information</h2-->
	
	
</body>
</html>
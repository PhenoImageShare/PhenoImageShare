<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<tr>
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
			<tr style="background-color: grey;">
				<td>gene</td>
				<td><var>String</var></td>
				<td>false</td>
				<td> [TO BE DEPRECATED] Use mutantGene instead. Filters for samples with a mutation in the specified gene.</td>
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
				<td><var> FEMALE / MALE </var> </td>
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
				<td><var> EXPRESSION </var> or <var>PHENOTYPE_ANATOMY </var> </td>
			</tr>
			<tr>
				<td>stage</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Stage ontology id or text.</td>
				<td><var> * </var></td>
				<td><var> postnatal stage / MmusDv_0000092 </var> </td>
			</tr>

			<!-- tr>
				<td>visualisationMethod</td>
				<td><var>String</var></td>
				<td>false</td>
				<td>Label or id of a child of Visualisation Method in FBBI ontology.</td>
				<td><var> * </var></td>
				<td><var> FEMALE / MALE </var> </td>
			</tr-->
			
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


	<!-- h2>Getting ROIs & annotations</h2-->

	<br/>
	<h2>/getRoi</h2>
	<h3>Parameters</h3>
	<table class="table table-striped">
		<thead>
			<tr>
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
				<td><var> - </var></td>
				<td><var>komp2_roi_112003_0</var><br></td>
			</tr>
		</tbody>
	</table>

	
	<br/>
	<h2>/getRois</h2>
	<h3>Parameters</h3>
	<table class="table table-striped">
		<thead>
			<tr>
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
				<td><var>komp2_112003</var><br></td>
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
			<tr>
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
				<td><var> - </var></td>
				<td><var>komp2_channel_112003_0</var><br></td>
			</tr>
		</tbody>
	</table>
	
	
	<br/>
	<h2>/getChannels</h2>
	<h3>Parameters</h3>
	<table class="table table-striped">
		<thead>
			<tr>
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
				<td><var> - </var></td>
				<td><var>komp2_112003</var><br></td>
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
	<p>Returns a JSONArray of suggestions fot autosuggest. One of the parameters passing the typed text is required.</p> 
	<h3>Parameters</h3>
	<table class="table table-striped">
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
				<td>term</td>
				<td><var>String</var></td>
				<td> false </td>
				<td>Return list of suggestions containing the passedstring, with the containing words highlighted.</td>
				<td><var> - </var></td>
				<td><var>abn</var><br></td>
			</tr>
			<tr style="background-color: grey;">
				<td>mutantGene</td>
				<td><var>String</var></td>
				<td> false</td>
				<td>[TO BE DEPRECATED] Suggestions for mutant gene symbols from annotations, with highlights.</td>
				<td><var> - </var></td>
				<td><var>a</var><br></td>
			</tr>
			<tr style="background-color: grey;">
				<td>expressedGeneOrAllele</td>
				<td><var>String</var></td>
				<td> false </td>
				<td>[TO BE DEPRECATED] Suggestions for expressed gene or allele symbols, with highlights.</td>
				<td><var> - </var></td>
				<td><var>m</var><br></td>
			</tr>
			<tr style="background-color: grey;">
				<td>phenotype</td>
				<td><var>String</var></td>
				<td> false</td>
				<td>[TO BE DEPRECATED] Suggestions for phenotype labels from annotations, with highlights.</td>
				<td><var> - </var></td>
				<td><var>abn</var><br></td>
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

</body>
</html>
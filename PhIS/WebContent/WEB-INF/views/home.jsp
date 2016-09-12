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
        <%--<script src="js/openseadragon/openseadragon.min.js"></script>--%>
	</head>
	
	<body>
		<h1>PhenoImageShare API</h1>
		<h2>
			For the Query API go here : <a href="/data/rest/">rest/</a>
		</h2>
		<h2>For the id Generator API go here : <a href="data/rest/idGen/">/rest/idGen/</a></h2>


        <div id ="container" style="width:550px; height: 500px; border: 1px solid #466d98; margin-bottom: 10px; margin-top: 10px;"></div>

        <%--<script type="text/javascript">--%>
        <%--Viewer = OpenSeadragon({--%>
            <%--id: "container",--%>
            <%--showNavigator:  true,--%>
            <%--tileSources: {--%>
                <%--type: 'image',--%>
                <%--url:  "http://idr-demo.openmicroscopy.org/webclient/render_thumbnail/size/10000/1920093/"--%>
            <%--}--%>

        <%--});--%>
        <%--</script>--%>

    </body>
	
</html>
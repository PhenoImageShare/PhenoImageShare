<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- /*******************************************************************************
* Copyright 2016 EMBL - European Bioinformatics Institute
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

    <link rel="stylesheet" href="../css/documentation.css">
    <link rel="stylesheet" href="http://www.phenoimageshare.org/phis/static/queries/css/bootstrap.css"/>

</head>

<body>
<div class="container">

    <div>
        <form method="POST" enctype="multipart/form-data" action="/phis/upload/">
            <table>
                <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
                <tr><td></td><td><input type="submit" value="Upload" /></td></tr>
            </table>
        </form>
    </div>


</div>
</body>
</html>
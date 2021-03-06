<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <jsp:include page="header_frag.jsp"/>
    <script type="text/javascript">   var jobId="${jobId}"; var baseUrl = "${baseUrl}"; </script>
    <script type="text/javascript" charset="utf8" src="${mappedHostname}${baseUrl}/js/status.js"></script>
    <div>
        <h1>Data submission</h1>
        <p>To submit data to PhenoImageShare you need to:</p>
        <ul>
            <li><b>1. Produce an XML file describing your data and containing links to your images. </b>
                You will be able to provide us information about the samples, your imaging process, existing anatomy or pehnotype annotations, as well as any publications, linking back to the original resource or licensing.  Please follow use <a href="https://raw.githubusercontent.com/PhenoImageShare/PhenoImageShare/master/PhIS/src/main/resources/phisSchema.xsd" download>PhenoImageShare XSD schema</a> to produce the XML file.</li>
            <li><b>2. Validate the XML file </b> using the form below. </li>
            <li>3. <a href="mailto:webmaster@phenoimageshare.org">Email us</a> to <b>submit the data</b> or for any other inquires. </li>
        </ul>
    </div>

    <c:if test="${jobId != null}">

        <p>A job was successfully created with id <a href="${baseUrl}/rest/upload/status?jobId=${jobId}">${jobId}</a></p>
        <div id="statusUpdatesUl">  </div>

    </c:if>

    <div id="formDiv">
        <form method="POST" enctype="multipart/form-data" action="${baseUrl}/rest/upload/">
           <label>Validate file:</label> <input type="file" name="file" accept=".xml"/> <button type="submit" value="Upload" class="btn btn-primary">Upload</button>
        </form>
    </div>

    <jsp:include page="footer_frag.jsp"/>


</html>
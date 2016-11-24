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
    <script type='text/javascript'>   var jobId="${jobId}" </script>
    <script type="text/javascript" charset="utf8" src="${mappedHostname}/${baseUrl}/js/status.js"></script>

    <c:if test="${jobId != null}">

        <p>A job was successfully created with id <a href="/phis/rest/upload/status?jobId=${jobId}">${jobId}</a></p>
        <div id="statusUpdatesUl">  </div>

    </c:if>

    <div id="formDiv">
        <form method="POST" enctype="multipart/form-data" action="/phis/rest/upload/">
           <label>File to upload:</label> <input type="file" name="file" accept=".xml"/> <button type="submit" value="Upload" class="btn btn-primary">Upload</button>
        </form>
    </div>

    <jsp:include page="footer_frag.jsp"/>


</html>
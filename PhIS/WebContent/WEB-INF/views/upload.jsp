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

    <div>
        <form method="POST" enctype="multipart/form-data" action="/phis/upload/">
            <table>
                <tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
                <tr><td></td><td><button type="submit" value="Upload" class="btn btn-primary">Upload</button></td></tr>
            </table>
        </form>
    </div>


    <div class="row" style="margin: 0px;padding-top: 0px;">
        <div class="col-lg-3">
        </div>

        <div class="col-lg-6">,
            <img src="/phis/static/queries/img/2015-01-07-phis_logos.jpg"/>
        </div>

        <div class="col-lg-3">
        </div>

    </div>


    <jsp:include page="footer_frag.jsp"/>

</html>
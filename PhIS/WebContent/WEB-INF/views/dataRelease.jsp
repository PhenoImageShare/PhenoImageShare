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

    <script type="text/javascript" charset="utf8" src="${mappedHostname}/${baseUrl}/js/jquery.dataTables.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>
    <link rel="stylesheet" href="${mappedHostname}${baseUrl}/css/jquery.dataTables.min.css">

    <div></div>
        <h1>Data release notes</h1>
        <div id="release"></div>
        <div id="container"></div>
        <div id="currentRelease"> </div>
        <table id="currentDatasources"> </table>
        <br/> <br/>
        <table id="currentOntologies"> </table>
    <div>
    <br/> <br/>

    <div id="previousReleases">   </div>


    <script type="text/javascript">

        var baseUrl = "${baseUrl}";

        $.getJSON( baseUrl + "/rest/getDataReleases", function( json ) {

            var releases = json.releases.sort(function(a, b) {
                return a.VERSION < b.VERSION; // sort descending order so that latest release (current) is the first one
            });

            var currentRelease = releases[5];

            $('#release').append("<p>In PhenoImageShare you can currently browse data linked to <span class='bigger'>" + currentRelease.GENES_NUMBER + "</span> genes in <span class='bigger'>" +
                currentRelease.species.length + " </span> species through <span class='bigger'>"
                + currentRelease.IMAGES_NUMBER + " </span> images and <span class='bigger'>" + " ??? </span> image annotations.  </p>");

            var categories = [];
            releases.map(function (a) {
                categories.push(a.VERSION);
            });

            var genes = [];
            releases.map(function(a){genes.push(a.GENES_NUMBER);});

            var images = [];
            releases.map(function(a){images.push(a.IMAGES_NUMBER);});

            console.log(genes);
            /** OVERVIEW CHART **/
            Highcharts.chart('container', {
                title: {
                    text: 'Datapoints by release',
                    x: -20 //center
                },
                credits:false,
                xAxis: {
                    categories: categories
                },
                yAxis: {
                    title:"count",
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: 'Genes',
                    data: genes
                }, {
                    name: 'Images',
                    data: images
                }]
            });


            /** CURRENT RELEASE **/

            $('#currentRelease').append("<h3> Current release: " + currentRelease.VERSION + "</h3>");
            var currentDatatable = [];
            currentRelease.datasources.map(function(a) {
                currentDatatable.push(JSON.parse("[\"" + a.NAME + "\"," + a.IMAGES_NUMBER + ",\""+ a.EXPORT_DATE +"\"]"));
            });

            // Fill current datasources table
            $('#currentDatasources').DataTable( {
                data: currentDatatable,
                "bLengthChange": false,
                "paging":   false,
                "info":     false,
                "bFilter":     false,
                columns: [
                    { title: "Name" },
                    { title: "Number of images" },
                    { title: "Export date" }
                ]
            });

            console.log(currentRelease.ontologies);
            var currentOntologies = [];
            currentRelease.ontologies.filter(function(item, pos) { return  currentRelease.ontologies.indexOf(item) == pos;}) // remove duplicates
                .map(function(a) { currentOntologies.push(JSON.parse("[\"" + a.NAME + "\",\""+ a.VERSION +"\"]"));}); // get list of values for datatables, without keys

            // Fill current ontologies table
            $('#currentOntologies').DataTable( {
                data: currentOntologies,
                "bLengthChange": false,
                "paging":   false,
                "info":     false,
                "bFilter":     false,
                columns: [
                    { title: "Ontology name" },
                    { title: "Version" }
                ]
            });


            /** ALL OLDER RELEASES **/
            releases.forEach(function(release){

                if (release == currentRelease){
                    console.log("SAME");
                } else {

                    // add new data div with id
                    var dataId = "data" + release.VERSION.replace(/\./g, "_");
                    $("#previousReleases").append( "<h3>Data release " + release.VERSION + "</h3>");
                    $("#previousReleases").append("<table id=\"" + dataId + "\"> </table> <br/> <br/>");

                    // add data table
                    var datatable = [];
                    release.datasources.map(function(a) {
                        datatable.push(JSON.parse("[\"" + a.NAME + "\"," + a.IMAGES_NUMBER + ",\""+ a.EXPORT_DATE +"\"]"));
                    });

                    var div = $('#'+dataId)
                    // Fill current datasources table
                    div.DataTable( {
                        data: datatable,
                        "bLengthChange": false,
                        "paging":   false,
                        "info":     false,
                        "bFilter":     false,
                        columns: [
                            { title: "Name" },
                            { title: "Number of images" },
                            { title: "Export date" }
                        ]
                    });

                    // add new ontologies div with id
                    var ontologiesId = "ontologies" + release.VERSION;
                    $("#previousReleases").append("<table id=\"" + ontologiesId + "\"> </table> <br/> <br/>");
                    var ontologies = [];
                    release.ontologies.filter(function(item, pos) { return  release.ontologies.indexOf(item) == pos;}) // remove duplicates
                            .map(function(a) { ontologies.push(JSON.parse("[\"" + a.NAME + "\",\""+ a.VERSION +"\"]"));}); // get list of values for datatables, without keys

                    // Fill current ontologies table
                    $('#' + ontologiesId).DataTable( {
                        data: ontologies,
                        "bLengthChange": false,
                        "paging":   false,
                        "info":     false,
                        "bFilter":     false,
                        columns: [
                            { title: "Ontology name" },
                            { title: "Version" }
                        ]
                    });

                }

            });

        });
    </script>

<jsp:include page="footer_frag.jsp"/>


</html>
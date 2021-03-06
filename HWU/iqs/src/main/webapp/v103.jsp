<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd">
<html>
    <body>
        <h2>IQS API summary</h2>


        <div>
            <%
                java.util.Properties prop = new java.util.Properties();
                prop.load(getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF"));
                String applVersion = prop.getProperty("Implementation-Version");
            %>                        
            <p>IQS code version: <%=applVersion%> </p>            
            <p>Base URL: <%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%></p>
            <p>Hitting EBI endpoint: <%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getEBI("103")%></p>
        </div>

        <hr> 

        <h3>Key</h3>
        <div style="background-color: grey;">
            Depreciated parameters or methods are given a grey background colour.
        </div>
        <div style="background-color: orange;">
            New parameters or methods are given an orange background colour.
        </div>
        <div>
            Unchanged parameters or methods are given a normal white background colour.
        </div>

        <hr>

        <h3>Instructions</h3>
        <div>
            <p>This is version <%=applVersion%>, to use it append &version=103 to your URL.</p>             
        </div>

        <hr>

        <h3>Compliance</h3>
        <div>            
            <p>This version is still under development and may contain several bugs!</p>            
            <p>For unit tests see <a tab="_blank" href="tests/index103.html">here</a>.</p>            
        </div>

        <hr>

        <div>
            <h3>/getImage</h3>
            <h4>parameters</h4>
            All parameters are optional

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
                        <td><var>8_komp2_307138</var><br></td>
                    </tr>
                    <tr>
                        <td>version</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Version of EBI to use.</td>
                        <td><var> 103 </var></td>
                        <td><var>103 | 102</var></td>
                    </tr>                    
                </tbody>
            </table>
            <h4>Examples:</h4>
            <ol>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImage?imageId=8_komp2_307138&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImage?imageId=8_komp2_307138&version=103</a></li>
            </ol>

        </div>

        <hr/>

        <div>
            <h3>/getImages</h3>
            <h4>parameters</h4>
            All parameters are optional

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
                        <td>Images depicting the given anatomical structure, regardless of abnormalities. You can request to prioritize certain queries with ^[desired_position], see example below. Default position is 0. These values are used to boost the queries so the order cannot be guaranteed..</td>
                        <td><var> * </var></td>
                        <td><var>MA:0000261, eye</var></td>
                    </tr>
                    <tr style="background-color: orange;">
                        <td>-anatomy</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Images NOT depicting the given anatomical structure, with or without of abnormalities.</td>
                        <td><var> * </var></td>
                        <td><var>MA:0000261, eye</var></td>
                    </tr>                    
                    <!--
                    <tr style="background-color: grey;">
                        <td>gene</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td> [TO BE DEPRECATED] Use mutantGene instead. Filters for samples with a mutation in the specified gene.</td>
                        <td><var> * </var></td>
                        <td><var>MGI:1891295, Spns2</var></td>
                    </tr>			
                    -->
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
                        <td>Label or id of a child of Imaging Method in FBBI ontology.</td>
                        <td><var> * </var></td>
                        <td><var> macroscopy </var> </td>
                    </tr>	
                    
                    <tr>
                        <td>position</td>
                        <td><var>Long</var></td>
                        <td>false</td>
                        <td>Position for genomic search. For better results with point mutations use interval search defined by startPosition and endPosition.</td>
                        <td><var> * </var></td>
                        <td><var> 91103288 </var> </td>
                    </tr>                    
                    <tr>
                        <td>startPosition</td>
                        <td><var>Long</var></td>
                        <td>false</td>
                        <td>Start position for genomic location interval search.</td>
                        <td><var> * </var></td>
                        <td><var> 91103288 </var> </td>
                    </tr>                    
                    <tr>
                        <td>endPosition</td>
                        <td><var>Long</var></td>
                        <td>false</td>
                        <td>End position for genomic location interval search..</td>
                        <td><var> * </var></td>
                        <td><var> 91103288 </var> </td>
                    </tr>
                    <tr>
                        <td>chromosome</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Chromosome for genomic location search.</td>
                        <td><var> * </var></td>
                        <td><var> X </var> </td>
                    </tr>                    
                    <tr>
                        <td>strand</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Strand for genomic location search.</td>
                        <td><var> * </var></td>
                        <td><var> No example provided by EBI </var> </td>
                    </tr>                    

                    
                    <tr>
                        <td>hostName</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Name of the website/resource hosting the images.</td>
                        <td><var> * </var></td>
                        <td><var> Mouse Atlas EMAGE  </var> </td>
                    </tr>    
                    
                    <tr>
                        <td>lastRoiEditAfter</td>
                        <td><var>Date</var></td>
                        <td>false</td>
                        <td>Get images with ROIs/annotations edited after the given date.</td>
                        <td><var> (none) </var></td>
                        <td><var> 1976-03-06T23:59:59.999Z  </var> </td>
                    </tr>   
                    <tr>
                        <td>lastRoiEditBefore</td>
                        <td><var>Date</var></td>
                        <td>false</td>
                        <td>Get images with ROIs/annotations edited before the given date.Please note that not all annotations are provided to us with a date, so this method might return incomplete annotation sets.</td>
                        <td><var> (none) </var></td>
                        <td><var> 2016-03-06T23:59:59.999Z  </var> </td>
                    </tr>                       

                    <!-- pagination -->
                    <tr>
                        <td>num</td>
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
                    <tr>
                        <td>version</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Version of EBI to use.</td>
                        <td><var> 103 </var></td>
                        <td><var>103 | 102</var></td>
                    </tr>			

                </tbody>
            </table>

            <h4>Examples:</h4>
            <ol>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImages?term=eye&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImages?term=eye&version=103</a></li>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImages?phenotype=MP:0010254&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImages?phenotype=MP:0010254&version=103</a></li>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImages?phenotype=MP:0010254&start=1&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImages?phenotype=MP:0010254&start=1&version=103</a></li>                
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImages?phenotype=cataracts&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImages?phenotype=cataracts&version=103</a></li>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImages?anatomy=eye^1&anatomy=heart&anatomy=liver^2&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImages?anatomy=eye^1&anatomy=heart&anatomy=liver^2&version=103</a></li>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImages?gene=Spns2&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getImages?gene=Spns2&version=103</a></li>
            </ol>
        </div>

        <hr>                

        <div>
            <h3>/getRoi</h3>
            <h4>parameters</h4>            
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
                        <td><var> * </var></td>
                        <td><var>8_roi_komp2_roi_307138_793338</var><br></td>
                    </tr>
                    <tr>
                        <td>version</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Version of EBI to use.</td>
                        <td><var> 103 </var></td>
                        <td><var>103 | 102</var></td>
                    </tr>			                    
                </tbody>
            </table>


            <h4>Example:</h4>
            <ol>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getRoi?id=8_roi_komp2_roi_307138_793338&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getRoi?id=8_roi_komp2_roi_307138_793338&version=103</a></li>
            </ol>
        </div>

        <hr>                

        <div>
            <h3>/getRois</h3>
            <p>Get all the ROIs in a particular image.</p>
            <p><b>NOTE</b>: the date queries only work for annotations created by PhIS users!</p>
            <h4>parameters</h4>            
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
                        <td> true </td>
                        <td>ROI associated to the image.</td>
                        <td><var> * </var></td>
                        <td><var>wtsi_komp2_112003</var><br></td>
                    </tr>
                    <!-- v103 -->
                    <tr>
                        <td>userId</td>
                        <td><var>String</var></td>
                        <td> false</td>
                        <td>Id of the user who created the annotation.</td>
                        <td><var> (none) </var></td>
                        <td><var>user_021897401</var><br></td>
                    </tr>                    
                    <tr>
                        <td>userGroup</td>
                        <td><var>String</var></td>
                        <td> false</td>
                        <td>Group to which the user belongs.</td>
                        <td><var> (none) </var></td>
                        <td><var>groupA</var><br></td>
                    </tr>


                    <!-- v103 -->
                    <tr>
                        <td>lastEditBefore</td>
                        <td><var>Date</var></td>
                        <td> false</td>
                        <td>Get annotations edited before the given date.</td>
                        <td><var> (none) </var></td>
                        <td><var>2016-03-06T23:59:59.999Z</var><br></td>
                    </tr>                    
                    <tr>
                        <td>lastEditAfter</td>
                        <td><var>Date</var></td>
                        <td> false</td>
                        <td>Get annotations edited after the given date.</td>
                        <td><var> (none) </var></td>
                        <td><var>2016-03-06T23:59:59.999Z</var><br></td>
                    </tr>                      
                    <tr>
                        <td>createdBefore</td>
                        <td><var>Date</var></td>
                        <td> false</td>
                        <td>Get annotations created before the given date.</td>
                        <td><var> (none) </var></td>
                        <td><var>2016-03-06T23:59:59.999Z</var><br></td>
                    </tr>                          
                    <tr>
                        <td>createdAfter</td>
                        <td><var>Date</var></td>
                        <td> false</td>
                        <td>Get annotations created a the given date.</td>
                        <td><var> (none) </var></td>
                        <td><var>2016-03-06T23:59:59.999Z</var><br></td>
                    </tr>


                    <tr>
                        <td>num</td>
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
                    <tr>
                        <td>version</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Version of EBI to use.</td>
                        <td><var> 103 </var></td>
                        <td><var>103 | 102</var></td>
                    </tr>			                    
                </tbody>
            </table>

            <h4>Example:</h4>
            <ol>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getRois?imageId=wtsi_komp2_112003&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getRois?imageId=wtsi_komp2_112003&version=103</a></li>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getRois?createdAfter=1976-03-06T23:59:59.999Z&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getRois?createdAfter=1976-03-06T23:59:59.999Z&version=103</a></li>
            </ol>
        </div>

        <hr>                

        <div>
            <h3>/getChannel</h3>
            <h4>parameters</h4>
            channelId parameter is mandatory
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
                        <td><var> * </var></td>
                        <td><var>wtsi_channel_komp2_channel_112003_0</var><br></td>
                    </tr>
                    <tr>
                        <td>version</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Version of EBI to use.</td>
                        <td><var> 103 </var></td>
                        <td><var>103 | 102</var></td>
                    </tr>			                    
                </tbody>
            </table>

            <h4>Example:</h4>
            <ol>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getChannel?id=wtsi_channel_komp2_channel_112003_0&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getChannel?id=wtsi_channel_komp2_channel_112003_0&version=103</a></li>
            </ol>
        </div>

        <hr>                

        <div>
            <h3>/getChannels</h3>
            Find all the channels in a particular image
            <h4>parameters</h4>
            imageId parameter is mandatory
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
                        <td><var> * </var></td>
                        <td><var>wtsi_komp2_112003</var><br></td>
                    </tr>

                    <tr>
                        <td>num</td>
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
                    <tr>
                        <td>version</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Version of EBI to use.</td>
                        <td><var> 103 </var></td>
                        <td><var>103 | 102</var></td>
                    </tr>			
                </tbody>

            </table>

            <h4>Example:</h4>
            <ol>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getChannels?imageId=wtsi_komp2_112003&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getChannels?imageId=wtsi_komp2_112003&version=103</a></li>
            </ol>

        </div>

        <hr>    

        <div>
            <h3>/getAutosuggest</h3>
            <p>Returns a JSONArray of suggestions for autosuggest. One of the parameters passing the typed text is required.</p>
            <h4>parameters</h4>

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
                        <td> false </td>
                        <td>Return list of suggestions containing the passed string, with the containing words highlighted.</td>
                        <td><var>*</var></td>
                        <td><var>abn</var><br></td>
                    </tr>                     
                    <tr>
                        <td>asType</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>If specified autosuggest will be typed (i.e. return only gene names matching the typed string). If left out autosuggest will be in generic mode.</td>
                        <td><var></var></td>
                        <td><var>GENE | ANATOMY | PHENOTYPE</var><br></td>
                    </tr>                    
                    <tr>
                        <td>stage</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Restricts results for autosuggest to documents for the stage passed.</td>
                        <td><var></var></td>
                        <td><var>postnatal stage</var><br></td>
                    </tr>  
                    <tr>
                        <td>imagingMethod</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Restricts results for autosuggest to documents for the imaging method passed.</td>
                        <td><var></var></td>
                        <td><var>X-ray illumination</var><br></td>
                    </tr>                      
                    <tr>
                        <td>taxon</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Restricts results for autosuggest to documents for the taxon passed.</td>
                        <td><var></var></td>
                        <td><var>Mus musculus</var><br></td>
                    </tr>   
                    <tr>
                        <td>sampleType</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Restricts results for autosuggest to documents for the sampleType passed.</td>
                        <td><var></var></td>
                        <td><var>MUTANT</var><br></td>
                    </tr>   
                    <tr>
                        <td>imageGeneratedBy</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Restricts results for autosuggest to documents for images generated by the institute passed.</td>
                        <td><var></var></td>
                        <td><var>WTSI</var><br></td>
                    </tr>                      
                    
                    <tr>
                        <td>hostName</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Restricts results for autosuggest to documents hosted on the website provided.</td>
                        <td><var>*</var></td>
                        <td><var>EMAGE</var><br></td>
                    </tr>                     

                    <!--
                    <tr style="background-color: grey;">
                        <td>mutantGene</td>
                        <td><var>String</var></td>
                        <td> false</td>
                        <td>Suggestions for mutant gene symbols from annotations, with highlights.</td>
                        <td><var>*</var></td>
                        <td><var>a</var><br></td>
                    </tr>
                    <tr style="background-color: grey;">
                        <td>expressedGeneOrAllele</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Suggestions for expressed gene or allele symbols, with highlights.</td>
                        <td><var>*</var></td>
                        <td><var>m</var><br></td>
                    </tr>
                    <tr style="background-color: grey;">
                        <td>phenotype</td>
                        <td><var>String</var></td>
                        <td> false</td>
                        <td>Suggestions for phenotype labels from annotations, with highlights.</td>
                        <td><var>*</var></td>
                        <td><var>abn</var><br></td>
                    </tr>
                    -->

                    <tr>
                        <td>num</td>
                        <td><var>Integer</var></td>
                        <td> false</td>
                        <td>Max number of suggestions to be returned.</td>
                        <td><var>100</var></td>
                        <td><var>20</var><br></td>
                    </tr>
                    <tr>
                        <td>version</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Version of EBI to use.</td>
                        <td><var> 103 </var></td>
                        <td><var>103 | 102</var></td>
                    </tr>			                    
                </tbody>
            </table>            

            <h4>Example:</h4>
            <ol>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getAutosuggest?term=ey&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getAutosuggest?term=ey&version=103</a></li>
            </ol>            
        </div>

        <hr>

        <div>
            <h3>/getComplexAutosuggest</h3>
            <p>Returns a JSONArray of suggestions for autosuggest. Like /getAutosuggest but offers more info on the term such as ontology id, type of annotation in which it is used and synonyms when available.</p>
            <h4>parameters</h4>

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
                        <td> false </td>
                        <td>Return list of suggestions containing the passed string, with the containing words highlighted.</td>
                        <td><var>*</var></td>
                        <td><var>abn</var><br></td>
                    </tr>                     
                    <tr>
                        <td>asType</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>If specified autosuggest will be typed (i.e. return only gene names matching the typed string). If left out autosuggest will be in generic mode.</td>
                        <td><var></var></td>
                        <td><var>GENE | ANATOMY | PHENOTYPE</var><br></td>
                    </tr>                    
                    <tr>
                        <td>stage</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Restricts results for autosuggest to documents for the stage passed.</td>
                        <td><var></var></td>
                        <td><var>postnatal stage</var><br></td>
                    </tr>  
                    <tr>
                        <td>imagingMethod</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Restricts results for autosuggest to documents for the imaging method passed.</td>
                        <td><var></var></td>
                        <td><var>X-ray illumination</var><br></td>
                    </tr>                      
                    <tr>
                        <td>taxon</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Restricts results for autosuggest to documents for the taxon passed.</td>
                        <td><var></var></td>
                        <td><var>Mus musculus</var><br></td>
                    </tr>   
                    <tr>
                        <td>sampleType</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Restricts results for autosuggest to documents for the sampleType passed.</td>
                        <td><var></var></td>
                        <td><var>MUTANT</var><br></td>
                    </tr>   
                    <tr>
                        <td>imageGeneratedBy</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Restricts results for autosuggest to documents for images generated by the institute passed.</td>
                        <td><var></var></td>
                        <td><var>WTSI</var><br></td>
                    </tr>                      


                    <!--
                    <tr style="background-color: grey;">
                        <td>mutantGene</td>
                        <td><var>String</var></td>
                        <td> false</td>
                        <td>Suggestions for mutant gene symbols from annotations, with highlights.</td>
                        <td><var>*</var></td>
                        <td><var>a</var><br></td>
                    </tr>
                    <tr style="background-color: grey;">
                        <td>expressedGeneOrAllele</td>
                        <td><var>String</var></td>
                        <td> false </td>
                        <td>Suggestions for expressed gene or allele symbols, with highlights.</td>
                        <td><var>*</var></td>
                        <td><var>m</var><br></td>
                    </tr>
                    <tr style="background-color: grey;">
                        <td>phenotype</td>
                        <td><var>String</var></td>
                        <td> false</td>
                        <td>Suggestions for phenotype labels from annotations, with highlights.</td>
                        <td><var>*</var></td>
                        <td><var>abn</var><br></td>
                    </tr>
                    -->

                    <tr>
                        <td>num</td>
                        <td><var>Integer</var></td>
                        <td> false</td>
                        <td>Max number of suggestions to be returned.</td>
                        <td><var>100</var></td>
                        <td><var>20</var><br></td>
                    </tr>
                    <tr>
                        <td>version</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Version of EBI to use.</td>
                        <td><var> 103 </var></td>
                        <td><var>103 | 102</var></td>
                    </tr>			                    
                </tbody>
            </table>            

            <h4>Example:</h4>
            <ol>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getComplexAutosuggest?term=ey&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getComplexAutosuggest?term=ey&version=103</a></li>
            </ol>            
        </div>        

        <hr>

        <div>
            <h3>/getDataReleases</h3>
            <h4>parameters</h4>
            No parameters!

            <h4>Example:</h4>
            <ol>
                <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getDataReleases?version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getDataReleases?version=103</a></li>
            </ol>
        </div>            



        <hr>

        <div>
            <h3 style="background-color: orange;">/similar</h3>
            <p>Returns a list of images similar to the given image.</p>
            <h4>parameters</h4>
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
                        <td> true </td>
                        <td>Image you wish to find similar images for.</td>
                        <td><var>*</var></td>
                        <td><var>wtsi_komp2_213108</var><br></td>
                    </tr>                     
                    <!-- pagination -->
                    <tr>
                        <td>num</td>
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
                    <tr>
                        <td>version</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Version of EBI to use.</td>
                        <td><var> 103 </var></td>
                        <td><var>103</var></td>
                    </tr>			

                </tbody>            

                <h4>Example:</h4>
                <ol>
                    <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>similar?imageId=wtsi_komp2_213108&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>similar?imageId=wtsi_komp2_213108&version=103</a></li>
                </ol>
        </div>             

        <hr />



        <hr>

        <div>
            <h3 style="background-color: orange;">/download</h3>
            <p>Returns a list of images similar to the given image.</p>
            <h4>parameters</h4>
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
                        <td> true </td>
                        <td>Image you wish to find similar images for.</td>
                        <td><var>*</var></td>
                        <td><var>wtsi_komp2_213108</var><br></td>
                    </tr>                     
                </tbody>            

                <h4>Example:</h4>
                <ol>
                    <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>download?imageId=wtsi_komp2_213108&version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>download?imageId=wtsi_komp2_213108&version=103</a></li>
                </ol>
        </div> 


                <hr />                    

                <h3>/getSpatialRelns</h3>

                <p>Returns a list of the spatial relationships that can be used to describe the relationships between ROI/annotations.</p>        

                <div>
                    <h4>parameters</h4>  
                    <p>No parameters for this operation.</p>

                    <div>
                        <h4>Examples</h4>
                        <ol>
                            <li><a href="<%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getSpatialRelns?version=103"><%= uk.ac.hw.macs.bisel.phis.iqs.GetHost.getURI()%>getSpatialRelns?version=103</a></p></li>
                        </ol>
                    </div>              

                    </body>
                    </html>

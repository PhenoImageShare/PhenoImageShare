<html>
    <body>
        <h2>PhIS Integrated Submission Service</h2>    

        <div>
            <p>Base URL: <%= org.bisel.phis.iss.client.GetISSURL.getURL()%></p>
            <p>Hitting EBI endpoint: <%= org.bisel.phis.iss.client.GetISSURL.getEBI("007")%></p>
        </div>


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
            <p>This is v008, to use it append &version=008 to your URL.</p>             
        </div>
        
        <hr>
        
        <h3>Compliance</h3>
        <div>            
            <p>This version is still under development and may contain several bugs!</p>
            <p><b>NOTE</b>: the EBI are still revising their API (& doc), so everything in this page is subject to change.</p>
            <p>For unit tests see <a tab="_blank" href="tests/index007.html">here</a>.</p>            
        </div>        
        

        <hr>

        
        <h3>/Annotation</h3>

        <p style="background-color: grey;">Version 005 has been removed!  However, you must still supply a version number.</p>

        <p style="background-color: orange;">When creating an annotation <b>all</b> params (ideally) should be specified.</p>
        <p>When deleting an annotation, you must provide version, phisid & creatorid; no other params are needed.</p>   
        <p>When editing an annotation, you must provide all params; those you wish to change AND those you wish unchanged.  EBI will delete any missing params!</p>

        <p>When supplying coords remember:</p>
        <ol>
            <li>1 coord = a point</li>
            <li>more than 1 coord = polyline or polygon</li>
        </ol>


        <p>The ISS is 3D so x, y & z coords must be supplied!</p>

        <h4>parameters</h4>

        <div>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Required</th>
                        <th>Description</th>                
                        <th>Example Values</th>
                    </tr>
                </thead>
                <tbody>                    
                    <tr>
                        <td>version</td>
                        <td><var>String</var></td>
                        <td>true</td>
                        <td>which EBI repository to use?  should be same as IQS version</td>                
                        <td><var>007</var><br></td>
                    </tr>            
                    <tr>
                        <td>action</td>
                        <td><var>String</var></td>
                        <td>true</td>
                        <td></td>                
                        <td><var>create | delete | edit</var><br></td>
                    </tr>
                    <tr>
                        <td>phisid</td>
                        <td><var>String</var></td>
                        <td style="background-color: orange;">true: edit & delete</td>
                        <td></td>                
                        <td><var> </var></td>
                    </tr>            
                    <tr>
                        <td>imageid</td>
                        <td><var>String</var></td>
                        <td>true: create & edit</td>
                        <td>Image ID must exist in EBI repo</td>                
                        <td><var>EMAGE_1218.1</var></td>
                    </tr>
                    <tr>

                    </tr>            
                    <tr>
                        <td>creatorid</td>
                        <td><var>String</var></td>
                        <td>true</td>
                        <td>eventually a phis id for users</td>                
                        <td><var>email address?</var></td>
                    </tr>
                    <tr>
                        <td>channelid</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>ID for channel</td>                
                        <td><var></var></td>
                    </tr>
                    <tr>
                        <td colspan="5"  style="background-color: green;">the above params should only be used once.  if more than one is supplied, they will be ignored</td>
                    </tr>
                    <tr>
                        <td colspan="5"></td>
                    </tr>                    
                    <tr>
                        <td colspan="5"  style="background-color: green;">you can supply the below params multiple times by simply repeating the parameter.  don't use csv</td>
                    </tr>
                    <tr>
                        <td  style="background-color: orange;">x_value</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>x coordinates of points</td>                
                        <td><var>1.8</var></td>
                    </tr>			
                    <tr>
                        <td  style="background-color: orange;">y_value</td>
                        <td><var>String</var></td>
                        <td>true: create & edit</td>
                        <td>y coordinates of points</td>                
                        <td><var>1.8</var></td>
                    </tr>
                    <tr>
                        <td  style="background-color: orange;">z_value</td>
                        <td><var>String</var></td>
                        <td>true: create & edit</td>
                        <td>z coordinates of points</td>                
                        <td><var>1.8</var></td>
                    </tr>            
                    <tr>
                        <td  style="background-color: orange;">phenotypeid</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Phenotype ontology ID</td>                
                        <td><var></var> </td>
                    </tr>
                    <tr>
                        <td  style="background-color: orange;">phenotype_term</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Phenotype ontology term</td>                
                        <td><var></var> </td>
                    </tr>
                    <tr>
                        <td  style="background-color: orange;">phenotype_text</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Phenotype free text</td>                
                        <td><var></var> </td>
                    </tr>
                    <tr>
                        <td  style="background-color: orange;">dpt_anatomyid</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Depicted anatomy ID</td>                
                        <td><var></var> </td>
                    </tr>
                    <tr>
                        <td  style="background-color: orange;">dpt_anatomy_term</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Depicted anatomy term</td>                
                        <td><var></var> </td>
                    </tr>
                    <tr>
                        <td  style="background-color: orange;">dpt_anatomy_text</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Depicted anatomy free text</td>                
                        <td><var></var> </td>
                    </tr>            
                    <tr>
                        <td  style="background-color: orange;">ge_anatomyid</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Gene expression anatomy ID</td>                
                        <td><var></var> </td>
                    </tr>
                    <tr>
                        <td  style="background-color: orange;">ge_anatomy_term</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Gene expression anatomy term</td>                
                        <td><var></var> </td>
                    </tr>
                    <tr>
                        <td  style="background-color: orange;">ge_anatomy_text</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Gene expression free text</td>                
                        <td><var></var> </td>
                    </tr>   
                    <tr>
                        <td  style="background-color: orange;">abn_anatomyid</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Abnormal anatomy ID</td>                
                        <td><var></var> </td>
                    </tr>
                    <tr>
                        <td  style="background-color: orange;">abn_anatomy_term</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Abnormal anatomy term</td>                
                        <td><var></var> </td>
                    </tr>
                    <tr>
                        <td  style="background-color: orange;">abn_anatomy_text</td>
                        <td><var>String</var></td>
                        <td>false</td>
                        <td>Abnormal free text</td>                
                        <td><var></var> </td>
                    </tr>             
                </tbody>
            </table>
        </div>


        <div>    
            <h4>Examples:</h4>    
            <ol>
                <li><a href="<%= org.bisel.phis.iss.client.GetISSURL.getURL()%>Annotation?action=create&phisid=abc123X19078&version=007&creatorid=solomon&imageid=emage_EMAGE_1218.1&x_value=33.333333333333336&x_value=63.75661375661376&y_value=42.79661016949152&y_value=70.33898305084746&z_value=0&z_value=0&dpt_anatomy_term=body%20cavity%20or%20lining&dpt_anatomy_term=anatomical%20cluster&dpt_anatomyid=EMAP:3144&dpt_anatomyid=EMAP:3144"><%= org.bisel.phis.iss.client.GetISSURL.getURL()%>Annotation?action=create&phisid=abc123X19078&version=007&creatorid=solomon&imageid=emage_EMAGE_1218.1&x_value=33.333333333333336&x_value=63.75661375661376&y_value=42.79661016949152&y_value=70.33898305084746&z_value=0&z_value=0&dpt_anatomy_term=body%20cavity%20or%20lining&dpt_anatomy_term=anatomical%20cluster&dpt_anatomyid=EMAP:3144&dpt_anatomyid=EMAP:3144</a>                            
                <li><a href="<%= org.bisel.phis.iss.client.GetISSURL.getURL()%>Annotation?action=delete&phisid=abc123X19078&version=007&creatorid=solomon"><%= org.bisel.phis.iss.client.GetISSURL.getURL()%>Annotation?action=delete&phisid=abc123X19078&version=007&creatorid=solomon</a>                            
            </ol>    
        </div>




        <hr />
        <h3>/Id</h3>



        <p style="background-color: orange;">Generates a valid PhIS ID using the EBI's ID generator service.</p>





        <div>
            <h4>parameters</h4>  
            <p>None!</p>
        </div>

        <div>
            <h4>Examples</h4>
            <ol>
                <li><a href="<%= org.bisel.phis.iss.client.GetISSURL.getURL()%>Id"><%= org.bisel.phis.iss.client.GetISSURL.getURL()%>Id</a></p></li>
            </ol>
        </div>




    </body>
</html>

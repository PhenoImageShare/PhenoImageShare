QUnit.config.reorder = false; //TODO: remove this line, not a good idea.

QUnit.module("ISS");

var base_url = "http://" + window.location.host + "/ISS";

//Annotation APIs unit tests
QUnit.test( "Add annotation (action=create,creatorid=solomon,phisid=roi_ua_test,imageid=emage_EMAGE_1218.1)", function( assert ) {
	
	var done = assert.async();
	//var url = "http://lxbisel.macs.hw.ac.uk:8080/ja/ISS/Annotation";
	
	var url = base_url + "/Annotation";
	
	var action = "create";
	var version = "008";
	var resultNo = 1;
	var phisid="roi_ua_test";
	var creatorid="solomon";
	var imageid = "emage_EMAGE_1218.1";
	var x_values = {x_value: [33.333333333333336, 63.75661375661376] };
	var y_values = {y_value: [42.79661016949152, 70.33898305084746] };
	var z_values = {z_value: [0, 0] };
	var dpt_anatomy_terms = {dpt_anatomy_term: ["body cavity or lining", "anatomical cluster"]};
	var dpt_anatomyids = {dpt_anatomyid: ["EMAP:3144", "EMAP:31445"]};
	
	var data = $.param({action:action, version:version, imageid: imageid, creatorid:creatorid, phisid:phisid,
				x_value: [33.333333333333336, 63.75661375661376],y_value: [42.79661016949152, 70.33898305084746],
				z_value: [0, 0],dpt_anatomy_term: ["body cavity or lining", "anatomical cluster"], dpt_anatomyid: ["EMAP:3144", "EMAP:31445"]}, true);
				
	var iqs_request = $.ajax({
		  						method: "POST",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.annotationId, undefined, "Valid response. Passed!" );
		assert.notEqual( response.status, undefined, "Valid response. Passed!" );
		
		var id = (response.annotationId != undefined ? response.annotationId : "undefined");
		var response_status = (response.status != undefined ? response.status : false);
		
		assert.equal( response_status, "success", "Annotation " + id + " successfully created!" );
		assert.equal( id, phisid, "Annotation Id ("+ id+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});

//Annotation APIs
QUnit.test( "Update annotation - dpt_anatomyid, dpt_anatomy_term and dpt_anatomy_text endpoints (action=edit,creatorid=solomon,phisid=roi_ua_test,imageid=emage_EMAGE_1218.1)", function( assert ) {
	
	var done = assert.async();	

	var url = base_url + "/Annotation";
	
	var action = "edit";
	var version = "008";
	var resultNo = 1;
	var phisid="roi_ua_test";
	var creatorid="solomon";
	var imageid = "emage_EMAGE_1218.1";
	var x_values = {x_value: [33.333333333333336, 63.75661375661376] };
	var y_values = {y_value: [42.79661016949152, 70.33898305084746] };
	var z_values = {z_value: [0, 0] };
	var dpt_anatomy_terms = {dpt_anatomy_term: ["heart_test", "blood_test"]};
	var dpt_anatomyids = {dpt_anatomyid: ["EMAP:31447", "EMAP:314456"]};
	
	var data = $.param({action:action, version:version, imageid: imageid, creatorid:creatorid, phisid:phisid,
				x_value: [33.333333333333336, 63.75661375661376],y_value: [42.79661016949152, 70.33898305084746],
				z_value: [0, 0],dpt_anatomy_term: ["heart_test", "blood_test"], dpt_anatomyid: ["EMAP:31447", "EMAP:314456"], "dpt_anatomy_text": ["heart_test text","blood_test text"]}, true);
				
				
				
	var iqs_request = $.ajax({
		  						method: "POST",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
						});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.annotationId, undefined, "Valid response. Passed!" );
		assert.notEqual( response.status, undefined, "Valid response. Passed!" );
		
		var id = (response.annotationId != undefined ? response.annotationId : "undefined");
		var response_status = (response.status != undefined ? response.status : false);
		
		assert.equal( response_status, "success", "Annotation "+ id +" successfully updated!" );
		assert.equal( id, phisid, "Annotation Id ("+ id+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Annotation APIs - editing annotation
QUnit.test( "Update annotation - testing phenotypeid, phenotype_text & phenotype_terms (action=edit,creatorid=solomon,phisid=roi_ua_test,imageid=emage_EMAGE_1218.1, phenotypeid = (EMAP:41447, EMAP:414456),  phenotype_terms = (cornea ulcer, cornea opacity), phenotype_text = (cornea ulcer text,cornea opacity text))", 
	
	function( assert ) {
	
	var done = assert.async();
	//var url = "http://lxbisel.macs.hw.ac.uk:8080/ja/ISS/Annotation";

	var url = base_url + "/Annotation";
	
	var action = "edit";
	var version = "008";
	var resultNo = 1;
	var phisid="roi_ua_test";
	var creatorid="solomon";
	var imageid = "emage_EMAGE_1218.1";
	
	var data = $.param({action:action, version:version, imageid: imageid, creatorid:creatorid, phisid:phisid,
				x_value: [33.333333333333336, 63.75661375661376],y_value: [42.79661016949152, 70.33898305084746],
				z_value: [0, 0],phenotype_term: ["cornea ulcer", "cornea opacity"], phenotype_text: ["cornea ulcer text","cornea opacity text"],phenotypeid: ["EMAP:41447", "EMAP:414456"]}, true);
				
				
				
	var iqs_request = $.ajax({
		  						method: "POST",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
						});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.annotationId, undefined, "Valid response. Passed!" );
		assert.notEqual( response.status, undefined, "Valid response. Passed!" );
		
		var id = (response.annotationId != undefined ? response.annotationId : "undefined");
		var response_status = (response.status != undefined ? response.status : false);
		
		assert.equal( response_status, "success", "Annotation "+ id +" successfully updated!" );
		assert.equal( id, phisid, "Annotation Id ("+ id+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});

//Annotation APIs - editing annotation
QUnit.test( "Update annotation - testing ge_anatomyid, ge_anatomy_term & ge_anatomy_text (action=edit,creatorid=solomon,phisid=roi_ua_test,imageid=emage_EMAGE_1218.1,ge_anatomyid = (EMAP:51447, EMAP:514456), ge_anatomy_term = (test GE term, test GE term), ge_anatomy_text = (test GE term text, test GE term text 2))", function( assert ) {
	
	var done = assert.async();
	//var url = "http://lxbisel.macs.hw.ac.uk:8080/ja/ISS/Annotation";

	var url = base_url + "/Annotation";
	
	var action = "edit";
	var version = "008";
	var resultNo = 1;
	var phisid="roi_ua_test";
	var creatorid="solomon";
	var imageid = "emage_EMAGE_1218.1";
	
	var data = $.param({action:action, version:version, imageid: imageid, creatorid:creatorid, phisid:phisid,
				x_value: [33.333333333333336, 63.75661375661376],y_value: [42.79661016949152, 70.33898305084746],
				z_value: [0, 0],ge_anatomy_term: ["test GE term", "test GE term2"], ge_anatomyid: ["EMAP:51447", "EMAP:514456"], ge_anatomy_text:["test GE term text","test GE term text2"]}, true);
				
				
	console.log(data);
	
	var iqs_request = $.ajax({
		  						method: "POST",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
						});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.annotationId, undefined, "Valid response. Passed!" );
		assert.notEqual( response.status, undefined, "Valid response. Passed!" );
		
		var id = (response.annotationId != undefined ? response.annotationId : "undefined");
		var response_status = (response.status != undefined ? response.status : false);
		
		assert.equal( response_status, "success", "Annotation "+ id +" successfully updated ! Message: "+response.message + "!" );
		assert.equal( id, phisid, "Annotation Id ("+ id+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});

//Annotation APIs - editing annotation
QUnit.test( "Update annotation - testing abn_anatomyid, abn_anatomy_term & abn_anatomy_text (action=edit,creatorid=solomon,phisid=roi_ua_test,imageid=emage_EMAGE_1218.1, abn_anatomyid = (EMAP:61447, EMAP:514456), abn_anatomy_term = (abnormal head shape - test, abnormal hair cycle - test), abn_anatomy_text = (abnormal head shape - test text, abnormal hair cycle - test text))", 
	
	function( assert ) {
	
	var done = assert.async();
	//var url = "http://lxbisel.macs.hw.ac.uk:8080/ja/ISS/Annotation";

	var url = base_url + "/Annotation";
	
	var action = "edit";
	var version = "008";
	var resultNo = 1;
	var phisid="roi_ua_test";
	var creatorid="solomon";
	var imageid = "emage_EMAGE_1218.1";
	
	var data = $.param({action:action, version:version, imageid: imageid, creatorid:creatorid, phisid:phisid,
				x_value: [33.333333333333336, 63.75661375661376],y_value: [42.79661016949152, 70.33898305084746],
				z_value: [0, 0],ge_anatomy_term: ["abnormal head shape - test", "abnormal hair cycle - test"], 
				ge_anatomyid: ["EMAP:51447", "EMAP:514456"], ge_anatomy_text:["abnormal head shape - test text"," abnormal hair cycle - test text"]}, true);
				
				
				
	var iqs_request = $.ajax({
		  						method: "POST",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
						});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.annotationId, undefined, "Valid response. Passed!" );
		assert.notEqual( response.status, undefined, "Valid response. Passed!" );
		
		var id = (response.annotationId != undefined ? response.annotationId : "undefined");
		var response_status = (response.status != undefined ? response.status : false);
		
		assert.equal( response_status, "success", "Annotation "+ id +" successfully updated!" );
		assert.equal( id, phisid, "Annotation Id ("+ id+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});



//Annotation APIs - deleting annotation
QUnit.test( "Delete annotation (action=delete,creatorid=solomon,phisid=roi_ua_test,imageid=emage_EMAGE_1218.1)", function( assert ) {
	
	var done = assert.async();
	//var url = "http://lxbisel.macs.hw.ac.uk:8080/ja/ISS/Annotation";

	var url = base_url + "/Annotation";
	
	var action = "delete";
	var version = "008";
	var resultNo = 1;
	var phisid="roi_ua_test";
	var creatorid="solomon";
	var imageid = "emage_EMAGE_1218.1";
	
	var data = $.param({action:action, version:version, imageid: imageid, creatorid:creatorid, phisid:phisid}, true);
	
	console.log(data);
				
	var iqs_request = $.ajax({
		  						method: "POST",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.annotationId, undefined, "Valid response. Passed!" );
		assert.notEqual( response.status, undefined, "Valid response. Passed!" );
		
		//test documents returned
		
		
		console.log(response);
		
		var id = (response.annotationId != undefined ? response.annotationId : "undefined");
		var response_status = (response.status != undefined ? response.status : false);
		
		assert.equal( response_status, "success", "Annotation "+ id +" successfully deleted!" );
		assert.equal( id, phisid, "Annotation Id ("+ id+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});



// Test ID creation
QUnit.test( "Get PhIS ID /Id()", function( assert ) {
	
	var done = assert.async();	
	var url = base_url + "/Id";
	var data = $.param({"version":"008"});
				
	var iqs_request = $.ajax({
		  						method: "POST",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.equal( response.result, "success", "Annotation id successfully created!" );
                var id = (response.phisid !== undefined ? response.phisid : "undefined");
                assert.notEqual(id, undefined, "Testing to see if phis id exists");
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});
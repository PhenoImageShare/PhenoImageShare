QUnit.config.reorder = false; //TODO: remove this line, not a good idea.

QUnit.module( "IQS" );

var base_url = "http://" + window.location.host +"/IQS";

//getImage unit test
//Unit test for image=emage_roi_EMAGE_2795.1, samplePreparation=living tissue endpoints
QUnit.test( "getImage (imageId=wtsi_komp2_112968)", function( assert ) {
	
	var done = assert.async();
	
	
	var url = base_url + "/getImage";
	var data = {"imageId":"wtsi_komp2_112968", "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, undefined, "Valid response. Passed!" );
		assert.notEqual( response.response.docs[0], undefined, "Response contains document. Passed!" );
		
		//test documents returned
		
		var doc_length = response.response.docs.length;
		var id = (response.response.docs[0] != undefined ? response.response.docs[0].id : "undefined");
		
		assert.equal( doc_length, 1, "1 match(es) found. Passed!" );
		assert.equal( id, "wtsi_komp2_112968", "Image Id ("+ id+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


QUnit.test( "getImages (term=eye)", function( assert ) {
	var done = assert.async();
	var url = base_url + "/getImages";
	
	var data = {"term":"eye", "num": 1, "version":"007"};
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.docs[0], undefined, "Contains document. Passed!" );
		var doc_length = response.response.docs.length;
		
		assert.equal( doc_length, 1, doc_length + " match(es) found. Passed!" );
		  done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
 
});


//Unit test for term=eye, num=55 endpoint
QUnit.test( "getImages (term=eye, num=55)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getImages";
	
	var data = {"term":"eye", "num": 55, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		var doc_length = response.response.docs.length;
		
		assert.equal( doc_length, 55, doc_length + " match(es) found. Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for term=eye, start=24 endpoint
QUnit.test( "getImages (term=eye, start=24)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getImages";
		
	var data = {"term":"eye", "start": 24, "num":1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		var doc_length = response.response.docs.length;
		var doc_start = response.response.start;
		
		assert.equal( doc_length, 1, doc_length + " match(es) found. Passed!" );
		assert.equal( doc_start, 24, " Result starts at " + doc_start + ". Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});

//Unit test for phenotype endpoint
QUnit.test( "getImages (phenotype=cornea)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getImages";
		
	var data = {"phenotype":"cornea", "num": 1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		var doc_length = response.response.docs.length;
		assert.equal( doc_length, 1, "Passed!" );
		  done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
 
  
});

//Unit test for anatomy endpoint
QUnit.test( "getImages (anatomy=limb)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getImages";
		
	var data = {"anatomy":"limb", "num": 1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		var doc_length = response.response.docs.length;
		assert.equal( doc_length, 1, "Passed!" );
		  done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
 
  
});

//Unit test for mutant gene endpoint
QUnit.test( "getImages (mutantGene=spns2)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getImages";
		
	var data = {"mutantGene":"spns2", "num": 1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		var doc_length = response.response.docs.length;
		assert.equal( doc_length, 1, "Passed!" );
		  done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for expressedFeature endpoint
QUnit.test( "getImages (expressedFeature=Sesn3)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getImages";
	
	var data = {"expressedFeature":"Sesn3", "num": 1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		var doc_length = response.response.docs.length;
		assert.equal( doc_length, 1, "Passed!" );
		  done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for sex=FEMALE, term=eye endpoints
QUnit.test( "getImages (sex=FEMALE, term=eye)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getImages";
	
	var data = {"sex":"FEMALE","term":"eye", "num": 1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		var doc_length = response.response.docs.length;
		var sex = response.response.docs[0].sex;
		
		assert.equal( doc_length, 1, "Matches found Passed!" );
		assert.equal( sex, "FEMALE", "Sex = FEMALE Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for sex=FEMALE, term=eye, taxon=Mus musculus endpoints
QUnit.test( "getImages (sex=FEMALE, term=eye, taxon=Mus musculus)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getImages";
	
	var data = {"sex":"FEMALE","term":"eye", "taxon":"Mus musculus", "num": 1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		var doc_length = response.response.docs.length;
		var sex = response.response.docs[0].sex;
		var species = response.response.docs[0].taxon;
		
		console.log(species);
		
		assert.equal( doc_length, 1, "Matches found Passed!" );
		assert.equal( sex, "FEMALE", "Sex (" + sex + ") Passed!" );
		assert.equal( species, "Mus musculus", "Species (" + species + ") Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for sex=FEMALE, term=neuroblast lineage, taxon=Drosophila melanogaster endpoints
QUnit.test( "getImages (sex=FEMALE, term=neuroblast lineage, taxon=Drosophila melanogaster)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getImages";
	
	var data = {"sex":"FEMALE","term":"neuroblast lineage", "taxon":"Drosophila melanogaster", "num": 1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		var doc_length = response.response.docs.length;
		var sex = response.response.docs[0].sex;
		var species = response.response.docs[0].taxon;
		
		assert.equal( doc_length, 1, "Matches found Passed!" );
		assert.equal( sex, "FEMALE", "Sex (" + sex + ") Passed!" );
		assert.equal( species, "Drosophila melanogaster", "Species ("+ species+ ") Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for term=heart, sampleType=WILD_TYPE endpoints
QUnit.test( "getImages (term=heart, sampleType=WILD_TYPE)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getImages";	
	
	var data = {"term":"heart", "sampleType":"WILD_TYPE", "num": 1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		var doc_length = response.response.docs.length;
		var sampleType = response.response.docs[0].sample_type;
		
		assert.equal( doc_length, 1, "Match(es) found Passed!" );
		assert.equal( sampleType, "WILD_TYPE", "Sample type ("+ sampleType+ ") Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for term=heart, imageType=EXPRESSION endpoints
QUnit.test( "getImages (term=heart, imageType=EXPRESSION)", function( assert ) {
	
	var done = assert.async();	
	var url = base_url + "/getImages";
	
	var data = {"term":"heart", "imageType":"EXPRESSION", "num": 1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		
		var doc_length = response.response.docs.length;
		var imageType = response.response.docs[0].image_type;
		
		
		assert.equal( doc_length, 1, "Match(es) found. Passed!" );
		assert.equal( imageType, "EXPRESSION", "Image type ("+ imageType+ ") Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for term=head, stage=postnatal stage endpoints
QUnit.test( "getImages (term=head, stage=postnatal stage)", function( assert ) {
	
	var done = assert.async();		
	var url = base_url + "/getImages";
		
	var data = {"term":"head", "stage":"postnatal stage", "num": 1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		
		var doc_length = response.response.docs.length;
		var stage = response.response.docs[0].stage;
		
		
		assert.equal( doc_length, 1, "1 match(es) found. Passed!" );
		assert.equal( stage, "postnatal stage", "Stage ("+ stage+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for term=head, samplePreparation=living tissue endpoints
QUnit.test( "getImages (term=head, samplePreparation=living tissue)", function( assert ) {
	
	var done = assert.async();		
	var url = base_url + "/getImages";
	
	var data = {"term":"head", "samplePreparation":"living tissue", "num": 1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		
		var doc_length = response.response.docs.length;
		var samplePreparations = response.response.docs[0].sample_preparation_label;
		var samplePreparation = 'living tissue';
		var test_value = samplePreparations.indexOf(samplePreparation) > -1;
		
		
		assert.equal( doc_length, 1, "1 match(es) found. Passed!" );
		assert.ok( test_value, "document contains "+ samplePreparation + ". Passed!" );
		assert.equal( samplePreparation, "living tissue", "Sample preparation ("+ samplePreparation+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});

//Unit test for term=head, samplePreparation=living tissue endpoints
QUnit.test( "getImages (term=abnormal, imagingMethod=macroscopy)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getImages";
		
	var data = {"term":"abnormal", "imagingMethod":"macroscopy", "num": 1, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		assert.notEqual( response.response, undefined, "Valid response. Passed!" );
		
		var doc_length = response.response.docs.length;
		var imagingMethod = response.response.docs[0].imaging_method_label;
		
		assert.equal( doc_length, 1, "1 match(es) found. Passed!" );
		assert.equal( imagingMethod, "macroscopy", "Imaging method ("+ imagingMethod+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});

//getRoi unit test - using IQS documentation example
//Unit test for id=emage_roi_EMAGE_2795.1, samplePreparation=living tissue endpoints
QUnit.test( "getROI (id=wtsi_roi_komp2_roi_112003_0)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getRoi";
		
	var data = {"id":"wtsi_roi_komp2_roi_112003_0", "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.docs[0], undefined, "Valid response. Passed!" );
		
		//test documents returned
		
		var doc_length = response.response.docs.length;
		var id = (response.response.docs[0] != undefined ? response.response.docs[0].id : "undefined");
		
		assert.equal( doc_length, 1, "1 match(es) found. Passed!" );
		assert.equal( id, "wtsi_roi_komp2_roi_112003_0", "ROI Id ("+ id+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});

//getRoi unit test
//Unit test for id endpoints
QUnit.test( "getROI (id=emage_roi_EMAGE_2795.1)", function( assert ) {
	
	var done = assert.async();	
	var url = base_url + "/getRoi";	

	var data = {"id":"emage_roi_EMAGE_2795.1", "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.docs[0], undefined, "Valid response. Passed!" );
		
		//test documents returned
		
		var doc_length = response.response.docs.length;
		var roiId = (response.response.docs[0] != undefined ? response.response.docs[0].id : "undefined");
		var imageId = (response.response.docs[0] != undefined ? response.response.docs[0].associated_image : "undefined");
		
		assert.equal( doc_length, 1, "1 match(es) found. Passed!" );
		assert.equal( roiId, "emage_roi_EMAGE_2795.1", "ROI Id ("+ roiId+ "). Passed!" );
		assert.equal( imageId, "emage_EMAGE_2795.1", "Associated image Id ("+ imageId+ "). Passed!" );
		
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//getRois unit test
//Unit test for imageId=wtsi_komp2_112003 endpoint
QUnit.test( "getROIs (imageId=wtsi_komp2_112003)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getRois";

	var data = {"imageId":"wtsi_komp2_112003", "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.docs[0], undefined, "Response contains documents. Passed!" );
		
		//test documents returned
		var doc_length = response.response.docs.length;
		var imageId = (response.response.docs[0] != undefined ? response.response.docs[0].associated_image : "undefined");
		var roiId = (response.response.docs[0] != undefined ? response.response.docs[0].id : "undefined");
		
		assert.equal( doc_length, 1, "1 match(es) found. Passed!" );
		assert.equal( imageId, "wtsi_komp2_112003", "Associated image id ("+ imageId+ "). Passed!" );
		assert.equal( roiId, "wtsi_roi_komp2_roi_112003_0", "Associated image id ("+ roiId+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});



//getChannel unit test
//Unit test for id=wtsi_channel_komp2_channel_112003_0 endpoint
QUnit.test( "getChannel (id=wtsi_channel_komp2_channel_112003_0)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getChannel";
	
	var data = {"id":"wtsi_channel_komp2_channel_112003_0", "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.docs[0], undefined, "Response contains documents. Passed!" );
		
		//test documents returned
		
		var doc_length = response.response.docs.length;
		var channelId = (response.response.docs[0] != undefined ? response.response.docs[0].id : "undefined");
		var imageId = (response.response.docs[0] != undefined ? response.response.docs[0].associated_image : "undefined");
		
		
		assert.equal( doc_length, 1, "1 match(es) found. Passed!" );
		assert.equal( channelId, "wtsi_channel_komp2_channel_112003_0", "Channel id ("+ channelId+ "). Passed!" );
		assert.equal( imageId, "wtsi_komp2_112003", "Associated image id ("+ imageId+ "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//getChannels unit test
//Unit test for imageId=wtsi_channel_komp2_channel_112003_0 endpoint
QUnit.test( "getChannels(imageId=wtsi_komp2_112003)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getChannels";
	
	var data = {"imageId":"wtsi_komp2_112003", "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.docs[0], undefined, "Response contains documents. Passed!" );
		
		//test documents returned
		
		var doc_length = response.response.docs.length;
		var imageId = (response.response.docs[0] != undefined ? response.response.docs[0].associated_image : "undefined");
		var channelId = (response.response.docs[0] != undefined ? response.response.docs[0].id : "undefined");
		
		assert.equal( doc_length, 1, "1 match(es) found. Passed!" );
		assert.equal( imageId, "wtsi_komp2_112003", "Associated image id ("+ imageId + "). Passed!" );
		assert.equal( channelId, "wtsi_channel_komp2_channel_112003_0", "Channel id ("+ channelId + "). Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//getAutosuggest unit tests

//Unit test for term=abn endpoint
QUnit.test( "getAutosuggest(term=abn)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getAutosuggest";
	var term = 'abn';
	var data = {"term":term, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.suggestions[0], undefined, "Response contains documents. Passed!" );
		
		//test documents returned for containing 'abn' string. assumption is that response values all contain string.
		var doc_length = response.response.suggestions.length;
		var check = true;
		
		for (var i = 0 ; i < doc_length; i++){
			if (response.response.suggestions[i].indexOf(term) == -1 ){
				check = false;
			}
		}
		
		assert.ok( check, "Suggestions contain " + term + ". Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for term=ab, asType endpoints (typed autosuggest)
QUnit.test( "getAutosuggest(term=ab, asType=GENE)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getAutosuggest";
	
	var term = 'ab';
	var asType = "GENE";
	
	//validation data
	var anatomy_term = "abnominal";
	var phenotype_term = "abnormal";
	
	var data = {"term":term, "asType":asType, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.suggestions[0], undefined, "Response contains documents. Passed!" );
		
		//test documents returned genes containing 'ab' string. test response against existence of anatomy & phenotype terms.
		
		var doc_length = response.response.suggestions.length;
		var check = true;
		
		for (var i = 0 ; i < doc_length; i++){
			if (response.response.suggestions[i].indexOf(anatomy_term) != -1 || response.response.suggestions[i].indexOf(phenotype_term) != -1 ){
				check = false;
			}
		}
		
		assert.ok( check, "Suggestions are genes containing " + term + ". Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for term=ab, asType endpoints (typed autosuggest)
QUnit.test( "getAutosuggest(term=ab, asType=ANATOMY)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getAutosuggest";
	
	var term = 'ab';
	var asType = "ANATOMY";
		
	var data = {"term":term, "asType":asType, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.suggestions[0], undefined, "Response contains documents. Passed!" );
		
		//test documents returned genes containing 'ab' string. test response against existence of anatomy & phenotype terms.
		
		var doc_length = response.response.suggestions.length;
		var check = true;
		
		for (var i = 0 ; i < doc_length; i++){
			if (response.response.suggestions[i].indexOf(term) === -1 ){
				check = false;
			}
		}
		
		assert.ok( check, "Suggestions are anatomy terms containing " + term + ". Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});

//Unit test for term=ab, asType endpoints (typed autosuggest)
QUnit.test( "getAutosuggest(term=ab, asType=PHENOTYPE)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getAutosuggest";
	
	var term = 'ab';
	var asType = "PHENOTYPE";
	
	//validation data
	var gene_term = "Abcd1";
	var anatomy_term = "abdominal";
	
	//sample, expected phenotype term
	var phenotype_term = "abnormal"
	
	var data = {"term":term, "asType":asType, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.suggestions[0], undefined, "Response contains documents. Passed!" );
		
		//test documents returned genes containing 'ab' string. test response against existence of anatomy & phenotype terms.
		
		var doc_length = response.response.suggestions.length;
		var term_check = true;
		var phenotype_term_check = false;
		var other_terms_check = true;
		
		for (var i = 0 ; i < doc_length; i++){
			if (response.response.suggestions[i].indexOf(gene_term) != -1 || response.response.suggestions[i].indexOf(anatomy_term) != -1 ){
				other_terms_check = false;
			}
		}
		
		for (var i = 0 ; i < doc_length; i++){
			if (response.response.suggestions[i].indexOf(term) == -1 ){
				term_check = false;
			}
		}
		
		for (var i = 0 ; i < doc_length; i++){
			if (response.response.suggestions[i].indexOf(phenotype_term) != -1 ){
				phenotype_term_check = true;
			}
		}
		
		assert.ok( term_check, "Suggestions are phenotype terms containing " + term + ". Passed!" );
		assert.ok( phenotype_term_check, "Suggestions are phenotype terms containing " + phenotype_term + ". Passed!" );
		assert.ok( other_terms_check, "Suggestions are phenotype terms that do not contain gene " + gene_term + " or anatomy term " + anatomy_term + ". Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});

///Unit test for term=abn and stage=postnatal stage endpoints
QUnit.test("getAutosuggest(term=adult, stage=adult stage)", function (assert) {

    var done = assert.async();
    var url = base_url + "/getAutosuggest";
    var data = {"term": "adult", "stage": "adult stage", "version": "007"};

    var iqs_request = $.ajax({
        method: "GET",
        url: url,
        data: data,
        //crossdomain: true,
        dataType: 'json',
        //traditional:true
    });

    var iqs_response = iqs_request.done(function (response) {
        //tests for valid response
        assert.notEqual(response.response, "undefined", "Valid response.");
        assert.notEqual(response.response.suggestions[0], "undefined", "Response contains documents.");

        //test documents returned genes containing 'ab' string. test response against existence of anatomy & phenotype terms.

        var doc_length = response.response.suggestions.length;
        var term_check = true;

        for (var i = 0; i < doc_length; i++) {
            if (response.response.suggestions[i].indexOf("adult") == -1) {
                term_check = false;
                break;
            } 
        }

        assert.ok(term_check, "Suggestions contain adult.");

        done();
    });

    iqs_request.fail(function (jqXHR, textStatus) {
        console.log(textStatus);
    });

});
  


//Unit test for term=abn and imagingMethod=macroscopy endpoints
QUnit.test( "getAutosuggest(term=abn, imagingMethod=macroscopy)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getAutosuggest";
	
	var imagingMethod = 'macroscopy';
	var term = "abn";
	
	var data = {"term":term, "imagingMethod":imagingMethod, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.suggestions[0], undefined, "Response contains documents. Passed!" );
		
		//test documents returned genes containing 'ab' string. test response against existence of anatomy & phenotype terms.
		
		var doc_length = response.response.suggestions.length;
		var term_check = false;
		
		for (var i = 0 ; i < doc_length; i++){
			if (response.response.suggestions[i].indexOf(term) == -1 ){
				term_check = false;
				break;
			}else{
				term_check = true;
			}
		}

		assert.ok( term_check, "Suggestions contain " + term + ". Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for term=abn and taxon=Mus musculus endpoints
QUnit.test( "getAutosuggest(term=abn, taxon=Mus musculus)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getAutosuggest";
		
	var term = "abn";
	
	var data = {"term":"abn", "taxon":"Mus musculus", "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. " );
		assert.notEqual( response.response.suggestions[0], undefined, "Response contains documents. " );
		
		//test documents returned genes containing 'ab' string. test response against existence of anatomy & phenotype terms.
		
		var doc_length = response.response.suggestions.length;
		var term_check = false;
		
		for (var i = 0 ; i < doc_length; i++){
			if (response.response.suggestions[i].indexOf(term) == -1 ){
				term_check = false;
				break;
			}else{
				term_check = true;
			}
		}

		assert.ok( term_check, "Suggestions contain " + term + ". " );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});


//Unit test for term=abn and sampleType=MUTANT endpoints
QUnit.test( "getAutosuggest(term=abn, sampleType=MUTANT)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getAutosuggest";
	
	var sampleType = 'MUTANT';
	var term = "abn";
	
	var data = {"term":term, "sampleType":sampleType, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.suggestions[0], undefined, "Response contains documents. Passed!" );
		
		//test documents returned genes containing 'ab' string. test response against existence of anatomy & phenotype terms.
		
		var doc_length = response.response.suggestions.length;
		var term_check = false;
		
		for (var i = 0 ; i < doc_length; i++){
			if (response.response.suggestions[i].indexOf(term) == -1 ){
				term_check = false;
				break;
			}else{
				term_check = true;
			}
		}

		assert.ok( term_check, "Suggestions contain " + term + ". Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});



//Unit test for term=abn and imageGeneratedBy=WTSI endpoints
QUnit.test( "getAutosuggest(term=abn, imageGeneratedBy=WTSI)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getAutosuggest";
	
	var imageGeneratedBy = 'WTSI';
	var term = "abn";
	
	var data = {"term":term, "imageGeneratedBy":imageGeneratedBy, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.suggestions[0], undefined, "Response contains documents. Passed!" );
		
		//test documents returned genes containing 'ab' string. test response against existence of anatomy & phenotype terms.
		
		var doc_length = response.response.suggestions.length;
		var term_check = false;
		
		for (var i = 0 ; i < doc_length; i++){
			if (response.response.suggestions[i].indexOf(term) == -1 ){
				term_check = false;
				break;
			}else{
				term_check = true;
			}
		}

		assert.ok( term_check, "Suggestions contain " + term + ". Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});

//Unit test for term=abn and num=20 endpoints
QUnit.test( "getAutosuggest(term=abn, num=20)", function( assert ) {
	
	var done = assert.async();
	var url = base_url + "/getAutosuggest";
	
	var num = '20';
	var term = "abn";
	
	var data = {"term":term, "num":num, "version":"007"};
	
	var iqs_request = $.ajax({
		  						method: "GET",
	  							url: url,
		  						data: data,
								//crossdomain: true,
								dataType:'json',
								//traditional:true
							});
	
	var iqs_response =  iqs_request.done(function( response ) {
		//tests for valid response
		assert.notEqual( response.response, "undefined", "Valid response. Passed!" );
		assert.notEqual( response.response.suggestions[0], undefined, "Response contains documents. Passed!" );
		
		//test documents returned genes containing 'ab' string. test response against existence of anatomy & phenotype terms.
		
		var doc_length = response.response.suggestions.length;
		var term_check = false;
		
		for (var i = 0 ; i < doc_length; i++){
			if (response.response.suggestions[i].indexOf(term) == -1 ){
				term_check = false;
				break;
			}else{
				term_check = true;
			}
		}
		
		assert.equal( doc_length, 20,  doc_length + " match(es) found. Passed!" );
		assert.ok( term_check, "Suggestions contain " + term + ". Passed!" );
		
		done();
	});
	
	iqs_request.fail(function( jqXHR, textStatus ) {
	  console.log(textStatus );
  });
  
});
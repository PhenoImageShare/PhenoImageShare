-- This should give the regions overlapped by the expression pattern of some transgene
SELECT DISTINCT fbbt.cvterm_name AS anatomy, fbbt.db_name AS idp, fbbt.accession, teg.transgene_name, teg.transgene_uniquename
	FROM vfbview_fbbt fbbt
  	JOIN expression_cvterm ec1 ON (fbbt.cvterm_id = ec1.cvterm_id)
  	JOIN expression_cvterm ec2 ON (ec2.expression_id = ec1.expression_id)
  	JOIN cvterm stage ON (stage.cvterm_id = ec2.cvterm_id)
  	JOIN feature_expression fe ON (ec1.expression_id = fe.expression_id)
  	JOIN pub ON (fe.pub_id = pub.pub_id)
  	JOIN vfbview_transgene_expressed_gp teg ON (teg.gp_feature_id = fe.feature_id)
	WHERE teg.transgene_name = ''
	AND pub.uniquename = ''; -- Interesting question - should we limit to source ref?  Perhaps in this case....

/* Use the results to populate expression indexes.  Assign just one ROI for whole image? 

For insertion - can I find appropriate accession for linking?








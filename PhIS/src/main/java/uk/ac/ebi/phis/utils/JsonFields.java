/*******************************************************************************
 * Copyright 2015 EMBL - European Bioinformatics Institute
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
 *******************************************************************************/
package uk.ac.ebi.phis.utils;

public final class JsonFields {
//	public static final String DOCUMENT_TYPE = "document_type";
    
//    <!-- PISh ids-->
    public static final String ID = "pish_id";
//    <!-- mainly image documents -->
    public static final String ORGANISM = "organism";
    public static final String SEX = "sex";
    public static final String AGE = "age";
    public static final String EMBRYO_AGE = "embryo_age";
    public static final String STAGE = "stage";
    public static final String CONDITIONS = "conditions";
    public static final String DATA_SOURCE = "data_source";
    public static final String IMAGE_URL = "image_url";
    public static final String IMAGE_CONTEXT_URL = "image_context_url";
    public static final String THUMBNAIL_PATH = "thumbnail_path";
    public static final String CENTER = "center";
    public static final String MACHINE = "machine";
    public static final String PROCEDURE = "procedure";
    public static final String IMAGE_TYPE = "image_type";
    public static final String FORMAT = "format";
    public static final String PHENOTYPE_ANN_LIST = "phenotype_ann_bag";
    public static final String ANATOMY_ANN_LIST = "anatomy_ann_bag"; // directed or computed anatomy annotations
    public static final String ANATOMY_PHENOTYPE_LIST = "anatomy_from_phenotype_bag"; // computed anatomy terms from phenotype ann
    public static final String ANATOMY_COMPUTED_LIST = "anatomy_computed_bag"; // computed anatomy terms from phenotype ann
    public static final String EXPRESSED_ANATOMY_ANN_LIST = "expressed_anatomy_bag"; // directed or computed expressed anatomy ann
    public static final String OTHER_ANN_LIST = "other_ann_bag";
    public static final String EXPRESSED_GF_LIST = "expressed_gf_bag";
    public static final String ORIGINAL_IMAGE_ID = "original_image_id";
    
//    <!-- applicable for mutant images only -->
//    <!-- Do we want any of these multivalued? -->
    public static final String GENE_NAME = "gene_name";
    public static final String GENE_ID = "gene_id";
    public static final String GENETIC_FEATURE_ID = "genetic_feature_id";
    public static final String GENETIC_FEATURE_NAME = "genetic_feature_name";
    public static final String GENETIC_FEATURE_ENSEMBL_ID = "genetic_feature_ensembl_id";
    public static final String CHROMOSOME = "chromosome";
    public static final String START_POS = "start_pos";
    public static final String INSERTION_SITE = "insertion_site";
    public static final String END_POS = "end_pos";
    public static final String STRAND = "strand";
    public static final String ZYGOSITY = "zygosity";
    
//    <!-- assiciated ids -->
    public static final String ASSOCIATED_ROI = "associated_roi";
    public static final String ASSOCIATED_CHANNEL = "associated_channel";
    
//    <!-- annotations -->
    public static final String ANATOMY_ID = "anatomy_id";
    public static final String ANATOMY_TERM = "anatomy_term";
    public static final String ANATOMY_FREETEXT = "anatomy_freetext";
//    <!-- should we have phenotypes at the image level actually? I'm beginning to think they should belong to the ROIs -->
    public static final String PHENOTYPE_ID = "phenotype_id";
    public static final String PHENOTYPE_TERM = "phenotype_term";
    public static final String PHENOTYPE_FREETEXT = "phenotype_freetext";
    public static final String PHENOTYPE_EQ = "phenotype_eq";
    public static final String PHENOTYPE_EQ_LABELS = "phenotype_eq_labels";
    
    public static final String OBSERVATIONS = "observations";
    
//    <!-- for all ontology terms we nee to make the difference between human-annotated and computer annotated -->
//    <!-- example of computed terms: 1. we map a controlled vocabulary to EMAP (done by hand) but we approximate TS from age, thus we can't be sure we are correct and also don't want to make the image source look bad making it seem we hd the information from them -->
//    <!-- 2. anatomy terms we can get from annotated phenotype terms, for the ontologies which have EQ mappings -->
    public static final String ANATOMY_COMPUTED_ID = "anatomy_computed_id";
    public static final String ANATOMY_COMPUTED_TERM = "anatomy_compted_term";
    public static final String PEHNOTYPE_COMPUTED_IF = "phenotype_computed_id";
    public static final String PHENOTYPE_COMPUTED_TERM = "phenotype_computed_term";
    
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String DEPTH = "depth";
    
    
    
//    <!------ Document type: ROI ------>
    
    public static final String ASSOCIATED_IMAGE = "associated_image";
//    <!-- associated_channel -->
//    <!-- let's start with rectangular shapes -->
    public static final String X_COORDINATES = "x_coordinates";
    public static final String Y_COORDINATES = "y_coordinates";
    public static final String Z_COORDINATES = "z_coordinates";
    
//    <!-- ROI specific annotations -->
    public static final String SIGNAL_STRENGTH = "signal_strength";
//    <!-- expression annotations, so expession images only -->
//    <!-- Do we need phenotype expression fields too? -->
//    <!-- ROI not-specific fields anatomy_id, anatomy_term, anatomy_freetext, phenotype_id, phenotype_term, phenotype_freetext, phenotype_eq, phenotype_eq_labels, observations -->
//    <!-- once we get user annotations we'll need more fields: -->
    public static final String AUTHOR = "author";    
    
    
//    <!----- Document type : Channel ------>
    
    public static final String CHANNEL_NAME = "chanel_name";
//    <!-- associated_image , associated_ROI -->
    public static final String STAINING = "staining";
    

}

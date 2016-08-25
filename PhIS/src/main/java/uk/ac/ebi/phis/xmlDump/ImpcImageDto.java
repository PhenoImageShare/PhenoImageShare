package uk.ac.ebi.phis.xmlDump;

import org.apache.solr.client.solrj.beans.Field;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ilinca on 25/08/2016.
 */
public class ImpcImageDto {
    public final static String ID = "id";
    public final static String DATASOURCE_ID = "datasource_id";
    public final static String DATASOURCE_NAME = "datasource_name";
    public final static String PROJECT_ID = "project_id";
    public final static String PROJECT_NAME = "project_name";
    public final static String PHENOTYPING_CENTER = "phenotyping_center";
    public final static String PHENOTYPING_CENTER_ID = "phenotyping_center_id";
    public final static String PRODUCTION_CENTER = "production_center";
    public final static String PRODUCTION_CENTER_ID = "production_center_id";
    public final static String LITTER_ID = "litter_id";
    public final static String GENE_ACCESSION_ID = "gene_accession_id";
    public final static String GENE_SYMBOL = "gene_symbol";
    public final static String ALLELE_ACCESSION_ID = "allele_accession_id";
    public final static String ALLELE_SYMBOL = "allele_symbol";
    public final static String ZYGOSITY = "zygosity";
    public final static String SEX = "sex";
    public final static String BIOLOGICAL_MODEL_ID = "biological_model_id";
    public final static String BIOLOGICAL_SAMPLE_ID = "biological_sample_id";
    public final static String BIOLOGICAL_SAMPLE_GROUP = "biological_sample_group";
    public final static String STRAIN_ACCESSION_ID = "strain_accession_id";
    public final static String STRAIN_NAME = "strain_name";
    public final static String GENETIC_BACKGROUND = "genetic_background";
    public final static String ALLELIC_COMPOSITION = "allelic_composition";
    public final static String PIPELINE_NAME = "pipeline_name";
    public final static String PIPELINE_ID = "pipeline_id";
    public final static String PIPELINE_STABLE_ID = "pipeline_stable_id";
    public final static String PROCEDURE_ID = "procedure_id";
    public final static String PROCEDURE_NAME = "procedure_name";
    public final static String PROCEDURE_STABLE_ID = "procedure_stable_id";
    public final static String PROCEDURE_GROUP = "procedure_group";
    public final static String PARAMETER_ID = "parameter_id";
    public final static String PARAMETER_NAME = "parameter_name";
    public final static String PARAMETER_STABLE_ID = "parameter_stable_id";
    public final static String EXPERIMENT_ID = "experiment_id";
    public final static String EXPERIMENT_SOURCE_ID = "experiment_source_id";
    public final static String OBSERVATION_TYPE = "observation_type";
    public final static String COLONY_ID = "colony_id";
    public final static String POPULATION_ID = "population_id";
    public final static String EXTERNAL_SAMPLE_ID = "external_sample_id";
    public final static String DATA_POINT = "data_point";
    public final static String ORDER_INDEX = "order_index";
    public final static String DIMENSION = "dimension";
    public final static String TIME_POINT = "time_point";
    public final static String DISCRETE_POINT = "discrete_point";
    public final static String CATEGORY = "category";
    public final static String VALUE = "value";
    public final static String METADATA = "metadata";
    public final static String METADATA_GROUP = "metadata_group";
    public final static String DOWNLOAD_FILE_PATH = "download_file_path";
    public final static String FILE_TYPE = "file_type";
    public final static String PARAMETER_ASSOCIATION_STABLE_ID = "parameter_association_stable_id";
    public final static String PARAMETER_ASSOCIATION_SEQUENCE_ID = "parameter_association_sequence_id";
    public final static String PARAMETER_ASSOCIATION_DIM_ID = "parameter_association_dim_id";
    public final static String PARAMETER_ASSOCIATION_NAME = "parameter_association_name";
    public final static String PARAMETER_ASSOCIATION_VALUE = "parameter_association_value";
    public final static String WEIGHT_PARAMETER_STABLE_ID = "weight_parameter_stable_id";
    public final static String WEIGHT_DAYS_OLD = "weight_days_old";
    public final static String WEIGHT = "weight";
    public static final String DEVELOPMENTAL_STAGE_ACCESSION = "developmental_stage_acc";
    public static final String DEVELOPMENTAL_STAGE_NAME = "developmental_stage_name";

    public final static String DATE_OF_BIRTH = "date_of_birth";
    public final static String DATE_OF_EXPERIMENT = "date_of_experiment";
    public final static String WEIGHT_DATE = "weight_date";
    public static final String TEXT_VALUE = "text_value";
    public static final String SUB_TERM_NAME = "sub_term_name";
    public static final String SUB_TERM_ID = "sub_term_id";
    public static final String SUB_TERM_DESCRIPTION = "sub_term_description";
    public static final String SEQUENCE_ID = "sequence_id";

    public static final String ANATOMY_ID = "anatomy_id";
    public static final String ANATOMY_TERM = "anatomy_term";
    public static final String ANATOMY_TERM_SYNONYM = "anatomy_term_synonym";
    public static final String ANATOMY_ID_TERM = "anatomy_id_term";
    public static final String TOP_LEVEL_ANATOMY_ID = "top_level_anatomy_id";
    public static final String TOP_LEVEL_ANATOMY_TERM = "top_level_anatomy_term";
    public static final String TOP_LEVEL_ANATOMY_TERM_SYNONYM = "top_level_anatomy_term_synonym";
    public static final String SELECTED_TOP_LEVEL_ANATOMY_ID = "selected_top_level_anatomy_id";
    public static final String SELECTED_TOP_LEVEL_ANATOMY_TERM = "selected_top_level_anatomy_term";
    public static final String SELECTED_TOP_LEVEL_ANATOMY_TERM_SYNONYM = "selected_top_level_anatomy_term_synonym";
    public static final String INTERMEDIATE_ANATOMY_ID = "intermediate_anatomy_id";
    public static final String INTERMEDIATE_ANATOMY_TERM = "intermediate_anatomy_term";
    public static final String INTERMEDIATE_ANATOMY_TERM_SYNONYM = "intermediate_anatomy_term_synonym";


    public static final String FULL_RESOLUTION_FILE_PATH = "full_resolution_file_path";

    public static final String OMERO_ID = "omero_id";
    public static final String DOWNLOAD_URL = "download_url";
    public static final String JPEG_URL = "jpeg_url";
    public static final String IMAGE_LINK = "image_link";

    public static final String EFO_ID = "efo_id";
    public static final String UBERON_ID = "uberon_id";

    public static final String SYMBOL_GENE = "symbol_gene";
    public static final String SYMBOL = "symbol";
    public static final String SUBTYPE = "subtype";

    // Genes annotated to this MA through images
    public static final String MGI_ACCESSION_ID = "mgi_accession_id";
    public static final String MARKER_SYMBOL = "marker_symbol";
    public static final String MARKER_NAME = "marker_name";
    public static final String MARKER_SYNONYM = "marker_synonym";
    public static final String MARKER_TYPE = "marker_type";
    public static final String HUMAN_GENE_SYMBOL = "human_gene_symbol";

    // Latest project status (ES cells/mice production status)
    public static final String STATUS = "status";

    // Latest mice phenotyping status for faceting
    public static final String IMITS_PHENOTYPE_STARTED = "imits_phenotype_started";
    public static final String IMITS_PHENOTYPE_COMPLETE = "imits_phenotype_complete";
    public static final String IMITS_PHENOTYPE_STATUS = "imits_phenotype_status";

    // Production/phenotyping centers
    public static final String LATEST_PRODUCTION_CENTRE = "latest_production_centre";
    public static final String LATEST_PHENOTYPING_CENTRE = "latest_phenotyping_centre";

    // Alleles of a gene
    public static final String ALLELE_NAME = "allele_name";
    public static final String INTERMEDIATE_MP_ID = "intermediate_mp_id";
    public static final String INTERMEDIATE_MP_TERM = "intermediate_mp_term";
    public static final String INTERMEDIATE_MP_TERM_SYN = "intermediate_mp_term_synonym";
    public static final String LEGACY_PHENOTYPE_STATUS = "legacy_phenotype_status";
    public static final String LATEST_PHENOTYPE_STATUS = "latest_phenotype_status";


    public static final String INCREMENT_VALUE = "increment_value";
    public static final String STAGE = "stage";

    public static final String MP_ID_TERM = "mp_id_term";
    public static final String MP_ID = "mp_id";
    public static final String MP_TERM = "mp_term";

    @Field(MP_ID)
    private List<String> mpTermIds;

    @Field(MP_TERM)
    private List<String> mpTerm;

    @Field(MP_ID_TERM)
    private List<String> mpIdTerm;

    @Field(FULL_RESOLUTION_FILE_PATH)
    private String fullResolutionFilePath;

    @Field(OMERO_ID)
    private int omeroId;

    @Field(DOWNLOAD_URL)
    private String downloadUrl;

    @Field(IMAGE_LINK)
    private String imageLink;

    @Field(JPEG_URL)
    private String jpegUrl;

    @Field(EFO_ID)
    private List<String> efoId;

    @Field(UBERON_ID)
    private List<String> uberonId;

    @Field(SYMBOL_GENE)
    private String symbolGene;//for search and annotation view

    @Field(STATUS)
    private List<String> status;

    @Field(IMITS_PHENOTYPE_STARTED)
    private List<String> imitsPhenotypeStarted;

    @Field(IMITS_PHENOTYPE_COMPLETE)
    private List<String> imitsPhenotypeComplete;

    @Field(IMITS_PHENOTYPE_STATUS)
    private List<String> imitsPhenotypeStatus;

    @Field(LEGACY_PHENOTYPE_STATUS)
    private Integer legacyPhenotypeStatus;

    @Field(LATEST_PRODUCTION_CENTRE)
    private List<String> latestProductionCentre;

    @Field(LATEST_PHENOTYPING_CENTRE)
    private List<String> latestPhenotypingCentre;

    @Field(ALLELE_NAME)
    private List<String> alleleName;

    @Field(MARKER_SYMBOL)
    private List<String> markerSymbol;

    @Field(MARKER_NAME)
    private List<String> markerName;

    @Field(MARKER_SYNONYM)
    private List<String> markerSynonym;

    @Field(MARKER_TYPE)
    private String markerType;

    @Field(HUMAN_GENE_SYMBOL)
    private List<String> humanGeneSymbol;

    @Field(SYMBOL)
    private String symbol;

    @Field(SUBTYPE)
    private String subtype;

    @Field(INCREMENT_VALUE)
    private Integer increment;

    @Field(STAGE)
    private String stage;

    @Field(LATEST_PHENOTYPE_STATUS)
    private List<String> latestPhenotypeStatus;

    @Field(ID)
    protected Integer id;

    @Field(DATASOURCE_ID)
    protected Integer dataSourceId;

    @Field(DATASOURCE_NAME)
    protected String dataSourceName;

    @Field(PROJECT_ID)
    protected Integer projectId;

    @Field(PROJECT_NAME)
    protected String projectName;

    @Field(PIPELINE_NAME)
    protected String pipelineName;

    @Field(PIPELINE_STABLE_ID)
    protected String pipelineStableId;

    @Field(PROCEDURE_STABLE_ID)
    protected String procedureStableId;

    @Field(PROCEDURE_GROUP)
    protected String procedureGroup;

    @Field(PARAMETER_STABLE_ID)
    protected String parameterStableId;

    @Field(PIPELINE_ID)
    protected Integer pipelineId;

    @Field(PROCEDURE_ID)
    protected Integer procedureId;

    @Field(PARAMETER_ID)
    protected Integer parameterId;

    @Field(STRAIN_ACCESSION_ID)
    protected String strainAccessionId;

    @Field(STRAIN_NAME)
    protected String strainName;

    @Field(GENETIC_BACKGROUND)
    protected String geneticBackground;

    @Field(ALLELIC_COMPOSITION)
    protected String allelicComposition;

    @Field(EXPERIMENT_SOURCE_ID)
    protected String experimentSourceId;

    @Field(GENE_SYMBOL)
    protected String geneSymbol;

    @Field(GENE_ACCESSION_ID)
    protected String geneAccession;

    @Field(EXPERIMENT_ID)
    protected Integer experimentId;

    @Field(PHENOTYPING_CENTER_ID)
    protected Integer phenotypingCenterId;

    @Field(PHENOTYPING_CENTER)
    protected String phenotypingCenter;

    @Field(PRODUCTION_CENTER_ID)
    protected Integer productionCenterId;

    @Field(PRODUCTION_CENTER)
    protected String productionCenter;

    @Field(LITTER_ID)
    protected String litterId;

    @Field(OBSERVATION_TYPE)
    protected String observationType;

    @Field(COLONY_ID)
    protected String colonyId;

    @Field(BIOLOGICAL_SAMPLE_ID)
    protected Integer biologicalSampleId;

    @Field(BIOLOGICAL_MODEL_ID)
    protected Integer biologicalModelId;

    @Field(ZYGOSITY)
    protected String zygosity;

    @Field(SEX)
    protected String sex;

    @Field(BIOLOGICAL_SAMPLE_GROUP)
    protected String group;

    @Field(CATEGORY)
    protected String category;

    @Field(DATA_POINT)
    protected Float dataPoint;

    @Field(ORDER_INDEX)
    protected Integer orderIndex;

    @Field(DIMENSION)
    protected String dimension;

    @Field(TIME_POINT)
    protected String timePoint;

    @Field(DISCRETE_POINT)
    protected Float discretePoint;

    @Field(EXTERNAL_SAMPLE_ID)
    protected String externalSampleId;

    @Field(PARAMETER_NAME)
    protected String parameterName;

    @Field(PROCEDURE_NAME)
    protected String procedureName;

    @Field(METADATA_GROUP)
    protected String metadataGroup;

    @Field(METADATA)
    protected List<String> metadata;

    @Field(ALLELE_ACCESSION_ID)
    protected String alleleAccession;

    @Field(ALLELE_SYMBOL)
    protected String alleleSymbol;

    @Field(DOWNLOAD_FILE_PATH)
    protected String downloadFilePath;

    @Field(FILE_TYPE)
    protected String fileType;

    @Field(PARAMETER_ASSOCIATION_STABLE_ID)
    protected List<String> parameterAssociationStableId;

    @Field(PARAMETER_ASSOCIATION_SEQUENCE_ID)
    protected List<String> parameterAssociationSequenceId;

    @Field(PARAMETER_ASSOCIATION_DIM_ID)
    protected List<String> parameterAssociationDimId;

    @Field(PARAMETER_ASSOCIATION_NAME)
    protected List<String> parameterAssociationName;

    @Field(PARAMETER_ASSOCIATION_VALUE)
    protected List<String> parameterAssociationValue;

    @Field(WEIGHT_PARAMETER_STABLE_ID)
    protected String weightParameterStableId;

    @Field(WEIGHT_DAYS_OLD)
    protected Integer weightDaysOld;

    @Field(WEIGHT)
    protected Float weight;

    @Field(DEVELOPMENTAL_STAGE_ACCESSION)
    protected String developmentalStageAcc;

    @Field(DEVELOPMENTAL_STAGE_NAME)
    protected String developmentalStageName;

    @Field(TEXT_VALUE)
    private String textValue;

    @Field(SUB_TERM_NAME)
    private List<String> subTermName;

    @Field(SUB_TERM_ID)
    private List<String> subTermId;

    @Field(SUB_TERM_DESCRIPTION)
    private List<String> subTermDescription;

    @Field(SEQUENCE_ID)
    private Integer sequenceId;

    @Field(ANATOMY_ID)
    List<String> anatomyId;

    @Field(ANATOMY_TERM)
    List<String> anatomyTerm;

    @Field(ANATOMY_ID_TERM)
    List<String> anatomyIdTerm;

    @Field(ANATOMY_TERM_SYNONYM)
    List<String> anatomyTermSynonym;

    @Field(INTERMEDIATE_ANATOMY_ID)
    List<String> intermediateAnatomyId;

    @Field(INTERMEDIATE_ANATOMY_TERM)
    List<String> intermediateAnatomyTerm;

    @Field(INTERMEDIATE_ANATOMY_TERM_SYNONYM)
    List<String> intermediateAnatomyTermSynonym;

    @Field(TOP_LEVEL_ANATOMY_ID)
    List<String> topLevelAnatomyId;

    @Field(TOP_LEVEL_ANATOMY_TERM)
    List<String> topLevelAnatomyTerm;

    @Field(TOP_LEVEL_ANATOMY_TERM_SYNONYM)
    List<String> topLevelAnatomyTermSynonym;

    @Field(SELECTED_TOP_LEVEL_ANATOMY_ID)
    List<String> selectedTopLevelAnatomyId;

    @Field(SELECTED_TOP_LEVEL_ANATOMY_TERM)
    List<String> selectedTopLevelAnatomyTerm;

    @Field(SELECTED_TOP_LEVEL_ANATOMY_TERM_SYNONYM)
    List<String> selectedTopLevelAnatomyTermSynonym;


    public List<String> getAnatomyId() {
        return anatomyId;
    }

    public void setAnatomyId(List<String> anatomyId) {
        this.anatomyId = anatomyId;
    }

    public List<String> getAnatomyTerm() {
        return anatomyTerm;
    }

    public void setAnatomyTerm(List<String> anatomyTerm) {
        this.anatomyTerm = anatomyTerm;
    }

    public List<String> getAnatomyIdTerm() {
        return anatomyIdTerm;
    }

    public void setAnatomyIdTerm(List<String> anatomyIdTerm) {
        this.anatomyIdTerm = anatomyIdTerm;
    }

    public List<String> getAnatomyTermSynonym() {
        return anatomyTermSynonym;
    }

    public void setAnatomyTermSynonym(List<String> anatomyTermSynonym) {
        this.anatomyTermSynonym = anatomyTermSynonym;
    }

    public List<String> getIntermediateAnatomyId() {
        return intermediateAnatomyId;
    }

    public void setIntermediateAnatomyId(List<String> intermediateAnatomyId) {
        this.intermediateAnatomyId = intermediateAnatomyId;
    }

    public List<String> getIntermediateAnatomyTerm() {
        return intermediateAnatomyTerm;
    }

    public void setIntermediateAnatomyTerm(List<String> intermediateAnatomyTerm) {
        this.intermediateAnatomyTerm = intermediateAnatomyTerm;
    }

    public List<String> getIntermediateAnatomyTermSynonym() {
        return intermediateAnatomyTermSynonym;
    }

    public void setIntermediateAnatomyTermSynonym(List<String> intermediateAnatomyTermSynonym) {
        this.intermediateAnatomyTermSynonym = intermediateAnatomyTermSynonym;
    }

    public List<String> getTopLevelAnatomyId() {
        return topLevelAnatomyId;
    }

    public void setTopLevelAnatomyId(List<String> topLevelAnatomyId) {
        this.topLevelAnatomyId = topLevelAnatomyId;
    }

    public List<String> getTopLevelAnatomyTerm() {
        return topLevelAnatomyTerm;
    }

    public void setTopLevelAnatomyTerm(List<String> topLevelAnatomyTerm) {
        this.topLevelAnatomyTerm = topLevelAnatomyTerm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Integer dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public String getPipelineStableId() {
        return pipelineStableId;
    }

    public void setPipelineStableId(String pipelineStableId) {
        this.pipelineStableId = pipelineStableId;
    }

    public String getProcedureStableId() {
        return procedureStableId;
    }

    public void setProcedureStableId(String procedureStableId) {
        this.procedureStableId = procedureStableId;
    }

    public String getProcedureGroup() {
        return procedureGroup;
    }

    public void setProcedureGroup(String procedureGroup) {
        this.procedureGroup = procedureGroup;
    }

    public String getParameterStableId() {
        return parameterStableId;
    }

    public void setParameterStableId(String parameterStableId) {
        this.parameterStableId = parameterStableId;
    }

    public Integer getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Integer pipelineId) {
        this.pipelineId = pipelineId;
    }

    public Integer getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Integer procedureId) {
        this.procedureId = procedureId;
    }

    public Integer getParameterId() {
        return parameterId;
    }

    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    public String getStrainAccessionId() {
        return strainAccessionId;
    }

    public void setStrainAccessionId(String strainAccessionId) {
        this.strainAccessionId = strainAccessionId;
    }

    public String getStrainName() {
        return strainName;
    }

    public void setStrainName(String strainName) {
        this.strainName = strainName;
    }

    public String getGeneticBackground() {
        return geneticBackground;
    }

    public void setGeneticBackground(String geneticBackground) {
        this.geneticBackground = geneticBackground;
    }

    public String getAllelicComposition() {
        return allelicComposition;
    }

    public void setAllelicComposition(String allelicComposition) {
        this.allelicComposition = allelicComposition;
    }

    public String getExperimentSourceId() {
        return experimentSourceId;
    }

    public void setExperimentSourceId(String experimentSourceId) {
        this.experimentSourceId = experimentSourceId;
    }

    public String getGeneSymbol() {
        return geneSymbol;
    }

    public void setGeneSymbol(String geneSymbol) {
        this.geneSymbol = geneSymbol;
    }

    public String getGeneAccession() {
        return geneAccession;
    }

    public void setGeneAccession(String geneAccession) {
        this.geneAccession = geneAccession;
    }

    public Integer getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Integer experimentId) {
        this.experimentId = experimentId;
    }

    public Integer getPhenotypingCenterId() {
        return phenotypingCenterId;
    }

    public void setPhenotypingCenterId(Integer phenotypingCenterId) {
        this.phenotypingCenterId = phenotypingCenterId;
    }

    public String getPhenotypingCenter() {
        return phenotypingCenter;
    }

    public void setPhenotypingCenter(String phenotypingCenter) {
        this.phenotypingCenter = phenotypingCenter;
    }

    public Integer getProductionCenterId() {
        return productionCenterId;
    }

    public void setProductionCenterId(Integer productionCenterId) {
        this.productionCenterId = productionCenterId;
    }

    public String getProductionCenter() {
        return productionCenter;
    }

    public void setProductionCenter(String productionCenter) {
        this.productionCenter = productionCenter;
    }

    public String getLitterId() {
        return litterId;
    }

    public void setLitterId(String litterId) {
        this.litterId = litterId;
    }

    public String getObservationType() {
        return observationType;
    }

    public void setObservationType(String observationType) {
        this.observationType = observationType;
    }

    public String getColonyId() {
        return colonyId;
    }

    public void setColonyId(String colonyId) {
        this.colonyId = colonyId;
    }

    public Integer getBiologicalSampleId() {
        return biologicalSampleId;
    }

    public void setBiologicalSampleId(Integer biologicalSampleId) {
        this.biologicalSampleId = biologicalSampleId;
    }

    public Integer getBiologicalModelId() {
        return biologicalModelId;
    }

    public void setBiologicalModelId(Integer biologicalModelId) {
        this.biologicalModelId = biologicalModelId;
    }

    public String getZygosity() {
        return zygosity;
    }

    public void setZygosity(String zygosity) {
        this.zygosity = zygosity;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getDataPoint() {
        return dataPoint;
    }

    public void setDataPoint(Float dataPoint) {
        this.dataPoint = dataPoint;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

    public Float getDiscretePoint() {
        return discretePoint;
    }

    public void setDiscretePoint(Float discretePoint) {
        this.discretePoint = discretePoint;
    }

    public String getExternalSampleId() {
        return externalSampleId;
    }

    public void setExternalSampleId(String externalSampleId) {
        this.externalSampleId = externalSampleId;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getMetadataGroup() {
        return metadataGroup;
    }

    public void setMetadataGroup(String metadataGroup) {
        this.metadataGroup = metadataGroup;
    }

    public List<String> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<String> metadata) {
        this.metadata = metadata;
    }

    public String getAlleleAccession() {
        return alleleAccession;
    }

    public void setAlleleAccession(String alleleAccession) {
        this.alleleAccession = alleleAccession;
    }

    public String getAlleleSymbol() {
        return alleleSymbol;
    }

    public void setAlleleSymbol(String alleleSymbol) {
        this.alleleSymbol = alleleSymbol;
    }

    public String getDownloadFilePath() {
        return downloadFilePath;
    }

    public void setDownloadFilePath(String downloadFilePath) {
        this.downloadFilePath = downloadFilePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public List<String> getParameterAssociationStableId() {
        return parameterAssociationStableId;
    }

    public void setParameterAssociationStableId(List<String> parameterAssociationStableId) {
        this.parameterAssociationStableId = parameterAssociationStableId;
    }

    public List<String> getParameterAssociationSequenceId() {
        return parameterAssociationSequenceId;
    }

    public void setParameterAssociationSequenceId(List<String> parameterAssociationSequenceId) {
        this.parameterAssociationSequenceId = parameterAssociationSequenceId;
    }

    public List<String> getParameterAssociationDimId() {
        return parameterAssociationDimId;
    }

    public void setParameterAssociationDimId(List<String> parameterAssociationDimId) {
        this.parameterAssociationDimId = parameterAssociationDimId;
    }

    public String getWeightParameterStableId() {
        return weightParameterStableId;
    }

    public void setWeightParameterStableId(String weightParameterStableId) {
        this.weightParameterStableId = weightParameterStableId;
    }

    public Integer getWeightDaysOld() {
        return weightDaysOld;
    }

    public void setWeightDaysOld(Integer weightDaysOld) {
        this.weightDaysOld = weightDaysOld;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public List<String> getTopLevelAnatomyTermSynonym() {
        return topLevelAnatomyTermSynonym;
    }

    public void setTopLevelAnatomyTermSynonym(List<String> topLevelAnatomyTermSynonym) {
        this.topLevelAnatomyTermSynonym = topLevelAnatomyTermSynonym;
    }

    public List<String> getSelectedTopLevelAnatomyId() {
        return selectedTopLevelAnatomyId;
    }

    public void setSelectedTopLevelAnatomyId(List<String> selectedTopLevelAnatomyId) {
        this.selectedTopLevelAnatomyId = selectedTopLevelAnatomyId;
    }

    public List<String> getSelectedTopLevelAnatomyTerm() {
        return selectedTopLevelAnatomyTerm;
    }

    public void setSelectedTopLevelAnatomyTerm(List<String> selectedTopLevelAnatomyTerm) {
        this.selectedTopLevelAnatomyTerm = selectedTopLevelAnatomyTerm;
    }

    public List<String> getSelectedTopLevelAnatomyTermSynonym() {
        return selectedTopLevelAnatomyTermSynonym;
    }

    public void setSelectedTopLevelAnatomyTermSynonym(List<String> selectedTopLevelAnatomyTermSynonym) {
        this.selectedTopLevelAnatomyTermSynonym = selectedTopLevelAnatomyTermSynonym;
    }

    public Integer getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public List<String> getSubTermName() {
        return subTermName;
    }

    public void setSubTermName(List<String> subTermName) {
        this.subTermName = subTermName;
    }

    public List<String> getSubTermId() {
        return subTermId;
    }

    public void addSubTermId(String id){
        if(this.subTermId==null){
            this.subTermId=new ArrayList<String>();
        }
        this.subTermId.add(id);
    }

    public void addSubTermName(String name){
        if(this.subTermName==null){
            this.subTermName=new ArrayList<String>();
        }
        this.subTermName.add(name);
    }

    public void addSubTermDescription(String description){
        if(this.subTermDescription==null){
            this.subTermDescription=new ArrayList<String>();
        }
        this.subTermDescription.add(description);
    }

    public void setSubTermId(List<String> subTermId) {
        this.subTermId = subTermId;
    }

    public List<String> getSubTermDescription() {
        return subTermDescription;
    }

    public void setSubTermDescription(List<String> subTermDescription) {
        this.subTermDescription = subTermDescription;
    }

    public void setTextValue(String textValue) {
        this.textValue=textValue;

    }

    public String getTextValue() {
        return textValue;
    }

    public List<String> getParameterAssociationName() {

        return parameterAssociationName;
    }


    public void setParameterAssociationName(List<String> parameterAssociationName) {

        this.parameterAssociationName = parameterAssociationName;
    }

    public void addParameterAssociationStableId(String id) {
        if(parameterAssociationStableId == null) {
            parameterAssociationStableId = new ArrayList<>();
        }
        parameterAssociationStableId.add(id);
    }

    public void addParameterAssociationName(String paramAssociationName) {

        if(parameterAssociationName == null) {
            parameterAssociationName = new ArrayList<String>();
        }
        parameterAssociationName.add(paramAssociationName);

    }

    public void addParameterAssociationSequenceId(String id) {
        if(parameterAssociationSequenceId == null) {
            parameterAssociationSequenceId = new ArrayList<>();
        }
        parameterAssociationSequenceId.add(id);
    }

    public void addParameterAssociationDimId(String id) {
        if(parameterAssociationDimId == null) {
            parameterAssociationDimId = new ArrayList<>();
        }
        parameterAssociationDimId.add(id);
    }


    public List<String> getMpIdTerm() {
        return mpIdTerm;
    }

    public void setMpIdTerm(List<String> mpIdTerm) {
        this.mpIdTerm = mpIdTerm;
    }

    public List<String> getMpTermIds() {
        return mpTermIds;
    }

    public void setMpTermIds(ArrayList<String> mpTermIds) {
        this.mpTermIds = mpTermIds;
    }

    public List<String> getMpTerm() {
        return mpTerm;
    }

    public void setMpTerm(List<String> mpTerm) {
        this.mpTerm = mpTerm;
    }

    public void setMpTermIds(List<String> mpTermIds) {
        this.mpTermIds = mpTermIds;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public List<String> getImitsPhenotypeStarted() {
        return imitsPhenotypeStarted;
    }

    public void setImitsPhenotypeStarted(List<String> imitsPhenotypeStarted) {
        this.imitsPhenotypeStarted = imitsPhenotypeStarted;
    }

    public String getSymbolGene() {
        return symbolGene;
    }

    public String getDevelopmentalStageAcc() {
        return developmentalStageAcc;
    }

    public void setDevelopmentalStageAcc(String developmentalStageAcc) {
        this.developmentalStageAcc = developmentalStageAcc;
    }

    public String getDevelopmentalStageName() {
        return developmentalStageName;
    }

    public void setDevelopmentalStageName(String developmentalStageName) {
        this.developmentalStageName = developmentalStageName;
    }

    public void setParameterAssociationValue(List<String> parameterAssociationValue) {
        this.parameterAssociationValue = parameterAssociationValue;
    }

    public List<String> getImitsPhenotypeComplete() {
        return imitsPhenotypeComplete;
    }

    public void setImitsPhenotypeComplete(List<String> imitsPhenotypeComplete) {
        this.imitsPhenotypeComplete = imitsPhenotypeComplete;
    }

    public List<String> getImitsPhenotypeStatus() {
        return imitsPhenotypeStatus;
    }

    public void setImitsPhenotypeStatus(List<String> imitsPhenotypeStatus) {
        this.imitsPhenotypeStatus = imitsPhenotypeStatus;
    }

    public List<String> getLatestProductionCentre() {
        return latestProductionCentre;
    }

    public List<String> getLatestPhenotypingCentre() {
        return latestPhenotypingCentre;
    }

    public List<String> getAlleleName() {
        return alleleName;
    }

    public List<String> getMarkerSynonym() {
        return markerSynonym;
    }

    public void setMarkerSynonym(List<String> markerSynonym) {
        this.markerSynonym = markerSynonym;
    }

    public String getMarkerType() {
        return markerType;
    }

    public void setMarkerType(String markerType) {
        this.markerType = markerType;
    }

    public List<String> getHumanGeneSymbol() {
        return humanGeneSymbol;
    }

    public void setHumanGeneSymbol(List<String> humanGeneSymbol) {
        this.humanGeneSymbol = humanGeneSymbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setIncrement(Integer increment) {
        this.increment = increment;
    }

    public List<String> getLatestPhenotypeStatus() {
        return latestPhenotypeStatus;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getSubtype() {

        return subtype;
    }

    public List<String> getMarkerName() {

        return markerName;
    }

    public void setMarkerName(List<String> markerName) {

        this.markerName = markerName;
    }

    public List<String> getMarkerSymbol() {

        return markerSymbol;
    }

    public void setMarkerSymbol(List<String> markerSymbol) {

        this.markerSymbol = markerSymbol;
    }

    public void setSubtype(String subtype) {

        this.subtype = subtype;
    }


    public List<String> getEfoId() {

        return efoId;
    }

    public void setEfoId(List<String> efoId) {

        this.efoId = efoId;
    }

    public void addEfoId(String id) {
        if (this.efoId == null) {
            this.efoId = new ArrayList<>();
        }
        this.efoId.add(id);
    }

    public List<String> getUberonId() {

        return uberonId;
    }

    public void setUberonId(List<String> uberonId) {

        this.uberonId = uberonId;
    }

    public void addUberonId(String id) {
        if (this.uberonId == null) {
            this.uberonId = new ArrayList<>();
        }
        this.uberonId.add(id);
    }

    public String getDownloadUrl() {

        return downloadUrl;
    }

    public int getOmeroId() {

        return omeroId;
    }

    public void setOmeroId(int omeroId) {

        this.omeroId = omeroId;
    }

    public String getFullResolutionFilePath() {

        return fullResolutionFilePath;
    }

    public void setFullResolutionFilePath(String fullResolutionFilePath) {

        this.fullResolutionFilePath = fullResolutionFilePath;
    }

    public void setDownloadUrl(String downloadUrl) {

        this.downloadUrl = downloadUrl;
    }

    public void setJpegUrl(String jpegUrl) {

        this.jpegUrl = jpegUrl;
    }

    public String getJpegUrl() {

        return jpegUrl;
    }

    public void addStatus(String status1) {

        if (this.status == null) {
            status = new ArrayList<String>();
        }
        status.add(status1);
    }

    public void addImitsPhenotypeStarted(String imitsPhenotypeStarted1) {

        if (this.imitsPhenotypeStarted == null) {
            this.imitsPhenotypeStarted = new ArrayList<String>();
        }
        this.imitsPhenotypeStarted.add(imitsPhenotypeStarted1);
    }

    public void addImitsPhenotypeComplete(String imitsPhenotypeComplete1) {

        if (this.imitsPhenotypeComplete == null) {
            this.imitsPhenotypeComplete = new ArrayList<String>();
        }
        this.imitsPhenotypeComplete.add(imitsPhenotypeComplete1);
    }

    public void addImitsPhenotypeStatus(String imitsPhenotypeStatus1) {

        if (this.imitsPhenotypeStatus == null) {
            this.imitsPhenotypeStatus = new ArrayList<String>();
        }
        this.imitsPhenotypeStatus.add(imitsPhenotypeStatus1);
    }

    public void setLegacyPhenotypeStatus(Integer legacyPhenotypeStatus) {

        this.legacyPhenotypeStatus = legacyPhenotypeStatus;
    }

    public Integer getLegacyPhenotypeStatus() {

        return legacyPhenotypeStatus;
    }

    public void setLatestProductionCentre(List<String> latestProductionCentre) {

        this.latestProductionCentre = latestProductionCentre;
    }

    public void setLatestPhenotypingCentre(List<String> latestPhenotypingCentre) {

        this.latestPhenotypingCentre = latestPhenotypingCentre;
    }

    public void setAlleleName(List<String> alleleName) {

        this.alleleName = alleleName;
    }

    public void addMarkerName(String markerName) {
        if (this.markerName == null) {
            this.markerName = new ArrayList<>();
        }
        this.markerName.add(markerName);
    }

    public void addMarkerSynonym(List<String> markerSynonym) {
        if (this.markerSynonym == null) {
            this.markerSynonym = new ArrayList<>();
        }
        this.markerSynonym.addAll(markerSynonym);
    }

    public void addMarkerType(String markerType) {

        this.markerType = markerType;
    }

    public void addHumanGeneSymbol(List<String> humanGeneSymbol) {

        if (this.humanGeneSymbol == null) {
            this.humanGeneSymbol = new ArrayList<String>();
        }
        this.humanGeneSymbol.addAll(humanGeneSymbol);
    }

    public void addSymbol(String markerName) {

        this.symbol = markerName;
    }

    public void setLatestPhenotypeStatus(List<String> latestPhenotypeStatus) {

        this.latestPhenotypeStatus = latestPhenotypeStatus;
    }

    public void addLatestPhenotypeStatus(String latestPhenotypeStatus) {
        if (this.latestPhenotypeStatus == null) {
            this.latestPhenotypeStatus = new ArrayList<String>();
        }
        this.latestPhenotypeStatus.add(latestPhenotypeStatus);
    }

    public String getSymbol() {
        // TODO Auto-generated method stub
        return symbol;
    }

    public void setSymbolGene(String symbolGene) {
        this.symbolGene = symbolGene;

    }

    public String getExpression(String maId) {

        int pos = maId.indexOf(maId);
        return getParameterAssociationValue().get(pos);

    }


    public List<String> getParameterAssociationValue() {
        return parameterAssociationValue;
    }



    @Override
    public String toString() {
        return "ImpcImageDto{" +
                "mpTermIds=" + mpTermIds +
                ", mpTerm=" + mpTerm +
                ", mpIdTerm=" + mpIdTerm +
                ", fullResolutionFilePath='" + fullResolutionFilePath + '\'' +
                ", omeroId=" + omeroId +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", jpegUrl='" + jpegUrl + '\'' +
                ", efoId=" + efoId +
                ", uberonId=" + uberonId +
                ", symbolGene='" + symbolGene + '\'' +
                ", status=" + status +
                ", imitsPhenotypeStarted=" + imitsPhenotypeStarted +
                ", imitsPhenotypeComplete=" + imitsPhenotypeComplete +
                ", imitsPhenotypeStatus=" + imitsPhenotypeStatus +
                ", legacyPhenotypeStatus=" + legacyPhenotypeStatus +
                ", latestProductionCentre=" + latestProductionCentre +
                ", latestPhenotypingCentre=" + latestPhenotypingCentre +
                ", alleleName=" + alleleName +
                ", markerSymbol=" + markerSymbol +
                ", markerName=" + markerName +
                ", markerSynonym=" + markerSynonym +
                ", markerType='" + markerType + '\'' +
                ", humanGeneSymbol=" + humanGeneSymbol +
                ", symbol='" + symbol + '\'' +
                ", subtype='" + subtype + '\'' +
                ", increment=" + increment +
                ", stage='" + stage + '\'' +
                ", latestPhenotypeStatus=" + latestPhenotypeStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImpcImageDto that = (ImpcImageDto) o;

        if (omeroId != that.omeroId) return false;
        if (mpTermIds != null ? !mpTermIds.equals(that.mpTermIds) : that.mpTermIds != null) return false;
        if (mpTerm != null ? !mpTerm.equals(that.mpTerm) : that.mpTerm != null) return false;
        if (mpIdTerm != null ? !mpIdTerm.equals(that.mpIdTerm) : that.mpIdTerm != null) return false;
        if (fullResolutionFilePath != null ? !fullResolutionFilePath.equals(that.fullResolutionFilePath) : that.fullResolutionFilePath != null)
            return false;
        if (downloadUrl != null ? !downloadUrl.equals(that.downloadUrl) : that.downloadUrl != null) return false;
        if (imageLink != null ? !imageLink.equals(that.imageLink) : that.imageLink != null) return false;
        if (jpegUrl != null ? !jpegUrl.equals(that.jpegUrl) : that.jpegUrl != null) return false;
        if (efoId != null ? !efoId.equals(that.efoId) : that.efoId != null) return false;
        if (uberonId != null ? !uberonId.equals(that.uberonId) : that.uberonId != null) return false;
        if (symbolGene != null ? !symbolGene.equals(that.symbolGene) : that.symbolGene != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (imitsPhenotypeStarted != null ? !imitsPhenotypeStarted.equals(that.imitsPhenotypeStarted) : that.imitsPhenotypeStarted != null)
            return false;
        if (imitsPhenotypeComplete != null ? !imitsPhenotypeComplete.equals(that.imitsPhenotypeComplete) : that.imitsPhenotypeComplete != null)
            return false;
        if (imitsPhenotypeStatus != null ? !imitsPhenotypeStatus.equals(that.imitsPhenotypeStatus) : that.imitsPhenotypeStatus != null)
            return false;
        if (legacyPhenotypeStatus != null ? !legacyPhenotypeStatus.equals(that.legacyPhenotypeStatus) : that.legacyPhenotypeStatus != null)
            return false;
        if (latestProductionCentre != null ? !latestProductionCentre.equals(that.latestProductionCentre) : that.latestProductionCentre != null)
            return false;
        if (latestPhenotypingCentre != null ? !latestPhenotypingCentre.equals(that.latestPhenotypingCentre) : that.latestPhenotypingCentre != null)
            return false;
        if (alleleName != null ? !alleleName.equals(that.alleleName) : that.alleleName != null) return false;
        if (markerSymbol != null ? !markerSymbol.equals(that.markerSymbol) : that.markerSymbol != null) return false;
        if (markerName != null ? !markerName.equals(that.markerName) : that.markerName != null) return false;
        if (markerSynonym != null ? !markerSynonym.equals(that.markerSynonym) : that.markerSynonym != null)
            return false;
        if (markerType != null ? !markerType.equals(that.markerType) : that.markerType != null) return false;
        if (humanGeneSymbol != null ? !humanGeneSymbol.equals(that.humanGeneSymbol) : that.humanGeneSymbol != null)
            return false;
        if (symbol != null ? !symbol.equals(that.symbol) : that.symbol != null) return false;
        if (subtype != null ? !subtype.equals(that.subtype) : that.subtype != null) return false;
        if (increment != null ? !increment.equals(that.increment) : that.increment != null) return false;
        if (stage != null ? !stage.equals(that.stage) : that.stage != null) return false;
        return latestPhenotypeStatus != null ? latestPhenotypeStatus.equals(that.latestPhenotypeStatus) : that.latestPhenotypeStatus == null;

    }

    @Override
    public int hashCode() {
        int result = mpTermIds != null ? mpTermIds.hashCode() : 0;
        result = 31 * result + (mpTerm != null ? mpTerm.hashCode() : 0);
        result = 31 * result + (mpIdTerm != null ? mpIdTerm.hashCode() : 0);
        result = 31 * result + (fullResolutionFilePath != null ? fullResolutionFilePath.hashCode() : 0);
        result = 31 * result + omeroId;
        result = 31 * result + (downloadUrl != null ? downloadUrl.hashCode() : 0);
        result = 31 * result + (imageLink != null ? imageLink.hashCode() : 0);
        result = 31 * result + (jpegUrl != null ? jpegUrl.hashCode() : 0);
        result = 31 * result + (efoId != null ? efoId.hashCode() : 0);
        result = 31 * result + (uberonId != null ? uberonId.hashCode() : 0);
        result = 31 * result + (symbolGene != null ? symbolGene.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (imitsPhenotypeStarted != null ? imitsPhenotypeStarted.hashCode() : 0);
        result = 31 * result + (imitsPhenotypeComplete != null ? imitsPhenotypeComplete.hashCode() : 0);
        result = 31 * result + (imitsPhenotypeStatus != null ? imitsPhenotypeStatus.hashCode() : 0);
        result = 31 * result + (legacyPhenotypeStatus != null ? legacyPhenotypeStatus.hashCode() : 0);
        result = 31 * result + (latestProductionCentre != null ? latestProductionCentre.hashCode() : 0);
        result = 31 * result + (latestPhenotypingCentre != null ? latestPhenotypingCentre.hashCode() : 0);
        result = 31 * result + (alleleName != null ? alleleName.hashCode() : 0);
        result = 31 * result + (markerSymbol != null ? markerSymbol.hashCode() : 0);
        result = 31 * result + (markerName != null ? markerName.hashCode() : 0);
        result = 31 * result + (markerSynonym != null ? markerSynonym.hashCode() : 0);
        result = 31 * result + (markerType != null ? markerType.hashCode() : 0);
        result = 31 * result + (humanGeneSymbol != null ? humanGeneSymbol.hashCode() : 0);
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        result = 31 * result + (subtype != null ? subtype.hashCode() : 0);
        result = 31 * result + (increment != null ? increment.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + (latestPhenotypeStatus != null ? latestPhenotypeStatus.hashCode() : 0);
        return result;
    }

    public void setMpTermId(ArrayList<String> mpTermIds) {
        this.mpTermIds = mpTermIds;

    }


}


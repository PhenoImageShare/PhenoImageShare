package uk.ac.ebi.phis.utils.ensemblclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {

	String source;//: "havana",
	//logic_name: "havana",
	String feature_type; //: "gene",
	String external_name; //: "Epb41l4aos",
	//havana_version: "1",
	String seq_region_name; //: "18",
	//havana_gene: "OTTMUSG00000042342",
	String strand; // : 1,
	String id; // : "ENSMUSG00000087590",
	String gene_id; // : "ENSMUSG00000087590",
	String version; //: 2,
	String assembly_name; //: "GRCm38",
	String description; //: "erythrocyte membrane protein band 4.1 like 4a, opposite strand [Source:MGI Symbol;Acc:MGI:1916999]",
	Long end; //: 33795986,
	String biotype; //: "lincRNA",
	Long start; //: 33794892
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFeature_type() {
		return feature_type;
	}

	public void setFeature_type(String feature_type) {
		this.feature_type = feature_type;
	}

	public String getExternal_name() {
		return external_name;
	}

	public void setExternal_name(String external_name) {
		this.external_name = external_name;
	}

	public String getSeq_region_name() {
		return seq_region_name;
	}

	public void setSeq_region_name(String seq_region_name) {
		this.seq_region_name = seq_region_name;
	}

	public String getStrand() {
		return strand;
	}

	public void setStrand(String strand) {
		this.strand = strand;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGene_id() {
		return gene_id;
	}

	public void setGene_id(String gene_id) {
		this.gene_id = gene_id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAssembly_name() {
		return assembly_name;
	}

	public void setAssembly_name(String assembly_name) {
		this.assembly_name = assembly_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public String getBiotype() {
		return biotype;
	}

	public void setBiotype(String biotype) {
		this.biotype = biotype;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "Feature [source=" + source + ", feature_type=" + feature_type + ", external_name=" + external_name
				+ ", seq_region_name=" + seq_region_name + ", strand=" + strand + ", id=" + id + ", gene_id=" + gene_id
				+ ", version=" + version + ", assembly_name=" + assembly_name + ", description=" + description
				+ ", end=" + end + ", biotype=" + biotype + ", start=" + start + "]";
	}

	
}

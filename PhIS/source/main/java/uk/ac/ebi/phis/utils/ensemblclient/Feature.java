package uk.ac.ebi.phis.utils.ensemblclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {

	String source;//: "havana",
	//logic_name: "havana",
	String featureType; //: "gene",
	String externalName; //: "Epb41l4aos",
	//havana_version: "1",
	String seqRegionName; //: "18",
	//havana_gene: "OTTMUSG00000042342",
	String strand; // : 1,
	String id; // : "ENSMUSG00000087590",
	String geneId; // : "ENSMUSG00000087590",
	String version; //: 2,
	String assemblyName; //: "GRCm38",
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
	public String getFeatureType() {
		return featureType;
	}
	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}
	public String getExternalName() {
		return externalName;
	}
	public void setExternalName(String externalName) {
		this.externalName = externalName;
	}
	public String getSeqRegionName() {
		return seqRegionName;
	}
	public void setSeqRegionName(String seqRegionName) {
		this.seqRegionName = seqRegionName;
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
	public String getGeneId() {
		return geneId;
	}
	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAssemblyName() {
		return assemblyName;
	}
	public void setAssemblyName(String assemblyName) {
		this.assemblyName = assemblyName;
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
		return "Feature [source=" + source + ", featureType=" + featureType + ", externalName=" + externalName
				+ ", seqRegionName=" + seqRegionName + ", strand=" + strand + ", id=" + id + ", geneId=" + geneId
				+ ", version=" + version + ", assemblyName=" + assemblyName + ", description=" + description + ", end="
				+ end + ", biotype=" + biotype + ", start=" + start + "]";
	}
	
}

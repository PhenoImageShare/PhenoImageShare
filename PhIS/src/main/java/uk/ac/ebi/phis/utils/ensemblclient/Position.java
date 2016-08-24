package uk.ac.ebi.phis.utils.ensemblclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Position {
	
	String seqRegionName;
	Integer strand;
	String coordSystem; 
	Long end;
	Long start; 
	String assembly; 
	
	public String getSeqRegionName() {
		return seqRegionName;
	}
	public void setSeq_region_name(String seq_region_name) {
		this.seqRegionName = seq_region_name;
	}
	public Integer getStrand() {
		return strand;
	}
	public void setStrand(Integer strand) {
		this.strand = strand;
	}
	public String getCoord_system() {
		return coordSystem;
	}
	public void setCoord_system(String coord_system) {
		this.coordSystem = coord_system;
	}
	public Long getEnd() {
		return end;
	}
	public void setEnd(Long end) {
		this.end = end;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public String getAssembly() {
		return assembly;
	}
	public void setAssembly(String assembly) {
		this.assembly = assembly;
	}
	
	@Override
	public String toString() {
		return "Position [seq_region_name=" + seqRegionName + ", strand=" + strand + ", coord_system=" + coordSystem
				+ ", end=" + end + ", start=" + start + ", assembly=" + assembly + "]";
	}
	
}

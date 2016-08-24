package uk.ac.ebi.phis.utils.ensemblclient;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MapResult {

	List<Mappings> mappings;

	public List<Mappings> getMappings() {
		return mappings;
	}

	public void setMappings(List<Mappings> mappings) {
		this.mappings = mappings;
	}

	@Override
	public String toString() {
		return "MapResult [mappings=" + mappings + "]";
	}
	
}

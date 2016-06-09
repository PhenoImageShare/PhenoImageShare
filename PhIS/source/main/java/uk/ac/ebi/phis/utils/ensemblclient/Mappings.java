package uk.ac.ebi.phis.utils.ensemblclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Mappings {

	private Position mapped;
	private Position original;
	
	public Position getMapped() {
		return mapped;
	}
	public void setMapped(Position mapped) {
		this.mapped = mapped;
	}
	public Position getOriginal() {
		return original;
	}
	public void setOriginal(Position original) {
		this.original = original;
	}
	@Override
	public String toString() {
		return "Mappings [mapped=" + mapped + ", original=" + original + "]";
	}
	
}

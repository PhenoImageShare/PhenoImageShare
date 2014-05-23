package uk.ac.ebi.phis.utils.ontology;

public class GenomicLocation {

	String chromosomeString;
	double start;
	double end;
	String ensemblId;
	int strand;
	
	public int getStrand() {
		return strand;
	}
	public void setStrand(int strand) {
		this.strand = strand;
	}
	public String getChromosomeString() {
		return chromosomeString;
	}
	public void setChromosomeString(String chromosomeString) {
		this.chromosomeString = chromosomeString;
	}
	public double getStart() {
		return start;
	}
	public void setStart(double start) {
		this.start = start;
	}
	public double getEnd() {
		return end;
	}
	public void setEnd(double end) {
		this.end = end;
	}
	public String getEnsemblId() {
		return ensemblId;
	}
	public void setEnsemblId(String ensemblId) {
		this.ensemblId = ensemblId;
	}
	
	
}

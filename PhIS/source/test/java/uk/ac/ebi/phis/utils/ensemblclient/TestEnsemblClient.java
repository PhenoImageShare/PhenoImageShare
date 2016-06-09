package uk.ac.ebi.phis.utils.ensemblclient;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


/**
 * @since 2016/06/09
 * @author ilinca
 *
 */
public class TestEnsemblClient {

	EnsemblClient ensemblClient = new EnsemblClient();
	
	@Test
	public void testConvertLocation(){
		
		Position newCoordinates = ensemblClient.convertLocation("NCBIM37", "GRCm38", "18", "Mus_musculus", new Long(33607548), new Long(34607548));
		assertTrue (newCoordinates.assembly.equalsIgnoreCase("GRCm38"));
		assertTrue (newCoordinates.getSeqRegionName().equalsIgnoreCase("18"));
		assertTrue (newCoordinates.getEnd() == 34447894);
		assertTrue (newCoordinates.getStart() == 33447894);
		assertTrue (newCoordinates.strand == 1);
		
	}
	
	@Test
	public void testGetGenes(){
		
		List<Feature> genes = ensemblClient.getGeneList("18", "Mus_musculus", new Long(33607548), new Long(34607548));
		assertFalse(genes.isEmpty());
		
	}
			
}

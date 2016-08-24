package uk.ac.ebi.phis.utils.ensemblclient;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


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
		
		List<Feature> genes = ensemblClient.getGeneList("3", "Mus_musculus", new Long(98082994), new Long(98082994));
		assertFalse(genes.isEmpty());
		assertTrue(genes.get(0).getExternal_name().equalsIgnoreCase("Notch2"));
		assertTrue(genes.get(0).getGene_id().equalsIgnoreCase("ENSMUSG00000027878"));
		
	}
			
}
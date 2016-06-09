package uk.ac.ebi.phis.utils.ensemblclient;

import java.util.List;

import org.springframework.web.client.RestTemplate;


/**
 * @since 2016/06/09
 * @author ilinca
 *
 */
public class EnsemblClient {

	
	
	public static final String LATEST_MOUSE_GENOME_ASSEMBLY = "GRCm38";
	public static final String MOUSE_GENOME_ASSEMBLY_37 = "NCBIM37";
	private static final String ENSEMBLBASEURL = "http://rest.ensembl.org/";
	
	/**
	 * @author ilinca
	 * @param currentAssembly
	 * @param newAssembly
	 * @param chromosome
	 * @param species
	 * @param start
	 * @param end
	 * @return Location converted to the required assembly
	 */
	public static Position convertLocation(String currentAssembly, String newAssembly, String chromosome, String species, long start, long end){ //https://rest.ensembl.org/map/Mus_musculus/NCBIM37/18:33607548-34607548/GRCm38?content-type=application/json
		
		String url = ENSEMBLBASEURL + "map/" + species + "/" + currentAssembly + "/" + chromosome + ":" + start + "-" + end + "/" +  newAssembly  + "?content-type=application/json";
		
		RestTemplate restTemplate = new RestTemplate();
       	Mappings res = restTemplate.getForObject(url, MapResult.class).getMappings().get(0);
       	
        return res.getMapped();
	}
	
	
	/**
	 * @author ilinca
	 * @param chromosome
	 * @param species
	 * @param start
	 * @param end
	 * @return list of genes within window
	 */
	public static List<Feature> getGeneList(String chromosome, String species, long start, long end){ // https://rest.ensembl.org/overlap/region/Mus_musculus/18:33607548-34607548?feature=gene;content-type=application/json .
		
		String url = ENSEMBLBASEURL + "overlap/region/" + species + "/" + chromosome + ":" + start + "-" + end + "?feature=gene;content-type=application/json";
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		List<Feature> features = restTemplate.getForObject(url, List.class);
		
		return features;
		
	}
	
}

package uk.ac.ebi.phis.utils.ensemblclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.RestTemplate;


/**
 * @since 2016/06/09
 * @author ilinca
 *
 */
@EnableRetry
public class EnsemblClient {
	
	public static final String LATEST_MOUSE_GENOME_ASSEMBLY = "GRCm38";
	public static final String MOUSE_GENOME_ASSEMBLY_37 = "NCBIM37";
	private static final String ENSEMBLBASEURL = "http://rest.ensembl.org/";
	
	private RestTemplate restTemplate; 

	private Map<String, Mappings> mappingCache;
	private Map<String, List<Feature>> overlapCache;
	
	public EnsemblClient (){

		HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(5).build();
		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
		mappingCache = new HashMap<>();
		overlapCache = new HashMap<>();
		
	}
	
	
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
	@Retryable
	public Position convertLocation(String currentAssembly, String newAssembly, String chromosome, String species, long start, long end){ //https://rest.ensembl.org/map/Mus_musculus/NCBIM37/18:33607548-34607548/GRCm38?content-type=application/json

		String url = ENSEMBLBASEURL + "map/" + species + "/" + currentAssembly + "/" + chromosome + ":" + start + "-" + end + "/" +  newAssembly  + "?content-type=application/json";
		Mappings res = null;
		
		if (!mappingCache.containsKey(url)){
			List<Mappings> m = restTemplate.getForObject(url, MapResult.class).getMappings();
			if (!m.isEmpty()){
				res = m.get(0);
				mappingCache.put(url, res);
			}
		} else {
			res = mappingCache.get(url);
		}
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
	@Retryable
	public List<Feature> getGeneList(String chromosome, String species, long start, long end){ // https://rest.ensembl.org/overlap/region/Mus_musculus/18:33607548-34607548?feature=gene;content-type=application/json .
		
		String url = ENSEMBLBASEURL + "overlap/region/" + species + "/" + chromosome + ":" + start + "-" + end + "?feature=gene;content-type=application/json";
		List<Feature> features; 
				
		if (overlapCache.containsKey(url)){
			features = overlapCache.get(url);
		} else {
			Feature[] lst = restTemplate.getForEntity(url, Feature[].class).getBody();
			features =  new ArrayList<Feature>(Arrays.asList(lst));
			overlapCache.put(url, features);
		}
		
		return features;
	}
	
	
}

package uk.ac.ebi.phis.utils.olsclient;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uk.ac.ebi.phis.exception.PhenoImageShareException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilinca on 12/12/2016.
 */
@EnableRetry
public class OlsClient {

    private static final String OLS_URL = "http://www.ebi.ac.uk/ols/api/ontologies/";
    private RestTemplate restTemplate;
    private Map<String, Term> termCache;


    public OlsClient() {
        HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(5).build();
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        termCache = new HashMap<>();
    }


    @Retryable
    public Term getTerm(String ontologyId, String oboId) throws PhenoImageShareException { //https://rest.ensembl.org/map/Mus_musculus/NCBIM37/18:33607548-34607548/GRCm38?content-type=application/json

        String url = OLS_URL + ontologyId + "/terms?obo_id=" + oboId;
        try {
            if (!termCache.containsKey(url)) {
                TermResult e = restTemplate.getForObject(url, TermResult.class);
                Embedded embedded = e.get_embedded();
                List<Term> terms = embedded.getTerms();
                if (!terms.isEmpty()) {
                    termCache.put(url, terms.get(0));
                }
            }
            return termCache.get(url);
        } catch (HttpClientErrorException e){
            System.out.println("Error for " + url);
            e.printStackTrace();
            throw new PhenoImageShareException(e.getMessage());
        }
    }

}

package uk.ac.ebi.phis.idgen;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class IdGenerator {
	public String getAnnotationId(){
		return "roi_ua_" + UUID.randomUUID();
	}
}

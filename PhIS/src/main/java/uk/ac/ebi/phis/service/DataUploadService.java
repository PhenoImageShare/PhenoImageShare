package uk.ac.ebi.phis.service;

import uk.ac.ebi.phis.dto.Job;

import java.util.concurrent.Future;

/**
 * Created by ilinca on 16/11/2016.
 */


public interface DataUploadService {


    public Future<Boolean> validateXml(Job job, String xmlPath, Boolean strict);

}
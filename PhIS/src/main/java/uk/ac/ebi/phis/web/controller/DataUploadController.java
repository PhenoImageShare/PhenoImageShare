package uk.ac.ebi.phis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uk.ac.ebi.phis.dto.Job;
import uk.ac.ebi.phis.service.DataUploadService;

import javax.validation.constraints.NotNull;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by ilinca on 10/11/2016.
 */

@Controller
@EnableAsync
public class DataUploadController {


    @NotNull
    @Value("${uploadDir}")
    private String uploadPath;

    @Autowired
    DataUploadService dataUploadService;

    ConcurrentMap<Long, Job> jobs = new ConcurrentHashMap<>(); //<jobId, job>


    @RequestMapping(value="/rest/upload", method= RequestMethod.POST)
    public String handleFileUpload(@ModelAttribute("upload") @RequestParam("file") MultipartFile file, Model model){

        String name = file.getOriginalFilename().replace(".xml", System.currentTimeMillis() + ".xml");

        long jobId = System.currentTimeMillis();
        Job newJob = new Job(jobId);
        jobs.put(jobId, newJob);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(uploadPath + "/" + name)));
                stream.write(bytes);
                stream.close();
                model.addAttribute("jobId", jobId );
                newJob.addJobUpdate("File successfully uploaded (" + file.getOriginalFilename() + ")", true);
                newJob.setFutureJob(dataUploadService.validateXml(newJob, uploadPath + "/" + name, false));
                return "upload";
            } catch (Exception e) {
                newJob.addJobUpdate("Filed to upload " + file.getOriginalFilename() + " => " + e.getMessage(), false);
                newJob.setCompleted(true);
                newJob.setSuccess(false);
                return "upload";
            }
        } else {
            newJob.addJobUpdate("Failed to upload " + file.getOriginalFilename() + " because the file was empty.", false);
            newJob.setCompleted(true);
            newJob.setSuccess(false);
            return "upload";
        }

    }

    @RequestMapping(value="/rest/upload", method= RequestMethod.GET)
    public String listUploadedFiles( @RequestParam(value="jobId", required=false) Long jobId, Model model ) throws IOException {

        model.addAttribute("jobId", jobId );
        return "upload";

    }


    @RequestMapping(value="/rest/upload/status", method= RequestMethod.GET)
    @ResponseBody
    public Job getStatusUpdates( @RequestParam(value="jobId", required=false) Long jobId, Model model) throws IOException {

        if (jobId != null && jobs.containsKey(jobId)){
            return jobs.get(jobId);
        }

        return null;
    }

}

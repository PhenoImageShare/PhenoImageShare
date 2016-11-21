package uk.ac.ebi.phis.dto;

import java.util.TreeSet;
import java.util.concurrent.Future;

/**
 * Created by ilinca on 16/11/2016.
 */
public class Job {

    Long jobId;
    TreeSet<JobUpdate> jobUpdates;
    Boolean completed;
    Boolean success;
    Future<Boolean> currentJob;


    public Job(Long jobId){

        this.jobId = jobId;
        this.jobUpdates = new TreeSet<>(JobUpdate.getComparatorByUpdatenumber());
        this.completed = false;
        jobUpdates.add(new JobUpdate(0, true, "A job was created with id " + jobId));

    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setFutureJob(Future<Boolean> currentJob){
        this.currentJob = currentJob;
    }

    public boolean isJobStillRunning(){
        return (!currentJob.isDone()) | (!currentJob.isCancelled());
    }

    public void addJobUpdate(String message, boolean completedSuccessfully){
        int seq = jobUpdates.size() + 1;
        jobUpdates.add(new JobUpdate(seq,completedSuccessfully,message));
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public TreeSet<JobUpdate> getJobUpdates() {
        return jobUpdates;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "{" +
                "\"jobId\":" + jobId +
                ", \"jobUpdates\":" + jobUpdates +
                ", \"completed\":" + completed +
                ", \"success\":" + success +
                '}';
    }
}

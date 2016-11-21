package uk.ac.ebi.phis.dto;

import java.util.Comparator;

/**
 * Created by ilinca on 16/11/2016.
 */
public class JobUpdate{

    private int updateNo;
    private boolean success;
    private String message;


    public JobUpdate(int updateNo, boolean success, String message){

        this.success = success;
        this.message = message;
        this.updateNo = updateNo;

    }

    public int getUpdateNo() {
        return updateNo;
    }

    public void setUpdateNo(int updateNo) {
        this.updateNo = updateNo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "\"updateNo\":" + updateNo +
                ", \"success\":" + success +
                ", \"message\":'" + message + '\'' +
                '}';
    }

    public static Comparator<JobUpdate> getComparatorByUpdatenumber(){

        return new Comparator<JobUpdate>() {
            @Override
            public int compare(JobUpdate o1, JobUpdate o2) {
                return Integer.compare(o1.updateNo, o2.updateNo);
            }
        };
    }
}
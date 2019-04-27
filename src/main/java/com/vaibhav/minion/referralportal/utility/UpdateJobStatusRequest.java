package com.vaibhav.minion.referralportal.utility;

public class UpdateJobStatusRequest {
    private long jobId;
    private String newJobStatus;

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getNewJobStatus() {
        return newJobStatus;
    }

    public void setNewJobStatus(String newJobStatus) {
        this.newJobStatus = newJobStatus;
    }
}

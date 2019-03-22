package com.vaibhav.minion.referralportal.model;

public class InsertJobResponse {

    private String jobId;
    private boolean isInserted;
    private String message;

    public InsertJobResponse(String jobId, boolean isInserted, String message) {
        this.jobId = jobId;
        this.isInserted = isInserted;
        this.message = message;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public boolean isInserted() {
        return isInserted;
    }

    public void setInserted(boolean inserted) {
        isInserted = inserted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

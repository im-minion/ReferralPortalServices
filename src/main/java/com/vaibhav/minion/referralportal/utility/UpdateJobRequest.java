package com.vaibhav.minion.referralportal.utility;

public class UpdateJobRequest {
    private String jobId;
//    private String jobTitle;
    private String jobDescription;
//    private String yeo;
//    private String createdByEmployeeId;
//    private String prefLocation;
    private boolean jobVisibility;
    private String jobStatus;
//    private String primarySkill;
//    private String secondarySkill;


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public boolean isJobVisibility() {
        return jobVisibility;
    }

    public void setJobVisibility(boolean jobVisibility) {
        this.jobVisibility = jobVisibility;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
}

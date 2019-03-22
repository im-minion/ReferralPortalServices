package com.vaibhav.minion.referralportal.model;

public class InsertJobRequest {
    private String jobId;
    private String jobTitle;
    private String jobDescription;
    private String yoe;
    private String prefLocation;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getYoe() {
        return yoe;
    }

    public void setYoe(String yoe) {
        this.yoe = yoe;
    }

    public String getPrefLocation() {
        return prefLocation;
    }

    public void setPrefLocation(String prefLocation) {
        this.prefLocation = prefLocation;
    }
}

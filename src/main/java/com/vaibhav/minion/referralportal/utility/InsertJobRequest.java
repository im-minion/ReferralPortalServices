package com.vaibhav.minion.referralportal.utility;



public class InsertJobRequest {
    private String jobId;
    private String jobTitle;
    private String jobDescription;
    private String yoe;
    private String prefLocation;
    private String createdByEmployeeId;
    private String primarySkill;
    private String secondarySkill;

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

    public String getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(String createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public String getSecondarySkill() {
        return secondarySkill;
    }

    public void setSecondarySkill(String secondarySkill) {
        this.secondarySkill = secondarySkill;
    }
}

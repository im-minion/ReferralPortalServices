package com.vaibhav.minion.referralportal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("JOBS")
public class JOBS {
    @Id
    private String id;
    private String jobId;
    private String jobTitle;
    private String jobDescription;
    private String yeo;
    private String createdByEmployeeId;
    private String prefLocation;
    private boolean jobVisibility = false; //default value
    private String jobStatus = "DEFAULT"; //default value
    private String primarySkill;
    private String secondarySkill;

    public String getId() {
        return id;
    }

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

    public String getYeo() {
        return yeo;
    }

    public void setYeo(String yeo) {
        this.yeo = yeo;
    }

    public String getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(String createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
    }

    public String getPrefLocation() {
        return prefLocation;
    }

    public void setPrefLocation(String prefLocation) {
        this.prefLocation = prefLocation;
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

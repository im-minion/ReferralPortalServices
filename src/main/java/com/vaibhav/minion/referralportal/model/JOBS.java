package com.vaibhav.minion.referralportal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("JOBS")
public class JOBS {
    @Id
    private String id;
    private String jobTitle;
    private String jobDescription;
    private String yeo;
    private String createdByEmployeeId;
    private String prefLocation;
    private List<JobStatus> jobstatusList;

    public String getId() {
        return id;
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

    public List<JobStatus> getJobstatusList() {
        return jobstatusList;
    }

    public void setJobstatusList(List<JobStatus> jobstatusList) {
        this.jobstatusList = jobstatusList;
    }
}

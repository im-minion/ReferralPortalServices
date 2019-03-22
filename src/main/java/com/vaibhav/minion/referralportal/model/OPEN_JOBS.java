package com.vaibhav.minion.referralportal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("OPEN_JOBS")
public class OPEN_JOBS {
    private String jobTitle;
    private String jobDescription;
    private String yeo;
    @Id
    private String id;

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
}

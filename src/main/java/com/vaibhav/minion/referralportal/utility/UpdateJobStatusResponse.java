package com.vaibhav.minion.referralportal.utility;

public class UpdateJobStatusResponse {
    private String message;
    private String newJobStatus;

    public UpdateJobStatusResponse(String message, String newJobStatus) {
        this.message = message;
        this.newJobStatus = newJobStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNewJobStatus() {
        return newJobStatus;
    }

    public void setNewJobStatus(String newJobStatus) {
        this.newJobStatus = newJobStatus;
    }
}

package com.vaibhav.minion.referralportal.utility;

public class UpdateJobVisibilityResponse {
    private String message;
    private boolean currentJobVisibility;

    public UpdateJobVisibilityResponse(String message, boolean currentJobVisibility) {
        this.message = message;
        this.currentJobVisibility = currentJobVisibility;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getCurrentJobVisibility() {
        return currentJobVisibility;
    }

    public void setCurrentJobVisibility(boolean currentJobVisibility) {
        this.currentJobVisibility = currentJobVisibility;
    }
}

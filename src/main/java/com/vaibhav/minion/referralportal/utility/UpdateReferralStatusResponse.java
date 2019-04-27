package com.vaibhav.minion.referralportal.utility;

public class UpdateReferralStatusResponse {
    private String nextLevel;
    private String message;
    private boolean isUpdated;

    public UpdateReferralStatusResponse(String nextLevel, String message, boolean isUpdated) {
        this.nextLevel = nextLevel;
        this.message = message;
        this.isUpdated = isUpdated;
    }

    public String getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(String nextLevel) {
        this.nextLevel = nextLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }
}

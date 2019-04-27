package com.vaibhav.minion.referralportal.utility;

public class UpdateReferralStatusRequest {
    private String referralEmailId;
    private String currentLevel;
    private String status; // ACCEPTED or REJECTED
    private String reason;

    public String getReferralEmailId() {
        return referralEmailId;
    }

    public void setReferralEmailId(String referralEmailId) {
        this.referralEmailId = referralEmailId;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

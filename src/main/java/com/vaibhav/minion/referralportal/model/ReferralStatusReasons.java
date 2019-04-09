package com.vaibhav.minion.referralportal.model;

public class ReferralStatusReasons {
    private String previousStatus;
    private String reasonToPromote;
    private String nextStatus;

    public String getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(String previousStatus) {
        this.previousStatus = previousStatus;
    }

    public String getReasonToPromote() {
        return reasonToPromote;
    }

    public void setReasonToPromote(String reasonToPromote) {
        this.reasonToPromote = reasonToPromote;
    }

    public String getNextStatus() {
        return nextStatus;
    }

    public void setNextStatus(String nextStatus) {
        this.nextStatus = nextStatus;
    }
}

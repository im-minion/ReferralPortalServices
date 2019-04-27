package com.vaibhav.minion.referralportal.utility;


public class InsertJobResponse {

    private String _id;
    private boolean isInserted;
    private String message;

    public InsertJobResponse(String _id, boolean isInserted, String message) {
        this._id = _id;
        this.isInserted = isInserted;
        this.message = message;
    }

    public String getJobId() {
        return _id;
    }

    public void setJobId(String jobId) {
        this._id = jobId;
    }

    public boolean isInserted() {
        return isInserted;
    }

    public void setInserted(boolean inserted) {
        isInserted = inserted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

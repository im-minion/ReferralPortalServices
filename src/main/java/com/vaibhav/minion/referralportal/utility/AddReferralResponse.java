package com.vaibhav.minion.referralportal.utility;

public class AddReferralResponse {

    private String _id;
    private boolean isReferred;
    private String message;

    public AddReferralResponse(String _id, boolean isReferred, String message) {
        this._id = _id;
        this.isReferred = isReferred;
        this.message = message;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean isReferred() {
        return isReferred;
    }

    public void setReferred(boolean referred) {
        isReferred = referred;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.vaibhav.minion.referralportal.utility;

public class LoginResponse {
    private String employeeId;
    private String employeeRole;
    private String message;

    public LoginResponse(String employeeId, String employeeRole, String message) {
        this.employeeId = employeeId;
        this.employeeRole = employeeRole;
        this.message = message;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

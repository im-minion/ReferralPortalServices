package com.vaibhav.minion.referralportal.utility;

public class ChangeRoleResponse {
    private String message;
    private String employeeId;
    private String employeeNewRole;

    public ChangeRoleResponse(String message, String employeeId, String employeeNewRole) {
        this.message = message;
        this.employeeId = employeeId;
        this.employeeNewRole = employeeNewRole;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNewRole() {
        return employeeNewRole;
    }

    public void setEmployeeNewRole(String employeeNewRole) {
        this.employeeNewRole = employeeNewRole;
    }
}

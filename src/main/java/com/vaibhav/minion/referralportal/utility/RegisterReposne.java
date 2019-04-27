package com.vaibhav.minion.referralportal.utility;

public class RegisterReposne {
    private String message;
    private String employeeId;
    private String employeeRole;

    public RegisterReposne(String message, String employeeId, String employeeRole) {
        this.message = message;
        this.employeeId = employeeId;
        this.employeeRole = employeeRole;
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

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }
}

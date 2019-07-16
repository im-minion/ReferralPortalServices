package com.vaibhav.minion.referralportal.utility;

public class ChangeRoleRequest {
    private String employeeId;
    private String employeeNewRole;

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

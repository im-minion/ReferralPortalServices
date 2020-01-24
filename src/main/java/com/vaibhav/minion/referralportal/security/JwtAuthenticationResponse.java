package com.vaibhav.minion.referralportal.security;


public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String employeeId;
    private String employeeRole;

    public JwtAuthenticationResponse(String accessToken, String employeeId, String employeeRole) {
        this.accessToken = accessToken;
        this.employeeId = employeeId;
        this.employeeRole = employeeRole;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
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
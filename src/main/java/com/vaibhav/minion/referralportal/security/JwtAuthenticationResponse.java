package com.vaibhav.minion.referralportal.security;


public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String employeeRole;

    public JwtAuthenticationResponse(String accessToken, String employeeRole) {
        this.accessToken = accessToken;
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

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }
}
package com.vaibhav.minion.referralportal.utility;

public class AnalyticalInfoResponse {
    private EmployeeAnalyticalInfo employeeAnalyticalInfo;
    private HMAnalyticalInfo hmAnalyticalInfo;
    private HRAnalyticalInfo hrAnalyticalInfo;

    public EmployeeAnalyticalInfo getEmployeeAnalyticalInfo() {
        return employeeAnalyticalInfo;
    }

    public void setEmployeeAnalyticalInfo(EmployeeAnalyticalInfo employeeAnalyticalInfo) {
        this.employeeAnalyticalInfo = employeeAnalyticalInfo;
    }

    public HMAnalyticalInfo getHmAnalyticalInfo() {
        return hmAnalyticalInfo;
    }

    public void setHmAnalyticalInfo(HMAnalyticalInfo hmAnalyticalInfo) {
        this.hmAnalyticalInfo = hmAnalyticalInfo;
    }

    public HRAnalyticalInfo getHrAnalyticalInfo() {
        return hrAnalyticalInfo;
    }

    public void setHrAnalyticalInfo(HRAnalyticalInfo hrAnalyticalInfo) {
        this.hrAnalyticalInfo = hrAnalyticalInfo;
    }
}

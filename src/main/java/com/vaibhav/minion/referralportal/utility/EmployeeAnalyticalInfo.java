package com.vaibhav.minion.referralportal.utility;

public class EmployeeAnalyticalInfo {
    private long allReferralsCount, hiredReferralsCount, rejectedReferralsCount, pendingReferralsCount, openJobsCount;

    public long getAllReferralsCount() {
        return allReferralsCount;
    }

    public void setAllReferralsCount(long allReferralsCount) {
        this.allReferralsCount = allReferralsCount;
    }

    public long getHiredReferralsCount() {
        return hiredReferralsCount;
    }

    public void setHiredReferralsCount(long hiredReferralsCount) {
        this.hiredReferralsCount = hiredReferralsCount;
    }

    public long getRejectedReferralsCount() {
        return rejectedReferralsCount;
    }

    public void setRejectedReferralsCount(long rejectedReferralsCount) {
        this.rejectedReferralsCount = rejectedReferralsCount;
    }

    public long getPendingReferralsCount() {
        return pendingReferralsCount;
    }

    public void setPendingReferralsCount(long pendingReferralsCount) {
        this.pendingReferralsCount = pendingReferralsCount;
    }

    public long getOpenJobsCount() {
        return openJobsCount;
    }

    public void setOpenJobsCount(long openJobsCount) {
        this.openJobsCount = openJobsCount;
    }
}

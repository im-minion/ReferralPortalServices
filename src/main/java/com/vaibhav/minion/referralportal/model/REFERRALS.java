package com.vaibhav.minion.referralportal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document("REFERRAL")
public class REFERRALS {

    @Id
    private String id;
    private String referralName;
    private Double jobId;
    private String referDate;
    private String panNumber;
    private String dob;
    private String yoe;
    private String primarySkill;
    private String secondarySkill;
    private String referralStatus = "RESUME_SCREENING";
    private String referredBy;
    private List<ReferralStatusReasons> referralStatusReasonsList;

    public String getReferralId() {
        return id;
    }

    public void setReferralId(String referralId) {
        this.id = referralId;
    }

    public String getReferralName() {
        return referralName;
    }

    public void setReferralName(String referralName) {
        this.referralName = referralName;
    }

    public Double getJobId() {
        return jobId;
    }

    public void setJobId(Double jobId) {
        this.jobId = jobId;
    }

    public String getReferDate() {
        return referDate;
    }

    public void setReferDate(String referDate) {
        this.referDate = referDate;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getYoe() {
        return yoe;
    }

    public void setYoe(String yoe) {
        this.yoe = yoe;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public String getSecondarySkill() {
        return secondarySkill;
    }

    public void setSecondarySkill(String secondarySkill) {
        this.secondarySkill = secondarySkill;
    }

    public String getReferralStatus() {
        return referralStatus;
    }

    public void setReferralStatus(String referralStatus) {
        this.referralStatus = referralStatus;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public List<ReferralStatusReasons> getReferralStatusReasonsList() {
        return referralStatusReasonsList;
    }

    public void setReferralStatusReasonsList(List<ReferralStatusReasons> referralStatusReasonsList) {
        this.referralStatusReasonsList = referralStatusReasonsList;
    }
}

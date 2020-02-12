package com.vaibhav.minion.referralportal.model;

import com.vaibhav.minion.referralportal.utility.ReferralStatusReasons;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document("REFERRAL")
public class REFERRALS {

    @Id
    private String id;
    private String referralEmailId;
    private String referralName;
    private String jobId;
    private String referDate;
    private String panNumber;
    private String dob;
    private String yoe;
    private String primarySkill;
    private String secondarySkill;
//    Deprecated
//    private Binary resume;
    private String resumeV2;
    private String referralCurrentLevel = "RESUME_SCREENING";
    private String referralCurrentStatus = "PENDING";
//    will be pending by default but once referral processed it will be  ACCEPTED or REJECTED
    private String referredBy;
    private List<ReferralStatusReasons> referralStatusReasonsList;

    public String getReferralId() {
        return id;
    }

    public String getReferralEmailId() {
        return referralEmailId;
    }

    public void setReferralEmailId(String referralEmailId) {
        this.referralEmailId = referralEmailId;
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

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
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

//    public Binary getResume() {
//        return resume;
//    }

    public String getResumeV2() {
        return resumeV2;
    }

    public void setResumeV2(String resumeV2) {
        this.resumeV2 = resumeV2;
    }

//    public void setResume(Binary resume) {
//        this.resume = resume;
//    }

    public String getReferralCurrentLevel() {
        return referralCurrentLevel;
    }

    public void setReferralCurrentLevel(String referralCurrentLevel) {
        this.referralCurrentLevel = referralCurrentLevel;
    }

    public String getReferralCurrentStatus() {
        return referralCurrentStatus;
    }

    public void setReferralCurrentStatus(String referralCurrentStatus) {
        this.referralCurrentStatus = referralCurrentStatus;
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

package com.vaibhav.minion.referralportal.repository;

import com.vaibhav.minion.referralportal.model.REFERRALS;
import com.vaibhav.minion.referralportal.utility.EmployeeAnalyticalInfo;
import com.vaibhav.minion.referralportal.utility.LevelStatus;
import com.vaibhav.minion.referralportal.utility.ReferralStatusReasons;
import com.vaibhav.minion.referralportal.utility.UpdateReferralStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReferralsRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String REFERRALS_COLLECTION = "REFERRALS";

    public List<REFERRALS> getReferralsFromJobId(String jobId) {
        Criteria jobIdCriteria = new Criteria("jobId").is(jobId);
        Query query = new Query();
        query.addCriteria(jobIdCriteria);
        List<REFERRALS> referrals = mongoTemplate.find(query, REFERRALS.class, REFERRALS_COLLECTION);
//        referrals = referrals.stream().filter(referrals1 -> !referrals1.getReferralCurrentLevel().equals("HR")).collect(Collectors.toList());
        return referrals;
    }

    public void updateReferralStatus(LevelStatus levelStatus, UpdateReferralStatusRequest updateReferralStatusRequest, ReferralStatusReasons referralStatusReasons) {

        Update update = new Update();
        update.set("referralCurrentLevel", levelStatus.getNextLevel());
        update.set("referralCurrentStatus", levelStatus.getNextStatus());
        update.addToSet("referralStatusReasonsList", referralStatusReasons);

        Query query = new Query();
        Criteria referralEmailIdCriteria = new Criteria("referralEmailId").is(updateReferralStatusRequest.getReferralEmailId());
        query.addCriteria(referralEmailIdCriteria);

        mongoTemplate.updateFirst(query, update, REFERRALS.class, REFERRALS_COLLECTION);
    }

    public List<REFERRALS> getReferralsOfEmployeeId(String employeeId) {
        Query query = new Query();
        Criteria referredByCriteria = new Criteria("referredBy").is(employeeId);
        query.addCriteria(referredByCriteria);
        return mongoTemplate.find(query, REFERRALS.class, REFERRALS_COLLECTION);
    }

    public REFERRALS addReferral(REFERRALS referrals) {
        return mongoTemplate.insert(referrals, REFERRALS_COLLECTION);
    }

    public boolean isReferralEmailIdExists(String referralEmailId) {
        Criteria emailIdCriteria = new Criteria("referralEmailId").is(referralEmailId);
        Query query = new Query();
        query.addCriteria(emailIdCriteria);
        REFERRALS referral = mongoTemplate.findOne(query, REFERRALS.class, REFERRALS_COLLECTION);
        return referral != null;
    }

    public List<REFERRALS> getReferralsAtHr() {
        Query query = new Query();
        query.addCriteria(new Criteria("referralCurrentLevel").is("HR")).addCriteria(new Criteria("referralCurrentStatus").is("PENDING"));
        return mongoTemplate.find(query, REFERRALS.class, REFERRALS_COLLECTION);
    }

    public List<REFERRALS> getAllReferralsForHr() {
        return mongoTemplate.findAll(REFERRALS.class, REFERRALS_COLLECTION);
    }

    public EmployeeAnalyticalInfo getReferralsCountFromEmployeeID(String employeeId) {
        EmployeeAnalyticalInfo employeeAnalyticalInfo = new EmployeeAnalyticalInfo();

        List<REFERRALS> referralsList = getReferralsOfEmployeeId(employeeId);
        int hiredCount = (int) referralsList.stream().filter(referrals -> referrals.getReferralCurrentLevel().equals("HR") && referrals.getReferralCurrentStatus().equals("ACCEPTED")).count();
        int rejectedCount = (int) referralsList.stream().filter(referrals -> referrals.getReferralCurrentStatus().equals("REJECTED")).count();
        int pendingCount = (int) referralsList.stream().filter(referrals -> referrals.getReferralCurrentStatus().equals("PENDING")).count();

        employeeAnalyticalInfo.setAllReferralsCount(referralsList.size());
        employeeAnalyticalInfo.setHiredReferralsCount(hiredCount);
        employeeAnalyticalInfo.setRejectedReferralsCount(rejectedCount);
        employeeAnalyticalInfo.setPendingReferralsCount(pendingCount);

        return employeeAnalyticalInfo;
    }
}

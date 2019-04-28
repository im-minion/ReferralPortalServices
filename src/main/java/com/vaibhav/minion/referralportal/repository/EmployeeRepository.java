package com.vaibhav.minion.referralportal.repository;

import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String REFERRALS_COLLECTION = "REFERRALS";

    private static final String JOBS_COLLECTION = "JOBS";

    public List<JOBS> getAllOpenJobs() {
        Criteria criteriaJobVisibility = new Criteria("jobVisibility").is(true);
        Criteria criteriaJobStatus = new Criteria("jobStatus").is("OPEN");
        Query query = new Query();
        query.addCriteria(criteriaJobVisibility).addCriteria(criteriaJobStatus);
        return mongoTemplate.find(query, JOBS.class, JOBS_COLLECTION);
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
}

package com.vaibhav.minion.referralportal.repository;

import com.vaibhav.minion.referralportal.dao.EmployeeDao;
import com.vaibhav.minion.referralportal.model.AddReferralRequest;
import com.vaibhav.minion.referralportal.model.AddReferralResponse;
import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository implements EmployeeDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String REFERRALS_COLLECTION = "REFERRALS";

    private static final String JOBS_COLLECTION = "JOBS";

    @Override
    public List<JOBS> getAllOpenJobs() {
        Criteria criteriaJobVisibility = new Criteria("jobVisibility").is(true);
        Criteria criteriaJobStatus = new Criteria("jobStatus").is("OPEN");
        Query query = new Query();
        query.addCriteria(criteriaJobVisibility).addCriteria(criteriaJobStatus);
        return mongoTemplate.find(query, JOBS.class, JOBS_COLLECTION);
    }

    @Override
    public List<REFERRALS> getReferralsOfEmployeeId(String employeeId) {
        Query query = new Query();
        Criteria referredByCriteria = new Criteria("referredBy").is(employeeId);
        query.addCriteria(referredByCriteria);
        return mongoTemplate.find(query, REFERRALS.class, REFERRALS_COLLECTION);
    }

    @Override
    public AddReferralResponse addReferral(AddReferralRequest addReferralRequest) {
        AddReferralResponse addReferralResponse;
        try {
            REFERRALS referrals = new REFERRALS();
            if(!isReferralEmailIdExists(addReferralRequest.getReferralEmailId())) {
                referrals.setReferralEmailId(addReferralRequest.getReferralEmailId());
                referrals.setDob(addReferralRequest.getDob());
                referrals.setJobId(addReferralRequest.getJobId());
                referrals.setPanNumber(addReferralRequest.getPanNumber());
                referrals.setReferDate(addReferralRequest.getReferDate());
                referrals.setReferralName(addReferralRequest.getReferralName());
                referrals.setPrimarySkill(addReferralRequest.getPrimarySkill());
                referrals.setSecondarySkill(addReferralRequest.getSecondarySkill());
                referrals.setReferredBy(addReferralRequest.getReferredBy());

                mongoTemplate.insert(referrals, REFERRALS_COLLECTION);
                addReferralResponse = new AddReferralResponse("", true, "");
            }else {
                addReferralResponse = new AddReferralResponse("", false, "Referral Email Id already Exists can't refer!");
            }
            return addReferralResponse;
        } catch (Exception e) {
            addReferralResponse = new AddReferralResponse("", false, "");
        }
        return addReferralResponse;
    }

    private boolean isReferralEmailIdExists(String referralEmailId) {
        Criteria emailIdCriteria = new Criteria("referralEmailId").is(referralEmailId);
        Query query = new Query();
        query.addCriteria(emailIdCriteria);
        REFERRALS referral = mongoTemplate.findOne(query,REFERRALS.class,REFERRALS_COLLECTION);
        return referral != null;
    }
}

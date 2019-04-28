package com.vaibhav.minion.referralportal.repository;

import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import com.vaibhav.minion.referralportal.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class HMRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String OPEN_JOBS_COLLECTION = "JOBS";
    private static final String REFERRALS_COLLECTION = "REFERRALS";

    public void insertJob(JOBS jobs) {
        mongoTemplate.insert(jobs, OPEN_JOBS_COLLECTION);
    }

    public boolean isJobIdExists(long jobId) {
        Criteria insertJobCriteria = new Criteria("jobId").is(jobId);
        Query query = new Query();
        query.addCriteria(insertJobCriteria);
        JOBS job = mongoTemplate.findOne(query, JOBS.class, OPEN_JOBS_COLLECTION);
        return job != null;
    }

    public List<JOBS> getAllJobsForHm(String employeeId) {
        Criteria criteriaEmployeeId = new Criteria("createdByEmployeeId").is(employeeId);
        Criteria criteriaJobVisibility = new Criteria("jobVisibility").is(true);
        Criteria criteriaJobStatus = new Criteria("jobStatus").is("OPEN");
        Query query = new Query();
        query.addCriteria(criteriaEmployeeId).addCriteria(criteriaJobVisibility).addCriteria(criteriaJobStatus);
        return mongoTemplate.find(query, JOBS.class, OPEN_JOBS_COLLECTION);
    }

    public List<REFERRALS> getReferralsFromJobId(Double jobId) {
        Criteria jobIdCriteria = new Criteria("jobId").is(jobId);
        Query query = new Query();
        query.addCriteria(jobIdCriteria);
        return mongoTemplate.find(query, REFERRALS.class, REFERRALS_COLLECTION);
    }

    public UpdateJobStatusResponse updateJobStatus(UpdateJobStatusRequest updateJobStatusRequest) {
        UpdateJobStatusResponse updateJobStatusResponse;
        try {
            Update update = new Update();
            update.set("jobStatus", updateJobStatusRequest.getNewJobStatus());
            Criteria jobIdCriteria = new Criteria("jobId").is(updateJobStatusRequest.getJobId());
            Query query = new Query();
            query.addCriteria(jobIdCriteria);
            mongoTemplate.updateFirst(query, update, JOBS.class, OPEN_JOBS_COLLECTION);
            return new UpdateJobStatusResponse("Successfully updated!", updateJobStatusRequest.getNewJobStatus());
        } catch (Exception e) {
            return new UpdateJobStatusResponse("Failed to update", "DEFAULT");
        }
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

    public void updateJobVisibility(Double jobId, boolean currentVisibility) {
//        enhanced version of query
        mongoTemplate.updateFirst(Query.query(new Criteria("jobId").is(jobId)), new Update().set("jobVisibility", !currentVisibility), OPEN_JOBS_COLLECTION);
    }

    public boolean getCurrentJobVisibility(Double jobId) {
        return Objects.requireNonNull(mongoTemplate.findOne(Query.query(new Criteria("jobId").is(jobId)), JOBS.class)).isJobVisibility();
    }

}

package com.vaibhav.minion.referralportal.repository;

import com.vaibhav.minion.referralportal.dao.HMDao;
import com.vaibhav.minion.referralportal.model.*;
import com.vaibhav.minion.referralportal.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HMRepository implements HMDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String OPEN_JOBS_COLLECTION = "JOBS";
    private static final String REFERRALS_COLLECTION = "REFERRALS";

    @Override
    public InsertJobResponse insertJob(InsertJobRequest insertJobRequest) {
        InsertJobResponse insertJobResponse;
        try {
            JOBS jobs = new JOBS();
            if (!isJobIdExists(insertJobRequest.getJobId())) {
                jobs.setJobId(insertJobRequest.getJobId());
                jobs.setJobDescription(insertJobRequest.getJobDescription());
                jobs.setJobTitle(insertJobRequest.getJobTitle());
                jobs.setYeo(insertJobRequest.getYoe());
                jobs.setCreatedByEmployeeId(insertJobRequest.getCreatedByEmployeeId());
                jobs.setPrimarySkill(insertJobRequest.getPrimarySkill());
                jobs.setSecondarySkill(insertJobRequest.getSecondarySkill());
                mongoTemplate.insert(jobs, OPEN_JOBS_COLLECTION);
                insertJobResponse = new InsertJobResponse(jobs.getId(), true, "success");
                //return insertJobResponse;
            } else {
                insertJobResponse = new InsertJobResponse(null, false, "JobId already exists!");
            }
            return insertJobResponse;
        } catch (Exception e) {
            insertJobResponse = new InsertJobResponse(null, false, e.getMessage());
            return insertJobResponse;
        }
    }

    private boolean isJobIdExists(long jobId) {
        Criteria insertJobCriteria = new Criteria("jobId").is(jobId);
        Query query = new Query();
        query.addCriteria(insertJobCriteria);
        JOBS job = mongoTemplate.findOne(query, JOBS.class, OPEN_JOBS_COLLECTION);
        return job != null;
    }

    @Override
    public List<JOBS> getAllJobsForHm(String employeeId) {
        Criteria criteriaEmployeeId = new Criteria("createdByEmployeeId").is(employeeId);
        Criteria criteriaJobVisibility = new Criteria("jobVisibility").is(true);
        Criteria criteriaJobStatus = new Criteria("jobStatus").is("OPEN");
        Query query = new Query();
        query.addCriteria(criteriaEmployeeId).addCriteria(criteriaJobVisibility).addCriteria(criteriaJobStatus);
        return mongoTemplate.find(query, JOBS.class, OPEN_JOBS_COLLECTION);
    }

    @Override
    public List<REFERRALS> getReferralsFromJobId(Double jobId) {
        Criteria jobIdCriteria = new Criteria("jobId").is(jobId);
        Query query = new Query();
        query.addCriteria(jobIdCriteria);
        return mongoTemplate.find(query, REFERRALS.class, REFERRALS_COLLECTION);
    }

    @Override
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

    @Override
    public UpdateReferralStatusResponse updateReferralStatus(UpdateReferralStatusRequest updateReferralStatusRequest) {
        UpdateReferralStatusResponse updateReferralStatusResponse;
        try {

            ReferralStatusReasons referralStatusReasons = new ReferralStatusReasons();
            referralStatusReasons.setLevel(updateReferralStatusRequest.getCurrentLevel());
            referralStatusReasons.setStatus(updateReferralStatusRequest.getStatus()); //either ACCEPTED or REJECTED
            referralStatusReasons.setReasonToUpdate(updateReferralStatusRequest.getReason());

            LevelStatus levelStatus = getNextLevelStatus(updateReferralStatusRequest.getCurrentLevel(), updateReferralStatusRequest.getStatus());
            Update update = new Update();
            update.set("referralCurrentLevel", levelStatus.getNextLevel());
            update.set("referralCurrentStatus", levelStatus.getNextStatus());
            update.addToSet("referralStatusReasonsList", referralStatusReasons);

            Query query = new Query();
            Criteria referralEmailIdCriteria = new Criteria("referralEmailId").is(updateReferralStatusRequest.getReferralEmailId());
            query.addCriteria(referralEmailIdCriteria);

            mongoTemplate.updateFirst(query, update, REFERRALS.class, REFERRALS_COLLECTION);

            return new UpdateReferralStatusResponse(levelStatus.getNextLevel(), "SUCCESS", true);
        } catch (Exception e) {
            return new UpdateReferralStatusResponse("", "FAILURE", false);
        }

    }


    private LevelStatus getNextLevelStatus(String currentLevel, String status) {
        LevelStatus levelStatus = new LevelStatus();
        String nextLevel = null;
        String nextStatus = "PENDING";
        if (status.equals("ACCEPTED")) {
            switch (currentLevel) {
                case "RESUME_SCREENING":
                    nextLevel = "L1";
                    break;
                case "L1":
                    nextLevel = "L2";
                    break;
                case "L2":
                    nextLevel = "HR";
                    break;
                case "HR":
                    nextLevel = "HR";
                    nextStatus = "ACCEPTED";
                    break;
                default:
                    break;
            }
        } else if (status.equals("REJECTED")) {
            nextLevel = currentLevel;
            nextStatus = "REJECTED";
        }
        levelStatus.setNextLevel(nextLevel);
        levelStatus.setNextStatus(nextStatus);
        return levelStatus;
    }

}

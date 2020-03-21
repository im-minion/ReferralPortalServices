package com.vaibhav.minion.referralportal.repository;

import com.mongodb.client.result.UpdateResult;
import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.utility.UpdateJobRequest;
import com.vaibhav.minion.referralportal.utility.UpdateJobStatusRequest;
import com.vaibhav.minion.referralportal.utility.UpdateJobStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class JobsRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String JOBS_COLLECTION = "JOBS";

    public List<JOBS> getAllOpenJobs() {
        Criteria criteriaJobVisibility = new Criteria("jobVisibility").is(true);
        Criteria criteriaJobStatus = new Criteria("jobStatus").is("OPEN");
        Query query = new Query();
        query.addCriteria(criteriaJobVisibility).addCriteria(criteriaJobStatus);
        return mongoTemplate.find(query, JOBS.class, JOBS_COLLECTION);
    }

    public void insertJob(JOBS jobs) {
        mongoTemplate.insert(jobs, JOBS_COLLECTION);
    }

    public boolean isJobIdExists(String jobId) {
        Criteria insertJobCriteria = new Criteria("jobId").is(jobId);
        Query query = new Query();
        query.addCriteria(insertJobCriteria);
        JOBS job = mongoTemplate.findOne(query, JOBS.class, JOBS_COLLECTION);
        return job != null;
    }

    public List<JOBS> getAllJobsCreatedByHm(String employeeId) {
        Criteria criteriaEmployeeId = new Criteria("createdByEmployeeId").is(employeeId);
//        Criteria criteriaJobVisibility = new Criteria("jobVisibility").is(true);
//        Criteria criteriaJobStatus = new Criteria("jobStatus").is("OPEN");
        Query query = new Query();
        query.addCriteria(criteriaEmployeeId);//.addCriteria(criteriaJobVisibility).addCriteria(criteriaJobStatus);
        return mongoTemplate.find(query, JOBS.class, JOBS_COLLECTION);
    }

    public UpdateJobStatusResponse updateJobStatus(UpdateJobStatusRequest updateJobStatusRequest) {
        try {
            Update update = new Update();
            update.set("jobStatus", updateJobStatusRequest.getNewJobStatus());
            Criteria jobIdCriteria = new Criteria("jobId").is(updateJobStatusRequest.getJobId());
            Query query = new Query();
            query.addCriteria(jobIdCriteria);
            mongoTemplate.updateFirst(query, update, JOBS.class, JOBS_COLLECTION);
            return new UpdateJobStatusResponse("Successfully updated!", updateJobStatusRequest.getNewJobStatus());
        } catch (Exception e) {
            return new UpdateJobStatusResponse("Failed to update", "DEFAULT");
        }
    }

    public void updateJobVisibility(String jobId, boolean currentVisibility) {
//        enhanced version of query
        mongoTemplate.updateFirst(Query.query(new Criteria("jobId").is(jobId)), new Update().set("jobVisibility", !currentVisibility), JOBS_COLLECTION);
    }

    public boolean getCurrentJobVisibility(String jobId) {
        return Objects.requireNonNull(mongoTemplate.findOne(Query.query(new Criteria("jobId").is(jobId)), JOBS.class)).isJobVisibility();
    }

    public JOBS getJobByJobId(String jobId) {
        Criteria jobCriteria = new Criteria("jobId").is(jobId);
        Query query = new Query();
        query.addCriteria(jobCriteria);
//        TODO: if not found what to do? Don't know will do once know this
        return mongoTemplate.findOne(query, JOBS.class, JOBS_COLLECTION);
    }

    public boolean update(UpdateJobRequest updateJobRequest) {
        Update update = new Update();
        update.set("jobStatus", updateJobRequest.getJobStatus());
        update.set("jobVisibility", updateJobRequest.isJobVisibility());
        update.set("jobDescription", updateJobRequest.getJobDescription());
        Criteria jobIdCriteria = new Criteria("jobId").is(updateJobRequest.getJobId());
        Query query = new Query();
        query.addCriteria(jobIdCriteria);
        UpdateResult u = mongoTemplate.updateFirst(query, update, JOBS.class, JOBS_COLLECTION);
        return u.wasAcknowledged();
    }

    public long getAllJobsCount() {
        return mongoTemplate.count(new Query(),JOBS_COLLECTION);

    }
}

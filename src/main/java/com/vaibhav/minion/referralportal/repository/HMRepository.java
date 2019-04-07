package com.vaibhav.minion.referralportal.repository;

import com.vaibhav.minion.referralportal.dao.HMDao;
import com.vaibhav.minion.referralportal.model.InsertJobRequest;
import com.vaibhav.minion.referralportal.model.InsertJobResponse;
import com.vaibhav.minion.referralportal.model.JOBS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HMRepository implements HMDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String OPEN_JOBS_COLLECTION = "JOBS";

    @Override
    public InsertJobResponse insertJob(InsertJobRequest insertJobRequest) {
        InsertJobResponse insertJobResponse;
        try {
            JOBS jobs = new JOBS();
            jobs.setJobDescription(insertJobRequest.getJobDescription());
            jobs.setJobTitle(insertJobRequest.getJobTitle());
            jobs.setYeo(insertJobRequest.getYoe());
            jobs.setCreatedByEmployeeId(insertJobRequest.getCreatedByEmployeeId());
            jobs.setPrimarySkill(insertJobRequest.getPrimarySkill());
            jobs.setSecondarySkill(insertJobRequest.getSecondarySkill());
            mongoTemplate.insert(jobs, OPEN_JOBS_COLLECTION);
            insertJobResponse = new InsertJobResponse(jobs.getId(), true, "success");
            return insertJobResponse;
        } catch (Exception e) {
            insertJobResponse = new InsertJobResponse(null, false, e.getMessage());
            return insertJobResponse;
        }
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
}

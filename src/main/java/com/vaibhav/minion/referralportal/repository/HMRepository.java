package com.vaibhav.minion.referralportal.repository;

import com.vaibhav.minion.referralportal.dao.HMDao;
import com.vaibhav.minion.referralportal.model.InsertJobRequest;
import com.vaibhav.minion.referralportal.model.InsertJobResponse;
import com.vaibhav.minion.referralportal.model.OPEN_JOBS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HMRepository implements HMDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String OPEN_JOBS_COLLECTION = "OPEN_JOBS";

    @Override
    public InsertJobResponse insertJob(InsertJobRequest insertJobRequest) {
        InsertJobResponse insertJobResponse;
        try {
            OPEN_JOBS open_jobs = new OPEN_JOBS();
            open_jobs.setJobDescription(insertJobRequest.getJobDescription());
            open_jobs.setJobTitle(insertJobRequest.getJobTitle());
            open_jobs.setYeo(insertJobRequest.getYoe());
            mongoTemplate.insert(open_jobs, OPEN_JOBS_COLLECTION);
            insertJobResponse = new InsertJobResponse(open_jobs.getId(), true, "success");
            return insertJobResponse;
        } catch (Exception e) {
            insertJobResponse =  new InsertJobResponse(null, false, e.getMessage());
            return insertJobResponse;
        }
    }
}

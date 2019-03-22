package com.vaibhav.minion.referralportal.dao;

import com.vaibhav.minion.referralportal.model.InsertJobRequest;
import com.vaibhav.minion.referralportal.model.InsertJobResponse;
import com.vaibhav.minion.referralportal.model.JOBS;

import java.util.List;

public interface HMDao {

    public InsertJobResponse insertJob(InsertJobRequest insertJobRequest);

    public List<JOBS> getAllOpenJobs(String employeeId);
}

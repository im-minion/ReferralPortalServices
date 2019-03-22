package com.vaibhav.minion.referralportal.dao;

import com.vaibhav.minion.referralportal.model.InsertJobRequest;
import com.vaibhav.minion.referralportal.model.InsertJobResponse;

public interface HMDao {

    public InsertJobResponse insertJob(InsertJobRequest insertJobRequest);

//    public GetAllOpenJobs getAllOpenJobs(String employeeId);
}

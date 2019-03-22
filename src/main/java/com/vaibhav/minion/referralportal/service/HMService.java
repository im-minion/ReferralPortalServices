package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.dao.HMDao;
import com.vaibhav.minion.referralportal.model.InsertJobRequest;
import com.vaibhav.minion.referralportal.model.InsertJobResponse;
import com.vaibhav.minion.referralportal.model.JOBS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HMService {

    @Autowired
    private HMDao hmDao;

    public InsertJobResponse insertJob(InsertJobRequest insertJobRequest) {
        InsertJobResponse res = hmDao.insertJob(insertJobRequest);
        return res;
    }

    public List<JOBS> getOpenJobs(String employeeId) {
        List<JOBS> jobsList = hmDao.getAllOpenJobs(employeeId);
        return jobsList;
    }
}

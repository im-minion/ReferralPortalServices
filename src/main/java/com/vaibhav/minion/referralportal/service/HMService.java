package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.dao.HMDao;
import com.vaibhav.minion.referralportal.model.*;
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
        List<JOBS> jobsList = hmDao.getAllJobsForHm(employeeId);
        return jobsList;
    }

    public List<REFERRALS> getReferralsFromJobId(Double jobId) {
        List<REFERRALS> referralsList = hmDao.getReferralsFromJobId(jobId);
        return referralsList;
    }

    public UpdateJobStatusResponse updateJobStatus(UpdateJobStatusRequest updateJobStatusRequest){
        return hmDao.updateJobStatus(updateJobStatusRequest);
    }

    public UpdateReferralStatusResponse updateReferralStatus(UpdateReferralStatusRequest updateReferralStatusRequest){
        return hmDao.updateReferralStatus(updateReferralStatusRequest);
    }
}

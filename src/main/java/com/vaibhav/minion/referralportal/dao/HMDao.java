package com.vaibhav.minion.referralportal.dao;

import com.vaibhav.minion.referralportal.model.*;

import java.util.List;

public interface HMDao {

    public InsertJobResponse insertJob(InsertJobRequest insertJobRequest);

    public List<JOBS> getAllJobsForHm(String employeeId);

    public List<REFERRALS> getReferralsFromJobId(Double jobId);

    public UpdateJobStatusResponse updateJobStatus(UpdateJobStatusRequest updateJobStatusRequest);
}

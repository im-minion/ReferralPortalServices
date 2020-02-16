package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import com.vaibhav.minion.referralportal.utility.*;

import java.util.List;


public interface IHMService {

    public InsertJobResponse insertJob(InsertJobRequest insertJobRequest);

    public List<JOBS> getAllJobsCreatedByHm(String employeeId);

    public List<REFERRALS> getReferralsFromJobId(String jobId);

    public UpdateJobVisibilityResponse updateJobVisibility(String jobId);

    public UpdateJobStatusResponse updateJobStatus(UpdateJobStatusRequest updateJobStatusRequest);

    public UpdateReferralStatusResponse updateReferralStatus(UpdateReferralStatusRequest updateReferralStatusRequest);

    public boolean updateJob(UpdateJobRequest updateJobRequest);
}

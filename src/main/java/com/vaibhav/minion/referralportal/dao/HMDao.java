package com.vaibhav.minion.referralportal.dao;

import com.vaibhav.minion.referralportal.model.*;
import com.vaibhav.minion.referralportal.utility.*;

import java.util.List;

public interface HMDao {

    public InsertJobResponse insertJob(InsertJobRequest insertJobRequest);

    public List<JOBS> getAllJobsForHm(String employeeId);

    public List<REFERRALS> getReferralsFromJobId(Double jobId);

    public UpdateJobStatusResponse updateJobStatus(UpdateJobStatusRequest updateJobStatusRequest);

    public UpdateReferralStatusResponse updateReferralStatus(UpdateReferralStatusRequest updateReferralStatusRequest);
}

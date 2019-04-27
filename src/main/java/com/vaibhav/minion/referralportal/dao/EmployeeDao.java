package com.vaibhav.minion.referralportal.dao;

import com.vaibhav.minion.referralportal.utility.AddReferralResponse;
import com.vaibhav.minion.referralportal.utility.AddReferralRequest;
import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;

import java.util.List;

public interface EmployeeDao {

    public List<JOBS> getAllOpenJobs();

    public List<REFERRALS> getReferralsOfEmployeeId(String employeeId);

    public AddReferralResponse addReferral(AddReferralRequest addReferralRequest);
}

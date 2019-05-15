package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import com.vaibhav.minion.referralportal.utility.AddReferralRequest;
import com.vaibhav.minion.referralportal.utility.AddReferralResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmployeeService {

    public List<JOBS> getAllOpenJobs();

    public List<REFERRALS> getReferralsOfEmployeeId(String employeeId);

    public AddReferralResponse addReferral(String resume, AddReferralRequest addReferralRequest);
}

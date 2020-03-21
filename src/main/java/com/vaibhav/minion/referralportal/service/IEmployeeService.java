package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import com.vaibhav.minion.referralportal.utility.AddReferralRequest;
import com.vaibhav.minion.referralportal.utility.AddReferralResponse;
import com.vaibhav.minion.referralportal.utility.AnalyticalInfoResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmployeeService {

    List<JOBS> getAllOpenJobs();

    List<REFERRALS> getReferralsOfEmployeeId(String employeeId);

    AddReferralResponse addReferral(MultipartFile file, AddReferralRequest addReferralRequest);

    InputStreamResource getFileByID(String fileId);

    JOBS getJobByJobId(String jobId);

    AnalyticalInfoResponse getAnalyticalInfo(String employeeId, String role);
}

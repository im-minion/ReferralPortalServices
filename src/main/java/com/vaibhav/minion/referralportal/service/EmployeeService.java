package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.dao.EmployeeDao;
import com.vaibhav.minion.referralportal.model.AddReferralRequest;
import com.vaibhav.minion.referralportal.model.AddReferralResponse;
import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public AddReferralResponse addReferral(AddReferralRequest addReferralRequest) {
        AddReferralResponse addReferralResponse = employeeDao.addReferral(addReferralRequest);
        return addReferralResponse;
    }

    public List<REFERRALS> getReferralsOfEmployeeId(String employeeId) {
        List<REFERRALS> referralsList = employeeDao.getReferralsOfEmployeeId(employeeId);
        return referralsList;
    }

    public List<JOBS> getAllOpenJobs() {
        List<JOBS> jobsList = employeeDao.getAllOpenJobs();
        return jobsList;
    }

}

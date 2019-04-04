package com.vaibhav.minion.referralportal.repository;

import com.vaibhav.minion.referralportal.dao.EmployeeDao;
import com.vaibhav.minion.referralportal.model.AddReferralResponse;
import com.vaibhav.minion.referralportal.model.AddReferralRequest;
import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository implements EmployeeDao {

    @Override
    public List<JOBS> getAllOpenJobs() {
        return null;
    }

    @Override
    public List<REFERRALS> getReferralsOfEmployeeId(String employeeId) {
        return null;
    }

    @Override
    public AddReferralResponse addReferral(AddReferralRequest addReferralRequest) {
        return null;
    }
}

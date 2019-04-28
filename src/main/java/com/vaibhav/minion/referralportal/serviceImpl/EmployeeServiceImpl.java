package com.vaibhav.minion.referralportal.serviceImpl;

import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import com.vaibhav.minion.referralportal.repository.EmployeeRepository;
import com.vaibhav.minion.referralportal.service.IEmployeeService;
import com.vaibhav.minion.referralportal.utility.AddReferralRequest;
import com.vaibhav.minion.referralportal.utility.AddReferralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public List<JOBS> getAllOpenJobs() {
        return employeeRepository.getAllOpenJobs();
    }

    @Override
    public List<REFERRALS> getReferralsOfEmployeeId(String employeeId) {
        return employeeRepository.getReferralsOfEmployeeId(employeeId);
    }

    @Override
    public AddReferralResponse addReferral(AddReferralRequest addReferralRequest) {
        AddReferralResponse addReferralResponse;
        try {
            REFERRALS referrals = new REFERRALS();
            if (!employeeRepository.isReferralEmailIdExists(addReferralRequest.getReferralEmailId())) {
                referrals.setReferralEmailId(addReferralRequest.getReferralEmailId());
                referrals.setDob(addReferralRequest.getDob());
                referrals.setJobId(addReferralRequest.getJobId());
                referrals.setPanNumber(addReferralRequest.getPanNumber());
                referrals.setReferDate(addReferralRequest.getReferDate());
                referrals.setReferralName(addReferralRequest.getReferralName());
                referrals.setPrimarySkill(addReferralRequest.getPrimarySkill());
                referrals.setSecondarySkill(addReferralRequest.getSecondarySkill());
                referrals.setReferredBy(addReferralRequest.getReferredBy());

                REFERRALS r = employeeRepository.addReferral(referrals);

                addReferralResponse = new AddReferralResponse(r.getReferralId(), true, "Referral added successfully!");
            } else {
                addReferralResponse = new AddReferralResponse(null, false, "Referral Email Id already Exists can't refer!");
            }
            return addReferralResponse;
        } catch (Exception e) {
            addReferralResponse = new AddReferralResponse(null, false, "Exception occurred!" + e.getMessage());
        }
        return addReferralResponse;
    }
}

package com.vaibhav.minion.referralportal.serviceImpl;


import com.vaibhav.minion.referralportal.model.EMPLOYEE;
import com.vaibhav.minion.referralportal.repository.EmployeeRepository;
import com.vaibhav.minion.referralportal.repository.JobsRepository;
import com.vaibhav.minion.referralportal.repository.ReferralsRepository;
import com.vaibhav.minion.referralportal.service.IAdminService;
import com.vaibhav.minion.referralportal.utility.ChangeRoleRequest;
import com.vaibhav.minion.referralportal.utility.ChangeRoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private ReferralsRepository referralsRepository;

    @Override
    public ChangeRoleResponse changeEmployeeRole(ChangeRoleRequest changeRoleRequest) {
       return employeeRepository.changeEmployeeRole(changeRoleRequest);
    }

    @Override
    public List<EMPLOYEE> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }
}

package com.vaibhav.minion.referralportal.serviceImpl;

import com.vaibhav.minion.referralportal.model.EMPLOYEE;
import com.vaibhav.minion.referralportal.repository.EmployeeRepository;
import com.vaibhav.minion.referralportal.repository.JobsRepository;
import com.vaibhav.minion.referralportal.repository.ReferralsRepository;
import com.vaibhav.minion.referralportal.service.IAuthService;
import com.vaibhav.minion.referralportal.utility.LoginRequest;
import com.vaibhav.minion.referralportal.utility.LoginResponse;
import com.vaibhav.minion.referralportal.utility.RegisterReposne;
import com.vaibhav.minion.referralportal.utility.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public RegisterReposne registerUser(RegisterRequest registerRequest) {
        RegisterReposne registerReposne;// = null;

        boolean isEmployeeIdExists = employeeRepository.checkIsEmployeeIdExists(registerRequest.getEmployeeId());

        if (isEmployeeIdExists) {
            registerReposne = new RegisterReposne("EmployeeId " + registerRequest.getEmployeeId() + " already exists, cannot Register", null, null);
        } else {
            try {
                EMPLOYEE employee = employeeRepository.registerUser(registerRequest);
                registerReposne = new RegisterReposne("SUCCESS", employee.getEmployeeId(), employee.getEmployeeRole());
            } catch (Exception e) {
                registerReposne = new RegisterReposne("FAILED IN  INSERTING", null, null);
            }
        }
        return registerReposne;
    }
}

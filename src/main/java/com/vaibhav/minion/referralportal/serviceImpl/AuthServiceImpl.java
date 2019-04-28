package com.vaibhav.minion.referralportal.serviceImpl;

import com.vaibhav.minion.referralportal.model.EMPLOYEE;
import com.vaibhav.minion.referralportal.repository.AuthRepository;
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
    private AuthRepository authRepository;


    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        LoginResponse loginResponse;// = null;

        EMPLOYEE employee = authRepository.loginUser(loginRequest);

        if (employee != null) {
            loginResponse = new LoginResponse(employee.getEmployeeId(), employee.getEmployeeRole(), "SUCCESS");
        } else {
            loginResponse = new LoginResponse(loginRequest.getEmployeeId(), null, "FAILED");
        }
        return loginResponse;
    }

    @Override
    public RegisterReposne registerUser(RegisterRequest registerRequest) {
        RegisterReposne registerReposne;// = null;

        boolean isEmployeeIdExists = authRepository.checkIsEmployeeIdExists(registerRequest.getEmployeeId());

        if (isEmployeeIdExists) {
            registerReposne = new RegisterReposne("EmployeeId " + registerRequest.getEmployeeId() + " already exists, cannot Register", null, null);
        } else {
            try {
                EMPLOYEE employee = authRepository.registerUser(registerRequest);
                registerReposne = new RegisterReposne("SUCCESS", employee.getEmployeeId(), employee.getEmployeeRole());
            } catch (Exception e) {
                registerReposne = new RegisterReposne("FAILED IN  INSERTING", null, null);
            }
        }
        return registerReposne;
    }
}

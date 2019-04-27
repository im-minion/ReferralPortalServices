package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.dao.AuthDao;
import com.vaibhav.minion.referralportal.utility.LoginRequest;
import com.vaibhav.minion.referralportal.utility.LoginResponse;
import com.vaibhav.minion.referralportal.utility.RegisterReposne;
import com.vaibhav.minion.referralportal.utility.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthDao authDao;

    public LoginResponse loginUser(LoginRequest loginRequest) {
        return authDao.loginUser(loginRequest);
    }

    public RegisterReposne registerUser(RegisterRequest registerRequest) {
        return authDao.registerUser(registerRequest);
    }
}

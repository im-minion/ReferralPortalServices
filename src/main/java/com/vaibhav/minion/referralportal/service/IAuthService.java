package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.utility.LoginRequest;
import com.vaibhav.minion.referralportal.utility.LoginResponse;
import com.vaibhav.minion.referralportal.utility.RegisterReposne;
import com.vaibhav.minion.referralportal.utility.RegisterRequest;

public interface IAuthService {

    public LoginResponse loginUser(LoginRequest loginRequest); //GET

    public RegisterReposne registerUser(RegisterRequest registerRequest); //POST
}

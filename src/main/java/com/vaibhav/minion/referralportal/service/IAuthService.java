package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.utility.LoginRequest;
import com.vaibhav.minion.referralportal.utility.LoginResponse;
import com.vaibhav.minion.referralportal.utility.RegisterReposne;
import com.vaibhav.minion.referralportal.utility.RegisterRequest;

public interface IAuthService {

    LoginResponse loginUser(LoginRequest loginRequest); //GET

    RegisterReposne registerUser(RegisterRequest registerRequest); //POST

    String getEmployeeRoleFromID(String userIdFromJWT);
}

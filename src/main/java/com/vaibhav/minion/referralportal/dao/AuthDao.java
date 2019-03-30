package com.vaibhav.minion.referralportal.dao;

import com.vaibhav.minion.referralportal.model.auth.LoginRequest;
import com.vaibhav.minion.referralportal.model.auth.LoginResponse;
import com.vaibhav.minion.referralportal.model.auth.RegisterReposne;
import com.vaibhav.minion.referralportal.model.auth.RegisterRequest;

public interface AuthDao {
    public LoginResponse loginUser(LoginRequest loginRequest); //GET

    public RegisterReposne registerUser(RegisterRequest registerRequest); //POST
}

package com.vaibhav.minion.referralportal.dao;

import com.vaibhav.minion.referralportal.utility.LoginRequest;
import com.vaibhav.minion.referralportal.utility.LoginResponse;
import com.vaibhav.minion.referralportal.utility.RegisterReposne;
import com.vaibhav.minion.referralportal.utility.RegisterRequest;

public interface AuthDao {
    public LoginResponse loginUser(LoginRequest loginRequest); //GET

    public RegisterReposne registerUser(RegisterRequest registerRequest); //POST
}

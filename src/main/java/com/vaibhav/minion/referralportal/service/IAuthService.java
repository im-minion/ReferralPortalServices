package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.utility.RegisterReposne;
import com.vaibhav.minion.referralportal.utility.RegisterRequest;

public interface IAuthService {

    RegisterReposne registerUser(RegisterRequest registerRequest); //POST
}

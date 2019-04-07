package com.vaibhav.minion.referralportal.repository;

import com.vaibhav.minion.referralportal.dao.AuthDao;
import com.vaibhav.minion.referralportal.model.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthRepository implements AuthDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String EMPLOYEE_COLLECTION = "EMPLOYEE";
    private static final String DEFAULT_ROLE = "EMPLOYEE";

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        Criteria loginEmployeeIdCriteria = new Criteria("employeeId").is(loginRequest.getEmployeeId());
        Criteria loginPasswordCriteria = new Criteria("encryptedPassword").is(loginRequest.getPassword());
        Query query = new Query();
        query.addCriteria(loginEmployeeIdCriteria).addCriteria(loginPasswordCriteria);

        List<EMPLOYEE> employee = mongoTemplate.find(query, EMPLOYEE.class, EMPLOYEE_COLLECTION);
        if (employee.size() == 1) {
            loginResponse.setMessage("SUCCESS");
            loginResponse.setEmployeeId(employee.get(0).getEmployeeId());
            loginResponse.setEmployeeRole(employee.get(0).getEmployeeRole());
        } else {
            loginResponse.setMessage("FAILED");
            loginResponse.setEmployeeRole(loginRequest.getEmployeeId());
            loginResponse.setEmployeeRole(null);
        }
        return loginResponse;
    }

    @Override
    public RegisterReposne registerUser(RegisterRequest registerRequest) {
        RegisterReposne registerReposne = null;
        boolean isEmployeeIdExists = checkIsEmployeeIdExsists(registerRequest.getEmployeeId());
        if (isEmployeeIdExists) {
            registerReposne = new RegisterReposne("EmployeeId " + registerRequest.getEmployeeId() + " already exists, cannot Register", null, null);
        } else {
            try {

                EMPLOYEE employee = new EMPLOYEE();
                employee.setEmployeeId(registerRequest.getEmployeeId());
                employee.setEncryptedPassword(registerRequest.getPassword());
                employee.setEmployeeRole(DEFAULT_ROLE);
                mongoTemplate.insert(employee);
                registerReposne = new RegisterReposne("SUCCESS", employee.getEmployeeId(), employee.getEmployeeRole());
            } catch (Exception e) {
                registerReposne = new RegisterReposne("FAILED IN  INSERTING", null, null);
            }
        }

        return registerReposne;
    }

    private boolean checkIsEmployeeIdExsists(String employeeId) {
        Criteria registerEmployeeIdCriteria = new Criteria("employeeId").is(employeeId);
        Query query = new Query();
        query.addCriteria(registerEmployeeIdCriteria);
        List<EMPLOYEE> employee = mongoTemplate.find(query, EMPLOYEE.class, EMPLOYEE_COLLECTION);
        return employee.size() > 0;
    }
}

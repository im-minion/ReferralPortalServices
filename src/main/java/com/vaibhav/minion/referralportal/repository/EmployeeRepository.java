package com.vaibhav.minion.referralportal.repository;

import com.mongodb.client.result.UpdateResult;
import com.vaibhav.minion.referralportal.model.EMPLOYEE;
import com.vaibhav.minion.referralportal.utility.ChangeRoleRequest;
import com.vaibhav.minion.referralportal.utility.ChangeRoleResponse;
import com.vaibhav.minion.referralportal.utility.LoginRequest;
import com.vaibhav.minion.referralportal.utility.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final String EMPLOYEE_COLLECTION = "EMPLOYEE";

    private static final String DEFAULT_ROLE = "EMPLOYEE";

    public ChangeRoleResponse changeEmployeeRole(ChangeRoleRequest changeRoleRequest) {
        try {
            Update update = new Update();
            update.set("employeeRole", changeRoleRequest.getEmployeeNewRole());
            Criteria jobIdCriteria = new Criteria("employeeId").is(changeRoleRequest.getEmployeeId());
            Query query = new Query();
            query.addCriteria(jobIdCriteria);
            UpdateResult updateResult = mongoTemplate.updateFirst(query, update, EMPLOYEE.class, EMPLOYEE_COLLECTION);
            if (updateResult.getMatchedCount() == 1 && updateResult.getModifiedCount() == 1) {
                return new ChangeRoleResponse("Successfully Changed!", changeRoleRequest.getEmployeeId(), changeRoleRequest.getEmployeeNewRole());
            } else {
                return new ChangeRoleResponse("Failed to update", changeRoleRequest.getEmployeeId(), "NA");
            }
        } catch (Exception e) {
            return new ChangeRoleResponse("Failed to update", changeRoleRequest.getEmployeeId(), "NA");
        }
    }

    public boolean checkIsEmployeeIdExists(String employeeId) {
        Criteria registerEmployeeIdCriteria = new Criteria("employeeId").is(employeeId);
        Query query = new Query();
        query.addCriteria(registerEmployeeIdCriteria);
        List<EMPLOYEE> employee = mongoTemplate.find(query, EMPLOYEE.class, EMPLOYEE_COLLECTION);
        return employee.size() > 0;
    }

    public EMPLOYEE registerUser(RegisterRequest registerRequest) {
        EMPLOYEE employee = new EMPLOYEE();
        employee.setEmployeeId(registerRequest.getEmployeeId());
        employee.setEncryptedPassword(passwordEncoder.encode(registerRequest.getPassword()));
        employee.setEmployeeRole(DEFAULT_ROLE);
        return mongoTemplate.insert(employee, EMPLOYEE_COLLECTION);
    }

    public EMPLOYEE findByUsernameOrEmail(String employeeId) {
        Criteria loginEmployeeIdCriteria = new Criteria("employeeId").is(employeeId);
        Query query = new Query();
        query.addCriteria(loginEmployeeIdCriteria);
        return mongoTemplate.findOne(query, EMPLOYEE.class, EMPLOYEE_COLLECTION);
    }

    public EMPLOYEE findById(String employeeId) {
        Criteria loginEmployeeIdCriteria = new Criteria("employeeId").is(employeeId);
        Query query = new Query();
        query.addCriteria(loginEmployeeIdCriteria);
        return mongoTemplate.findOne(query, EMPLOYEE.class, EMPLOYEE_COLLECTION);
    }

    public List<EMPLOYEE> getAllEmployees() {
        return mongoTemplate.findAll(EMPLOYEE.class);
    }
}

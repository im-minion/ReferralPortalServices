package com.vaibhav.minion.referralportal.repository;

import com.mongodb.client.result.UpdateResult;
import com.vaibhav.minion.referralportal.model.EMPLOYEE;
import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
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

//    private static final String REFERRALS_COLLECTION = "REFERRALS";

    private static final String EMPLOYEE_COLLECTION = "EMPLOYEE";

    private static final String DEFAULT_ROLE = "EMPLOYEE";

//    private static final String JOBS_COLLECTION = "JOBS";

//    public List<JOBS> getAllOpenJobs() {
//        Criteria criteriaJobVisibility = new Criteria("jobVisibility").is(true);
//        Criteria criteriaJobStatus = new Criteria("jobStatus").is("OPEN");
//        Query query = new Query();
//        query.addCriteria(criteriaJobVisibility).addCriteria(criteriaJobStatus);
//        return mongoTemplate.find(query, JOBS.class, JOBS_COLLECTION);
//    }

//    public List<REFERRALS> getReferralsOfEmployeeId(String employeeId) {
//        Query query = new Query();
//        Criteria referredByCriteria = new Criteria("referredBy").is(employeeId);
//        query.addCriteria(referredByCriteria);
//        return mongoTemplate.find(query, REFERRALS.class, REFERRALS_COLLECTION);
//    }

//    public REFERRALS addReferral(REFERRALS referrals) {
//        return mongoTemplate.insert(referrals, REFERRALS_COLLECTION);
//    }

//    public boolean isReferralEmailIdExists(String referralEmailId) {
//        Criteria emailIdCriteria = new Criteria("referralEmailId").is(referralEmailId);
//        Query query = new Query();
//        query.addCriteria(emailIdCriteria);
//        REFERRALS referral = mongoTemplate.findOne(query, REFERRALS.class, REFERRALS_COLLECTION);
//        return referral != null;
//    }

    // **********
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

    public EMPLOYEE loginUser(LoginRequest loginRequest) {
        Criteria loginEmployeeIdCriteria = new Criteria("employeeId").is(loginRequest.getEmployeeId());
        Criteria loginPasswordCriteria = new Criteria("encryptedPassword").is(loginRequest.getPassword());
        Query query = new Query();
        query.addCriteria(loginEmployeeIdCriteria).addCriteria(loginPasswordCriteria);
        return mongoTemplate.findOne(query, EMPLOYEE.class, EMPLOYEE_COLLECTION);
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
}

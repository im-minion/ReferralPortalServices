//package com.vaibhav.minion.referralportal.repository;
//
//import com.vaibhav.minion.referralportal.model.EMPLOYEE;
//import com.vaibhav.minion.referralportal.utility.LoginRequest;
//import com.vaibhav.minion.referralportal.utility.RegisterRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class AuthRepository {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    private static final String EMPLOYEE_COLLECTION = "EMPLOYEE";
//    private static final String DEFAULT_ROLE = "EMPLOYEE";
//
//    public EMPLOYEE loginUser(LoginRequest loginRequest) {
//        Criteria loginEmployeeIdCriteria = new Criteria("employeeId").is(loginRequest.getEmployeeId());
//        Criteria loginPasswordCriteria = new Criteria("encryptedPassword").is(loginRequest.getPassword());
//        Query query = new Query();
//        query.addCriteria(loginEmployeeIdCriteria).addCriteria(loginPasswordCriteria);
//        return mongoTemplate.findOne(query, EMPLOYEE.class, EMPLOYEE_COLLECTION);
//    }
//
//    public boolean checkIsEmployeeIdExists(String employeeId) {
//        Criteria registerEmployeeIdCriteria = new Criteria("employeeId").is(employeeId);
//        Query query = new Query();
//        query.addCriteria(registerEmployeeIdCriteria);
//        List<EMPLOYEE> employee = mongoTemplate.find(query, EMPLOYEE.class, EMPLOYEE_COLLECTION);
//        return employee.size() > 0;
//    }
//
//    public EMPLOYEE registerUser(RegisterRequest registerRequest) {
//        EMPLOYEE employee = new EMPLOYEE();
//        employee.setEmployeeId(registerRequest.getEmployeeId());
//        employee.setEncryptedPassword(passwordEncoder.encode(registerRequest.getPassword()));
//        employee.setEmployeeRole(DEFAULT_ROLE);
//        return mongoTemplate.insert(employee,EMPLOYEE_COLLECTION);
//    }
//
//    public EMPLOYEE findByUsernameOrEmail(String usernameOrEmail, String employeeId) {
//        Criteria loginEmployeeIdCriteria = new Criteria("employeeId").is(employeeId);
//        Query query = new Query();
//        query.addCriteria(loginEmployeeIdCriteria);
//        return mongoTemplate.findOne(query, EMPLOYEE.class, EMPLOYEE_COLLECTION);
//    }
//
//    public EMPLOYEE findById(String employeeId) {
//        Criteria loginEmployeeIdCriteria = new Criteria("employeeId").is(employeeId);
//        Query query = new Query();
//        query.addCriteria(loginEmployeeIdCriteria);
//        return mongoTemplate.findOne(query, EMPLOYEE.class, EMPLOYEE_COLLECTION);
//    }
//}
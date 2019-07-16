package com.vaibhav.minion.referralportal.repository;

import com.mongodb.client.result.UpdateResult;
import com.vaibhav.minion.referralportal.model.EMPLOYEE;
import com.vaibhav.minion.referralportal.utility.ChangeRoleRequest;
import com.vaibhav.minion.referralportal.utility.ChangeRoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    private static final String EMPLOYEE_COLLECTION = "EMPLOYEE";

    public ChangeRoleResponse changeEmployeeRole(ChangeRoleRequest changeRoleRequest) {
        try {
            Update update = new Update();
            update.set("employeeRole", changeRoleRequest.getEmployeeNewRole());
            Criteria jobIdCriteria = new Criteria("employeeId").is(changeRoleRequest.getEmployeeId());
            Query query = new Query();
            query.addCriteria(jobIdCriteria);
            UpdateResult updateResult = mongoTemplate.updateFirst(query, update, EMPLOYEE.class, EMPLOYEE_COLLECTION);
            if (updateResult.getMatchedCount() == 1 && updateResult.getModifiedCount() == 1){
                return new ChangeRoleResponse("Successfully Changed!", changeRoleRequest.getEmployeeId(),changeRoleRequest.getEmployeeNewRole());
            }else {
                return new ChangeRoleResponse("Failed to update", changeRoleRequest.getEmployeeId(),"NA");
            }
        } catch (Exception e) {
            return new ChangeRoleResponse("Failed to update", changeRoleRequest.getEmployeeId(),"NA");
        }
    }
}

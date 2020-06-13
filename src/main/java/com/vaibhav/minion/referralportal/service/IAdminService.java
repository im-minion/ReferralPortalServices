package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.model.EMPLOYEE;
import com.vaibhav.minion.referralportal.utility.ChangeRoleRequest;
import com.vaibhav.minion.referralportal.utility.ChangeRoleResponse;

import java.util.List;

public interface IAdminService {
    ChangeRoleResponse changeEmployeeRole(ChangeRoleRequest changeRoleRequest);

    List<EMPLOYEE> getAllEmployees();
}

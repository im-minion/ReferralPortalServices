package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.utility.ChangeRoleRequest;
import com.vaibhav.minion.referralportal.utility.ChangeRoleResponse;

public interface IAdminService {
    ChangeRoleResponse changeEmployeeRole(ChangeRoleRequest changeRoleRequest);
}

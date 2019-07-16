package com.vaibhav.minion.referralportal.serviceImpl;

import com.vaibhav.minion.referralportal.repository.AdminRepository;
import com.vaibhav.minion.referralportal.service.IAdminService;
import com.vaibhav.minion.referralportal.utility.ChangeRoleRequest;
import com.vaibhav.minion.referralportal.utility.ChangeRoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public ChangeRoleResponse changeEmployeeRole(ChangeRoleRequest changeRoleRequest) {
       return adminRepository.changeEmployeeRole(changeRoleRequest);
    }
}

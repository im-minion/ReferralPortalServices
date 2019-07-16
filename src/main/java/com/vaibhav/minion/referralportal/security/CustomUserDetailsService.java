package com.vaibhav.minion.referralportal.security;

import com.vaibhav.minion.referralportal.model.EMPLOYEE;
import com.vaibhav.minion.referralportal.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        EMPLOYEE user = employeeRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
//                .orElseThrow(() ->
//                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
//                );
//        TODO EXCEPTION HANDLE

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(String employeeId) {
        EMPLOYEE user = employeeRepository.findById(employeeId);
//
//                .orElseThrow(
//                () -> new UsernameNotFoundException("User not found with id : " + id)
//        );
//        TODO EXCEPTION HANDLE

        return UserPrincipal.create(user);
    }
}

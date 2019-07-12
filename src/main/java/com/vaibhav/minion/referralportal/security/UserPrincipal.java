package com.vaibhav.minion.referralportal.security;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vaibhav.minion.referralportal.model.EMPLOYEE;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserPrincipal implements UserDetails {
    private String id;

    private String employeeId;

    @JsonIgnore
    private String employeeRole;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String id, String employeeId, String employeeRole, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeRole = employeeRole;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(EMPLOYEE user) {
//        List<GrantedAuthority> authorities = user.getEmployeeRole().stream().map(employeeRole ->
//                new SimpleGrantedAuthority(employeeRole.getName().name())
//        ).collect(Collectors.toList());
//        TODO : DEBUG AND VALIDATE FOLLOWING
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority g = new SimpleGrantedAuthority(user.getEmployeeRole());
        authorities.add(g);
//                Collections.singletonList(new SimpleGrantedAuthority(user.getEmployeeRole()));

        return new UserPrincipal(
                user.getId(),
                user.getEmployeeId(),
                user.getEmployeeRole(),
                user.getEncryptedPassword(),
                authorities
        );
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getId() {
        return id;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    @Override
    public String getUsername() {
        return employeeId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
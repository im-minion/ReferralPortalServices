package com.vaibhav.minion.referralportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class ReferralPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReferralPortalApplication.class, args);
    }

}

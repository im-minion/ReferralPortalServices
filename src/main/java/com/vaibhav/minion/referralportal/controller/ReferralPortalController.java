package com.vaibhav.minion.referralportal.controller;

import com.vaibhav.minion.referralportal.model.InsertJobRequest;
import com.vaibhav.minion.referralportal.model.InsertJobResponse;
import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.service.EmployeeService;
import com.vaibhav.minion.referralportal.service.HMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rp")
public class ReferralPortalController {

    @Autowired
    private HMService hmService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/hm/insertJob", consumes = "application/json", produces = "application/json")
    public ResponseEntity<InsertJobResponse> insertJob(@RequestBody InsertJobRequest insertJobRequest) {
        InsertJobResponse insertJobResponse = hmService.insertJob(insertJobRequest);
        return ResponseEntity.ok().body(insertJobResponse);
    }

    @GetMapping(value = "/hm/getOpenJob", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<JOBS>> getOpenJobs(@RequestParam("employeeId") String employeeId) {
        List<JOBS> openJobsList = hmService.getOpenJobs(employeeId);
        return ResponseEntity.ok().body(openJobsList);
    }


}

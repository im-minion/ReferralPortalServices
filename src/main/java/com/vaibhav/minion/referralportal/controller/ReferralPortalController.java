package com.vaibhav.minion.referralportal.controller;

import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import com.vaibhav.minion.referralportal.service.IAdminService;
import com.vaibhav.minion.referralportal.service.IEmployeeService;
import com.vaibhav.minion.referralportal.service.IHMService;
import com.vaibhav.minion.referralportal.service.IHRService;
import com.vaibhav.minion.referralportal.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rp")
@CrossOrigin("*")
public class ReferralPortalController {

    @Autowired
    private IHMService hmService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IHRService hrService;

    @Autowired
    private IAdminService adminService;

    @GetMapping(value = "/employee/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok().body("Hi employee!");
    }

    @GetMapping(value = "/hm/ping")
    public ResponseEntity<String> pingHM() {
        return ResponseEntity.ok().body("Hi HM!");
    }

    @GetMapping(value = "/hr/ping")
    public ResponseEntity<String> pingHR() {
        return ResponseEntity.ok().body("Hi HR!");
    }

    @GetMapping(value = "/admin/ping")
    public ResponseEntity<String> pingAdmin() {
        return ResponseEntity.ok().body("Hi Admin!");
    }

    /*********************************************_HM_***********************************************************/

    @PostMapping(value = "/hm/insertJob", consumes = "application/json", produces = "application/json")
    public ResponseEntity<InsertJobResponse> insertJob(@RequestBody InsertJobRequest insertJobRequest) {
        InsertJobResponse insertJobResponse = hmService.insertJob(insertJobRequest);
        return ResponseEntity.ok().body(insertJobResponse);
    }

    @GetMapping(value = "/hm/getOpenJob", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<JOBS>> getOpenJobs(@RequestParam("employeeId") String employeeId) {
        List<JOBS> openJobsList = hmService.getAllJobsForHm(employeeId);
        return ResponseEntity.ok().body(openJobsList);
    }

    @GetMapping(value = "/hm/getReferrals", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<REFERRALS>> getReferrals(@RequestParam("jobId") Double jobId) {
        List<REFERRALS> referralsList = hmService.getReferralsFromJobId(jobId);
        return ResponseEntity.ok().body(referralsList);
    }

    @GetMapping(value = "/hm/updateJobVisibility", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UpdateJobVisibilityResponse> updateJobVisibility(@RequestParam("jobId") Double jobId) {
        UpdateJobVisibilityResponse updateJobVisibilityResponse = hmService.updateJobVisibility(jobId);
        return ResponseEntity.ok().body(updateJobVisibilityResponse);
    }

    @PostMapping(value = "/hm/updateJobStatus", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UpdateJobStatusResponse> updateJobStatus(@RequestBody UpdateJobStatusRequest updateJobStatusRequest) {
        UpdateJobStatusResponse updateJobStatusResponse = hmService.updateJobStatus(updateJobStatusRequest);
        return ResponseEntity.ok().body(updateJobStatusResponse);
    }

    @PostMapping(value = "/hm/updateReferralsStatus", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UpdateReferralStatusResponse> updateReferralStatus(@RequestBody UpdateReferralStatusRequest updateReferralStatusRequest) {
        UpdateReferralStatusResponse updateReferralStatusResponse = hmService.updateReferralStatus(updateReferralStatusRequest);
        return ResponseEntity.ok().body(updateReferralStatusResponse);
    }


    /*********************************************_EMPLOYEE_***********************************************************/

    @GetMapping(value = "/employee/getOpenJob", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<JOBS>> getOpenAllJobs() {
        List<JOBS> openJobsList = employeeService.getAllOpenJobs();
        return ResponseEntity.ok().body(openJobsList);
    }

    @PostMapping(value = "/employee/addReferral", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AddReferralResponse> addReferral(@RequestParam("file") String resume, @RequestBody AddReferralRequest addReferralRequest) {
        AddReferralResponse referralResponse = employeeService.addReferral(resume, addReferralRequest);
        return ResponseEntity.ok().body(referralResponse);
    }

    @GetMapping(value = "/employee/getReferralsByEmployeeId", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<REFERRALS>> getReferrals(@RequestParam("employeeId") String employeeId) {
        List<REFERRALS> employeeList = employeeService.getReferralsOfEmployeeId(employeeId);
        return ResponseEntity.ok().body(employeeList);
    }

    /*********************************************_HR_***********************************************************/

    @GetMapping(value = "/hr/getReferralsAtHr", produces = "application/json")
    public ResponseEntity<List<REFERRALS>> getReferralsAtHr() {
        List<REFERRALS> referralsList = hrService.getReferralsAtHr();
        return ResponseEntity.ok().body(referralsList);
    }

    @PostMapping(value = "/hr/updateReferralsStatus", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UpdateReferralStatusResponse> updateReferralStatusHR(@RequestBody UpdateReferralStatusRequest updateReferralStatusRequest) {
        UpdateReferralStatusResponse updateReferralStatusResponse = hmService.updateReferralStatus(updateReferralStatusRequest);
        return ResponseEntity.ok().body(updateReferralStatusResponse);
    }

    @GetMapping(value = "/hr/getAllReferrals", produces = "application/json")
    public ResponseEntity<List<REFERRALS>> getAllReferralsForHr() {
        List<REFERRALS> referralsList = hrService.getAllReferralsForHr();
        return ResponseEntity.ok().body(referralsList);
    }

    /*********************************************_ADMIN_***********************************************************/

    @PostMapping(value = "/admin/changeRole")
    public ResponseEntity<ChangeRoleResponse> changeEmployeeRole(@RequestBody ChangeRoleRequest changeRoleRequest) {
        ChangeRoleResponse changeRoleResponse = adminService.changeEmployeeRole(changeRoleRequest);
        return ResponseEntity.ok().body(changeRoleResponse);
    }
}

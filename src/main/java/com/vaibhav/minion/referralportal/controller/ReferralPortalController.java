package com.vaibhav.minion.referralportal.controller;

import com.google.gson.Gson;
import com.vaibhav.minion.referralportal.model.EMPLOYEE;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.apache.tomcat.util.http.fileupload.IOUtils.copy;


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

    @GetMapping(value = "/hm/getOpenJobs", produces = "application/json")
    public ResponseEntity<List<JOBS>> getOpenJobs(@RequestParam("employeeId") String employeeId) {
        List<JOBS> openJobsList = hmService.getAllJobsCreatedByHm(employeeId);
        return ResponseEntity.ok().body(openJobsList);
    }

    @GetMapping(value = "/hm/getReferrals", produces = "application/json")
    public ResponseEntity<List<REFERRALS>> getHmReferrals(@RequestParam("jobId") String jobId) {
        List<REFERRALS> referralsList = hmService.getReferralsFromJobId(jobId);
        return ResponseEntity.ok().body(referralsList);
    }

    @PutMapping(value = "/hm/updateJob")
    public ResponseEntity<?> updateJob(@RequestBody UpdateJobRequest updateJobRequest){
        return ResponseEntity.ok().body(hmService.updateJob(updateJobRequest));
    }

    @PostMapping(value = "/hm/updateReferralsStatus", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UpdateReferralStatusResponse> updateReferralStatus(@RequestBody UpdateReferralStatusRequest updateReferralStatusRequest) {
        UpdateReferralStatusResponse updateReferralStatusResponse = hmService.updateReferralStatus(updateReferralStatusRequest);
        return ResponseEntity.ok().body(updateReferralStatusResponse);
    }

    @GetMapping(value = "/hm/getAllReferralsAtHM", produces = "application/json")
    public ResponseEntity<List<REFERRALS>> getAllReferralsAtHm() {
        //TODO : this is hr service convert
        return getAllReferralsForHr();
    }

    /*********************************************_EMPLOYEE_***********************************************************/

    @GetMapping(value = "/employee/getOpenJobs", produces = "application/json")
    public ResponseEntity<List<JOBS>> getOpenAllJobs() {
        List<JOBS> openJobsList = employeeService.getAllOpenJobs();
        return ResponseEntity.ok().body(openJobsList);
    }

    @GetMapping(value = "/employee/getJobByJobId", produces = "application/json")
    public ResponseEntity<JOBS> getJobByJobId(@RequestParam final String jobId) {
        JOBS openJobsList = employeeService.getJobByJobId(jobId);
        return ResponseEntity.ok().body(openJobsList);
    }

    @PostMapping(value = "/employee/addReferral")
    public ResponseEntity<AddReferralResponse> addReferral(@RequestParam final MultipartFile file, @RequestParam("myjson") String json) {
        Gson gson = new Gson();
        AddReferralRequest addReferralRequest = gson.fromJson(json, AddReferralRequest.class);
        AddReferralResponse referralResponse = employeeService.addReferral(file, addReferralRequest);
        return ResponseEntity.ok().body(referralResponse);
    }

    @GetMapping(value = "/employee/getReferralsByEmployeeId", produces = "application/json")
    public ResponseEntity<List<REFERRALS>> getEmployeeReferrals(@RequestParam("employeeId") String employeeId) {
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

    @GetMapping(value = "/admin/getAllEmployees")
    public ResponseEntity<List<EMPLOYEE>> getAllEmployees() {
        List<EMPLOYEE> employees = adminService.getAllEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @PostMapping(value = "/admin/changeRole")
    public ResponseEntity<ChangeRoleResponse> changeEmployeeRole(@RequestBody ChangeRoleRequest changeRoleRequest) {
        ChangeRoleResponse changeRoleResponse = adminService.changeEmployeeRole(changeRoleRequest);
        return ResponseEntity.ok().body(changeRoleResponse);
    }

    /*****************************************_GET_FILE_BY_FILE_ID**********************************************************/
    @GetMapping(value = "/employee/getFileById", produces = "application/pdf")
    public ResponseEntity<?> getFileById(@RequestParam String fileId, HttpServletResponse response) {
        InputStream inputStream;
        try {
            inputStream = employeeService.getFileByID(fileId).getInputStream();
            response.setContentType("application/pdf");
            copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            return ResponseEntity.ok().body(null);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping(value = "/employee/getAnalyticalInfo", produces = "application/json")
    public ResponseEntity<?> getAnalyticalInfo(@RequestParam final String employeeId, @RequestParam final String employeeRole) {
        AnalyticalInfoResponse analyticalInfoResponse = employeeService.getAnalyticalInfo(employeeId, employeeRole);
        return ResponseEntity.ok().body(analyticalInfoResponse);
    }
}

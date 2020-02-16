package com.vaibhav.minion.referralportal.serviceImpl;

import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import com.vaibhav.minion.referralportal.repository.EmployeeRepository;
import com.vaibhav.minion.referralportal.repository.JobsRepository;
import com.vaibhav.minion.referralportal.repository.ReferralsRepository;
import com.vaibhav.minion.referralportal.service.IHMService;
import com.vaibhav.minion.referralportal.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HMServiceImpl implements IHMService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private ReferralsRepository referralsRepository;

    @Override
    public InsertJobResponse insertJob(InsertJobRequest insertJobRequest) {
        InsertJobResponse insertJobResponse;
        try {
            JOBS jobs = new JOBS();
            if (!jobsRepository.isJobIdExists(insertJobRequest.getJobId())) {
                jobs.setJobId(insertJobRequest.getJobId());
                jobs.setJobDescription(insertJobRequest.getJobDescription());
                jobs.setJobTitle(insertJobRequest.getJobTitle());
                jobs.setYeo(insertJobRequest.getYoe());
                jobs.setCreatedByEmployeeId(insertJobRequest.getCreatedByEmployeeId());
                jobs.setPrimarySkill(insertJobRequest.getPrimarySkill());
                jobs.setSecondarySkill(insertJobRequest.getSecondarySkill());
                jobsRepository.insertJob(jobs);
                insertJobResponse = new InsertJobResponse(jobs.getId(), true, "success");
                //return insertJobResponse;
            } else {
                insertJobResponse = new InsertJobResponse(null, false, "JobId already exists!");
            }
            return insertJobResponse;
        } catch (Exception e) {
            insertJobResponse = new InsertJobResponse(null, false, e.getMessage());
            return insertJobResponse;
        }
    }


    @Override
    public List<JOBS> getAllJobsCreatedByHm(String employeeId) {
        return jobsRepository.getAllJobsCreatedByHm(employeeId);
    }

    @Override
    public List<REFERRALS> getReferralsFromJobId(String jobId) {
        return referralsRepository.getReferralsFromJobId(jobId);
    }

    @Override
    public UpdateJobVisibilityResponse updateJobVisibility(String jobId) {
        try {
            boolean currentVisibility = jobsRepository.getCurrentJobVisibility(jobId);
            jobsRepository.updateJobVisibility(jobId, currentVisibility);
            return new UpdateJobVisibilityResponse("Success", !currentVisibility);
        } catch (Exception e) {
            return new UpdateJobVisibilityResponse("Failure", false);
        }
    }

    @Override
    public UpdateJobStatusResponse updateJobStatus(UpdateJobStatusRequest updateJobStatusRequest) {
        return jobsRepository.updateJobStatus(updateJobStatusRequest);
    }

    @Override
    public UpdateReferralStatusResponse updateReferralStatus(UpdateReferralStatusRequest updateReferralStatusRequest) {
        try {

            ReferralStatusReasons referralStatusReasons = new ReferralStatusReasons();
            referralStatusReasons.setLevel(updateReferralStatusRequest.getCurrentLevel());
            referralStatusReasons.setStatus(updateReferralStatusRequest.getStatus()); //either ACCEPTED or REJECTED
            referralStatusReasons.setReasonToUpdate(updateReferralStatusRequest.getReason());

            LevelStatus levelStatus = getNextLevelStatus(updateReferralStatusRequest.getCurrentLevel(), updateReferralStatusRequest.getStatus());

            referralsRepository.updateReferralStatus(levelStatus, updateReferralStatusRequest, referralStatusReasons);

            return new UpdateReferralStatusResponse(levelStatus.getNextLevel(), "SUCCESS", true);
        } catch (Exception e) {
            return new UpdateReferralStatusResponse("", "FAILURE", false);
        }

    }

    @Override
    public boolean updateJob(UpdateJobRequest updateJobRequest) {
        return jobsRepository.update(updateJobRequest);
    }


    private LevelStatus getNextLevelStatus(String currentLevel, String status) {
        LevelStatus levelStatus = new LevelStatus();
        String nextLevel = null;
        String nextStatus = "PENDING";
        if (status.equals("ACCEPTED")) {
            switch (currentLevel) {
                case "RESUME_SCREENING":
                    nextLevel = "L1";
                    break;
                case "L1":
                    nextLevel = "L2";
                    break;
                case "L2":
                    nextLevel = "HR";
                    break;
                case "HR":
                    nextLevel = "HR";
                    nextStatus = "ACCEPTED";
                    break;
                default:
                    break;
            }
        } else if (status.equals("REJECTED")) {
            nextLevel = currentLevel;
            nextStatus = "REJECTED";
        }
        levelStatus.setNextLevel(nextLevel);
        levelStatus.setNextStatus(nextStatus);
        return levelStatus;
    }
}

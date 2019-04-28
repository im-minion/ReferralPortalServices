package com.vaibhav.minion.referralportal.serviceImpl;

import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import com.vaibhav.minion.referralportal.repository.HMRepository;
import com.vaibhav.minion.referralportal.service.IHMService;
import com.vaibhav.minion.referralportal.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HMServiceImpl implements IHMService {

    @Autowired
    private HMRepository hmRepository;

    @Override
    public InsertJobResponse insertJob(InsertJobRequest insertJobRequest) {
        InsertJobResponse insertJobResponse;
        try {
            JOBS jobs = new JOBS();
            if (!hmRepository.isJobIdExists(insertJobRequest.getJobId())) {
                jobs.setJobId(insertJobRequest.getJobId());
                jobs.setJobDescription(insertJobRequest.getJobDescription());
                jobs.setJobTitle(insertJobRequest.getJobTitle());
                jobs.setYeo(insertJobRequest.getYoe());
                jobs.setCreatedByEmployeeId(insertJobRequest.getCreatedByEmployeeId());
                jobs.setPrimarySkill(insertJobRequest.getPrimarySkill());
                jobs.setSecondarySkill(insertJobRequest.getSecondarySkill());
                hmRepository.insertJob(jobs);
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
    public List<JOBS> getAllJobsForHm(String employeeId) {
        return hmRepository.getAllJobsForHm(employeeId);
    }

    @Override
    public List<REFERRALS> getReferralsFromJobId(Double jobId) {
        return hmRepository.getReferralsFromJobId(jobId);
    }

    @Override
    public UpdateJobVisibilityResponse updateJobVisibility(Double jobId) {
        try {
            boolean currentVisibility = hmRepository.getCurrentJobVisibility(jobId);
            hmRepository.updateJobVisibility(jobId, currentVisibility);
            return new UpdateJobVisibilityResponse("Success", !currentVisibility);
        } catch (Exception e) {
            return new UpdateJobVisibilityResponse("Failure", false);
        }
    }

    @Override
    public UpdateJobStatusResponse updateJobStatus(UpdateJobStatusRequest updateJobStatusRequest) {
        return hmRepository.updateJobStatus(updateJobStatusRequest);
    }

    @Override
    public UpdateReferralStatusResponse updateReferralStatus(UpdateReferralStatusRequest updateReferralStatusRequest) {
        try {

            ReferralStatusReasons referralStatusReasons = new ReferralStatusReasons();
            referralStatusReasons.setLevel(updateReferralStatusRequest.getCurrentLevel());
            referralStatusReasons.setStatus(updateReferralStatusRequest.getStatus()); //either ACCEPTED or REJECTED
            referralStatusReasons.setReasonToUpdate(updateReferralStatusRequest.getReason());

            LevelStatus levelStatus = getNextLevelStatus(updateReferralStatusRequest.getCurrentLevel(), updateReferralStatusRequest.getStatus());

            hmRepository.updateReferralStatus(levelStatus, updateReferralStatusRequest, referralStatusReasons);

            return new UpdateReferralStatusResponse(levelStatus.getNextLevel(), "SUCCESS", true);
        } catch (Exception e) {
            return new UpdateReferralStatusResponse("", "FAILURE", false);
        }

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

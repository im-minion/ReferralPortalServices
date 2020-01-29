package com.vaibhav.minion.referralportal.serviceImpl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.vaibhav.minion.referralportal.model.JOBS;
import com.vaibhav.minion.referralportal.model.REFERRALS;
import com.vaibhav.minion.referralportal.repository.EmployeeRepository;
import com.vaibhav.minion.referralportal.repository.JobsRepository;
import com.vaibhav.minion.referralportal.repository.ReferralsRepository;
import com.vaibhav.minion.referralportal.service.IEmployeeService;
import com.vaibhav.minion.referralportal.utility.AddReferralRequest;
import com.vaibhav.minion.referralportal.utility.AddReferralResponse;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private ReferralsRepository referralsRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations gridFsOperations;

    @Override
    public List<JOBS> getAllOpenJobs() {
        return jobsRepository.getAllOpenJobs();
    }

    @Override
    public List<REFERRALS> getReferralsOfEmployeeId(String employeeId) {
        return referralsRepository.getReferralsOfEmployeeId(employeeId);
    }

    @Override
    public AddReferralResponse addReferral(MultipartFile file, AddReferralRequest addReferralRequest) {
        AddReferralResponse addReferralResponse;
        try {
            REFERRALS referrals = new REFERRALS();
            if (!referralsRepository.isReferralEmailIdExists(addReferralRequest.getReferralEmailId())) {
                referrals.setReferralEmailId(addReferralRequest.getReferralEmailId());
                referrals.setDob(addReferralRequest.getDob());
                referrals.setJobId(addReferralRequest.getJobId());
                referrals.setPanNumber(addReferralRequest.getPanNumber());
                referrals.setReferDate(addReferralRequest.getReferDate());
                referrals.setReferralName(addReferralRequest.getReferralName());
                referrals.setPrimarySkill(addReferralRequest.getPrimarySkill());
                referrals.setSecondarySkill(addReferralRequest.getSecondarySkill());
                referrals.setResume(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
                DBObject metaData = new BasicDBObject();
//                InputStream inputStream = new FileInputStream("C:\\Users\\vaibhav\\Documents\\Resume\\VaibhavMiniyar_Resume_v2.pdf");
                if(file != null) {
                    InputStream inputStream = file.getInputStream();
//                TODO: HARDCODED THINK If WE CAN DO SOMETHING
                    metaData.put("type", "pdf");
                    Date date = new Date();
                    String fileId = gridFsOperations.store(inputStream, addReferralRequest.getReferralName() + date.getTime(), "application/pdf", metaData).toString();
                    referrals.setResumeV2(fileId);
                } else {
                    throw new FileNotFoundException();
                }
                referrals.setReferredBy(addReferralRequest.getReferredBy());

                REFERRALS r = referralsRepository.addReferral(referrals);

                addReferralResponse = new AddReferralResponse(r.getReferralId(), true, "Referral added successfully!");
//                GridFSFile dbFile = gridFsOperations.findOne(new Query(Criteria.where("_id").is(fileId)));
//                dbFile.writeTo("C:/Users/bahota/Desktop/Local-Disk/reactive-logo.png");
            } else {
                addReferralResponse = new AddReferralResponse(null, false, "Referral Email Id already Exists can't refer!");
            }
            return addReferralResponse;
        } catch (Exception e) {
            addReferralResponse = new AddReferralResponse(null, false, "Exception occurred!" + e.getMessage());
        }
        return addReferralResponse;
    }

    @Override
    public InputStreamResource getFileByID(String fileId) {
        GridFSFile dbFile = gridFsOperations.findOne(new Query(Criteria.where("_id").is(fileId)));
        if (dbFile != null) {
            return gridFsOperations.getResource(dbFile);
        }
        return null;
    }

    @Override
    public JOBS getJobByJobId(String jobId) {
        return jobsRepository.getJobByJobId(jobId);
    }
}

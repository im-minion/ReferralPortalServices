package com.vaibhav.minion.referralportal.serviceImpl;

import com.vaibhav.minion.referralportal.model.REFERRALS;
import com.vaibhav.minion.referralportal.repository.EmployeeRepository;
import com.vaibhav.minion.referralportal.repository.JobsRepository;
import com.vaibhav.minion.referralportal.repository.ReferralsRepository;
import com.vaibhav.minion.referralportal.service.IHRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HRServiceImpl implements IHRService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private ReferralsRepository referralsRepository;

    @Override
    public List<REFERRALS> getReferralsAtHr() {

        return referralsRepository.getReferralsAtHr();
    }

    @Override
    public List<REFERRALS> getAllReferralsForHr() {
        return referralsRepository.getAllReferralsForHr();
    }

//    private List<REFERRALS> filterReferrals(List<REFERRALS> allReferrals) {
////        allReferrals.stream().filter( referral -> {
////            referral.getReferralStatusReasonsList().stream().filter( referralStatusReasons -> {
////                referralStatusReasons.getLevel() == "HR" && referralStatusReasons.ge
////            } );
////        } );
//        List<REFERRALS> referralsAtHr = new ArrayList<>();
//        for (REFERRALS allReferral : allReferrals) {
//            for (int j = 0; j < allReferral.getReferralStatusReasonsList().size(); j++) {
//                if (allReferral.getReferralStatusReasonsList().get(j).getStatus().equals("ACCEPTED")
//                        && allReferral.getReferralStatusReasonsList().get(j).getLevel().equals("L2")) {
//                    referralsAtHr.add(allReferral);
//                }
//            }
//        }
////        allReferrals.stream().filter(referral ->
////                referral.getReferralStatusReasonsList().stream().map(
////                referralStatusReason -> {
////                      if (referralStatusReason.getStatus().equals("ACCEPTED") && referralStatusReason.getLevel().equals("L2")) {
////                          return referral;
////                      } else {
////                          return null;
////                      }
////                }
////
////        ));
//        return referralsAtHr;
//    }
}

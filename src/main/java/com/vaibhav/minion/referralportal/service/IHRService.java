package com.vaibhav.minion.referralportal.service;

import com.vaibhav.minion.referralportal.model.REFERRALS;

import java.util.List;

public interface IHRService {

    List<REFERRALS> getReferralsAtHr();

    List<REFERRALS> getAllReferralsForHr();
}

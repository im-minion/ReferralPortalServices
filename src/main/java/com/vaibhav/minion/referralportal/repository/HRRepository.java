//package com.vaibhav.minion.referralportal.repository;
//
//import com.vaibhav.minion.referralportal.model.REFERRALS;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class HRRepository {
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    private static final String REFERRALS_COLLECTION = "REFERRALS";
//
//    public List<REFERRALS> getReferralsAtHr() {
//        Query query = new Query();
//        query.addCriteria(new Criteria("referralCurrentLevel").is("HR")).addCriteria(new Criteria("referralCurrentStatus").is("PENDING"));
//        return mongoTemplate.find(query, REFERRALS.class, REFERRALS_COLLECTION);
//    }
//
//    public List<REFERRALS> getAllReferralsForHr() {
//        return mongoTemplate.findAll(REFERRALS.class, REFERRALS_COLLECTION);
//    }
//}

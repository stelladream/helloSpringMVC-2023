package kr.ac.hansung.controller;

import kr.ac.hansung.dao.OfferDao;
import kr.ac.hansung.model.Offer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements InitializingBean {

    private final OfferDao offerDao;

    @Autowired
    public DataInitializer(OfferDao offerDao) {
        this.offerDao = offerDao;
    }

    @Override
    public void afterPropertiesSet() {
        Offer offer1 = new Offer("Alice", "alice@hansung.ac.kr", "스프링 프로그래밍이 재미있네요");
        Offer offer2 = new Offer("Bob", "bob@hansung.ac.kr", "Rest API를 설계해요");
        Offer offer3 = new Offer("Charlie", "charlie@hansung.ac.kr", "Back-end 로직에 집중해요");

        offerDao.insert(offer1);
        offerDao.insert(offer2);
        offerDao.insert(offer3);
    }
}
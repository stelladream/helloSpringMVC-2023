package kr.ac.hansung.service;

import kr.ac.hansung.dao.OfferDao;
import kr.ac.hansung.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfferService {

    // service -> dao
    @Autowired
    private OfferDao offerDao;

    public List<Offer> getAllOffers() {
        return offerDao.getOffers();
    }

    public Offer getOfferById(int id) {
        return offerDao.getOfferById(id);
    }

    public void insertOffer(Offer offer) {
        offerDao.insert(offer);
    }

    public void updateOffer(Offer offer) {
        offerDao.update(offer);
    }


    public void deleteOffer(Offer offer) {
        offerDao.delete(offer);
    }
}

package kr.ac.hansung.controller;

import kr.ac.hansung.exception.OfferNotFoundException;
import kr.ac.hansung.model.Offer;
import kr.ac.hansung.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController  // @Controller + @ResponseBody
@RequestMapping("/api/offers")
public class OfferRestController {

    @Autowired
    private OfferService offerService;

    @GetMapping()
    public ResponseEntity<List<Offer>> getOffers() {

        List<Offer> offers = offerService.getAllOffers();

        if(offers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOffer(@PathVariable("id") int id) {

        Offer offer = offerService.getOfferById(id);

        if(offer== null) {
            throw new OfferNotFoundException(id);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createOffer(@RequestBody Offer offer, UriComponentsBuilder ucBuilder) {

        offerService.insertOffer(offer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/offers/{id}").buildAndExpand(offer.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable("id") int id, @RequestBody Offer offer) {

        Offer currentOffer = offerService.getOfferById(id);
        if(currentOffer == null) {
            throw new OfferNotFoundException(id);
        }

        currentOffer.setEmail(offer.getEmail());
        currentOffer.setName(offer.getName());
        currentOffer.setText(offer.getText());

        offerService.updateOffer(currentOffer);

        return new ResponseEntity<>(currentOffer, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable("id") int id) {

        Offer currentOffer = offerService.getOfferById(id);
        if(currentOffer == null) {
           throw new OfferNotFoundException(id);
        }

        offerService.deleteOffer(currentOffer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

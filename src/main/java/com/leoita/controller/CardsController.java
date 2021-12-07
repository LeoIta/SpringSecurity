package com.leoita.controller;

import com.leoita.model.Card;
import com.leoita.model.Customer;
import com.leoita.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @PostMapping("/myCards")
    public List<Card> getCardDetails(@RequestBody Customer customer) {
        List<Card> cards = cardsRepository.findByCustomerId(customer.getId());
        return cards == null ? Collections.emptyList() : cards;
    }

}
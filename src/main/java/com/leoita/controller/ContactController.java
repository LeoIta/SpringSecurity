package com.leoita.controller;

import com.leoita.model.Contact;
import com.leoita.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;

@RestController
public class ContactController {

    private SecureRandom random = SecureRandom.getInstanceStrong();

    @Autowired
    private ContactRepository contactRepository;

    public ContactController() throws NoSuchAlgorithmException {
        // manage NoSuchAlgorithmException
    }

    @PostMapping("/contact")
//    @PreFilter("filterObject.contactName == 'Test'")// PreFilter checks the permissions before method execution
    @PostFilter("filterObject.contactEmail == 'test'")// PostFilter checks the permissions after method execution
    public Contact saveContactInquiryDetails(@RequestBody Contact contact) {
        contact.setContactId(getServiceReqNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
    }

    public String getServiceReqNumber() {
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR"+ranNum;
    }

}
package com.leoita.controller;

import com.leoita.model.Account;
import com.leoita.model.Customer;
import com.leoita.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/myAccount")
    public Account getAccountDetails(@RequestBody Customer customer) {
        Account account = accountRepository.findByCustomerId(customer.getId());
        return account == null ? null : account;
    }

}
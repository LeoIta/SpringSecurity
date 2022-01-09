package com.leoita.controller;

import com.leoita.model.AccountTransactions;
import com.leoita.model.Customer;
import com.leoita.repository.AccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @PostMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestBody Customer customer) {
        List<AccountTransactions> accountTransactions = accountTransactionsRepository.
                findByEmailOrderByTransactionDtDesc(customer.getEmail());
        return accountTransactions == null ? Collections.emptyList() : accountTransactions;
    }

}
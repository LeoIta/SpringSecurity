package com.leoita.controller;

import com.leoita.model.Customer;
import com.leoita.model.Loan;
import com.leoita.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoanRepository loanRepository;

    @PostMapping("/myLoans")
    public List<Loan> getLoanDetails(@RequestBody Customer customer) {
        List<Loan> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customer.getId());
        return loans == null ? Collections.emptyList() : loans;
    }

}
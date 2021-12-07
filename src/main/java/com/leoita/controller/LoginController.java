package com.leoita.controller;

import com.leoita.model.Customer;
import com.leoita.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

public class LoginController {


    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Principal user) {
        List<Customer> customers = customerRepository.findByEmail(user.getName());
        return customers.isEmpty()?null: customers.get(0);
    }

}

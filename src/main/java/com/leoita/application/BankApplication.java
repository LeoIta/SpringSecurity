package com.leoita.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@ComponentScan("com.leoita.controller")
@ComponentScan("com.leoita.config")
@ComponentScan("com.leoita.service")
@EnableJpaRepositories("com.leoita.repository")
@EntityScan("com.leoita.model")
@EnableWebSecurity(debug = true)
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

}

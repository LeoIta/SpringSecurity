package com.leoita.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.leoita.controller")
@ComponentScan("com.leoita.config")
@ComponentScan("com.leoita.service")
@EnableJpaRepositories("com.leoita.repository")
@EntityScan("com.leoita.model")
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

}

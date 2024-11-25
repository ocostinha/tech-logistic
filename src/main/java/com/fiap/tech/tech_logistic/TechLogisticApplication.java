package com.fiap.tech.tech_logistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.fiap.tech.tech_logistic.repository.jpa"})
public class TechLogisticApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechLogisticApplication.class, args);
    }

}

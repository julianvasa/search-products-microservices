package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistrationService {

    public static void main(String[] args) {
        // Tell server to look for registration-server.yml
        System.setProperty("spring.config.name", "registration-server");
        SpringApplication.run(RegistrationService.class, args);
    }
}

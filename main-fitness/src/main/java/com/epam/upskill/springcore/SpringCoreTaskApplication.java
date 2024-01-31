package com.epam.upskill.authenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.epam.upskill.feignclients")
public class SpringCoreTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCoreTaskApplication.class, args);
    }

}

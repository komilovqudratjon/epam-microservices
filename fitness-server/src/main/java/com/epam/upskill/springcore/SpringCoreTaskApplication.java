package com.epam.upskill.springcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCoreTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCoreTaskApplication.class, args);
    }

}

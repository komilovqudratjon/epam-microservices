package com.epam.upskill.springcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {"com.epam.upskill.springcore", "com.epam.upskill.rabbitmq"}
)
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.epam.upskill.feignclients")
public class MainFitness {
    public static void main(String[] args) {
        SpringApplication.run(MainFitness.class, args);
    }

}

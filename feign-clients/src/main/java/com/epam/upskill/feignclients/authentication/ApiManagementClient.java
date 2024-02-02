package com.epam.upskill.feignclients.authentication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "authentication-service",
        url = "http://localhost:8084"
)
//@Service
public interface ApiManagementClient {

    @GetMapping("/auth/v1/check")
    ApiManagementResponse checkAuthorization(@RequestParam("token") String token, @RequestParam("path") String path);
}

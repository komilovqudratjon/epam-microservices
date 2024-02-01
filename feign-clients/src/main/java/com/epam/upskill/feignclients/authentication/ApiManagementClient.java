package com.epam.upskill.feignclients.authentication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "api-management",
        url = "${clients.authentication.url}"
)
@Service
public interface ApiManagementClient {

    @GetMapping("/api/v1/api-keys/{apiKey}/applications/{application}/authorisations")
    ApiManagementResponse isKeyAuthorizedForApplication(
            @PathVariable("apiKey") String apiKey,
            @PathVariable("application") String application
    );
}

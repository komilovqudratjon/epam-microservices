package com.epam.upskill.authenticationservice.controller;

import com.epam.upskill.authenticationservice.service.CheckAuthService;
import com.epam.upskill.feignclients.authentication.ApiManagementResponse;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/check")
@AllArgsConstructor
@Api(tags = "Check")
@Slf4j
public class CheckAuthController {

    private final CheckAuthService checkAuthService;


    @GetMapping()
    public ApiManagementResponse checkAuthorization(@RequestParam("token") String token, @RequestParam("path") String path) {
        try {
            return new ApiManagementResponse(checkAuthService.checkAuth(token, path));
        } catch (Exception e) {
            log.error("Error checking authorization: {}", e.getMessage());
            return new ApiManagementResponse(false);
        }
    }


}

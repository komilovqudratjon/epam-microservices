package com.epam.apigw.security;


import com.epam.upskill.feignclients.authentication.ApiManagementClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service("main")
@AllArgsConstructor
public class ApiJWTAuthorizationCheckerImpl implements ApiJWTAuthorizationChecker {

    private final ApiManagementClient apiManagementClient;

    @Override
    public boolean isAuthorized(String tokenJWT, String applicationName) {
        return apiManagementClient.isKeyAuthorizedForApplication(
                tokenJWT,
                applicationName
        ).isAuthorized();
    }

}

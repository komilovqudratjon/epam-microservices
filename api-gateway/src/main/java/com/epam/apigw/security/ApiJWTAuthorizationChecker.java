package com.epam.apigw.security;

public interface ApiJWTAuthorizationChecker {
    boolean isAuthorized(String apiKey, String path);
}

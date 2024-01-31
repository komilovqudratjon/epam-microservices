package com.epam.upskill.feignclients.authentication;

import lombok.Data;

@Data
public class ApiManagementResponse {
    private final boolean isAuthorized;
}

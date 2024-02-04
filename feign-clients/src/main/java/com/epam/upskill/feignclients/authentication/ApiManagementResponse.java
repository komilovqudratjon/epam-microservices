package com.epam.upskill.feignclients.authentication;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiManagementResponse {
    private boolean isAuthorized;
}

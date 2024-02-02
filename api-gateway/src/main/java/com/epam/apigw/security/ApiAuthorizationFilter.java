package com.epam.apigw.security;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class ApiAuthorizationFilter
        implements GlobalFilter, Ordered {

    private final ApiJWTAuthorizationChecker apiJWTAuthorizationChecker;
    private final AntPathMatcher pathMatcher;

    private final Set<String> openEndpoints;


    public ApiAuthorizationFilter(@Qualifier("main") ApiJWTAuthorizationChecker apiJWTAuthorizationChecker, AntPathMatcher pathMatcher) {
        this.apiJWTAuthorizationChecker = apiJWTAuthorizationChecker;
        this.pathMatcher = pathMatcher;
        this.openEndpoints = Set.of(
                "/fitness/v1/trainings/types",
                "/fitness/swagger-ui/**",
                "/fitness/v3/api-docs",
                "/fitness/v3/api-docs/**",
                "/fitness/swagger-ui.html/**",
                "/workload/swagger-ui/**",
                "/workload/v3/api-docs",
                "/workload/v3/api-docs/**",
                "/workload/swagger-ui.html",
                "/auth/**");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> apiKeyHeader = exchange.getRequest().getHeaders().get("Authorization");
        String path = exchange.getRequest().getPath().value();

        if (openEndpoints.stream().anyMatch(p -> pathMatcher.match(p, path))) {
            return chain.filter(exchange);
        }
        if (!isEmpty(apiKeyHeader) && !apiJWTAuthorizationChecker.isAuthorized(apiKeyHeader.get(0), path)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You're not authorized to access this API");
        }


        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}

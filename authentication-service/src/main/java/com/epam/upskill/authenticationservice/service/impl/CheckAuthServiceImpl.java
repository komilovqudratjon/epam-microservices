package com.epam.upskill.authenticationservice.service.impl;

import com.epam.upskill.authenticationservice.model.RoleName;
import com.epam.upskill.authenticationservice.model.Users;
import com.epam.upskill.authenticationservice.security.JwtUtil;
import com.epam.upskill.authenticationservice.security.UsersDetails;
import com.epam.upskill.authenticationservice.service.CheckAuthService;
import com.epam.upskill.authenticationservice.service.db.MainUserRepository;
import com.epam.upskill.authenticationservice.service.db.UserDatabase;
import com.epam.upskill.authenticationservice.service.impl.mapper.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.HashMap;
import java.util.Set;

/**
 * @description: Service class for managing Training entities.
 * @date: 08 November 2023 $
 * @time: 5:43 AM 47 $
 * @author: Qudratjon Komilov
 */

@Service
//@RequiredArgsConstructor
@Slf4j
public class CheckAuthServiceImpl implements CheckAuthService {

    private final UserDatabase userDatabase;

    private final JwtUtil tokenProvider;

    private final AntPathMatcher pathMatcher;

    private final Set<String> openEndpoints;

    private final HashMap<RoleName, Set<String>> openEndpointsByRole = new HashMap<>();

    {

        openEndpointsByRole.put(RoleName.ROLE_USER, Set.of("/fitness/**"));

        openEndpointsByRole.put(RoleName.ROLE_TRAINEE, Set.of("/fitness/**"));

        openEndpointsByRole.put(RoleName.ROLE_TRAINER, Set.of("/fitness/**"));


        openEndpointsByRole.put(RoleName.ROLE_ADMIN, Set.of("/workload/**", "/auth/**", "/fitness/**"));


        openEndpoints = Set.of(
//                "/fitness/v1/trainings/types",
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

    public CheckAuthServiceImpl(UserDatabase userDatabase, JwtUtil tokenProvider, AntPathMatcher pathMatcher) {
        this.userDatabase = userDatabase;
        this.tokenProvider = tokenProvider;
        this.pathMatcher = pathMatcher;
    }

    @Override
    public boolean checkAuth(String token, String path) {
        try {
            if (isOpenEndpoint(path)) {
                return true;
            }
            String s = tokenProvider.parseJwt(token);
            if (tokenProvider.validateAccessToken(s)) {
                String username = tokenProvider.getUsernameFromJWT(s);
                Users userDetails = userDatabase.findByUsername(username).orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return isOpenEndpointByRole(path, userDetails.getRole()) || isOpenEndpoint(path);
            }

            return false;
        } catch (Exception e) {
            return false;
        }


    }

    private boolean isOpenEndpoint(String path) {
        return openEndpoints.stream().anyMatch(p -> pathMatcher.match(p, path));
    }

    private boolean isOpenEndpointByRole(String path, RoleName role) {
        return openEndpointsByRole.get(role).stream().anyMatch(p -> pathMatcher.match(p, path));
    }

}
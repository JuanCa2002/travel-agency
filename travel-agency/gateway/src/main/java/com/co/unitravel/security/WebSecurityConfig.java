package com.co.unitravel.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.http.HttpMethod;


@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {


    public static final String ADMIN = "admin";
    public static final String USER = "user";
    public static final String ADMIN_ACCOMMODATION = "admin_alojamiento";
    private final JwtAuthConverter jwtAuthConverter;


    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {


        http
                .authorizeExchange( e -> e
                              .pathMatchers("/api/v1/user-management/swagger-ui/index.html/**",
                                      "/api/v1/user-management/swagger-ui/**",
                                      "/api/v1/user-management/v3/api-docs/**",
                                      "/api/v1/user-management/swagger-ui/**",
                                      "/api/v1/user-management/swagger-ui.html",
                                      "/api/v1/user-management/swagger-resources/**",
                                      "/api/v1/user-management/swagger-resources/configuration/ui",
                                      "/api/v1/user-management/swagger-resources/configuration/security",
                                      "/api/v1/user-management/swagger.v1+json/**",
                                      "/api/v1/accommodation-management/swagger-ui/index.html/**",
                                      "/api/v1/accommodation-management/swagger-ui/**",
                                      "/api/v1/accommodation-management/v3/api-docs/**",
                                      "/api/v1/accommodation-management/swagger-ui/**",
                                      "/api/v1/accommodation-management/swagger-ui.html",
                                      "/api/v1/accommodation-management/swagger-resources/**",
                                      "/api/v1/accommodation-management/swagger-resources/configuration/ui",
                                      "/api/v1/accommodation-management/swagger-resources/configuration/security",
                                      "/api/v1/accommodation-management/swagger.v1+json/**",
                                      "/api/v1/reservation-management/swagger-ui/index.html/**",
                                      "/api/v1/reservation-management/swagger-ui/**",
                                      "/api/v1/reservation-management/v3/api-docs/**",
                                      "/api/v1/reservation-management/swagger-ui/**",
                                      "/api/v1/reservation-management/swagger-ui.html",
                                      "/api/v1/reservation-management/swagger-resources/**",
                                      "/api/v1/reservation-management/swagger-resources/configuration/ui",
                                      "/api/v1/reservation-management/swagger-resources/configuration/security",
                                      "/api/v1/reservation-management/swagger.v1+json/**").permitAll()
                                .pathMatchers("/api/v1/user-management/user/**").hasAnyRole(ADMIN, USER)
                                .pathMatchers("/api/v1/user-management/**").hasAnyRole(ADMIN)
                                .pathMatchers(HttpMethod.GET,"/api/v1/accommodation-management/destination/**").hasAnyRole(USER, ADMIN)
                                .pathMatchers("/api/v1/accommodation-management/comment/**").hasAnyRole(USER, ADMIN)
                                .pathMatchers(HttpMethod.GET,"/api/v1/accommodation-management/accommodation/**").hasAnyRole(USER, ADMIN, ADMIN_ACCOMMODATION)
                                .pathMatchers(HttpMethod.GET,"/api/v1/accommodation-management/accommodation/administrator/**").hasAnyRole(ADMIN_ACCOMMODATION)
                                .pathMatchers("/api/v1/accommodation-management/accommodation/**").hasRole(ADMIN_ACCOMMODATION)
                                .pathMatchers("/api/v1/accommodation-management/**").hasRole(ADMIN)
                                .pathMatchers("/api/v1/reservation-management/reservation/**").hasAnyRole(ADMIN, USER)
                                .pathMatchers("/api/v1/reservation-management/**").hasRole(ADMIN)
                                .pathMatchers("/api/v1/authentication-management/**").permitAll()
                                .anyExchange().authenticated());

        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
        http.csrf().disable();
        return http.build();
    }

}

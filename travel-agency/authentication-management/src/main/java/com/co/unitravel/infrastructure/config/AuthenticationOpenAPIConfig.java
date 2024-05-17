package com.co.unitravel.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class AuthenticationOpenAPIConfig {

    private static final String SERVER_PORT = "2208";

    @Bean
    public OpenAPI springAuthenticationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Authentication management API")
                        .description("API that provides services for the management, administration and control of uniTravel authentication")
                        .version("v0.0.1"))
                .servers(
                        List.of(
                                new Server().url("http://localhost:" + SERVER_PORT +"/api/v1/authentication-management").description("API Gateway")
                        )
                );
    }
}

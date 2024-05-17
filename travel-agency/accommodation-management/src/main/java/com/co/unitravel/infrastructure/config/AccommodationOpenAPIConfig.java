package com.co.unitravel.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccommodationOpenAPIConfig {

    private static final String SERVER_PORT = "2208";

    @Bean
    public OpenAPI springAccommodationOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info().title("Accommodation management API")
                        .description("API that provides services for the management, administration and control of uniTravel accommodations")
                        .version("v0.0.1"))
                .servers(
                        List.of(
                                new Server().url("http://localhost:" + SERVER_PORT +"/api/v1/accommodation-management").description("API Gateway")
                        )
                );
    }
}

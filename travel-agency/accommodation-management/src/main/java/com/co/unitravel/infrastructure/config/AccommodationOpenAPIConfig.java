package com.co.unitravel.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AccommodationOpenAPIConfig {

    private static final String SERVER_PORT = "8084";

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
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

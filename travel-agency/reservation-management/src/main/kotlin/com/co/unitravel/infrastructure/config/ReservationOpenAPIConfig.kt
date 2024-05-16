package com.co.unitravel.infrastructure.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.security.SecurityScheme

const val SERVER_PORT = "2208"

@Configuration
open class ReservationOpenAPIConfig {

    @Bean
    open fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components().addSecuritySchemes(
                    "bearerAuth", SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
            )
            .info(
                Info()
                .title("Reservation Management API")
                .description("API that provides services for the management, administration and control of uniTravel reservations"))
            .servers(listOf(
                Server().apply {
                    url("http://localhost:$SERVER_PORT/api/v1/reservation-management")
                    description("API Gateway")
                }
            ));
    }


}
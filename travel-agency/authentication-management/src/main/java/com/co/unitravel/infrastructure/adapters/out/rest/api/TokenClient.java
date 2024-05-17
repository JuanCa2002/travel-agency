package com.co.unitravel.infrastructure.adapters.out.rest.api;

import com.co.unitravel.infrastructure.adapters.out.rest.api.response.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
@Component
@FeignClient(name = "token-client",  url = "${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
public interface TokenClient {

    @PostMapping(
            path = "/protocol/openid-connect/token",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}
    )
    TokenResponse sendRequest(String ruta);
}

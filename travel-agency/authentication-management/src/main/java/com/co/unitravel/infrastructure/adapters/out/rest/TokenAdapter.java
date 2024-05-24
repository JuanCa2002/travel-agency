package com.co.unitravel.infrastructure.adapters.out.rest;

import com.co.unitravel.domain.models.Login;
import com.co.unitravel.domain.models.RefreshToken;
import com.co.unitravel.domain.models.Token;
import com.co.unitravel.infrastructure.adapters.out.rest.api.TokenClient;
import com.co.unitravel.infrastructure.adapters.out.rest.api.mappers.token.TokenMapper;
import com.co.unitravel.infrastructure.adapters.out.rest.api.response.TokenResponse;
import com.co.unitravel.infrastructure.ports.out.client.token.TokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenAdapter implements TokenPort {

    private final TokenClient tokenClient;

    private final TokenMapper tokenMapper;
    @Override
    public Token getToken(Login login) {
        String clientID = "springboot-keycloak-client";
        String usernameAdmin = login.getUsername();
        String passwordAdmin  = login.getPassword();
        String grantType = "password";

        String requestBody = String.format("client_id=%s&username=%s&password=%s&grant_type=%s", clientID, usernameAdmin, passwordAdmin, grantType);

        TokenResponse response = tokenClient.sendRequest(requestBody);
        return tokenMapper.responseToDomain(response);
    }

    @Override
    public Token getRefreshToken(RefreshToken refreshToken) {
        String clientID = "springboot-keycloak-client";
        String grantType = "refresh_token";
        String refreshTokenValue = refreshToken.getRefreshTokenKey();

        String requestBody = String.format("client_id=%s&grant_type=%s&refresh_token=%s", clientID, grantType, refreshTokenValue);
        TokenResponse response = tokenClient.sendRequest(requestBody);
        return tokenMapper.responseToDomain(response);
    }
}

package com.co.unitravel.infrastructure.adapters.in.rest.controllers;

import com.co.unitravel.domain.models.User;
import com.co.unitravel.infrastructure.adapters.in.rest.configuration.AuthenticationApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.LoginRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.RefreshTokenRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.TokenResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.AuthenticationMapperApi;
import com.co.unitravel.infrastructure.ports.in.login.LoginUseCase;
import com.co.unitravel.infrastructure.ports.in.refreshToken.RefreshTokenUseCase;
import com.co.unitravel.infrastructure.ports.out.client.user.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/authentication-management")
public class AuthenticationController implements AuthenticationApi {

    private final LoginUseCase loginUseCase;

    private final RefreshTokenUseCase refreshTokenUseCase;

    private final UserPort userPort;

    private final AuthenticationMapperApi authenticationMapperApi;

    @PostMapping("/login")
    @Override
    public ResponseEntity<TokenResponse> login(LoginRequest loginRequest) {
        var result = loginUseCase.login(authenticationMapperApi.loginRequestToDomain(loginRequest));
        return new ResponseEntity<>(authenticationMapperApi.domainToTokenResponse(result), HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    @Override
    public ResponseEntity<TokenResponse> refreshToken(RefreshTokenRequest refreshTokenRequest) {
        var result = refreshTokenUseCase.refreshToken(authenticationMapperApi.refreshTokenRequestToDomain(refreshTokenRequest));
        return new ResponseEntity<>(authenticationMapperApi.domainToTokenResponse(result), HttpStatus.OK);
    }

    @PostMapping("/newUser")
    public ResponseEntity<Void> createUser(@RequestBody User user, @RequestHeader("Authorization") String token) {
        userPort.registerUser(user, token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

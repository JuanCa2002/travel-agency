package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.LoginRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationApi {

    @Operation(summary = "Login on the application", description = "Login to get authentication token", tags = {"login"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Token generated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)))})
    ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest);
}

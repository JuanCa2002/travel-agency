package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.UserRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserApi {
    @Operation(summary = "Create new user", description = "Add a new user", tags = {"user"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New user added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))})
    ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest userRequest) throws BusinessException;
}

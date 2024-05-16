package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.UserRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.UserUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.UserResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@SecurityRequirement(name = "bearerAuth")
public interface UserApi {
    @Operation(summary = "Create new user", description = "Add a new user", tags = {"user"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New user added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))})
    ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest userRequest) throws BusinessException, NotFoundException, JsonProcessingException;

    @Operation(summary = "Find an user by id", description = "Find user by its id", tags = {"user"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))})
    ResponseEntity<UserResponse> findById(@PathVariable Long id) throws NotFoundException;

    @Operation(summary = "Update an user by id", description = "Update user by its id", tags = {"user"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated status", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))})
    ResponseEntity<UserResponse> update(@RequestBody UserUpdateRequest userUpdateRequest) throws NotFoundException, JsonProcessingException;

    @Operation(summary = "Update an user status", description = "Update user status by its id", tags = {"user"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated status", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))})
    ResponseEntity<UserResponse> updateStatus(@PathVariable Long id) throws NotFoundException;

    @Operation(summary = "Update an user password", description = "Update user password by its id", tags = {"user"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated password")})
    ResponseEntity<Void> updatePassword(@PathVariable Long id, String currentPassword, String newPassword) throws NotFoundException, BusinessException;


    @Operation(summary = "Find an user by identification", description = "Find user by identification", tags = {"user"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))})
    ResponseEntity<UserResponse> findByIdentification(String documentNumber, Integer documentTypeId) throws NotFoundException;

    @Operation(summary = "Find an user by authentication", description = "Find user by authentication", tags = {"user"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))})
    ResponseEntity<UserResponse> findByAuthentication(String email, String password) throws NotFoundException;

    @Operation(summary = "Find an user by email", description = "Find user by email", tags = {"user"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))})
    ResponseEntity<UserResponse> findByEmail(String email) throws NotFoundException;
}

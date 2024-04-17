package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.PermissionRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.PermissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface PermissionApi {

    @Operation(summary = "Create new permission", description = "Add a new permission", tags = {"permission"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New permission added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PermissionResponse.class)))})
    ResponseEntity<PermissionResponse> save(@Valid @RequestBody PermissionRequest permissionRequest) throws BusinessException;
}

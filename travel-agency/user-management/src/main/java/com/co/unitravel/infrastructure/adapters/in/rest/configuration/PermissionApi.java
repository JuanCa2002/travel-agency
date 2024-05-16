package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.PermissionRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.GetRolResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.PermissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
public interface PermissionApi {

    @Operation(summary = "Create new permission", description = "Add a new permission", tags = {"permission"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New permission added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PermissionResponse.class)))})
    ResponseEntity<PermissionResponse> save(@Valid @RequestBody PermissionRequest permissionRequest) throws BusinessException;

    @Operation(summary = "Find permissions by role", description = "Find permissions by rol id", tags = {"permission"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of permissions by role", content = @Content(mediaType = "application/json", array= @ArraySchema(schema = @Schema(implementation = PermissionResponse.class))))})
    ResponseEntity<List<PermissionResponse>> findByRol(@PathVariable Long rolId) throws NotFoundException;
}

package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.RolRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.RolUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.GetRolResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.RolResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RolApi {
    @Operation(summary = "Create new rol", description = "Add a new rol", tags = {"rol"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New rol added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RolResponse.class)))})
    ResponseEntity<RolResponse> save(@Valid @RequestBody RolRequest rolRequest) throws BusinessException, NotFoundException;

    @Operation(summary = "Find a rol by id", description = "Find rol by its id", tags = {"rol"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found rol", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetRolResponse.class)))})
    ResponseEntity<GetRolResponse> findById(@PathVariable Long id) throws NotFoundException;

    @Operation(summary = "Find all roles", description = "Find a complete list of roles", tags = {"rol"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All roles", content = @Content(mediaType = "application/json", array= @ArraySchema(schema = @Schema(implementation = GetRolResponse.class))))})
    ResponseEntity<List<GetRolResponse>> findAll();

    @Operation(summary = "Update a rol", description = "Update a existing rol by its id", tags = {"rol"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated rol", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RolResponse.class)))})
    ResponseEntity<RolResponse> update(@Valid @RequestBody RolUpdateRequest rolUpdateRequest) throws NotFoundException, BusinessException;
}

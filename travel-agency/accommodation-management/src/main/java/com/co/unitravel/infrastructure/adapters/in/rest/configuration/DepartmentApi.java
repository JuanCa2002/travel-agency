package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DepartmentRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DepartmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
public interface DepartmentApi {

    @Operation(summary = "Create new department", description = "Add a new department", tags = {"department"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New department added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DepartmentResponse.class)))})
    ResponseEntity<DepartmentResponse> save(@Valid @RequestBody DepartmentRequest departmentRequest) throws BusinessException;

    @Operation(summary = "Find all departments", description = "Find all existing departments", tags = {"department"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of departments", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DepartmentResponse.class))))})
    ResponseEntity<List<DepartmentResponse>> findAll();
}

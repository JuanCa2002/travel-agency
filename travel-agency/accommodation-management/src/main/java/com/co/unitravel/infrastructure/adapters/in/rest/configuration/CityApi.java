package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.CityRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.CityResponse;
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
public interface CityApi {

    @Operation(summary = "Create new city", description = "Add a new city", tags = {"city"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New city added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CityResponse.class)))})
    ResponseEntity<CityResponse> save(@Valid @RequestBody CityRequest cityRequest) throws NotFoundException, BusinessException;

    @Operation(summary = "Find a city by id", description = "Find a existing city by its id", tags = {"city"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found city", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CityResponse.class)))})
    ResponseEntity<CityResponse> findById(@PathVariable Long id) throws NotFoundException;

    @Operation(summary = "Find cities by department", description = "Find a list of cities by department", tags = {"city"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "City list", content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = CityResponse.class))))})
    ResponseEntity<List<CityResponse>> findByDepartment(@PathVariable Integer departmentId) throws NotFoundException;
}

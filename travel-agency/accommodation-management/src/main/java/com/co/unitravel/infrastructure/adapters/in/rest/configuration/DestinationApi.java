package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DestinationRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DestinationUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DestinationResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.PageResponse;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
public interface DestinationApi {
    @Operation(summary = "Create new destination", description = "Add a new destination", tags = {"destination"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New destination added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DestinationResponse.class)))})
    ResponseEntity<DestinationResponse> save(@Valid @RequestBody DestinationRequest destinationRequest) throws NotFoundException, BusinessException;

    @Operation(summary = "Update a destination by id", description = "Update an existing destination by its id", tags = {"destination"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated destination", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DestinationResponse.class)))})
    ResponseEntity<DestinationResponse> update(@Valid @RequestBody DestinationUpdateRequest destinationRequest) throws NotFoundException;

    @Operation(summary = "Find a destination by id", description = "Find an existing destination by its id", tags = {"destination"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found destination", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DestinationResponse.class)))})
    ResponseEntity<DestinationResponse> findById(Long id) throws NotFoundException;

    @Operation(summary = "Find a destination by city", description = "Find an existing destination by city id", tags = {"destination"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found destination", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DestinationResponse.class)))})
    ResponseEntity<DestinationResponse> findByCity(Long cityId) throws NotFoundException;

    @Operation(summary = "Find paginated list of destination", description = "Find a paginated list of destinations", tags = {"destination"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of destinations", content = @Content(mediaType = "application/json", array= @ArraySchema(schema = @Schema(implementation = DestinationResponse.class))))})
    ResponseEntity<PageResponse<List<DestinationResponse>>> findByCriteria(@RequestParam(name = "cityName", required = false) String cityName,
                                                                           @RequestParam(name = "rowsPerPage", defaultValue = "10") Integer rowsPerPage,
                                                                           @RequestParam(name = "skip", defaultValue = "0") Integer skip);
}

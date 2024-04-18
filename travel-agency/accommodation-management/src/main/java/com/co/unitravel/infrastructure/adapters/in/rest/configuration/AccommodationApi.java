package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.AccommodationRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.AccommodationUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DestinationUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.AccommodationResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DestinationResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AccommodationApi {

    @Operation(summary = "Create new accommodation", description = "Add a new accommodation", tags = {"accommodation"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New accommodation added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccommodationResponse.class)))})
    ResponseEntity<AccommodationResponse> save(@Valid @RequestBody AccommodationRequest accommodationRequest) throws NotFoundException, BusinessException;

    @Operation(summary = "Update a accommodation by id", description = "Update an existing accommodation by its id", tags = {"accommodation"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated accommodation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccommodationResponse.class)))})
    ResponseEntity<AccommodationResponse> update(@Valid @RequestBody AccommodationUpdateRequest accommodationRequest) throws NotFoundException;

    @Operation(summary = "Find paginated list of accommodation", description = "Find a paginated list of accommodations", tags = {"accommodation"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of accommodation", content = @Content(mediaType = "application/json", array= @ArraySchema(schema = @Schema(implementation = AccommodationResponse.class))))})
    ResponseEntity<PageResponse<List<AccommodationResponse>>> findByCriteria(@RequestParam(name = "rowsPerPage", defaultValue = "10") Integer rowsPerPage,
                                                                           @RequestParam(name = "skip", defaultValue = "0") Integer skip);
}

package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.CityRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.CityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CityApi {

    @Operation(summary = "Create new city", description = "Add a new city", tags = {"city"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New city added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CityResponse.class)))})
    ResponseEntity<CityResponse> save(@Valid @RequestBody CityRequest cityRequest) throws NotFoundException;
}

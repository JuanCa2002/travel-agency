package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DestinationRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DestinationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface DestinationApi {
    @Operation(summary = "Create new destination", description = "Add a new destination", tags = {"destination"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New destination added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DestinationResponse.class)))})
    ResponseEntity<DestinationResponse> save(@Valid @RequestBody DestinationRequest destinationRequest) throws NotFoundException, BusinessException;
}

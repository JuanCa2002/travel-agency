package com.co.unitravel.infrastructure.adapters.`in`.rest.configuration

import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.AirplaneRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.AirplaneResponse
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


interface AirplaneApi {

    @Operation(
        summary = "Create new airplane",
        description = "Add a new airplane",
        tags = ["airplane"]
    )
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "New airplane added successfully", content = [Content(mediaType = "application/json", schema = Schema(implementation = AirplaneResponse::class))])
    )
    fun save(@Valid @RequestBody airplaneRequest: AirplaneRequest):ResponseEntity<AirplaneResponse>
}
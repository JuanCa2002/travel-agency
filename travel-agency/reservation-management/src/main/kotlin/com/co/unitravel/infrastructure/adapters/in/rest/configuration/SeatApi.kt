package com.co.unitravel.infrastructure.adapters.`in`.rest.configuration

import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.SeatRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.SeatUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.SeatResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

interface SeatApi {

    @Operation(
            summary = "Create new seat",
            description = "Add a new seat",
            tags = ["seat"]
    )
    @ApiResponses(
            ApiResponse(responseCode = "201", description = "New seat added successfully", content = [Content(mediaType = "application/json", schema = Schema(implementation = SeatResponse::class))])
    )
    fun save(@Valid @RequestBody seatRequest: SeatRequest): ResponseEntity<SeatResponse>

    @Operation(
            summary = "Update a seat by id",
            description = "Update an existing seat by its id",
            tags = ["seat"]
    )
    @ApiResponses(
            ApiResponse(responseCode = "200", description = "Updated seat", content = [Content(mediaType = "application/json", schema = Schema(implementation = SeatResponse::class))])
    )
    fun update(@Valid @RequestBody seatUpdateRequest: SeatUpdateRequest):ResponseEntity<SeatResponse>
}
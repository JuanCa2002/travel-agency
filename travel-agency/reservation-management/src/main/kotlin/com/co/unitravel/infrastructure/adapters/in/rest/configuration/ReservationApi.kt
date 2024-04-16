package com.co.unitravel.infrastructure.adapters.`in`.rest.configuration

import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.FlightRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.FlightUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.FlightResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.ReservationRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.ReservationUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.ReservationResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface ReservationApi {

    @Operation(
        summary = "Create new reservation",
        description = "Add a new reservation",
        tags = ["reservation"]
    )
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "New reservation added successfully", content = [Content(mediaType = "application/json", schema = Schema(implementation = ReservationResponse::class))])
    )
    fun save(@Valid @RequestBody reservationRequest: ReservationRequest): ResponseEntity<ReservationResponse>

    @Operation(
        summary = "Update a reservation by id",
        description = "Update an existing reservation by its id",
        tags = ["reservation"]
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Updated reservation", content = [Content(mediaType = "application/json", schema = Schema(implementation = ReservationResponse::class))])
    )
    fun update(@Valid @RequestBody reservationUpdateRequest: ReservationUpdateRequest):ResponseEntity<ReservationResponse>

    @Operation(
        summary = "Find a reservation by id",
        description = "Find an existing reservation by its id",
        tags = ["reservation"]
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Found reservation", content = [Content(mediaType = "application/json", schema = Schema(implementation = ReservationResponse::class))])
    )
    fun findById(@PathVariable id:Long):ResponseEntity<ReservationResponse>
}
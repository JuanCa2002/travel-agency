package com.co.unitravel.infrastructure.adapters.`in`.rest.configuration

import com.co.unitravel.domain.models.enums.AirplaneStatus
import com.co.unitravel.domain.models.enums.FlightStatus
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.filter.FlightFilterRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.FlightRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.FlightUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.SeatRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.SeatUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.AirplaneResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.FlightResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.PageResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.SeatResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

interface FlightApi {

    @Operation(
        summary = "Create new flight",
        description = "Add a new flight",
        tags = ["flight"]
    )
    @ApiResponses(
        ApiResponse(responseCode = "201", description = "New flight added successfully", content = [Content(mediaType = "application/json", schema = Schema(implementation = FlightResponse::class))])
    )
    fun save(@Valid @RequestBody flightRequest: FlightRequest): ResponseEntity<FlightResponse>

    @Operation(
        summary = "Update a flight by id",
        description = "Update an existing flight by its id",
        tags = ["flight"]
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Updated flight", content = [Content(mediaType = "application/json", schema = Schema(implementation = FlightResponse::class))])
    )
    fun update(@Valid @RequestBody flightUpdateRequest: FlightUpdateRequest):ResponseEntity<FlightResponse>

    @Operation(
        summary = "Find a paginated list of flights by criteria",
        description = "Find a paginated list of existing flights by criteria",
        tags = ["flight"]
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Paginated list", content = [Content(mediaType = "application/json", array = ArraySchema(schema = Schema(implementation = FlightResponse::class)))])
    )
    fun findByCriteria( flightFilterRequest: FlightFilterRequest,
                       @RequestParam(name = "rowsPerPage", required = true, defaultValue = "10") rowsPerPage: Int,
                       @RequestParam(name = "skip", required = true, defaultValue = "0") skip:Int):ResponseEntity<PageResponse<List<FlightResponse>>>
}
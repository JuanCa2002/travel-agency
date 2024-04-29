package com.co.unitravel.infrastructure.adapters.`in`.rest.configuration

import com.co.unitravel.domain.models.enums.SeatClass
import com.co.unitravel.domain.models.enums.SeatStatus
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.SeatRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.SeatUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.SeatResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

interface SeatApi {

    @Operation(
            summary = "Create list of a seat by a model",
            description = "Add new seats by a model",
            tags = ["seat"]
    )
    @ApiResponses(
            ApiResponse(responseCode = "201", description = "New seats added successfully", content = [Content(mediaType = "application/json", array = ArraySchema(schema = Schema(implementation = SeatResponse::class)))])
    )
    fun save(@Valid @RequestBody seatRequest: SeatRequest, stock: Int): ResponseEntity<List<SeatResponse>>

    @Operation(
            summary = "Update a list of seats by ids",
            description = "Update an existing seats by its ids",
            tags = ["seat"]
    )
    @ApiResponses(
            ApiResponse(responseCode = "200", description = "Updated seats", content = [Content(mediaType = "application/json", array= ArraySchema(schema = Schema(implementation = SeatResponse::class)))])
    )
    fun update(ids: MutableList<Long>, customerId: Long, seatStatus: SeatStatus):ResponseEntity<List<SeatResponse>>

    @Operation(
            summary = "Find a seat by id",
            description = "Find an existing seat by its id",
            tags = ["seat"]
    )
    @ApiResponses(
            ApiResponse(responseCode = "200", description = "Found seat", content = [Content(mediaType = "application/json", schema = Schema(implementation = SeatResponse::class))])
    )
    fun findById(@PathVariable id:Long):ResponseEntity<SeatResponse>

    @Operation(summary = "Find seats by airplane", description = "Find a list of seats by airplane", tags = ["seat"])
    @ApiResponses(
            value = [ApiResponse(responseCode = "200", description = "Seat list", content = [Content(mediaType = "application/json", schema = Schema(implementation = SeatResponse::class))])]
    )
    fun findByAirplane(@PathVariable airplaneId: Long): ResponseEntity<List<SeatResponse>>

    @Operation(
            summary = "Update status of a seat by id",
            description = "Update status of an existing seat by its id",
            tags = ["seat"]
    )
    @ApiResponses(
            ApiResponse(responseCode = "200", description = "Seat status updated", content = [Content(mediaType = "application/json", schema = Schema(implementation = SeatResponse::class))])
    )
    fun updateStatus(@PathVariable id:Long, @RequestParam status: SeatStatus):ResponseEntity<SeatResponse>


    @Operation(summary = "Find seats by customer", description = "Find a list of seats by customer", tags = ["seat"])
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "Seat list", content = [Content(mediaType = "application/json", schema = Schema(implementation = SeatResponse::class))])]
    )
    fun findByCustomer(@PathVariable customerId: Long): ResponseEntity<List<SeatResponse>>


    @Operation(
        summary = "Update price of a seats by airplane and class",
        description = "Update price of an existing seats by airplane id and class",
        tags = ["seat"]
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Seats price updated", content = [Content(mediaType = "application/json", array= ArraySchema(schema = Schema(implementation = SeatResponse::class)))])
    )
    fun updatePrice(airplaneId: Long, seatClass: SeatClass, newPrice: Long):ResponseEntity<List<SeatResponse>>
}
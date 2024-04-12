package com.co.unitravel.infrastructure.adapters.`in`.rest.configuration

import com.co.unitravel.domain.models.enums.AirplaneStatus
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.AirplaneRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.AirplaneUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.AirplaneResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.PageResponse
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam


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

    @Operation(
        summary = "Find an airplane by id",
        description = "Find an existing airplane by its id",
        tags = ["airplane"]
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Found airplane", content = [Content(mediaType = "application/json", schema = Schema(implementation = AirplaneResponse::class))])
    )
    fun findById(@PathVariable id:Long):ResponseEntity<AirplaneResponse>

    @Operation(
        summary = "Find a paginated list of airplanes by criteria",
        description = "Find a paginated list of existing airplanes by criteria",
        tags = ["airplane"]
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Paginated list", content = [Content(mediaType = "application/json", array = ArraySchema(schema = Schema(implementation = AirplaneResponse::class)))])
    )
    fun findByCriteria(@RequestParam(name = "id", required = false) id:Long?,
                       @RequestParam(name = "status", required = false) status: AirplaneStatus?,
                       @RequestParam(name = "rowsPerPage", required = true, defaultValue = "10") rowsPerPage: Int,
                       @RequestParam(name = "skip", required = true, defaultValue = "0") skip:Int):ResponseEntity<PageResponse<List<AirplaneResponse>>>


    @Operation(
        summary = "Update an airplane by id",
        description = "Update an existing airplane by its id",
        tags = ["airplane"]
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Updated airplane", content = [Content(mediaType = "application/json", schema = Schema(implementation = AirplaneResponse::class))])
    )
    fun update(@Valid @RequestBody airplaneUpdateRequest: AirplaneUpdateRequest):ResponseEntity<AirplaneResponse>


    @Operation(
        summary = "Update status of an airplane by id",
        description = "Update status of an existing airplane by its id",
        tags = ["airplane"]
    )
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Airplane status updated", content = [Content(mediaType = "application/json", schema = Schema(implementation = AirplaneResponse::class))])
    )
    fun updateStatus(@PathVariable id:Long, @RequestParam status: AirplaneStatus):ResponseEntity<AirplaneResponse>
}
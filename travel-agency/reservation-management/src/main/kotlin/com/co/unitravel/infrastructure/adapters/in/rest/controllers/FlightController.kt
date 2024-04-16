package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers

import com.co.unitravel.domain.models.enums.FlightStatus
import com.co.unitravel.infrastructure.adapters.`in`.rest.configuration.FlightApi
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.filter.FlightFilterRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.FlightRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.FlightUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.FlightResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.PageResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.mappers.FlightMapperApi
import com.co.unitravel.infrastructure.ports.`in`.flight.FlightUseCase
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequiredArgsConstructor
@RequestMapping("\${request-mapping.controller.flight}")
open class FlightController(private val flightUseCase: FlightUseCase, private val flightMapperApi: FlightMapperApi): FlightApi {

    @PostMapping
    override fun save(flightRequest: FlightRequest): ResponseEntity<FlightResponse> {
        val result = flightUseCase.create(flightMapperApi.requestToDomain(flightRequest));
        return ResponseEntity(flightMapperApi.domainToResponse(result), HttpStatus.CREATED);
    }

    @PutMapping
    override fun update(flightUpdateRequest: FlightUpdateRequest): ResponseEntity<FlightResponse> {
        val response = flightUseCase.update(flightUpdateRequest.id!!, flightMapperApi.updateRequestToDomain(flightUpdateRequest))
        return ResponseEntity(flightMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping
    override fun findByCriteria(
        flightFilterRequest: FlightFilterRequest,
        rowsPerPage: Int,
        skip: Int
    ): ResponseEntity<PageResponse<List<FlightResponse>>> {
        val paginatedResult = flightUseCase.getByCriteria(flightMapperApi.filterRequestToRq(flightFilterRequest), rowsPerPage, skip);
        val responses = flightMapperApi.domainsToResponses(paginatedResult.data);
        val pageResponse = PageResponse(responses, paginatedResult.total);

        return ResponseEntity(pageResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    override fun findById(@PathVariable id: Long): ResponseEntity<FlightResponse> {
        val result = flightUseCase.getById(id);
        return ResponseEntity(flightMapperApi.domainToResponse(result), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    override fun updateStatus(@PathVariable id: Long, status: FlightStatus): ResponseEntity<FlightResponse> {
        val response = flightUseCase.updateStatus(id, status);
        return ResponseEntity(flightMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/all")
    override fun findAllCriteria(flightFilterRequest: FlightFilterRequest): ResponseEntity<List<FlightResponse>> {
        val response = flightUseCase.findAll(flightMapperApi.filterRequestToRq(flightFilterRequest));
        return ResponseEntity(flightMapperApi.domainsToResponses(response), HttpStatus.OK);
    }
}
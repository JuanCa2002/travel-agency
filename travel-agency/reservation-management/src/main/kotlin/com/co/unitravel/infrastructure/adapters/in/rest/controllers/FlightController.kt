package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers

import com.co.unitravel.domain.models.enums.FlightStatus
import com.co.unitravel.infrastructure.adapters.`in`.rest.configuration.FlightApi
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.FlightRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.FlightUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.FlightResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.PageResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.mappers.FlightMapperApi
import com.co.unitravel.infrastructure.ports.`in`.flight.FlightUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

open class FlightController(private val flightUseCase: FlightUseCase, private val flightMapperApi: FlightMapperApi): FlightApi {

    override fun save(flightRequest: FlightRequest): ResponseEntity<FlightResponse> {
        val result = flightUseCase.create(flightMapperApi.requestToDomain(flightRequest));
        return ResponseEntity(flightMapperApi.domainToResponse(result), HttpStatus.CREATED);
    }

    override fun update(flightUpdateRequest: FlightUpdateRequest): ResponseEntity<FlightResponse> {
        val response = flightUseCase.update(flightUpdateRequest.id!!, flightMapperApi.updateRequestToDomain(flightUpdateRequest))
        return ResponseEntity(flightMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    override fun findByCriteria(
        id: Long?,
        status: FlightStatus?,
        rowsPerPage: Int,
        skip: Int
    ): ResponseEntity<PageResponse<List<FlightResponse>>> {
        val paginatedResult = flightUseCase.getByCriteria(id, status, rowsPerPage, skip);
        val responses = flightMapperApi.domainsToResponses(paginatedResult.data);
        val pageResponse = PageResponse(responses, paginatedResult.total);

        return ResponseEntity(pageResponse, HttpStatus.OK);
    }
}
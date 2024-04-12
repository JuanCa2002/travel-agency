package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers

import com.co.unitravel.domain.models.Airplane
import com.co.unitravel.domain.models.enums.AirplaneStatus
import com.co.unitravel.infrastructure.adapters.`in`.rest.configuration.AirplaneApi
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.AirplaneRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.AirplaneUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.AirplaneResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.PageResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.mappers.AirplaneMapperApi
import com.co.unitravel.infrastructure.ports.`in`.airplane.AirplaneUseCase
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("\${request-mapping.controller.airplane}")
class AirplaneController(private val airplaneUseCase: AirplaneUseCase,
                         private val airplaneMapperApi: AirplaneMapperApi): AirplaneApi {

    @PostMapping
    override fun save(airplaneRequest: AirplaneRequest): ResponseEntity<AirplaneResponse> {
        val result = airplaneUseCase.create(airplaneMapperApi.requestToDomain(airplaneRequest));
        return ResponseEntity(airplaneMapperApi.domainToResponse(result), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    override fun findById(id: Long): ResponseEntity<AirplaneResponse> {
        val result = airplaneUseCase.getById(id);
        return ResponseEntity(airplaneMapperApi.domainToResponse(result), HttpStatus.OK);
    }

    @GetMapping
    override fun findByCriteria(
        id: Long?,
        status: AirplaneStatus?,
        rowsPerPage: Int,
        skip: Int
    ): ResponseEntity<PageResponse<List<AirplaneResponse>>> {
        val paginatedResult = airplaneUseCase.getByCriteria(id, status, rowsPerPage, skip);
        val responses = airplaneMapperApi.domainsToResponses(paginatedResult.data);
        val pageResponse = PageResponse(responses, paginatedResult.total);

        return ResponseEntity(pageResponse, HttpStatus.OK);
    }

    @PutMapping
    override fun update(airplaneUpdateRequest: AirplaneUpdateRequest): ResponseEntity<AirplaneResponse> {
        val response = airplaneUseCase.update(airplaneUpdateRequest.id, airplaneMapperApi.updateRequestToDomain(airplaneUpdateRequest))
        return ResponseEntity(airplaneMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    override fun updateStatus(id: Long, status: AirplaneStatus): ResponseEntity<AirplaneResponse> {
        val response = airplaneUseCase.updateStatus(id, status);
        return ResponseEntity(airplaneMapperApi.domainToResponse(response), HttpStatus.OK);
    }

}
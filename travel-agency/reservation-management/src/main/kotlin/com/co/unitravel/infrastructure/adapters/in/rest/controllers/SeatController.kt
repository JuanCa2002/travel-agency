package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers

import com.co.unitravel.infrastructure.adapters.`in`.rest.configuration.SeatApi
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.SeatRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.SeatUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.SeatResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.mappers.SeatMapperApi
import com.co.unitravel.infrastructure.ports.`in`.seat.SeatUseCase
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("\${request-mapping.controller.seat}")
open class SeatController(private val seatUseCase: SeatUseCase, private val seatMapperApi: SeatMapperApi): SeatApi {

    @PostMapping
    override fun save(seatRequest: SeatRequest): ResponseEntity<SeatResponse> {
        val result = seatUseCase.create(seatMapperApi.requestToDomain(seatRequest));
        return ResponseEntity(seatMapperApi.domainToResponse(result), HttpStatus.CREATED);
    }

    override fun update(seatUpdateRequest: SeatUpdateRequest): ResponseEntity<SeatResponse> {
        val response = seatUseCase.update(seatUpdateRequest.id!!, seatMapperApi.updateRequestToDomain(seatUpdateRequest))
        return ResponseEntity(seatMapperApi.domainToResponse(response), HttpStatus.OK);
    }

}
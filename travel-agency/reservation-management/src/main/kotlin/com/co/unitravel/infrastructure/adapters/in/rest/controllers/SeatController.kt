package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers

import com.co.unitravel.domain.models.enums.SeatStatus
import com.co.unitravel.infrastructure.adapters.`in`.rest.configuration.SeatApi
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.SeatRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.SeatUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.SeatResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.mappers.SeatMapperApi
import com.co.unitravel.infrastructure.ports.`in`.seat.SeatUseCase
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("\${request-mapping.controller.seat}")
open class SeatController(private val seatUseCase: SeatUseCase, private val seatMapperApi: SeatMapperApi): SeatApi {

    @PostMapping
    override fun save(seatRequest: SeatRequest): ResponseEntity<SeatResponse> {
        val result = seatUseCase.create(seatMapperApi.requestToDomain(seatRequest));
        return ResponseEntity(seatMapperApi.domainToResponse(result), HttpStatus.CREATED);
    }

    @PutMapping
    override fun update(seatUpdateRequest: SeatUpdateRequest): ResponseEntity<SeatResponse> {
        val response = seatUseCase.update(seatUpdateRequest.id!!, seatMapperApi.updateRequestToDomain(seatUpdateRequest))
        return ResponseEntity(seatMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    override fun findById(@PathVariable id: Long): ResponseEntity<SeatResponse> {
        val result = seatUseCase.getById(id);
        return ResponseEntity(seatMapperApi.domainToResponse(result), HttpStatus.OK);
    }

    @GetMapping("/airplane/{airplaneId}")
    override fun findByAirplane(@PathVariable airplaneId: Long): ResponseEntity<List<SeatResponse>> {
        val response = seatUseCase.getByAirplane(airplaneId);
        return ResponseEntity(seatMapperApi.domainsToResponses(response), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    override fun updateStatus(@PathVariable id: Long, status: SeatStatus): ResponseEntity<SeatResponse> {
        val response = seatUseCase.updateStatus(id, status);
        return ResponseEntity(seatMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    //@GetMapping("/customer/{customerId}")
    //override fun findByCustomer(@PathVariable customerId: Long): ResponseEntity<SeatResponse> {
    //    val response = seatUseCase.getByCustomerId(customerId);
    //    return ResponseEntity(seatMapperApi.domainToResponse(response), HttpStatus.OK);
    //}

}
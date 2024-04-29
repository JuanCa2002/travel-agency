package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers

import com.co.unitravel.domain.models.enums.SeatClass
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
    override fun save(seatRequest: SeatRequest, stock: Int): ResponseEntity<List<SeatResponse>> {
        val result = seatUseCase.create(seatMapperApi.requestToDomain(seatRequest), stock);
        return ResponseEntity(seatMapperApi.domainsToResponses(result), HttpStatus.CREATED);
    }

    @PutMapping
    override fun update(@RequestBody ids: MutableList<Long>, customerId: Long, seatStatus: SeatStatus): ResponseEntity<List<SeatResponse>> {
        val response = seatUseCase.updateAll(ids, customerId, seatStatus)
        return ResponseEntity(seatMapperApi.domainsToResponses(response), HttpStatus.OK);
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

    @GetMapping("/customer/{customerId}")
    override fun findByCustomer(@PathVariable customerId: Long): ResponseEntity<List<SeatResponse>> {
        val response = seatUseCase.getByCustomer(customerId)
        return ResponseEntity(seatMapperApi.domainsToResponses(response), HttpStatus.OK)
    }

    @PatchMapping("/price")
    override fun updatePrice(airplaneId: Long, seatClass: SeatClass, newPrice: Long): ResponseEntity<List<SeatResponse>> {
        val response = seatUseCase.updatePrice(newPrice, seatClass, airplaneId)
        return ResponseEntity(seatMapperApi.domainsToResponses(response), HttpStatus.OK)
    }
}
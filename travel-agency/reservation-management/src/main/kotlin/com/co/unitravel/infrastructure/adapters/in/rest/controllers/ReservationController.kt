package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers

import com.co.unitravel.domain.models.enums.ReservationStatus
import com.co.unitravel.infrastructure.adapters.`in`.rest.configuration.ReservationApi
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.filter.ReservationFilterRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.ReservationRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.ReservationUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.ReservationResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.mappers.ReservationMapperApi
import com.co.unitravel.infrastructure.ports.`in`.reservation.ReservationUseCase
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("\${request-mapping.controller.reservation}")
class ReservationController(private val reservationUseCase: ReservationUseCase, private val reservationMapperApi: ReservationMapperApi): ReservationApi {

    @PostMapping
    override fun save(reservationRequest: ReservationRequest): ResponseEntity<ReservationResponse> {
        val result = reservationUseCase.create(reservationMapperApi.requestToDomain(reservationRequest));
        return ResponseEntity(reservationMapperApi.domainToResponse(result), HttpStatus.CREATED);
    }

    @PutMapping
    override fun update(reservationUpdateRequest: ReservationUpdateRequest): ResponseEntity<ReservationResponse> {
        val response = reservationUseCase.update(reservationUpdateRequest.id!!, reservationMapperApi.updateRequestToDomain(reservationUpdateRequest))
        return ResponseEntity(reservationMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    override fun findById(id: Long): ResponseEntity<ReservationResponse> {
        val result = reservationUseCase.getById(id);
        return ResponseEntity(reservationMapperApi.domainToResponse(result), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    override fun updateStatus(id: Long, status: ReservationStatus): ResponseEntity<ReservationResponse> {
        val response = reservationUseCase.updateStatus(id, status);
        return ResponseEntity(reservationMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/all")
    override fun findAllCriteria(reservationFilterRequest: ReservationFilterRequest): ResponseEntity<List<ReservationResponse>> {
        val response = reservationUseCase.findAllByCriteria(reservationMapperApi.filterRequestToRq(reservationFilterRequest));
        return ResponseEntity(reservationMapperApi.domainsToResponses(response), HttpStatus.OK);
    }
}
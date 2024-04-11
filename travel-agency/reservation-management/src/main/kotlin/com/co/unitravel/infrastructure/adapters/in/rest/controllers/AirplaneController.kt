package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers

import com.co.unitravel.infrastructure.adapters.`in`.rest.configuration.AirplaneApi
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.AirplaneRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.AirplaneResponse
import com.co.unitravel.infrastructure.adapters.`in`.rest.mappers.AirplaneMapperApi
import com.co.unitravel.infrastructure.ports.`in`.airplane.AirplaneUseCase
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
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
}
package com.co.unitravel.infrastructure.adapters.in.rest.controllers;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.configuration.CityApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.CityRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.CityResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.CityMapperApi;
import com.co.unitravel.infrastructure.ports.in.city.CityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.city}")
public class CityController implements CityApi {

    private final CityUseCase cityUseCase;

    private final CityMapperApi cityMapperApi;

    @PostMapping
    @Override
    public ResponseEntity<CityResponse> save(CityRequest cityRequest) throws NotFoundException {
        var response = cityUseCase.create(cityMapperApi.requestToDomain(cityRequest));
        return new ResponseEntity<>(cityMapperApi.domainToResponse(response), HttpStatus.CREATED);
    }
}

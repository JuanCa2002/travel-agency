package com.co.unitravel.infrastructure.adapters.in.rest.controllers;


import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.configuration.DestinationApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DestinationRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DestinationResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.DestinationMapperApi;
import com.co.unitravel.infrastructure.ports.in.destination.DestinationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.destination}")
public class DestinationController implements DestinationApi {

    private final DestinationUseCase destinationUseCase;

    private final DestinationMapperApi destinationMapperApi;

    @PostMapping
    @Override
    public ResponseEntity<DestinationResponse> save(DestinationRequest destinationRequest) throws NotFoundException, BusinessException {
        var response = destinationUseCase.create(destinationMapperApi.requestToDomain(destinationRequest));
        return new ResponseEntity<>(destinationMapperApi.domainToResponse(response), HttpStatus.OK);
    }
}

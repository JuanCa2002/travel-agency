package com.co.unitravel.infrastructure.adapters.in.rest.controllers;


import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.infrastructure.adapters.in.rest.configuration.DestinationApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DestinationRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DestinationUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DestinationResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.DestinationMapperApi;
import com.co.unitravel.infrastructure.ports.in.destination.DestinationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PutMapping
    @Override
    public ResponseEntity<DestinationResponse> update(DestinationUpdateRequest destinationRequest) throws NotFoundException {
        var response = destinationUseCase.update(destinationMapperApi.updateRequestToDomain(destinationRequest));
        return new ResponseEntity<>(destinationMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<DestinationResponse> findById(@PathVariable Long id) throws NotFoundException {
        var response = destinationUseCase.getById(id);
        return new ResponseEntity<>(destinationMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/city/{cityId}")
    @Override
    public ResponseEntity<DestinationResponse> findByCity(@PathVariable Long cityId) throws NotFoundException {
        var response = destinationUseCase.getDestinationByCity(cityId);
        return new ResponseEntity<>(destinationMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<PageResponse<List<DestinationResponse>>> findByCriteria(String cityName, Integer rowsPerPage, Integer skip) {
        var response = destinationUseCase.findByCriteria(cityName, rowsPerPage, skip);
        PageResponse<List<DestinationResponse>> pageResponse = new PageResponse<>();
        pageResponse.setData(destinationMapperApi.domainsToResponses(response.data()));
        pageResponse.setTotal(response.total());
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }
}

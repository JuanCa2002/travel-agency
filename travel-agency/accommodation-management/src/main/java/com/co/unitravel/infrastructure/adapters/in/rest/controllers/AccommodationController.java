package com.co.unitravel.infrastructure.adapters.in.rest.controllers;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.configuration.AccommodationApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.AccommodationRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.AccommodationUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DestinationUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.AccommodationResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DestinationResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.PageResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.AccommodationMapperApi;
import com.co.unitravel.infrastructure.ports.in.accommodation.AccommodationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.accommodation}")
public class AccommodationController implements AccommodationApi {

    private final AccommodationUseCase accommodationUseCase;

    private final AccommodationMapperApi accommodationMapperApi;

    @PostMapping
    @Override
    public ResponseEntity<AccommodationResponse> save(AccommodationRequest accommodationRequest) throws NotFoundException, BusinessException {
        var response = accommodationUseCase.create(accommodationMapperApi.requestToDomain(accommodationRequest));
        return new ResponseEntity<>(accommodationMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @PutMapping
    @Override
    public ResponseEntity<AccommodationResponse> update(AccommodationUpdateRequest accommodationRequest) throws NotFoundException {
        var response = accommodationUseCase.update(accommodationMapperApi.updateRequestToDomain(accommodationRequest));
        return new ResponseEntity<>(accommodationMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<PageResponse<List<AccommodationResponse>>> findByCriteria(Integer rowsPerPage, Integer skip) {
        var response = accommodationUseCase.findByCriteria(rowsPerPage, skip);
        PageResponse<List<AccommodationResponse>> pageResponse = new PageResponse<>();
        pageResponse.setData(accommodationMapperApi.domainsToResponses(response.data()));
        pageResponse.setTotal(response.total());
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<AccommodationResponse> findById(Long id) throws NotFoundException {
        var response = accommodationUseCase.getById(id);
        return new ResponseEntity<>(accommodationMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/destination/{id}")
    @Override
    public ResponseEntity<AccommodationResponse> findByDestination(Long destinationId) throws NotFoundException {
        var response = accommodationUseCase.getByDestination(destinationId);
        return new ResponseEntity<>(accommodationMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<AccommodationResponse> updateStatus(Long id) throws NotFoundException {
        var response = accommodationUseCase.updateStatus(id);
        return new ResponseEntity<>(accommodationMapperApi.domainToResponse(response), HttpStatus.OK);
    }
}

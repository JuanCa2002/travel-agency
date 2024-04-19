package com.co.unitravel.infrastructure.adapters.in.rest.controllers;


import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.configuration.CommentApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.CommentRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.CommentUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.CommentResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.CommentMapperApi;
import com.co.unitravel.infrastructure.ports.in.comment.CommentUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.comment}")
public class CommentController implements CommentApi {

    private final CommentMapperApi commentMapperApi;

    private final CommentUseCase commentUseCase;

    @PostMapping
    @Override
    public ResponseEntity<CommentResponse> save(CommentRequest commentRequest) throws NotFoundException, BusinessException, JsonProcessingException {
        var response = commentUseCase.create(commentMapperApi.requestToDomain(commentRequest));
        return new ResponseEntity<>(commentMapperApi.domainToResponse(response), HttpStatus.CREATED);
    }

    @PutMapping
    @Override
    public ResponseEntity<CommentResponse> update(CommentUpdateRequest commentUpdateRequest) throws NotFoundException {
        var response = commentUseCase.update(commentMapperApi.updateRequestToDomain(commentUpdateRequest));
        return new ResponseEntity<>(commentMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<CommentResponse> updateStatus(@PathVariable Long id) throws NotFoundException {
        var response = commentUseCase.updateStatus(id);
        return new ResponseEntity<>(commentMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/accommodation/{accommodationId}")
    @Override
    public ResponseEntity<List<CommentResponse>> findByAccommodation(@PathVariable Long accommodationId) {
        var response = commentUseCase.getByAccommodation(accommodationId);
        return new ResponseEntity<>(commentMapperApi.domainsToResponses(response), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    @Override
    public ResponseEntity<List<CommentResponse>> findByCustomer(@PathVariable Long customerId) {
        var response = commentUseCase.getByCustomer(customerId);
        return new ResponseEntity<>(commentMapperApi.domainsToResponses(response), HttpStatus.OK);
    }
}

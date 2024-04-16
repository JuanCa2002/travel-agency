package com.co.unitravel.infrastructure.adapters.in.rest.controllers;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.configuration.DocumentTypeApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DocumentTypeRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DocumentTypeUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DocumentTypeResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.DocumentTypeMapperApi;
import com.co.unitravel.infrastructure.ports.in.documenttype.DocumentTypeUseCase;
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
@RequestMapping("${request-mapping.controller.document-type}")
public class DocumentTypeController implements DocumentTypeApi {

    private final DocumentTypeMapperApi documentTypeMapperApi;

    private final DocumentTypeUseCase documentTypeUseCase;

    @PostMapping
    @Override
    public ResponseEntity<DocumentTypeResponse> save(DocumentTypeRequest documentTypeRequest) throws BusinessException {
        var response = documentTypeUseCase.create(documentTypeMapperApi.requestToDomain(documentTypeRequest));
        return new ResponseEntity<>(documentTypeMapperApi.domainToResponse(response), HttpStatus.CREATED);
    }

    @PutMapping
    @Override
    public ResponseEntity<DocumentTypeResponse> update(DocumentTypeUpdateRequest documentTypeUpdateRequest) throws NotFoundException, BusinessException {
        var response = documentTypeUseCase.update(documentTypeMapperApi.updateRequestToDomain(documentTypeUpdateRequest));
        return new ResponseEntity<>(documentTypeMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<DocumentTypeResponse> findById(@PathVariable Integer id) throws NotFoundException {
        var response = documentTypeUseCase.getById(id);
        return new ResponseEntity<>(documentTypeMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<DocumentTypeResponse>> findAll() {
        var response = documentTypeUseCase.getAll();
        return new ResponseEntity<>(documentTypeMapperApi.domainsToResponses(response), HttpStatus.OK);
    }
}

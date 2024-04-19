package com.co.unitravel.infrastructure.adapters.in.rest.controllers;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.configuration.RolApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.RolRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.RolUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.GetRolResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.RolResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.RolMapperApi;
import com.co.unitravel.infrastructure.ports.in.rol.RolUseCase;
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
@RequestMapping("${request-mapping.controller.rol}")
public class RolController implements RolApi {

    private final RolUseCase rolUseCase;

    private final RolMapperApi rolMapperApi;

    @PostMapping
    @Override
    public ResponseEntity<RolResponse> save(RolRequest rolRequest) throws BusinessException, NotFoundException {
        var response = rolUseCase.create(rolMapperApi.requestToDomain(rolRequest));
        return new ResponseEntity<>(rolMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<GetRolResponse> findById(@PathVariable Long id) throws NotFoundException {
        var response = rolUseCase.findById(id);
        return new ResponseEntity<>(rolMapperApi.domainToGetResponse(response), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<GetRolResponse>> findAll() {
        var response = rolUseCase.getAll();
        return new ResponseEntity<>(rolMapperApi.domainsToGetResponses(response), HttpStatus.OK);
    }

    @PutMapping
    @Override
    public ResponseEntity<RolResponse> update(RolUpdateRequest rolUpdateRequest) throws NotFoundException, BusinessException {
        var response = rolUseCase.update(rolMapperApi.updateRequestToUpdateDomain(rolUpdateRequest));
        return new ResponseEntity<>(rolMapperApi.domainToResponse(response), HttpStatus.OK);
    }
}

package com.co.unitravel.infrastructure.adapters.in.rest.controllers;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.configuration.PermissionApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.PermissionRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.PermissionResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.PermissionMapperApi;
import com.co.unitravel.infrastructure.ports.in.permission.PermissionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.permission}")
public class PermissionController implements PermissionApi {

    private final PermissionMapperApi permissionMapperApi;

    private final PermissionUseCase permissionUseCase;
    @PostMapping
    @Override
    public ResponseEntity<PermissionResponse> save(PermissionRequest permissionRequest) throws BusinessException {
        var response = permissionUseCase.create(permissionMapperApi.requestToDomain(permissionRequest));
        return new ResponseEntity<>(permissionMapperApi.domainToResponse(response), HttpStatus.CREATED);
    }

    @GetMapping("/rol/{rolId}")
    @Override
    public ResponseEntity<List<PermissionResponse>> findByRol(@PathVariable Long rolId) throws NotFoundException {
        var response = permissionUseCase.getByRole(rolId);
        return new ResponseEntity<>(permissionMapperApi.domainsToResponses(response), HttpStatus.OK);
    }
}

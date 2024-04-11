package com.co.unitravel.infrastructure.adapters.in.rest.controllers;

import com.co.unitravel.infrastructure.adapters.in.rest.configuration.DepartmentApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DepartmentRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DepartmentResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.DepartmentMapperApi;
import com.co.unitravel.infrastructure.ports.in.department.DepartmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.department}")
public class DepartmentController implements DepartmentApi {

    private final DepartmentUseCase departmentUseCase;

    private final DepartmentMapperApi departmentMapperApi;

    @PostMapping
    @Override
    public ResponseEntity<DepartmentResponse> save(DepartmentRequest departmentRequest) {
        var result = departmentUseCase.create(departmentMapperApi.requestToDomain(departmentRequest));
        return new ResponseEntity<>(departmentMapperApi.domainToResponse(result), HttpStatus.CREATED);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<DepartmentResponse>> findAll() {
        return new ResponseEntity<>(departmentMapperApi.domainsToResponses(departmentUseCase.getAll()), HttpStatus.OK);
    }
}

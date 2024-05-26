package com.co.unitravel.infrastructure.adapters.in.rest.controllers;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.configuration.UserApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.UserRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.UserUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.UserResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.UserMapperApi;
import com.co.unitravel.infrastructure.ports.in.user.UserUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.user}")
public class UserController implements UserApi {

    private final UserUseCase userUseCase;

    private final UserMapperApi userMapperApi;

    @PostMapping
    @Override
    public ResponseEntity<UserResponse> save(UserRequest userRequest, String token) throws BusinessException, NotFoundException, JsonProcessingException {
        var response = userUseCase.create(userMapperApi.requestToDomain(userRequest),token);
        return new ResponseEntity<>(userMapperApi.domainToResponse(response), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) throws NotFoundException {
        var response = userUseCase.getById(id);
        return new ResponseEntity<>(userMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @PutMapping
    @Override
    public ResponseEntity<UserResponse> update(UserUpdateRequest userUpdateRequest) throws NotFoundException, JsonProcessingException {
        var response = userUseCase.update(userMapperApi.updateRequestToDomain(userUpdateRequest));
        return new ResponseEntity<>(userMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Override
    public ResponseEntity<UserResponse> updateStatus(Long id) throws NotFoundException {
        var response = userUseCase.updateStatus(id);
        return new ResponseEntity<>(userMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @PatchMapping("/password/{id}")
    @Override
    public ResponseEntity<Void> updatePassword(Long id, String currentPassword, String newPassword) throws NotFoundException, BusinessException {
        userUseCase.updatePassword(id, currentPassword, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/identification")
    @Override
    public ResponseEntity<UserResponse> findByIdentification(String documentNumber, Integer documentTypeId) throws NotFoundException {
        var response = userUseCase.getByIdentification(documentNumber, documentTypeId);
        return new ResponseEntity<>(userMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/authentication")
    @Override
    public ResponseEntity<UserResponse> findByAuthentication(String email, String password) throws NotFoundException {
        var response = userUseCase.getByAuthentication(email, password);
        return new ResponseEntity<>(userMapperApi.domainToResponse(response), HttpStatus.OK);
    }

    @GetMapping("/email")
    @Override
    public ResponseEntity<UserResponse> findByEmail(String email) throws NotFoundException {
        var response = userUseCase.getByEmail(email);
        return new ResponseEntity<>(userMapperApi.domainToResponse(response), HttpStatus.OK);
    }
}

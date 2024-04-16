package com.co.unitravel.infrastructure.adapters.in.rest.controllers;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.infrastructure.adapters.in.rest.configuration.UserApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.UserRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.UserResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.UserMapperApi;
import com.co.unitravel.infrastructure.ports.in.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.user}")
public class UserController implements UserApi {

    private final UserUseCase userUseCase;

    private final UserMapperApi userMapperApi;

    @PostMapping
    @Override
    public ResponseEntity<UserResponse> save(UserRequest userRequest) throws BusinessException {
        var response = userUseCase.create(userMapperApi.requestToDomain(userRequest));
        return new ResponseEntity<>(userMapperApi.domainToResponse(response), HttpStatus.CREATED);
    }
}

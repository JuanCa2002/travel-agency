package com.co.unitravel.infrastructure.adapters.in.rest.controllers;


import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.configuration.CommentApi;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.CommentRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.CommentResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.mappers.CommentMapperApi;
import com.co.unitravel.infrastructure.ports.in.comment.CommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.comment}")
public class CommentController implements CommentApi {

    private final CommentMapperApi commentMapperApi;

    private final CommentUseCase commentUseCase;

    @PostMapping
    @Override
    public ResponseEntity<CommentResponse> save(CommentRequest commentRequest) throws NotFoundException, BusinessException {
        var response = commentUseCase.create(commentMapperApi.requestToDomain(commentRequest));
        return new ResponseEntity<>(commentMapperApi.domainToResponse(response), HttpStatus.CREATED);
    }
}

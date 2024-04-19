package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.CommentRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.CommentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommentApi {

    @Operation(summary = "Create new comment", description = "Add a new comment", tags = {"comment"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New comment added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponse.class)))})
    ResponseEntity<CommentResponse> save(@Valid @RequestBody CommentRequest commentRequest) throws NotFoundException, BusinessException;
}

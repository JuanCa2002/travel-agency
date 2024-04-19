package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.CommentRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.CommentUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.CityResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.CommentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CommentApi {

    @Operation(summary = "Create new comment", description = "Add a new comment", tags = {"comment"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New comment added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponse.class)))})
    ResponseEntity<CommentResponse> save(@Valid @RequestBody CommentRequest commentRequest) throws NotFoundException, BusinessException;

    @Operation(summary = "Update a comment by id", description = "Update an existing comment by its id", tags = {"comment"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated comment", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponse.class)))})
    ResponseEntity<CommentResponse> update(@Valid @RequestBody CommentUpdateRequest commentUpdateRequest) throws NotFoundException;

    @Operation(summary = "Update status of a comment by id", description = "Update status of an existing comment by its id", tags = {"comment"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "comment status updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponse.class)))})
    ResponseEntity<CommentResponse> updateStatus(Long id) throws NotFoundException;

    @Operation(summary = "Find comments by accommodation", description = "Find a list of comments by accommodation", tags = {"comment"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Comment list", content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = CommentResponse.class))))})
    ResponseEntity<List<CommentResponse>> findByAccommodation(@PathVariable Long accommodationId);

    @Operation(summary = "Find comments by customer", description = "Find a list of comments by customer", tags = {"comment"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Comment list", content = @Content(mediaType = "application/json", array = @ArraySchema( schema = @Schema(implementation = CommentResponse.class))))})
    ResponseEntity<List<CommentResponse>> findByCustomer(@PathVariable Long customerId);
}

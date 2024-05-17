package com.co.unitravel.infrastructure.adapters.in.rest.configuration;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DocumentTypeRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DocumentTypeUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DocumentTypeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
public interface DocumentTypeApi {

    @Operation(summary = "Create new document type", description = "Add a new document type", tags = {"document-type"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "New document type added successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DocumentTypeResponse.class)))})
    ResponseEntity<DocumentTypeResponse> save(@Valid @RequestBody DocumentTypeRequest documentTypeRequest) throws BusinessException;

    @Operation(summary = "Update a document type", description = "Update a existing document type by its id", tags = {"document-type"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated document type", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DocumentTypeResponse.class)))})
    ResponseEntity<DocumentTypeResponse> update(@Valid @RequestBody DocumentTypeUpdateRequest documentTypeUpdateRequest) throws NotFoundException, BusinessException;

    @Operation(summary = "Find a document type by id", description = "Find document type by its id", tags = {"document-type"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found document type", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DocumentTypeResponse.class)))})
    ResponseEntity<DocumentTypeResponse> findById(@PathVariable Integer id) throws NotFoundException;

    @Operation(summary = "Find all document types", description = "Find a complete list of document types", tags = {"document-type"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All document types", content = @Content(mediaType = "application/json", array= @ArraySchema(schema = @Schema(implementation = DocumentTypeResponse.class))))})
    ResponseEntity<List<DocumentTypeResponse>> findAll();
}

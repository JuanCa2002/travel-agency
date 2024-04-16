package com.co.unitravel.infrastructure.adapters.in.rest.mappers;

import com.co.unitravel.domain.models.DocumentType;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DocumentTypeRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DocumentTypeUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DocumentTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentTypeMapperApi {

    DocumentType requestToDomain(DocumentTypeRequest request);

    DocumentTypeResponse domainToResponse(DocumentType domain);

    DocumentType updateRequestToDomain(DocumentTypeUpdateRequest updateRequest);

    List<DocumentTypeResponse> domainsToResponses(List<DocumentType> domains);
}

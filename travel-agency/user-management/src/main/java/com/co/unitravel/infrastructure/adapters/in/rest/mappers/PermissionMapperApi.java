package com.co.unitravel.infrastructure.adapters.in.rest.mappers;

import com.co.unitravel.domain.models.Permission;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.PermissionRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.PermissionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapperApi {

    Permission requestToDomain(PermissionRequest request);

    List<PermissionResponse> domainsToResponses(List<Permission> domains);

    PermissionResponse domainToResponse(Permission domain);
}

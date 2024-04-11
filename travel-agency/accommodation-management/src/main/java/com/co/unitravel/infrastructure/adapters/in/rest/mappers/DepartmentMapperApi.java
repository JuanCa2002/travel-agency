package com.co.unitravel.infrastructure.adapters.in.rest.mappers;

import com.co.unitravel.domain.models.Department;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DepartmentRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DepartmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapperApi {

    Department requestToDomain(DepartmentRequest request);

    DepartmentResponse domainToResponse(Department domain);

    List<DepartmentResponse> domainsToResponses(List<Department> domains);
}

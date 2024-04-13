package com.co.unitravel.infrastructure.adapters.in.rest.mappers;

import com.co.unitravel.domain.models.City;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.CityRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.CityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapperApi {

    @Mapping(target = "department.id", source = "departmentId")
    City requestToDomain(CityRequest request);

    CityResponse domainToResponse(City city);

}

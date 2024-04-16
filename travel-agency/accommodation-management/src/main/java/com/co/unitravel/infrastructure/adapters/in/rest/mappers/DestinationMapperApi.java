package com.co.unitravel.infrastructure.adapters.in.rest.mappers;

import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DestinationRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.DestinationUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.DestinationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DestinationMapperApi {

    @Mapping(target = "city.id", source = "cityId")
    Destination requestToDomain(DestinationRequest request);

    DestinationResponse domainToResponse(Destination destination);

    List<DestinationResponse> domainsToResponses(List<Destination> domains);

    Destination updateRequestToDomain(DestinationUpdateRequest destinationUpdateRequest);
}

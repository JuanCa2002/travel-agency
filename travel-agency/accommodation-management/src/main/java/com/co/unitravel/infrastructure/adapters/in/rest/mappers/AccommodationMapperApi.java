package com.co.unitravel.infrastructure.adapters.in.rest.mappers;

import com.co.unitravel.domain.models.Accommodation;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.AccommodationRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.AccommodationUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.AccommodationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccommodationMapperApi {

    @Mapping(target = "destination.id", source = "destinationId")
    Accommodation requestToDomain(AccommodationRequest request);

    AccommodationResponse domainToResponse(Accommodation accommodation);

    List<AccommodationResponse> domainsToResponses(List<Accommodation> domains);

    Accommodation updateRequestToDomain(AccommodationUpdateRequest accommodationUpdateRequest);
}

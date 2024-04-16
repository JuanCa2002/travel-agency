package com.co.unitravel.infrastructure.adapters.in.rest.mappers;

import com.co.unitravel.domain.models.User;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.UserRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapperApi {

    User requestToDomain(UserRequest userRequest);

    UserResponse domainToResponse(User user);
}

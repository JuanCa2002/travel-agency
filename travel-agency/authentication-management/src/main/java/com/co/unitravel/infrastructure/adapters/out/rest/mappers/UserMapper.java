package com.co.unitravel.infrastructure.adapters.out.rest.mappers;

import com.co.unitravel.domain.models.User;
import com.co.unitravel.domain.models.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User responseToDomain(UserResponse userResponse);
}

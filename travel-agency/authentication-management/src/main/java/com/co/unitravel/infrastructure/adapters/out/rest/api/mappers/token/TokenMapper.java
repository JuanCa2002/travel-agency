package com.co.unitravel.infrastructure.adapters.out.rest.api.mappers.token;

import com.co.unitravel.domain.models.Token;
import com.co.unitravel.infrastructure.adapters.out.rest.api.response.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TokenMapper {

    Token responseToDomain(TokenResponse tokenResponse);
}

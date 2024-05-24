package com.co.unitravel.infrastructure.adapters.in.rest.mappers;

import com.co.unitravel.domain.models.Login;
import com.co.unitravel.domain.models.RefreshToken;
import com.co.unitravel.domain.models.Token;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.LoginRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.RefreshTokenRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthenticationMapperApi {

    Login loginRequestToDomain(LoginRequest loginRequest);

    TokenResponse domainToTokenResponse(Token token);

    RefreshToken refreshTokenRequestToDomain(RefreshTokenRequest refreshTokenRequest);
}

package com.co.unitravel.infrastructure.adapters.`in`.rest.mappers

import com.co.unitravel.domain.models.Airplane
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.AirplaneRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.AirplaneResponse
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface AirplaneMapperApi {

    fun requestToDomain(airplaneRequest: AirplaneRequest): Airplane;

    fun domainToResponse(airplane: Airplane): AirplaneResponse;

}
package com.co.unitravel.infrastructure.adapters.out.rest.`in`.mappers

import com.co.unitravel.domain.models.client.City
import com.co.unitravel.infrastructure.adapters.out.rest.`in`.responses.CityResponse
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CityMapperClient {

    fun responseToDomain(cityResponse: CityResponse): City
}
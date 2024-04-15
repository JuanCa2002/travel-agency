package com.co.unitravel.infrastructure.adapters.`in`.rest.mappers

import com.co.unitravel.domain.models.Flight
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.FlightRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.FlightUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.FlightResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface FlightMapperApi {

    @Mapping(target = "airplane.id", source= "airplaneId")
    fun requestToDomain(flightRequest: FlightRequest): Flight;

    fun domainToResponse(flight: Flight): FlightResponse;

    fun domainsToResponses(flights: List<Flight>): List<FlightResponse>

    fun updateRequestToDomain(flightUpdateRequest: FlightUpdateRequest): Flight
}
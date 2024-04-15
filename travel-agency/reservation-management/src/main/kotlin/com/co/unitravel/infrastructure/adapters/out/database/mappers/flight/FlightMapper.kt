package com.co.unitravel.infrastructure.adapters.out.database.mappers.flight

import com.co.unitravel.domain.models.Flight
import com.co.unitravel.infrastructure.adapters.out.database.entities.FlightEntity
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface FlightMapper {

    fun domainToEntity(flight: Flight): FlightEntity

    fun entityToDomain(flightEntity: FlightEntity): Flight

    fun entitiesToDomains(flightEntities: List<FlightEntity>): List<Flight>

    fun mergerToEntity(@MappingTarget target: FlightEntity, source: Flight)
}
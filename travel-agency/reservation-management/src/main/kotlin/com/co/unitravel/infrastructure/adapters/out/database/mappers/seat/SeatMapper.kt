package com.co.unitravel.infrastructure.adapters.out.database.mappers.seat

import com.co.unitravel.domain.models.Seat
import com.co.unitravel.infrastructure.adapters.out.database.entities.SeatEntity
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface SeatMapper {

    fun domainToEntity(seat: Seat): SeatEntity

    fun entityToDomain(seatEntity: SeatEntity): Seat

    fun entitiesToDomains(seatEntities: List<SeatEntity>): List<Seat>

    fun mergerToEntity(@MappingTarget target: SeatEntity, source: Seat)

}
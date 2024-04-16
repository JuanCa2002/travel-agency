package com.co.unitravel.infrastructure.adapters.out.database.mappers.reservation

import com.co.unitravel.domain.models.Reservation
import com.co.unitravel.infrastructure.adapters.out.database.entities.ReservationEntity
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface ReservationMapper {

    fun domainToEntity(reservation: Reservation): ReservationEntity

    fun entityToDomain(reservationEntity: ReservationEntity): Reservation

    fun entitiesToDomains(reservationEntities: List<ReservationEntity>): List<Reservation>

    fun mergerToEntity(@MappingTarget target: ReservationEntity, source: Reservation)
}
package com.co.unitravel.infrastructure.adapters.out.database.mappers.airplane

import com.co.unitravel.domain.models.Airplane
import com.co.unitravel.infrastructure.adapters.out.database.entities.AirplaneEntity
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
 interface AirplaneMapper {

    fun domainToEntity(airplanePort: Airplane): AirplaneEntity

    fun entityToDomain(airplaneEntity: AirplaneEntity): Airplane
}
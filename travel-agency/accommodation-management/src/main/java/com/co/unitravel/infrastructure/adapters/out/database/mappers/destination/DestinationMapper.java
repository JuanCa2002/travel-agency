package com.co.unitravel.infrastructure.adapters.out.database.mappers.destination;

import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.infrastructure.adapters.out.database.entities.DestinationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DestinationMapper {

    Destination entityToDomain(DestinationEntity entity);

    DestinationEntity domainToEntity(Destination domain);
}

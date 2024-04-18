package com.co.unitravel.infrastructure.adapters.out.database.mappers.accommodation;

import com.co.unitravel.domain.models.Accommodation;
import com.co.unitravel.infrastructure.adapters.out.database.entities.AccommodationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccommodationMapper {

    Accommodation entityToDomain(AccommodationEntity entity);

    AccommodationEntity domainToEntity(Accommodation domain);

    void mergeToEntity(@MappingTarget AccommodationEntity target, Accommodation source);

    List<Accommodation> entitiesToDomains(List<AccommodationEntity> entityList);
}

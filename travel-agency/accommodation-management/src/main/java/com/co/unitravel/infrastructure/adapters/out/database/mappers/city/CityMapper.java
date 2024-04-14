package com.co.unitravel.infrastructure.adapters.out.database.mappers.city;

import com.co.unitravel.domain.models.City;
import com.co.unitravel.infrastructure.adapters.out.database.entities.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityMapper {

    City entityToDomain(CityEntity city);

    CityEntity domainToEntity(City city);

    List<City> entitiesToDomains(List<CityEntity> entityList);
}

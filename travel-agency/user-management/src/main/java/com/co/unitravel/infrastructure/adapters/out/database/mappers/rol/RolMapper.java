package com.co.unitravel.infrastructure.adapters.out.database.mappers.rol;

import com.co.unitravel.domain.models.Rol;
import com.co.unitravel.infrastructure.adapters.out.database.entities.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RolMapper {

    RolEntity domainToEntity(Rol domain);

    Rol entityToDomain(RolEntity entity);

    List<Rol> entitiesToDomains(List<RolEntity> entityList);

    void mergeToEntity(@MappingTarget RolEntity target, Rol source);
}

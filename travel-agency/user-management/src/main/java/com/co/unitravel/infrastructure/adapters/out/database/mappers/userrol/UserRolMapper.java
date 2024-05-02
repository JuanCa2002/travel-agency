package com.co.unitravel.infrastructure.adapters.out.database.mappers.userrol;

import com.co.unitravel.domain.models.UserRol;
import com.co.unitravel.infrastructure.adapters.out.database.entities.UserRolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserRolMapper {

    List<UserRolEntity> domainsToEntities(List<UserRol> userRolList);

    List<UserRol> entitiesToDomains(List<UserRolEntity> userRolEntities);
}

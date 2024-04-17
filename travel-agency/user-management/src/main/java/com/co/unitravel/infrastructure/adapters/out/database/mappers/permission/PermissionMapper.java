package com.co.unitravel.infrastructure.adapters.out.database.mappers.permission;

import com.co.unitravel.domain.models.Permission;
import com.co.unitravel.infrastructure.adapters.out.database.entities.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PermissionMapper {

    Permission entityToDomain(PermissionEntity entity);

    PermissionEntity domainToEntity(Permission domain);

    List<Permission> entitiesToDomains(List<PermissionEntity> entities);
}

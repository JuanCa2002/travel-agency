package com.co.unitravel.infrastructure.adapters.out.database.mappers.rolpermission;

import com.co.unitravel.domain.models.RolPermission;
import com.co.unitravel.infrastructure.adapters.out.database.entities.RolPermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RolPermissionMapper {

    RolPermissionEntity domainToEntity(RolPermission domain);

    RolPermission entityToDomain(RolPermissionEntity entity);

    List<RolPermissionEntity> domainsToEntities(List<RolPermission> domains);

    List<RolPermission> entitiesToDomains(List<RolPermissionEntity> entities);
}

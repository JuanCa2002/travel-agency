package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.domain.models.RolPermission;
import com.co.unitravel.infrastructure.adapters.out.database.entities.RolPermissionEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.rolpermission.RolPermissionMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.RolPermissionRepository;
import com.co.unitravel.infrastructure.ports.out.rolpermission.RolPermissionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RolPermissionAdapter implements RolPermissionPort {

    private final RolPermissionRepository rolPermissionRepository;

    private final RolPermissionMapper rolPermissionMapper;
    @Override
    public List<RolPermission> saveAll(List<RolPermission> rolPermissions) {
        List<RolPermissionEntity> savedList = rolPermissionRepository.saveAll(rolPermissionMapper.domainsToEntities(rolPermissions));
        return rolPermissionMapper.entitiesToDomains(savedList);
    }
}

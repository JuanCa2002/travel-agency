package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.domain.models.Permission;
import com.co.unitravel.infrastructure.adapters.out.database.entities.PermissionEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.permission.PermissionMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.PermissionRepository;
import com.co.unitravel.infrastructure.ports.out.permission.PermissionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionAdapter implements PermissionPort {

    private final PermissionRepository permissionRepository;

    private final PermissionMapper permissionMapper;

    @Override
    public Permission save(Permission permission) {
        PermissionEntity savedPermission = permissionRepository.save(permissionMapper.domainToEntity(permission));
        return permissionMapper.entityToDomain(savedPermission);
    }
}

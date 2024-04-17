package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.application.exceptions.permission.PermissionErrorCodes;
import com.co.unitravel.application.exceptions.permission.PermissionNotFoundException;
import com.co.unitravel.domain.models.Permission;
import com.co.unitravel.infrastructure.adapters.out.database.entities.PermissionEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.permission.PermissionMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.PermissionRepository;
import com.co.unitravel.infrastructure.ports.out.permission.PermissionPort;
import com.ctc.wstx.cfg.ParsingErrorMsgs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<Permission> findByIds(List<Long> ids) {
        List<PermissionEntity> permissionEntities = permissionRepository.findAllById(ids);
        return permissionMapper.entitiesToDomains(permissionEntities);
    }

    @Override
    public List<Permission> findByRole(Long roleId) throws NotFoundException {
        PermissionNotFoundException errorNotFound = new PermissionNotFoundException();
        errorNotFound.addError(PermissionErrorCodes.PERMISSION_BY_ROL_NOT_FOUND, new Object[]{roleId});
        List<PermissionEntity> permissions = permissionRepository.findPermissionById(roleId);
        if(permissions.isEmpty()) throw errorNotFound;
        return permissionMapper.entitiesToDomains(permissions);
    }

    @Override
    public void existsById(Long id) throws NotFoundException {
        PermissionNotFoundException errorNotFound = new PermissionNotFoundException();
        errorNotFound.addError(PermissionErrorCodes.PERMISSION_NOT_FOUND, new Object[]{id});
        boolean confirmation = permissionRepository.existsById(id);
        if(!confirmation) throw errorNotFound;
    }
}

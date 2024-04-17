package com.co.unitravel.application.usecases;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Permission;
import com.co.unitravel.infrastructure.ports.in.permission.PermissionUseCase;
import com.co.unitravel.infrastructure.ports.out.permission.PermissionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionUseImpl implements PermissionUseCase {

    private final PermissionPort permissionPort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Permission create(Permission permission) {
        permission.setId(null);
        permission.setName(permission.getName().toUpperCase());
        return permissionPort.save(permission);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Permission> getByRole(Long roleId) throws NotFoundException {
        return permissionPort.findByRole(roleId);
    }
}

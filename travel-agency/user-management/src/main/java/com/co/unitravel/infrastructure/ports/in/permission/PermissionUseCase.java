package com.co.unitravel.infrastructure.ports.in.permission;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Permission;

import java.util.List;

public interface PermissionUseCase {

    Permission create(Permission permission);

    List<Permission> getByRole(Long roleId) throws NotFoundException;
}

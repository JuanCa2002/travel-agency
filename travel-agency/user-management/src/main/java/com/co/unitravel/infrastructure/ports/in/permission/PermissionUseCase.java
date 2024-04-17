package com.co.unitravel.infrastructure.ports.in.permission;

import com.co.unitravel.domain.models.Permission;

public interface PermissionUseCase {

    Permission create(Permission permission);
}

package com.co.unitravel.infrastructure.ports.out.permission;

import com.co.unitravel.domain.models.Permission;

public interface PermissionPort {

    Permission save(Permission permission);
}

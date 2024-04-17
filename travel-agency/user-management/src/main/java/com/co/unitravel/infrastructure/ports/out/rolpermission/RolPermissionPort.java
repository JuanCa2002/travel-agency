package com.co.unitravel.infrastructure.ports.out.rolpermission;

import com.co.unitravel.domain.models.RolPermission;

import java.util.List;

public interface RolPermissionPort {

    List<RolPermission> saveAll(List<RolPermission> rolPermissions);
}

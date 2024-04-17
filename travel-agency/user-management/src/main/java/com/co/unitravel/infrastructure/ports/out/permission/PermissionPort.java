package com.co.unitravel.infrastructure.ports.out.permission;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Permission;

import java.util.List;

public interface PermissionPort {

    Permission save(Permission permission);

    List<Permission> findByIds(List<Long> ids);

    List<Permission> findByRole(Long roleId) throws NotFoundException;

    void existsById(Long id) throws NotFoundException;
}

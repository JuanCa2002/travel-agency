package com.co.unitravel.application.usecases;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Permission;
import com.co.unitravel.domain.models.Rol;
import com.co.unitravel.domain.models.RolPermission;
import com.co.unitravel.infrastructure.ports.in.rol.RolUseCase;
import com.co.unitravel.infrastructure.ports.out.permission.PermissionPort;
import com.co.unitravel.infrastructure.ports.out.rol.RolPort;
import com.co.unitravel.infrastructure.ports.out.rolpermission.RolPermissionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RolUseCaseImpl implements RolUseCase {
    private final RolPort rolPort;

    private final RolPermissionPort rolPermissionPort;

    private final PermissionPort permissionPort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Rol create(Rol rol) throws NotFoundException{
        Rol savedRol = rolPort.save(rol);
        List<RolPermission> rolPermissions = createListRolPermission(rol.getPermissionList(), savedRol);
        List<RolPermission> savedRolPermissions = rolPermissionPort.saveAll(rolPermissions);
        savedRol.setPermissionList(permissionPort.findByIds(getPermissionList(savedRolPermissions)));
        return savedRol;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Rol> getAll() {
        return rolPort.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Rol findById(Long id) throws NotFoundException {
        return rolPort.findById(id);
    }


    private List<RolPermission> createListRolPermission(List<Permission> permissions, Rol rol) throws NotFoundException {
        List<RolPermission> rolPermissions = new ArrayList<>(permissions.size());
        for(Permission permission: permissions){
            RolPermission rolPermission = new RolPermission();
            permissionPort.existsById(permission.getId());
            rolPermission.setPermission(permission);
            rolPermission.setRol(rol);
            rolPermissions.add(rolPermission);
        }
        return rolPermissions;
    }

    private List<Long> getPermissionList(List<RolPermission> rolPermissions){
        return rolPermissions.stream()
                .map(rolPermission -> rolPermission.getPermission().getId())
                .toList();
    }
}

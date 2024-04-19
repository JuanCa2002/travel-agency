package com.co.unitravel.application.usecases;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.application.exceptions.rol.RolBusinessException;
import com.co.unitravel.application.exceptions.rol.RolErrorCodes;
import com.co.unitravel.domain.models.Permission;
import com.co.unitravel.domain.models.Rol;
import com.co.unitravel.domain.models.RolPermission;
import com.co.unitravel.domain.models.RolUpdate;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RolUseCaseImpl implements RolUseCase {
    private final RolPort rolPort;

    private final RolPermissionPort rolPermissionPort;

    private final PermissionPort permissionPort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Rol create(Rol rol) throws NotFoundException, BusinessException {
        RolBusinessException error = new RolBusinessException();
        error.addError(RolErrorCodes.ROL_NAME_ALREADY_EXISTS, new Object[]{rol.getName()});
        if(rolPort.existsByName(rol.getName())) throw error;
        rol.setName(rol.getName().toUpperCase());
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

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {BusinessException.class, NotFoundException.class})
    @Override
    public Rol update(RolUpdate rolUpdate) throws NotFoundException, BusinessException {
        RolBusinessException error = new RolBusinessException();
        Rol rol = new Rol();
        rol.setId(rolUpdate.getId());
        rol.setName(rolUpdate.getName()!= null ? rolUpdate.getName().toUpperCase(): null);
        error.addError(RolErrorCodes.ROL_NAME_ALREADY_EXISTS, new Object[]{rol.getName()});
        if(rolPort.existsByName(rol.getName())) throw error;
        Rol updatedRol = rolPort.update(rol);
        if(!rolUpdate.getPermissionsToRemove().isEmpty()){
            error = new RolBusinessException();
            List<RolPermission> validPermissions = findValidRolPermissionByRol(rolUpdate.getPermissionsToRemove(),rolUpdate.getId());
            if(validPermissions.size() != rolUpdate.getPermissionsToRemove().size()){
                error.addError(RolErrorCodes.ROL_INVALID_PERMISSIONS_TO_REMOVE, null);
                throw error;
            }
            rolPermissionPort.removeAll(validPermissions);
        }
        if(!rolUpdate.getPermissionsToAdd().isEmpty()){
            List<RolPermission> rolPermissions = createListRolPermission(rolUpdate.getPermissionsToAdd(), rol);
            rolPermissionPort.saveAll(rolPermissions);
        }
        List<Permission> permissionsByRole = permissionPort.findByRole(rol.getId());
        updatedRol.setPermissionList(permissionsByRole);
        return updatedRol;
    }

    private List<RolPermission> findValidRolPermissionByRol(List<Permission> permissionsIds, Long rolId){
        List<RolPermission> rolPermissions = rolPermissionPort.findByRole(rolId);
        return rolPermissions.stream().filter(rolPermission ->
                permissionsIds.stream().anyMatch(permission -> Objects.equals(rolPermission.getPermission().getId(), permission.getId()))).toList();
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

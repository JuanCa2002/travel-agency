package com.co.unitravel.infrastructure.adapters.in.rest.mappers;

import com.co.unitravel.domain.models.Permission;
import com.co.unitravel.domain.models.Rol;
import com.co.unitravel.domain.models.RolUpdate;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.RolRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.RolUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.GetRolResponse;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.RolResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RolMapperApi {

    default Rol requestToDomain(RolRequest request){
        if(request == null){
            return null;
        }
        Rol rol = new Rol();
        rol.setName(request.getName());
        rol.setPermissionList(longListToPermissions(request.getPermissionList()));
        return rol;
    }

    default RolUpdate updateRequestToUpdateDomain(RolUpdateRequest rolUpdateRequest){
        if(rolUpdateRequest == null){
            return null;
        }
        RolUpdate rolUpdate = new RolUpdate();
        rolUpdate.setId(rolUpdateRequest.getId());
        rolUpdate.setName(rolUpdateRequest.getName());
        rolUpdate.setPermissionsToAdd(longListToPermissions(rolUpdateRequest.getPermissionsToAdd()));
        rolUpdate.setPermissionsToRemove(longListToPermissions(rolUpdateRequest.getPermissionsToRemove()));
        return rolUpdate;
    }

    default List<Permission> longListToPermissions(List<Long> permissionIds){
        if(permissionIds == null || permissionIds.isEmpty()){
            return Collections.emptyList();
        }
        List<Permission> permissions = new ArrayList<>(permissionIds.size());
        for(Long id: permissionIds){
            Permission permission = new Permission();
            permission.setId(id);
            permissions.add(permission);
        }
        return  permissions;
    }

    RolResponse domainToResponse(Rol rol);

    GetRolResponse domainToGetResponse(Rol rol);

    List<GetRolResponse> domainsToGetResponses(List<Rol> roles);
}

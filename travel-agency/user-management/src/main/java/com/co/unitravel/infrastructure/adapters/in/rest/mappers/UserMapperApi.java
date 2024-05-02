package com.co.unitravel.infrastructure.adapters.in.rest.mappers;

import com.co.unitravel.domain.models.DocumentType;
import com.co.unitravel.domain.models.Rol;
import com.co.unitravel.domain.models.User;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.UserRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.request.UserUpdateRequest;
import com.co.unitravel.infrastructure.adapters.in.rest.controllers.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapperApi {

    default User requestToDomain(UserRequest userRequest){
        if(userRequest == null){
            return null;
        }
        User user = new User();
        user.setName(userRequest.getName());
        user.setLastNames(userRequest.getLastNames());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setDocumentNumber(userRequest.getDocumentNumber());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setCityId(userRequest.getCityId());
        DocumentType documentType = new DocumentType();
        documentType.setId(userRequest.getDocumentTypeId());
        user.setDocumentType(documentType);
        user.setRolList(longListToRoles(userRequest.getRolIds()));
        return user;
    }

    default List<Rol> longListToRoles(List<Long> rolesIds){
        if(rolesIds == null || rolesIds.isEmpty()){
            return Collections.emptyList();
        }
        List<Rol> rolList = new ArrayList<>(rolesIds.size());
        for(Long id: rolesIds){
            Rol rol = new Rol();
            rol.setId(id);
            rolList.add(rol);
        }
        return rolList;
    }

    UserResponse domainToResponse(User user);

    User updateRequestToDomain(UserUpdateRequest userUpdateRequest);
}

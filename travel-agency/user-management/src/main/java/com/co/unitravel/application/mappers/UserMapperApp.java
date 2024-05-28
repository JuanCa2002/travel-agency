package com.co.unitravel.application.mappers;

import com.co.unitravel.domain.models.Credential;
import com.co.unitravel.domain.models.User;
import com.co.unitravel.domain.models.UserKeycloak;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapperApp {

    default UserKeycloak userToUserKeycloak(User user, String token) {
        if(user == null){
            return null;
        }
        UserKeycloak userKeycloak = new UserKeycloak();
        userKeycloak.setEnabled(true);
        userKeycloak.setEmailVerified(true);
        userKeycloak.setUsername(user.getEmail());
        userKeycloak.setEmail(user.getEmail());
        userKeycloak.setFirstName(user.getName());
        userKeycloak.setLastName(user.getLastNames());
        List<Credential> credentials = new ArrayList<>();
        Credential credential = new Credential();
        credential.setType("password");
        credential.setValue("rollback");
        credential.setTemporary(false);
        credentials.add(credential);
        userKeycloak.setCredentials(credentials);
        userKeycloak.setToken(token);
        return userKeycloak;
    }
}

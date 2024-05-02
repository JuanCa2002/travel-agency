package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.domain.models.UserRol;
import com.co.unitravel.infrastructure.adapters.out.database.entities.UserRolEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.userrol.UserRolMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.UserRolRepository;
import com.co.unitravel.infrastructure.ports.out.userrol.UserRolPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRolAdapter implements UserRolPort {

    private final UserRolMapper userRolMapper;

    private final UserRolRepository userRolRepository;
    @Override
    public List<UserRol> saveAll(List<UserRol> userRoles) {
        List<UserRolEntity> userRolEntities = userRolMapper.domainsToEntities(userRoles);
        return userRolMapper.entitiesToDomains(userRolRepository.saveAll(userRolEntities));
    }
}

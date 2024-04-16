package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.domain.models.User;
import com.co.unitravel.infrastructure.adapters.out.database.entities.UserEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.user.UserMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.UserRepository;
import com.co.unitravel.infrastructure.ports.out.user.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserPort {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        UserEntity savedUser = userRepository.save(userMapper.domainToEntity(user));
        return userMapper.entityToDomain(savedUser);
    }

    @Override
    public boolean existsByDocument(String documentNumber) {
        return userRepository.existsByDocument(documentNumber);
    }
}

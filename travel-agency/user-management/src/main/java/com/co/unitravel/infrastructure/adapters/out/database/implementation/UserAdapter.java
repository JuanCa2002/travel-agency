package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.application.exceptions.user.UserErrorCodes;
import com.co.unitravel.application.exceptions.user.UserNotFoundException;
import com.co.unitravel.domain.models.User;
import com.co.unitravel.infrastructure.adapters.out.database.entities.UserEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.user.UserMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.UserRepository;
import com.co.unitravel.infrastructure.ports.out.user.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Override
    public User findById(Long id) throws NotFoundException {
        UserNotFoundException errorNotFound = new UserNotFoundException();
        errorNotFound.addError(UserErrorCodes.USER_NOT_FOUND, new Object[]{id});
        return userMapper.entityToDomain(userRepository.findById(id).orElseThrow(()-> errorNotFound));
    }

    @Override
    public User update(User user) throws NotFoundException {
        UserNotFoundException errorNotFound = new UserNotFoundException();
        errorNotFound.addError(UserErrorCodes.USER_NOT_FOUND, new Object[]{user.getId()});
        UserEntity target = userRepository.findById(user.getId()).orElseThrow(()-> errorNotFound);
        userMapper.mergeToEntity(target, user);
        return userMapper.entityToDomain(userRepository.save(target));
    }

    @Override
    public User findByIdentification(String documentNumber, Integer documentTypeId) throws NotFoundException {
        UserNotFoundException errorNotFound = new UserNotFoundException();
        errorNotFound.addError(UserErrorCodes.USER_NOT_FOUND_BY_IDENTIFICATION, null);
        User user = userMapper.entityToDomain(userRepository.findByIdentification(documentNumber, documentTypeId));
        if(user == null) throw errorNotFound;
        return user;
    }

    @Override
    public User findByAuthentication(String email, String password) throws NotFoundException {
        UserNotFoundException errorNotFound = new UserNotFoundException();
        errorNotFound.addError(UserErrorCodes.USER_NOT_FOUND_BY_AUTHENTICATION, null);
        User user = userMapper.entityToDomain(userRepository.findByEmail(email));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordMatches = encoder.matches(password, user!= null ? user.getPassword():null);
        if(!passwordMatches) throw errorNotFound;
        return user;
    }

    @Override
    public User findByEmail(String email) throws NotFoundException {
        UserNotFoundException errorNotFound = new UserNotFoundException();
        errorNotFound.addError(UserErrorCodes.USER_NOT_FOUND_BY_EMAIL, null);
        User user = userMapper.entityToDomain(userRepository.findByEmail(email));
        if(user == null) throw errorNotFound;
        return user;
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}

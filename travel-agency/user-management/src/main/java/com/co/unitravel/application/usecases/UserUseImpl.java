package com.co.unitravel.application.usecases;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.GeneralApiErrorCodes;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.application.exceptions.user.UserBusinessException;
import com.co.unitravel.application.exceptions.user.UserErrorCodes;
import com.co.unitravel.application.exceptions.user.UserNotFoundException;
import com.co.unitravel.application.mappers.UserMapperApp;
import com.co.unitravel.domain.models.*;
import com.co.unitravel.domain.models.enums.UserStatus;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.user.UserMapper;
import com.co.unitravel.infrastructure.ports.in.user.UserUseCase;
import com.co.unitravel.infrastructure.ports.out.client.in.AuthenticationInClientPort;
import com.co.unitravel.infrastructure.ports.out.client.in.CityInClientPort;
import com.co.unitravel.infrastructure.ports.out.documenttype.DocumentTypePort;
import com.co.unitravel.infrastructure.ports.out.rol.RolPort;
import com.co.unitravel.infrastructure.ports.out.user.UserPort;
import com.co.unitravel.infrastructure.ports.out.userrol.UserRolPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserUseImpl implements UserUseCase {

    private final UserPort userPort;

    private final DocumentTypePort documentTypePort;

    private final CityInClientPort cityInClientPort;

    private final UserRolPort userRolPort;

    private final RolPort rolPort;

    private final UserMapperApp userMapperApp;

    private final AuthenticationInClientPort authenticationInClientPort;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {BusinessException.class, NotFoundException.class})
    @Override
    public User create(User user, String token) throws NotFoundException, BusinessException, JsonProcessingException {
        DocumentType documentType = documentTypePort.findById(user.getDocumentType().getId());
        UserBusinessException error = new UserBusinessException();
        error.addError(UserErrorCodes.USER_DOCUMENT_NUMBER_ALREADY_EXISTS, null);
        UserNotFoundException errorNotFound = new UserNotFoundException();
        errorNotFound.addError(GeneralApiErrorCodes.CITY_NOT_FOUND, new Object[]{user.getCityId()});
        boolean validCity = cityInClientPort.findById(user.getCityId());
        if(!validCity) throw  errorNotFound;
        if(userPort.existsByDocument(user.getDocumentNumber())) throw error;
        String password = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setId(null);
        user.setStatus(UserStatus.ACTIVO);
        user.setDocumentType(documentType);
        User savedUser = userPort.save(user);
        List<UserRol> savedUserRolList = userRolPort.saveAll(getUserRolList(user.getRolList(), savedUser));
        savedUser.setRolList(rolPort.findByIds(getRolIds(savedUserRolList)));
        UserKeycloak userKeycloak = userMapperApp.userToUserKeycloak(user, token, password);
        userKeycloak.setRealmRoles(getRolNames(rolPort.findByIds(getRolIds(savedUserRolList))));
        authenticationInClientPort.registerNewUser(userKeycloak);
        return savedUser;
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(Long id) throws NotFoundException {
        return userPort.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User updateStatus(Long id) throws NotFoundException {
        User user = userPort.findById(id);
        user.setStatus(user.getStatus().equals(UserStatus.ACTIVO) ?  UserStatus.INACTIVO: UserStatus.ACTIVO);
        return userPort.update(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updatePassword(Long id, String currentPassword, String newPassword) throws NotFoundException, BusinessException {
        UserBusinessException error = new UserBusinessException();
        error.addError(UserErrorCodes.USER_PASSWORD_DOES_NOT_MATCH, null);
        User user = userPort.findById(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordMatches = encoder.matches(currentPassword, user.getPassword());
        if(passwordMatches){
            String hashedNewPassword = encoder.encode(newPassword);
            user.setPassword(hashedNewPassword);
            userPort.update(user);
        }else{
            throw error;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User getByIdentification(String documentNumber, Integer documentTypeId) throws NotFoundException {
        return userPort.findByIdentification(documentNumber, documentTypeId);
    }

    @Transactional(readOnly = true)
    @Override
    public User getByAuthentication(String email, String password) throws NotFoundException {
        return userPort.findByAuthentication(email, password);
    }

    @Transactional(readOnly = true)
    @Override
    public User getByEmail(String email) throws NotFoundException {
        return userPort.findByEmail(email);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {BusinessException.class, NotFoundException.class})
    @Override
    public User update(User user) throws NotFoundException, JsonProcessingException {
        UserNotFoundException errorNotFound = new UserNotFoundException();
        errorNotFound.addError(GeneralApiErrorCodes.CITY_NOT_FOUND, new Object[]{user.getCityId()});
        if(user.getCityId()!= null){
            boolean validCity = cityInClientPort.findById(user.getCityId());
            if(!validCity) throw  errorNotFound;
        }
        User updatedUser = new User();
        updatedUser.setId(user.getId());
        updatedUser.setCityId(user.getCityId());
        updatedUser.setStatus(user.getStatus());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        return userPort.update(updatedUser);
    }

    private List<UserRol> getUserRolList(List<Rol> rolList, User user) throws NotFoundException {
        List<UserRol> userRolList = new ArrayList<>(rolList.size());
        for (Rol rol: rolList){
            UserRol userRol = new UserRol();
            rolPort.existsById(rol.getId());
            userRol.setRol(rol);
            userRol.setUser(user);
            userRolList.add(userRol);
        }
        return userRolList;
    }

    private List<Long> getRolIds(List<UserRol> userRolList){
        return userRolList.stream()
                .map(userRol -> userRol.getRol().getId())
                .toList();
    }

    private List<String> getRolNames(List<Rol> rolList){
        return rolList.stream()
                .map(rol -> rol.getName().toLowerCase())
                .toList();
    }
}

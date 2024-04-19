package com.co.unitravel.application.usecases;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.GeneralApiErrorCodes;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.application.exceptions.user.UserBusinessException;
import com.co.unitravel.application.exceptions.user.UserErrorCodes;
import com.co.unitravel.application.exceptions.user.UserNotFoundException;
import com.co.unitravel.domain.models.DocumentType;
import com.co.unitravel.domain.models.User;
import com.co.unitravel.domain.models.enums.UserStatus;
import com.co.unitravel.infrastructure.ports.in.user.UserUseCase;
import com.co.unitravel.infrastructure.ports.out.client.in.CityInClientPort;
import com.co.unitravel.infrastructure.ports.out.documenttype.DocumentTypePort;
import com.co.unitravel.infrastructure.ports.out.user.UserPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUseImpl implements UserUseCase {

    private final UserPort userPort;

    private final DocumentTypePort documentTypePort;

    private final CityInClientPort cityInClientPort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User create(User user) throws NotFoundException, BusinessException, JsonProcessingException {
        DocumentType documentType = documentTypePort.findById(user.getDocumentType().getId());
        UserBusinessException error = new UserBusinessException();
        error.addError(UserErrorCodes.USER_DOCUMENT_NUMBER_ALREADY_EXISTS, null);
        UserNotFoundException errorNotFound = new UserNotFoundException();
        errorNotFound.addError(GeneralApiErrorCodes.CITY_NOT_FOUND, new Object[]{user.getCityId()});
        boolean validCity = cityInClientPort.findById(user.getCityId());
        if(!validCity) throw  errorNotFound;
        if(userPort.existsByDocument(user.getDocumentNumber())) throw error;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setId(null);
        user.setStatus(UserStatus.ACTIVO);
        user.setDocumentType(documentType);
        return userPort.save(user);
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
}

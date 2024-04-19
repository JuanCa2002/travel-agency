package com.co.unitravel.infrastructure.ports.in.user;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserUseCase {

    User create(User user) throws NotFoundException, BusinessException, JsonProcessingException;

    User getById(Long id) throws NotFoundException;

    User updateStatus(Long id) throws NotFoundException;

    void updatePassword(Long id, String currentPassword, String newPassword) throws NotFoundException, BusinessException;

    User getByIdentification(String documentNumber, Integer documentTypeId) throws NotFoundException;

    User getByAuthentication(String email, String password) throws NotFoundException;
    User getByEmail(String email) throws NotFoundException;


}

package com.co.unitravel.infrastructure.ports.out.user;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.User;

public interface UserPort {
     User save(User user);

     boolean existsByDocument(String documentNumber);

     User findById(Long id) throws NotFoundException;

     User update(User user) throws NotFoundException;

     User findByIdentification(String documentNumber, Integer documentTypeId) throws NotFoundException;

     User findByAuthentication(String email, String password) throws NotFoundException;

     User findByEmail(String email) throws NotFoundException;

     boolean existsById(Long id);
}

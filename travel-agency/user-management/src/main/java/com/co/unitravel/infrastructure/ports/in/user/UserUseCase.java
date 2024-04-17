package com.co.unitravel.infrastructure.ports.in.user;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.User;

public interface UserUseCase {

    User create(User user) throws NotFoundException, BusinessException;
}

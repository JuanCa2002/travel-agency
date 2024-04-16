package com.co.unitravel.application.exceptions.user;

import com.co.unitravel.application.exceptions.general.BusinessException;

public class UserBusinessException extends BusinessException {
    public UserBusinessException() {
        super(UserErrorCodes.USER_ERROR_HEADER, UserErrorCodes.USER_INVALID_TRANSACTION);
    }
}

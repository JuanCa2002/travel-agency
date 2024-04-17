package com.co.unitravel.application.exceptions.user;

import com.co.unitravel.application.exceptions.general.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super(UserErrorCodes.USER_ERROR_HEADER, UserErrorCodes.USER_INVALID_TRANSACTION);
    }
}

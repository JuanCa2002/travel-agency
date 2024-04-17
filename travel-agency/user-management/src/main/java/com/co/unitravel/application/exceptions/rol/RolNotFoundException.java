package com.co.unitravel.application.exceptions.rol;

import com.co.unitravel.application.exceptions.general.NotFoundException;

public class RolNotFoundException extends NotFoundException {
    public RolNotFoundException() {
        super(RolErrorCodes.ROL_ERROR_HEADER, RolErrorCodes.ROL_INVALID_TRANSACTION);
    }
}

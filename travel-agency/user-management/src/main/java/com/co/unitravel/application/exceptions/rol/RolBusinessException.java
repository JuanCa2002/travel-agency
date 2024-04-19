package com.co.unitravel.application.exceptions.rol;

import com.co.unitravel.application.exceptions.general.BusinessException;

public class RolBusinessException extends BusinessException {
    public RolBusinessException() {
        super(RolErrorCodes.ROL_ERROR_HEADER, RolErrorCodes.ROL_INVALID_TRANSACTION);
    }
}

package com.co.unitravel.application.exceptions.permission;

import com.co.unitravel.application.exceptions.general.NotFoundException;

public class PermissionNotFoundException extends NotFoundException {
    public PermissionNotFoundException() {
        super(PermissionErrorCodes.PERMISSION_ERROR_HEADER, PermissionErrorCodes.PERMISSION_INVALID_TRANSACTION);
    }
}

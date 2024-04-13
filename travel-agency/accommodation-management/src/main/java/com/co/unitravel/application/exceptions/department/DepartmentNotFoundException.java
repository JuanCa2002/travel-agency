package com.co.unitravel.application.exceptions.department;

import com.co.unitravel.application.exceptions.general.NotFoundException;

public class DepartmentNotFoundException extends NotFoundException {
    public DepartmentNotFoundException() {
        super(DepartmentErrorCodes.DEPARTMENT_ERROR_HEADER, DepartmentErrorCodes.DEPARTMENT_INVALID_TRANSACTION);
    }
}

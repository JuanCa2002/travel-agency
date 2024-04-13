package com.co.unitravel.application.exceptions.department;

import com.co.unitravel.application.exceptions.general.BusinessException;

public class DepartmentBusinessException extends BusinessException {

    public DepartmentBusinessException() {
        super(DepartmentErrorCodes.DEPARTMENT_ERROR_HEADER, DepartmentErrorCodes.DEPARTMENT_INVALID_TRANSACTION);
    }
}

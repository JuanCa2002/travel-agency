package com.co.unitravel.application.exceptions.city;

import com.co.unitravel.application.exceptions.general.BusinessException;

public class CityBusinessException extends BusinessException {
    public CityBusinessException() {
        super(CityErrorCodes.CITY_ERROR_HEADER, CityErrorCodes.CITY_INVALID_TRANSACTION);
    }
}

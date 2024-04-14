package com.co.unitravel.application.exceptions.city;

import com.co.unitravel.application.exceptions.general.NotFoundException;

public class CityNotFoundException extends NotFoundException {
    public CityNotFoundException() {
        super(CityErrorCodes.CITY_ERROR_HEADER, CityErrorCodes.CITY_INVALID_TRANSACTION);
    }
}

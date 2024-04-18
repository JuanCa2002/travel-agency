package com.co.unitravel.application.exceptions.accommodation;

import com.co.unitravel.application.exceptions.general.NotFoundException;

public class AccommodationNotFoundException extends NotFoundException {

    public AccommodationNotFoundException() {
        super(AccommodationErrorCodes.ACCOMMODATION_ERROR_HEADER, AccommodationErrorCodes.ACCOMMODATION_INVALID_TRANSACTION);
    }
}

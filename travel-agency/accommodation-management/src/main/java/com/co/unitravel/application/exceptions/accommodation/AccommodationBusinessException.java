package com.co.unitravel.application.exceptions.accommodation;

import com.co.unitravel.application.exceptions.general.BusinessException;

public class AccommodationBusinessException extends BusinessException {
    public AccommodationBusinessException() {
        super(AccommodationErrorCodes.ACCOMMODATION_ERROR_HEADER, AccommodationErrorCodes.ACCOMMODATION_INVALID_TRANSACTION);
    }
}

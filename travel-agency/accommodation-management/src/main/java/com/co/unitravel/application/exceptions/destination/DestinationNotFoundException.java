package com.co.unitravel.application.exceptions.destination;

import com.co.unitravel.application.exceptions.general.NotFoundException;

public class DestinationNotFoundException extends NotFoundException {
    public DestinationNotFoundException() {
        super(DestinationErrorCodes.DESTINATION_ERROR_HEADER, DestinationErrorCodes.DESTINATION_INVALID_TRANSACTION);
    }
}

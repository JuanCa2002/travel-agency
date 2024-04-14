package com.co.unitravel.application.exceptions.destination;

import com.co.unitravel.application.exceptions.general.BusinessException;

public class DestinationBusinessException extends BusinessException {
    public DestinationBusinessException() {
        super(DestinationErrorCodes.DESTINATION_ERROR_HEADER, DestinationErrorCodes.DESTINATION_INVALID_TRANSACTION);
    }
}

package com.co.unitravel.application.exceptions.airplane

import com.co.unitravel.application.exceptions.general.NotFoundException

open class AirplaneNotFoundException:
        NotFoundException(AirplaneErrorCodes.AIRPLANE_ERROR_HEADER, AirplaneErrorCodes.AIRPLANE_INVALID_TRANSACTION)
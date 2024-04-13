package com.co.unitravel.application.exceptions.airplane

import com.co.unitravel.application.exceptions.general.BusinessException

open class AirplaneBusinessException :
    BusinessException(AirplaneErrorCodes.AIRPLANE_ERROR_HEADER, AirplaneErrorCodes.AIRPLANE_INVALID_TRANSACTION)
package com.co.unitravel.application.exceptions.seat

import com.co.unitravel.application.exceptions.general.BusinessException

open class SeatBusinessException: BusinessException(SeatErrorCodes.SEAT_ERROR_HEADER, SeatErrorCodes.SEAT_INVALID_TRANSACTION)
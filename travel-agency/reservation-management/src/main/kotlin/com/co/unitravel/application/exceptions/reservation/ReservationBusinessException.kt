package com.co.unitravel.application.exceptions.reservation

import com.co.unitravel.application.exceptions.general.BusinessException

open class ReservationBusinessException
    : BusinessException(ReservationErrorCodes.RESERVATION_ERROR_HEADER, ReservationErrorCodes.RESERVATION_INVALID_TRANSACTION)
package com.co.unitravel.application.exceptions.seat

import com.co.unitravel.application.exceptions.airplane.AirplaneErrorCodes
import com.co.unitravel.application.exceptions.general.NotFoundException

open class SeatNotFoundException: NotFoundException(SeatErrorCodes.SEAT_ERROR_HEADER, SeatErrorCodes.SEAT_INVALID_TRANSACTION)


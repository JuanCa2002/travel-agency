package com.co.unitravel.application.exceptions.reservation

import com.co.unitravel.application.exceptions.flight.FlightErrorCodes
import com.co.unitravel.application.exceptions.general.NotFoundException

open class ReservationNotFoundException: NotFoundException(ReservationErrorCodes.RESERVATION_ERROR_HEADER, ReservationErrorCodes.RESERVATION_INVALID_TRANSACTION) {
}
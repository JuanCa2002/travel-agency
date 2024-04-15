package com.co.unitravel.application.exceptions.flight

import com.co.unitravel.application.exceptions.general.NotFoundException
import com.co.unitravel.application.exceptions.seat.SeatErrorCodes

open class FlightNotFoundException:  NotFoundException(FlightErrorCodes.FLIGHT_ERROR_HEADER, FlightErrorCodes.FLIGHT_INVALID_TRANSACTION) {
}
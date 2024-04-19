package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response

import com.co.unitravel.domain.models.Flight
import com.co.unitravel.domain.models.enums.ReservationPaymentMethod
import com.co.unitravel.domain.models.enums.ReservationStatus
import java.time.LocalDate

data class ReservationResponse(

    var id: Long? = null,

    var accommodationId: Long? = null,

    var customerId: Long? = null,

    var flight: Flight? = Flight(),

    var reservationDate: LocalDate? = null,

    var checkInDate: LocalDate? = null,

    var checkOutDate: LocalDate? = null,

    var paymentMethod: ReservationPaymentMethod? = null,

    var numberPeople: Int? = null,

    var reservationStatus: ReservationStatus? = null,

    var finalPrice: Long? = null
)

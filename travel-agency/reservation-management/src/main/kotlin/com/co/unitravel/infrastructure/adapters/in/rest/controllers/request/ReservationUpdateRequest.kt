package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request

import com.co.unitravel.domain.models.Flight
import com.co.unitravel.domain.models.enums.ReservationPaymentMethod
import com.co.unitravel.domain.models.enums.ReservationStatus
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class ReservationUpdateRequest(

    @field:NotNull
    var id: Long,

    var flightId: Long?,

    var checkInDate: LocalDate?,

    var checkOutDate: LocalDate?,

    var paymentMethod: ReservationPaymentMethod?,

    var reservationStatus: ReservationStatus?

)

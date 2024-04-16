package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request

import com.co.unitravel.domain.models.Flight
import com.co.unitravel.domain.models.enums.ReservationPaymentMethod
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class ReservationRequest(

    @field:NotNull
    var accommodationId: Long,

    @field:NotNull
    var customerId: Long,

    @field:NotNull
    var flightId: Long,

    @field:NotNull
    var checkInDate: LocalDate,

    @field:NotNull
    var checkOutDate: LocalDate,

    @field:NotNull
    var paymentMethod: ReservationPaymentMethod,

    @field:NotNull
    var numberPeople: String

)

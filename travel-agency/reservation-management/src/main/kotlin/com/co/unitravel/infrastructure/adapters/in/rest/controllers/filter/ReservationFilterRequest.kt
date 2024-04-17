package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.filter

import com.co.unitravel.domain.models.enums.ReservationStatus
import java.time.LocalDate

data class ReservationFilterRequest(

    var accommodationId: Long?,

    var customerId: Long?,

    var checkInDate: LocalDate?,

    var status: ReservationStatus?
)

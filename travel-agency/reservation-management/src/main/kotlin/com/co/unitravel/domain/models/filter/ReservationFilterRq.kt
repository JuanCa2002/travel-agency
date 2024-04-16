package com.co.unitravel.domain.models.filter

import com.co.unitravel.domain.models.enums.ReservationStatus
import java.time.LocalDate

data class ReservationFilterRq(

    var acocommodationId: Long?,

    var customerId: Long?,

    var checkInDate: LocalDate?,

    var reservationDate: ReservationStatus?

)

package com.co.unitravel.domain.models.filter

import com.co.unitravel.domain.models.enums.FlightStatus
import java.time.LocalDate

data class FlightFilterRq (

    var id: Long?,

    var status: FlightStatus?,

    var initialCityId: Long?,

    var finalCityId: Long?,

    var departureTime: LocalDate?
)
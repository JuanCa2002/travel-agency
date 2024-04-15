package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.filter

import com.co.unitravel.domain.models.enums.FlightStatus
import java.time.LocalDate

data class FlightFilterRequest(
    var id: Long?,

    var status: FlightStatus?,

    var initialCityId: Long?,

    var finalCityId: Long?,

    var departureTime: LocalDate?
)
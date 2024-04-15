package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response

import com.co.unitravel.domain.models.Airplane
import com.co.unitravel.domain.models.enums.FlightStatus
import java.time.LocalDate
import java.time.LocalTime

data class FlightResponse(

    var id: Long? = null,

    var initialCityId: Long? = null,

    var finalCityId: Long? = null,

    var airplane: Airplane? = Airplane(),

    var flightTime: LocalTime? = null,

    var departureTime: LocalDate? = null,

    var estimatedArrivalTime: LocalTime? = null,

    var name: String? = null,

    var price: Int? = null,

    var flightStatus: FlightStatus? = null
)

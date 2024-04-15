package com.co.unitravel.domain.models

import com.co.unitravel.domain.models.enums.FlightStatus
import java.time.LocalDate
import java.time.LocalTime

data class Flight(

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

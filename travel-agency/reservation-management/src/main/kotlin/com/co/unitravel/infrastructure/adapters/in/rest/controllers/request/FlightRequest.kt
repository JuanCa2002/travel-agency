package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request

import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.time.LocalTime

data class FlightRequest(

    @field:NotNull
    var initialCityId: Long,

    @field:NotNull
    var finalCityId: Long,

    @field:NotNull
    var airplaneId: Long,

    @field:NotNull
    var flightTime: LocalTime,

    @field:NotNull
    var departureTime: LocalDate,

    @field:NotNull
    var estimatedArrivalTime: LocalTime,

    @field:NotNull
    var name: String,

    @field:NotNull
    var price: Int
)

package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    var flightTimeString: String,

    @field:NotNull
    var departureTime: LocalDate,

    @field:NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    var estimatedArrivalTimeString: String,

    @field:NotNull
    var name: String,

    @field:NotNull
    var price: Int
) {
    @get:JsonIgnore
    val flightTime: LocalTime
        get() = LocalTime.parse(flightTimeString)

    @get:JsonIgnore
    val estimatedArrivalTime: LocalTime
        get() = LocalTime.parse(estimatedArrivalTimeString)
}
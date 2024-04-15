package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request

import com.co.unitravel.domain.models.enums.FlightStatus
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.time.LocalTime

data class FlightUpdateRequest(

    @field:NotNull
    var id: Long?,

    var initialCityId: Long?,

    var finalCityId: Long?,

    var airplaneId: Long,

    var flightTimeString: String?,

    var departureTime: LocalDate?,

    var estimatedArrivalTimeString: String?,

    var name: String?,

    var price: Int?,

    var flightStatus: FlightStatus?
){
    @get:JsonIgnore
    val flightTime: LocalTime?
        get() = flightTimeString?.let {
            if (it.isNotEmpty()) {
                LocalTime.parse(it)
            } else {
                null
            }
        }

    @get:JsonIgnore
    val estimatedArrivalTime: LocalTime?
        get() = estimatedArrivalTimeString?.let {
            if (it.isNotEmpty()) {
                LocalTime.parse(it)
            } else {
                null
            }
        }
}

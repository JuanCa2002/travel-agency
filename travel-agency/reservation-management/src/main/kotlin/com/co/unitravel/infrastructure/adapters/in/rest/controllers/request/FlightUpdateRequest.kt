package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request

import com.co.unitravel.domain.models.enums.FlightStatus
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.time.LocalTime

data class FlightUpdateRequest(

    @field:NotNull
    var id: Long?,

    var initialCityId: Long?,

    var finalCityId: Long?,

    var airplaneId: Long,

    var flightTime: LocalTime?,

    var departureTime: LocalDate?,

    var estimatedArrivalTime: LocalTime?,

    var name: String?,

    var price: Int?,

    var flightStatus: FlightStatus?
)

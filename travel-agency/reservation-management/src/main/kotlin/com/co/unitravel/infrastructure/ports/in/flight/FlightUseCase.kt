package com.co.unitravel.infrastructure.ports.`in`.flight

import com.co.unitravel.domain.models.Flight
import com.co.unitravel.domain.models.filter.FlightFilterRq
import com.co.unitravel.domain.models.records.PageModel

interface FlightUseCase {

    fun create(flight: Flight): Flight

    fun update(id: Long, flight: Flight): Flight

    fun getByCriteria(flightFilterRq: FlightFilterRq,
                      rowsPerPage: Int,
                      skip: Int): PageModel<List<Flight>>
}
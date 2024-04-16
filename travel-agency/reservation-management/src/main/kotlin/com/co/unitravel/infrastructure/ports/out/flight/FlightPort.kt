package com.co.unitravel.infrastructure.ports.out.flight

import com.co.unitravel.domain.models.Flight
import com.co.unitravel.domain.models.enums.FlightStatus
import com.co.unitravel.domain.models.records.PageModel
import java.time.LocalDate

interface FlightPort {

    fun save(flight: Flight): Flight

    fun update(id: Long, flight: Flight): Flight

    fun findByCriteria(id: Long?,
                       status: FlightStatus?,
                       initialCityId: Long?,
                       finalCityId:Long?,
                       departureTime: LocalDate?,
                       rowsPerPage: Int,
                       skip: Int): PageModel<List<Flight>>

    fun findById(id:Long): Flight

    fun findAll(id: Long?,
                status: FlightStatus?,
                initialCityId: Long?,
                finalCityId:Long?,
                departureTime: LocalDate?):List<Flight>
}
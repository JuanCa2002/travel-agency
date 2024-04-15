package com.co.unitravel.application.usecases

import com.co.unitravel.domain.models.Flight
import com.co.unitravel.domain.models.enums.FlightStatus
import com.co.unitravel.domain.models.records.PageModel
import com.co.unitravel.infrastructure.ports.`in`.flight.FlightUseCase
import com.co.unitravel.infrastructure.ports.out.flight.FlightPort
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

open class FlightUseCaseImpl(private val flightPort: FlightPort): FlightUseCase {

    @Transactional(propagation = Propagation.REQUIRED)
    override fun create(flight: Flight): Flight {
        flight.id = null;
        flight.flightStatus = FlightStatus.PROGRAMADO;
        return flightPort.save(flight);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(id: Long, flight: Flight): Flight {
        return flightPort.update(id, flight);
    }

    @Transactional(readOnly = true)
    override fun getByCriteria(id: Long?, status: FlightStatus?, rowsPerPage: Int, skip: Int): PageModel<List<Flight>> {
        return flightPort.findByCriteria(id!!, status, rowsPerPage, skip)
    }
}
package com.co.unitravel.application.usecases

import com.co.unitravel.application.exceptions.flight.FlightNotFoundException
import com.co.unitravel.application.exceptions.general.GeneralApiErrorCodes
import com.co.unitravel.domain.models.Flight
import com.co.unitravel.domain.models.enums.FlightStatus
import com.co.unitravel.domain.models.filter.FlightFilterRq
import com.co.unitravel.domain.models.records.PageModel
import com.co.unitravel.infrastructure.ports.`in`.flight.FlightUseCase
import com.co.unitravel.infrastructure.ports.out.airplane.AirplanePort
import com.co.unitravel.infrastructure.ports.out.client.`in`.CityInClientPort
import com.co.unitravel.infrastructure.ports.out.flight.FlightPort
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
open class FlightUseCaseImpl(private val flightPort: FlightPort,
                             private val airplanePort: AirplanePort,
                             private val cityInClientPort: CityInClientPort)
    : FlightUseCase {

    @Transactional(propagation = Propagation.REQUIRED)
    override fun create(flight: Flight): Flight {
        val errorNotFound = FlightNotFoundException()
        val airplane = airplanePort.findById(flight.airplane!!.id!!)
        if(!cityInClientPort.findById(flight.initialCityId!!)){
            errorNotFound.addError(GeneralApiErrorCodes.CITY_NOT_FOUND, arrayOf(flight.initialCityId!!))
            throw errorNotFound
        }else if(!cityInClientPort.findById(flight.finalCityId!!)){
            errorNotFound.addError(GeneralApiErrorCodes.CITY_NOT_FOUND, arrayOf(flight.finalCityId!!))
            throw errorNotFound
        }
        flight.id = null;
        flight.flightStatus = FlightStatus.PROGRAMADO;
        flight.airplane = airplane;
        return flightPort.save(flight);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(id: Long, flight: Flight): Flight {
        return flightPort.update(id, flight);
    }

    @Transactional(readOnly = true)
    override fun getByCriteria(flightFilterRq: FlightFilterRq,
                               rowsPerPage: Int,
                               skip: Int): PageModel<List<Flight>> {
        return flightPort.findByCriteria(flightFilterRq.id,
                                         flightFilterRq.status,
                                         flightFilterRq.initialCityId,
                                         flightFilterRq.finalCityId,
                                         flightFilterRq.departureTime,
                                         rowsPerPage,
                                         skip)
    }

    @Transactional(readOnly = true)
    override fun getById(id: Long): Flight {
        return flightPort.findById(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun updateStatus(id: Long, flightStatus: FlightStatus): Flight {
        val flight = Flight()
        flight.flightStatus = flightStatus
        return flightPort.update(id, flight)
    }

    @Transactional(readOnly = true)
    override fun findAll(flightFilterRq: FlightFilterRq): List<Flight> {
        return flightPort.findAll(flightFilterRq.id,
                            flightFilterRq.status,
                            flightFilterRq.initialCityId,
                            flightFilterRq.finalCityId,
                            flightFilterRq.departureTime)
    }
}
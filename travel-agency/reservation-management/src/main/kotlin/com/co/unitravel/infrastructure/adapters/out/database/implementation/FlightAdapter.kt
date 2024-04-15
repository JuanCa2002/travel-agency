package com.co.unitravel.infrastructure.adapters.out.database.implementation

import com.co.unitravel.application.exceptions.flight.FlightErrorCodes
import com.co.unitravel.application.exceptions.flight.FlightNotFoundException
import com.co.unitravel.domain.models.Flight
import com.co.unitravel.domain.models.enums.FlightStatus
import com.co.unitravel.domain.models.records.PageModel
import com.co.unitravel.infrastructure.adapters.out.database.entities.FlightEntity
import com.co.unitravel.infrastructure.adapters.out.database.mappers.flight.FlightMapper
import com.co.unitravel.infrastructure.adapters.out.database.repository.FlightRepository
import com.co.unitravel.infrastructure.ports.out.flight.FlightPort
import lombok.RequiredArgsConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.math.BigInteger
import java.time.LocalDate
import kotlin.math.ceil

@Component
@RequiredArgsConstructor
class FlightAdapter(private val flightRepository: FlightRepository, private val flightMapper: FlightMapper): FlightPort {

    override fun save(flight: Flight): Flight {
        val savedFlight = flightRepository.save(flightMapper.domainToEntity(flight));
        return flightMapper.entityToDomain(savedFlight);
    }

    override fun update(id: Long, flight: Flight): Flight {
        val errorNotFound = FlightNotFoundException()
        errorNotFound.addError(FlightErrorCodes.FLIGHT_NOT_FOUND, arrayOf(id))
        val flightFound: FlightEntity = flightRepository.findById(id).orElseThrow{errorNotFound};
        flightMapper.mergerToEntity(flightFound, flight);
        return flightMapper.entityToDomain(flightRepository.save(flightFound))
    }

    override fun findByCriteria(
        id: Long?,
        status: FlightStatus?,
        initialCityId: Long?,
        finalCityId:Long?,
        departureTime: LocalDate?,
        rowsPerPage: Int,
        skip: Int
    ): PageModel<List<Flight>> {
        val pageNumber: Int = ceil(skip.toDouble() / rowsPerPage).toInt()
        val pageable: Pageable = PageRequest.of(pageNumber, rowsPerPage)

        val page: Page<FlightEntity> = flightRepository.findByCriteria(id, status,initialCityId, finalCityId, departureTime, pageable);
        return PageModel(flightMapper.entitiesToDomains(page.content), BigInteger.valueOf(page.totalElements))
    }
}
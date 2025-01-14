package com.co.unitravel.infrastructure.adapters.out.database.implementation

import com.co.unitravel.application.exceptions.seat.SeatErrorCodes
import com.co.unitravel.application.exceptions.seat.SeatNotFoundException
import com.co.unitravel.domain.models.Seat
import com.co.unitravel.domain.models.enums.SeatClass
import com.co.unitravel.infrastructure.adapters.out.database.entities.SeatEntity
import com.co.unitravel.infrastructure.adapters.out.database.mappers.seat.SeatMapper
import com.co.unitravel.infrastructure.adapters.out.database.repository.SeatRepository
import com.co.unitravel.infrastructure.ports.out.seat.SeatPort
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class SeatAdapter(private val seatMapper: SeatMapper,
                  private val seatRepository: SeatRepository):
        SeatPort {

    override fun update(id: Long, seat: Seat): Seat {
        val errorNotFound = SeatNotFoundException()
        errorNotFound.addError(SeatErrorCodes.SEAT_NOT_FOUND, arrayOf(id))
        var seatFound: SeatEntity = seatRepository.findById(id).orElseThrow{errorNotFound};
        seatFound = SeatMapper.mergeToEntity(seatFound, seat);
        return seatMapper.entityToDomain(seatRepository.save(seatFound))
    }

    override fun findById(id: Long): Seat {
        val errorNotFound = SeatNotFoundException()
        errorNotFound.addError(SeatErrorCodes.SEAT_NOT_FOUND, arrayOf(id))
        return seatMapper.entityToDomain(seatRepository.findById(id).orElseThrow{errorNotFound});
    }

    override fun findByAirplane(airplaneId: Long): List<Seat> {
        val errorNotFound = SeatNotFoundException()
        errorNotFound.addError(SeatErrorCodes.SEAT_NOT_FOUND, arrayOf(airplaneId))
        return seatMapper.entitiesToDomains(seatRepository.findByAirplane(airplaneId));
    }

    override fun findByCustomerAndAirplane(customerId: Long, airplaneId: Long, flightId: Long): List<Seat> {
        val errorNotFoundException = SeatNotFoundException()
        errorNotFoundException.addError(SeatErrorCodes.SEAT_INVALID_CUSTOMER, arrayOf(customerId, flightId))
        val foundSeatList = seatRepository.findByCustomerAndAirplane(customerId, airplaneId)
        if(foundSeatList.isEmpty()) throw errorNotFoundException
        return seatMapper.entitiesToDomains(foundSeatList)
    }

    override fun saveAll(seats: List<Seat>): List<Seat> {
        val seatsToSave: List<SeatEntity> = seatMapper.domainsToEntities(seats)
        val savedSeats = seatRepository.saveAll(seatsToSave)
        return seatMapper.entitiesToDomains(savedSeats)
    }

    override fun updateAll(seats: List<Seat>, ids: List<Long>): List<Seat> {
        var targets = seatRepository.findAllById(ids)
        targets =  SeatMapper.mergeToEntities(targets, seats)
        return seatMapper.entitiesToDomains(seatRepository.saveAll(targets))
    }

    override fun findByIds(ids: List<Long>): List<Seat> {
        val foundSeats = seatRepository.findAllById(ids)
        return seatMapper.entitiesToDomains(foundSeats)
    }

    override fun findByCustomer(customerId: Long): List<Seat> {
        val seatsByCustomer = seatRepository.findByCustomer(customerId)
        return seatMapper.entitiesToDomains(seatsByCustomer)
    }

    override fun findByClassAndAirplane(seatClass: SeatClass, airplaneId: Long): List<Seat> {
        val errorNotFound = SeatNotFoundException()
        errorNotFound.addError(SeatErrorCodes.SEATS_AIRPLANE_CLASS_NOT_FOUND, arrayOf(airplaneId, seatClass))
        val foundSeats = seatRepository.findByClassAndAirplane(seatClass, airplaneId)
        if(foundSeats.isEmpty()) throw errorNotFound
        return seatMapper.entitiesToDomains(foundSeats)
    }

}
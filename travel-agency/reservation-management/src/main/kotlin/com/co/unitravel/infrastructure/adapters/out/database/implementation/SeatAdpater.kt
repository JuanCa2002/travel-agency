package com.co.unitravel.infrastructure.adapters.out.database.implementation

import com.co.unitravel.application.exceptions.seat.SeatErrorCodes
import com.co.unitravel.application.exceptions.seat.SeatNotFoundException
import com.co.unitravel.domain.models.Seat
import com.co.unitravel.infrastructure.adapters.out.database.entities.SeatEntity
import com.co.unitravel.infrastructure.adapters.out.database.mappers.seat.SeatMapper
import com.co.unitravel.infrastructure.adapters.out.database.repository.SeatRepository
import com.co.unitravel.infrastructure.ports.out.seat.SeatPort
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class SeatAdpater(private val seatMapper: SeatMapper,
                  private val seatRepository: SeatRepository):
        SeatPort {

    override fun save(seat: Seat): Seat {
        val savedSeat = seatRepository.save(seatMapper.domainToEntity(seat));
        return seatMapper.entityToDomain(savedSeat);
    }

    override fun update(id: Long, seat: Seat): Seat {
        val errorNotFound = SeatNotFoundException()
        errorNotFound.addError(SeatErrorCodes.SEAT_NOT_FOUND, arrayOf(id))
        val seatFound: SeatEntity = seatRepository.findById(id).orElseThrow{errorNotFound};
        seatMapper.mergerToEntity(seatFound, seat);
        return seatMapper.entityToDomain(seatRepository.save(seatFound))
    }
}
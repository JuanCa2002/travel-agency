package com.co.unitravel.infrastructure.adapters.out.database.implementation

import com.co.unitravel.application.exceptions.reservation.ReservationErrorCodes
import com.co.unitravel.application.exceptions.reservation.ReservationNotFoundException
import com.co.unitravel.domain.models.Reservation
import com.co.unitravel.infrastructure.adapters.out.database.entities.ReservationEntity
import com.co.unitravel.infrastructure.adapters.out.database.mappers.reservation.ReservationMapper
import com.co.unitravel.infrastructure.adapters.out.database.repository.ReservationRespository
import com.co.unitravel.infrastructure.ports.out.reservation.ReservationPort
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class ReservationAdapter(private val reservationRepository: ReservationRespository, private val reservationMapper: ReservationMapper):ReservationPort {

    override fun save(reservation: Reservation): Reservation {
        val savedReservation = reservationRepository.save(reservationMapper.domainToEntity(reservation));
        return reservationMapper.entityToDomain(savedReservation);
    }

    override fun update(id: Long, reservation: Reservation): Reservation {
        val errorNotFound = ReservationNotFoundException()
        errorNotFound.addError(ReservationErrorCodes.RESERVATION_NOT_FOUND, arrayOf(id))
        val reservationFound: ReservationEntity = reservationRepository.findById(id).orElseThrow{errorNotFound};
        reservationMapper.mergerToEntity(reservationFound, reservation);
        return reservationMapper.entityToDomain(reservationRepository.save(reservationFound))
    }

    override fun findById(id: Long): Reservation {
        val errorNotFound = ReservationNotFoundException()
        errorNotFound.addError(ReservationErrorCodes.RESERVATION_NOT_FOUND, arrayOf(id))
        return reservationMapper.entityToDomain(reservationRepository.findById(id).orElseThrow{errorNotFound});
    }
}
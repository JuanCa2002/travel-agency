package com.co.unitravel.application.usecases

import com.co.unitravel.application.exceptions.general.GeneralApiErrorCodes
import com.co.unitravel.application.exceptions.reservation.ReservationNotFoundException
import com.co.unitravel.domain.models.Reservation
import com.co.unitravel.domain.models.enums.ReservationStatus
import com.co.unitravel.domain.models.filter.ReservationFilterRq
import com.co.unitravel.infrastructure.ports.`in`.reservation.ReservationUseCase
import com.co.unitravel.infrastructure.ports.out.client.`in`.AccommodationInClientPort
import com.co.unitravel.infrastructure.ports.out.client.`in`.UserInClientPort
import com.co.unitravel.infrastructure.ports.out.flight.FlightPort
import com.co.unitravel.infrastructure.ports.out.reservation.ReservationPort
import com.co.unitravel.infrastructure.ports.out.seat.SeatPort
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@RequiredArgsConstructor
open class ReservationUseCaseImpl(private val reservationPort: ReservationPort,
                                  private val flightPort: FlightPort,
                                  private val userInClientPort: UserInClientPort,
                                  private val accommodationInClientPort: AccommodationInClientPort) :
    ReservationUseCase {

    @Transactional(propagation = Propagation.REQUIRED)
    override fun create(reservation: Reservation): Reservation {
        val errorNotFound = ReservationNotFoundException()
        val flight = flightPort.findById(reservation.flight!!.id!!)
        val o = accommodationInClientPort.findById(reservation.accommodationId!!)
        println("ACCOMMODATION$o")
        if(!userInClientPort.findById(reservation.customerId!!)){
            errorNotFound.addError(GeneralApiErrorCodes.USER_NOT_FOUND, arrayOf(reservation.customerId!!))
            throw errorNotFound
        }else if(!accommodationInClientPort.findById(reservation.accommodationId!!)){
            errorNotFound.addError(GeneralApiErrorCodes.ACCOMMODATION_NOT_FOUND, arrayOf(reservation.accommodationId!!))
            throw errorNotFound
        }
        reservation.id = null
        reservation.flight = flight
        reservation.reservationDate = LocalDate.now()
        reservation.finalPrice = 2000000
        reservation.reservationStatus = ReservationStatus.PENDIENTE
        return reservationPort.save(reservation)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(id: Long, reservation: Reservation): Reservation {
        if(reservation.flight != null && reservation.flight!!.id != null){
            val flight = flightPort.findById(reservation.flight!!.id!!)
            reservation.flight = flight
        }

        return reservationPort.update(id, reservation)
    }

    @Transactional(readOnly = true)
    override fun getById(id: Long): Reservation {
        return reservationPort.findById(id)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun updateStatus(id: Long, reservationStatus: ReservationStatus): Reservation {
        val reservation = Reservation()
        reservation.reservationStatus = reservationStatus
        return reservationPort.update(id, reservation)
    }

    @Transactional(readOnly = true)
    override fun findAllByCriteria(reservationFilterRq: ReservationFilterRq): List<Reservation> {
        return reservationPort.findAllByCriteria(reservationFilterRq.customerId,
            reservationFilterRq.accommodationId,
            reservationFilterRq.checkInDate,
            reservationFilterRq.reservationStatus)
    }
}
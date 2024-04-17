package com.co.unitravel.infrastructure.ports.out.reservation

import com.co.unitravel.domain.models.Reservation
import com.co.unitravel.domain.models.enums.ReservationStatus
import java.time.LocalDate

interface ReservationPort {

    fun save(reservation: Reservation): Reservation

    fun update(id: Long, reservation: Reservation): Reservation

    fun findById(id:Long): Reservation

    fun findAllByCriteria(accommodationId: Long?,
                          customerId: Long?,
                          checkInDate: LocalDate?,
                          reservationStatus: ReservationStatus?):List<Reservation>
}
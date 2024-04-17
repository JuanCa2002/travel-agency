package com.co.unitravel.infrastructure.ports.`in`.reservation

import com.co.unitravel.domain.models.Reservation
import com.co.unitravel.domain.models.enums.ReservationStatus
import com.co.unitravel.domain.models.filter.ReservationFilterRq

interface ReservationUseCase {

    fun create(reservation: Reservation): Reservation

    fun update(id: Long, reservation: Reservation): Reservation

    fun getById(id:Long): Reservation

    fun updateStatus(id: Long, reservationStatus: ReservationStatus): Reservation

    fun findAllByCriteria(reservationFilterRq: ReservationFilterRq): List<Reservation>
}
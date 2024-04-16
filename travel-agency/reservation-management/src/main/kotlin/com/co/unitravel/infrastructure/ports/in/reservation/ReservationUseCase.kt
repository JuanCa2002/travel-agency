package com.co.unitravel.infrastructure.ports.`in`.reservation

import com.co.unitravel.domain.models.Reservation

interface ReservationUseCase {

    fun create(reservation: Reservation): Reservation

    fun update(id: Long, reservation: Reservation): Reservation

    fun getById(id:Long): Reservation
}
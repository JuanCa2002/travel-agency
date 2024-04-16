package com.co.unitravel.infrastructure.ports.out.reservation

import com.co.unitravel.domain.models.Reservation

interface ReservationPort {

    fun save(reservation: Reservation): Reservation

    fun update(id: Long, reservation: Reservation): Reservation

    fun findById(id:Long): Reservation
}
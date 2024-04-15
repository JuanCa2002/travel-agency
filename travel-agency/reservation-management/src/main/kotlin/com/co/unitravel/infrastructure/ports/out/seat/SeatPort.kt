package com.co.unitravel.infrastructure.ports.out.seat

import com.co.unitravel.domain.models.Seat

interface SeatPort {

    fun save(seat: Seat): Seat

    fun update(id: Long, seat: Seat): Seat

    fun findById(id:Long): Seat

    fun findByAirplane(airplaneId: Long): List<Seat>

}
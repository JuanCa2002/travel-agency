package com.co.unitravel.infrastructure.ports.out.seat

import com.co.unitravel.domain.models.Seat
import com.co.unitravel.domain.models.enums.SeatClass

interface SeatPort {

    fun save(seat: Seat): Seat

    fun update(id: Long, seat: Seat): Seat
}
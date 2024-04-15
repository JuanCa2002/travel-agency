package com.co.unitravel.infrastructure.ports.`in`.seat

import com.co.unitravel.domain.models.Seat
import com.co.unitravel.domain.models.enums.SeatClass

interface SeatUseCase {

    fun create(seat: Seat):Seat

    fun update(id: Long, seat: Seat): Seat

}
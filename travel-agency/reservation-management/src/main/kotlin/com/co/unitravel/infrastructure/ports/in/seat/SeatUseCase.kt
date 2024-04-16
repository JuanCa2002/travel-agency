package com.co.unitravel.infrastructure.ports.`in`.seat

import com.co.unitravel.domain.models.Seat
import com.co.unitravel.domain.models.enums.SeatStatus

interface SeatUseCase {

    fun create(seat: Seat):Seat

    fun update(id: Long, seat: Seat): Seat

    fun getById(id:Long): Seat

    fun updateStatus(id: Long, seatStatus: SeatStatus): Seat

    fun getByAirplane(airplaneId: Long): List<Seat>

    //fun getByCustomerId(customerId: Long): Seat


}
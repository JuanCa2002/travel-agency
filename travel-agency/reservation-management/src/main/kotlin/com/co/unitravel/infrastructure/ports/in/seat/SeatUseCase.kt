package com.co.unitravel.infrastructure.ports.`in`.seat

import com.co.unitravel.domain.models.Seat
import com.co.unitravel.domain.models.enums.SeatClass
import com.co.unitravel.domain.models.enums.SeatStatus

interface SeatUseCase {

    fun create(seat: Seat, stock: Int):List<Seat>

    fun updateAll(ids: List<Long>, customerId: Long, seatStatus: SeatStatus): List<Seat>

    fun getById(id:Long): Seat

    fun updateStatus(id: Long, seatStatus: SeatStatus): Seat

    fun getByAirplane(airplaneId: Long): List<Seat>

    fun getByCustomer(customerId: Long): List<Seat>

    fun updatePrice(price: Long, seatClass: SeatClass, airplaneId: Long): List<Seat>


}
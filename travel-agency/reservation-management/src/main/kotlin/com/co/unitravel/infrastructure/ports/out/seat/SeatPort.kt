package com.co.unitravel.infrastructure.ports.out.seat

import com.co.unitravel.domain.models.Seat
import com.co.unitravel.domain.models.enums.SeatClass

interface SeatPort {

    fun update(id: Long, seat: Seat): Seat

    fun findById(id:Long): Seat

    fun findByAirplane(airplaneId: Long): List<Seat>

    fun findByCustomerAndAirplane(customerId: Long, airplaneId: Long, flightId: Long): List<Seat>

    fun saveAll(seats: List<Seat>): List<Seat>

    fun updateAll(seats: List<Seat>, ids: List<Long>): List<Seat>

    fun findByIds(ids: List<Long>): List<Seat>

    fun findByCustomer(customerId: Long): List<Seat>

    fun findByClassAndAirplane(seatClass: SeatClass, airplaneId: Long): List<Seat>

}
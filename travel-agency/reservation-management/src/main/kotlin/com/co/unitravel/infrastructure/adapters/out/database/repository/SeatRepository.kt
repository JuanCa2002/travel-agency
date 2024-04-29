package com.co.unitravel.infrastructure.adapters.out.database.repository

import com.co.unitravel.domain.models.enums.SeatClass
import com.co.unitravel.infrastructure.adapters.out.database.entities.SeatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SeatRepository: JpaRepository<SeatEntity, Long>  {

    @Query("SELECT S FROM SeatEntity S WHERE S.airplane.id = :airplaneId")
    fun findByAirplane(@Param("airplaneId") airplaneId: Long): List<SeatEntity>

    @Query("SELECT S FROM SeatEntity S WHERE S.customerId = :customerId " +
            "AND S.airplane.id = :airplaneId AND S.seatStatus IN ('RESERVADO', 'COMPRADO')")
    fun findByCustomerAndAirplane(@Param("customerId") customerId:Long,
                                @Param("airplaneId") airplaneId: Long): List<SeatEntity>

    @Query("SELECT S FROM SeatEntity S WHERE S.customerId = :customerId")
    fun findByCustomer(@Param("customerId") customerId: Long): List<SeatEntity>

    @Query("SELECT S FROM SeatEntity S WHERE S.seatClass = :seatClass AND S.airplane.id = :airplaneId")
    fun findByClassAndAirplane(@Param("seatClass") seatClass: SeatClass,
                               @Param("airplaneId") airplaneId: Long): List<SeatEntity>

}
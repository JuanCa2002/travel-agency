package com.co.unitravel.infrastructure.adapters.out.database.repository

import com.co.unitravel.infrastructure.adapters.out.database.entities.SeatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SeatRepository: JpaRepository<SeatEntity, Long>  {

    @Query("SELECT S FROM SeatEntity S WHERE S.airplane.id = :airplaneId")
    fun findByAirplane(@Param("airplaneId") airplaneId: Long): List<SeatEntity>
}
package com.co.unitravel.infrastructure.adapters.out.database.repository

import com.co.unitravel.domain.models.enums.FlightStatus
import com.co.unitravel.infrastructure.adapters.out.database.entities.FlightEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface FlightRepository: JpaRepository<FlightEntity, Long> {

    @Query("SELECT F FROM FlightEntity F WHERE (:id IS NULL OR F.id = :id)" +
            "AND (:status IS NULL OR F.flightStatus = :flight_status)")
    fun findByCriteria(@Param("id") id:Long?,
                       @Param("flight_status") flightStatus: FlightStatus?,
                       pageable: Pageable
    ): Page<FlightEntity>
}
package com.co.unitravel.infrastructure.adapters.out.database.repository

import com.co.unitravel.domain.models.enums.FlightStatus
import com.co.unitravel.infrastructure.adapters.out.database.entities.FlightEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface FlightRepository: JpaRepository<FlightEntity, Long> {

    @Query("SELECT F FROM FlightEntity F WHERE (:id IS NULL OR F.id = :id)" +
            "AND (:initialCityId IS NULL OR F.initialCityId = :initialCityId) " +
            "AND (:status IS NULL OR F.flightStatus = :status) " +
            "AND (:finalCityId IS NULL OR F.finalCityId = :finalCityId) " +
            "AND (:departureTime IS NULL OR F.departureTime = :departureTime)")
    fun findByCriteria(@Param("id") id:Long?,
                       @Param("status") flightStatus: FlightStatus?,
                       @Param("initialCityId") initialCityId: Long?,
                       @Param("finalCityId") finalCityId:Long?,
                       @Param("departureTime") departureTime: LocalDate?,
                       pageable: Pageable
    ): Page<FlightEntity>
}
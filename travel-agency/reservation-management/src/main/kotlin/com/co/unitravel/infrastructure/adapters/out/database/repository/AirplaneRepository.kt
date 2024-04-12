package com.co.unitravel.infrastructure.adapters.out.database.repository

import com.co.unitravel.domain.models.enums.AirplaneStatus
import com.co.unitravel.infrastructure.adapters.out.database.entities.AirplaneEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AirplaneRepository: JpaRepository<AirplaneEntity, Long> {

    @Query("SELECT AI FROM AirplaneEntity AI WHERE (:id IS NULL OR AI.id = :id)" +
           "AND (:status IS NULL OR AI.status = :status)")
    fun findByCriteria(@Param("id") id:Long?,
                       @Param("status") status: AirplaneStatus?,
                       pageable: Pageable
                       ): Page<AirplaneEntity>

}
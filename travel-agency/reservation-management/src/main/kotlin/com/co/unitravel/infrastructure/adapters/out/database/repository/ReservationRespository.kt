package com.co.unitravel.infrastructure.adapters.out.database.repository

import com.co.unitravel.infrastructure.adapters.out.database.entities.ReservationEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRespository: JpaRepository<ReservationEntity, Long> {
}
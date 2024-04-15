package com.co.unitravel.infrastructure.adapters.out.database.repository

import com.co.unitravel.infrastructure.adapters.out.database.entities.SeatEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository: JpaRepository<SeatEntity, Long>  {
}
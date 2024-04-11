package com.co.unitravel.infrastructure.adapters.out.database.repository

import com.co.unitravel.infrastructure.adapters.out.database.entities.AirplaneEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AirplaneRepository: JpaRepository<AirplaneEntity, Long> {
}
package com.co.unitravel.infrastructure.ports.out.airplane

import com.co.unitravel.domain.models.Airplane

fun interface AirplanePort {
    fun save(airplane: Airplane): Airplane
}
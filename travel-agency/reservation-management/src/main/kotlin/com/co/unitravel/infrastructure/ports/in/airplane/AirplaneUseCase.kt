package com.co.unitravel.infrastructure.ports.`in`.airplane

import com.co.unitravel.domain.models.Airplane

interface AirplaneUseCase {

    fun create(airplane: Airplane): Airplane;
}
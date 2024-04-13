package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request

import com.co.unitravel.domain.models.enums.AirplaneStatus
import jakarta.validation.constraints.NotNull

data class AirplaneUpdateRequest(

    @field:NotNull
    val id: Long?,

    var model:String? = null,

    var numberSeats:Int? = null,

    var status: AirplaneStatus? = null
)
package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request

import com.co.unitravel.domain.models.enums.AirplaneStatus
import jakarta.validation.constraints.NotNull

data class AirplaneUpdateRequest(

    @NotNull
    var id: Long = 0,

    var model:String? = null,

    var numberSeats:Int? = null,

    var status: AirplaneStatus? = null
)
package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request

import jakarta.validation.constraints.NotNull

data class AirplaneRequest (

    @NotNull
    var model:String? = null,

    @NotNull
    var numberSeats:Int? = null
)
package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request

import jakarta.validation.constraints.NotNull

data class AirplaneRequest (

    @field:NotNull
    val model:String,

    @field:NotNull
    val numberSeats:Int?
)
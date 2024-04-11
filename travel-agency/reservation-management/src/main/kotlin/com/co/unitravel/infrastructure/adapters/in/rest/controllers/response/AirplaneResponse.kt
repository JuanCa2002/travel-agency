package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response

import com.co.unitravel.domain.models.enums.AirplaneStatus

data class AirplaneResponse (

    var id: Int? = null,

    var model:String? = null,

    var numberSeats:Int? = null,

    var status: AirplaneStatus? = null
)
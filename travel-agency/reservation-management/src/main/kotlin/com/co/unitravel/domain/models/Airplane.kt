package com.co.unitravel.domain.models

import com.co.unitravel.domain.models.enums.AirplaneStatus

data class Airplane (

    var id: Long? = null,

    var model:String? = null,

    var numberSeats:Int? = null,

    var status: AirplaneStatus? = null,
)
package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response

import com.co.unitravel.domain.models.enums.SeatClass
import com.co.unitravel.domain.models.enums.SeatStatus

data class SeatResponse(

        var id: Int? = null,

        var airplane: AirplaneResponse? = null,

        var numberSeat: Int? = null,

        var seatClass: SeatClass? = null,

        var price: Long? = null,

        var customerId: Long? = null,

        var seatStatus: SeatStatus? = null
)

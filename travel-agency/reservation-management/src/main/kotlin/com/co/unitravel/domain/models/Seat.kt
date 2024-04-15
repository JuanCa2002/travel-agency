package com.co.unitravel.domain.models

import com.co.unitravel.domain.models.enums.SeatClass
import com.co.unitravel.domain.models.enums.SeatStatus

data class Seat(

        var id: Long? = null,

        var airplane: Airplane? = Airplane(),

        var numberSeat: Int? = null,

        var seatClass: SeatClass? = null,

        var price: Int? = null,

        var customerId: Long? = null,

        var seatStatus: SeatStatus? = null

)

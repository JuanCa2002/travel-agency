package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request

import com.co.unitravel.domain.models.enums.SeatClass
import jakarta.validation.constraints.NotNull

data class SeatRequest(

        @field:NotNull
        val airplaneId: Long,

        @field:NotNull
        val numberSeat:Int,

        @field:NotNull
        val seatClass: SeatClass,

        @field:NotNull
        val price: Int
)

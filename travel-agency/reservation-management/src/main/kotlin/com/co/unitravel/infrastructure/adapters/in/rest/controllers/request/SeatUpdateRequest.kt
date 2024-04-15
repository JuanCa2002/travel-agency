package com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request

import com.co.unitravel.domain.models.enums.SeatStatus
import jakarta.validation.constraints.NotNull

data class SeatUpdateRequest(

        @field:NotNull
        val id: Long?,

        val price: Int?,

        val customerId: Long?,

        val seatStatus: SeatStatus?
)

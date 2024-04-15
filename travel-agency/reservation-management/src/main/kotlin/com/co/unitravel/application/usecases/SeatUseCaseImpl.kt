package com.co.unitravel.application.usecases

import com.co.unitravel.domain.models.Seat
import com.co.unitravel.domain.models.enums.SeatStatus
import com.co.unitravel.infrastructure.ports.`in`.seat.SeatUseCase
import com.co.unitravel.infrastructure.ports.out.seat.SeatPort
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
open class SeatUseCaseImpl(private val seatPort: SeatPort): SeatUseCase {

    @Transactional(propagation = Propagation.REQUIRED)
    override fun create(seat: Seat): Seat {
        seat.id = null;
        seat.seatStatus = SeatStatus.DISPONIBLE;
        return seatPort.save(seat);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(id: Long, seat: Seat): Seat {
        return seatPort.update(id, seat);
    }
}
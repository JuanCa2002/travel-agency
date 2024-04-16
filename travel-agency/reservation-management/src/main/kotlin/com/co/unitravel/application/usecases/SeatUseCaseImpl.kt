package com.co.unitravel.application.usecases

import com.co.unitravel.domain.models.Airplane
import com.co.unitravel.domain.models.Seat
import com.co.unitravel.domain.models.enums.SeatStatus
import com.co.unitravel.infrastructure.ports.`in`.seat.SeatUseCase
import com.co.unitravel.infrastructure.ports.out.airplane.AirplanePort
import com.co.unitravel.infrastructure.ports.out.seat.SeatPort
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
open class SeatUseCaseImpl(private val seatPort: SeatPort, private val airplanePort: AirplanePort): SeatUseCase {

    @Transactional(propagation = Propagation.REQUIRED)
    override fun create(seat: Seat): Seat {
        val airplane = airplanePort.findById(seat.airplane!!.id!!)
        seat.id = null;
        seat.seatStatus = SeatStatus.DISPONIBLE;
        seat.airplane = airplane
        return seatPort.save(seat);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(id: Long, seat: Seat): Seat {
        return seatPort.update(id, seat);
    }

    @Transactional(readOnly = true)
    override fun getById(id: Long): Seat {
        return seatPort.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun updateStatus(id: Long, seatStatus: SeatStatus): Seat {
        val seat = Seat()
        seat.seatStatus = seatStatus
        return seatPort.update(id, seat)
    }

    @Transactional(readOnly = true)
    override fun getByAirplane(airplaneId: Long): List<Seat> {
        return seatPort.findByAirplane(airplaneId);
    }

    //@Transactional(readOnly = true)
    //override fun getByCustomerId(customerId: Long): Seat {
    //    return seatPort.findByCustomer((customerId))
    //}
}
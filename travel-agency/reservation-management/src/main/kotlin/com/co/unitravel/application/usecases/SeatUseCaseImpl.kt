package com.co.unitravel.application.usecases

import com.co.unitravel.application.exceptions.general.GeneralApiErrorCodes
import com.co.unitravel.application.exceptions.seat.SeatBusinessException
import com.co.unitravel.application.exceptions.seat.SeatErrorCodes
import com.co.unitravel.application.exceptions.seat.SeatNotFoundException
import com.co.unitravel.domain.models.Seat
import com.co.unitravel.domain.models.enums.SeatClass
import com.co.unitravel.domain.models.enums.SeatStatus
import com.co.unitravel.infrastructure.ports.`in`.seat.SeatUseCase
import com.co.unitravel.infrastructure.ports.out.airplane.AirplanePort
import com.co.unitravel.infrastructure.ports.out.client.`in`.UserInClientPort
import com.co.unitravel.infrastructure.ports.out.seat.SeatPort
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
open class SeatUseCaseImpl(private val seatPort: SeatPort,
                           private val airplanePort: AirplanePort,
                           private val userInClientPort: UserInClientPort): SeatUseCase {

    @Transactional(propagation = Propagation.REQUIRED)
    override fun create(seat: Seat, stock: Int): List<Seat> {
        val seatsToCreate: MutableList<Seat> = arrayListOf()
        val airplane = airplanePort.findById(seat.airplane!!.id!!)
        val seatsByAirplane = seatPort.findByAirplane(airplane.id!!)
        val error = SeatBusinessException()
        error.addError(SeatErrorCodes.AMOUNT_EXCEEDS_ALLOWED_QUANTITY, arrayOf(
                                                        airplane.model!!, airplane.numberSeats!!,
                                                        airplane.numberSeats!! - seatsByAirplane.size))
        var airplaneSeatAmount = seatsByAirplane.size
        if((airplaneSeatAmount + stock) > airplane.numberSeats!!) throw error
        var cont = 1
        while (stock >= cont){
            airplaneSeatAmount ++
            val seatModel = Seat()
            seatModel.seatClass = seat.seatClass
            seatModel.airplane = airplane
            seatModel.seatStatus = SeatStatus.DISPONIBLE
            seatModel.price = seat.price
            seatModel.numberSeat = airplaneSeatAmount
            seatsToCreate.add(seatModel);
            cont++
        }
        return seatPort.saveAll(seatsToCreate)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun updateAll(ids: List<Long>, customerId: Long, seatStatus: SeatStatus): List<Seat> {
        val errorNotFound:SeatNotFoundException = SeatNotFoundException()
        val error = SeatBusinessException()
        error.addError(SeatErrorCodes.SEAT_STATUS_INVALID, null)
        errorNotFound.addError(GeneralApiErrorCodes.USER_NOT_FOUND, arrayOf(customerId))
        if(!userInClientPort.findById(customerId)) throw errorNotFound
        val seatsToUpdate = seatPort.findByIds(ids)
        if(!validateSeatsAllowToUpdate(seatsToUpdate, seatStatus, customerId)) throw error
        val updatedSeats: MutableList<Seat> = arrayListOf()
        for( seat in seatsToUpdate){
            seat.seatStatus = seatStatus
            if(seatStatus == SeatStatus.DISPONIBLE){
                seat.customerId = null
            }else if(seatStatus == SeatStatus.RESERVADO){
                seat.customerId = customerId
            }
            updatedSeats.add(seat)
        }
        return seatPort.updateAll(updatedSeats, ids);
    }

    @Transactional(readOnly = true)
    override fun getById(id: Long): Seat {
        return seatPort.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun updateStatus(id: Long, seatStatus: SeatStatus): Seat {
        val seat = Seat()
        seat.seatStatus = seatStatus
        seat.id = id
        return seatPort.update(id, seat)
    }

    @Transactional(readOnly = true)
    override fun getByAirplane(airplaneId: Long): List<Seat> {
        return seatPort.findByAirplane(airplaneId);
    }

    @Transactional(readOnly = true)
    override fun getByCustomer(customerId: Long): List<Seat> {
        return seatPort.findByCustomer(customerId)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun updatePrice(price: Long, seatClass: SeatClass, airplaneId: Long): List<Seat> {
        val seats = seatPort.findByClassAndAirplane(seatClass, airplaneId)
        val updatedSeats: MutableList<Seat> = arrayListOf()
        val seatIds = seats.map { it.id!! }
        for(seat in seats){
           seat.price = price
           updatedSeats.add(seat)
        }
        return seatPort.updateAll(updatedSeats,seatIds)
    }


    private fun validateSeatsAllowToUpdate(seats: List<Seat>, seatStatus: SeatStatus, customerId: Long): Boolean{
        var foundSeats: List<Seat> = arrayListOf()
        if(seatStatus == SeatStatus.RESERVADO){
            foundSeats = seats.filter { it.seatStatus!! ==SeatStatus.DISPONIBLE }
        }
        if(seatStatus == SeatStatus.COMPRADO){
            foundSeats = seats.filter { it.seatStatus!! == SeatStatus.RESERVADO && it.customerId == customerId}
        }
        if(seatStatus == SeatStatus.DISPONIBLE){
            foundSeats = seats.filter { (it.seatStatus!! == SeatStatus.RESERVADO || it.seatStatus == SeatStatus.COMPRADO) && it.customerId == customerId}
        }
        return foundSeats.size == seats.size
    }
}
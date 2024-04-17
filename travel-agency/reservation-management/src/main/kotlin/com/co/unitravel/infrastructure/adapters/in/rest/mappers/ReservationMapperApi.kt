package com.co.unitravel.infrastructure.adapters.`in`.rest.mappers

import com.co.unitravel.domain.models.Reservation
import com.co.unitravel.domain.models.filter.ReservationFilterRq
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.filter.ReservationFilterRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.ReservationRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.ReservationUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.ReservationResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface ReservationMapperApi {

    @Mapping(target = "flight.id", source= "flightId")
    fun requestToDomain(reservationRequest: ReservationRequest): Reservation;

    fun domainToResponse(reservation: Reservation): ReservationResponse;

    fun domainsToResponses(reservations: List<Reservation>): List<ReservationResponse>

    @Mapping(target = "flight.id", source= "flightId")
    fun updateRequestToDomain(reservationUpdateRequest: ReservationUpdateRequest): Reservation

    fun filterRequestToRq(reservationFilterRequest: ReservationFilterRequest): ReservationFilterRq
}
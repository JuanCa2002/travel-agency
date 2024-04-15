package com.co.unitravel.infrastructure.adapters.`in`.rest.mappers

import com.co.unitravel.domain.models.Seat
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.SeatRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.request.SeatUpdateRequest
import com.co.unitravel.infrastructure.adapters.`in`.rest.controllers.response.SeatResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.ReportingPolicy

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface SeatMapperApi {

    @Mapping(target = "airplane.id", source= "airplaneId")
    fun requestToDomain(seatRequest: SeatRequest): Seat;

    fun domainToResponse(seat: Seat): SeatResponse;

    fun domainsToResponses(seats: List<Seat>): List<SeatResponse>

    fun updateRequestToDomain(seatupdateRequest: SeatUpdateRequest): Seat

}
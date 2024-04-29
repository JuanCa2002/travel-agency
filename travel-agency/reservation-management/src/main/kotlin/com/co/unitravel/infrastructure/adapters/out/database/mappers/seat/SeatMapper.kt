package com.co.unitravel.infrastructure.adapters.out.database.mappers.seat

import com.co.unitravel.domain.models.Seat
import com.co.unitravel.domain.models.enums.SeatStatus
import com.co.unitravel.infrastructure.adapters.out.database.entities.SeatEntity
import org.mapstruct.*

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface SeatMapper {

    fun domainToEntity(seat: Seat): SeatEntity

    fun entityToDomain(seatEntity: SeatEntity): Seat

    fun entitiesToDomains(seatEntities: List<SeatEntity>): List<Seat>



    fun domainsToEntities(seats: List<Seat>): List<SeatEntity>

    companion object{
        @JvmStatic
        @Named("mergeToEntities")
        fun mergeToEntities(targets: List<SeatEntity>, sources: List<Seat>): List<SeatEntity>{
            val mergeTargets: MutableList<SeatEntity> = arrayListOf()
            if( targets.isEmpty() || sources.isEmpty()) return mergeTargets.toList()
            for (target in targets){
                val source = sources.find { it.id == target.id }
                if (source != null) {
                    mergeTargets.add(mergeToEntity(target, source))
                }
            }
            return mergeTargets.toList()
        }

        @JvmStatic
        @Named("mergeToEntity")
        fun mergeToEntity(target: SeatEntity, source: Seat): SeatEntity{
            if(source.id == target.id){
                if(source.customerId == null && source.seatStatus == SeatStatus.DISPONIBLE){
                    target.customerId = source.customerId
                }
                if(source.customerId!= null){
                    target.customerId = source.customerId
                }
                if(source.price!=null){
                    target.price = source.price
                }
                if(source.seatStatus!=null){
                    target.seatStatus = source.seatStatus
                }
            }
            return target
        }
    }

}
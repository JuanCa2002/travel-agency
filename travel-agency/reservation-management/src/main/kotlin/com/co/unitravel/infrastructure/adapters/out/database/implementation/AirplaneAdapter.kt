package com.co.unitravel.infrastructure.adapters.out.database.implementation

import com.co.unitravel.domain.models.Airplane
import com.co.unitravel.infrastructure.adapters.out.database.mappers.airplane.AirplaneMapper
import com.co.unitravel.infrastructure.adapters.out.database.repository.AirplaneRepository
import com.co.unitravel.infrastructure.ports.out.airplane.AirplanePort
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class AirplaneAdapter( private val airplaneRepository: AirplaneRepository,
                       private val airplaneMapper: AirplaneMapper): AirplanePort{

    override fun save(airplane: Airplane): Airplane {
        val savedAirplane = airplaneRepository.save(airplaneMapper.domainToEntity(airplane));
        return airplaneMapper.entityToDomain(savedAirplane);
    }


}
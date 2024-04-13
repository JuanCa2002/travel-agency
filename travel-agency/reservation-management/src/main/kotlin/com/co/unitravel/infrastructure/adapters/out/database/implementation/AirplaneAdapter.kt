package com.co.unitravel.infrastructure.adapters.out.database.implementation

import com.co.unitravel.application.exceptions.airplane.AirplaneErrorCodes
import com.co.unitravel.application.exceptions.airplane.AirplaneNotFoundException
import com.co.unitravel.domain.models.Airplane
import com.co.unitravel.domain.models.enums.AirplaneStatus
import com.co.unitravel.domain.models.records.PageModel
import com.co.unitravel.infrastructure.adapters.out.database.entities.AirplaneEntity
import com.co.unitravel.infrastructure.adapters.out.database.mappers.airplane.AirplaneMapper
import com.co.unitravel.infrastructure.adapters.out.database.repository.AirplaneRepository
import com.co.unitravel.infrastructure.ports.out.airplane.AirplanePort
import lombok.RequiredArgsConstructor
import org.springframework.context.MessageSource
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.math.BigInteger
import java.util.*
import kotlin.math.ceil

@Component
@RequiredArgsConstructor
class AirplaneAdapter( private val airplaneRepository: AirplaneRepository,
                       private val airplaneMapper: AirplaneMapper)
: AirplanePort{

    override fun save(airplane: Airplane): Airplane {
        val savedAirplane = airplaneRepository.save(airplaneMapper.domainToEntity(airplane));
        return airplaneMapper.entityToDomain(savedAirplane);
    }

    override fun findById(id: Long): Airplane {
        val errorNotFound = AirplaneNotFoundException()
        errorNotFound.addError(AirplaneErrorCodes.AIRPLANE_NOT_FOUND, arrayOf(id))
        return airplaneMapper.entityToDomain(airplaneRepository.findById(id).orElseThrow{errorNotFound});
    }

    override fun findByCriteria(
        id: Long?,
        status: AirplaneStatus?,
        rowsPerPage: Int,
        skip: Int
    ): PageModel<List<Airplane>> {
        val pageNumber: Int = ceil(skip.toDouble() / rowsPerPage).toInt()
        val pageable: Pageable = PageRequest.of(pageNumber, rowsPerPage)

        val page: Page<AirplaneEntity> = airplaneRepository.findByCriteria(id, status, pageable);
        return PageModel(airplaneMapper.entitiesToDomains(page.content), BigInteger.valueOf(page.totalElements))
    }

    override fun update(id:Long, airplane: Airplane):Airplane {
        val errorNotFound = AirplaneNotFoundException()
        errorNotFound.addError(AirplaneErrorCodes.AIRPLANE_NOT_FOUND, arrayOf(id))
        val airplaneFound:AirplaneEntity = airplaneRepository.findById(id).orElseThrow{errorNotFound};
        airplaneMapper.mergerToEntity(airplaneFound, airplane);
        return airplaneMapper.entityToDomain(airplaneRepository.save(airplaneFound))
    }


}
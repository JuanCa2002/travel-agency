package com.co.unitravel.application.usecases

import com.co.unitravel.domain.models.Airplane
import com.co.unitravel.domain.models.enums.AirplaneStatus
import com.co.unitravel.domain.models.records.PageModel
import com.co.unitravel.infrastructure.ports.`in`.airplane.AirplaneUseCase
import com.co.unitravel.infrastructure.ports.out.airplane.AirplanePort
import org.springframework.transaction.annotation.Transactional
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation

@Service
@RequiredArgsConstructor
open class AirplaneUseCaseImpl( private val airplanePort: AirplanePort): AirplaneUseCase {

    @Transactional(propagation = Propagation.REQUIRED)
    override fun create(airplane: Airplane): Airplane {
        airplane.id = null;
        airplane.status = AirplaneStatus.DISPONIBLE;
        return airplanePort.save(airplane);
    }

    @Transactional(readOnly = true)
    override fun getById(id: Long): Airplane {
        return airplanePort.findById(id);
    }

    @Transactional(readOnly = true)
    override fun getByCriteria(
        id: Long?,
        status: AirplaneStatus?,
        rowsPerPage: Int,
        skip: Int
    ): PageModel<List<Airplane>> {
        return airplanePort.findByCriteria(id, status, rowsPerPage, skip)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun update(id: Long, airplane: Airplane): Airplane {
        return airplanePort.update(id, airplane);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    override fun updateStatus(id: Long, airplaneStatus: AirplaneStatus): Airplane {
        val airplane = Airplane()
        airplane.status = airplaneStatus
        return airplanePort.update(id, airplane)
    }
}
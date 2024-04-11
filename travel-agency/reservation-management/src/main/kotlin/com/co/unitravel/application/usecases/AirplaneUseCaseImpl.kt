package com.co.unitravel.application.usecases

import com.co.unitravel.domain.models.Airplane
import com.co.unitravel.domain.models.enums.AirplaneStatus
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
}
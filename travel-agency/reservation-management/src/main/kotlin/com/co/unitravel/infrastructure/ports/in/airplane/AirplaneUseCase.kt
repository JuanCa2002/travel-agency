package com.co.unitravel.infrastructure.ports.`in`.airplane

import com.co.unitravel.domain.models.Airplane
import com.co.unitravel.domain.models.enums.AirplaneStatus
import com.co.unitravel.domain.models.records.PageModel

interface AirplaneUseCase {

    fun create(airplane: Airplane): Airplane

    fun getById(id:Long): Airplane

    fun getByCriteria(id: Long?,
                      status: AirplaneStatus?,
                      rowsPerPage: Int,
                      skip: Int): PageModel<List<Airplane>>

    fun update(id:Long, airplane: Airplane): Airplane

    fun updateStatus(id: Long, airplaneStatus: AirplaneStatus): Airplane
}
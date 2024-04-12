package com.co.unitravel.infrastructure.ports.out.airplane

import com.co.unitravel.domain.models.Airplane
import com.co.unitravel.domain.models.enums.AirplaneStatus
import com.co.unitravel.domain.models.records.PageModel

interface AirplanePort {
    fun save(airplane: Airplane): Airplane

    fun findById(id:Long): Airplane

    fun findByCriteria(id: Long?, status: AirplaneStatus?, rowsPerPage: Int, skip: Int): PageModel<List<Airplane>>

    fun update(id:Long, airplane: Airplane): Airplane
}
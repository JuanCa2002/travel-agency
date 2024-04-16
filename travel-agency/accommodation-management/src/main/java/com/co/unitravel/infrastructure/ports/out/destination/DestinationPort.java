package com.co.unitravel.infrastructure.ports.out.destination;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.domain.models.record.PageModel;

import java.util.List;

public interface DestinationPort {

    Destination save(Destination destination);

    Destination findByCity(Long cityId) throws NotFoundException;

    Destination update(Destination destination) throws NotFoundException;

    Destination findById(Long id) throws NotFoundException;

    boolean existsByCity(Long cityId);

    PageModel<List<Destination>> findByCriteria(String cityName, Integer rowsPerPage, Integer skip);


}

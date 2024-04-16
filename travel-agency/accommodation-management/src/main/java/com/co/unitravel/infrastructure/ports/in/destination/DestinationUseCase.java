package com.co.unitravel.infrastructure.ports.in.destination;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.domain.models.record.PageModel;

import java.util.List;

public interface DestinationUseCase {

    Destination create(Destination destination) throws NotFoundException, BusinessException;

    Destination update(Destination destination) throws NotFoundException;

    Destination getById(Long id) throws NotFoundException;

    Destination getDestinationByCity(Long cityId) throws NotFoundException;

    PageModel<List<Destination>> findByCriteria(String cityName, Integer rowsPerPage, Integer skip);
}

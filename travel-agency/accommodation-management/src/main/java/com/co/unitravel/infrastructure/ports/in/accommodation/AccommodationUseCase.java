package com.co.unitravel.infrastructure.ports.in.accommodation;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Accommodation;
import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.domain.models.record.PageModel;

import java.util.List;

public interface AccommodationUseCase {

    Accommodation create(Accommodation accommodation) throws NotFoundException, BusinessException;

    Accommodation update(Accommodation accommodation) throws NotFoundException;

    PageModel<List<Accommodation>> findByCriteria(Integer rowsPerPage, Integer skip);

    Accommodation getById(Long id) throws NotFoundException;

    Accommodation getByDestination(Long id) throws NotFoundException;

    Accommodation updateStatus(Long id) throws NotFoundException;
}

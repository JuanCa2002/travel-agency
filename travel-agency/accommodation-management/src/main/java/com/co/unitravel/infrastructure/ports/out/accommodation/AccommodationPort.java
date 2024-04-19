package com.co.unitravel.infrastructure.ports.out.accommodation;

import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Accommodation;
import com.co.unitravel.domain.models.record.PageModel;

import java.util.List;

public interface AccommodationPort {

    Accommodation save(Accommodation accommodation);

    Accommodation update(Accommodation accommodation) throws NotFoundException;

    PageModel<List<Accommodation>> findByCriteria(Integer rowsPerPage, Integer skip);

    Accommodation findById(Long id) throws NotFoundException;

    Accommodation findByDestination(Long destinationId) throws NotFoundException;

    boolean existsById(Long id);
}

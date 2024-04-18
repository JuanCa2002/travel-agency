package com.co.unitravel.application.usecases.accommodation;

import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Accommodation;
import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.domain.models.enums.AccommodationStatus;
import com.co.unitravel.domain.models.record.PageModel;
import com.co.unitravel.infrastructure.ports.in.accommodation.AccommodationUseCase;
import com.co.unitravel.infrastructure.ports.out.accommodation.AccommodationPort;
import com.co.unitravel.infrastructure.ports.out.destination.DestinationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationUseCaseImpl implements AccommodationUseCase {

    private final DestinationPort destinationPort;

    private final AccommodationPort accommodationPort;

    @Override
    public Accommodation create(Accommodation accommodation) throws NotFoundException, BusinessException {
        Destination destination = destinationPort.findById(accommodation.getDestination().getId());
        accommodation.setDestination(destination);
        accommodation.setAccommodationStatus(AccommodationStatus.ACTIVO);
        accommodation.setId(null);
        accommodation.setRating(0);
        return accommodationPort.save(accommodation);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Accommodation update(Accommodation accommodation) throws NotFoundException {
        return accommodationPort.update(accommodation);
    }

    @Transactional(readOnly = true)
    @Override
    public PageModel<List<Accommodation>> findByCriteria(Integer rowsPerPage, Integer skip) {
        return accommodationPort.findByCriteria(rowsPerPage, skip);
    }
}

package com.co.unitravel.application.usecases.accommodation;

import com.co.unitravel.application.exceptions.accommodation.AccommodationNotFoundException;
import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.GeneralApiErrorCodes;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Accommodation;
import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.domain.models.enums.AccommodationStatus;
import com.co.unitravel.domain.models.record.PageModel;
import com.co.unitravel.infrastructure.ports.in.accommodation.AccommodationUseCase;
import com.co.unitravel.infrastructure.ports.out.accommodation.AccommodationPort;
import com.co.unitravel.infrastructure.ports.out.client.in.UserInClientPort;
import com.co.unitravel.infrastructure.ports.out.destination.DestinationPort;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    private final UserInClientPort userInClientPort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Accommodation create(Accommodation accommodation) throws NotFoundException, BusinessException, JsonProcessingException {
        Destination destination = destinationPort.findById(accommodation.getDestination().getId());
        AccommodationNotFoundException errorNotFound = new AccommodationNotFoundException();
        errorNotFound.addError(GeneralApiErrorCodes.USER_ACCOMMODATION_NOT_FOUND, new Object[]{accommodation.getAdministratorId()});
        if(!userInClientPort.findById(accommodation.getAdministratorId())) throw errorNotFound;
        accommodation.setDestination(destination);
        accommodation.setAccommodationStatus(AccommodationStatus.ACTIVO);
        accommodation.setId(null);
        accommodation.setRating(0.0);
        accommodation.setSummationVotes(0.0);
        accommodation.setNumberVotes(0);
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

    @Transactional(readOnly = true)
    @Override
    public Accommodation getById(Long id) throws NotFoundException {
        return accommodationPort.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Accommodation getByDestination(Long id) throws NotFoundException {
        return accommodationPort.findByDestination(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Accommodation updateStatus(Long id) throws NotFoundException {
        Accommodation accommodation = accommodationPort.findById(id);
        accommodation.setAccommodationStatus(accommodation.getAccommodationStatus().equals(AccommodationStatus.ACTIVO) ?
                AccommodationStatus.INACTIVO : AccommodationStatus.ACTIVO);
        return accommodationPort.update(accommodation);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Accommodation> getByAdministratorId(Long administratorId) {
        return accommodationPort.findByAdministrator(administratorId);
    }
}

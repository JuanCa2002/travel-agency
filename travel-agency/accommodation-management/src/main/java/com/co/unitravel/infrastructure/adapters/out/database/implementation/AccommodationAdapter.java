package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.application.exceptions.accommodation.AccommodationErrorCodes;
import com.co.unitravel.application.exceptions.accommodation.AccommodationNotFoundException;
import com.co.unitravel.application.exceptions.destination.DestinationErrorCodes;
import com.co.unitravel.application.exceptions.destination.DestinationNotFoundException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Accommodation;
import com.co.unitravel.domain.models.record.PageModel;
import com.co.unitravel.infrastructure.adapters.out.database.entities.AccommodationEntity;
import com.co.unitravel.infrastructure.adapters.out.database.entities.DestinationEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.accommodation.AccommodationMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.AccommodationRepository;
import com.co.unitravel.infrastructure.ports.out.accommodation.AccommodationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AccommodationAdapter implements AccommodationPort {

    private final AccommodationMapper accommodationMapper;

    private final AccommodationRepository accommodationRepository;

    @Override
    public Accommodation save(Accommodation accommodation) {
        AccommodationEntity savedAccommodation = accommodationRepository.save(accommodationMapper.domainToEntity(accommodation));
        return accommodationMapper.entityToDomain(savedAccommodation);
    }

    @Override
    public Accommodation update(Accommodation accommodation) throws NotFoundException {
        AccommodationNotFoundException errorNotFound = new AccommodationNotFoundException();
        errorNotFound.addError(AccommodationErrorCodes.ACCOMMODATION_NOT_FOUND, new Object[]{accommodation.getId()});
        AccommodationEntity target = accommodationRepository.findById(accommodation.getId()).orElseThrow(()->errorNotFound);
        accommodationMapper.mergeToEntity(target, accommodation);
        return accommodationMapper.entityToDomain(accommodationRepository.save(target));
    }

    @Override
    public PageModel<List<Accommodation>> findByCriteria(Integer rowsPerPage, Integer skip) {
        int pageNumber = (int) Math.ceil((double)skip/rowsPerPage);
        Pageable pageable = PageRequest.of(pageNumber, rowsPerPage);

        Page<AccommodationEntity> page = accommodationRepository.findByCriteria(pageable);
        return new PageModel<>(accommodationMapper.entitiesToDomains(page.getContent()), BigInteger.valueOf(page.getTotalElements()));
    }

    @Override
    public Accommodation findById(Long id) throws NotFoundException {
        AccommodationNotFoundException errorNotFound = new AccommodationNotFoundException();
        errorNotFound.addError(AccommodationErrorCodes.ACCOMMODATION_NOT_FOUND, new Object[]{id});
        return accommodationMapper.entityToDomain(accommodationRepository.findById(id).orElseThrow(()-> errorNotFound));
    }

    @Override
    public Accommodation findByDestination(Long destinationId) throws NotFoundException {
        AccommodationNotFoundException errorNotFound = new AccommodationNotFoundException();
        AccommodationEntity foundAccommodation = accommodationRepository.findByDestination(destinationId);
        errorNotFound.addError(AccommodationErrorCodes.ACCOMMODATION_BY_LOCATION_NOT_FOUND, new Object[]{destinationId});
        if(foundAccommodation == null) throw errorNotFound;
        return accommodationMapper.entityToDomain(foundAccommodation);
    }
}

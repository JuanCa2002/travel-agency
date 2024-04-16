package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.application.exceptions.destination.DestinationErrorCodes;
import com.co.unitravel.application.exceptions.destination.DestinationNotFoundException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.domain.models.record.PageModel;
import com.co.unitravel.infrastructure.adapters.out.database.entities.DestinationEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.destination.DestinationMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.DestinationRepository;
import com.co.unitravel.infrastructure.ports.out.destination.DestinationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DestinationAdapter implements DestinationPort {

    private final DestinationMapper destinationMapper;

    private final DestinationRepository destinationRepository;
    @Override
    public Destination save(Destination destination) {
        DestinationEntity savedDestination = destinationRepository.save(destinationMapper.domainToEntity(destination));
        return destinationMapper.entityToDomain(savedDestination);
    }

    @Override
    public Destination findByCity(Long cityId) throws NotFoundException{
        DestinationNotFoundException errorNotFound = new DestinationNotFoundException();
        DestinationEntity foundDestination = destinationRepository.findByCity(cityId);
        errorNotFound.addError(DestinationErrorCodes.DESTINATION_BY_CITY_NOT_FOUND, new Object[]{cityId});
        if(foundDestination == null) throw errorNotFound;
        return destinationMapper.entityToDomain(foundDestination);
    }

    @Override
    public Destination update(Destination destination) throws NotFoundException {
        DestinationNotFoundException errorNotFound = new DestinationNotFoundException();
        errorNotFound.addError(DestinationErrorCodes.DESTINATION_NOT_FOUND, new Object[]{destination.getId()});
        DestinationEntity target = destinationRepository.findById(destination.getId()).orElseThrow(()->errorNotFound);
        destinationMapper.mergeToEntity(target, destination);
        return destinationMapper.entityToDomain(destinationRepository.save(target));
    }

    @Override
    public Destination findById(Long id) throws NotFoundException {
        DestinationNotFoundException errorNotFound = new DestinationNotFoundException();
        errorNotFound.addError(DestinationErrorCodes.DESTINATION_NOT_FOUND, new Object[]{id});
        return destinationMapper.entityToDomain(destinationRepository.findById(id).orElseThrow(()-> errorNotFound));
    }

    @Override
    public boolean existsByCity(Long cityId) {
        return destinationRepository.existsByCity(cityId);
    }

    @Override
    public PageModel<List<Destination>> findByCriteria(String cityName, Integer rowsPerPage, Integer skip) {
        int pageNumber = (int) Math.ceil((double)skip/rowsPerPage);
        Pageable pageable = PageRequest.of(pageNumber, rowsPerPage);

        Page<DestinationEntity> page = destinationRepository.findByCriteria(cityName,pageable);
        return new PageModel<>(destinationMapper.entitiesToDomains(page.getContent()), BigInteger.valueOf(page.getTotalElements()));

    }
}

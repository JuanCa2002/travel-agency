package com.co.unitravel.infrastructure.adapters.out.database.implementation;

import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.infrastructure.adapters.out.database.entities.DestinationEntity;
import com.co.unitravel.infrastructure.adapters.out.database.mappers.destination.DestinationMapper;
import com.co.unitravel.infrastructure.adapters.out.database.repository.DestinationRepository;
import com.co.unitravel.infrastructure.ports.out.destination.DestinationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    public Destination findByCity(Long cityId) {
        DestinationEntity foundDestination = destinationRepository.findByCity(cityId);
        return destinationMapper.entityToDomain(foundDestination);
    }

    @Override
    public boolean existsByCity(Long cityId) {
        return destinationRepository.existsByCity(cityId);
    }
}

package com.co.unitravel.infrastructure.ports.out.destination;

import com.co.unitravel.domain.models.Destination;

public interface DestinationPort {

    Destination save(Destination destination);

    Destination findByCity(Long cityId);

    boolean existsByCity(Long cityId);
}

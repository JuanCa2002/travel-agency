package com.co.unitravel.infrastructure.ports.in.destination;

import com.co.unitravel.application.exceptions.city.CityNotFoundException;
import com.co.unitravel.application.exceptions.destination.DestinationBusinessException;
import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.Destination;

public interface DestinationUseCase {

    Destination create(Destination destination) throws NotFoundException, BusinessException;
}

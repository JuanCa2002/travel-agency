package com.co.unitravel.application.usecases.destination;

import com.co.unitravel.application.exceptions.destination.DestinationBusinessException;
import com.co.unitravel.application.exceptions.destination.DestinationErrorCodes;
import com.co.unitravel.application.exceptions.general.BusinessException;
import com.co.unitravel.application.exceptions.general.NotFoundException;
import com.co.unitravel.domain.models.City;
import com.co.unitravel.domain.models.Destination;
import com.co.unitravel.domain.models.record.PageModel;
import com.co.unitravel.infrastructure.ports.in.destination.DestinationUseCase;
import com.co.unitravel.infrastructure.ports.out.city.CityPort;
import com.co.unitravel.infrastructure.ports.out.destination.DestinationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationUseCaseImpl implements DestinationUseCase {

    private final CityPort cityPort;

    private final DestinationPort destinationPort;
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Destination create(Destination destination) throws NotFoundException, BusinessException {
        City city = cityPort.findById(destination.getCity().getId());
        DestinationBusinessException error = new DestinationBusinessException();
        error.addError(DestinationErrorCodes.DESTINATION_WITH_CITY_ALREADY_EXISTS, new Object[]{city.getName()});
        if(destinationPort.existsByCity(destination.getCity().getId())) throw error;
        destination.setId(null);
        destination.setCity(city);
        return destinationPort.save(destination);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Destination update(Destination destination) throws NotFoundException {
        return destinationPort.update(destination);
    }

    @Transactional(readOnly = true)
    @Override
    public Destination getById(Long id) throws NotFoundException {
        return destinationPort.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Destination getDestinationByCity(Long cityId) throws NotFoundException {
        return destinationPort.findByCity(cityId);
    }

    @Transactional(readOnly = true)
    @Override
    public PageModel<List<Destination>> findByCriteria(String cityName, Integer rowsPerPage, Integer skip) {
        return destinationPort.findByCriteria(cityName, rowsPerPage, skip);
    }
}

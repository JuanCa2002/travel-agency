package com.co.unitravel.infrastructure.ports.out.city;

import com.co.unitravel.application.exceptions.city.CityNotFoundException;
import com.co.unitravel.domain.models.City;

public interface CityPort {

    City save(City city);

    City findById(Long id) throws CityNotFoundException;

    boolean validate(Long id);

}
